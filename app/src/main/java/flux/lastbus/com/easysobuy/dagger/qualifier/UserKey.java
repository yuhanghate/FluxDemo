package flux.lastbus.com.easysobuy.dagger.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 当前登陆用户密码标识
 * Created by yuhang on 16-7-27.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface UserKey {
}
