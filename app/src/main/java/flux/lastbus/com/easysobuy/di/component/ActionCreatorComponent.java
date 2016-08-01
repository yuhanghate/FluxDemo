package flux.lastbus.com.easysobuy.di.component;

import dagger.Component;
import flux.lastbus.com.easysobuy.database.core.VersionManageCore;
import flux.lastbus.com.easysobuy.database.dao.DaoSession;
import flux.lastbus.com.easysobuy.database.manage.SearchManage;
import flux.lastbus.com.easysobuy.database.manage.SearchNetKeyManage;
import flux.lastbus.com.easysobuy.database.manage.UserInfoManage;
import flux.lastbus.com.easysobuy.database.manage.UsersManage;
import flux.lastbus.com.easysobuy.database.module.ModuleClassList;
import flux.lastbus.com.easysobuy.di.module.ActionCreatorModule;
import flux.lastbus.com.easysobuy.di.module.UserModule;
import flux.lastbus.com.easysobuy.di.scope.ActionCreatorScope;
import flux.lastbus.com.easysobuy.flux.creator.UserActionCreator;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.ui.activity.LoginActivity;
import retrofit2.Retrofit;

/**
 * 数据提供帮助类注入器
 * Created by yuhang on 16-7-28.
 */
@ActionCreatorScope
@Component(dependencies = AppComponent.class, modules = ActionCreatorModule.class)
public interface ActionCreatorComponent {
//    void inject(App app);

    void inject(LoginActivity loginAction);


/**
     * 获取帐号信息相关数据
     * @return
     */

    UserActionCreator getUserActionCreator();

    Dispatcher getDispatcher();
    Retrofit getRetrofit();
    VersionManageCore getVersionManageCore();
    ModuleClassList getModuleClassList();
    DaoSession getDaoSession();
    UsersManage getUsersManage();
    UserInfoManage getUserInfoManage();
    SearchNetKeyManage getSearchNetKeyManage();
    SearchManage getSearchManage();
}

