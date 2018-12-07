package com.ralf.module_db.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ralf.module_db.data.entity.SampleDBEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SAMPLE_DBENTITY".
*/
public class SampleDBEntityDao extends AbstractDao<SampleDBEntity, Long> {

    public static final String TABLENAME = "SAMPLE_DBENTITY";

    /**
     * Properties of entity SampleDBEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property EntityId = new Property(1, String.class, "entityId", false, "ENTITY_ID");
        public final static Property EntityJson = new Property(2, String.class, "entityJson", false, "ENTITY_JSON");
    }


    public SampleDBEntityDao(DaoConfig config) {
        super(config);
    }
    
    public SampleDBEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SAMPLE_DBENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"ENTITY_ID\" TEXT UNIQUE ," + // 1: entityId
                "\"ENTITY_JSON\" TEXT);"); // 2: entityJson
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SAMPLE_DBENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SampleDBEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String entityId = entity.getEntityId();
        if (entityId != null) {
            stmt.bindString(2, entityId);
        }
 
        String entityJson = entity.getEntityJson();
        if (entityJson != null) {
            stmt.bindString(3, entityJson);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SampleDBEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String entityId = entity.getEntityId();
        if (entityId != null) {
            stmt.bindString(2, entityId);
        }
 
        String entityJson = entity.getEntityJson();
        if (entityJson != null) {
            stmt.bindString(3, entityJson);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public SampleDBEntity readEntity(Cursor cursor, int offset) {
        SampleDBEntity entity = new SampleDBEntity( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // entityId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // entityJson
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SampleDBEntity entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setEntityId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEntityJson(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SampleDBEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SampleDBEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SampleDBEntity entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
