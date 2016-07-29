package flux.lastbus.com.easysobuy.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.BuildConfig;
import flux.lastbus.com.easysobuy.constant.URLConstant;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;
import flux.lastbus.com.easysobuy.http.converter.GsonConverterFactory;
import flux.lastbus.com.easysobuy.http.interceptor.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 提供api需要的参数对象
 * Created by yuhang on 16-7-27.
 */
@Module(includes = HttpApiModule.class)
public class RetrofitModule {


    /**
     * 提供Gson对象
     * @return
     */
    @AppScope
    @Provides
    public Gson provideGson(){
//        return new Gson();
        //注意这里的Gson的构建方式为GsonBuilder,区别于test1中的Gson gson = new Gson();
        return new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
                //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
                //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
                .create();
    }

    /**
     * 提供Gson解析工厂类
     * @param gson
     * @return
     */
    @AppScope
    @Provides
    public GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    /**
     * 提供API生成类
     * @param converterFactory
     * @param okHttpClient
     * @param rxJavaCallAdapterFactory
     * @return
     */
    @AppScope
    @Provides
    public Retrofit provideRetrofit(GsonConverterFactory converterFactory, OkHttpClient okHttpClient, RxJavaCallAdapterFactory rxJavaCallAdapterFactory){
        return new Retrofit.Builder()
                .baseUrl(URLConstant.URL)
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    /**
     * 提供OKHttpClient
     * @param httpLoggingInterceptor
     * @return
     */
    @AppScope
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor) //Log拦截器
                .build();
    }

    /**
     * 提供日志拦截器
     * @return
     */
    @AppScope
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        return BuildConfig.DEBUG ?
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) :
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
    }

    /**
     * 提供RxJava适配器
     * @return
     */
    @AppScope
    @Provides
    public RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }


}
