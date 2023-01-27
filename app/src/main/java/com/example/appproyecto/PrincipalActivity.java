package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        configureTiendaButton();

        configureLogoutButton();

        configureProfileButton();

        configureRankingButton();

        configureDenunciaButton();

        configureForoButton();
    }

    private void configureForoButton(){
        Button button = (Button) findViewById(R.id.button_foro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, Foro.class));
            }
        });
    }

    private void configureRankingButton(){
        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, Ranking.class));
            }
        });
    }

    private void configureProfileButton(){
        Button button = (Button) findViewById(R.id.button_profile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, activity_profile.class));
            }
        });
    }

    private void configureLogoutButton() {
        Button button = (Button) findViewById(R.id.button_logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(PrincipalActivity.this, MainActivity.class));
            }
        });
    }

    private void configureTiendaButton(){
        Button tienda_button = (Button) findViewById(R.id.tienda);
        tienda_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, tienda.class));
            }
        });
    }

    private void configureDenunciaButton(){
        Button button6 = (Button) findViewById(R.id.button2);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, DenunciaActivity.class));
            }
        });
    }
}