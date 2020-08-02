package com.ben.clover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.ben.clover.adapter.PageAdapter;
import com.ben.clover.fragment.LoginFragment;
import com.ben.clover.fragment.RegisterFragment;
import com.rd.PageIndicatorView;

import java.util.Arrays;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private PageIndicatorView pageIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        findId();
        initViews();
    }

    private void findId() {
        viewPager = findViewById(R.id.viewPager);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);
    }

    private void initViews() {
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), 2, initFragment());
        viewPager.setAdapter(pagerAdapter);

        pageIndicatorView.setCount(2);
        pageIndicatorView.setSelection(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<? extends Fragment> initFragment(){
        return Arrays.asList(
                new LoginFragment(),
                new RegisterFragment()
        );
    }

    private int INDEX_FRAGMENT_LOGIN = 0;
    public void switchFragment(){
        viewPager.setAdapter(pagerAdapter);
    }

    public void switchToRegister(){
        viewPager.setCurrentItem(1);
    }

    public void switchActivity(Class<?> T){
        startActivity(new Intent(this, T));
        finish();
    }
}