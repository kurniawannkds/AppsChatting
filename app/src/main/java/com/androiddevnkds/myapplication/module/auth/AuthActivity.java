package com.androiddevnkds.myapplication.module.auth;

import android.os.Bundle;
import android.util.Log;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseActivity;
import com.androiddevnkds.myapplication.databinding.ActivityAuthBinding;

import com.androiddevnkds.myapplication.module.auth.login.LoginFragment;
import com.androiddevnkds.myapplication.utils.helper.FragmentHelper;
import com.orhanobut.hawk.Hawk;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public class AuthActivity extends BaseActivity {

    private ActivityAuthBinding mbinding;
    private FragmentManager fm = getSupportFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        initUI();
        initEvent();
    }

    @Override
    public void initUI() {

        FragmentHelper.fragmentInitializer(R.id.fl_fragment_container, fm, new LoginFragment(), null);
    }

    @Override
    public void initEvent() {

    }
}
