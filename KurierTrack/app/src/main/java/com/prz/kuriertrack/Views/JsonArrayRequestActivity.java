package com.prz.kuriertrack.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.prz.kuriertrack.AppController;
import com.prz.kuriertrack.Database.DatabaseAdapter;
import com.prz.kuriertrack.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
*
*/
public class JsonArrayRequestActivity extends Activity {

    private String TAG = JsonArrayRequestActivity.class.getSimpleName();
    private Button btnJsonArray;
    private TextView msgResponse;
    private ProgressDialog pDialog;

    private DatabaseAdapter dbAdapter;
    public static final String URL = "http://89.188.203.141:8080/rest/packs/all";

    // These tags will be used to cancel the requests
    private String tag_json_arry = "jarray_req";

    /**
	 *
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_array);
        dbAdapter = new DatabaseAdapter(getApplicationContext());

        btnJsonArray = (Button) findViewById(R.id.btnJsonArray);
        msgResponse = (TextView) findViewById(R.id.msgResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        btnJsonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.deletePacks();
                makeJsonArrayReq();
            }
        });

    }

    /**
     * Making json array request
     * */
    private void makeJsonArrayReq() {
        pDialog.show();
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonobject = response.getJSONObject(i);
                                String name = jsonobject.getString("name");
                                String desc = jsonobject.getString("description");
                                String street = jsonobject.getString("street");
                                String address = jsonobject.getString("address");
                                String city = jsonobject.getString("city");
                                String country = jsonobject.getString("country");
                                dbAdapter.insertContact(name, desc, street, address, city, country);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } {

                        }

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.dismiss();
            }
        });





        req.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_arry);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

}
