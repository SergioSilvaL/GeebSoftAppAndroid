package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.DataModel.MaestroPregunta;
import com.tecnologiasintech.geebsoftapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sergio on 1/17/2017.
 */


public class RecyclerAdapterPregunta
        extends RecyclerView.Adapter<RecyclerAdapterPregunta.ViewHolder> {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private final List<MaestroPregunta> mMaestroPregunta;
    private Random mRandom;


    public RecyclerAdapterPregunta(Context context, RecyclerView recyclerView) {
        mMaestroPregunta = new ArrayList<>();//mPasswords = new ArrayList<>();
        mContext = context;//mInflator = LayoutInflator.from(context);
        mRandom = new Random();
        mRecyclerView = recyclerView;


        //Dummy Content
        for (int i = 0; i < 7; i++) {
            mMaestroPregunta.add(new MaestroPregunta(
                    getRandomName(),
                    getRandomComment(),
                    getRandomRating()
            ));
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_pregunta, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MaestroPregunta currentPregunta = mMaestroPregunta.get(position);

        holder.bindToView(currentPregunta);
    }


    @Override
    public int getItemCount() {
        return mMaestroPregunta.size();
    }

    private String getRandomName() {
        String[] names = new String[]{
                "Sergio Silva Leon",
                "Esto se va descontroloar",
                "Ay mi dios",
                "Programacion Orientada a Objectos Programacion Orientada a Objectos",
                "",
                "45",
                "Redes"
                ,
        };
        return names[mRandom.nextInt(names.length)];
    }
    private String getRandomComment() {
        String[] names = new String[]{
                "And i looked at my life , i asked my self this question",
                "Esto se va descontroloar",
                "Algún interesado en realizar sus practicas en TE Connectivity Empalme? Se busca alumno de Sistemas Computacionales.\n" +
                        "TE connectivity está interesado en la formación de futuros profesionales, brindándoles la oportunidad de aplicar " +
                        "los conocimientos teóricos adquiridos durante su carrera académica al mundo laboral, ofreciéndole al alumno áreas practicas " +
                        "acordes a los intereses, conocimientos y habilidades de los mismos, además de flexibilidad de horario, ayuda de transportación y " +
                        "apoyo económico.;",
                "Programacion Orientada a Objectos Programacion Orientada a Objectos",
                "",
                "Compañeros de minería (federico) la maestra quiere saber donde serán las exposiciones de hoy! Gracias ",
                "Buen día:\n" +
                        "Si hay alguien interesado en tomar el diplomado de .NET para titularse hay una vacante con oportunidad de pago a un mes, el curso ya comenzo este lunes... envía inbox para información =)"
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
    public int getRandomRating() {

        return mRandom.nextInt(32);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tituloTextView;
        private TextView cuerpoTextView;
        private TextView ratingTextView;


        public ViewHolder(final View itemView) {
            super(itemView);

            tituloTextView = (TextView) itemView.findViewById(R.id.textView_pregunta_titulo);
            cuerpoTextView = (TextView) itemView.findViewById(R.id.textView_pregunta_cuerpo);
            ratingTextView = (TextView) itemView.findViewById(R.id.textView_Pregunta_numeroderespues);

//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    itemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            Intent intent  = new Intent(mContext, RespuestasActivity.class);
//
//                            intent.putExtra("Profesor_asunto",mProfesorPreguntas.get
//                                    (getAdapterPosition()).getAsunto());
//                            intent.putExtra("Profesor_Cuerpo",mProfesorPreguntas.get
//                                    (getAdapterPosition()).getCuerpo());
//                            mContext.startActivity(intent);
//                        }
//                    });
//                }
//            });

        }


        public void bindToView(MaestroPregunta pregunta) {
            tituloTextView.setText(pregunta.getAsunto());
            cuerpoTextView.setText(pregunta.getCuerpo());
            ratingTextView.setText(String.valueOf(pregunta.getNumero_respuestas()));

        }
    }
}