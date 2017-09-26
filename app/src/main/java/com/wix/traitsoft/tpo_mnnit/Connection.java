package com.wix.traitsoft.tpo_mnnit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Asus on 03-09-2017.
 */

public class Connection {
    private Context context;
    Connection(Context context)
    {
        this.context = context;
    }

    public int checkConnection()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected())
            return 1;
        else
        {
            return 0;
        }
    }

}
