# 一、实验目的

#### 探究Intent应用



# 二、实验过程

#### 显式Intent核心代码：

```java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
```

#### 运行结果：

![]()



#### 隐形Intent核心代码：

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("http://www.baidu.com"));
                //intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
                //Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
            }
        });
    }

<activity android:name=".ThirdActivity">
            <intent-filter tools:ignore="AppLinkUrlError">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:scheme="http" />
            </intent-filter>
        </activity>

        <activity android:name=".SecondActivity">
            <intent-filter>
                <action android:name="com.example.activitytest.ACTION_START" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="com.example.activitytest.MY_CATEGORY" />
            </intent-filter>
        </activity>
        <activity
            android:name=".FirstActivity"
            android:label="This is FirstActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
```

#### 运行结果：

![]()





# 三、总结

#### 通过这次实验，我深刻理解了Intent应用

