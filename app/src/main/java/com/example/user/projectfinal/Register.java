package com.example.user.projectfinal;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Register extends AppCompatActivity {
    EditText register_name, register_password, register_email, register_phone,register_address, register_BG, register_age, register_gender;
    Button register_btn,login_btn;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name = (EditText)findViewById(R.id.register_name);
        register_password = (EditText)findViewById(R.id.register_password);
        register_email = (EditText)findViewById(R.id.register_email);
        register_phone = (EditText)findViewById(R.id.register_phone);
        register_address = (EditText)findViewById(R.id.register_address);
        register_BG = (EditText)findViewById(R.id.register_BG);
        register_age = (EditText)findViewById(R.id.register_age);
        register_gender = (EditText)findViewById(R.id.register_gender);
        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        register_btn = (Button)findViewById(R.id.register_btn);
        login_btn = (Button)findViewById(R.id.login_Btn);





    }

    public void registerbtn(View view) {
        String url = InfoUser.WEB_url+"userSign.php?name="+register_name.getText().toString()+
                "&email="+register_email.getText().toString()+"&phone="+register_phone.getText().toString()+"&password="+
                register_password.getText().toString()+"&address="+register_address.getText().toString()+"&BG="+register_BG.getText().toString()+
                "&age="+register_age.getText().toString()+"&gender="+register_gender.getText().toString();


        RequestQueue rq = Volley.newRequestQueue(Register.this);
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (register_name.getText().toString().trim().equals("") || register_email.getText().toString().trim().equals("")
                        || register_address.getText().toString().trim().equals("")||register_phone.getText().toString().trim().equals("") ||
                        register_BG.getText().toString().trim().equals("") || register_age.getText().toString().trim().equals("") ||
                        register_gender.getText().toString().trim().equals("")){
                    Toast.makeText(Register.this, "Please Fill All Data", Toast.LENGTH_SHORT).show();

                }
                else {


                    if (response.equals("MOBILE NUM EXIST")){
                    Toast.makeText(Register.this, "Phone NUM Exist", Toast.LENGTH_SHORT).show();
                } else {

                     InfoUser.name = register_name.getText().toString();
                    InfoUser.email = register_email.getText().toString();
                    InfoUser.password = register_password.getText().toString();
                    InfoUser.phone = register_phone.getText().toString();
                    InfoUser.address = register_address.getText().toString();
                    InfoUser.BloodGroub = register_BG.getText().toString();
                    InfoUser.age = register_age.getText().toString();
                    InfoUser.gender = register_gender.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String phone = register_phone.getText().toString();
                    editor.putString("phone", phone);


                        Intent i = new Intent(Register.this, MainActivity.class);
                    startActivity(i);
                }}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
          rq.add(sr);
    }

    public void loginbtn(View view) {

        LoginFragment loginFragment = new LoginFragment();
        FragmentManager manager = getFragmentManager();
        loginFragment.show(manager,"login");
    }
}
