package com.tecnologiasintech.geebsoftapp.MaestroPerfilInformacion;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tecnologiasintech.geebsoftapp.Datepost;
import com.tecnologiasintech.geebsoftapp.Maestro;
import com.tecnologiasintech.geebsoftapp.MaestroPerfilInformacion.MaestroComentario;
import com.tecnologiasintech.geebsoftapp.MaestroPerfilInformacion.MaestroComentarioAdapter;
import com.tecnologiasintech.geebsoftapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MaestroPerfilInformacionActivity extends AppCompatActivity implements MaestroComentarioAdapter.Callback {

    private MaestroComentarioAdapter mAdapter;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String TAG = MaestroPerfilInformacionActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestro_perfil_informacion);
        String MaestroNombreRef = getIntent().getStringExtra("MaestroNombre");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(MaestroNombreRef);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEditDialog(null);
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String MaestroNombreRef = getIntent().getStringExtra("MaestroNombre");

        mAdapter =  new MaestroComentarioAdapter(this,MaestroNombreRef,this);
        RecyclerView view = (RecyclerView) findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setHasFixedSize(true);
        view.setAdapter(mAdapter);
    }



    private void showAddEditDialog(final MaestroComentario maestroComentario){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir Comentario");
        View view = getLayoutInflater().inflate(R.layout.dialog_comment,null,false);
        builder.setView(view);
        final EditText comentarioEditText = (EditText) view.findViewById(R.id.dialog_add_comentario_text);


        comentarioEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200){
        }});



        if (maestroComentario !=null){
            comentarioEditText.setText(maestroComentario.getUsuarioComentario());

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String comentario = comentarioEditText.getText().toString();
                    mAdapter.update(maestroComentario,comentario);
                }
            };
        }

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (maestroComentario==null){
                    String comentario = comentarioEditText.getText().toString();

                    Datepost date =  new Datepost();
                    String mydate = date.getDatePost();

                    String nombre;
                    String photoLink;

                    Log.v(TAG,user.getDisplayName()+user.getPhotoUrl());




                    if(user.getDisplayName()==null|| user.getPhotoUrl()==null){
                        Log.v(TAG,"Display name is null");
                        nombre = "Estudiante ITH";
                        photoLink = "";
                    }else{
                        nombre = user.getDisplayName();
                        photoLink = user.getPhotoUrl().toString();
                    }

                    //Todo more than 1 caracter



                        if(comentario.length()!=0)
                    mAdapter.add(new MaestroComentario(nombre,comentario,mydate,photoLink));
                }
            }
        });
        builder.setNegativeButton(R.string.dialog_cancelar,null);

        builder.create().show();
    }



    @Override
    public void onEdit(MaestroComentario maestroComentario) {
        showAddEditDialog(maestroComentario);
    }
}
