package com.androiddevnkds.myapplication.module.friends;

import com.androiddevnkds.myapplication.data.DataManager;
import com.androiddevnkds.myapplication.model.UserModel;
import com.androiddevnkds.myapplication.utils.konstanta.K;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FriendsPresenter implements FriendsContract.friendsPresenter {

    private FriendsContract.friendsView friendsView;

    public FriendsPresenter(FriendsContract.friendsView friendsView){
        this.friendsView = friendsView;
    }

    @Override
    public void setUserList() {

        friendsView.showProgressBar();

        FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = mFireStore.collection(K.PATH_USER_COLLECTION);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<UserModel> userModelList = new ArrayList<>();

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    UserModel userModel = documentSnapshot.toObject(UserModel.class);
                    if(DataManager.can().getUserEmail()!=null){
                        if(!DataManager.can().getUserEmail().equalsIgnoreCase(userModel.getEmail())){
                            userModelList.add(userModel);
                        }
                    }
                }

                if(userModelList.size()>0){
                    friendsView.hideProgressBar();
                    friendsView.showUserList(userModelList);
                }
                else {
                    onFailed(1,"No other user---Invite your friends");
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                onFailed(1,"Error load user---Please try again");
            }
        });
    }

    private void onFailed(int tipe, String message){

        friendsView.hideProgressBar();
        friendsView.onFailed(1,message);
    }
}
