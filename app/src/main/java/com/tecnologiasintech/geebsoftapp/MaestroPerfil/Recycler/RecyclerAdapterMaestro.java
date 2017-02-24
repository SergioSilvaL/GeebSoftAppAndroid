package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.DataModel.MaestroMateria;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.DataModel.maestroComentarios;
import com.tecnologiasintech.geebsoftapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sergio on 1/20/2017.
 */

public class RecyclerAdapterMaestro
        extends RecyclerView.Adapter<RecyclerAdapterMaestro.ViewHolder> {

    Context c;
    ArrayList<maestroComentarios> maestroComentarioses;


    public RecyclerAdapterMaestro(Context c, ArrayList<maestroComentarios> maestroComentarioses) {
        this.maestroComentarioses=maestroComentarioses;
        this.c=c;
    }


    @Override
    public RecyclerAdapterMaestro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_comentario, parent, false);

        return new RecyclerAdapterMaestro.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(RecyclerAdapterMaestro.ViewHolder holder, int position) {

        maestroComentarios currentComentario = maestroComentarioses.get(position);

        holder.bindToView(currentComentario);
    }


    @Override
    public int getItemCount() {
        return maestroComentarioses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView usuarioComentarioTxt,usuarioFechaPublicacionTxt,usuarioNombreTxt;



        public ViewHolder(final View itemView) {
            super(itemView);

            usuarioComentarioTxt= (TextView) itemView.findViewById(R.id.txtViewusuarioComentario);
            usuarioFechaPublicacionTxt= (TextView) itemView.findViewById(R.id.txtViewusuarioFechaPublicacion);
            usuarioNombreTxt= (TextView) itemView.findViewById(R.id.txtViewusuarioNombre);

        }


        public void bindToView(maestroComentarios comentario) {

            usuarioComentarioTxt.setText(comentario.getUsuarioComentario());
            usuarioFechaPublicacionTxt.setText(comentario.getUsuarioFechaPublicacion());
            usuarioNombreTxt.setText(comentario.getUsuarioNombre());

        }
    }
}
