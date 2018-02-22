package com.example.user.projectfinal;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateDataFragment extends Fragment {

TextView tvFragment_number;
EditText editTextFragment_BG,editTextFragment_address,editTextFragment_age,editTextFragment_gender,tvFragment_name,tvFragment_email, editText_pass;
Button button_save;
    public UpdateDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_data, container, false);

        tvFragment_name = (EditText) v.findViewById(R.id.tvFragment_name);
        tvFragment_email = (EditText) v.findViewById(R.id.tvFragment_email);
        tvFragment_number = (TextView)v.findViewById(R.id.tvFragment_number);

        editTextFragment_BG = (EditText)v.findViewById(R.id.editTextFragment_BG);
        editTextFragment_address = (EditText)v.findViewById(R.id.editTextFragment_address);
        editTextFragment_age = (EditText)v.findViewById(R.id.editTextFragment_age);
        editText_pass = (EditText)v.findViewById(R.id.tvFragment_pass);

        editTextFragment_gender = (EditText)v.findViewById(R.id.editTextFragment_gender);

        tvFragment_number.setText(InfoUser.phone);

        button_save = (Button)v.findViewById(R.id.button_save);

        Toast.makeText(getActivity(), InfoUser.phone, Toast.LENGTH_SHORT).show();
        button_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String url =InfoUser.WEB_url+"updateUserData.php?phone="+InfoUser.phone+"&name="+tvFragment_name.getText().toString()+"&email="+
                        tvFragment_email.getText().toString()+"&BG="+editTextFragment_BG.getText().toString()+"&address="+editTextFragment_address.getText().toString()+
                        "&age="+editTextFragment_age.getText().toString()+"&gender="+editTextFragment_gender.getText().toString()+"&password="+editText_pass.getText().toString();

                RequestQueue rq = Volley.newRequestQueue(getActivity());
                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                            InfoUser.address = editTextFragment_address.getText().toString();
                            InfoUser.age = editTextFragment_age.getText().toString();
                            InfoUser.BloodGroub = editTextFragment_BG.getText().toString();
                            InfoUser.gender = editTextFragment_gender.getText().toString();
                            InfoUser.name =tvFragment_name.getText().toString();
                            InfoUser.email=tvFragment_email.getText().toString();

                            Toast.makeText(getActivity(), "SUCCESSFULLY Update", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getActivity(),Register.class);
                            startActivity(i);


                    }


               /*
                        *
                        *    if (response.equals("Mobile Does'nt Existing")){
                            Toast.makeText(getActivity(), "Please inter correct phone number", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            InfoUser.address = editTextFragment_address.getText().toString();
                            InfoUser.age = editTextFragment_age.getText().toString();
                            InfoUser.BloodGroub = editTextFragment_BG.getText().toString();
                            InfoUser.gender = editTextFragment_gender.getText().toString();
                            InfoUser.name =tvFragment_name.getText().toString();
                            InfoUser.email=tvFragment_email.getText().toString();

                            Toast.makeText(getActivity(), "SUCCESSFULLY Update", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getActivity(),Register.class);
                            startActivity(i);

                        }*/


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                rq.add(jor);
            }





            });
        return v;


}}


