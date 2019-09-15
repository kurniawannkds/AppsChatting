package com.androiddevnkds.myapplication.module.friends;

import com.androiddevnkds.myapplication.model.UserModel;

import java.util.List;

public interface FriendsContract {

    interface friendsView{

        void showProgressBar();

        void hideProgressBar();

        void onFailed(int tipe, String message);

        void showUserList(List<UserModel> userModel);
    }

    interface friendsPresenter{

        void setUserList();
    }
}
