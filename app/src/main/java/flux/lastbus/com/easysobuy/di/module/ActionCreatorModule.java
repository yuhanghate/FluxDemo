package flux.lastbus.com.easysobuy.di.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.di.qualifier.ActionCreator;
import flux.lastbus.com.easysobuy.di.scope.AppScope;
import flux.lastbus.com.easysobuy.flux.creator.TestActionCreator;
import flux.lastbus.com.easysobuy.flux.creator.UserActionCreator;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.http.api.StoreApi;

/**
 * 提供ActionCreator数据帮助类
 * Created by yuhang on 16-7-28.
 */
@Module
public class ActionCreatorModule {

    public ActionCreatorModule() {
    }

    /**
     * 提供用户帐号相关数据
     * @return
     */
    @AppScope
    @Provides
    public UserActionCreator provideUserActionCreator(Dispatcher mDispatcher, StoreApi mStoreApi){
        return new UserActionCreator(mDispatcher,mStoreApi);
    }

    @AppScope
    @Provides
    public TestActionCreator provideTestActionCreator(Dispatcher mDispatcher){
        return new TestActionCreator(mDispatcher);
    }

    @AppScope
    @ActionCreator("name")
    @Provides
    public String getName(){
        return "name";
    }

    @AppScope
    @ActionCreator("pass")
    @Provides
    public String getPassowrd() {
        return "passwrod";
    }

}
