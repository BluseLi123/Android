package cn.bluseli.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import cn.bluseli.android.R;
import com.bumptech.glide.Glide;

public class ImageViewActivity extends AppCompatActivity
{

    private ImageView mIv4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_image_view);

        mIv4 = findViewById(R.id.iv_4);
        Glide.with(this).load("https://developer.android.google.cn/studio/images/studio-icon-stable.png").into(mIv4);
    }
}
