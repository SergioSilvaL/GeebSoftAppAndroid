package com.tecnologiasintech.geebsoftapp.MaestroPerfil;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Fragments.ComentarioFragment;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Fragments.MateriaFragment;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Fragments.PreguntaFragment;

/**
 * Created by Sergio on 1/17/2017.
 */

public class Perfil_Maestro_Tab_Adapter extends FragmentPagerAdapter{

    int numeroDeSecciones;
    private Context context;

    public Perfil_Maestro_Tab_Adapter(FragmentManager fm, int numeroDeSeciones, Context context) {

        super(fm);
        this.numeroDeSecciones= numeroDeSeciones;
        this.context = context;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        // recibimos la posición por parámetro y en función de ella devolvemos el Fragment correspondiente a esa sección.
        switch (position) {

            case 0: // siempre empieza desde 0
                return new ComentarioFragment();

//            case 1:
//                return new PreguntaFragment();

            case 1:
                return  new MateriaFragment();

            // si la posición recibida no se corresponde a ninguna sección
            default:
                return null;
        }
    }




    @Override
    public CharSequence getPageTitle(int position) {

        // recibimos la posición por parámetro y en función de ella devolvemos el titulo correspondiente.
        switch (position) {

            case 0: // siempre empieza desde 0, la primera Tab es 0
                return "Comentarios";
//            case 1:
//                return "Preguntas";

            case 1:
                return "Materias";

            // si la posición recibida no se corresponde a ninguna sección
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numeroDeSecciones;
    }
}
