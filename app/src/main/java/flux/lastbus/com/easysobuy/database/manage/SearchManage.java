package flux.lastbus.com.easysobuy.database.manage;

import flux.lastbus.com.easysobuy.database.core.DaoCore;
import flux.lastbus.com.easysobuy.database.dao.SearchDBDao;
import flux.lastbus.com.easysobuy.database.module.SearchDB;

/**
 * 搜索记录关键字
 * Created by yuhang on 16-6-27.
 */
public class SearchManage extends DaoCore<SearchDB, Long> {
    SearchDBDao mSearchDao;
    public SearchManage(SearchDBDao dao) {
        super(dao);
        mSearchDao = dao;
    }

    public SearchDBDao getDao() {
        return mSearchDao;
    }
}
