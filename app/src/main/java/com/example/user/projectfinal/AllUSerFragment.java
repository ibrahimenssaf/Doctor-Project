package com.example.user.projectfinal;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URI;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllUSerFragment extends Fragment {

    String url = InfoUser.WEB_url+"ShowUSerData.php";
    RecyclerView rv;
    ArrayList<AllUserProduct> list = new ArrayList<>();
    public AllUSerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_u, container, false);
        rv = (RecyclerView)v.findViewById(R.id.rv);
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int x=0; x< response.length(); x++){
                    try {
                        list.add(new AllUserProduct(response.getJSONObject(x).getString("name"),
                                response.getJSONObject(x).getString("phone"),
                                response.getJSONObject(x).getString("BG")));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ProductAdapter_allUser productAdapter_allUser = new ProductAdapter_allUser(getActivity(), list);
                rv.setAdapter(productAdapter_allUser);
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(jar);
        return v;
    }


}





















