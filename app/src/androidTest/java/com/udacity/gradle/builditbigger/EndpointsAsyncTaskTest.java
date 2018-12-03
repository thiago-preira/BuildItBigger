package com.udacity.gradle.builditbigger;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.gradle.builditbigger.async.EndpointsAsyncTask;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class EndpointsAsyncTaskTest implements EndpointsAsyncTask.OnJokeReceiveListener {
    public static final int TIMEOUT = 40;
    private final String TAG = EndpointsAsyncTaskTest.class.getSimpleName();
    private String joke;

    @Test
    public void doInBackground() {
        try {
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
            endpointsAsyncTask.execute();
            String result = endpointsAsyncTask.get(TIMEOUT, TimeUnit.SECONDS);
            assertNotNull(result);
            assertFalse(TextUtils.isEmpty(result));
            Log.e(TAG, "resultado: "+result );
        } catch (Exception e) {
            Log.e(TAG, "erro");
            fail();
        }
    }

    @Override
    public void onJokeReceive(String data) {
        this.joke = data;
    }
}
