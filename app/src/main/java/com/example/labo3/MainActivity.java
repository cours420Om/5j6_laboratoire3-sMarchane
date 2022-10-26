package com.example.labo3;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar tb_action = (Toolbar) findViewById(R.id.tb_barreAction);
        setSupportActionBar(tb_action);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }


}