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
    //Utilizamos este Usuario para recoger la respuesta del Swager y poder trabajar con un Usuario
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicializamos el usuario
        usuario = new User();

        configureLoginButton();
    }

    private void configureLoginButton(){
        //Inicializamos y le damos los valores al Button y a los EditText
        Button LoginButton = (Button) findViewById(R.id.Login_button);
        EditText mail = (EditText) findViewById(R.id.textMail);
        EditText password = (EditText) findViewById(R.id.textPassword);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inicializamos y llamamos al swagger para poder hacer las consultas pertinentes
                Swagger swagger = Swagger.retrofit.create(Swagger.class);
                //Assignamos valores a una classe UserLogin
                UserLogin ul = new UserLogin(mail.getText().toString(),password.getText().toString());
                Call<User> call = swagger.Login(ul);
                call.enqueue(new Callback<User>() {
                    @Override
                    //Si obtenemos una respuesta
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("Respuesta_1",response.toString());
                        Log.d("Respuesta_2",response.body().toString());
                        //Si la respuesta es correcta, en nuestro caso recibimos un 201
                        if (response.isSuccessful()){
                            //rellenamos el valor de usuario con la respuesta obtenida
                            usuario = response.body();
                            //Como la respuesta es correcta podemos ir a la actividad principal
                            startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                            //Inicializamos unas SharedPreferences para poder rellenar sus valores
                            SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("name", usuario.getName());
                            editor.putInt("idUsuario", usuario.getIdUsuario());
                            editor.putInt("dinero", usuario.getDinero());
                            editor.putString("username",usuario.getUsername());
                            editor.putString("mail",usuario.getMail());
                            editor.putInt("xp", usuario.getXp());
                            editor.commit();
                        }
                        else {
                            Snackbar mySnackbar = Snackbar.make(view, "Inicio de Sesion Incorrecto", BaseTransientBottomBar.LENGTH_SHORT);
                            mySnackbar.show();
                        }
                    }
                    //Si no obtenemos una respuesta
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