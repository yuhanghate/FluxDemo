package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.Unbinder;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.bus.RxBus;
import flux.lastbus.com.easysobuy.di.component.ActivityComponent;
import flux.lastbus.com.easysobuy.di.component.AppComponent;
import flux.lastbus.com.easysobuy.di.component.DaggerActivityComponent;
import flux.lastbus.com.easysobuy.di.module.ActivityModule;
import flux.lastbus.com.easysobuy.di.qualifier.ActivityCompositeSubscription;
import flux.lastbus.com.easysobuy.di.qualifier.ActivityUnbinder;
import flux.lastbus.com.easysobuy.flux.action.BaseAction;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.flux.store.event.ChangeEvent;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Activity基类
 * Created by yuhang on 16-7-27.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Inject
    Dispatcher mDispatcher;
    @Inject
    @ActivityUnbinder
    Unbinder mUnbinder;
    @Inject
    @ActivityCompositeSubscription
    CompositeSubscription mCompositeSubscription;

    /**
     * View数据提供及View相关逻辑处理类
     */
    BaseStore mStore;

    /**
     * Activity事件注入器
     */
    ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onContentView());
        init();
        onStoreChangeEvent();
    }

    /**
     * 初始化信息
     */
    public void init(){

        /**
         * 创建注入器
         */
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();

        //Activity注入
        onInitComponent();

        //注册Store
        mStore = onCreateStore();
        if(mStore != null){
            mDispatcher.register(mStore);
        }
    }

    /**
     * Subscription对象进行缓存,进行对象释放
     *
     * @param subscription
     */
    public void addSubscription(Subscription subscription) {
        if(mCompositeSubscription == null){
            mCompositeSubscription = getActivityComponent().getCompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Subscription释放
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
            this.mCompositeSubscription = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除View注入
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
        //解绑Store
        if(mStore != null){
            mDispatcher.unregister(mStore);
            mStore = null;
        }

    }

    /************************** 以下为提供方法 ***********************************/


    /**
     * Activity内容布局
     * @return
     */
    public abstract int onContentView();

    /**
     * 生成Store对象
     * @return
     */
    public abstract BaseStore onCreateStore();


    /**
     * 获取Context对象
     * @return
     */
    public Context getContext(){return this;}

    /**
     * 获取分发器
     * @return
     */
    public Dispatcher getDispatcher(){return mDispatcher;}

    /**
     * 获取Application注入器
     * @return
     */
    public AppComponent getAppComponent(){return getApp().getAppComponent();}

    /**
     * 获取Activity注入器
     * @return
     */
    public ActivityComponent getActivityComponent(){return mActivityComponent;}

    /**
     * 返回App
     * @return
     */
    public App getApp(){return (App) getApplication();}

    /**
     * Store事件变化通知
     */
    public void onStoreChangeEvent(){}

    /**
     * 获取Store对象
     * @return
     */
    public <T extends BaseStore> T getStore(){
        return (T) mStore;
    }

    /**
     * 注册Store通知事件
     * @param event 注册事件Class类型,
     * @param <V> observable
     * @return
     */
    public <V extends ChangeEvent> Observable<V> registerEvent(Class<V> event){
        return RxBus.instance().toObservable(event);
    }

    /**
     * 子类DI事件初始化
     */
    public void onInitComponent(){
        getActivityComponent().inject(this);
    }

    /**
     * 分发数据到Store层
     * @param action
     */
    public void dispatch(BaseAction action){
        getDispatcher().dispatch(action);
    }

}
