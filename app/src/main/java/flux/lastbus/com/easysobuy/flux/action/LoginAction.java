package flux.lastbus.com.easysobuy.flux.action;

import android.os.Bundle;

/**
 * 登陆
 * Created by yuhang on 16-7-28.
 */
public class LoginAction extends BaseAction {
    /**
     * 登陆成功
     */
    public static final String ACTION_LOGIN_SUCCESSED = "action_login_successed";
    /**
     * 登陆失败
     */
    public static final String ACTION_LOGIN_FAILED = "action_login_failed";


    public LoginAction(String type, Bundle data) {
        super(type, data);
    }

    public LoginAction(String type) {
        super(type);
    }
}
