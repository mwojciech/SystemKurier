package com.prz.kuriertrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.prz.kuriertrack.Views.JsonArrayRequestActivity;
import com.prz.kuriertrack.Views.JsonObjectRequestActivity;

public class MainActivity extends Activity {

    private Button btnJsonObject;
    private Button btnJsonArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		btnJsonObject = (Button) findViewById(R.id.btnJsonObjectRequest);
		btnJsonArray = (Button) findViewById(R.id.btnJsonArrayRequest);


        btnJsonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JsonObjectRequestActivity.class));
            }
        });

        btnJsonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JsonArrayRequestActivity.class));
            }
        });

	}
}
