package br.com.aeso.medicinealert.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import br.com.aeso.medicinealert.R;

public class HoraActivity extends Activity {
	
	TimePicker timerPiker;
	Button btnSalvarHora;
	String hora;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hora);
		
		timerPiker = (TimePicker) findViewById(R.id.timePicker1);
		btnSalvarHora = (Button) findViewById(R.id.btnSalvarHora);
		
		btnSalvarHora.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HoraActivity.this,CadastraRemedioActivity.class);
				hora = timerPiker.getCurrentHour()+":"+timerPiker.getCurrentMinute();
				intent.putExtra("Hora de ínicio do remédio", hora);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hora, menu);
		return true;
	}

}
