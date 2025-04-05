package com.malla.testapplist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

public class QueryAllPackagesFragment extends Fragment {

    public QueryAllPackagesFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = getView().findViewById(R.id.text_view);

        try {
            PackageManager pm = requireActivity().getPackageManager();
            List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            StringBuilder sb = new StringBuilder();
            for (ApplicationInfo app : apps) {
                sb.append(app.packageName).append("\n");
            }
            textView.setText(sb.toString());
        } catch (SecurityException e) {
            textView.setText("需要添加QUERY_ALL_PACKAGES权限");
        } catch (Exception e) {
            textView.setText("获取应用列表失败：" + e.getMessage());
        }
    }
}