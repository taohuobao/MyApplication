package com.taohuobao.md.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taohuobao.md.myapplication.R;

/**
 * Created by Sky on 2016/11/18.
 */

public class TabContentFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";

    public static TabContentFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);

        TabContentFragment tabContentFragment = new TabContentFragment();
        tabContentFragment.setArguments(arguments);

        return tabContentFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_tab_content, null);
        ((TextView) contentView.findViewById(R.id.tv_content)).setText(getArguments().getString(EXTRA_CONTENT));
        return contentView;
    }


}
