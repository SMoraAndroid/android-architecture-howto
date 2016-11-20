package com.smora.arch.howto.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.smora.arch.howto.R;

public class BrowseAllFragment extends Fragment {

    public BrowseAllFragment() {
        // Required empty public constructor
    }

    public static BrowseAllFragment newInstance() {
        BrowseAllFragment fragment = new BrowseAllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_browse_all, container, false);

        ((WebView) view.findViewById(R.id.webview)).loadUrl("http://localhost:8080/ws/places");

        return view;
    }

}
