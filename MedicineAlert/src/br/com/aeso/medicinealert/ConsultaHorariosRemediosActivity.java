package br.com.aeso.medicinealert;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ConsultaHorariosRemediosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_horarios_remedios);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consulta_horarios_remedios, menu);
		return true;
	}

}
