package flux.lastbus.com.easysobuy.dagger.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;

/**
 * Flux
 * Created by yuhang on 16-7-27.
 */
@Module
public class FluxModule {


    @Provides
    @AppScope
    public Dispatcher provideDispatcher(){
        return new Dispatcher();
    }
}
