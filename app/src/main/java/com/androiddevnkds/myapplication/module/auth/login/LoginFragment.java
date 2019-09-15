package com.androiddevnkds.myapplication.module.auth.login;

import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.adapter.UsersAdapter;
import com.androiddevnkds.myapplication.base.BaseFragment;
import com.androiddevnkds.myapplication.data.DataManager;
import com.androiddevnkds.myapplication.databinding.FragmentLoginBinding;
import com.androiddevnkds.myapplication.model.UserModel;
import com.androiddevnkds.myapplication.module.auth.register.RegisterFragment;
import com.androiddevnkds.myapplication.module.main.MainActivity;
import com.androiddevnkds.myapplication.utils.helper.FragmentHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginFragment extends BaseFragment implements LoginContract.loginView {

    private Context mContext;
    private FragmentLoginBinding mBinding;
    private LoginPresenter loginPresenter;
    private static final int RC_SIGN_IN = 100 ;

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

        mBinding.btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgressBar();

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(mContext,gso);

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        mBinding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.lyDialogForgotPass.etForgotPassword.setText("");
                mBinding.lyBlack.lyBlack.setVisibility(View.VISIBLE);
                mBinding.lyDialogForgotPass.lyDialogForgotPass.setVisibility(View.VISIBLE);
            }
        });

        mBinding.lyBlack.lyBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.lyBlack.lyBlack.setVisibility(View.GONE);
                mBinding.lyDialogForgotPass.lyDialogForgotPass.setVisibility(View.GONE);
            }
        });

        mBinding.lyDialogForgotPass.btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mBinding.lyDialogForgotPass.etForgotPassword.getText().toString().trim();
                loginPresenter.forgotPassword(email);
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
            pDialog.setCanceledOnTouchOutside(true);
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

        hideProgressBar();
    }

    @Override
    public void showMain() {

        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getActivity()).finish();
        }
    }

    @Override
    public void onFailedResetPass(int tipe, String message) {
        if(tipe == 1){
           mBinding.lyDialogForgotPass.etForgotPassword.setError(message);
        }
        else {

            String[] split = message.trim().split("---");
            final SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
//            pDialog.getProgressHelper().setBarColor(Color.parseColor("#BA5B50"));
            pDialog.setTitleText(split[0]);
            pDialog.setContentText(split[1]);
            pDialog.hideConfirmButton();
            pDialog.setCanceledOnTouchOutside(true);
            pDialog.show();
        }

        hideProgressBar();
    }

    @Override
    public void onSuccessResetPassword(String message) {

        mBinding.lyBlack.lyBlack.setVisibility(View.GONE);
        mBinding.lyDialogForgotPass.lyDialogForgotPass.setVisibility(View.GONE);
        String[] split = message.trim().split("---");
        final SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
//            pDialog.getProgressHelper().setBarColor(Color.parseColor("#BA5B50"));
        pDialog.setTitleText(split[0]);
        pDialog.setContentText(split[1]);
        pDialog.setCanceledOnTouchOutside(true);
        pDialog.setConfirmText("Oke");
        pDialog.show();
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {

                pDialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            final FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null && user.getEmail() != null) {


                                UserModel userModel = new UserModel(user.getEmail(),user.getDisplayName(),"");
                                FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                                mFirestore.document("Users/"+user.getEmail()).set(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        DataManager.can().setUserEmail(user.getEmail());
                                        hideProgressBar();
                                        showMain();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        hideProgressBar();
                                        onFailed(3,"Register failed----Please try again");
                                    }
                                });


                            }
                            else {
                                hideProgressBar();
                                onFailed(3,"Register failed----Please try again");
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            hideProgressBar();
                            onFailed(3, "Authentication failed---check your email and password or sign up "+task.getException().getMessage());

                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                hideProgressBar();
                onFailed(3, "Authentication failed---check your email and password or sign up "+e.getMessage());
            }
        });
    }
}
