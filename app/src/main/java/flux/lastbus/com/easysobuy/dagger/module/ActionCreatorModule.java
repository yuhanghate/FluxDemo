package flux.lastbus.com.easysobuy.dagger.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;
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

    public ActionCreatorModule(Dispatcher mDispatcher) {
        this.mDispatcher = mDispatcher;
    }

    /**
     * 提供用户帐号相关数据
     * @return
     */
    @AppScope
    @Provides
    public UserActionCreator provideUserActionCreator(StoreApi storeApi){
        return new UserActionCreator(mDispatcher,storeApi);
    }
}
