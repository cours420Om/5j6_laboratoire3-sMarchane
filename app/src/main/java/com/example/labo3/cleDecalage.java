package com.example.labo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class cleDecalage extends AppCompatActivity {

    EditText etCleDecalage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cle_decalage);


        etCleDecalage = (EditText) findViewById(R.id.et_cle);
    }
}