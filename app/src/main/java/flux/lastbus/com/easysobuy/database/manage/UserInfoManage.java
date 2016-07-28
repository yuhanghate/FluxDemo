package flux.lastbus.com.easysobuy.database.manage;


import flux.lastbus.com.easysobuy.database.core.DaoCore;
import flux.lastbus.com.easysobuy.database.dao.UserInfoDBDao;
import flux.lastbus.com.easysobuy.database.module.UserInfoDB;

/**
 * IM 用户信息列表
 * Created by yuhang on 16-6-27.
 */
public class UserInfoManage extends DaoCore<UserInfoDB, Long> {
    UserInfoDBDao mUserInfoDao;
    public UserInfoManage(UserInfoDBDao dao) {
        super(dao);
        mUserInfoDao = dao;
    }



    public void deleteByMemberId(String memberId){

        UserInfoDB info = queryBuilder().where(UserInfoDBDao.Properties.Member_id.eq(memberId)).build().forCurrentThread().unique();
        if(info != null){
            delete(info);
        }
    }
}
