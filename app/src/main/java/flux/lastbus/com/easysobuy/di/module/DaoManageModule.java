package flux.lastbus.com.easysobuy.di.module;

import dagger.Module;
import dagger.Provides;
import flux.lastbus.com.easysobuy.database.dao.SearchDBDao;
import flux.lastbus.com.easysobuy.database.dao.SearchNetKeyDBDao;
import flux.lastbus.com.easysobuy.database.dao.UserInfoDBDao;
import flux.lastbus.com.easysobuy.database.dao.UsersDBDao;
import flux.lastbus.com.easysobuy.database.manage.SearchManage;
import flux.lastbus.com.easysobuy.database.manage.SearchNetKeyManage;
import flux.lastbus.com.easysobuy.database.manage.UserInfoManage;
import flux.lastbus.com.easysobuy.database.manage.UsersManage;
import flux.lastbus.com.easysobuy.di.scope.AppScope;

/**
 * DAO管理
 * Created by yuhang on 16-7-27.
 */
@Module
public class DaoManageModule {

    @AppScope
    @Provides
    public UsersManage provideUsersManage(UsersDBDao dao){
        return new UsersManage(dao);
    }

    @AppScope
    @Provides
    public UserInfoManage provideUserInfoManage(UserInfoDBDao dao){
        return new UserInfoManage(dao);
    }

    @AppScope
    @Provides
    public SearchNetKeyManage provideSearchNetKeyManage(SearchNetKeyDBDao dao){
        return new SearchNetKeyManage(dao);
    }

    @AppScope
    @Provides
    public SearchManage provideSearchManage(SearchDBDao dao){
        return new SearchManage(dao);
    }
}
