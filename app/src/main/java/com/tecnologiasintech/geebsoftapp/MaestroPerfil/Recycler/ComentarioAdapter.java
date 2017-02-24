package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tecnologiasintech.geebsoftapp.ItemClickListener;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.DataModel.maestroComentarios;
import com.tecnologiasintech.geebsoftapp.R;

import java.util.ArrayList;

/**
 * Created by Sergio on 1/18/2017.
 */

public class ComentarioAdapter extends RecyclerView.Adapter<MyViewHolderComentario>{

    Context c;
    ArrayList<maestroComentarios> maestroComentarioses;
    private static final String TAG = ComentarioAdapter.class.getSimpleName();

    public ComentarioAdapter(Context c ,ArrayList<maestroComentarios> maestroComentarioses){
        this.c=c;
        this.maestroComentarioses=maestroComentarioses;
    }


    @Override
    public MyViewHolderComentario onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.view_comentario,parent,false);
        return new MyViewHolderComentario(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderComentario holder, int position) {
        final maestroComentarios m = maestroComentarioses.get(position);


        holder.usuarioComentarioTxt.setText(m.getUsuarioComentario());
        holder.usuarioFechaPublicacionTxt.setText(m.getUsuarioFechaPublicacion());
//        holder.usuarioLikeTxt.setText(m.getUsuarioLike());
        holder.usuarioNombreTxt.setText(m.getUsuarioNombre());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Log.v(TAG, m.getUsuarioNombre());
            }
        });
    }

    @Override
    public int getItemCount() {
        return maestroComentarioses.size();
    }
}
