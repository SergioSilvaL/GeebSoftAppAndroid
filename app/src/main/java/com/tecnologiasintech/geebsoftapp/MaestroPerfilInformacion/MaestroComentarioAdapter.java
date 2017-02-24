package com.tecnologiasintech.geebsoftapp.MaestroPerfilInformacion;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.geebsoftapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 1/22/2017.
 */

public class MaestroComentarioAdapter extends RecyclerView.Adapter<MaestroComentarioAdapter.ViewHolder> {

    private List<MaestroComentario> mMaestroComentarios;
    private Callback mCallback;
    private DatabaseReference mMaestroComentarioRef;
    private Context context;

    public MaestroComentarioAdapter(Callback callback, String maestroNombreRef, Context context){
        mCallback = callback;
        mMaestroComentarios = new ArrayList<>();
        this.context =context;


        mMaestroComentarioRef = FirebaseDatabase.getInstance().getReference()
                .child("Maestros").child(maestroNombreRef).child("maestroComentarios");
        mMaestroComentarioRef.addChildEventListener(new ComentarioChildEventListener());
    }

    class ComentarioChildEventListener implements ChildEventListener{

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            MaestroComentario comenterio  = dataSnapshot.getValue(MaestroComentario.class);
            comenterio.setKey(dataSnapshot.getKey());
            mMaestroComentarios.add(0,comenterio);
            notifyDataSetChanged();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comentario,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MaestroComentario maestroComentario = mMaestroComentarios.get(position);
        holder.mUsuarioComentarioTextView.setText(maestroComentario.getUsuarioComentario());
        holder.mUsuarioNombreTextView.setText(maestroComentario.getUsuarioNombre());
        holder.mUsuarioFechaPublicacionTextView.setText(maestroComentario.getUsuarioFechaPublicacion());
        //Set Image View from URI
//        if(!maestroComentario.getUsuarioFotoPerfil().equalsIgnoreCase("")||
//                maestroComentario.getUsuarioFotoPerfil()!=null)
        Glide.with(context).load(maestroComentario.getUsuarioFotoPerfil()).into(holder.mUsuarioPhotoImgView);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCallback.onEdit(maestroComentario);
//            }
//        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                remove(mMaestroComentarios.get(position));
//                notifyDataSetChanged();
//                return true;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mMaestroComentarios.size();
    }

//    public void remove(MaestroComentario maestroComentario){
//        mMaestroComentarios.remove(maestroComentario);
//        notifyDataSetChanged();
//    }

    public void add(MaestroComentario maestroComentario){
        mMaestroComentarioRef.push().setValue(maestroComentario);
    }

    public void update(MaestroComentario maestroComentario , String newComentario){
        maestroComentario.setUsuarioComentario(newComentario);
        notifyDataSetChanged();
    }

    public interface Callback{
        public void onEdit(MaestroComentario maestroComentario);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mUsuarioNombreTextView;
        private TextView mUsuarioComentarioTextView;
        private TextView mUsuarioFechaPublicacionTextView;
        private ImageView mUsuarioPhotoImgView;

        public ViewHolder(View itemView) {
            super(itemView);
            mUsuarioComentarioTextView = (TextView) itemView.findViewById(R.id.txtViewusuarioComentario);
            mUsuarioNombreTextView = (TextView) itemView.findViewById(R.id.txtViewusuarioNombre);
            mUsuarioFechaPublicacionTextView= (TextView) itemView.findViewById(R.id.txtViewusuarioFechaPublicacion);
            mUsuarioPhotoImgView = (ImageView) itemView.findViewById(R.id.imageViewusuarioFotoPerfill);

        }
    }
}
