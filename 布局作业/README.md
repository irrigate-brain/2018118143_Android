# 一、实验目的

#### 掌握布局



# 二、实验过程

#### 线性布局核心代码：

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button 1" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button 2" />

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button 3" />
</LinearLayout>
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E5%B8%83%E5%B1%80%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE1.png?raw=true)

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        android:text="Button 1" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        android:text="Button 2" />

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        android:text="Button 3" />
</LinearLayout>
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E5%B8%83%E5%B1%80%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE2.png?raw=true)

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="top"
        android:text="Button 1" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:text="Button 2" />

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:text="Button 3" />
</LinearLayout>
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E5%B8%83%E5%B1%80%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE3.png?raw=true)

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <EditText
        android:id="@+id/input_message"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:hint="Type something"
        />

    <Button
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Send"
        />
</LinearLayout>
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E5%B8%83%E5%B1%80%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE4.png?raw=true)

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <EditText
        android:id="@+id/input_message"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:hint="Type something"
        />

    <Button
        android:id="@+id/send"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Send"
        />
</LinearLayout>
```

#### 运行结果：

![](https://github.com/irrigate-brain/2018118143_Android/blob/homework/%E5%B8%83%E5%B1%80%E4%BD%9C%E4%B8%9A/%E6%88%AA%E5%9B%BE5.png?raw=true)

# 三、总结

#### 通过这次实验，我深刻理解了线性布局

