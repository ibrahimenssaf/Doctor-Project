package com.example.user.projectfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DoctorLoginActivity extends AppCompatActivity {

    EditText editText_doctorID,editText_doctorPass;
    Button doctor_BtnLogin;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        editText_doctorID = (EditText)findViewById(R.id.editText_doctorID);
        editText_doctorPass = (EditText)findViewById(R.id.editText_doctorPass);

        doctor_BtnLogin = (Button)findViewById(R.id.button_Doc);
        sharedPreferences = getSharedPreferences("DoctorData", Context.MODE_PRIVATE);

        doctor_BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = InfoUser.WEB_url+"DoctorLogin.php?id="+editText_doctorID.getText().toString()+"&password="+editText_doctorPass.getText().toString();
                RequestQueue rq = Volley.newRequestQueue(DoctorLoginActivity.this);
                StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("FAILD")){
                            Toast.makeText(DoctorLoginActivity.this, "Please Ensuring For Your Data", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            InfoUser.DocID = editText_doctorID.getText().toString();
                            InfoUser.DocPass = editText_doctorPass.getText().toString();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            int id = Integer.parseInt(editText_doctorID.getText().toString());
                            int password = Integer.parseInt(editText_doctorPass.getText().toString());
                            editor.putInt("DocID", id);
                            editor.putInt("DocPAss",password);
                            editor.commit();

                            Intent i = new Intent(DoctorLoginActivity.this, Control_DOC_Activity.class);
                            startActivity(i);
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rq.add(sr);

            }
        });

    }
}
