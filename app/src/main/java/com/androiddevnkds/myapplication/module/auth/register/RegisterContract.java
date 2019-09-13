package com.androiddevnkds.myapplication.module.auth.register;

import com.androiddevnkds.myapplication.model.UserModel;

public interface RegisterContract {

    interface registerView{

        void showProgressBar();

        void hideProgressBar();

        void onFailed(int tipe, String message);

        void showMain();
    }

    interface registerPresenter{

        void register(UserModel userModel,String password, String confirmPass);
    }
}
