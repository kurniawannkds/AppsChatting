package com.androiddevnkds.myapplication.utils.helper;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHelper {
    // For first fragment in every activity
    public static void fragmentInitializer(int resource,
                                           FragmentManager fragmentManager,
                                           Fragment fragment,
                                           Bundle bundle) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (bundle != null) fragment.setArguments(bundle);

        fragmentTransaction.add(resource, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void fragmentChanger(int resource,
                                       FragmentManager fragmentManager,
                                       Fragment fragment,
                                       Bundle bundle, boolean addToBackstack) {
        if (fragment != null) {
            if (bundle != null) fragment.setArguments(bundle);
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(resource, fragment);
            if (addToBackstack)
                transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}

