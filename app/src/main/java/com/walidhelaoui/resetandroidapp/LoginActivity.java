package com.walidhelaoui.resetandroidapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.resetandroidapp.utils.AppSingleton;
import com.walidhelaoui.resetandroidapp.utils.CurrentUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    public static final String ServerAddress = "http://192.168.0.102/";
    public static final String ServerURL = LoginActivity.ServerAddress+"resetWS/web/api/login";
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
            StringRequest postRequest = new StringRequest(Request.Method.POST, ServerURL, // the request body, which is a JsonObject otherwise null
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.contains("token")){
                                String json = response.substring(response.indexOf("{")  , response.length());
                                try {
                                    JSONObject data = (JSONObject) new JSONObject(json).get("data");
                                    String token = data.getString("token");
                                    LoginActivity.token = token;
                                    Log.e("token", token);
                                    new CurrentUser(LoginActivity.this);
                                    progressDialog.hide();
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                Toast.makeText(LoginActivity.this,"Username/Password Invalide!",Toast.LENGTH_SHORT).show();
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
            ) {
                @Override
                public byte[] getBody() throws AuthFailureError {
                    HashMap<String, String> params2 = new HashMap<String, String>();
                    params2.put("username", edtUsername.getText().toString());
                    params2.put("password", edtPassword.getText().toString());
                    return new JSONObject(params2).toString().getBytes();

                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }


            };

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
