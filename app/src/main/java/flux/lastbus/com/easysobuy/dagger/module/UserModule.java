package flux.lastbus.com.easysobuy.dagger.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.dagger.qualifier.UserID;
import flux.lastbus.com.easysobuy.dagger.qualifier.UserKey;
import flux.lastbus.com.easysobuy.dagger.qualifier.UserName;
import flux.lastbus.com.easysobuy.dagger.scope.UserScope;
import flux.lastbus.com.easysobuy.flux.bean.UserView;

/**
 * 提供当前登陆用户信息
 * Created by yuhang on 16-7-27.
 */
@Module
public class UserModule {
    private UserView mUserView;
    public UserModule(UserView userView){
        this.mUserView = userView;
    }

    /**
     * 提供用户名
     * @return
     */
    @UserScope
    @UserName
    @Provides
    public String provideUserName(){
        return mUserView.getName();
    }

    /**
     * 提供用户唯一身份令版Key
     * @return
     */
    @UserScope
    @UserKey
    @Provides
    public String provideKey(){
        return mUserView.getKey();
    }

    /**
     * 提供用户唯一ID
     * @return
     */
    @UserScope
    @UserID
    @Provides
    public String provideUserid(){
        return mUserView.getUid();
    }

    /**
     * 提供用户信息对象
     * @return
     */
    @UserScope
    @Provides
    public UserView provideUserView(){
        return mUserView;
    }

}
