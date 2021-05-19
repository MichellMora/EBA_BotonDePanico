package com.proyecto.ebabotndepnico;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.proyecto.ebabotndepnico.R;
import com.facebook.CallbackManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ConexionFacebook extends AppCompatActivity {

    private CallbackManager cbm;
    private LoginButton loginButton;
    private TextView tvperfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_facebook);

       /* FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/

        loginButton = findViewById(R.id.login_button);
        tvperfil = findViewById(R.id.tvperfil);

        cbm = CallbackManager.Factory.create();

        loginButton.setReadPermissions(Arrays.asList("user_gender, user_friends"));

        loginButton.registerCallback(cbm,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Inicio", "Exitoso");
                    }

                    @Override
                    public void onCancel() {
                        Log.d("Inicio", "No procesado");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("Inicio", "Errado");
                    }
                });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cbm.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        GraphRequest gp = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("Datos", object.toString());
                        try {
                            String name = object.getString("name");
                            String id = object.getString("id");
                            tvperfil.setText(name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle b= new Bundle();
        b.putString("fields", "gender , name, id, first_name,last_name");

        gp.setParameters(b);
        gp.executeAsync();
    }

    AccessTokenTracker att = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null){
                LoginManager.getInstance().logOut();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        att.stopTracking();
    }
}