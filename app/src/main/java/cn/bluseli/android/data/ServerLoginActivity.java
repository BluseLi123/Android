package cn.bluseli.android.data;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bluseli.android.R;

import java.io.InputStream;
import java.io.OutputStream;

public class ServerLoginActivity extends AppCompatActivity {

    EditText et_username ;
    EditText et_password ;
    TextView tv_forget;
    CheckBox cb_sava;
    CheckBox cb_auto;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_edit_text);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_forget = findViewById(R.id.tv_forget);
        cb_sava = findViewById(R.id.cb_sava);
        cb_auto = findViewById(R.id.cb_auto);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setText("访问服务器，验证登录");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SdcardLoginActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                login(view);
            }
        });

        loadInfo();
    }

    private void loadInfo() {
        Context ctx = ServerLoginActivity.this;

        try {
            InputStream in = ctx.openFileInput("user.data");
            byte[] buffer = new byte[512];
            int len = in.read(buffer);
            String text = new String(buffer, 0, len);
            String[] arr = text.split("#");
            et_username.setText(arr[0]);
            et_password.setText(arr[1]);
            cb_sava.setChecked(true);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(View view) {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(ServerLoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //连接服务器进行登录验证
            if (cb_sava.isChecked()) {
                try {
                    OutputStream out = ServerLoginActivity.this.openFileOutput("user.data",Context.MODE_PRIVATE);
                    String data = username+"#"+password;
                    out.write(data.getBytes());
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
