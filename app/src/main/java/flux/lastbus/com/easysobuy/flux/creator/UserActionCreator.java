package flux.lastbus.com.easysobuy.flux.creator;

import android.os.Bundle;

import flux.lastbus.com.easysobuy.constant.HttpConstant;
import flux.lastbus.com.easysobuy.flux.action.UserAction;
import flux.lastbus.com.easysobuy.flux.creator.conversion.UserConversion;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.http.api.StoreApi;
import flux.lastbus.com.easysobuy.http.params.LoginParams;
import flux.lastbus.com.easysobuy.http.result.LoginResult;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 用户信息数据管理
 * Created by yuhang on 16-7-28.
 */
public class UserActionCreator extends BaseActionCreator{
    /**
     * 商城API
     */
    StoreApi mStoreApi;

    public UserActionCreator(Dispatcher dispatcher, StoreApi storeApi) {
        super(dispatcher);
        this.mStoreApi = storeApi;
    }

    /**
     * 登陆
     * @param name 用户名
     * @param password 密码
     */
    public void login(String name, String password){
        if(mStoreApi == null) throw new NullPointerException("StoreApi not is Null");

        //加载中
        progressLoadingAction(true);

        //帐号登陆
        mStoreApi.postLoginResult(new LoginParams(name, password).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onLoginAction(result));

    }

    /**
     * 登陆回调通知
     * @param result
     */
    private void onLoginAction(LoginResult result){
        if(result.getCode() == HttpConstant.SUCCESSED){
            //成功回调
            Bundle bundle = UserConversion.getLoginResult2UserView(result, UserAction.ACTION_LOGIN_SUCCESSED);
            dispatch(new UserAction(UserAction.ACTION_LOGIN_SUCCESSED,bundle));
        }else{
            //失败回调
            dispatch(new UserAction(UserAction.ACTION_LOGIN_FAILED));
        }
        //加载完成
        progressLoadingAction(false);
    }

    /**
     * 加载回调
     * @param isLoading true:加载中  false：加载完成
     */
    private void progressLoadingAction(boolean isLoading){
        if(isLoading){
            dispatch(new UserAction(UserAction.ACTION_LOADING));
        }else{
            dispatch(new UserAction(UserAction.ACTION_LOADED));
        }
    }

}
