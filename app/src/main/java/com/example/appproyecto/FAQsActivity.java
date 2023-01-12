package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appproyecto.modelo.FAQ;
import com.example.appproyecto.modelo.Objeto;
import com.example.appproyecto.modelo.Swagger;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQsActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private MyAdapter_FAQ adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private List<FAQ> preguntas;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        preguntas = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter_FAQ(this);
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
                        adapter.remove(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void doApiCall() { //final SwipeRefreshLayout mySwipeRefreshLayout
        progressBar.setVisibility(View.VISIBLE);

        Swagger swagger = Swagger.retrofit.create(Swagger.class);
        Call<List<FAQ>> call = swagger.Preguntas();

        call.enqueue(new Callback<List<FAQ>>() {
            @Override
            public void onResponse(Call<List<FAQ>> call, Response<List<FAQ>> response) {
                // set the results to the adapter
                adapter.setData(response.body());
                Log.d("FAQs",response.body().toString());
                preguntas = response.body();
                for (int i = 0; i < preguntas.size(); i++){
                    Log.d("Preguntas" + i,preguntas.get(i).getPregunta());
                }
                for (int i = 0; i < preguntas.size(); i++){
                    Log.d("Respuestas" + i,String.valueOf(preguntas.get(i).getRespuesta()));
                }
            }
            @Override
            public void onFailure(Call<List<FAQ>> call, Throwable t) {
                String msg = "Error in retrofit: "+t.toString();
                Log.d(TAG,msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            }
        });
        progressBar.setVisibility(View.GONE);

    }

}
