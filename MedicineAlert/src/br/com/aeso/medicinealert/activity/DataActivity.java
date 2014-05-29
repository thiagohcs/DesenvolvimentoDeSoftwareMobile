package br.com.aeso.medicinealert.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import br.com.aeso.medicinealert.R;

public class DataActivity extends Activity {
	
	DatePicker dataPiker;
	Button btnSalvarData;
	String data;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		
		dataPiker = (DatePicker) findViewById(R.id.datePicker1);
		btnSalvarData = (Button) findViewById(R.id.btnSalvarData);
		
		btnSalvarData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				data = dataPiker.getDayOfMonth()+"/"+dataPiker.getMonth()+"/"+dataPiker.getYear();
				
			}
		});  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_hora, menu);
		return true;
	}

}
