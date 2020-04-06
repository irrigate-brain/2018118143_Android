package com.example.androidthreadtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int UPDATE_TEXT = 1;
    private static final int  END_TEXT = 2;

    private  TextView text;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    // 在这里可以进行UI操作
                    Pa tmp;
                    tmp =((Pa) msg.obj);
                    text.setText("我是子线程："+tmp.getTid()+"\n  我的值是："+tmp.getCounter());
                    break;
                case END_TEXT:

                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        Button changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
        text.setText("我是："+0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_text:
                DemoThread thread1 = new DemoThread();
                Log.d("子线程号：", " "+ thread1.getId()) ;
                try {
                    //Thread.sleep(1000);
                    thread1.start();
                }catch (Exception e){

                }
               // Log.d("子线程号：", " "+ thread1.getId()) ;
                Log.d("主线程号：", " " + Thread.currentThread().getId());
               // text.setText("我是："+counter);
                break;
        }
    }

    class DemoThread extends Thread{
        private  int counter = 0;
        @Override
        public void run() {
            while(true){
                counter++;
                Message message = new Message();
                message.what = UPDATE_TEXT;
                message.obj =new Pa(Thread.currentThread().getId(),counter);
                handler.sendMessage(message); // 将Message对象发送出去
                try {
                    Thread.sleep(6000);
                }catch (Exception e){
                        break;
                }
            }
            Message message = new Message();
            message.what = END_TEXT;
        }
    }

    class Pa {
        private  long tid;
        private  int counter;
        public Pa(long tid, int counter){
            this.tid = tid;
            this.counter = counter;
        }

        public int getCounter() {
            return counter;
        }

        public long getTid() {
            return tid;
        }
    }
}
