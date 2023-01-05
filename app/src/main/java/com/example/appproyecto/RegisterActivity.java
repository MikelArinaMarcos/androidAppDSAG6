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
import com.example.appproyecto.modelo.User;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usuario = new User();

        configureRegisterButton();
    }

    private void configureRegisterButton(){
        Button LoginButton = (Button) findViewById(R.id.Login_button);
        EditText mail = (EditText) findViewById(R.id.Mail);
        EditText password = (EditText) findViewById(R.id.textPassword);
        EditText surname = (EditText) findViewById(R.id.Surname);
        EditText name = (EditText) findViewById(R.id.Name);
        EditText username = (EditText) findViewById(R.id.textMail);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Swagger swagger = Swagger.retrofit.create(Swagger.class);
                User ur = new User(username.getText().toString(),mail.getText().toString(),name.getText().toString(),surname.getText().toString(),password.getText().toString());
                Call call = swagger.Register(ur);//Call<User> call = swagger.Register(ur)
                call.enqueue(new Callback<User>() {//call.enqueue(new Callback<User>()
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {//Call<User> call, Response<User> response
                        Log.d("Respuesta_1",response.toString());
                        Log.d("Respuesta_2",response.body().toString());
                        if (response.isSuccessful()){
                            usuario = response.body();
                            Log.d("Nombre usuario",usuario.getName());
                            startActivity(new Intent(RegisterActivity.this, PrincipalActivity.class));
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
                            Snackbar mySnackbar = Snackbar.make(view, "Registro Incorrecto", BaseTransientBottomBar.LENGTH_SHORT);
                            mySnackbar.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {//Call<User> call, Throwable t
                        Snackbar mySnackbar = Snackbar.make(view, "No has podido Registrarte / Failure", BaseTransientBottomBar.LENGTH_SHORT);
                        Log.d("Fallada",t.toString());
                        mySnackbar.show();
                    }
                });
            }
        });
    }
}