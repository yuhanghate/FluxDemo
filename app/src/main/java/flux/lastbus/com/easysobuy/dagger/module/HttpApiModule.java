package flux.lastbus.com.easysobuy.dagger.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;
import flux.lastbus.com.easysobuy.http.api.StoreApi;
import retrofit2.Retrofit;

/**
 * 提供所有网络api接口
 * Created by yuhang on 16-7-27.
 */
@Module
public class HttpApiModule {
    Retrofit mRetrofit;

    public HttpApiModule(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    /**
     * 提供商城API
     * @return
     */
    @AppScope
    @Provides
    public StoreApi provideStoreApi(){
        return mRetrofit.create(StoreApi.class);
    }
}
