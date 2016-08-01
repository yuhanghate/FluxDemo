package flux.lastbus.com.easysobuy.di.component;

import dagger.Component;
import flux.lastbus.com.easysobuy.di.scope.FragmentScope;

/**
 * Fragment 注入器
 * Created by yuhang on 16-7-27.
 */
@FragmentScope
@Component(dependencies = ActivityComponent.class)
public interface FragmentComponent {

    void inject(android.app.Fragment fragment);
}
