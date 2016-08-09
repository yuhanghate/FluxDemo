package flux.lastbus.com.easysobuy.flux.creator.conversion;

import android.os.Bundle;
import android.os.Parcelable;

import flux.lastbus.com.easysobuy.flux.action.UserAction;
import flux.lastbus.com.easysobuy.flux.bean.UserView;
import flux.lastbus.com.easysobuy.http.result.LoginResult;
import flux.lastbus.com.easysobuy.utils.BundleUtil;

/**
 * 用户类型转换
 * Created by yuhang on 16-7-28.
 */
public class UserConversion {
    public static final String TYPE_USERVIEW = UserAction.ACTION_LOGIN_SUCCESSED;

    /**
     * LoginResult -> UserView
     * @param result
     * @return
     */
    public static Bundle getLoginResult2UserView(LoginResult result, String action){
        if(result == null) throw new NullPointerException("LoginResult not is NUll");

        UserView userView = new UserView();
        userView.setName(result.getDatas().getUsername());
        userView.setKey(result.getDatas().getKey());
        userView.setUid(result.getDatas().getUserid());

        return createBundle(userView, action);
    }

    /**
     * Object -> Bundle
     * @param data
     * @param action
     * @param <T>
     * @return
     */
    private static <T extends Parcelable> Bundle createBundle(T data, String action){
        Bundle bundle = BundleUtil.newInstance();
        bundle.putParcelable(action, data);
        return bundle;
    }
}
