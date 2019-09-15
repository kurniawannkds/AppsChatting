package com.androiddevnkds.myapplication.module.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.androiddevnkds.myapplication.R;
import com.androiddevnkds.myapplication.data.DataManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentHostCallback;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginPresenter implements LoginContract.loginPresenter {
    private LoginContract.loginView loginView;

    public LoginPresenter(LoginContract.loginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password) {

        loginView.showProgressBar();
        if(hasError(email,password)){

        }
        else {
            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    Log.e("EMAIL", Objects.requireNonNull(user.getEmail()));
                                }
                                if (user != null && user.getEmail() != null) {
                                    DataManager.can().setUserEmail(user.getEmail());
                                    loginView.hideProgressBar();
                                    loginView.showMain();
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                loginView.hideProgressBar();
                                loginView.onFailed(3, "Authentication failed---check your email and password or sign up");

                            }


                        }
                    });
        }
    }

    @Override
    public void forgotPassword(String email) {

        loginView.showProgressBar();

        if (TextUtils.isEmpty(email)){
           onFailedResetPass(1,"Email cannot be empty");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            onFailedResetPass(1,"Email wrong format");
        }
        else {
            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        onSuccessResetPass("Reset Password---We send an email for reset your password");
                    }

                    else {
                        onFailedResetPass(2,"Failed !---Please try again "+task.getException().getMessage());
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    onFailedResetPass(2,"Failed !---Please try again "+e.getMessage());

                }
            });
        }
    }

    @Override
    public void loginByGoogle(Context context) {
        // Configure Google Sign In

    }

    //validasi
    private boolean hasError(String userName, String password){

        if(TextUtils.isEmpty(userName)){


            onFailed(1,"Email cannot be empty");
            return true;
        }
        else if(TextUtils.isEmpty(password)){

            onFailed(2,"Password cannot be empty");
            return true;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){

            onFailed(1,"Email wrong format");
            return true;
        }

        else {

            return false;
        }
    }

    private void onFailed(int tipe, String message){

        loginView.hideProgressBar();
        loginView.onFailed(tipe,message);
    }

    private void  onFailedResetPass(int tipe, String message){
        loginView.hideProgressBar();
        loginView.onFailedResetPass(tipe,message);
    }

    private void onSuccessResetPass(String message){
        loginView.hideProgressBar();
        loginView.onSuccessResetPassword(message);
    }
}
