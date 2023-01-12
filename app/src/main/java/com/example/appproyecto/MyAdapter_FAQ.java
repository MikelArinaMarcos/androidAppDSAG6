package com.example.appproyecto;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

public class MyAdapter_FAQ extends RecyclerView.Adapter<MyAdapter_FAQ.ViewHolder>{
    private  AppCompatActivity activity;
    private List<FAQ> values;


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

    public void setData(List<FAQ> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, FAQ item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter_FAQ(AppCompatActivity activity){
        this.activity = activity;
        values = new ArrayList<>();}
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter_FAQ(List<FAQ> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter_FAQ.ViewHolder onCreateViewHolder(ViewGroup parent,
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
        FAQ faq = values.get(position);
        final String pregunta =faq.getPregunta();
        holder.txtHeader.setText(pregunta);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove(position);
                for (int i = 0; i < values.size(); i++){
                    Log.d("Pregunta" + i,values.get(i).getPregunta());
                }
            }
        });

        holder.txtFooter.setText(faq.getRespuesta()); //"Descripcion: " + o.getDescripcion()

        holder.icon.setImageResource(R.drawable.icono_arma2);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
