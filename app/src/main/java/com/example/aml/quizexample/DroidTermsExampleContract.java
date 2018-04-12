package com.example.aml.quizexample;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by aml on 11/04/18.
 */

public class DroidTermsExampleContract implements BaseColumns {

    public static final String CONTENT_AUTHORITY = "com.example.udacity.droidtermsexample";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TERMS = "terms" ;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TERMS).build();
    public static final String  CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY
                                               +"/" +PATH_TERMS ;
    public static final int DATBASE_VERSION = 1 ;
    public static final String DATBASE_TABLE = "term_entries";
    public static final String DATABASE_NAME = "terms";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_DEFINITION = "definition";
    public static final String[] COLUMNS = {_ID , COLUMN_WORD , COLUMN_DEFINITION};
    public static final int COLUMN_INDEX_ID = 0 ;
    public static final int COLUMN_INDEX_WORD = 1 ;
    public static final int COLUMN_INDEX_DEFINITION = 2 ;




public  static  Uri buildTermUriWithId (long id) {
    return  ContentUris.withAppendedId(CONTENT_URI , id) ;
}


}
