package com.taohuobao.md.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.taohuobao.md.myapplication.R;
import com.taohuobao.md.myapplication.fragment.TabContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sky on 2016/11/17.
 */

public class TabLayoutTopActivity extends BaseActivity {


    private TabLayout mTabl;
    private ViewPager mContentVp;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;

    private ContentPagerAdapter contentPagerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_top);

        mTabl = (TabLayout) findViewById(R.id.tl_tab);
        mContentVp = (ViewPager) findViewById(R.id.vp_content);


        initContent();
        initTab();
    }

    private void initTab() {

        mTabl.setTabMode(TabLayout.MODE_SCROLLABLE);
        /**
         * @param   normalColor,
         * @param   selectedColor
         */
        mTabl.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.white));
        mTabl.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.white));
        //Elevation 高度
        ViewCompat.setElevation(mTabl, 10);
        mTabl.setupWithViewPager(mContentVp);
    }


    private void initContent() {
        tabIndicators = new ArrayList<>();
        for(int i= 0 ; i< 3 ;i++) {
            tabIndicators.add("Tab" + i);
        }
        tabFragments = new ArrayList<>();
        for(String s : tabIndicators){
            tabFragments.add(TabContentFragment.newInstance(s));
        }

        contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tab_add:
                tabIndicators.add("Tab" + tabIndicators.size());
                tabFragments.add(TabContentFragment.newInstance(tabIndicators.get(tabIndicators.size() -1 )));
                contentPagerAdapter.notifyDataSetChanged();
                mTabl.setupWithViewPager(mContentVp);
                return true;
            case R.id.tab_mode_fixed:
                mTabl.setTabMode(TabLayout.MODE_FIXED);
                return true;
            case R.id.tab_mode_scrollable:
                mTabl.setTabMode(TabLayout.MODE_SCROLLABLE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }
}
