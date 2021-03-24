package com.example.lab3_hello_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private Button zero_btn;
    private Button count_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        zero_btn = (Button) findViewById(R.id.button_zero);
        count_btn = (Button) findViewById(R.id.button_count);
    }

    @Override
    public  void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("mCount", mCount);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public  void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCount = savedInstanceState.getInt("mCount");
        show();
    }

    public void show() {
        if(mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        zero_btn.setBackgroundColor(Color.BLACK);
        if(mCount % 2 != 0) {
            count_btn.setBackgroundColor(Color.GREEN);
        } else {
            count_btn.setBackgroundColor(Color.RED);
        }
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void zeroClear(View view) {
        mCount = 0;
        mShowCount.setText(Integer.toString(mCount));
        count_btn.setBackgroundColor(Color.RED);
        zero_btn.setBackgroundColor(Color.GRAY);
    }

    public void countUp(View view) {
        mCount++;
        show();
    }
}
