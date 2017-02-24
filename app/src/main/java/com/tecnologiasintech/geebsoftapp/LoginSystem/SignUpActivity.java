package com.tecnologiasintech.geebsoftapp.LoginSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tecnologiasintech.geebsoftapp.MainActivity;
import com.tecnologiasintech.geebsoftapp.R;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = " SignUpActivity";
    private EditText inputUserName, inputEmail, inputPassword, inputPasswordConfirmation;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    private String email,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Get Firebase auth Instance
        auth = FirebaseAuth.getInstance();

        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputUserName = (EditText) findViewById(R.id.SignUpUserName);
        inputEmail = (EditText) findViewById(R.id.SignUpemail);
        inputPassword = (EditText) findViewById(R.id.SignUppassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = inputUserName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                //String passwordConfirmation = inputPasswordConfirmation.getText().toString().trim();

                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(getApplicationContext(), "Ingrese su nombre de usuario!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingrese su correo electronico!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingrese su contraseña!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Favor de introducir mas de 6 caracteres !", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if (!passwordConfirmation.equals(password)){
//                    Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                progressBar.setVisibility(View.VISIBLE);
                //create user



                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "Se he establecido su cuenta con éxito !" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Error en autenticacion." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });



    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        progressBar.setVisibility(View.GONE);
    }

    private boolean FieldsValidate(){

        //Reset errors.
        inputEmail.setError(null);
        inputPassword.setError(null);
        //inputPasswordConfirmation.setError(null);

        //Store Values at the time of the login attempt.
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        //String passwordConfirmation = inputPasswordConfirmation.getText().toString().trim();

        boolean fieldsValidated = true;
        View focusView= null;


        //Check for a valid email address.
        if (TextUtils.isEmpty(email)){
            inputEmail.setError(getString(R.string.error_field_required));
            focusView = inputEmail;
            fieldsValidated = false;
        }else if (!isEmailValid(email)){
            inputEmail.setError(getString(R.string.error_invalid_email));
            focusView = inputEmail;
            fieldsValidated = false;
        }


        // Check for a valid password
        if (TextUtils.isEmpty(password)) {
            inputPassword.setError(getString(R.string.error_field_required));
            focusView = inputPassword;
            fieldsValidated = false;
        } else if (!isPasswordValid(password)) {
            inputPassword.setError(getString(R.string.error_invalid_password));
            focusView = inputPassword;
            fieldsValidated = false;
        }

        //check for a valid password confirmation

        /*if (TextUtils.isEmpty(passwordConfirmation)) {
            inputPasswordConfirmation.setError(getString(R.string.error_field_required));
            focusView = inputPasswordConfirmation;
            fieldsValidated = false;
        } else if (!isPasswordConfirmationValid(passwordConfirmation)) {
            inputPasswordConfirmation.setError(getString(R.string.error_invalid_password));
            focusView = inputPasswordConfirmation;
            fieldsValidated = false;
        }*/

        if(!fieldsValidated){
            //There was an error
            focusView.requestFocus();
        }

        return fieldsValidated;


    }
    private boolean isEmailValid(String email){
        //Validates its a working email address
        return email.contains("@");
    }
    private boolean isPasswordValid(String password){
        //Todo: create Expcetion for the email to exist
        return password.length()>6;
    }
    /*private boolean isPasswordConfirmationValid(String passwordConfirmation){
        return password.equals(passwordConfirmation);
    }*/



}





