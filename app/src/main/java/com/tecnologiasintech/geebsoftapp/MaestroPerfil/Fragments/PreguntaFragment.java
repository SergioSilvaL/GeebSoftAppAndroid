package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.RecyclerAdapterPregunta;
import com.tecnologiasintech.geebsoftapp.R;

/**
 * Created by Sergio on 1/17/2017.
 */

public class PreguntaFragment extends Fragment {


    public PreguntaFragment() {
        // Required empty public constructor
    }

    private RecyclerAdapterPregunta mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pregunta, container, false);

        //Capture the recyclerView

        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.recyclerViewPregunta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        //Create the Adapter
        mAdapter = new RecyclerAdapterPregunta(this.getContext(), recyclerView);

        //Binding
        recyclerView.setAdapter(mAdapter);


        return view;

    }
}
