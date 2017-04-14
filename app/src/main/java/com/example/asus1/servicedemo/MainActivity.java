package com.example.asus1.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start_but;
    private Button stop_but;
    private Button conn_but;
    private Button dis_conn_but;
    private Button fore_service;
    private Button intent_service;



    private MyService.DownloadBinder binder;//加强活动与service的联系
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //这里的ibinder就是Service中onBind返回的Binder
            binder= (MyService.DownloadBinder) iBinder;
            binder.showDownloadMessage();
            Log.d("Service","绑定了d");

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //这里面的语句都不能执行就
            Log.d("Service","取消绑定了d");
            Log.i("Service","取消绑定了i");
            Log.e("Service","取消绑定了e");
            Toast.makeText(MainActivity.this, "取消绑定了d", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main_layout);

        start_but= (Button) findViewById(R.id.start_service_but);
        stop_but= (Button) findViewById(R.id.stop_service_but);
        conn_but= (Button) findViewById(R.id.conn_service);
        dis_conn_but= (Button) findViewById(R.id.dis_conn_but);
        fore_service= (Button) findViewById(R.id.fore_service);
        intent_service= (Button) findViewById(R.id.intent_service_but);

        start_but.setOnClickListener(this);

        stop_but.setOnClickListener(this);
        conn_but.setOnClickListener(this);
        dis_conn_but.setOnClickListener(this);
        fore_service.setOnClickListener(this);
        intent_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.start_service_but:
            {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                startService(intent);
                break;
            }
            case R.id.stop_service_but:
            {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                stopService(intent);
                break;
            }
            case R.id.conn_service:
            {
                Intent intent=new Intent(this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);//绑定并自动创建服务
                break;
            }
            case R.id.dis_conn_but:
            {

                try {
                    unbindService(connection);
                } catch (Exception e) {
                    Toast.makeText(this, "服务已经解除绑定！", Toast.LENGTH_SHORT).show();
                }

                break;
            }
            case R.id.fore_service:
            {
                Intent intent=new Intent(MainActivity.this,ForeService.class);
                startService(intent);
                break;
            }
            case R.id.intent_service_but:
            {
                Log.d("MainActivity","Thread id is"+Thread.currentThread().getId());
                Intent intent=new Intent(this,MyIntentService.class);
                startService(intent);
                break;
            }

        }
    }
}
