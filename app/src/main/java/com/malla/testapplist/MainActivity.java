package com.malla.testapplist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化ViewPager和TabLayout
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);

        // 设置适配器并绑定Tab标题
        viewPager.setAdapter(new AppListPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
    }

    // 内部适配器类
    private class AppListPagerAdapter extends FragmentPagerAdapter {
        // 修改前只有2个Fragment
        // 修改后包含所有6种获取方式
        private final Fragment[] fragments = {
            new LauncherAppsFragment(),
            new PackageManagerAppsFragment(),
            new IntentFilterFragment(),          // 新增
            new QueryIntentActivitiesFragment(), // 新增
            new UsageStatsFragment(),            // 新增
            new QueryAllPackagesFragment()       // 新增
        };
        private final String[] tabTitles = {     // 新增对应标签
            "LauncherApps方法",
            "PackageManager方法",
            "IntentFilter方法",
            "QueryIntentActivities方法",
            "UsageStats方法",
            "QueryAllPackages方法"
        };

        public AppListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length; // 自动适配数量
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}