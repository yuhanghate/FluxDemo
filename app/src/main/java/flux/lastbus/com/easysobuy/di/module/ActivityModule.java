package flux.lastbus.com.easysobuy.di.module;

import android.app.Activity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.di.qualifier.ActivityCompositeSubscription;
import flux.lastbus.com.easysobuy.di.qualifier.ActivityUnbinder;
import rx.subscriptions.CompositeSubscription;

/**
 * Activity相关IOC提供类
 * Created by yuhang on 16-8-3.
 */
@Module
public class ActivityModule {
    Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @ActivityUnbinder
    @Provides
    public Unbinder provideUnbinder(){
        return ButterKnife.bind(mActivity);
    }


    @ActivityCompositeSubscription
    @Provides
    public CompositeSubscription provideCompositeSubscription(){
        return new CompositeSubscription();
    }
}
