package com.ismailhakkiaydin.notdefteri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NotActivity extends AppCompatActivity {


    EditText not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        not= (EditText) findViewById(R.id.etNot);

        Intent intent=getIntent();
        if(intent!=null){
            String tiklanilanNot=intent.getStringExtra("notIcerik");
            not.setText(tiklanilanNot);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}