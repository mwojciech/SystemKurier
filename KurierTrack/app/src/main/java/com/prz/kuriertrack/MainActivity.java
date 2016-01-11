package com.prz.kuriertrack;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.prz.kuriertrack.Database.DatabaseAdapter;
import com.prz.kuriertrack.Model.Pack;
import com.prz.kuriertrack.Views.JsonArrayRequestActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Button btnClearSelected;
    private Button btnSave;
    private Button btnMao;
    private ListView lv;
    private LinearLayout llControlButtons;

    private DatabaseAdapter dbAdapter;
    private Cursor contactCursor;
    private List<Pack> list;
    private PackListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*********** ZADANIE 2 ************/
        //widok
        btnSave = (Button) findViewById(R.id.btnSave);
        btnMao = (Button) findViewById(R.id.btnMao);

        lv = (ListView) findViewById(R.id.lvContacts);
        llControlButtons = (LinearLayout) findViewById(R.id.llControlButtons);

        initListView();
        buttonsOnClickListeners();
        /*--------------------------------*/

    }

    private void initListView() {
        dbAdapter = new DatabaseAdapter(getApplicationContext());
        list = new ArrayList<Pack>();

        contactCursor = getAllRecords();
        updateContactList();

        listAdapter = new PackListAdapter(this, list);
        lv.setAdapter(listAdapter);

        initListViewOnItemClick();
    }


    private Cursor getAllRecords() {
        contactCursor = dbAdapter.getAllContacts();
        if(contactCursor != null) {
            contactCursor.moveToFirst();
        }
        return contactCursor;
    }


    private void updateContactList() {
        if(contactCursor != null && contactCursor.moveToFirst()) {
            do {
                int id = contactCursor.getInt(DatabaseAdapter.ID_COLUMN);
                String name = contactCursor.getString(DatabaseAdapter.NAME_COLUMN);
                String desc = contactCursor.getString(DatabaseAdapter.DESC_COLUMN);
                String street = contactCursor.getString(DatabaseAdapter.STREET_COLUMN);
                String address = contactCursor.getString(DatabaseAdapter.ADDRESS_COLUMN);
                String city = contactCursor.getString(DatabaseAdapter.CITY_COLUMN);
                String country = contactCursor.getString(DatabaseAdapter.COUNTRY_COLUMN);

                list.add(new Pack(id, name, desc, street, address, city, country));
            } while(contactCursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        if(dbAdapter != null)
            dbAdapter.close();
        super.onDestroy();
    }


    private void initListViewOnItemClick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Pack pack = list.get(position);
                pack.getAddress();
                String search = pack.getCity() + ",+" + pack.getStreet() + "+" + pack.getAddress() + ",+" + pack.getCountry();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + search);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }



    private void buttonsOnClickListeners() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnSave:
                        startActivity(new Intent(MainActivity.this, JsonArrayRequestActivity.class));
                        break;
                    case R.id.btnMao:
                        ArrayList<String> l = new ArrayList<String>();
                        for(int i =0; i < list.size(); i++){
                            String a = list.get(i).getCity();
                            String b = list.get(i).getStreet();
                            String c = list.get(i).getAddress();
                            String fin = a + " " + b + " " + c;
                            l.add(fin);
                        }
                        Log.d("Wojciech", "  " + l);
         Intent i = new Intent(getApplicationContext(), MapActivity.class);
                        i.putExtra("lista", l);
                        startActivity(i);
                        break;
                    default:
                        break;
                }
            }
        };
        btnSave.setOnClickListener(onClickListener);
        btnMao.setOnClickListener(onClickListener);
    }


    private void updateListViewData() {
        dbAdapter.deletePacks();
        contactCursor.requery();
        list.clear();
        updateContactList();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListView();
    }
}

