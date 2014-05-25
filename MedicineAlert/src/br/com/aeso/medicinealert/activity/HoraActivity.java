package br.com.aeso.medicinealert.activity;

import br.com.aeso.medicinealert.R;
import br.com.aeso.medicinealert.R.layout;
import br.com.aeso.medicinealert.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HoraActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hora);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hora, menu);
		return true;
	}

}
