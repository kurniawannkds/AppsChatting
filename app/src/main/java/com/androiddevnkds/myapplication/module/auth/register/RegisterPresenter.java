package com.androiddevnkds.myapplication.module.auth.register;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.androiddevnkds.myapplication.data.DataManager;
import com.androiddevnkds.myapplication.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;
import java.util.concurrent.Executor;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegisterPresenter implements RegisterContract.registerPresenter {

    private RegisterContract.registerView registerView;

    public RegisterPresenter(RegisterContract.registerView registerView){
        this.registerView = registerView;
    }

    @Override
    public void register(final UserModel userModel, final String password, String confirmPass) {

        registerView.showProgressBar();
        if(validateInput(userModel,password,confirmPass)){}
        else {

            final FirebaseAuth mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(userModel.getEmail(), password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");

                                Log.e("regis","masuk sukses");
                                final FirebaseUser user = mAuth.getCurrentUser();

                                if (user != null && user.getEmail() != null) {

                                    Log.e("regis","user gak null");

                                    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                                    Log.e("regis","Users/"+user.getEmail());
                                    mFirestore.document("Users/"+user.getEmail()).set(userModel).
                                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Log.e("regis","masuk sukses add");

                                            DataManager.can().setUserEmail(user.getEmail());
                                            registerView.hideProgressBar();
                                            registerView.showMain();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Log.e("regis","masuk failed");
                                            Log.e("regis",e.getMessage());

                                            AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), password);
                                            user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Log.d(TAG, "User account deleted!");
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                            registerView.onFailed(10,"Register failed----Please try again");
                                        }
                                    });
                                }
                                else {
                                    registerView.onFailed(10,"Register failed----Please try again");
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                registerView.onFailed(10,"Register failed----Email wrong or already register");

                            }
                        }
                    });
        }
    }

    private boolean validateInput(UserModel userModel, String password, String confirmPass){

        String userEmail = userModel.getEmail();
        String nameUser = userModel.getName();

        if(TextUtils.isEmpty(userEmail)){


            onFailed(1,"Email cannot be empty");
            return true;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){

            onFailed(1,"Email wrong format");
            return true;
        }

        else if(TextUtils.isEmpty(password)){

            onFailed(2,"Password cannot be empty");
            return true;
        }

        else if(TextUtils.isEmpty(confirmPass)){

            onFailed(3,"Confirm password cannot be empty");
            return true;
        }

        else if(!confirmPass.equalsIgnoreCase(password)){

            onFailed(4,"Password and confirm password didn't match");
            return true;
        }

        else if(TextUtils.isEmpty(nameUser)){

            onFailed(5,"Username cannot be empty");
            return true;
        }

        else {

            return false;
        }
    }

    private void onFailed(int tipe, String message){

        registerView.hideProgressBar();
        registerView.onFailed(tipe,message);
    }
}
