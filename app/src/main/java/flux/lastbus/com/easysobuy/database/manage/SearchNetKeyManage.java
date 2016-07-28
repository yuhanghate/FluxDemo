package flux.lastbus.com.easysobuy.database.manage;


import java.util.ArrayList;
import java.util.List;

import flux.lastbus.com.easysobuy.database.core.DaoCore;
import flux.lastbus.com.easysobuy.database.dao.SearchNetKeyDBDao;
import flux.lastbus.com.easysobuy.database.module.SearchNetKeyDB;
import rx.Observable;

/**
 * Created by yuhang on 16-6-29.
 */
public class SearchNetKeyManage extends DaoCore<SearchNetKeyDB, Long>{
    SearchNetKeyDBDao mSearchNetKeyDao;
    public SearchNetKeyManage(SearchNetKeyDBDao dao) {
        super(dao);
        mSearchNetKeyDao = dao;
    }

    /**
     * 热门搜索转转String
     * @return
     */
    public List<String> getListString(){
        final List<String> list = new ArrayList<>();
        List<SearchNetKeyDB> keys = queryAll();
        Observable.from(keys)
                .map(SearchNetKeyDB::getKey)
                .subscribe(s -> list.add(s));
        return list;
    }


    public SearchNetKeyDBDao getDao() {
        return mSearchNetKeyDao;
    }
}
