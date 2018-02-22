package com.example.user.projectfinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 18/02/2018.
 */

public class ProductAdapter_allUser extends RecyclerView.Adapter<ProductAdapter_allUser.AllUser> {
    ArrayList<AllUserProduct> list = new ArrayList();
    Context context;

    public ProductAdapter_allUser(Context context, ArrayList<AllUserProduct> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();

    }


    public void filterList(ArrayList<AllUserProduct> list) {
        this.list = list;
        notifyDataSetChanged();


    }

    @Override
    public AllUser onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_row_alluser, parent, false);
        return new AllUser(v);
    }

    @Override
    public void onBindViewHolder(final AllUser holder, final int position) {
        String url = InfoUser.WEB_url + "ShowUserData.php";
        holder.tv_username.setText(list.get(position).name);
        holder.tv_userphone.setText(list.get(position).phone);
        holder.tv_userBG.setText(list.get(position).bloodgroub);
        holder.tv_userphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).phone, Toast.LENGTH_SHORT).show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AllUser extends RecyclerView.ViewHolder{
            TextView tv_username, tv_userphone, tv_userBG ;
        public AllUser(View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_userphone = itemView.findViewById(R.id.tv_phoneUser);
            tv_userBG = itemView.findViewById(R.id.tv_userBG);


        }
    }
}
