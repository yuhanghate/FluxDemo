package flux.lastbus.com.easysobuy.database.version;

import android.database.sqlite.SQLiteDatabase;

import flux.lastbus.com.easysobuy.app.App;
import flux.lastbus.com.easysobuy.dagger.component.AppComponent;
import flux.lastbus.com.easysobuy.database.core.VersionCoreInterface;
import flux.lastbus.com.easysobuy.database.core.VersionManageCore;
import flux.lastbus.com.easysobuy.database.module.ModuleClassList;

/**
 * 数据库版本操作类
 * Created by yuhang on 16-6-30.
 */
public class VersionDB1 extends VersionCoreInterface {


    public void onUpgrade(SQLiteDatabase db) {
        AppComponent appComponent = App.getInstance().getAppComponent();

        VersionManageCore versionManageCore = appComponent.getVersionManageCore();
        ModuleClassList moduleClassList = appComponent.getModuleClassList();

        versionManageCore.migrate(db, moduleClassList.getList());

    }
}