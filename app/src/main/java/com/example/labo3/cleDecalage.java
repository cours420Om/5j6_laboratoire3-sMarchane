package com.example.labo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cleDecalage extends AppCompatActivity {

    EditText etCleDecalage;
    Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cle_decalage);

        Intent retour = new Intent();

        etCleDecalage = (EditText) findViewById(R.id.et_cle);
        btnValider = (Button) findViewById(R.id.btn_valider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value=etCleDecalage.getText().toString();
                if(value.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Veuillez rentrer une clé valide", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("cle", value);

                    startActivity(intent);
                }



            }
        });
    }
}