package com.prz.kuriertrack.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.prz.kuriertrack.AppController;
import com.prz.kuriertrack.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
*
*/
public class JsonObjectRequestActivity extends Activity {

	private String TAG = JsonObjectRequestActivity.class.getSimpleName();
	private Button btnJsonObject;
	private TextView msgResponse;
	private ProgressDialog pDialog;

    public static final String URL = "http://192.168.0.101:8080/rest/packs/all";
	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req";

	/**
	 *
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json_object);

        btnJsonObject = (Button) findViewById(R.id.btnJsonObj);
		msgResponse = (TextView) findViewById(R.id.msgResponse);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

        btnJsonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjectReq();
            }
        });
	}

	/**
	 * Making json object request
	 * */
	private void makeJsonObjectReq() {
        pDialog.show();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, URL, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						msgResponse.setText(response.toString());
                        pDialog.dismiss();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
                        pDialog.dismiss();
					}
				}) {

			/**
			 * Passing some request headers
			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				/*params.put("name", "Androidhive");
				params.put("email", "abc@androidhive.info");
				params.put("pass", "password123");*/

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq,
				tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);		
	}
}
