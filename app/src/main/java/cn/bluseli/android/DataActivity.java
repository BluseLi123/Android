package cn.bluseli.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.bluseli.android.data.FileLoginActivity;
import cn.bluseli.android.data.SdcardLoginActivity;
import cn.bluseli.android.data.ServerLoginActivity;
import cn.bluseli.android.data.SqliteActivity;
import cn.bluseli.android.data.XmlActivity;

public class DataActivity extends AppCompatActivity {

    private Button mBtnfilelogin;
    private Button mBtnSdlogin;
    private Button mBtnServer;
    private Button mBtnXml;
    private Button mBtnSqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mBtnfilelogin = findViewById(R.id.btn_file);
        mBtnSdlogin   = findViewById(R.id.btn_sdsave);
        mBtnServer    = findViewById(R.id.btn_server);
        mBtnXml       = findViewById(R.id.btn_xml);
        mBtnSqlite       = findViewById(R.id.btn_sqlite);
        setListeners();
    }
    // 监听器方法
    private void setListeners()
    {
        DataActivity.OnClick onClick = new DataActivity.OnClick();
        mBtnfilelogin.setOnClickListener(onClick);
        mBtnSdlogin.setOnClickListener(onClick);
        mBtnServer.setOnClickListener(onClick);
        mBtnXml.setOnClickListener(onClick);
        mBtnSqlite.setOnClickListener(onClick);
    }

    // 实现监听器接口
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                // 跳转到LinearLayout演示页面
                case R.id.btn_file:
                    intent = new Intent(DataActivity.this, FileLoginActivity.class);
                    break;
                case R.id.btn_sdsave:
                    intent = new Intent(DataActivity.this, SdcardLoginActivity.class);
                    break;
                case R.id.btn_server:
                    intent = new Intent(DataActivity.this, ServerLoginActivity.class);
                    break;
                case R.id.btn_xml:
                    intent = new Intent(DataActivity.this, XmlActivity.class);
                    break;
                case R.id.btn_sqlite:
                    intent = new Intent(DataActivity.this, SqliteActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}
