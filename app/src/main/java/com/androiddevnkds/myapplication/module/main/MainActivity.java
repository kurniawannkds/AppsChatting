package com.androiddevnkds.myapplication.module.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseActivity;

import com.androiddevnkds.myapplication.databinding.ActivityMainBinding;
import com.androiddevnkds.myapplication.module.auth.login.LoginFragment;
import com.androiddevnkds.myapplication.module.friends.FriendsFragment;
import com.androiddevnkds.myapplication.utils.helper.FragmentHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.hawk.Hawk;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mbinding;
    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initUI();
        initEvent();

    }

    @Override
    public void initUI() {

        FragmentHelper.fragmentInitializer(R.id.fl_fragment_container, fm, new FriendsFragment(), null);

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
