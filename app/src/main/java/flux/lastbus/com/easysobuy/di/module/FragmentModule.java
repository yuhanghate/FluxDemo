package flux.lastbus.com.easysobuy.di.module;

import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.di.qualifier.FragmentCompositeSubscription;
import flux.lastbus.com.easysobuy.di.qualifier.FragmentUnbinder;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuhang on 16-8-3.
 */
@Module
public class FragmentModule {
    Fragment mFragment;
    View mView;

    public FragmentModule(Fragment mFragment, View mView) {
        this.mFragment = mFragment;
        this.mView = mView;
    }

    /**
     * 返回View绑定对象
     * @return
     */
    @FragmentUnbinder
    @Provides
    public Unbinder provideUnbinder(){
        return ButterKnife.bind(mFragment, mView);
    }

    @FragmentCompositeSubscription
    @Provides
    public CompositeSubscription provideCompositeSubscription(){
        return new CompositeSubscription();
    }
}
