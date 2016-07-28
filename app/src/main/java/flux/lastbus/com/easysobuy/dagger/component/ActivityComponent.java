package flux.lastbus.com.easysobuy.dagger.component;

import dagger.Component;
import flux.lastbus.com.easysobuy.dagger.scope.ActivityScope;
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;

/**
 * Activity注入器
 * Created by yuhang on 16-7-27.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(BaseActivity appCompatActivity);

}
