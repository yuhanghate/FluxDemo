package flux.lastbus.com.easysobuy.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Fragment单例
 * Created by yuhang on 16-7-27.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {

}
