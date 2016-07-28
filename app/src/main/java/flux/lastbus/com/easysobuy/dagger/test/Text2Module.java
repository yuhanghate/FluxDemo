package flux.lastbus.com.easysobuy.dagger.test;

import java.util.Date;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;

/**
 * Created by yuhang on 16-7-28.
 */
@Module
public class Text2Module {
    @Provides
    @AppScope
    public Date getDate(){
        return new Date();
    }
}
