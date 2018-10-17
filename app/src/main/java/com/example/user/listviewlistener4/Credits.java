package com.example.user.listviewlistener4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.user.listviewlistener4.R;

public class Credits extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        tv = findViewById(R.id.tv);
        tv.setText("Made by: \n" + "Sharon Danieli");
    }

    public void ToActivity2(View view) {
        finish();
    }
}
