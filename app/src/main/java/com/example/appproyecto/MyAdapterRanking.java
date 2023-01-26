package com.example.appproyecto;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appproyecto.modelo.Objeto;
import com.example.appproyecto.modelo.User;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterRanking extends RecyclerView.Adapter<MyAdapterRanking.ViewHolder>{
    private AppCompatActivity activity;
    private List<Objeto> values;
    private List<User> values2;


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
        //notifyDataSetChanged();
    }

    public void setData2(List<User> myDataset) {
        values2 = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Objeto item) {
        values.add(position, item);
        //notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        //notifyItemRemoved(position);
    }

    public MyAdapterRanking(AppCompatActivity activity){
        this.activity = activity;
        values2 = new ArrayList<>();}
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterRanking(List<User> myDataset) {
        values2 = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
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
        for (int i = 0; i < values2.size(); i++){
            Log.d("NombreUsuarioLista" + i,values2.get(i).getName());
        }
        for (int i = 0; i < values2.size(); i++){
            Log.d("xpLista" + i,String.valueOf(values2.get(i).getXp()));
        }

        User u = values2.get(position);
        final String name = u.getName();
        holder.txtHeader.setText("Usuario: " + name);

        holder.txtFooter.setText("Experiencia: " + u.getXp()); //"Descripcion: " + o.getDescripcion()

        //holder.icon.setImageResource(R.drawable.icono_arma2);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values2.size();
    }
}
