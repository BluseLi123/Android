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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import cn.bluseli.android.R;

public class FileLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_username ;
    private EditText et_password ;
    private TextView tv_forget;
    private CheckBox cb_sava;
    private CheckBox cb_auto;
    private Button btn_login;

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
        btn_login.setOnClickListener(this);
        btn_login.setText("保存到文件");
        loadInfo();

    }

    @Override
    //button 重写onClick()方法
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //Toast.makeText(this, "HelloWorld", Toast.LENGTH_SHORT).show();
                login();
                break;
        }
    }

    private void loadInfo() {
        File file = new File("/data/data/cn.bluseli.android/user.txt");
        //Context ctx = FileLoginActivity.this;
        //File fileDir = ctx.getFilesDir();
        //File file = new File(fileDir,"user.txt");
        if(!file.exists())return;

        try {
            InputStream in = new FileInputStream(file);
            byte[] buffer = new byte[512];
            int len = in.read(buffer);
            String text = new String(buffer, 0, len);
            String[] arr = text.split("#");
            et_username.setText(arr[0]);
            et_password.setText(arr[1]);
            cb_sava.setChecked(true);
            boolean auto=Boolean.valueOf(arr[2]);
            cb_auto.setChecked(auto);

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(FileLoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //连接服务器进行登录验证
            if (cb_sava.isChecked()) {
                File file = new File("/data/data/cn.bluseli.android/user.txt");
                //File file = new File(FileLoginActivity.this.getFilesDir(),"user.txt");
                try {
                    OutputStream out = new FileOutputStream(file);
                    String data;
                    if(cb_auto.isChecked())
                    {
                        data = username+"#"+password+"#"+String.valueOf(true);
                    }
                    else
                    {
                        data = username+"#"+password+"#"+String.valueOf(false);
                    }
                    out.write(data.getBytes());
                    out.close();
                    Toast.makeText(this, "密码保存成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                File file = new File("/data/data/cn.bluseli.android/user.txt");
                file.delete();
                Toast.makeText(this, "密码清除成功", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
