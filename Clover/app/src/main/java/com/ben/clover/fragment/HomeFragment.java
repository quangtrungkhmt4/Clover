package com.ben.clover.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ben.clover.MainActivity;
import com.ben.clover.R;
import com.ben.clover.adapter.FriendAdapter;
import com.ben.clover.adapter.StrangerAdapter;
import com.ben.clover.adapter.StrangerBaseAdapter;
import com.ben.clover.constant.ResponseCode;
import com.ben.clover.model.Friend;
import com.ben.clover.request.SearchFriendRequest;
import com.ben.clover.request.SearchStrangerRequest;
import com.ben.clover.response.ResponseEntity;
import com.ben.clover.response.SearchFriendResponse;
import com.ben.clover.response.SearchStrangerResponse;
import com.ben.clover.response.SuccessResponse;
import com.ben.clover.util.DialogUtil;
import com.ben.clover.util.LoadMore;
import com.ben.clover.view.GridViewNotScroll;
import com.ben.clover.view.InteractiveScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerFriend, recyclerStranger;
    private FriendAdapter friendAdapter;
    private List<Friend> friends, strangers;
    private MainActivity mainActivity;
    private StrangerAdapter strangerAdapter;
    private int allStranger = 0;
    private int currentPage = 0;
    private GridViewNotScroll gvStranger;
    private StrangerBaseAdapter strangerBaseAdapter;
    private InteractiveScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findId(view);
        initViews();
        return view;
    }

    private void findId(View view) {
        recyclerFriend = view.findViewById(R.id.recyclerFriends);
//        recyclerStranger = view.findViewById(R.id.recyclerStranger);
        gvStranger = view.findViewById(R.id.gvStranger);
        scrollView = view.findViewById(R.id.scrollView);
    }

    private void initViews() {
        mainActivity = (MainActivity) getActivity();
        friends = new ArrayList<>();
        friendAdapter = new FriendAdapter(getContext(), friends);
        recyclerFriend.setHasFixedSize(true);
        recyclerFriend.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerFriend.setAdapter(friendAdapter);
        loadListFriends();


        strangers = new ArrayList<>();
        strangerBaseAdapter = new StrangerBaseAdapter(getContext(), strangers);
        gvStranger.setAdapter(strangerBaseAdapter);

        addStranger(0);

        scrollView.setOnBottomReachedListener(new InteractiveScrollView.OnBottomReachedListener() {
            @Override
            public void onBottomReached() {
                Toast.makeText(mainActivity, "bottom", Toast.LENGTH_SHORT).show();
            }
        });

//        initStranger();
//
//        //Set Load more event
//        strangerAdapter.setLoadMore(new LoadMore() {
//            @Override
//            public void onLoadMore() {
//                if (strangers.size() <= allStranger) // Change max size
//                {
//                    strangers.add(null);
//                    strangerAdapter.notifyItemInserted(strangers.size() - 1);
//
//                    currentPage++;
//                    addStranger(currentPage);
//                } else {
//                    Toast.makeText(mainActivity, "Load data completed !", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void loadListFriends() {
        SearchFriendRequest request = SearchFriendRequest.builder()
                .token(mainActivity.getUserData().getToken())
                .skip(0)
                .take(10).build();
        Call<JsonObject> call = mainActivity.getDataClient().searchFriend(request);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Type type = new TypeToken<ResponseEntity<SearchFriendResponse>>() {
                }.getType();
                ResponseEntity<SearchFriendResponse> entity = new Gson().fromJson(response.body().toString(), type);
                if (entity.getCode() != 0) {
                    DialogUtil.initDialogNoti(getContext(), ResponseCode.getMessage(getContext(), entity.getCode()));
                    return;
                }
                friends.addAll(entity.getData().getFriends());
                friendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                DialogUtil.initDialogNoti(getContext(), t.getMessage());
            }
        });
    }

    private void initStranger() {
        strangers = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerStranger.setLayoutManager(manager);
        strangerAdapter = new StrangerAdapter(recyclerStranger, mainActivity, strangers);
        recyclerStranger.setAdapter(strangerAdapter);
        addStranger(0);
    }

//    private void refeshStrangers() {
//        strangers.addAll(addStranger());
//        strangerAdapter.notifyDataSetChanged();
//    }

    private void addStranger(int page) {
        SearchStrangerRequest request = SearchStrangerRequest.builder()
                .token(mainActivity.getUserData().getToken())
                .page(page)
                .take(20).build();
        Call<JsonObject> call = mainActivity.getDataClient().searchStrangers(request);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Type type = new TypeToken<ResponseEntity<SearchStrangerResponse>>() {
                }.getType();
                ResponseEntity<SearchStrangerResponse> entity = new Gson().fromJson(response.body().toString(), type);
                if (entity.getCode() != 0) {
                    DialogUtil.initDialogNoti(getContext(), ResponseCode.getMessage(getContext(), entity.getCode()));
                    return;
                }

//                if (!strangers.isEmpty()){
//                    strangers.remove(strangers.size() - 1);
//                    strangerAdapter.notifyItemRemoved(strangers.size());
//                }

                strangers.addAll(entity.getData().getStrangers());
                allStranger = entity.getData().getTotal();
                strangerBaseAdapter.notifyDataSetChanged();
//                strangerAdapter.setLoaded();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                DialogUtil.initDialogNoti(getContext(), t.getMessage());
            }
        });
    }
}