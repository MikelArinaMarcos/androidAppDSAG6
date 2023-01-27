package com.example.appproyecto.modelo;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.*;

public interface Swagger {

    String URL = "http://10.0.2.2:8080/dsaApp/";//
    //String URL = "http://147.83.7.208:8080/dsaApp/";
    //String URL = "http://10.192.31.128:8080/dsaApp/";

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("juego/FAQs")
    Call<List<FAQ>> Preguntas();

    @POST("juego/login")
    Call<User> Login(@Body UserLogin ul);

    @POST("juego/users/register")
    Call<User> Register(@Body User ur);

    @GET("juego/objetos")
    Call<List<Objeto>> Objetos();

    @POST("juego/user/buy/{idObjeto}/{idUsuario}")
    Call<Void> ComprarObjeto(@Path("idUsuario")int idUsuario, @Path("idObjeto")int idObjeto);

    @GET("juego/users")
    Call<List<User>> Usuarios();

    @POST("issue")
    Call<Issue> Issue(Issue issue);

    @POST("juego/Issue/add")
    Call<Issue> AddIssue(Issue issue);

    @GET("juego/Issues")
    Call<List<Issue>> Issues();

}
