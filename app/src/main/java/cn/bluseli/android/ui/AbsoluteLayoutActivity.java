package cn.bluseli.android.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bluseli.android.R;

public class AbsoluteLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_absolute_layout);

        final Activity activity = this;
        //先取出两个组件
        final EditText mTextPhone = findViewById(R.id.editText_phone);
        Button mBtnCall = findViewById(R.id.button_call);

        mBtnCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /* 动态申请权限 */
                ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.CALL_PHONE
                }, 0x11);

                String phone = mTextPhone.getText().toString();
                if (phone == null | phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请填写手机号", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);

            }
        });
    }
}
