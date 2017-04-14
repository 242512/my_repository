package com.example.asus1.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private DownloadBinder binder=new DownloadBinder();
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.d("Service","onStartCommand");//只有创建时才会执行
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service","onDestroy");
    }


    class DownloadBinder extends Binder
    {
        public void showDownloadMessage()
        {
            Log.d("Service","这是Binder中的方法执行信息");
        }
    }
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }
}
