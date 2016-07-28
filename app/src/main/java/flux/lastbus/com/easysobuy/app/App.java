package flux.lastbus.com.easysobuy.app;

import android.app.Application;

import javax.inject.Inject;

import de.greenrobot.dao.query.QueryBuilder;
import flux.lastbus.com.easysobuy.BuildConfig;
import flux.lastbus.com.easysobuy.dagger.component.AppComponent;
import flux.lastbus.com.easysobuy.dagger.component.DaggerAppComponent;
import flux.lastbus.com.easysobuy.dagger.module.AppModule;
import flux.lastbus.com.easysobuy.dagger.module.DaoManageModule;
import flux.lastbus.com.easysobuy.dagger.module.DaoModule;
import flux.lastbus.com.easysobuy.dagger.module.DatabaseModule;
import flux.lastbus.com.easysobuy.dagger.module.FluxModule;
import flux.lastbus.com.easysobuy.dagger.module.HttpApiModule;
import flux.lastbus.com.easysobuy.dagger.module.RetrofitApiModule;
import flux.lastbus.com.easysobuy.database.dao.DaoSession;

/**
 * 全局应用
 * 初始化全局对象
 * Created by yuhang on 16-7-27.
 */
public class App extends Application {
    @Inject
    DaoSession mDaoSession;


    AppComponent mAppComponent;
    static App mApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initAppComponent();
        initDatabase();
    }

    /**
     * 初始化全局对象
     * Http/Database/ShereProferce/Service
     */
    private void initAppComponent(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .daoManageModule(new DaoManageModule())
                .daoModule(new DaoModule())
                .databaseModule(new DatabaseModule())
                .fluxModule(new FluxModule())
                .httpApiModule(new HttpApiModule())
                .retrofitApiModule(new RetrofitApiModule())
                .build();

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
     * 获取App对象
     * @return
     */
    public static App getInstance(){
        return mApp;
    }

}
