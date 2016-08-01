package flux.lastbus.com.easysobuy.di.component;

import dagger.Component;
import flux.lastbus.com.easysobuy.di.scope.ActivityScope;
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;
import flux.lastbus.com.easysobuy.ui.activity.LoginActivity;

/**
 * Activity注入器
 * Created by yuhang on 16-7-27.
 */
@ActivityScope
@Component(dependencies = ActionCreatorComponent.class)
public interface ActivityComponent {

    void inject(BaseActivity appCompatActivity);

    void inject(LoginActivity loginActivity);


}
