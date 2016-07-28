package flux.lastbus.com.easysobuy.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Application单例
 * Created by yuhang on 16-7-27.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
