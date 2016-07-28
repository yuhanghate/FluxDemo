package flux.lastbus.com.easysobuy.database.manage;

import android.text.TextUtils;

import java.util.Date;

import de.greenrobot.dao.query.Query;
import flux.lastbus.com.easysobuy.database.core.DaoCore;
import flux.lastbus.com.easysobuy.database.dao.UsersDBDao;
import flux.lastbus.com.easysobuy.database.module.UserInfoDB;
import flux.lastbus.com.easysobuy.database.module.UsersDB;

/**
 * 用户信息
 * Created by yuhang on 16-6-27.
 */
public class UsersManage extends DaoCore<UsersDB, Long> {
    private UsersDB lastUser;
    private UsersDBDao mUsersDao;

    public UsersManage(UsersDBDao dao) {
        super(dao);
        mUsersDao = dao;
    }

    public UsersDBDao getDao(){
        return mUsersDao;
    }

    /**
     * 获取当前最后一次登陆用户
     *
     * @return
     */
    public UsersDB getLastUser() {
        if (lastUser != null) return lastUser;

        Query<UsersDB> query = queryBuilder().orderDesc(UsersDBDao.Properties.LastTime).limit(1).build().forCurrentThread();
        return query.unique();

    }

    public UserInfoDB getLastUserInfo(){
        if(getLastUser() == null) return null;

        return getLastUser().getInfo();
    }

    /**
     * 删除当前用户信息及个人信息
     */
    public void deleteLastUser(){
        UserInfoDB lastUserInfo = getLastUserInfo();
//        if(lastUserInfo != null) App.getInstance().getAppComponent().getUserInfoManage().rxDelete(lastUserInfo);

        if(getLastUser() != null) rxDelete(getLastUser());

        lastUser = null;
    }

    /**
     * 最后登陆用户名称
     * @return
     */
    public String getLastUserName(){
        if(getLastUser() == null || TextUtils.isEmpty(getLastUser().getUserName())) return "";

        return getLastUser().getUserName();
    }

    /**
     * 获取个人信息id
     * @return
     */
    public String getLastMemberId(){
        if(getLastUserInfo()==null
                || TextUtils.isEmpty(getLastUser().getInfo().getMember_id())){
            return "";
        }

        return getLastUser().getInfo().getMember_id();
    }

    /**
     * 获取头像
     * @return
     */
    public String getLastMemberAvatar(){
        if(getLastUserInfo() == null
                || TextUtils.isEmpty(getLastUser().getInfo().getMember_avatar())){
            return "";
        }
        return getLastUser().getInfo().getMember_avatar();
    }

    /**
     * 获取令牌
     * @return
     */
    public String getLastKey(){
        if(getLastUser() == null) return "";

        return getLastUser().getKey();
    }


    public void deleteByMemberID(String memberId){
        UsersDB users = queryBuilder().where(UsersDBDao.Properties.Member_id.eq(memberId)).unique();
        delete(users);
    }

    /**
     * 保存用户信息
     *
     * @param users
     */
    public void saveUser(UsersDB users) {
        if (users != null) {
            users.setLastTime(new Date());
            lastUser = users;
            rxSaveOrUpdate(lastUser);
//            rxSave(users);
        }
    }



}
