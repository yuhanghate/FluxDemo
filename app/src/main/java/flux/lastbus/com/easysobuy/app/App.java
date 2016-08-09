package flux.lastbus.com.easysobuy.app;

import android.app.Application;

import javax.inject.Inject;

import de.greenrobot.dao.query.QueryBuilder;
import flux.lastbus.com.easysobuy.BuildConfig;
import flux.lastbus.com.easysobuy.di.component.AppComponent;
import flux.lastbus.com.easysobuy.di.component.DaggerAppComponent;
import flux.lastbus.com.easysobuy.di.component.UserComponent;
import flux.lastbus.com.easysobuy.di.module.ActionCreatorModule;
import flux.lastbus.com.easysobuy.di.module.AppModule;
import flux.lastbus.com.easysobuy.di.module.DaoManageModule;
import flux.lastbus.com.easysobuy.di.module.DaoModule;
import flux.lastbus.com.easysobuy.di.module.DatabaseModule;
import flux.lastbus.com.easysobuy.di.module.FluxModule;
import flux.lastbus.com.easysobuy.di.module.HttpApiModule;
import flux.lastbus.com.easysobuy.di.module.RetrofitModule;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.http.api.StoreApi;

/**
 * 全局应用
 * 初始化全局对象
 * Created by yuhang on 16-7-27.
 */
public class App extends Application {

    @Inject
    Dispatcher mDispatcher;
    @Inject
    StoreApi mStoreApi;


    static App mApp;

    AppComponent mAppComponent;
//    ActionCreatorComponent mActionCreatorComponent;
    UserComponent mUserComponent;

    /**
     * 获取App对象
     * @return
     */
    public static App getInstance(){
        return mApp;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        initDatabase();
    }

    /**
     * 初始化全局对象
     * Http/Database/ShereProferce/Service
     */
    private void initAppComponent(){
        mAppComponent = DaggerAppComponent.builder()
                .retrofitModule(new RetrofitModule())
                .databaseModule(new DatabaseModule())
                .daoModule(new DaoModule())
                .daoManageModule(new DaoManageModule())
                .appModule(new AppModule(this))
                .fluxModule(new FluxModule())
                .httpApiModule(new HttpApiModule())
                .actionCreatorModule(new ActionCreatorModule())
                .build();

        //注入
        mAppComponent.inject(this);
    }

    /**
     * Debug模式：数据库初始化日志信息
     */
    private void initDatabase(){
        if (BuildConfig.DEBUG) {//Debug版本打印日志
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
    }

    /**
     * 获取AppComponent对象
     * @return
     */
    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    /**
     * 获取当前登陆用户信息
     * @return
     */
    public UserComponent getUserComponent(){
        return mUserComponent;
    }

    /**
     * 创建用户信息注入器
     * @param component
     */
    public void createUserComponent(UserComponent component){
        this.mUserComponent = component;
    }

    /**
     * 释放用户注入器
     */
    public void releseUserComponent(){
        this.mUserComponent = null;
    }

}
