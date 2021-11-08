package com.example.ejercicio08_bbdd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder>{
    private List<Encapsulador> entradas;

    public Adaptador(List<Encapsulador> entradas) {
        this.entradas = entradas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        MyHolder mvh = new MyHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.grupo.setText(entradas.get(position).getGrupo());
        holder.disco.setText(entradas.get(position).getDisco());
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        private TextView grupo;
        private TextView disco;

        public MyHolder(View vista) {
            super(vista);
            grupo = (TextView) vista.findViewById(R.id.listaGrupo);
            disco = (TextView) vista.findViewById(R.id.listaDisco);
        }
    }
}
