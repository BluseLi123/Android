package cn.bluseli.android.ui.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import cn.bluseli.android.R;

public class MyListAdapter extends BaseAdapter
{

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyListAdapter(Context context)
    {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return 10;
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    static class ViewHolder
    {
        public ImageView imageView;
        public TextView tvName, tvTele, tvAddr;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder = null;
        if (view == null)
        {
            view = mLayoutInflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.iv);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvTele = view.findViewById(R.id.tv_telephone);
            holder.tvAddr = view.findViewById(R.id.tv_addr);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();
        holder.tvName.setText("BluseLi");
        holder.tvTele.setText("023-7894561");
        holder.tvAddr.setText("重庆市万州区XX街道XX号");
        Glide.with(mContext).load("https://pic.taifua.com/me/material-1.png").into(holder.imageView);
        return view;
    }
}
