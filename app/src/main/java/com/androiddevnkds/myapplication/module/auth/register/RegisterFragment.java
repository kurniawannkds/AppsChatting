package com.androiddevnkds.myapplication.module.auth.register;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseFragment;
import com.androiddevnkds.myapplication.databinding.FragmentLoginBinding;
import com.androiddevnkds.myapplication.databinding.FragmentRegisterBinding;
import com.androiddevnkds.myapplication.model.UserModel;
import com.androiddevnkds.myapplication.module.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterFragment extends BaseFragment implements RegisterContract.registerView {

    private Context mContext;
    private FragmentRegisterBinding mBinding;
    private RegisterPresenter registerPresenter;

    public RegisterFragment() {
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        registerPresenter = new RegisterPresenter(this);
        initUI();
        initEvent();

        return mBinding.getRoot();
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvent() {

        mBinding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mBinding.etEmail.getText().toString().trim();
                String password = mBinding.etUserPassword.getText().toString().trim();
                String confPassword = mBinding.etUserConfirmPassword.getText().toString().trim();
                String userName = mBinding.etUserName.getText().toString().trim();

                UserModel userModel = new UserModel(email,userName,"");
                registerPresenter.register(userModel,password,confPassword);
            }
        });
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

        //email
        if(tipe==1){
            mBinding.etEmail.setError(message);
        }
        else if(tipe==2){
            mBinding.etUserPassword.setError(message);
        }

        else if(tipe==3){

            mBinding.etUserConfirmPassword.setError(message);
        }
        else if(tipe==4){

            mBinding.etUserPassword.setError(message);
            mBinding.etUserConfirmPassword.setError(message);
        }
        else if(tipe==5){
            mBinding.etUserName.setError(message);
        }

        else{

            String[] split = message.trim().split("---");
            SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
//            pDialog.getProgressHelper().setBarColor(Color.parseColor("#BA5B50"));
            pDialog.setTitleText(split[0]);
            pDialog.setContentText(split[1]);
            pDialog.setCancelable(true);
            pDialog.hideConfirmButton();
            pDialog.show();
            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {

                }
            });
        }
    }

    @Override
    public void showMain() {

        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getActivity()).finish();
        }
    }
}
