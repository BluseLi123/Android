package cn.bluseli.android.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.bluseli.android.R;

public class SqliteActivity extends AppCompatActivity {

    private Button mBtnToast1;
    private Button mBtnToast2;
    private Button mBtnToast3;
    private Button mBtnToast4;
    private TextView TvRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_toast);

        mBtnToast1=findViewById(R.id.btn_toast1);
        mBtnToast2=findViewById(R.id.btn_toast2);
        mBtnToast3=findViewById(R.id.btn_toast3);
        mBtnToast4=findViewById(R.id.btn_toast4);
        TvRecord=findViewById(R.id.tv_record);

        mBtnToast1.setText("增");
        mBtnToast2.setText("删");
        mBtnToast3.setText("改");
        mBtnToast4.setText("查");

        setListeners();
    }

    // 监听器方法
    private void setListeners()
    {
        SqliteActivity.OnClick onClick = new SqliteActivity.OnClick();
        mBtnToast1.setOnClickListener(onClick);
        mBtnToast2.setOnClickListener(onClick);
        mBtnToast3.setOnClickListener(onClick);
        mBtnToast4.setOnClickListener(onClick);
    }
    // 实现监听器接口
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            SqliteHelper msqliteHelper = new SqliteHelper(SqliteActivity.this);
            SQLiteDatabase database=msqliteHelper.getWritableDatabase(); //无库则创建
            ContentValues values;
            switch (view.getId()) {
                case R.id.btn_toast1://添加
                    //database.execSQL("insert into person(name,age) values(?,?)",new Object[]{ "张三",18 });
                    values = new ContentValues();
                    values.put("name","王五");
                    values.put("age",22);
                    values.put("phone","023-78954132");
                    values.put("addr","重庆市璧山区XXX街道XX号");
                    long newid=database.insert("person",null,values);
                    TvRecord.append("新数据的ID="+newid+"\n");
                    break;
                case R.id.btn_toast3://修改
                    //database.execSQL("update person set age=20 where name=?",new Object[]{"张三"});

                    values = new ContentValues();
                    values.put("age",32);
                    values.put("addr","重庆市潼南区XXX街道XX号");
                    int n=database.update("person",values,"name=?",new String[]{"王五"});
                    TvRecord.append("影响行数："+n+"\n");
                    break;
                case R.id.btn_toast2://删除
                    //database.execSQL("delete from person where name=?",new Object[]{"张三"});
                    int m=database.delete("person","age>?",new String[]{"18"});
                    TvRecord.append("影响行数："+m+"\n");
                    break;
                case R.id.btn_toast4://查询
                    //Cursor cursor=database.rawQuery("select * from person ",new String[]{});
                    Cursor cursor = database.query("person", null, null, null, null, null, null);
                    while (cursor.moveToNext()) { //当游标正常下移（默认BeforeFirst）
                        int id=cursor.getInt(0);
                        String name = cursor.getString(1);
                        int age = cursor.getInt(2);
                        String phone = cursor.getString(3);
                        String addr = cursor.getString(4);

                        TvRecord.append(String.format("[id=%s,name=%s,age=%s,phone=%s,addr=%s]\n",id,name,age,phone,addr));
                    }
            }
            database.close();

        }
    }

    //事务
    public void transaction(View view) {
        SqliteHelper myOpenHelper = new SqliteHelper(this);
        SQLiteDatabase database=myOpenHelper.getWritableDatabase(); //无库则创建
        try{
            database.beginTransaction();
            for (int i = 1; i <= 10; i++) {
                database.execSQL("insert into person(name,age) values(?,?)",new Object[]{ "张三"+i,18+i });
                if (i == 4) {
                    int t = 10/0;
                }
            }
            database.setTransactionSuccessful(); //标记为成功
        }finally {
            database.endTransaction(); //结束事务（提交或回滚）
        }
    }
}
