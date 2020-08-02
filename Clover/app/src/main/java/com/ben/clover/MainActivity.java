package com.ben.clover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.ben.clover.adapter.PageAdapter;
import com.ben.clover.fragment.ConversationFragment;
import com.ben.clover.fragment.HomeFragment;
import com.ben.clover.fragment.ProfileFragment;
import com.ben.clover.fragment.SearchFragment;
import com.ben.clover.network.DataClient;
import com.ben.clover.network.RetrofitInstance;
import com.ben.clover.response.RegisterResponse;
import com.ben.clover.util.PreferencesUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private BottomNavigationView bottomNavigationView;
    private DataClient dataClient;
    private RegisterResponse loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();
        initViews();
    }

    private void findId() {
        viewPager = findViewById(R.id.viewPagerTop);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void initViews() {
        getLoginData();
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), 4, initFragment());
        viewPager.setAdapter(pagerAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(0);
        dataClient = RetrofitInstance.getInstance().create(DataClient.class);
    }

    private void getLoginData() {
        String loginDataStr = PreferencesUtil.getData(PreferencesUtil.LOGIN_RESPONSE_KEY, this);
        loginData = new Gson().fromJson(loginDataStr, RegisterResponse.class);
    }

    private List<? extends Fragment> initFragment() {
        return Arrays.asList(
                new HomeFragment(),
                new SearchFragment(),
                new ConversationFragment(),
                new ProfileFragment()
        );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemNewFeed:
                viewPager.setCurrentItem(0);
                break;
            case R.id.itemSearch:
                viewPager.setCurrentItem(1);
                break;
            case R.id.itemMessage:
                viewPager.setCurrentItem(2);
                break;
            case R.id.itemProfile:
                viewPager.setCurrentItem(3);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public DataClient getDataClient(){
        return dataClient;
    }

    public RegisterResponse getUserData(){
        return loginData;
    }
}