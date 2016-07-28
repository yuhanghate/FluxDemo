package flux.lastbus.com.easysobuy.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 当前登陆帐号单例
 * Created by yuhang on 16-7-27.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
