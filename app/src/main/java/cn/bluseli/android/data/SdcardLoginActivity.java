package cn.bluseli.android.data;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
import cn.bluseli.android.R;
import cn.bluseli.android.data.util.PermisionUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.Formatter;
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

//public class SdcardLoginActivity extends AppCompatActivity implements View.OnClickListener{
public class SdcardLoginActivity extends AppCompatActivity {

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
        btn_login.setText("保存到SD卡");
        //button 重写onClick()方法
        //btn_login.setOnClickListener(this);
        //button 使用匿名内部类
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                login();
            }
        });

        loadInfo();

    }
    /*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //Toast.makeText(this, "HelloWorld", Toast.LENGTH_SHORT).show();
                login();
                break;
        }
    }
    */
    private void loadInfo() {
        //File file = new File("/data/data/cn.bluseli.android/user.txt");
        Context ctx = SdcardLoginActivity.this;
//        File fileDir = ctx.getFilesDir();
//        File file = new File(fileDir,"user.txt");
//        if(!file.exists())return;

        try {
            InputStream in = ctx.openFileInput("user.data");
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

        Context ctx = SdcardLoginActivity.this;

        if ( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
            Toast.makeText(SdcardLoginActivity.this, "sdcard就绪", Toast.LENGTH_SHORT).show();

            File ext = Environment.getExternalStorageDirectory(); //SD卡目录
            long usableSpace = ext.getUsableSpace();
            long totalSpace = ext.getTotalSpace();
            String use = Formatter.formatFileSize(ctx,usableSpace);
            String total = Formatter.formatFileSize(ctx,totalSpace);
            Toast.makeText(ctx, use+" / "+total, Toast.LENGTH_SHORT).show();

            File f2 = new File(ext, "user.info");
            try {
                PermisionUtils.verifyStoragePermissions(SdcardLoginActivity.this);
                OutputStream out = new FileOutputStream(f2);
                out.write("www.bluseli.cn".getBytes());
                out.close();
                InputStream in = new FileInputStream(f2);
                byte[] buffer = new byte[64];
                int len=in.read(buffer);

                Toast.makeText(ctx, new String(buffer,0,len) , Toast.LENGTH_SHORT).show();

                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(SdcardLoginActivity.this, "sdcard不存在或未挂载", Toast.LENGTH_SHORT).show();
        }


        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(SdcardLoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //连接服务器进行登录验证
            if (cb_sava.isChecked()) {
                //File file = new File("/data/data/cn.bluseli.android/user.txt");
                //File file = new File(MainActivity.this.getFilesDir(),"user.txt");
                try {
                    OutputStream out = SdcardLoginActivity.this.openFileOutput("user.data",Context.MODE_PRIVATE);
                    String data;
                    if(cb_auto.isChecked()) {
                        data = username+"#"+password+"#"+String.valueOf(true);
                    }
                    else {
                        data = username+"#"+password+"#"+String.valueOf(false);
                    }
                    out.write(data.getBytes());
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                File file = new File(SdcardLoginActivity.this.getFilesDir(),"user.data");
                file.delete();

                File ext = Environment.getExternalStorageDirectory(); //SD卡目录
                File file_temp = new File(ext, "user.info");
                if(file_temp.delete()) {
                    Toast.makeText(SdcardLoginActivity.this, "文件已删除", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
