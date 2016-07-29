package flux.lastbus.com.easysobuy.dagger.component;

import dagger.Component;
import flux.lastbus.com.easysobuy.dagger.module.ActionCreatorModule;
import flux.lastbus.com.easysobuy.dagger.module.UserModule;
import flux.lastbus.com.easysobuy.dagger.scope.ActionCreatorScope;
import flux.lastbus.com.easysobuy.flux.creator.UserActionCreator;
import flux.lastbus.com.easysobuy.ui.activity.LoginActivity;

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

    UserComponent plus(UserModule module);
}

