package com.androiddevnkds.myapplication.base;

import android.content.Context;
import android.os.Bundle;


import com.androidnetworking.AndroidNetworking;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseFragment extends Fragment {
    private static FragmentManager mFragMgr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getActivity().getApplicationContext());

        mFragMgr = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * method used for first UI initialization & manipulation
     */
    public abstract void initUI();

    /**
     * method used for first event initialization & manipulation
     */
    public abstract void initEvent();
}
