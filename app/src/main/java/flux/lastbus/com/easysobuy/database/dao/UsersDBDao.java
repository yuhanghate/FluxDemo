package flux.lastbus.com.easysobuy.database.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import flux.lastbus.com.easysobuy.database.module.UserInfoDB;

import flux.lastbus.com.easysobuy.database.module.UsersDB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USERS_DB".
*/
public class UsersDBDao extends AbstractDao<UsersDB, String> {

    public static final String TABLENAME = "USERS_DB";

    /**
     * Properties of entity UsersDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Key = new Property(0, String.class, "key", false, "KEY");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property Uid = new Property(2, String.class, "uid", true, "UID");
        public final static Property LastTime = new Property(3, java.util.Date.class, "lastTime", false, "LAST_TIME");
        public final static Property Member_id = new Property(4, String.class, "member_id", false, "MEMBER_ID");
    };

    private DaoSession daoSession;


    public UsersDBDao(DaoConfig config) {
        super(config);
    }
    
    public UsersDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USERS_DB\" (" + //
                "\"KEY\" TEXT NOT NULL ," + // 0: key
                "\"USER_NAME\" TEXT NOT NULL ," + // 1: userName
                "\"UID\" TEXT PRIMARY KEY NOT NULL ," + // 2: uid
                "\"LAST_TIME\" INTEGER," + // 3: lastTime
                "\"MEMBER_ID\" TEXT);"); // 4: member_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USERS_DB\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, UsersDB entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getKey());
        stmt.bindString(2, entity.getUserName());
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(3, uid);
        }
 
        java.util.Date lastTime = entity.getLastTime();
        if (lastTime != null) {
            stmt.bindLong(4, lastTime.getTime());
        }
 
        String member_id = entity.getMember_id();
        if (member_id != null) {
            stmt.bindString(5, member_id);
        }
    }

    @Override
    protected void attachEntity(UsersDB entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2);
    }    

    /** @inheritdoc */
    @Override
    public UsersDB readEntity(Cursor cursor, int offset) {
        UsersDB entity = new UsersDB( //
            cursor.getString(offset + 0), // key
            cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // uid
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // lastTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // member_id
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, UsersDB entity, int offset) {
        entity.setKey(cursor.getString(offset + 0));
        entity.setUserName(cursor.getString(offset + 1));
        entity.setUid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLastTime(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setMember_id(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(UsersDB entity, long rowId) {
        return entity.getUid();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(UsersDB entity) {
        if(entity != null) {
            return entity.getUid();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserInfoDBDao().getAllColumns());
            builder.append(" FROM USERS_DB T");
            builder.append(" LEFT JOIN USER_INFO_DB T0 ON T.\"MEMBER_ID\"=T0.\"MEMBER_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected UsersDB loadCurrentDeep(Cursor cursor, boolean lock) {
        UsersDB entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        UserInfoDB info = loadCurrentOther(daoSession.getUserInfoDBDao(), cursor, offset);
        entity.setInfo(info);

        return entity;    
    }

    public UsersDB loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<UsersDB> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<UsersDB> list = new ArrayList<UsersDB>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<UsersDB> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<UsersDB> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}