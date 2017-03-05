package com.nsnik.nrs.aysmgridview.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Nikhil on 05-Mar-17.
 */

public class tablenames {

    public static final String mDatabaseName = "test";
    public static final String mTableName = "testable";
    public static final int mDatabaseVersion = 1;

    public static final String mScheme = "content://";
    public static final String mAuthority = "com.nsnik.nrs.aysmgridview";

    public static final Uri mBaseUri = Uri.parse(mScheme+mAuthority);
    public static final Uri mContentUri = Uri.withAppendedPath(mBaseUri,mTableName);

    public class table1 implements BaseColumns{
        public static final String mId = BaseColumns._ID;
        public static final String mName = "name";
        public static final String mSurname = "surname";
    }
}
