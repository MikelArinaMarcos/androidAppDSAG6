package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language = (Button) findViewById(R.id.idiomaBtn);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, LanguageActivity.class));
            }
        });


        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String value = prefs.getString("name", "");
        if(value.isEmpty()){
            System.out.printf("Shared Preferences vacio");
        } else {
            startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
        }

        configureLoginButton();
        configureRegisterButton();
    }

    private void configureLoginButton(){
        Button LoginButton = (Button) findViewById(R.id.Login_button);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void configureRegisterButton(){
        Button RegisterButton = (Button) findViewById(R.id.Register_button);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
}