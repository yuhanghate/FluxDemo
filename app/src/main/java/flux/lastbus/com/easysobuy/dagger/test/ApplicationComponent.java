package flux.lastbus.com.easysobuy.dagger.test;

import java.util.Date;

import dagger.Component;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.dagger.module.FluxModule;
import flux.lastbus.com.easysobuy.dagger.module.RetrofitModule;
import flux.lastbus.com.easysobuy.dagger.module.UserModule;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;

/**
 * Created by yuhang on 16-7-28.
 */
@AppScope
@Component(modules = {Text1Module.class, Text2Module.class, FluxModule.class, RetrofitModule.class, UserModule.class})
public interface ApplicationComponent {
    void inject(App app);

    Date getDate();
}
