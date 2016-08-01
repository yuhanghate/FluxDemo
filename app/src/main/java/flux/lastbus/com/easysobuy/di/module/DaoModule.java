package flux.lastbus.com.easysobuy.di.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.database.dao.DaoSession;
import flux.lastbus.com.easysobuy.database.dao.SearchDBDao;
import flux.lastbus.com.easysobuy.database.dao.SearchNetKeyDBDao;
import flux.lastbus.com.easysobuy.database.dao.UserInfoDBDao;
import flux.lastbus.com.easysobuy.database.dao.UsersDBDao;
import flux.lastbus.com.easysobuy.di.scope.AppScope;

/**
 * 提供所有数据库Dao
 * Created by yuhang on 16-7-27.
 */
@Module(includes = {DaoManageModule.class})
public class DaoModule {

    /**
     * 提供用户信息DAO
     * @param daoSession
     * @return
     */
    @AppScope
    @Provides
    public UsersDBDao provideUsersDBDao(DaoSession daoSession){
        return daoSession.getUsersDBDao();
    }

    /**
     * 提供用户详情DAO
     * @param daoSession
     * @return
     */
    @AppScope
    @Provides
    public UserInfoDBDao provideUserInfoDBDao(DaoSession daoSession){
        return daoSession.getUserInfoDBDao();
    }

    /**
     * 提供网络热门搜索DAO
     * @param daoSession
     * @return
     */
    @AppScope
    @Provides
    public SearchNetKeyDBDao provideSearchNetKeyDBDao(DaoSession daoSession){
        return daoSession.getSearchNetKeyDBDao();
    }

    /**
     * 提供本地搜索记录DAO
     * @param daoSession
     * @return
     */
    @AppScope
    @Provides
    public SearchDBDao provideSearchDBDao(DaoSession daoSession){
        return daoSession.getSearchDBDao();
    }
}
