package flux.lastbus.com.easysobuy.database.core;

import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.QueryBuilder;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 数据库操作基类
 * @param <V>
 * @param <K>
 */
public abstract class DaoCore<V, K> {
    public  AbstractDao<V, K> mDao;


    public DaoCore(AbstractDao dao) {
        mDao = dao;
    }



    /**
     * 保存对象
     * @param item 保存对象
     */
    public void save(V item) {
        mDao.insert(item);
    }

    /**
     * 异步保存
     * @param t
     */
    public void rxSave(V t){
        getObservable(t).subscribe(ts -> save(ts));
    }

    /**
     * 保存多个对象
     * @param items 保存对象
     */
    public void save(V... items) {
        mDao.insertInTx(items);
    }

    /**
     * 异步保存多个对象
     * @param items
     */
    public void rxSave(V... items){
        getObservable(items).subscribe(ts -> save(ts));
    }

    /**
     * 批量保存
     * @param items 保存对象
     */
    public void save(List<V> items) {
        mDao.insertInTx(items);
    }

    /**
     * 异步批量保存
     * @param items
     */
    public void rxSave(List<V> items){
        getObservable(items).subscribe(ts -> save(ts));
    }

    /**
     * 保存或者更新
     * @param item 保存对象
     */
    public void saveOrUpdate(V item) {
        mDao.insertOrReplace(item);
    }

    /**
     * 异步保存或者更新
     * @param item
     */
    public void rxSaveOrUpdate(V item){
        getObservable(item).subscribe(ts -> saveOrUpdate(ts));
    }

    /**
     * 保存或者更新多个对象
     * @param items 保存对象
     */
    public void saveOrUpdate(V... items) {
        mDao.insertOrReplaceInTx(items);
    }

    /**
     * 异步保存或者更新多个对象
     * @param items
     */
    public void rxSaveOrUpdate(V... items){
        getObservable(items).subscribe(ts -> saveOrUpdate(ts));
    }

    /**
     * 批量保存或者更新
     * @param items 对象集合
     */
    public void saveOrUpdate(List<V> items) {
        mDao.insertOrReplaceInTx(items);
    }

    /**
     * 异步批量保存或者更新
     * @param items
     */
    public void rxSaveOrUpdate(List<V> items){
        getObservable(items).subscribe(ts -> saveOrUpdate(ts));
    }

    /**
     * 根据id,删除数据
     * @param key
     */
    public void deleteByKey(K key) {
        mDao.deleteByKey(key);
    }

    /**
     * 根据id,删除数据
     * @param key
     */
    public void rxDeleteByKey(K key){
        getObservableKey(key).subscribe(ks -> deleteByKey(ks));
    }

    /**
     * 删除数据
     * @param item
     */
    public void delete(V item) {
        mDao.delete(item);
    }

    /**
     * 异步删除数据
     * @param item
     */
    public void rxDelete(V item){
        getObservable(item).subscribe(ts -> delete(ts));
    }

    /**
     * 删除多条数据
     * @param items
     */
    public void delete(V... items) {
        mDao.deleteInTx(items);
    }

    /**
     * 批量删除数据
     * @param items
     */
    public void delete(List<V> items) {
        mDao.deleteInTx(items);
    }

    /**
     * 清空表数据
     */
    public void deleteAll() {
        mDao.deleteAll();
    }

    /**
     * 异步清空表数据
     */
    public void rxDeleteAll(){
        Observable.create(subscriber -> deleteAll())
                .subscribeOn(Schedulers.computation())
                .subscribe();
    }

    /**
     * 更新数据
     * @param item
     */
    public void update(V item) {
        mDao.update(item);
    }

    /**
     * 异步更新数据
     * @param item
     */
    public void rxUpdate(V item){
        getObservable(item).subscribe(ts -> update(ts));
    }

    /**
     * 更新多条数据
     * @param items
     */
    public void update(V... items) {
        mDao.updateInTx(items);
    }

    /**
     * 异步更新多条数据
     * @param items
     */
    public void rxUpdate(V... items){
        getObservable(items).subscribe(ts -> update(ts));
    }

    /**
     * 批量更新
     * @param items
     */
    public void update(List<V> items) {
        mDao.updateInTx(items);
    }

    /**
     * 异步批量更新
     * @param items
     */
    public void rxUpdate(List<V> items){
        getObservable(items).subscribe(ts -> update(ts));
    }

    /**
     * 根据id查询数据
     * @param key
     * @return
     */
    public V query(K key) {
        return mDao.load(key);
    }

    /**
     * 异步根据id查询数据
     * @param key
     * @return
     */
    public Observable<V> rxQuery(K key){
        return getObservableKey(key).flatMap(k -> Observable.just(query(k)));
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<V> queryAll() {
        return mDao.loadAll();
    }

    /**
     * 异步查询所有数据
     * @return
     */
    public Observable<List<V>> rxQueryAll(){
        return Observable.create(new Observable.OnSubscribe<List<V>>() {
            @Override
            public void call(Subscriber<? super List<V>> subscriber) {
                subscriber.onNext(queryAll());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation());

    }

    /**
     * 根据条件数据
     * @param where 条件
     * @param params 条件值
     * @return
     */
    public List<V> query(String where, String... params) {

        return mDao.queryRaw(where, params);
    }

    /**
     * 异常根据条件数据
     * @param where
     * @param params
     * @return
     */
    public Observable<List<V>> rxQuery(final String where, final String... params){
        return Observable.create(new Observable.OnSubscribe<List<V>>() {
            @Override
            public void call(Subscriber<? super List<V>> subscriber) {
                subscriber.onNext(query(where, params));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation());

    }

    /**
     * 原生sql查询
     * @return
     */
    public QueryBuilder<V> queryBuilder() {

        return mDao.queryBuilder();
    }

    /**
     * 异常原生查询原生sql查询
     * @return
     */
    public Observable<QueryBuilder<V>> rxQueryBuilder(){
        return Observable.create(new Observable.OnSubscribe<QueryBuilder<V>>() {
            @Override
            public void call(Subscriber<? super QueryBuilder<V>> subscriber) {
                subscriber.onNext(queryBuilder());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation());

    }

    /**
     * 数据总共条数
     * @return
     */
    public long count() {
        return mDao.count();
    }

    public Observable<Long> rxCount(){
         return Observable.create(new Observable.OnSubscribe<Long>() {
             @Override
             public void call(Subscriber<? super Long> subscriber) {
                 subscriber.onNext(count());
                 subscriber.onCompleted();
             }
         }).subscribeOn(Schedulers.computation());
    }

    public void refresh(V item) {
        mDao.refresh(item);
    }


    public void detach(V item) {
        mDao.detach(item);
    }

    /**
     * 返回Observable对象
     * @param items
     * @return
     */
    private Observable<V[]> getObservable(V... items){
        return  Observable.just(items)
                .subscribeOn(Schedulers.computation());
    }

    /**
     * 返回Observable对象
     * @param list
     * @return
     */
    private Observable<List<V>> getObservable(List<V> list){
        return Observable.just(list).subscribeOn(Schedulers.computation());
    }

    /**
     * 返回Observable对象
     * @param items
     * @return
     */
    private Observable<K> getObservableKey(K items){
        return  Observable.just(items)
                .subscribeOn(Schedulers.computation());
    }

    /**
     * 返回Observable对象
     * @param list
     * @return
     */
    private Observable<List<K>> getObservableKey(List<K> list){
        return Observable.just(list).subscribeOn(Schedulers.computation());
    }



}
