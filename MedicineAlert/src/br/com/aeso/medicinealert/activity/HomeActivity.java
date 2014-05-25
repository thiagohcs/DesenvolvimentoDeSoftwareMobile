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

	Button btCadastrar;
	Button btConsulta;
	
//	private OnClickListener onClickConsultar = new OnClickListener() {
//		@Override
//		public void onClick(View v) {
//			Intent intent = new Intent(HomeActivity.this,ConsultaHorariosRemediosActivity.class);
//			startActivity(intent);
//		}
//	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	
		btCadastrar = (Button) findViewById(R.id.btCadadastra);
		btConsulta = (Button) findViewById(R.id.btConsulta);
		
		btCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, CadastraRemedioActivity.class);
				startActivity(i);
			}
		});
		btConsulta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,ConsultaHorariosRemediosActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	
	
}
