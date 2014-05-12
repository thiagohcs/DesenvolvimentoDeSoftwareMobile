package br.com.aeso.medicinealert.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import br.com.aeso.medicinealert.R;

public class ConsultaHorariosRemediosActivity extends Activity {

	SearchView svConsultaRemedio;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_horarios_remedios);
		
		svConsultaRemedio = (SearchView) findViewById(R.id.svConsultaRemedio);
		
		svConsultaRemedio.setOnQueryTextFocusChangeListener( new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consulta_horarios_remedios, menu);
	
		return true;
	}

	private class SearchFiltro implements OnQueryTextListener{

		@Override
		public boolean onQueryTextChange(String newText) {
			Log.i("Script", "onQueryTextChange ->" + newText);
			return false;
		}

		@Override
		public boolean onQueryTextSubmit(String query) {
			Log.i("Script", "onQueryTextSubmit ->" + query);
			return false;
		}
		
	}
}
