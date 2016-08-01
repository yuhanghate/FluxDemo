package flux.lastbus.com.easysobuy.flux.action;

import android.os.Bundle;
import android.os.Parcel;

/**
 * 登陆
 * Created by yuhang on 16-7-28.
 */
public class UserAction extends BaseAction {
    /**
     * 登陆成功
     */
    public static final String ACTION_LOGIN_SUCCESSED = "action_login_successed";
    /**
     * 登陆失败
     */
    public static final String ACTION_LOGIN_FAILED = "action_login_failed";

    /**
     * 加载中
     */
    public static final String ACTION_LOADING = "action_Login_loading";

    /**
     * 加载完成
     */
    public static final String ACTION_LOADED = "action_Login_loaded";


    public UserAction(String type, Bundle data) {
        super(type, data);
    }

    public UserAction(String type) {
        super(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public UserAction(Parcel in) {
        super(in);
    }

    public static final Creator<UserAction> CREATOR = new Creator<UserAction>() {
        @Override
        public UserAction createFromParcel(Parcel source) {
            return new UserAction(source);
        }

        @Override
        public UserAction[] newArray(int size) {
            return new UserAction[size];
        }
    };
}
