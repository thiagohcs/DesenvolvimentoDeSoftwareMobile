package br.com.aeso.medicinealert.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import br.com.aeso.medicinealert.R;
import br.com.aeso.medicinealert.entities.PrescricaoRemedio;
import br.com.aeso.medicinealert.persistence.BDHelper;

public class ConsultaHorariosRemediosActivity extends Activity {

	private ListView lv;
	private BDHelper rep;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_horarios_remedios);

		rep = new BDHelper(getApplicationContext());
		lv = (ListView) findViewById(R.id.Lista);
		List<String> listRemedio = listaRemedio();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listRemedio);
				
		lv.setAdapter(adapter);
	}

	public List<String> listaRemedio(){
		List<PrescricaoRemedio> listRemedio = rep.buscar();
		List<String> remedios = new ArrayList<String>();
		for (int i = 0; i < listRemedio.size()-1; i++) {
			remedios.add(listRemedio.get(i).getNomeRemedio());
		}
		return remedios;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.consulta_horarios_remedios, menu);
		SearchView sv = new SearchView(this);
		sv.setOnQueryTextListener(new SearchFiltro());

		MenuItem m1 = menu.add(0, 0, 0, "Item1");
		m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		m1.setActionView(sv);

		return true;
	}

	private class SearchFiltro implements OnQueryTextListener {

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
