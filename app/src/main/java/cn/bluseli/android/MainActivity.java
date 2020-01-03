package cn.bluseli.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnUi;
    private Button mBtmData;
    private Button mBtnDemo;
    private Button mBtnDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnUi = findViewById(R.id.btn_ui_view);
        mBtnDemo=findViewById(R.id.btn_ui_demo);
        mBtmData=findViewById(R.id.btn_datasava);
        mBtnDevice=findViewById(R.id.btn_device);

        setListeners();
    }
    private void setListeners()
    {
        OnClick onClick = new OnClick();
        mBtnUi.setOnClickListener(onClick);
        mBtnDemo.setOnClickListener(onClick);
        mBtmData.setOnClickListener(onClick);
        mBtnDevice.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Intent intent = null;
            switch (view.getId())
            {
                case R.id.btn_ui_view:
                    intent = new Intent(MainActivity.this, UiActivity.class);
                    break;
                case R.id.btn_datasava:
                    intent = new Intent(MainActivity.this, DataActivity.class);
                    break;
                case R.id.btn_ui_demo:
                    intent = new Intent(MainActivity.this, DemoActivity.class);
                    break;
                case R.id.btn_device:
                    intent = new Intent(MainActivity.this, DeviceActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}
