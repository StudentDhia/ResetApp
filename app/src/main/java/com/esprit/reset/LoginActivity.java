package com.esprit.reset;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.esprit.reset.utils.AppSingleton;
import com.esprit.reset.utils.CurrentUser;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    public static final String ServerAddress = "http://41.226.11.243:10080/";
    public static final String ServerURL = LoginActivity.ServerAddress+"reset/webservices/login.php";
    public static String token="";

    ProgressDialog progressDialog;
    private static final String TAG = "LoginActivity";
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }

    public void loginAction(View view) throws JSONException {
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        if (verification()){
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            // Request parameters to be send with post request
            StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"?username="+edtUsername.getText().toString()+"&password="+edtPassword.getText().toString(), // the request body, which is a JsonObject otherwise null
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                try {
                                    JSONObject data = new JSONObject(response);
                                    //String token = data.getString("token");
                                    LoginActivity.token = data.get("id").toString();
                                    Log.e("token", token);
                                    CurrentUser.user.setUsername(data.getString("username"));
                                    CurrentUser.user.setEmail(data.getString("email"));
                                    //CurrentUser.user.setEnable(data.getBoolean("enabled"));
                                   // CurrentUser.user.setSmoker(data.getBoolean("smoker"));
                                   // CurrentUser.user.setDrinker(data.getBoolean("drinker"));
                                    CurrentUser.user.setDrink_score(data.getLong("drink_score"));
                                    CurrentUser.user.setSmoke_score(data.getLong("smoke_score"));
                                    Log.e("user", CurrentUser.user.toString());
                                    progressDialog.hide();
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                    finish();
                                } catch (JSONException e) {
                                    Toast.makeText(LoginActivity.this,"Username/Password Invalide!",Toast.LENGTH_SHORT).show();
                                    progressDialog.hide();
                                }

                            //progressDialog.hide();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this,"Try Again it may be a internet problem",Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
            );

            // Adding JsonObject request to request queue
            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest, REQUEST_TAG);
        }





    }

    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistreActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public boolean verification(){
        Boolean verifcationBool = true;
        if ((edtUsername.getText().toString().equals(""))){
            edtUsername.setError("enter a username");
            progressDialog.hide();
            verifcationBool = false;
            onPause();

        }
        if (edtPassword.getText().toString().equals("")){
            edtPassword.setError("enter a password");
            progressDialog.hide();
            verifcationBool = false;
            onPause();

        }
        return verifcationBool;
    }
}
