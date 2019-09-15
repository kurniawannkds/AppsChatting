package com.androiddevnkds.myapplication.module.friends;

public class FriendsPresenter implements FriendsContract.friendsPresenter {

    private FriendsContract.friendsView friendsView;

    public FriendsPresenter(FriendsContract.friendsView friendsView){
        this.friendsView = friendsView;
    }
}
