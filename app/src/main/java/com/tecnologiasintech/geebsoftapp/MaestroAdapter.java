package com.tecnologiasintech.geebsoftapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.MaestroPerfilActivity;
import com.tecnologiasintech.geebsoftapp.MaestroPerfilInformacion.MaestroComentario;
import com.tecnologiasintech.geebsoftapp.MaestroPerfilInformacion.MaestroPerfilInformacionActivity;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 1/22/2017.
 */

public class MaestroAdapter extends RecyclerView.Adapter<MaestroAdapter.ViewHolder>{

    private List<Maestro> mMaestro;

    private DatabaseReference mMaestroRef;
    private Context context;

    public static List<Maestro> filterMaestros;


    Intent intent;

    public MaestroAdapter(Context c){

        context = c;
        mMaestro = new ArrayList<>();
        mMaestroRef = FirebaseDatabase.getInstance().getReference();
        mMaestroRef.addChildEventListener(new MaestroChildEventListener());

        //Set for Filtering
        filterMaestros = mMaestro;

    }

    public void setFilter(ArrayList<Maestro> newList){
        mMaestro =  new ArrayList<>();
        mMaestro.addAll(newList);
        notifyDataSetChanged();
    }

    class MaestroChildEventListener implements ChildEventListener{

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//            MaestroComentario comenterio  = dataSnapshot.getValue(MaestroComentario.class);
//            comenterio.setKey(dataSnapshot.getKey());
//            mMaestroComentarios.add(0,comenterio);
//            notifyDataSetChanged();
            fetchData(dataSnapshot);
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

        private void fetchData(DataSnapshot dataSnapshot){
            mMaestro.clear();
            for (DataSnapshot mMaestroRef: dataSnapshot.getChildren()){
                Maestro maestro = mMaestroRef.getValue(Maestro.class);
                mMaestro.add(maestro);

            }
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_maestro,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Maestro maestro =  mMaestro.get(position);
        holder.nameTxt.setText(maestro.getMaestroNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent =  new Intent(context, MaestroPerfilInformacionActivity.class);
                intent.putExtra("MaestroNombre",maestro.getMaestroNombre());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mMaestro.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);

        }
    }
}
