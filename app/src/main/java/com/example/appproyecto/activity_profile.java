package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class activity_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Utilizo la funcion declarada
        pruebaTextView();
    }

    //Esta funcion recoge los datos de las SharedPreferences que nos sean necessarios y los pone en el TextView correspondiente
    private void pruebaTextView(){
        //Con esto Inicializamos un TextView con un nombre especifico y le assignamos el textview que le corresponde
        TextView username = (TextView) findViewById(R.id.username);
        TextView dinero = (TextView) findViewById(R.id.dinero);
        TextView mail = (TextView) findViewById(R.id.mail);

        //Utilizamos las SharedPreferences para asignar los valores del usuario al campo del perfil
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        username.setText("Username: " + prefs.getString("username",""));
        dinero.setText("Dinero: " + String.valueOf(prefs.getInt("dinero",0)));
        mail.setText("Mail: " + prefs.getString("mail",""));
    }
}