package com.androiddevnkds.myapplication.module.splashScreen;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenPresenter implements SplashScreenContract.splashScreenPresenter {
    private SplashScreenContract.splashScreenView splashScreenView;

    public SplashScreenPresenter(SplashScreenContract.splashScreenView splashScreenView){
        this.splashScreenView = splashScreenView;
    }
    @Override
    public void checkUserCache() {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null){
            splashScreenView.showLogin();
        }
        else {
            Log.e("USER",currentUser.getEmail());
            splashScreenView.showMainScreen();
        }
    }
}
