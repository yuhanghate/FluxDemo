package flux.lastbus.com.easysobuy.app;

import android.app.Application;

import javax.inject.Inject;

import de.greenrobot.dao.query.QueryBuilder;
import flux.lastbus.com.easysobuy.BuildConfig;
import flux.lastbus.com.easysobuy.dagger.component.ActionCreatorComponent;
import flux.lastbus.com.easysobuy.dagger.component.AppComponent;
import flux.lastbus.com.easysobuy.dagger.component.DaggerActionCreatorComponent;
import flux.lastbus.com.easysobuy.dagger.component.DaggerAppComponent;
import flux.lastbus.com.easysobuy.dagger.component.UserComponent;
import flux.lastbus.com.easysobuy.dagger.module.ActionCreatorModule;
import flux.lastbus.com.easysobuy.dagger.module.AppModule;
import flux.lastbus.com.easysobuy.dagger.module.DaoManageModule;
import flux.lastbus.com.easysobuy.dagger.module.DaoModule;
import flux.lastbus.com.easysobuy.dagger.module.DatabaseModule;
import flux.lastbus.com.easysobuy.dagger.module.FluxModule;
import flux.lastbus.com.easysobuy.dagger.module.HttpApiModule;
import flux.lastbus.com.easysobuy.dagger.module.RetrofitModule;
import flux.lastbus.com.easysobuy.dagger.module.UserModule;
import flux.lastbus.com.easysobuy.flux.bean.UserView;
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
    ActionCreatorComponent mActionCreatorComponent;
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
     * 获取数据帮助类注入器
     * @return
     */
    public ActionCreatorComponent getActionCreatorComponent(){
        if(mActionCreatorComponent == null){
            mActionCreatorComponent= DaggerActionCreatorComponent.builder()
                    .appComponent(getAppComponent())
                    .actionCreatorModule(new ActionCreatorModule(mDispatcher,mStoreApi))
                    .build();
        }
        return mActionCreatorComponent;
    }

    /**
     * 保存当前登陆用户信息并提供注入器
     * @param userView
     * @return
     */
    public  UserComponent createUserComponent(UserView userView){
        mUserComponent = getActionCreatorComponent().plus(new UserModule(userView));
        return mUserComponent;
    }

    /**
     * 回收当前登陆用户信息
     */
    private void releaseUserComponent(){
        mUserComponent = null;
    }

    /**
     * 获取当前登陆用户注入器
     * @param userView 登陆用户信息
     * @return
     */
    public UserComponent getUserComponent(UserView userView){
        if(mUserComponent == null){
            return createUserComponent(userView);
        }
        return mUserComponent;
    }

}
