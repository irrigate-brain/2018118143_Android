# 一、实验目的

#### 掌握活动的四种启动模式



# 二、实验过程

#### standard启动模式核心代码：

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity",this.toString());
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });
    }
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/2018118143%E7%AC%AC%E4%BA%8C%E6%AC%A1%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE1.png?raw=true)



#### singleTop启动模式核心代码：

```java
<activity
            android:name=".FirstActivity"
            android:launchMode="singleTop"
            android:label="This is FirstActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
</activity>
                    
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity",this.toString());
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Task id is "+getTaskId());
        setContentView(R.layout.second_layout);
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/2018118143%E7%AC%AC%E4%BA%8C%E6%AC%A1%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE2.png?raw=true)

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/2018118143%E7%AC%AC%E4%BA%8C%E6%AC%A1%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE3.png?raw=true)



#### singleTask启动模式核心代码：

```java
<activity
            android:name=".FirstActivity"
            android:launchMode="singleTask"
            android:label="This is FirstActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
</activity>
                    
@Override
protected void onRestart() {
    super.onRestart();
    Log.d("FirstActivity","onRestart");
}

@Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
}
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/2018118143%E7%AC%AC%E4%BA%8C%E6%AC%A1%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE4.png?raw=true)



#### singleInstance启动模式核心代码：

```java
<activity android:name=".SecondActivity"
        android:launchMode="singleInstance">
        <intent-filter>
            <action android:name="com.example.activitytest.ACTION_START" />

            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="com.example.activitytest.MY_CATEGORY" />
        </intent-filter>
</activity>
                
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity","Task id is "+getTaskId());
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ThirdActivity","Task id is "+getTaskId());
        setContentView(R.layout.third_layout);
    }
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/2018118143%E7%AC%AC%E4%BA%8C%E6%AC%A1%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE5.png?raw=true)



# 三、总结

#### 通过这次实验，我深刻理解了四种活动启动模式的差异

