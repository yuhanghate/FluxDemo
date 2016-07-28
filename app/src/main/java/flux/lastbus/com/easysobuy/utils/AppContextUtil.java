package flux.lastbus.com.easysobuy.utils;

import android.content.Context;

import flux.lastbus.com.easysobuy.app.App;


/**
 * @author lsxiao
 * @date 2015-11-08 23:28
 */
public class AppContextUtil {

    public static Context instance() {
        return App.getInstance();
    }
}
