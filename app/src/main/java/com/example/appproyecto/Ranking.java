package com.example.appproyecto;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appproyecto.modelo.Swagger;
import com.example.appproyecto.modelo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ranking extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapterRanking adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private List<User> listausuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        listausuarios = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapterRanking(this);
        recyclerView.setAdapter(adapter);

        doApiCall();

        // Manage swipe on items
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //adapter.remove(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void doApiCall() { //final SwipeRefreshLayout mySwipeRefreshLayout
        Swagger swagger = Swagger.retrofit.create(Swagger.class);
        Call<List<User>> call = swagger.Usuarios();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                // set the results to the adapter
                //adapter.setData2(response.body());
                Log.d("Usuarios",response.body().toString());
                listausuarios = response.body();
                for (int i = 0; i < listausuarios.size(); i++){
                    Log.d("NombreUsuario" + i,listausuarios.get(i).getName());
                }
                for (int i = 0; i < listausuarios.size(); i++){
                    Log.d("xp" + i,String.valueOf(listausuarios.get(i).getXp()));
                }

                //Ordeno los ususarios en funcion de su xp de mayor a menor
                Collections.sort(listausuarios, new Comparator<User>() {
                    @Override
                    public int compare(User p1, User p2) {
                        return new Integer(p2.getXp()).compareTo(new Integer(p1.getXp()));
                    }
                });
                //Meto en el adapter la lista ordenada tal y como la queriamos
                adapter.setData2(listausuarios);

                for (int i = 0; i < listausuarios.size(); i++){
                    Log.d("NombreUsuarioRanking" + i,listausuarios.get(i).getName());
                }
                for (int i = 0; i < listausuarios.size(); i++){
                    Log.d("xpRanking" + i,String.valueOf(listausuarios.get(i).getXp()));
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                String msg = "Error in retrofit: "+t.toString();

            }
        });
    }
}