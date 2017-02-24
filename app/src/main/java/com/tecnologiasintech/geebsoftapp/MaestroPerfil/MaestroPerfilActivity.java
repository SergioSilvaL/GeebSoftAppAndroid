package com.tecnologiasintech.geebsoftapp.MaestroPerfil;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tecnologiasintech.geebsoftapp.R;

public class MaestroPerfilActivity extends AppCompatActivity {

    private Perfil_Maestro_Tab_Adapter mAdapter_viewPagerPrincipal;
    private ViewPager mViewPager;
    private String profesor_nombre;
    private String profesor_carrera;
    private int currentPage;
    private FloatingActionButton fab;
    private LayoutInflater mInflater;
    private boolean primerSession = false;


    private TextView mProfesor_Nombre, mProfesor_Carrera;
    private RatingBar mRatingBar;


    private static final String TAG = MaestroPerfilActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestro_perfil);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        setUpTabLayoutViewPager();


        String s = getIntent().getStringExtra("Maestro_Nombre");

        Log.v(TAG,s);



    }

    private void setUpTabLayoutViewPager(){
        // Iniciamos la barra de tabs
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_Perfil);

        // Añadimos las 3 tabs de las secciones.
        // Le damos modo "fixed" para que todas las tabs tengan el mismo tamaño. También le asignamos una gravedad centrada.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        //tabLayout.addTab(tabLayout.newTab());



        //Iniciamos el ViewPager.
        mViewPager = (ViewPager) findViewById(R.id.PerfilPager);


        mAdapter_viewPagerPrincipal = new Perfil_Maestro_Tab_Adapter(
                getSupportFragmentManager(),tabLayout.getTabCount(),this
        );


        mViewPager.setAdapter(mAdapter_viewPagerPrincipal);


        /*Bandera se ocupa por el momento ya que no se detecta el
        on click listener del fab , porque el currentPage, cambia ya que
                se da swipe en pagina del la actividad*/


        if(primerSession==false){
            primerSession = true;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addComentario();
                }
            });
        }


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {



                currentPage = arg0;
                if(currentPage <1) {
                    fab.show();
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(currentPage == 0){

                                addComentario();

                            }
//                            else if (currentPage==1){
//                               addPregunta();
//                            }
//                          Snackbar.make(view, "Se presionó el FAB " + currentPage, Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show();
                        }
                    });
                }else if(currentPage==1){
                    fab.hide();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        tabLayout.setupWithViewPager(mViewPager);


    }

    private void addComentario() {

        final Dialog dialog = new AlertDialog.Builder(this)

                .setTitle("Comentario")
                .setView(R.layout.dialog_comment)
                .setNegativeButton(android.R.string.cancel,null)
                .create();
        dialog.show();

    }


}
