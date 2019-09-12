package com.androiddevnkds.myapplication.module.login;

import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseFragment;
import com.androiddevnkds.myapplication.databinding.FragmentLoginBinding;
import com.androiddevnkds.myapplication.utils.konstanta.K;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class LoginFragment extends BaseFragment {

    private Context mContext;
    private FragmentLoginBinding mBinding;

    public LoginFragment() {
        // Required empty public constructor
        setArguments(new Bundle());
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

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
