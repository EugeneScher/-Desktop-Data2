package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Tablichka extends AppCompatActivity implements View.OnClickListener {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablichka);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
