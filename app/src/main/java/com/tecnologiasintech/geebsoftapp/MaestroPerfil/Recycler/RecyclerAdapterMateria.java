package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.DataModel.MaestroMateria;
import com.tecnologiasintech.geebsoftapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sergio on 1/17/2017.
 */

public class RecyclerAdapterMateria
        extends RecyclerView.Adapter<RecyclerAdapterMateria.ViewHolder> {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private final List<MaestroMateria> mMaestroMateria;
    private Random mRandom;


    public RecyclerAdapterMateria(Context context, RecyclerView recyclerView) {
        mMaestroMateria = new ArrayList<>();//mPasswords = new ArrayList<>();
        mContext = context;//mInflator = LayoutInflator.from(context);
        mRandom = new Random();
        mRecyclerView = recyclerView;


        //Dummy Content
        for (int i = 0; i < 7; i++) {
            mMaestroMateria.add(new MaestroMateria(
                    getRandomName(),
                    getRandomCarrera()));
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_materia, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MaestroMateria currentMateria = mMaestroMateria.get(position);

        holder.bindToView(currentMateria);
    }


    @Override
    public int getItemCount() {
        return mMaestroMateria.size();
    }

    private String getRandomName() {
        String[] names = new String[]{
                "Calculo Integral ",
                "Metodos Numericos",
                "Algebra Lineal",
                "Programacion Orientada a Objectos",
                "Conmutacion y enrutamiento",
                "Lenguas y Automatas I",
                "Redes"
                ,
        };
        return names[mRandom.nextInt(names.length)];
    }

    private String getRandomCarrera() {
        String[] names = new String[]{
                "Ing. Biomedica", "Ing. Eléctrica", "Ing. Electronica"

        };
        return names[mRandom.nextInt(names.length)];
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView maestronameTextView;
        private TextView maestrocarreraTextView;


        public ViewHolder(final View itemView) {
            super(itemView);

            maestronameTextView = (TextView) itemView.findViewById(R.id.textViewMateriaCarrera);
            maestrocarreraTextView = (TextView) itemView.findViewById(R.id.textViewMateriaNombre);
        }


        public void bindToView(MaestroMateria materia) {
            maestronameTextView.setText(materia.getMateria_Carrera());
            maestrocarreraTextView.setText(materia.getMateria_Nombre());

        }
    }
}