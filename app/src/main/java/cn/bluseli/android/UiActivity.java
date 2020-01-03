package cn.bluseli.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import cn.bluseli.android.ui.AbsoluteLayoutActivity;
import cn.bluseli.android.ui.ButtonActivity;
import cn.bluseli.android.ui.Calculator;
import cn.bluseli.android.ui.CheckBoxActivity;
import cn.bluseli.android.ui.CustomDialogActivity;
import cn.bluseli.android.ui.DialogActivity;
import cn.bluseli.android.ui.EditTextActivity;
import cn.bluseli.android.ui.ImageViewActivity;
import cn.bluseli.android.ui.LinearlayoutActivity;
import cn.bluseli.android.ui.PopupWindowActivity;
import cn.bluseli.android.ui.ProgressActivity;
import cn.bluseli.android.ui.RatioButtonActivity;
import cn.bluseli.android.ui.RelativelayoutActivity;
import cn.bluseli.android.ui.ScrollViewActivity;
import cn.bluseli.android.ui.TableLayoutActivity;
import cn.bluseli.android.ui.TextViewActivity;
import cn.bluseli.android.ui.ToastActivity;
import cn.bluseli.android.ui.WebViewActivity;
import cn.bluseli.android.ui.gridview.GridViewActivity;
import cn.bluseli.android.ui.listview.ListViewActivity;
import cn.bluseli.android.ui.recyclerview.RecyclerViewActivity;

