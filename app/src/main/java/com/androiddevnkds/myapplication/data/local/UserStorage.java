package com.androiddevnkds.myapplication.data.local;

import com.androiddevnkds.myapplication.utils.konstanta.K;
import com.orhanobut.hawk.Hawk;

public class UserStorage implements CacheContract {
    @Override
    public boolean isCacheValid(String key) {
        return false;
    }

    //email
    public void setUserEmail(String val) {
        Hawk.put(K.KEY_USER_EMAIL, val);
    }

    public String getUserEmail() {
        return Hawk.get(K.KEY_USER_EMAIL);
    }

    public void removeUserEmail() {
        Hawk.delete(K.KEY_USER_EMAIL);
    }
}
