package flux.lastbus.com.easysobuy.database.core;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yuhang on 16-6-30.
 */
public abstract class VersionCoreInterface {
    public abstract void onUpgrade(SQLiteDatabase db);
}