public class UiActivity extends AppCompatActivity {
    private Button mBtnLinearlayout;
    private Button mBtnRelativelayout;
    private Button mBtnAbsoluteLayout;
    private Button mBtnTextView;
    private Button mBtnButton;
    private Button mBtnEditText;
    private Button mBtnRatioButton;
    private Button mBtnCheckBox;
    private Button mBtnImageView;
    private Button mBtnListView;
    private Button mBtnTableview;
    private Button mBtnGridView;
    private Button mBtnScrollView;
    private Button mBtnRecycleView;
    private Button mBtnWebView;
    private Button mBtnToast;
    private Button mBtnDialog;
    private Button mBtnProgress;
    private Button mBtnCustomDialog;
    private Button mBtnPopupWindow;
    private Button mBtnCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        mBtnLinearlayout = findViewById(R.id.btn_linearlayout);
        mBtnRelativelayout = findViewById(R.id.btn_relativelayout);
        mBtnAbsoluteLayout = findViewById(R.id.btn_absoluteLayout);
        mBtnTextView = findViewById(R.id.btn_textview);
        mBtnButton = findViewById(R.id.btn_button);
        mBtnEditText = findViewById(R.id.btn_edittext);
        mBtnRatioButton = findViewById(R.id.btn_ratiobutton);
        mBtnCheckBox = findViewById(R.id.btn_checkbox);
        mBtnImageView = findViewById(R.id.btn_image_view);
        mBtnListView = findViewById(R.id.btn_list_view);
        mBtnTableview = findViewById(R.id.btn_table_view);
        mBtnGridView = findViewById(R.id.btn_grid_view);
        mBtnScrollView = findViewById(R.id.btn_scroll_view);
        mBtnRecycleView = findViewById(R.id.btn_recycle_view);
        mBtnWebView = findViewById(R.id.btn_web_view);
        mBtnToast = findViewById(R.id.btn_toast);
        mBtnDialog = findViewById(R.id.btn_dialog);
        mBtnProgress = findViewById(R.id.btn_progress);
        mBtnCustomDialog = findViewById(R.id.btn_custom_dialog);
        mBtnPopupWindow = findViewById(R.id.btn_popup_window);
        mBtnCalculator = findViewById(R.id.btn_calculator);
        setListeners();
    }
    // 监听器方法
    private void setListeners()
    {
        OnClick onClick = new OnClick();
        mBtnLinearlayout.setOnClickListener(onClick);
        mBtnRelativelayout.setOnClickListener(onClick);
        mBtnAbsoluteLayout.setOnClickListener(onClick);
        mBtnTextView.setOnClickListener(onClick);
        mBtnButton.setOnClickListener(onClick);
        mBtnEditText.setOnClickListener(onClick);
        mBtnRatioButton.setOnClickListener(onClick);
        mBtnCheckBox.setOnClickListener(onClick);
        mBtnImageView.setOnClickListener(onClick);
        mBtnListView.setOnClickListener(onClick);
        mBtnTableview.setOnClickListener(onClick);
        mBtnGridView.setOnClickListener(onClick);
        mBtnScrollView.setOnClickListener(onClick);
        mBtnRecycleView.setOnClickListener(onClick);
        mBtnWebView.setOnClickListener(onClick);
        mBtnToast.setOnClickListener(onClick);
        mBtnDialog.setOnClickListener(onClick);
        mBtnProgress.setOnClickListener(onClick);
        mBtnCustomDialog.setOnClickListener(onClick);
        mBtnPopupWindow.setOnClickListener(onClick);
        mBtnCalculator.setOnClickListener(onClick);
    }

    // 实现监听器接口
    private class OnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Intent intent = null;
            switch (view.getId())
            {
                // 跳转到LinearLayout演示页面
                case R.id.btn_linearlayout:
                    intent = new Intent(UiActivity.this, LinearlayoutActivity.class);
                    break;
                // 跳转到RelativeLayout演示页面
                case R.id.btn_relativelayout:
                    intent = new Intent(UiActivity.this, RelativelayoutActivity.class);
                    break;
                // 跳转到absoluteLayout演示页面
                case R.id.btn_absoluteLayout:
                    intent = new Intent(UiActivity.this, AbsoluteLayoutActivity.class);
                    break;
                // 跳转到TextView演示页面
                case R.id.btn_textview:
                    intent = new Intent(UiActivity.this, TextViewActivity.class);
                    break;
                // 跳转到Button演示页面
                case R.id.btn_button:
                    intent = new Intent(UiActivity.this, ButtonActivity.class);
                    break;
                // 跳转到EditText演示页面
                case R.id.btn_edittext:
                    intent = new Intent(UiActivity.this, EditTextActivity.class);
                    break;
                // 跳转到RatioButton演示页面
                case R.id.btn_ratiobutton:
                    intent = new Intent(UiActivity.this, RatioButtonActivity.class);
                    break;
                // 跳转到CheckBox演示页面
                case R.id.btn_checkbox:
                    intent = new Intent(UiActivity.this, CheckBoxActivity.class);
                    break;
                // 跳转到ImageView演示页面
                case R.id.btn_image_view:
                    intent = new Intent(UiActivity.this, ImageViewActivity.class);
                    break;
                // 跳转到ListView演示页面
                case R.id.btn_list_view:
                    intent = new Intent(UiActivity.this, ListViewActivity.class);
                    break;
                // 跳转到table_view演示页面
                case R.id.btn_table_view:
                    intent = new Intent(UiActivity.this, TableLayoutActivity.class);
                    break;
                // 跳转到GridView演示页面
                case R.id.btn_grid_view:
                    intent = new Intent(UiActivity.this, GridViewActivity.class);
                    break;
                // 跳转到ScrollView演示页面
                case R.id.btn_scroll_view:
                    intent = new Intent(UiActivity.this, ScrollViewActivity.class);
                    break;
                // 跳转到RecycleView演示页面
                case R.id.btn_recycle_view:
                    intent = new Intent(UiActivity.this, RecyclerViewActivity.class);
                    break;
                // 跳转到WebView演示页面
                case R.id.btn_web_view:
                    intent = new Intent(UiActivity.this, WebViewActivity.class);
                    break;
                // 跳转到Toast页面
                case R.id.btn_toast:
                    intent = new Intent(UiActivity.this, ToastActivity.class);
                    break;
                // 跳转到Dialog页面
                case R.id.btn_dialog:
                    intent = new Intent(UiActivity.this, DialogActivity.class);
                    break;
                // 跳转到Progress页面
                case R.id.btn_progress:
                    intent = new Intent(UiActivity.this, ProgressActivity.class);
                    break;
                // 跳转到CustomDialog页面
                case R.id.btn_custom_dialog:
                    intent = new Intent(UiActivity.this, CustomDialogActivity.class);
                    break;
                // 跳转到PopupWindow页面
                case R.id.btn_popup_window:
                    intent = new Intent(UiActivity.this, PopupWindowActivity.class);
                    break;
                // 跳转到calculator页面
                case R.id.btn_calculator:
                    intent = new Intent(UiActivity.this, Calculator.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}
