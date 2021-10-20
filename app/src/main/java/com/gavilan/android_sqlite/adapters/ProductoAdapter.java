package com.gavilan.android_sqlite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gavilan.android_sqlite.R;
import com.gavilan.android_sqlite.models.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {
    ArrayList<Producto> productos;
    public ProductoAdapter(ArrayList<Producto> productos){
        this.productos = productos;
    }
    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        holder.cargarProducto(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCat;
        TextView txtNombre, txtPrecio, txtStock;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.imgCat);
            txtNombre = itemView.findViewById(R.id.txtNom);
            txtPrecio = itemView.findViewById(R.id.txtPre);
            txtStock = itemView.findViewById(R.id.txtSt);
        }

        void cargarProducto(Producto p){
            txtNombre.setText(p.getNombre());
            txtPrecio.setText("$"+p.getPrecio());
            txtStock.setText("Quedan "+p.getStock());
        }
    }
}
