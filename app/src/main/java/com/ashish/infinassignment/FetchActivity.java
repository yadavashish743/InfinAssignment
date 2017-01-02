package com.ashish.infinassignment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchActivity extends AppCompatActivity {
    private Button btn ;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        queue = Volley.newRequestQueue(this);
        btn =(Button)findViewById(R.id.fetch_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                askForPermission();
                if (ContextCompat.checkSelfPermission(FetchActivity.this,
                        Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED) {
                    nextActivity();

                }
            }
        });

    }
    public void nextActivity(){
        queue.add(new StringRequest(Request.Method.GET, "http://private-b08d8d-nikitest.apiary-mock.com/contacts",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<String> nameList = new ArrayList();
                        ArrayList<String> emailList = new ArrayList();
                        ArrayList<String> phoneList = new ArrayList();
                        ArrayList<String> officePhoneList = new ArrayList();
                        ArrayList<String> latitudeList = new ArrayList();
                        ArrayList<String> longitudeList = new ArrayList();
                        Log.d("contact","1234");

                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            JSONArray contacts = jsonObject.getJSONArray("contacts");
                            Log.d("contact","length  "+contacts.length());

                            for (int i=0; i<contacts.length();i++) {
                                nameList.add(contacts.getJSONObject(i).getString("name"));
                                try {
                                    emailList.add(contacts.getJSONObject(i).getString("email"));
                                }
                                catch (Exception e){
                                    emailList.add("XXXXXX");
                                }
                                try {
                                    phoneList.add(contacts.getJSONObject(i).getString("phone"));
                                }
                                catch (Exception e){
                                    phoneList.add("XXXXXX");
                                }
                                try {
                                    officePhoneList.add(contacts.getJSONObject(i).getString("officePhone"));
                                }
                                catch (Exception e){
                                    officePhoneList.add("XXXXXX");
                                }

                                latitudeList.add(contacts.getJSONObject(i).getString("latitude"));
                                longitudeList.add(contacts.getJSONObject(i).getString("longitude"));

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        for (int i = 0; i <nameList.size();i++){
                            Log.d("name",nameList.get(i)+" ashish");
                            Log.d("email",emailList.get(i)+" ashish");
                            Log.d("phone",phoneList.get(i)+" ashish");
                            Log.d("office",officePhoneList.get(i)+" ashish");
                            Log.d("lat",latitudeList.get(i)+" ashish");
                            Log.d("long",longitudeList.get(i)+" ashish");
                        }


                        Intent intent = new Intent(FetchActivity.this,MainActivity.class);
                        intent.putStringArrayListExtra("fnameList",nameList);
                        Log.d("fname","list");
                        intent.putStringArrayListExtra("femailList",emailList);
                        intent.putStringArrayListExtra("fphoneList",phoneList);
                        intent.putStringArrayListExtra("fofficePhoneList",officePhoneList);
                        intent.putStringArrayListExtra("latitudeList",latitudeList);
                        intent.putStringArrayListExtra("longitudeList",longitudeList);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));

    }
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 16;

    public boolean askForPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_CONTACTS)) {

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_CONTACTS)
                            == PackageManager.PERMISSION_GRANTED) {
                        nextActivity();
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    }

