package com.ben.clover.util;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckNetworkConnection extends AsyncTask< Void, Void, Boolean > {
    private OnConnectionCallback onConnectionCallback;
    private Context context;

    public CheckNetworkConnection(Context con, OnConnectionCallback onConnectionCallback) {
        super();
        this.onConnectionCallback = onConnectionCallback;
        this.context = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void...params) {
        if (context == null)
            return false;

        boolean isConnected = false;
        try {
            URL url = new URL("https://www.google.com/");

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 30);
            urlc.connect();

            if (urlc.getResponseCode() == 200) {
                isConnected = true;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            isConnected = false;
        } catch (IOException e) {
            e.printStackTrace();
            isConnected = false;
        }
        return isConnected;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);

        if (b) {
            onConnectionCallback.onConnectionSuccess();
        } else {
            String msg = "No Internet Connection";
            if (context == null)
                msg = "Context is null";
            onConnectionCallback.onConnectionFail(msg);
        }

    }

    public interface OnConnectionCallback {
        void onConnectionSuccess();

        void onConnectionFail(String errorMsg);
    }
}