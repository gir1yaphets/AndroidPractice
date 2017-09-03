package com.example.a01_viewdraw;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import static com.example.a01_viewdraw.MainFragment.LAYOUT_RES_ID;
import static com.example.a01_viewdraw.MainFragment.VIEW_CLASS_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static final CategoryView[] sCategoryViews = new CategoryView[] {
        new CategoryView("circle", R.layout.fragment_main, "com.example.a01_viewdraw.view.CustomView01"),
        new CategoryView("oval", R.layout.fragment_main, "com.example.a01_viewdraw.view.CustomView02"),
        new CategoryView("heart", R.layout.fragment_main, "com.example.a01_viewdraw.view.CustomView03"),
        new CategoryView("直方图", R.layout.fragment_main, "com.example.a01_viewdraw.view.CustomView04"),
        new CategoryView("图片", R.layout.fragment_main, "com.example.a01_viewdraw.view.CustomView05"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(LAYOUT_RES_ID, sCategoryViews[position].getLayoutResId());
                bundle.putString(VIEW_CLASS_NAME, sCategoryViews[position].getViewClass());
                return MainFragment.newInstance(getApplicationContext(), bundle);
            }

            @Override
            public int getCount() {
                return sCategoryViews.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return sCategoryViews[position].getTitle();
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
