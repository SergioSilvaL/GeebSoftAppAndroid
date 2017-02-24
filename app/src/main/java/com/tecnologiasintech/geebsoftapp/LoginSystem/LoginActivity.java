
package com.tecnologiasintech.geebsoftapp.LoginSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.tecnologiasintech.geebsoftapp.MainActivity;
import com.tecnologiasintech.geebsoftapp.R;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.util.Arrays;

import static android.view.View.GONE;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{



    private EditText inputEmail, inputPassword;

    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private static final String TAG = LoginActivity.class.getSimpleName();

//    //Google Shit
    private static int RC_SIGN_IN = 0;
//    private GoogleApiClient mGoogleApiClient;

    //Facebook shit
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    //General Conection Shit
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.sign_up01);



        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        //Checks if user is Logged in

        if(mAuth.getCurrentUser()!= null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }

        mAuthListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user  = firebaseAuth.getCurrentUser();
                if(user != null){
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getEmail() + "\n "+ user.getDisplayName());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...

            }
        };


        //Google Auth

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

        //Button to Sign In With Google
        //TODO Convert Google ImageButton to SignInbutton to be able login with
//        SignInButton mGooglesignInButton = (SignInButton) findViewById(R.id.google_sign_in_button);
//        mGooglesignInButton.setSize(SignInButton.SIZE_WIDE);
//        mGooglesignInButton.setColorScheme(SignInButton.COLOR_LIGHT);
//        mGooglesignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.v(TAG,"Google Sign In Button Clicked");
//                signInWithGoogle();
//            }
//        });

        //Facebook shit again
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.btn_register_facebook);
        //Todo Add background
        loginButton.setBackgroundResource(R.drawable.button_facebook_login);

        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Se cancelo la operacion",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Ocurrio un error al ingresar",Toast.LENGTH_SHORT).show();
            }
        });


        inputEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        inputPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        btnSignup = (Button) findViewById(R.id.btnLoginSignUp);
        btnReset = (Button) findViewById(R.id.btnLoginResetPassword);
        btnLogin = (Button) findViewById(R.id.btnLoginSignIn);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Sign In button was clicked");
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingrese un correo electronico!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingrese una contraseña!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        //Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(GONE);
                        if(e instanceof FirebaseAuthException){
                            Log.v("Error_FirebaseAuth",((FirebaseAuthException) e).getErrorCode());

                            Toast.makeText(LoginActivity.this,
                                    FirebaseExceptionConstants.getFirebaseExceptionConstants(
                                            (((FirebaseAuthException) e).getErrorCode())),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });




    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error, en el login con facebook",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    //Google Firebase Auth

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //google

//        if(requestCode == RC_SIGN_IN){
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//
//            if(result.isSuccess()){
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = result.getSignInAccount();
//                firebaseAuthWithGoogle(account);
//            }
//            else{
//                //// Google Sign In failed
//                Log.d(TAG, "Google Login Failed");
//            }
//        }
    }


    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener!= null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
//
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//
//
//                        Log.d("AUTH", "signInWithCredential:oncomplete: " + task.isSuccessful());
//                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                    }
//                });
//    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed.");
    }

//    private void signInWithGoogle(){
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

}
