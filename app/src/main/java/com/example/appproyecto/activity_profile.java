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

        pruebaTextView();
    }

    private void pruebaTextView(){
        TextView username = (TextView) findViewById(R.id.username);
        TextView dinero = (TextView) findViewById(R.id.dinero);
        TextView mail = (TextView) findViewById(R.id.mail);

        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        username.setText("Username: " + prefs.getString("username",""));
        dinero.setText("Dinero: " + String.valueOf(prefs.getInt("dinero",0)));
        mail.setText("Mail: " + prefs.getString("mail",""));
    }
}