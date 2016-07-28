package flux.lastbus.com.easysobuy.dagger.component;

import android.app.Application;

import dagger.Component;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.dagger.module.AppModule;
import flux.lastbus.com.easysobuy.dagger.module.DatabaseModule;
import flux.lastbus.com.easysobuy.dagger.module.FluxModule;
import flux.lastbus.com.easysobuy.dagger.module.RetrofitApiModule;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;
import flux.lastbus.com.easysobuy.database.core.VersionManageCore;
import flux.lastbus.com.easysobuy.database.dao.DaoSession;
import flux.lastbus.com.easysobuy.database.manage.SearchManage;
import flux.lastbus.com.easysobuy.database.manage.SearchNetKeyManage;
import flux.lastbus.com.easysobuy.database.manage.UserInfoManage;
import flux.lastbus.com.easysobuy.database.manage.UsersManage;
import flux.lastbus.com.easysobuy.database.module.ModuleClassList;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.http.api.StoreApi;

/**
 * Application注入器
 * Created by yuhang on 16-7-27.
 */
@AppScope
@Component(modules = {AppModule.class, RetrofitApiModule.class, DatabaseModule.class, FluxModule.class})
public interface AppComponent {

    void inject(Application application);

    /********************** Application区域开始 ********************************/

    /**
     * 获取Application
     * @return
     */
    Application getApplication();

    /**
     * 获取App
     * @return
     */
    App getApp();

    /*********************** Application区域结束 *******************************/





    /********************** Database区域开始 ********************************/

    /**
     * 数据库版本升级
     * @return
     */
    VersionManageCore getVersionManageCore();

    /**
     * 数据库需要升级的表
     * @return
     */
    ModuleClassList getModuleClassList();

    /**
     * 获取DAO管理对象
     * @return
     */
    DaoSession getDaoSession();

    /**
     * 获取帐号管理
     * @return
     */
    UsersManage getUsersManage();

    /**
     * 用户详细信息管理
     * @return
     */
    UserInfoManage getUserInfoManage();

    /**
     * 网络关键词管理
     * @return
     */
    SearchNetKeyManage getSearchNetKeyManage();

    /**
     * 本地搜索关键词管理
     * @return
     */
    SearchManage getSearchManage();


    /*********************** Database区域结束 *******************************/





    /********************** Http区域开始 ********************************/

    StoreApi getStoreApi();

    /*********************** Http区域结束 *******************************/


    /********************** 当前登陆用户区域开始 ********************************/



    /********************** 当前登陆用户区域开始 ********************************/



    /********************** Flux区域开始 ********************************/

    Dispatcher getDispatcher();

    /********************** Flux区域开始 ********************************/



}
