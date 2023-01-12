package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LanguageActivity extends AppCompatActivity {

    TextView txt_bienvenida;
    Button btn_cambiar_idioma;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        txt_bienvenida = (TextView) findViewById(R.id.mensajeIdioma);
        btn_cambiar_idioma = (Button) findViewById(R.id.button);

        intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings");

        btn_cambiar_idioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });
    }
}