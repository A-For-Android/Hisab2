package com.wix.traitsoft.tpo_mnnit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import java.util.HashMap;

/**
 * Created by shiva on 25-09-2017.
 */

public class Session {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Session(Context context)
    {
        sharedPreferences = context.getSharedPreferences("Cms",0);
        editor = sharedPreferences.edit();
    }

    public boolean isNull()
    {
        if(sharedPreferences.getString("username",null)==null&&sharedPreferences.getString("password",null)==null)
        return true;
        return false;
    }

    public void store(String username,String password)
    {
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }

    public Pair<String,String> getDetails()
    {

        String username = sharedPreferences.getString("username",null);
        String password = sharedPreferences.getString("password",null);
        Pair<String,String>hm  = new Pair<>(username,password);
        return hm;
    }

    public void clear()
    {
        editor.clear();
        editor.commit();
    }

}
