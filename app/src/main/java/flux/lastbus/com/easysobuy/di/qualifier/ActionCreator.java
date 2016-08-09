package flux.lastbus.com.easysobuy.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by yuhang on 16-8-5.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionCreator {
    String value() default "";
}
