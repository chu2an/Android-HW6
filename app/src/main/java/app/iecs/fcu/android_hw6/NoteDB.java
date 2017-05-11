package app.iecs.fcu.android_hw6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.WebView;

import java.util.ArrayList;

/**
 * Created by Chuan.an on 2017/5/12.
 */

public class NoteDB {
    final static String NOTETABLE = "notetable" ;
    final static int NOTEVERSION = 1 ;

    static ArrayList<String> getTitleList(SQLiteDatabase db){
        ArrayList<String> titlelist = new ArrayList<String>();
        Cursor cu = db.rawQuery("select title from " + NOTETABLE , null );
        // 將 Cursor ( cu ) 定址為整個 DataBase
        cu.moveToFirst();
        // cu 復位
        for (int i = 0 ; i < cu.getCount() ; i++ ){
            int titleIndex = cu.getColumnIndex("title");
            // titleIndex ( 標題索引 ) 宣告 等於 使用 cu.getColumnIndex 方法取出的 title 行的索引址
            String title = cu.getString(titleIndex);
            // 將 titleIndex( title行的索引址 ) 中的值使用 cu.getString 方法取出
            titlelist.add(title);
            // 將取出的值加入至 titlelist 中
            cu.moveToNext();
            // cu 移至下一列
        }
        return titlelist;
        // 回傳已經建構好的 titlelist
    }
    static String getBody(SQLiteDatabase db, String title) {
        Cursor cu = db.rawQuery("select * from " + NOTETABLE + " where title='" + title +"';", null);
        // 將 cu 定址為 title 列 ( 尋找名為 title 的那一列 )
        cu.moveToFirst();
        // cu 復位
        return cu.getString(cu.getColumnIndex("body"));
        // 回傳 使用 cu.getString 方法轉換出的 使用 cu.getColumnIndex 方法取得的 body 行內容
    }
    static void addNote(SQLiteDatabase db, String title, String body) {
        ArrayList<String> titlelist = getTitleList(db);
        boolean finded = false;
        for (int i = 0; i < titlelist.size(); i++) {
            if (title.equals(titlelist.get(i))) {
                finded = true;
                break;
            }
        }
        //使用 FindSameTitle 尋找是否為舊的標題 找到為 true

        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("body", body);

        if (finded == false) {
            db.insert(NOTETABLE, null, cv);
        } else {
            db.update(NOTETABLE, cv, "title='" + title + "'", null);
        }
    }
    private boolean FindSameTitle(SQLiteDatabase db, String title){
        ArrayList<String> titlelist = getTitleList(db);
        boolean finded = false;
        for (int i = 0; i < titlelist.size(); i++) {
            if (title.equals(titlelist.get(i))) {
                finded = true;
                break;
            }
        }
        return finded;
    }
    static void delNote(SQLiteDatabase db, String title) {
        db.delete(NOTETABLE, "title=" + "'" +
                title + "'", null);
    }
}
