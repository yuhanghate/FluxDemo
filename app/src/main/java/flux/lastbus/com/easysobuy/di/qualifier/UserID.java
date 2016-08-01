package flux.lastbus.com.easysobuy.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 当前登陆用户ID标识
 * Created by yuhang on 16-7-27.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface UserID {
}
