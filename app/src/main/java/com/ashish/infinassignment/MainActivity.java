package com.ashish.infinassignment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ashish.infinassignment.Adapters.TabPagerAdapter;
import com.ashish.infinassignment.Fragments.TabLayoutAll;
import com.ashish.infinassignment.Fragments.TabLayoutMap;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<String> numberList = new ArrayList<String>();
    private ArrayList<Contacts>contacts = new ArrayList<Contacts>();
    private ArrayList<String> newNumberList1 = new ArrayList<String>();
    private ArrayList<String> newNameList = new ArrayList<String>();
    private ArrayList<String> fnameList = new ArrayList<String>();
    private ArrayList<String> femailList = new ArrayList<String>();
    private ArrayList<String> phoneList = new ArrayList<String>();
    private ArrayList<String> officePhoneList = new ArrayList<String>();
    private ArrayList<String> latitudeList = new ArrayList<String>();
    private ArrayList<String> longitudeList = new ArrayList<String>();
    private Contacts con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager)findViewById(R.id.mainActivity_viewPager);
        tabLayout = (TabLayout)findViewById(R.id.main_activity_tab_layout);
        setSupportActionBar((Toolbar)findViewById(R.id.main_activity_toolbar));
        toolbar = (Toolbar)findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TabLayout");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {

            readingContacts();
        }

        /*
        *Fetching lists from FetchActivity
         */
        fnameList = getIntent().getStringArrayListExtra("fnameList");
        for (int i = 0; i<fnameList.size(); i++){
            Log.d("fnameList",fnameList.get(i).toString());
        }
        Log.d("fname",fnameList.size()+"   list");
        femailList = getIntent().getStringArrayListExtra("femailList");
        phoneList =getIntent().getStringArrayListExtra("fphoneList");
        officePhoneList = getIntent().getStringArrayListExtra("fofficePhoneList");
        latitudeList = getIntent().getStringArrayListExtra("latitudeList");
        longitudeList = getIntent().getStringArrayListExtra("longitudeList");



        ArrayList<Fragment> fragments = new ArrayList<>();
        Bundle args = new Bundle();

        Collections.sort(contacts);
        for(int i=0; i <contacts.size();i++){
            newNameList.add(contacts.get(i).getName());
            newNumberList1.add(contacts.get(i).getNumber());

        }

        args.putStringArrayList("nameList",newNameList);
        args.putStringArrayList("numberList",newNumberList1);
        args.putStringArrayList("fnameList",fnameList);
        args.putStringArrayList("femailList",femailList);
        args.putStringArrayList("fphoneList",phoneList);
        args.putStringArrayList("fofficePhoneList",officePhoneList);
        args.putStringArrayList("latitudeList",latitudeList);
        args.putStringArrayList("longitudeList",longitudeList);


        Fragment tabLayoutAll = new TabLayoutAll();
        tabLayoutAll.setArguments(args);
        fragments.add(tabLayoutAll);

        Fragment tabLayoutMap = new TabLayoutMap();
        tabLayoutMap.setArguments(args);
        fragments.add(tabLayoutMap);
        CardView cv=(CardView)findViewById(R.id.single_card);
        pager.setAdapter(new TabPagerAdapter(this,getSupportFragmentManager(),fragments,new String[]{"All Contacts","Contacts Map"}));
        tabLayout.setupWithViewPager(pager,true);
        Log.d("Checking TabLayout","ashish");

    }

    public void readingContacts(){

        ArrayList<String> nameListRaw = new ArrayList<String>();
        ArrayList<String> numberListRaw = new ArrayList<String>();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = getContentResolver().query(uri, projection, null, null, null);

        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        if(people.moveToFirst()) {
            do {
                String name   = people.getString(indexName);
                String number = people.getString(indexNumber);
                // Log.d("first name", name);
                nameListRaw.add(name);
                numberListRaw.add(number);

                //String number = people.getString(indexNumber);
            } while (people.moveToNext());
        }
        int incrementer=0;
        for (int i = 0; i < numberListRaw.size(); i++){
            if (numberListRaw.get(i).length() >= 10){
                nameList.add(nameListRaw.get(i));
                numberList.add(numberListRaw.get(i));
                String name=nameList.get(incrementer).toString();
                String number=numberList.get(incrementer).toString();
                con = new Contacts(name,number);
                contacts.add(con);
                incrementer++;
            }

        }
        /*for (int as=0; as<numberList.size(); as++){
            Log.d("number", numberList.get(as).toString());
        }*/

    }
}
