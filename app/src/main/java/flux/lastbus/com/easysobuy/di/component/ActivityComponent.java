package flux.lastbus.com.easysobuy.di.component;

import butterknife.Unbinder;
import dagger.Component;
import flux.lastbus.com.easysobuy.di.module.ActivityModule;
import flux.lastbus.com.easysobuy.di.module.UserModule;
import flux.lastbus.com.easysobuy.di.qualifier.ActivityCompositeSubscription;
import flux.lastbus.com.easysobuy.di.qualifier.ActivityUnbinder;
import flux.lastbus.com.easysobuy.di.scope.ActivityScope;
import flux.lastbus.com.easysobuy.flux.creator.TestActionCreator;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;
import flux.lastbus.com.easysobuy.ui.activity.LoginActivity;
import rx.subscriptions.CompositeSubscription;

/**
 * Activity注入器
 * Created by yuhang on 16-7-27.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity appCompatActivity);
    void inject(LoginActivity loginActivity);

    UserComponent newUserComponent(UserModule module);

    @ActivityUnbinder
    Unbinder getUnbinder();
    Dispatcher getDispatcher();
    TestActionCreator getTestActionCreator();
    @ActivityCompositeSubscription
    CompositeSubscription getCompositeSubscription();
}
