package com.example.camposdegolf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Sirve de enlace entre un conjunto de datos y una vista mostrada en un 'RecyclerView'.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder>{
    private List<Encapsulador> entradas;

public Adaptador(List<Encapsulador> entradas) {
        this.entradas = entradas;
        }

/**
 * Crea la vista del 'RecyclerView' a partir del arhivo XML diseñado manualmente.
 * @param parent 'ViewGroup' al que se agregará la vista cuando se vincule a una posición del adaptador.
 *               En este caso, se añade al 'ViewGroup' del padre.
 * @param viewType Tipo de vista de la nueva vista.
 * @return Un 'ViewHolder' que contiene una vita del tipo indicado.
 */
@NonNull
@Override
public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        MyHolder mvh = new MyHolder(v);
        return mvh;
        }

/**
 * Permite mostrar los datos del 'RecyclerView' en la posición indicada.
 * @param holder ViewHolder que se debe actualizar para representar el contenido de los items de la
 *               posición indicada en el conjunto de datos.
 * @param position Posición del item dentro del conjunto de datos del adaptador.
 */
@Override
public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.id.setText(entradas.get(position).getid());
        holder.nombreCampo.setText(entradas.get(position).getnombreCampo());
        }

/**
 * Devuelve el número total de items del conjunto de datos que contiene el adaptador.
 * @return Número total de items del adatador.
 */
@Override
public int getItemCount() {
        return entradas.size();
        }

/**
 * Describe la vista de un item y metadatos sobre su posición dentro del 'RecyclerView'.
 */
public static class MyHolder extends RecyclerView.ViewHolder{
    private TextView id;
    private TextView nombreCampo;

    public MyHolder(View vista) {
        super(vista);
        id = (TextView) vista.findViewById(R.id.txtid);
        nombreCampo = (TextView) vista.findViewById(R.id.txtNombre);
    }
}
}
