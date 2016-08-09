package flux.lastbus.com.easysobuy.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.Unbinder;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.bus.RxBus;
import flux.lastbus.com.easysobuy.di.component.AppComponent;
import flux.lastbus.com.easysobuy.di.component.DaggerFragmentComponent;
import flux.lastbus.com.easysobuy.di.component.FragmentComponent;
import flux.lastbus.com.easysobuy.di.module.FragmentModule;
import flux.lastbus.com.easysobuy.di.qualifier.FragmentCompositeSubscription;
import flux.lastbus.com.easysobuy.di.qualifier.FragmentUnbinder;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.flux.store.event.ChangeEvent;
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Fragment 基类
 * Created by yuhang on 16-8-3.
 */
public abstract class BaseFragment extends Fragment {
    @Inject
    @FragmentUnbinder
    Unbinder mUnbinder;
    @Inject
    @FragmentCompositeSubscription
    CompositeSubscription mCompositeSubscription;
    @Inject
    Dispatcher mDispatcher;

    BaseStore mBaseStore;
    BaseActivity mBaseActivity;
    FragmentComponent mFragmentComponent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(onContentView(), container, false);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        init(view);
        super.onViewCreated(view, savedInstanceState);
        //初始化注入器
        initComponent(view);
        //注册Store
        registerStore();
        //注册RxBus
        onStoreChangeEvent();
        //Fragment初始化
        onInitFragment();
    }


    /**
     * 初始化Fragment注入器
     *
     * @param view
     */
    public void initComponent(View view) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(mBaseActivity.getActivityComponent())
                .fragmentModule(new FragmentModule(this, view))
                .build();

        onInitComponent();
    }

    /**
     * 注册 Store
     */
    public void registerStore(){
        //注册Store
        mBaseStore = onCreateStore();
        if(mBaseStore != null){
            mDispatcher.register(mBaseStore);
        }
    }

    /**
     * 增加Rxjava释放
     *
     * @param subscription
     */
    public void addSubscription(Subscription subscription) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = getFragmentComponent().getCompositeSubscription();
        }

        this.mCompositeSubscription.add(subscription);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        //解绑Store
        if(mBaseStore != null){
            mDispatcher.unregister(mBaseStore);
            mBaseStore = null;
        }


    }




    /************************** 以下为提供方法 ***********************************/



    /**
     * Activity内容布局
     *
     * @return
     */
    public abstract int onContentView();


    /**
     * 生成Store对象
     *
     * @return
     */
    public abstract BaseStore onCreateStore();

    /**
     * 获取分发器
     *
     * @return
     */
    public Dispatcher getDispatcher() {
        return mDispatcher;
    }

    /**
     * 获取Application注入器
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return getApp().getAppComponent();
    }

    /**
     * 获取Activity注入器
     *
     * @return
     */
    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    /**
     * 返回App
     *
     * @return
     */
    public App getApp() {
        return (App) getActivity().getApplication();
    }

    /**
     * Store事件变化通知
     */
    public void onStoreChangeEvent() {
    }

    /**
     * 获取Store对象
     *
     * @return
     */
    public <T extends BaseStore> T getInstanceStore() {
        return (T) mBaseStore;
    }

    /**
     * 注册Store通知事件
     * 线程安全,全部执行在UI线程
     *
     * @param event 注册事件Class类型,
     * @param <V>   observable
     * @return
     */
    public <V extends ChangeEvent> Observable<V> registerEvent(Class<V> event) {
        return RxBus.instance().toObservable(event).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 返回Activity
     * @param <T>
     * @return
     */
    public <T extends BaseActivity> T getAct() {
        return (T) mBaseActivity;
    }

    /**
     * 初始化Fragment
     */
    public abstract void onInitFragment();

    /**
     * 子类DI事件初始化
     */
    public void onInitComponent(){
        getFragmentComponent().inject(this);
    }
}
