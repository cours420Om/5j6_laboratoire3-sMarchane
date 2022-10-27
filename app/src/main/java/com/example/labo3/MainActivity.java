package com.example.labo3;

import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    EditText et_texte, et_cleDecalage;
    Button btn_crypter, btn_decrypter;
    TextView tv_recu;
    MenuItem mnu_ouvrir;
    private String nomFichier = "messageChiffrement.txt";
    private String chemin = "MyFileStorage";
    File monFichierExternes;
    String mesDonnees = "";
    private static final String alphabetString = "abcdefghijklmnopqrstuvwxyz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mnu_ouvrir = (MenuItem)findViewById(R.id.mnu_ouvrir);
        et_texte = (EditText)findViewById(R.id.et_text);
        btn_crypter = (Button)findViewById(R.id.btn_crypter);
        btn_decrypter = (Button)findViewById(R.id.btn_decrypter);
        et_cleDecalage = (EditText)findViewById(R.id.et_cle);
        tv_recu = (TextView)findViewById(R.id.tv_reception);




        Toolbar tb_action = (Toolbar) findViewById(R.id.tb_barreAction);
        setSupportActionBar(tb_action);

        if(!stockageExterneDisponible() || stockageExterneLectureSeule()){
            btn_crypter.setEnabled(false);
            btn_decrypter.setEnabled(false);
        }else{
            monFichierExternes = new File(getExternalFilesDir(chemin), nomFichier);
        }

        //Bouton crypté
        btn_crypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle extras = getIntent().getExtras();
                String cle = extras.getString("cle");

                crypter(et_texte.getText().toString(), Integer.parseInt(cle));/*try {
                    FileOutputStream fichierOut = new FileOutputStream(monFichierExternes);
                    fichierOut.write(et_texte.getText().toString().getBytes());
                    fichierOut.close();
                    et_texte.setText("");
                }catch (IOException e){
                    e.printStackTrace();
                }
                /*FileInputStream fichierIN = null;
                try{
                    fichierIN = new FileInputStream(monFichierExternes);
                    DataInputStream streamReader = new DataInputStream(fichierIN);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamReader));
                    String ligne;
                    while((ligne = bufferedReader.readLine()) != null){
                        mesDonnees = mesDonnees + ligne;
                    }
                    streamReader.close();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }*/
                //tv_recu.setText(mesDonnees);
            }
        });
    }
    public String crypter(String message, int cle){
        message = message.toLowerCase();
        String messageCrypter = "";

        for(int i = 0; i < message.length(); i++){
            int charPos = alphabetString.indexOf(message.charAt(i));
            int key = (cle + charPos) % 26;
            char replace = alphabetString.charAt(key);
            messageCrypter += replace;
            tv_recu.setText(messageCrypter);
        }
        return messageCrypter;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }


    private static boolean stockageExterneLectureSeule(){
    String extStorageState = Environment.getExternalStorageState();
    if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
        return true;
    }
    return false;
    }


    private static boolean stockageExterneDisponible(){
    String extStorageState = Environment.getExternalStorageState();
    if(Environment.MEDIA_MOUNTED.equals(extStorageState)){
        return true;
    }
    return false;
    }


    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.mnu_ouvrir:


                FileInputStream fichierIN = null;
                try{
                    fichierIN = new FileInputStream(monFichierExternes);
                    DataInputStream streamReader = new DataInputStream(fichierIN);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamReader));
                    String ligne;
                    while((ligne = bufferedReader.readLine()) != null){
                        mesDonnees = mesDonnees + ligne;
                    }
                    streamReader.close();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
                et_texte.getText();
                tv_recu.setText(mesDonnees);
                return true;
            case R.id.mnu_cle:

                Intent intent = new Intent(MainActivity.this, cleDecalage.class);
                startActivity(intent);



                return true;
            case R.id.mnu_quitter:
                System.exit(0);
                return true;
            case R.id.mnu_sauvegarde:
                try {
                    FileOutputStream fichierOut = new FileOutputStream(monFichierExternes);
                    fichierOut.write(et_texte.getText().toString().getBytes());
                    fichierOut.close();
                    et_texte.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), nomFichier + " sauvegarder", Toast.LENGTH_LONG);
                    toast.show();
                }catch (IOException e){
                    e.printStackTrace();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}