package cn.bluseli.android.ui.listview;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bluseli.android.R;

public class ListViewActivity extends AppCompatActivity
{

    private ListView mLv_contacter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_list_view);

        final Activity activity = this;
        mLv_contacter = findViewById(R.id.lv_contacter);
        mLv_contacter.setAdapter(new MyListAdapter(ListViewActivity.this));

        // 点击事件
        mLv_contacter.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                /* 动态申请权限 */
                ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.CALL_PHONE
                }, 0x11);

                //String phone = mTextPhone.getText().toString();
                String phone =mLv_contacter.getChildAt(i).findViewById(R.id.tv_telephone).toString();
                //String phone =mLv_contacter.getSelectedView().findViewById(R.id.tv_telephone).toString();
                //String phone = view.findViewById(R.id.tv_telephone).toString();
                //System.out.println(phone);
                if (phone == null | phone.isEmpty()) {
                    return;
                }

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);

            }
        });

        // 长按事件
        mLv_contacter.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(ListViewActivity.this, "长按 pos: " + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
