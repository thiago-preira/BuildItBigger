package com.udacity.gradle.builditbigger.async;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi sMyApi = null;
    private OnJokeReceiveListener mOnJokeReceiveListener;


    public interface OnJokeReceiveListener {
        void onJokeReceive(String data);
    }

    public EndpointsAsyncTask(OnJokeReceiveListener OnJokeReceiveListener) {
        this.mOnJokeReceiveListener = OnJokeReceiveListener;
    }


    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... params) {
        if (sMyApi == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-224421.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            sMyApi = builder.build();
        }

        try {
            return sMyApi.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mOnJokeReceiveListener.onJokeReceive(result);
    }
}
