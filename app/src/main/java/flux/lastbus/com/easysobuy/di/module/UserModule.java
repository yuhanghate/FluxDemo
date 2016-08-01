package flux.lastbus.com.easysobuy.di.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.di.qualifier.UserID;
import flux.lastbus.com.easysobuy.di.qualifier.UserKey;
import flux.lastbus.com.easysobuy.di.qualifier.UserName;
import flux.lastbus.com.easysobuy.di.scope.AppScope;
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
    @AppScope
    @UserName
    @Provides
    public String provideUserName(){
        if(mUserView == null) return null;
        return mUserView.getName();
    }

    /**
     * 提供用户唯一身份令版Key
     * @return
     */
    @AppScope
    @UserKey
    @Provides
    public String provideKey(){
        if(mUserView == null) return null;
        return mUserView.getKey();
    }

    /**
     * 提供用户唯一ID
     * @return
     */
    @AppScope
    @UserID
    @Provides
    public String provideUserid(){
        if(mUserView == null) return null;
        return mUserView.getUid();
    }

    /**
     * 提供用户信息对象
     * @return
     */
    @AppScope
    @Provides
    public UserView provideUserView(){
        if(mUserView == null) return null;
        return mUserView;
    }

}
