package com.androiddevnkds.myapplication.data;

import com.androiddevnkds.myapplication.data.local.UserStorage;
import com.google.gson.Gson;

public class DataManager implements DataManagerType {

    private static DataManager dm;
    private UserStorage sUserStorage = new UserStorage();

    public static DataManager can() // or use, or call (?)
    {
        if (dm == null) {
            dm = new DataManager();
        }
        return dm;
    }

    @Override
    public String getUserInfoFromStorage() {
        return sUserStorage.getUserEmail();
    }

    @Override
    public void setUserEmail(String val) {
        sUserStorage.setUserEmail(val);
    }

    @Override
    public void removeUserEmail() {
        sUserStorage.removeUserEmail();
    }
}
