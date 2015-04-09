package me.mattlogan.rhymecity.log;

import android.util.Log;

import retrofit.RestAdapter;

public class DebugLogger implements RestAdapter.Log {

    @Override
    public void log(String message) {
        Log.d("logger", message);
    }
}
