package com.ben.clover.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ben.clover.R;
import com.ben.clover.model.Friend;
import com.ben.clover.network.RetrofitInstance;
import com.bumptech.glide.Glide;

import java.util.List;

public class StrangerBaseAdapter extends BaseAdapter {

    private Context context;
    private List<Friend> friends;
    private LayoutInflater inflater;

    public StrangerBaseAdapter(Context context, List<Friend> friends) {
        this.context = context;
        this.friends = friends;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_stranger, null);
        TextView tvName = convertView.findViewById(R.id.tvNameStranger);
        ImageView imAvatar = convertView.findViewById(R.id.imgAvatarStranger);
        ImageView imgIsOnline = convertView.findViewById(R.id.imgIsOnline);
        Friend item = friends.get(position);
        tvName.setText(item.getName());
        Glide.with(context).load(RetrofitInstance.IP_STORAGE + item.getAvatar()).into(imAvatar);
        imgIsOnline.setImageResource(R.drawable.ic_online);

        return convertView;
    }
}