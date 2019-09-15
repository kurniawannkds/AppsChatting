package com.androiddevnkds.myapplication.module.auth.login;

import android.content.Context;

public interface LoginContract {

    interface loginView{

        void showProgressBar();

        void hideProgressBar();

        void onFailed(int tipe,String message);

        void showMain();

        void onFailedResetPass(int tipe,String message);

        void onSuccessResetPassword(String message);
    }

    interface loginPresenter{

        void login(String email, String password);

        void forgotPassword(String email);

        void loginByGoogle(Context context);
    }
}
