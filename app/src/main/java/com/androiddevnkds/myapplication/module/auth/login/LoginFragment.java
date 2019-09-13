package com.androiddevnkds.myapplication.module.auth.login;

import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.base.BaseFragment;
import com.androiddevnkds.myapplication.databinding.FragmentLoginBinding;
import com.androiddevnkds.myapplication.module.auth.register.RegisterFragment;
import com.androiddevnkds.myapplication.module.main.MainActivity;
import com.androiddevnkds.myapplication.utils.helper.FragmentHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginFragment extends BaseFragment implements LoginContract.loginView {

    private Context mContext;
    private FragmentLoginBinding mBinding;
    private LoginPresenter loginPresenter;

    public LoginFragment(){
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginPresenter = new LoginPresenter(this);

        initUI();
        initEvent();

        return mBinding.getRoot();
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvent() {

        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mBinding.etUserName.getText().toString().trim();
                String password = mBinding.etUserPassword.getText().toString().trim();
                loginPresenter.login(email, password);
            }
        });

        mBinding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentHelper.fragmentChanger(R.id.fl_fragment_container,
                        ((AppCompatActivity) mContext).getSupportFragmentManager(),
                        new RegisterFragment(), null, false);
            }
        });
    }

    //mvp

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
    public void onFailed(int tipe,String message) {

        //email
        if(tipe==1){

            mBinding.etUserName.setError(message);
        }
        else if(tipe==2){
            mBinding.etUserPassword.setError(message);
        }
        else {
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

                    // reuse previous dialog instance
//                            sDialog.setTitleText("Deleted!")
//                                    .setContentText("Your imaginary file has been deleted!")
//                                    .setConfirmText("OK")
//                                    .setConfirmClickListener(null)
//                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
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
