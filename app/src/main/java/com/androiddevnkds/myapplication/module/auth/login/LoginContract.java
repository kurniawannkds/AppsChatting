package com.androiddevnkds.myapplication.module.auth.login;

public interface LoginContract {

    interface loginView{

        void showProgressBar();

        void hideProgressBar();

        void onFailed(int tipe,String message);

        void showMain();
    }

    interface loginPresenter{

        void login(String email, String password);
    }
}
