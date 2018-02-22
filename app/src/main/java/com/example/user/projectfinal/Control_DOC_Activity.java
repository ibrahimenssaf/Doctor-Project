package com.example.user.projectfinal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class Control_DOC_Activity extends AppCompatActivity {

    //Toolbar tol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control__doc_);

       // tol = (Toolbar) findViewById(R.id.toolbar);
         // setSupportActionBar(tol);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_allUser:

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                AllUSerFragment allUSerFragment = new AllUSerFragment();
                transaction.replace(R.id.container, allUSerFragment);
                transaction.commit();
                return true;

            case R.id.action_search:
                Toast.makeText(Control_DOC_Activity.this, "", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_send:
                Toast.makeText(Control_DOC_Activity.this, "SEND SMS", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}