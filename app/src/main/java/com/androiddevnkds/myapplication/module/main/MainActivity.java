package com.androiddevnkds.myapplication.module.main;

import android.os.Bundle;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseActivity;
import com.androiddevnkds.myapplication.databinding.ActivityMainBinding;
import com.orhanobut.hawk.Hawk;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mbinding;

    @Override
    public void initUI() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Hawk.init(this).build();
    }
}
