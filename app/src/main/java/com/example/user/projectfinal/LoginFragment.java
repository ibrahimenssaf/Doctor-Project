package com.example.user.projectfinal;


import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class LoginFragment extends DialogFragment {

EditText loginF_password,loginF_phone;
Button loginF_btn;
SharedPreferences sharedPreferences;
String url =InfoUser.WEB_url+"login.php?";
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        loginF_phone = (EditText)v.findViewById(R.id.loginF_phone);
        loginF_password = (EditText)v.findViewById(R.id.loginF_password);
        loginF_btn = (Button)v.findViewById(R.id.buttonF_login);
        InfoUser.phone = loginF_phone.getText().toString();
        InfoUser.password = loginF_password.getText().toString();

       // SharedPreferences preferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = preferences.edit();
        //InfoUser.phone = loginF_phone.getText().toString();
        //editor.putString("phone", InfoUser.phone);

        loginF_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url+="phone="+loginF_phone.getText().toString()+"&password="+loginF_password.getText().toString();
                RequestQueue rq = Volley.newRequestQueue(getActivity());
                StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("FAILD")){
                            Toast.makeText(getActivity(), "LOGIN FAILD", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            InfoUser.phone = loginF_phone.getText().toString();
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                rq.add(sr);
            }
        });
        return v;
    }

}
