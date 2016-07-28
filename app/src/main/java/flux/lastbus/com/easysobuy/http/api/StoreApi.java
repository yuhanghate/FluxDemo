package flux.lastbus.com.easysobuy.http.api;


import java.util.Map;

import flux.lastbus.com.easysobuy.http.result.LoginResult;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 商城API
 * Created by yuhang on 16-5-30.
 */
public interface StoreApi {


    /**
     * 帐号登陆
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?act=login")
    Observable<LoginResult> postLoginResult(@FieldMap Map<String, String> map);




}
