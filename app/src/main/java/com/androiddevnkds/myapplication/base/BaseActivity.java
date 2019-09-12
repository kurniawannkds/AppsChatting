package com.androiddevnkds.myapplication.base;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public abstract class BaseActivity extends AppCompatActivity {

    private static FragmentManager mFragMgr;

    /**
     * method used for first UI initialization & manipulation
     */
    public abstract void initUI();

    /**
     * method used for first event initialization & manipulation
     */
    public abstract void initEvent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragMgr = getSupportFragmentManager();
    }

}