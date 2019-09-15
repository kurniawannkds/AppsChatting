package com.androiddevnkds.myapplication.module.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseFragment;
import com.androiddevnkds.myapplication.databinding.FragmentFriendsBinding;
import com.androiddevnkds.myapplication.databinding.FragmentRegisterBinding;
import com.androiddevnkds.myapplication.model.UserModel;
import com.androiddevnkds.myapplication.module.auth.register.RegisterContract;
import com.androiddevnkds.myapplication.module.auth.register.RegisterPresenter;
import com.androiddevnkds.myapplication.module.main.MainActivity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FriendsFragment extends BaseFragment implements FriendsContract.friendsView {

    private Context mContext;
    private FragmentFriendsBinding mBinding;
    private FriendsPresenter friendsPresenter;

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

    }

    @Override
    public void initEvent() {


    }

}
