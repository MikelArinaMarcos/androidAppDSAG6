package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appproyecto.modelo.Objeto;
import com.example.appproyecto.modelo.Swagger;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tienda extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private List<Objeto> listaobjetos;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        listaobjetos = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        doApiCall(); //null

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
                        Log.d("IDObjeto",String.valueOf(viewHolder.getAdapterPosition()));
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void doApiCall() { //final SwipeRefreshLayout mySwipeRefreshLayout
        Swagger swagger = Swagger.retrofit.create(Swagger.class);
        Call<List<Objeto>> call = swagger.Objetos();

        call.enqueue(new Callback<List<Objeto>>() {
            @Override
            public void onResponse(Call<List<Objeto>> call, Response<List<Objeto>> response) {
                // set the results to the adapter
                adapter.setData(response.body());
                Log.d("Tienda",response.body().toString());
                listaobjetos = response.body();
                for (int i = 0; i < listaobjetos.size(); i++){
                    Log.d("NombreObjeto" + i,listaobjetos.get(i).getNombre());
                }
                for (int i = 0; i < listaobjetos.size(); i++){
                    Log.d("ID" + i,String.valueOf(listaobjetos.get(i).getIdObjeto()));
                }
            }
            @Override
            public void onFailure(Call<List<Objeto>> call, Throwable t) {
                String msg = "Error in retrofit: "+t.toString();
                Log.d(TAG,msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            }
        });
    }
}