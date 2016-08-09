package flux.lastbus.com.easysobuy.di.component;

import butterknife.Unbinder;
import dagger.Component;
import flux.lastbus.com.easysobuy.di.module.FragmentModule;
import flux.lastbus.com.easysobuy.di.qualifier.FragmentCompositeSubscription;
import flux.lastbus.com.easysobuy.di.qualifier.FragmentUnbinder;
import flux.lastbus.com.easysobuy.di.scope.FragmentScope;
import flux.lastbus.com.easysobuy.flux.creator.TestActionCreator;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.ui.fragment.BaseFragment;
import flux.lastbus.com.easysobuy.ui.fragment.TestFragment;
import rx.subscriptions.CompositeSubscription;

/**
 * Fragment 注入器
 * Created by yuhang on 16-7-27.
 */
@FragmentScope
@Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(BaseFragment baseFragment);

    void inject(TestFragment testFragment);

    @FragmentUnbinder
    Unbinder getUnbinder();
    Dispatcher getDispatcher();
    TestActionCreator getTestActionCreator();
    @FragmentCompositeSubscription
    CompositeSubscription getCompositeSubscription();

}
