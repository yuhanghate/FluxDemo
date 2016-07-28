package flux.lastbus.com.easysobuy.app;

import android.app.Application;
import android.util.Log;

import java.util.Date;

import javax.inject.Inject;

import de.greenrobot.dao.query.QueryBuilder;
import flux.lastbus.com.easysobuy.BuildConfig;
import flux.lastbus.com.easysobuy.dagger.module.FluxModule;
import flux.lastbus.com.easysobuy.dagger.module.RetrofitModule;
import flux.lastbus.com.easysobuy.dagger.test.ApplicationComponent;
import flux.lastbus.com.easysobuy.dagger.test.DaggerApplicationComponent;
import flux.lastbus.com.easysobuy.dagger.test.Text1Module;
import flux.lastbus.com.easysobuy.dagger.test.Text2Module;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import retrofit2.Retrofit;

/**
 * 全局应用
 * 初始化全局对象
 * Created by yuhang on 16-7-27.
 */
public class App extends Application {
//    AppComponent mAppComponent;

//    ActionCreatorComponent mActionCreatorComponent;

//    @Inject
//    Retrofit mRetrofit;
//    @Inject
//    StoreApi mStoreApi;
//    @Inject
//LoginParams mRetrofit;

    @Inject
    Date date;
    @Inject
    String string;
    @Inject
    Retrofit retrofit;
    @Inject
    Dispatcher dispatcher;



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

        ApplicationComponent component = DaggerApplicationComponent.builder()
                .fluxModule(new FluxModule())
                .retrofitModule(new RetrofitModule())
                .text1Module(new Text1Module())
                .text2Module(new Text2Module())
                .build();
        component.inject(this);

        Date date = component.getDate();
        Log.i("","");
        /*DaggerAppComponent.builder()
                .fluxModule(new FluxModule())
                .build();
        mAppComponent.inject(this);*/
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
   /* public AppComponent getAppComponent(){
        return mAppComponent;
    }

    *//**
     * 获取数据帮助类注入器
     * @return
     *//*
    public ActionCreatorComponent getActionCreatorComponent(){
        if(mActionCreatorComponent == null){
//            DaggerActionCreatorComponent.builder()
//                    .httpApiModule(new HttpApiModule(getAppComponent().getRetrofit()))
//                    .actionCreatorModule(new ActionCreatorModule(mDispatcher))
//                    .build();
        }
        return mActionCreatorComponent;
    }*/

    /**
     * 获取App对象
     * @return
     */
    public static App getInstance(){
        return mApp;
    }


}
