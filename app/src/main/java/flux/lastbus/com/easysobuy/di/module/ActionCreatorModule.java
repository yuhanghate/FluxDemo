package flux.lastbus.com.easysobuy.di.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.di.scope.ActionCreatorScope;
import flux.lastbus.com.easysobuy.flux.creator.UserActionCreator;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.http.api.StoreApi;

/**
 * 提供ActionCreator数据帮助类
 * Created by yuhang on 16-7-28.
 */
@Module
public class ActionCreatorModule {
    Dispatcher mDispatcher;
    StoreApi mStoreApi;

    public ActionCreatorModule(Dispatcher mDispatcher, StoreApi mStoreApi) {
        this.mDispatcher = mDispatcher;
        this.mStoreApi = mStoreApi;
    }

    /**
     * 提供用户帐号相关数据
     * @return
     */
    @ActionCreatorScope
    @Provides
    public UserActionCreator provideUserActionCreator(){
        return new UserActionCreator(mDispatcher,mStoreApi);
    }
}
