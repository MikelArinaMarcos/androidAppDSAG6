package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appproyecto.modelo.Issue;
import com.example.appproyecto.modelo.Swagger;
import com.example.appproyecto.modelo.User;
import com.example.appproyecto.modelo.UserLogin;

import retrofit2.Call;


public class DenunciaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        //Inicializamos el usuario
        Issue issue = new Issue();

        configureEnviarButton();
    }

    private void configureEnviarButton() {

        //Inicializamos y le damos los valores al Button y a los EditText
        Button EnviarButton = (Button) findViewById(R.id.enviar);
        EditText informer = (EditText) findViewById(R.id.textNombre);
        EditText message = (EditText) findViewById(R.id.textDenuncia);
        EditText date = (EditText) findViewById(R.id.textDate) ;

        EnviarButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //Inicializamos y llamamos al swagger para poder hacer las consultas pertinentes
                Swagger swagger = Swagger.retrofit.create(Swagger.class);
                //Assignamos valores a una classe Issue
                Issue issue = new Issue(date.getText().toString() ,informer.getText().toString(),message.getText().toString());
                Call<Issue> call = swagger.Issue(issue);






            }
        });
        }
    }
