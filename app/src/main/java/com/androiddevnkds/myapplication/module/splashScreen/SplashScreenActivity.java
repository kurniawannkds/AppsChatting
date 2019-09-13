package com.androiddevnkds.myapplication.module.splashScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseActivity;
import com.androiddevnkds.myapplication.databinding.ActivitySplashBinding;
import com.androiddevnkds.myapplication.module.auth.AuthActivity;
import com.androiddevnkds.myapplication.module.main.MainActivity;
import com.orhanobut.hawk.Hawk;

public class SplashScreenActivity extends BaseActivity implements SplashScreenContract.splashScreenView {

    private SplashScreenPresenter splashScreenPresenter;
    private ActivitySplashBinding mbinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
        splashScreenPresenter = new SplashScreenPresenter(this);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Log.e("TES","TES");
                splashScreenPresenter.checkUserCache();

            }
        },2000);

        initUI();
        initEvent();
    }

    @Override
    public void initUI() {


    }

    @Override
    public void initEvent() {

    }

    @Override
    public void showMainScreen() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLogin() {

        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
