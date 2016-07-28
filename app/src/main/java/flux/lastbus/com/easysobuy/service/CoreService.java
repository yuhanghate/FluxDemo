package flux.lastbus.com.easysobuy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 处理核心业务Service
 * Created by yuhang on 16-7-27.
 */
public class CoreService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
