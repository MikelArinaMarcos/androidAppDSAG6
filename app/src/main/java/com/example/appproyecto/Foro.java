package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.appproyecto.modelo.Issue;
import com.example.appproyecto.modelo.Swagger;
import com.example.appproyecto.modelo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Foro extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapterIssues adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Issue> listaissues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        listaissues = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapterIssues(this);
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
        Call<List<Issue>> call = swagger.Issues();

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                // set the results to the adapter
                //adapter.setData2(response.body());
                Log.d("Usuarios",response.body().toString());
                listaissues = response.body();

                //Meto en el adapter la lista ordenada tal y como la queriamos
                adapter.setData(listaissues);

            }
            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                String msg = "Error in retrofit: "+t.toString();

            }
        });
    }
}