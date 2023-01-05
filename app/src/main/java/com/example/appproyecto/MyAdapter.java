package com.example.appproyecto;


import java.util.ArrayList;
import java.util.List;

//import android.support.v7.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appproyecto.modelo.Objeto;
import com.example.appproyecto.modelo.Swagger;
import com.example.appproyecto.modelo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Objeto> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            icon = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void setData(List<Objeto> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Objeto item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(){values = new ArrayList<>();}
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Objeto> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Objeto o = values.get(position);
        final String name = o.getNombre();
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove(position);
                Log.d("NombreObjeto1",values.get(0).getNombre());
                Log.d("NombreObjeto2",values.get(1).getNombre());
                Log.d("NombreObjeto3",values.get(2).getNombre());
                Log.d("ID1",String.valueOf(values.get(0).getIdObjeto()));
                Log.d("ID2",String.valueOf(values.get(1).getIdObjeto()));
                Log.d("ID3",String.valueOf(values.get(2).getIdObjeto()));
                /*
                Swagger swagger = Swagger.retrofit.create(Swagger.class);
                Call call = swagger.ComprarObjeto(45,2);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.d("ComprarObjeto","Objeto Compradissimo");
                    }
                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.d("ComprarObjeto","Objeto NOOOOOOOOOOO Comprado");
                    }


                });
                */
            }
        });

        holder.txtFooter.setText(o.getDescripcion()); //"Descripcion: " + o.getDescripcion()

        holder.icon.setImageResource(R.drawable.icono_arma2);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}