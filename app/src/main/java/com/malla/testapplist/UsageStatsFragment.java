package com.malla.testapplist;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class UsageStatsFragment extends Fragment {

    public UsageStatsFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = getView().findViewById(R.id.text_view);

        try {
            UsageStatsManager usageStatsManager = (UsageStatsManager) requireActivity().getSystemService(Context.USAGE_STATS_SERVICE);
            long endTime = System.currentTimeMillis();
            long startTime = endTime - 1000 * 60 * 60 * 24; // Last 24 hours
            List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);
            StringBuilder sb = new StringBuilder();
            for (UsageStats stat : stats) {
                sb.append(stat.getPackageName()).append("\n");
            }
            textView.setText(sb.toString());
        } catch (SecurityException e) {
            textView.setText("需要开启使用统计权限");
        } catch (Exception e) {
            textView.setText("获取应用列表失败：" + e.getMessage());
        }
    }
}