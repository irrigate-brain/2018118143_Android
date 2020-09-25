package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean OnOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.add_item:
             Toast.makeText(this,"You clicked Add",Toast.LENGTH_SHORT).show();
             break;
        case R.id.remove_item:
            Toast.makeText(this,"You clicked Remove",Toast.LENGTH_SHORT).show();
            break;
        default:
        }
        return true;
    }
}