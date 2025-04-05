package com.malla.testapplist;

import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class LauncherAppsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launcher_apps, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = getView().findViewById(R.id.text_view);

        try {
            LauncherApps launcherApps = requireActivity().getSystemService(LauncherApps.class);
            UserHandle user = android.os.Process.myUserHandle();
            List<LauncherActivityInfo> apps = launcherApps.getActivityList(null, user);
            StringBuilder sb = new StringBuilder();
            for (LauncherActivityInfo app : apps) {
                sb.append(app.getComponentName().getPackageName()).append("\n");
            }
            textView.setText(sb.toString());
        } catch (SecurityException e) {
            textView.setText("需要添加QUERY_ALL_PACKAGES权限");
        } catch (Exception e) {
            textView.setText("获取应用列表失败：" + e.getMessage());
        }
    }
}