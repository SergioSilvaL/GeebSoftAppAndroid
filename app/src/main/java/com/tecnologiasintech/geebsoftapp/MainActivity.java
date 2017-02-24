package com.tecnologiasintech.geebsoftapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.geebsoftapp.LoginSystem.LoginActivity;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Fragments.FirebaseComentarioHelper;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.MaestroPerfilActivity;
import com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.ComentarioAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static com.tecnologiasintech.geebsoftapp.R.id.imageViewUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private MaestroAdapter mAdapter;
    private Maestro maestro;



    Toolbar toolbar;

    //UI Elements
    TextView mUserInformation;
    ImageView mUserProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mAdapter = new MaestroAdapter(this);
        RecyclerView view = (RecyclerView) findViewById(R.id.recyclerView_maestro);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setHasFixedSize(true);
        view.setAdapter(mAdapter);

        mUserProfilePicture = (ImageView) findViewById(R.id.imageViewUser);

        Glide.with(this).load(user.getPhotoUrl()).into(mUserProfilePicture);

        //Log.v(TAG,user.getPhotoUrl().toString());


        mUserInformation = (TextView) findViewById(R.id.txtViewUserName);
        String displayName = "Estudiante ITH";

        if(user.getDisplayName()!=null){
            displayName = user.getDisplayName();
        }

        mUserInformation.setText(displayName+" !");





        //Todo Diseno de los botones Google and Facebook

        //Todo Upload App



    }



    private void signOut(){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LoginActivity.class));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                signOut();
                return true;

//            case R.id.action_favorite:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the searchBar
        getMenuInflater().inflate(R.menu.menu_items,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);


        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Perform final Search
                Log.v(TAG,"Query text Sumbmit");

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Text has Changed, apply filtering?
                Log.v(TAG,"OnQueryTextChange: "+ newText);


                newText = newText.toLowerCase();
                ArrayList<Maestro> newList = new ArrayList<>();


                for (Maestro maestro : MaestroAdapter.filterMaestros){

                    Log.v(TAG,"Profesor: "+ maestro.getMaestroNombre());


                    String name = maestro.getMaestroNombre().toLowerCase();

                    if(name.contains(newText)){

                        newList.add(maestro);

                    }
                }

                mAdapter.setFilter(newList);



                return false;
            }
        });

        return true;
    }
}
