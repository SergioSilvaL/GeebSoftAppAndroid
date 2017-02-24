package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnologiasintech.geebsoftapp.ItemClickListener;
import com.tecnologiasintech.geebsoftapp.R;

/**
 * Created by Sergio on 1/18/2017.
 */

public class MyViewHolderComentario extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView usuarioComentarioTxt,usuarioFechaPublicacionTxt,usuarioNombreTxt;
    //,usuarioLikeTxt
    //ImageView usuarioFotoPerfilImg;
    ItemClickListener itemClickListener;

    public MyViewHolderComentario(View itemView) {
        super(itemView);

        usuarioComentarioTxt= (TextView) itemView.findViewById(R.id.txtViewusuarioComentario);
        usuarioFechaPublicacionTxt= (TextView) itemView.findViewById(R.id.txtViewusuarioFechaPublicacion);
        //usuarioFotoPerfilTxt= (TextView) itemView.findViewById(R.id.imageViewusuarioFotoPerfill);
        //usuarioLikeTxt= (TextView) itemView.findViewById(R.id.txtViewusuarioLike);
        usuarioNombreTxt= (TextView) itemView.findViewById(R.id.txtViewusuarioNombre);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
