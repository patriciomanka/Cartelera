package com.undav.cartelera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;

public class InfoActivity extends ActionBarActivity {
	private Button fb,web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_main);
		fb = (Button) findViewById (R.id.btFB);
		fb.setOnClickListener (new OnClickListener () {
			public void onClick (View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/kandazteam")));
         }
      });
		web = (Button) findViewById (R.id.btWeb);
		web.setOnClickListener (new OnClickListener () {
			public void onClick (View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://kandazteam.wix.com/kandaz")));
         }
      });
	}
	public void onBackPressed() {
	    finish();
	}
}