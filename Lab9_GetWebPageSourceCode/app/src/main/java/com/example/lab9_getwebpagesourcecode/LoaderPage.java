package com.example.lab9_getwebpagesourcecode;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LoaderPage extends AsyncTaskLoader<String> {
    String queryString;
    Context mContext;
    String mTransProtocol;

    public LoaderPage(@NonNull Context context, String queryString, String mTransProtocol) {
        super(context);
        mContext=context;
        this.queryString= queryString;
        this.mTransProtocol=mTransProtocol;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getSoruceCode(mContext,queryString,mTransProtocol);

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
