package com.androiddevnkds.myapplication.module.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseActivity;

import com.androiddevnkds.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.hawk.Hawk;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mbinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initUI();
        initEvent();

    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvent() {

        mbinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });
    }
}
