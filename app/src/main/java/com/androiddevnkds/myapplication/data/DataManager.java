package com.androiddevnkds.myapplication.data;

public class DataManager implements DataManagerType {

    private static DataManager dm;

    public static DataManager can() // or use, or call (?)
    {
        if (dm == null) {
            dm = new DataManager();
        }
        return dm;
    }
}
