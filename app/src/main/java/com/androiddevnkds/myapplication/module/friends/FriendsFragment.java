package com.androiddevnkds.myapplication.module.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.adapter.UsersAdapter;
import com.androiddevnkds.myapplication.base.BaseFragment;
import com.androiddevnkds.myapplication.databinding.FragmentFriendsBinding;
import com.androiddevnkds.myapplication.databinding.FragmentRegisterBinding;
import com.androiddevnkds.myapplication.model.UserModel;
import com.androiddevnkds.myapplication.module.auth.register.RegisterContract;
import com.androiddevnkds.myapplication.module.auth.register.RegisterPresenter;
import com.androiddevnkds.myapplication.module.main.MainActivity;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FriendsFragment extends BaseFragment implements FriendsContract.friendsView {

    private Context mContext;
    private FragmentFriendsBinding mBinding;
    private FriendsPresenter friendsPresenter;
    private UsersAdapter usersAdapter;

    public FriendsFragment() {
        // Required empty public constructor
        setArguments(new Bundle());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (getArguments() != null) {

        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends, container, false);
        friendsPresenter = new FriendsPresenter(this);
        initUI();
        initEvent();

        return mBinding.getRoot();
    }

    @Override
    public void initUI() {

        friendsPresenter.setUserList();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void showProgressBar() {

        mBinding.viewBackground.setVisibility(View.VISIBLE);
        mBinding.pbBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        mBinding.viewBackground.setVisibility(View.GONE);
        mBinding.pbBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailed(int tipe, String message) {

        if(tipe==1){
            String[] split = message.trim().split("---");
            SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText(split[0]);
            pDialog.setContentText(split[1]);
            pDialog.setCanceledOnTouchOutside(true);
            pDialog.hideConfirmButton();
            pDialog.show();
        }
    }

    @Override
    public void showUserList(List<UserModel> userModel) {

        usersAdapter = new UsersAdapter(mContext,userModel);
        mBinding.rvUsers.setHasFixedSize(true);
        mBinding.rvUsers.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rvUsers.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvUsers.setAdapter(usersAdapter);
    }
}
