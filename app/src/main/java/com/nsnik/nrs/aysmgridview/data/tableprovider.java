package com.nsnik.nrs.aysmgridview.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.nsnik.nrs.aysmgridview.data.tablenames.table1;

/**
 * Created by Nikhil on 05-Mar-17.
 */

public class tableprovider extends ContentProvider{

    private static final int allNames  = 1541;
    private static final int singleName  = 1542;


    static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(tablenames.mAuthority,tablenames.mTableName,allNames);
        sUriMatcher.addURI(tablenames.mAuthority,tablenames.mTableName+"/#",singleName);
    }

    tablehelper helper;

    @Override
    public boolean onCreate() {
        helper = new tablehelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sdb = helper.getReadableDatabase();
        Cursor c = null;
        switch (sUriMatcher.match(uri)){
            case allNames:
                c = sdb.query(tablenames.mTableName,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case singleName:
                selection = table1.mId+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                c = sdb.query(tablenames.mTableName,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Invaild Uri : "+uri);
        }
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (sUriMatcher.match(uri)){
            case allNames:
                return insertVal(uri,values);
            default:
                throw new IllegalArgumentException("Invalid Uri : "+uri);
        }
    }

    private Uri insertVal(Uri u,ContentValues cv){
        SQLiteDatabase sdb = helper.getWritableDatabase();
        long count = sdb.insert(tablenames.mTableName,null,cv);
        if(count == 0){
            return null;
        }else {
            getContext().getContentResolver().notifyChange(u,null);
            return Uri.withAppendedPath(u,String.valueOf(count));
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
