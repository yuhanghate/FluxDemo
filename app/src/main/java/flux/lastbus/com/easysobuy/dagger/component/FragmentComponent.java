package flux.lastbus.com.easysobuy.dagger.component;

import android.support.v4.app.Fragment;

import dagger.Component;
import flux.lastbus.com.easysobuy.dagger.scope.FragmentScope;

/**
 * Fragment 注入器
 * Created by yuhang on 16-7-27.
 */
@FragmentScope
@Component(dependencies = ActivityComponent.class)
public interface FragmentComponent {
    void inject(Fragment fragment);

    void inject(android.app.Fragment fragment);
}
