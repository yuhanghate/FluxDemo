package flux.lastbus.com.easysobuy.dagger.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;

/**
 * Application应用Module
 * Created by yuhang on 16-7-27.
 */
@Module
public class AppModule {
    App mApplication;

    public AppModule(App application){
        this.mApplication = application;
    }

    /**
     * 提供App
     * @return
     */
    @AppScope
    @Provides
    public App provideApp(){
        return mApplication;
    }

    /**
     * 提供Application
     * @return
     */
    @AppScope
    @Provides
    public Application provideApplication(){
        return mApplication;
    }
}
