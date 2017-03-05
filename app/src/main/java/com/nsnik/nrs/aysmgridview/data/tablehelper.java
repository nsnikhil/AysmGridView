package com.nsnik.nrs.aysmgridview.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.nsnik.nrs.aysmgridview.data.tablenames.table1;

/**
 * Created by Nikhil on 05-Mar-17.
 */

public class tablehelper extends SQLiteOpenHelper{

    private static final String mCreateTable = "CREATE TABLE " + tablenames.mTableName + " ("
            +table1.mId + " INTEGER AUTO_INCREMENT PRIMARY KEY, "
            +table1.mName + " TEXT, "
            +table1.mSurname + " TEXT "
            +");";

    private static final String mDropTable = "DROP TABLE IF EXISTS "+ tablenames.mTableName;

    public tablehelper(Context context) {
        super(context, tablenames.mDatabaseName, null, tablenames.mDatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    private void createTable(SQLiteDatabase sdb){
        sdb.execSQL(mCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(mDropTable);
        createTable(db);
    }
}
