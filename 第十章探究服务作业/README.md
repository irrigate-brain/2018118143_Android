# 一、实验目的

#### 探究服务



# 二、实验过程

#### 启动和停止服务核心代码：

```java
package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Myservice","onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Myservice","onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("Myservice","onDestroy executed");
        super.onDestroy();
    }
}

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = (Button) findViewById(R.id.start_service);
        Button stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            default:
                break;
        }
    }
}
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E7%AC%AC%E5%8D%81%E7%AB%A0%E6%8E%A2%E7%A9%B6%E6%9C%8D%E5%8A%A1%E4%BD%9C%E4%B8%9A/1.png?raw=true)

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E7%AC%AC%E5%8D%81%E7%AB%A0%E6%8E%A2%E7%A9%B6%E6%9C%8D%E5%8A%A1%E4%BD%9C%E4%B8%9A/2.png?raw=true)



#### 活动和服务进行通信核心代码：

```java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = (Button) findViewById(R.id.start_service);
        Button stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        Button bindService = (Button) findViewById(R.id.bind_service);
        Button unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default:
                break;
        }
    }

package com.example.servicetest;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d("Myservice","startDownload executed");
        }

        public int getProgress() {
            Log.d("Myservice","getProgress executed");
            return 0;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Myservice","onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Myservice","onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("Myservice","onDestroy executed");
        super.onDestroy();
    }
}

```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E7%AC%AC%E5%8D%81%E7%AB%A0%E6%8E%A2%E7%A9%B6%E6%9C%8D%E5%8A%A1%E4%BD%9C%E4%B8%9A/3.png?raw=true)

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E7%AC%AC%E5%8D%81%E7%AB%A0%E6%8E%A2%E7%A9%B6%E6%9C%8D%E5%8A%A1%E4%BD%9C%E4%B8%9A/4.png?raw=true)



#### 前台服务核心代码：

```java
@Override
    public void onCreate() {
        super.onCreate();
        Log.d("Myservice","onCreate executed");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E7%AC%AC%E5%8D%81%E7%AB%A0%E6%8E%A2%E7%A9%B6%E6%9C%8D%E5%8A%A1%E4%BD%9C%E4%B8%9A/5.png?raw=true)



#### 使用IntentService核心代码：

```java
@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("MyIntentService","Thread id is "+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestroy executed");
    }
}

<Button
        android:id="@+id/start_intent_service"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Start IntentService" />
            
case R.id.start_intent_service:
                Log.d("MainActivity","Thread id is "+Thread.currentThread().getId());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;

Button startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E7%AC%AC%E5%8D%81%E7%AB%A0%E6%8E%A2%E7%A9%B6%E6%9C%8D%E5%8A%A1%E4%BD%9C%E4%B8%9A/6.png?raw=true)

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E7%AC%AC%E5%8D%81%E7%AB%A0%E6%8E%A2%E7%A9%B6%E6%9C%8D%E5%8A%A1%E4%BD%9C%E4%B8%9A/7.png?raw=true)





# 三、总结

#### 通过这次实验，我深刻理解了服务

