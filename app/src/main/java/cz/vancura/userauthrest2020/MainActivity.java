package cz.vancura.userauthrest2020;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "myTAG-MainActivity";

    Button buttonLogin, buttonRegister;
    EditText editTextUsername, editTextPassword;
    CheckBox checkBox;
    TextView textViewStatus;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "started");

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewStatus = findViewById(R.id.textViewStatus);
        editTextUsername = findViewById(R.id.editTextTextPersonName);
        editTextPassword = findViewById(R.id.editTextNumberPassword);
        checkBox = findViewById(R.id.checkBox);

        queue = Volley.newRequestQueue(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                Log.d(TAG, "login username=" + username + " password=" + password);

                if ((!checkBox.isChecked() && (username.length()!=0) && (password.length()!=0))) {

                    Log.d(TAG, "Login without secure");

                    // PHP Url without Secure
                    String url ="https://www.vancura.cz/programing/Android/Demo/UserAuth/non-secured/login.php";

                    // Url params
                    Uri builtUri = Uri.parse(url).buildUpon()
                            .appendQueryParameter("username", username)
                            .appendQueryParameter("password", password)
                            .build();

                    String urlWithParams = builtUri.toString();
                    Log.d(TAG, "login urlWithParams=" + urlWithParams);

                    // Request a string response from the provided URL - GET
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, urlWithParams,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    String what = "Login Response is: "+ response;
                                    Log.d(TAG, what);
                                    textViewStatus.setText(what);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String what = "Login Volley ERROR "+ error.getLocalizedMessage();
                            Log.e(TAG, what);
                            textViewStatus.setText(what);
                        }
                    });

                    queue.add(stringRequest);

                }else{
                    // empty input
                    ShowEmptyError();
                }

                if ((checkBox.isChecked() && (username.length()!=0) && (password.length()!=0))) {

                    Log.d(TAG, "Login with secure");

                    // PHP Url with secure JWT
                    String url ="https://www.vancura.cz/programing/Android/Demo/UserAuth/secured/part2/login.php";

                    // data v JSON format
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("email", username+"@vancura.cz");
                        jsonBody.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String what = "Login Response is: "+ response;
                            Log.d(TAG, what);
                            textViewStatus.setText(what);
                            // "success":1,"message":"You have successfully logged in.","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL3BocF9hdXRoX2FwaVwvIiwiYXVkIjoiaHR0cDpcL1wvbG9jYWxob3N0XC9waHBfYXV0aF9hcGlcLyIsImlhdCI6MTYwNzM0ODM1NiwiZXhwIjoxNjA3MzUxOTU2LCJkYXRhIjp7InVzZXJfaWQiOiIxMCJ9fQ._H5hqmCAp27sIMn9QRaUGrAR-l4JdVymS8bWgB5Jv2E"}
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String what = "Login Volley ERROR "+ error.getLocalizedMessage();
                            Log.e(TAG, what);
                            textViewStatus.setText(what);
                        }
                    });

                    queue.add(postRequest);
                }else{
                    // empty input
                    ShowEmptyError();
                }

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                Log.d(TAG, "Register username=" + username + " password=" + password);

                if ((!checkBox.isChecked() && (username.length()!=0) && (password.length()!=0))) {

                    Log.d(TAG, "Register without secure");

                    // PHP Url without Secure
                    String url ="https://www.vancura.cz/programing/Android/Demo/UserAuth/non-secured/signup.php";

                    // Url params
                    Uri builtUri = Uri.parse(url).buildUpon()
                            .appendQueryParameter("username", username)
                            .appendQueryParameter("password", password)
                            .build();

                    String urlWithParams = builtUri.toString();
                    Log.d(TAG, "Register urlWithParams=" + urlWithParams);

                    // Request a string response from the provided URL - GET
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, urlWithParams,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    String what = "Register Response is: "+ response;
                                    Log.d(TAG, what);
                                    textViewStatus.setText(what);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String what = "Register Volley ERROR "+ error.getLocalizedMessage();
                            Log.e(TAG, what);
                            textViewStatus.setText(what);
                        }
                    });

                    queue.add(stringRequest);

                }else{
                    // empty input
                    ShowEmptyError();
                }


                if ((checkBox.isChecked() && (username.length()!=0) && (password.length()!=0))) {

                    Log.d(TAG, "Register with secure");

                    // PHP Url with secure JWT
                    String url ="https://www.vancura.cz/programing/Android/Demo/UserAuth/secured/part2/register.php";

                    // data v JSON format
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("name", username);
                        jsonBody.put("email", username+"@vancura.cz");
                        jsonBody.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String what = "Register Response is: "+ response;
                            Log.d(TAG, what);
                            textViewStatus.setText(what);
                            // response={"success":1,"status":201,"message":"You have successfully registered."}

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String what = "Register Volley ERROR "+ error.getLocalizedMessage();
                            Log.e(TAG, what);
                            textViewStatus.setText(what);
                        }
                    });

                    queue.add(postRequest);

                }else{
                    // empty input
                    ShowEmptyError();
                }

            }
        });

    }

    private void ShowEmptyError(){

        Toast.makeText(this, "Insert some data", Toast.LENGTH_SHORT).show();

    }
}