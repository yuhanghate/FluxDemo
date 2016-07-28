package flux.lastbus.com.easysobuy.dagger.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.constant.DatabaseConstant;
import flux.lastbus.com.easysobuy.dagger.scope.AppScope;
import flux.lastbus.com.easysobuy.database.core.VersionCoreInterfaceImpl;
import flux.lastbus.com.easysobuy.database.dao.DaoMaster;
import flux.lastbus.com.easysobuy.database.dao.DaoSession;
import flux.lastbus.com.easysobuy.database.module.ModuleClassList;

/**
 * 提供数据库相关操作类
 * Created by yuhang on 16-7-27.
 */
@Module(includes = {DaoModule.class})
public class DatabaseModule {

    /**
     * 提供版本升级
     * @param app
     * @return
     */
    @AppScope
    @Provides
    public VersionCoreInterfaceImpl provideVersionCoreInterface(App app){
        return new VersionCoreInterfaceImpl(app, DatabaseConstant.DEFAULT_DB_NAME, null);
    }

    /**
     * 提供数据库读写操作
     * @param coreInterface
     * @return
     */
    @AppScope
    @Provides
    public DaoMaster provideDaoMaster(VersionCoreInterfaceImpl coreInterface){
        return new DaoMaster(coreInterface.getWritableDatabase());
    }

    /**
     * 提供数据库DAO管理
     * @param daoMaster
     * @return
     */
    @AppScope
    @Provides
    public DaoSession provideDaoSession(DaoMaster daoMaster){
        return daoMaster.newSession();
    }

    /**
     * 数据库需要升级的表
     * @return
     */
    @AppScope
    @Provides
    public ModuleClassList provideModuleClassList(){
        return new ModuleClassList();
    }
}
