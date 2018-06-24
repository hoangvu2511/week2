package com.example.soundloneteamcomp.searcharticle.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;

import com.example.soundloneteamcomp.searcharticle.R;

import butterknife.BindView;

public class NetWorkUtil {
    private static Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static void setContext(Context context) {
        NetWorkUtil.context = context;
    }

    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
            return  netInfo!= null && netInfo.isConnectedOrConnecting();
        }

        return false;
    }
    public static Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
