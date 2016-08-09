package flux.lastbus.com.easysobuy.di.component;

import dagger.Subcomponent;
import flux.lastbus.com.easysobuy.di.module.UserModule;
import flux.lastbus.com.easysobuy.di.qualifier.UserID;
import flux.lastbus.com.easysobuy.di.qualifier.UserKey;
import flux.lastbus.com.easysobuy.di.qualifier.UserName;
import flux.lastbus.com.easysobuy.di.scope.UserScope;
import flux.lastbus.com.easysobuy.flux.bean.UserView;

/**
 * 当前登陆用户注入器
 * Created by yuhang on 16-7-27.
 */
@UserScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
//    void inject(LoginActivity loginActivity);
    UserView getUserView();

    @UserName
    String getName();

    @UserID
    String getUserid();

    @UserKey
    String getKey();
}
