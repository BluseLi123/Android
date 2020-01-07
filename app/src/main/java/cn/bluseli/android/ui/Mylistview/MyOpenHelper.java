package com.itshidu.demo_listview3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class MyOpenHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;

    public MyOpenHelper(Context context) {
        super(context, "person.db", null, VERSION); //创建sqlite数据库
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement,name text,age integer,sex text)");

        System.out.println("onCreate");

        Random r = new Random();
        for(int i=1;i<=20;i++) {
            String name = null;
            String sex = null;
            if(Math.random()<0.5) {
                System.out.print("女：");
                name = RandomName.randomName(true);
                sex = "女";
            }else {
                System.out.print("男：");
                name = RandomName.randomName(false);
                sex = "男";
            }
            System.out.println(name);

            int age = r.nextInt(10)+18;

            db.execSQL("insert into person(name,age,sex) values(?,?,?)",new Object[]{
                    name,age,sex
            });

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        System.out.println("onUpgrade");

    }
}
