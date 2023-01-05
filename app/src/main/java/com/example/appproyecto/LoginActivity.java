package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appproyecto.modelo.Swagger;
import com.example.appproyecto.modelo.UserLogin;
import com.example.appproyecto.modelo.User;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configureLoginButton();
    }

    private void configureLoginButton(){
        Button LoginButton = (Button) findViewById(R.id.Login_button);
        EditText mail = (EditText) findViewById(R.id.textMail);
        EditText password = (EditText) findViewById(R.id.textPassword);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Swagger swagger = Swagger.retrofit.create(Swagger.class);
                UserLogin ul = new UserLogin(mail.getText().toString(),password.getText().toString());
                Call<User> call = swagger.Login(ul);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("Respuesta_1",response.toString());
                        if (response.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                            SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("name", mail.getText().toString());
                            editor.commit();
                        }
                        else {
                            Snackbar mySnackbar = Snackbar.make(view, "Inicio de Sesion Incorrecto", BaseTransientBottomBar.LENGTH_SHORT);
                            mySnackbar.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Snackbar mySnackbar = Snackbar.make(view, "No se ha podido Iniciar Sesion / Failure", BaseTransientBottomBar.LENGTH_SHORT);
                        Log.d("Fallada",t.toString());
                        mySnackbar.show();
                    }
                });
            }
        });
    }
}