package br.com.aeso.medicinealert;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

	Button btCadastro,btConsulta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	
		btCadastro = (Button) findViewById(R.id.btCadadastro);
		btConsulta = (Button) findViewById(R.id.btConsulta);
		
		btCadastro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarCadastro();
				
			}
		});
		
		btConsulta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarConsulta();				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void chamarCadastro(){
		setContentView(R.layout.activity_cadastra_remedio);
	}
	
	public void chamarConsulta(){
		setContentView(R.layout.activity_consulta_horarios_remedios);
	}
	
	
}
