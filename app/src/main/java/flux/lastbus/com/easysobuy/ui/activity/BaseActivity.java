package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.dagger.component.ActivityComponent;
import flux.lastbus.com.easysobuy.dagger.component.AppComponent;
import flux.lastbus.com.easysobuy.dagger.component.DaggerActivityComponent;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Activity基类
 * Created by yuhang on 16-7-27.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Inject
    Dispatcher mDispatcher;

    BaseStore mStore;
    Unbinder mUnbinder;

    ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onContentView());

        init();
    }

    /**
     * 初始化信息
     */
    public void init(){
        //注入View布局
//        ButterKnife.bind(this);
        mUnbinder = ButterKnife.bind(this);

        //注入
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .build();
        mActivityComponent.inject(this);

        //注册Store
        mStore = onCreateStore();
        if(mStore != null){
            mDispatcher.register(mStore);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除View注入
//        ButterKnife.unbind(this);

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
    public AppComponent getAppComponent(){return ((App)getApplication()).getAppComponent();}

    /**
     * 获取Activity注入器
     * @return
     */
    public ActivityComponent getActivityComponent(){return mActivityComponent;}

}
