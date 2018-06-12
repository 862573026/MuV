package com.newx.entity.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.newx.entity.local.VideoInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VIDEO_INFO".
*/
public class VideoInfoDao extends AbstractDao<VideoInfo, Long> {

    public static final String TABLENAME = "VIDEO_INFO";

    /**
     * Properties of entity VideoInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Id = new Property(1, int.class, "id", false, "ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Album = new Property(3, String.class, "album", false, "ALBUM");
        public final static Property Artist = new Property(4, String.class, "artist", false, "ARTIST");
        public final static Property DisplayName = new Property(5, String.class, "displayName", false, "DISPLAY_NAME");
        public final static Property MimeType = new Property(6, String.class, "mimeType", false, "MIME_TYPE");
        public final static Property Path = new Property(7, String.class, "path", false, "PATH");
        public final static Property Size = new Property(8, long.class, "size", false, "SIZE");
        public final static Property Duration = new Property(9, long.class, "duration", false, "DURATION");
    }


    public VideoInfoDao(DaoConfig config) {
        super(config);
    }
    
    public VideoInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIDEO_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"ID\" INTEGER NOT NULL ," + // 1: id
                "\"TITLE\" TEXT," + // 2: title
                "\"ALBUM\" TEXT," + // 3: album
                "\"ARTIST\" TEXT," + // 4: artist
                "\"DISPLAY_NAME\" TEXT," + // 5: displayName
                "\"MIME_TYPE\" TEXT," + // 6: mimeType
                "\"PATH\" TEXT," + // 7: path
                "\"SIZE\" INTEGER NOT NULL ," + // 8: size
                "\"DURATION\" INTEGER NOT NULL );"); // 9: duration
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIDEO_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoInfo entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(4, album);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(5, artist);
        }
 
        String displayName = entity.getDisplayName();
        if (displayName != null) {
            stmt.bindString(6, displayName);
        }
 
        String mimeType = entity.getMimeType();
        if (mimeType != null) {
            stmt.bindString(7, mimeType);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(8, path);
        }
        stmt.bindLong(9, entity.getSize());
        stmt.bindLong(10, entity.getDuration());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoInfo entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(4, album);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(5, artist);
        }
 
        String displayName = entity.getDisplayName();
        if (displayName != null) {
            stmt.bindString(6, displayName);
        }
 
        String mimeType = entity.getMimeType();
        if (mimeType != null) {
            stmt.bindString(7, mimeType);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(8, path);
        }
        stmt.bindLong(9, entity.getSize());
        stmt.bindLong(10, entity.getDuration());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VideoInfo readEntity(Cursor cursor, int offset) {
        VideoInfo entity = new VideoInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.getInt(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // album
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // artist
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // displayName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // mimeType
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // path
            cursor.getLong(offset + 8), // size
            cursor.getLong(offset + 9) // duration
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoInfo entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.getInt(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAlbum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setArtist(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDisplayName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMimeType(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPath(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSize(cursor.getLong(offset + 8));
        entity.setDuration(cursor.getLong(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VideoInfo entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VideoInfo entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoInfo entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
