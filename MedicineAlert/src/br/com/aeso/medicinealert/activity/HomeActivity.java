package br.com.aeso.medicinealert.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.aeso.medicinealert.R;

public class HomeActivity extends Activity {

	Button btCadastro,btConsulta;
	
	private OnClickListener onClickCadastar = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this,CadastraRemedioActivity.class);
			startActivity(intent);
		}
	};
	
	private OnClickListener onClickConsultar = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this,ConsultaHorariosRemediosActivity.class);
			startActivity(intent);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	
		btCadastro = (Button) findViewById(R.id.btCadadastro);
		btConsulta = (Button) findViewById(R.id.btConsulta);
		
		btCadastro.setOnClickListener(onClickCadastar);
		btConsulta.setOnClickListener(onClickConsultar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	
	
}
