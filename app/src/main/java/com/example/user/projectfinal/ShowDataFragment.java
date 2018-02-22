package com.example.user.projectfinal;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowDataFragment extends Fragment {

    //String url = InfoUser.WEB_url+"showDataByPhone.php?phone="+InfoUser.phone;
    TextView tvShow_name,tvShow_email,tvShow_adddress,tvShow_BG,tvShow_age,tvShow_gender,tvShow_phone;
    Button btn_change;
    SharedPreferences preferences;
    public ShowDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_data, container, false);

        tvShow_name = (TextView) v.findViewById(R.id.tvShow_name);
        tvShow_email = (TextView)v.findViewById(R.id.tvShow_email);
        tvShow_adddress = (TextView)v.findViewById(R.id.tvShow_address);
        tvShow_BG = (TextView)v.findViewById(R.id.tvShow_BG);
        tvShow_age = (TextView)v.findViewById(R.id.tvShow_Age);
        tvShow_gender = (TextView)v.findViewById(R.id.tvShow_gender);
        tvShow_phone = (TextView) v.findViewById(R.id.tvShow_phone);
        btn_change = (Button)v.findViewById(R.id.UpdateData);
       //  preferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
       //  preferences.getString("phone",InfoUser.phone);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),MainActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getContext().startActivity(i);
                }
            }
        });

        String url = InfoUser.WEB_url+"showDataByPhone.php?phone="+InfoUser.phone;

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

             try {
                    tvShow_name.setText(response.getString("name"));
                    tvShow_email.setText(response.getString("email"));
                    tvShow_adddress.setText(response.getString("address"));
                    tvShow_BG.setText(response.getString("BG"));
                    tvShow_age.setText(response.getString("age"));
                    tvShow_phone.setText(response.getString("phone"));
                    tvShow_phone.setText(response.getString("phone"));
                    tvShow_gender.setText(response.getString("gender"));

                 Toast.makeText(getActivity(), "Hello "+tvShow_name.getText()+"\n"+"Thank you for inserted your Data "+"\n"+"if you need to update your information press in change button", Toast.LENGTH_LONG).show();


             } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
        rq.add(jor);


        return v;

    }

}
