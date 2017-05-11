package app.iecs.fcu.android_hw6;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chuan.an on 2017/5/12.
 */

public class DBOpenHelper extends SQLiteOpenHelper{

    public DBOpenHelper(Context context) {
        super( context , "mydb.db" ,  null , NoteDB.NOTEVERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + NoteDB.NOTETABLE + "(title,body);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //none
    }
}
