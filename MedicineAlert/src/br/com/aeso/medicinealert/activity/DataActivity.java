package br.com.aeso.medicinealert.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import br.com.aeso.medicinealert.R;

public class DataActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_hora, menu);
		return true;
	}

}
