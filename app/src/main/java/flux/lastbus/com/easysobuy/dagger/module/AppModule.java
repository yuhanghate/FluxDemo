package flux.lastbus.com.easysobuy.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.app.App;

/**
 * Application应用Module
 * Created by yuhang on 16-7-27.
 */
@Module(includes = FluxModule.class)
public class AppModule {
    App mApplication;

    public AppModule(App application){
        this.mApplication = application;
    }

    /**
     * 提供App
     * @return
     */
    @Singleton
    @Provides
    public App provideApp(){
        return mApplication;
    }

}
