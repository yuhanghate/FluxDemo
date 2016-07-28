package flux.lastbus.com.easysobuy.dagger.test;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;

/**
 * Created by yuhang on 16-7-28.
 */
@Module
public class Text1Module {
    @Provides
    @AppScope
    public String getString(){
        return new String();
    }
}
