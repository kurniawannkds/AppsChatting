package com.androiddevnkds.myapplication.module.splashScreen;

public interface SplashScreenContract {

    interface splashScreenView{

        void showMainScreen();

        void showLogin();
    }

    interface splashScreenPresenter{

        void checkUserCache();
    }
}
