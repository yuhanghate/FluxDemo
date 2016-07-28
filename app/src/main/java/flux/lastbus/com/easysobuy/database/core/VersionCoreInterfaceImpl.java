package flux.lastbus.com.easysobuy.database.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import flux.lastbus.com.easysobuy.database.dao.DaoMaster;


/**
 * 数据库版本升级
 * Created by yuhang on 16-6-30.
 */
public  class VersionCoreInterfaceImpl extends DaoMaster.OpenHelper {

    public static final String TAG = VersionCoreInterfaceImpl.class.getSimpleName();
    private  String VERSION = "flux.lastbus.com.easysobuy.database.version.VersionDB";//升级数据库操作类

    public VersionCoreInterfaceImpl(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 版本升级
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            try {
                VersionCoreInterface migratorHelper = (VersionCoreInterface) Class.forName(VERSION+ i).newInstance();

                if (migratorHelper != null) {


                    migratorHelper.onUpgrade(db);
                }

            } catch (ClassNotFoundException | ClassCastException | IllegalAccessException | InstantiationException e) {

                Log.e(TAG, "Could not migrate from schema from schema: " + i + " to " + i++);
                break;
            }


        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        for (int i = newVersion; i < oldVersion; i--) {
            try {
                VersionCoreInterface migratorHelper = (VersionCoreInterface) Class.forName(VERSION+ i).newInstance();

                if (migratorHelper != null) {


                    migratorHelper.onUpgrade(db);
                }

            } catch (ClassNotFoundException | ClassCastException | IllegalAccessException | InstantiationException e) {

                Log.e(TAG, "Could not migrate from schema from schema: " + i + " to " + i++);
                break;
            }


        }
    }
}
