package com.example.asus1.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("IntentService","Thread id is "+Thread.currentThread().getId());
        //这是在子线程中运行的代码
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("IntentService","onDestroy ");
    }
}
