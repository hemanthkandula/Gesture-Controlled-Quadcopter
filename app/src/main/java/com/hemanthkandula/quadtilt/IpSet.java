package com.hemanthkandula.quadtilt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
//
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

public class IpSet extends AppCompatActivity {
    private static final String KEY_IP = "IP_config";

    private EditText ip_address;
   // public SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref", 0);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_set);
        ip_address = (EditText) findViewById(R.id.editText);
        // Button save = (Button)findViewById(R.id.button);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    public void Saveip(View view) {
        String ip = ip_address.getText().toString();
        getip(ip);


    }

    public void getip(String ip) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("IP", 0);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_IP, ip);
// give key value as "sound" you mentioned and value what you // to want give as "1" in you mentioned
        editor.commit();
        //SharedPrefrences.setDefaults("ip_congfig",ip,getApplicationContext());

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }



}

