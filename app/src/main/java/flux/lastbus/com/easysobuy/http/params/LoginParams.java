package flux.lastbus.com.easysobuy.http.params;

import com.orhanobut.logger.Logger;

import java.util.Map;

import flux.lastbus.com.easysobuy.utils.Object2Map;

/**
 * 帐户登陆
 * Created by yuhang on 16-6-12.
 */
public class LoginParams {
    public static final String ANDROID = "android";
    private String username;
    private String password;
    private String client;

    public LoginParams(String username, String password) {
        this.username = username;
        this.password = password;
        this.client = ANDROID;
        Logger.i(toString());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "LoginParams{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", client='" + client + '\'' +
                '}';
    }

    public Map<String, String> toMap() {
        return Object2Map.object2Map(this);
    }
}
