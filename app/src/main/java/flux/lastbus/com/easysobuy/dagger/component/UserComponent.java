package flux.lastbus.com.easysobuy.dagger.component;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;
import flux.lastbus.com.easysobuy.dagger.module.UserModule;
import flux.lastbus.com.easysobuy.dagger.scope.UserScope;

/**
 * 当前登陆用户注入器
 * Created by yuhang on 16-7-27.
 */
@UserScope
@Component(dependencies = AppComponent.class, modules = {UserModule.class})
public interface UserComponent {
    void inject(AppCompatActivity appCompatActivity);
}
