package flux.lastbus.com.easysobuy.di.component;

import dagger.Component;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.database.core.VersionManageCore;
import flux.lastbus.com.easysobuy.database.dao.DaoSession;
import flux.lastbus.com.easysobuy.database.manage.SearchManage;
import flux.lastbus.com.easysobuy.database.manage.SearchNetKeyManage;
import flux.lastbus.com.easysobuy.database.manage.UserInfoManage;
import flux.lastbus.com.easysobuy.database.manage.UsersManage;
import flux.lastbus.com.easysobuy.database.module.ModuleClassList;
import flux.lastbus.com.easysobuy.di.module.AppModule;
import flux.lastbus.com.easysobuy.di.module.DatabaseModule;
import flux.lastbus.com.easysobuy.di.module.FluxModule;
import flux.lastbus.com.easysobuy.di.module.RetrofitModule;
import flux.lastbus.com.easysobuy.di.qualifier.ActionCreator;
import flux.lastbus.com.easysobuy.di.scope.AppScope;
import flux.lastbus.com.easysobuy.flux.creator.TestActionCreator;
import flux.lastbus.com.easysobuy.flux.creator.UserActionCreator;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import retrofit2.Retrofit;

/**
 * Application注入器
 * Created by yuhang on 16-7-27.
 */
@AppScope
@Component(modules = {FluxModule.class,
        RetrofitModule.class,
        DatabaseModule.class,
        AppModule.class})
public interface AppComponent {

    void inject(App application);



    /********************** Database区域开始 ********************************/

    /**
     * 数据库版本升级
     *
     * @return
     */
    VersionManageCore getVersionManageCore();

    /**
     * 数据库需要升级的表
     *
     * @return
     */
    ModuleClassList getModuleClassList();

    /**
     * 获取DAO管理对象
     *
     * @return
     */
    DaoSession getDaoSession();

    /**
     * 获取帐号管理
     *
     * @return
     */
    UsersManage getUsersManage();

    /**
     * 用户详细信息管理
     *
     * @return
     */
    UserInfoManage getUserInfoManage();

    /**
     * 网络关键词管理
     *
     * @return
     */
    SearchNetKeyManage getSearchNetKeyManage();

    /**
     * 本地搜索关键词管理
     *
     * @return
     */
    SearchManage getSearchManage();


    /*********************** Database区域结束 *******************************/


    /**********************
     * Http区域开始
     ********************************/

    /**
     * Http API接口
     * @return
     */
    Retrofit getRetrofit();

    /*********************** Http区域结束 *******************************/

    /**********************
     * Flux区域开始
     ********************************/

    /**
     * Store事件分发器
     * @return
     */
    Dispatcher getDispatcher();

    /**********************
     * Flux区域开始
     ********************************/

    /**
     * 用户信息数据
     * @return
     */
    UserActionCreator getUserActionCreator();



    /**
     * 获取Test Action
     * @return
     */
    TestActionCreator getTestActionCreator();

    @ActionCreator("name")
    String getName();

    @ActionCreator("pass")
    String getPass();

}
