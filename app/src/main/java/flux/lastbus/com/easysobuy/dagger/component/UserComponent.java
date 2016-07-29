package flux.lastbus.com.easysobuy.dagger.component;

import dagger.Subcomponent;
import flux.lastbus.com.easysobuy.dagger.module.UserModule;
import flux.lastbus.com.easysobuy.dagger.qualifier.UserID;
import flux.lastbus.com.easysobuy.dagger.qualifier.UserKey;
import flux.lastbus.com.easysobuy.dagger.qualifier.UserName;
import flux.lastbus.com.easysobuy.dagger.scope.UserScope;
import flux.lastbus.com.easysobuy.flux.bean.UserView;

/**
 * 当前登陆用户注入器
 * Created by yuhang on 16-7-27.
 */
@UserScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
    UserView getUserView();

    @UserName
    String getName();

    @UserID
    String getUserid();

    @UserKey
    String getKey();
}
