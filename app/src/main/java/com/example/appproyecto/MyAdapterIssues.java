package com.example.appproyecto;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appproyecto.modelo.Issue;
import com.example.appproyecto.modelo.Objeto;
import com.example.appproyecto.modelo.User;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterIssues extends RecyclerView.Adapter<MyAdapterIssues.ViewHolder>{
    private List<Issue> values;
    private AppCompatActivity activity;

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

    public void setData(List<Issue> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public MyAdapterIssues(AppCompatActivity activity){
        this.activity = activity;
        values = new ArrayList<>();}
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterIssues(List<Issue> myDataset) {
        values = myDataset;
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
    public void onBindViewHolder(MyAdapterIssues.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        Issue u = values.get(position);
        final String name = u.getInformer();
        holder.txtHeader.setText("Usuario: " + name);

        holder.txtFooter.setText(u.getMessage()); //"Descripcion: " + o.getDescripcion()

        //holder.icon.setImageResource(R.drawable.icono_arma2);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }
}
