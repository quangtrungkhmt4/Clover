package com.ben.clover.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.ben.clover.R;
import com.ben.clover.model.Friend;
import com.ben.clover.network.RetrofitInstance;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.SingleItemRowHolder> {

    private List<Friend> itemsList;
    private Context mContext;

    public FriendAdapter(Context context, List<Friend> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        Friend singleItem = itemsList.get(i);
        Glide.with(mContext).load(RetrofitInstance.IP_STORAGE + singleItem.getAvatar()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        private CircleImageView avatar;
        private ImageView imgStatus;

        private SingleItemRowHolder(View view) {
            super(view);

            this.imgStatus = view.findViewById(R.id.imgStatus);
            this.avatar =  view.findViewById(R.id.imgAvatar);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
//
//                }
//            });


        }
    }
}