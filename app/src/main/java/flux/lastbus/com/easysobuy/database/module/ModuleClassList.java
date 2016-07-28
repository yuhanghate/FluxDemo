package flux.lastbus.com.easysobuy.database.module;

import de.greenrobot.dao.AbstractDao;
import flux.lastbus.com.easysobuy.database.dao.SearchDBDao;
import flux.lastbus.com.easysobuy.database.dao.SearchNetKeyDBDao;
import flux.lastbus.com.easysobuy.database.dao.UserInfoDBDao;
import flux.lastbus.com.easysobuy.database.dao.UsersDBDao;

/**
 * 数据库所有需要升级表
 * Created by yuhang on 16-7-27.
 */
public class ModuleClassList {
    Class<? extends AbstractDao<?, ?>>[] array;

    public ModuleClassList() {
        array =
                new Class[]{SearchDBDao.class
                        , SearchNetKeyDBDao.class
                        , UserInfoDBDao.class
                        , UsersDBDao.class};
    }

    /**
     * 获取需要升级的数据库集合
     * @return
     */
    public Class<? extends AbstractDao<?, ?>>[] getList(){
        return array;
    }
}
