package com.esprit.reset;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.esprit.reset.utils.AppSingleton;

import org.json.JSONException;

public class RegistreActivity extends AppCompatActivity {

    public static final String ServerURL = LoginActivity.ServerAddress+"reset/webservices/Register.php";    ProgressDialog progressDialog;
    private static final String TAG = "RegisterActivity";

    EditText edtUsername,edtEmail,edtPassword,edtRepeatedPassword;
    TextView link_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        setContentView(R.layout.activity_registre);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtRepeatedPassword = (EditText) findViewById(R.id.edtRepeatedPassword);
        link_login = (TextView) findViewById(R.id.link_login);

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }

    public void registreAction(View view) throws JSONException {
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        progressDialog.setMessage("Loading...");


        if(validate()){
            progressDialog.show();

            // Request parameters to be send with post request
            StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL+"?username="+edtUsername.getText().toString()+"&email="+edtEmail.getText().toString()+"&password="+edtPassword.getText().toString(), // the request body, which is a JsonObject otherwise null
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG,response);
                            if(response.matches("success")){
                                Toast.makeText(RegistreActivity.this,"Done",Toast.LENGTH_SHORT).show();
                                progressDialog.hide();
                                startActivity(new Intent(RegistreActivity.this,LoginActivity.class));
                            }else {
                                Toast.makeText(RegistreActivity.this,"Register failed",Toast.LENGTH_SHORT).show();
                                progressDialog.hide();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(RegistreActivity.this,"Register failed check your connection",Toast.LENGTH_SHORT).show();

                        }
                    }
            );

            // Adding JsonObject request to request queue
            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest, REQUEST_TAG);
        }

    }

    public boolean validate() {
        boolean valid = true;

        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String reEnterPassword = edtRepeatedPassword.getText().toString();

        if (username.isEmpty() || username.length() < 3) {
            edtUsername.setError("at least 3 characters");
            valid = false;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("enter a valid email address");
            valid = false;
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            edtRepeatedPassword.setError("Password Do not match");
            valid = false;
        } else {
            edtRepeatedPassword.setError(null);
        }

        return valid;
    }
}
