package cn.bluseli.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;

    public SqliteHelper(Context context) {
        super(context, "Sqlite.db", null, VERSION); //创建sqlite数据库
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement,name text,age integer, phone text, addr text)");
        //System.out.println("onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        System.out.println("onUpgrade");
        if (VERSION == 3) {
            db.execSQL("alter table person add addr text");
        }
        if (VERSION == 4) {

        }
    }
}
