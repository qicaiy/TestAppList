package com.malla.testapplist;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class IntentFilterFragment extends Fragment {

    public IntentFilterFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = getView().findViewById(R.id.text_view);

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            PackageManager pm = requireActivity().getPackageManager();
            List<ResolveInfo> apps = pm.queryIntentActivities(intent, 0);
            StringBuilder sb = new StringBuilder();
            for (ResolveInfo app : apps) {
                sb.append(app.activityInfo.packageName).append("\n");
            }
            textView.setText(sb.toString());
        } catch (SecurityException e) {
            textView.setText("需要添加QUERY_ALL_PACKAGES权限");
        } catch (Exception e) {
            textView.setText("获取应用列表失败：" + e.getMessage());
        }
    }
}