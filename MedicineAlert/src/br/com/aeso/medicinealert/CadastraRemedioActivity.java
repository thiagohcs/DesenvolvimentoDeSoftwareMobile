package br.com.aeso.medicinealert;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class CadastraRemedioActivity extends Activity {

	private TextView tvData;
	private Button btVoltMenu;
	private DatePicker dtCadastro;
	
	
	
	private int ano;
	private int mes;
	private int dia;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_remedio);
	
		btVoltMenu = (Button) findViewById(R.id.cancCadastro);	
		
	
		
		btVoltMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				voltarMenuPrincipal();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_remedio, menu);
		return true;
	
		
		
	}
	
	
	public void voltarMenuPrincipal(){
		setContentView(R.layout.activity_home);
	}
	
	public void setCurrentDateOnView(){
		
		tvData = (TextView)findViewById(R.id.tvData);
		dtCadastro = (DatePicker)findViewById(R.id.cadastroRemedioDate);
		
		final Calendar c = Calendar.getInstance();
		ano = c.get(Calendar.YEAR);
		mes = c.get(Calendar.MONTH);
		dia = c.get(Calendar.DAY_OF_MONTH);
		
		//Inserir a data correta no textView
		tvData.setText(new StringBuilder()
		    //Mês é 0 , basta apenas adicionar mais 1.
			.append(mes + 1).append("-").append(dia).append("-").append(ano).append(" ")
				
				);
		
		//Inserir a data correta dentro do datePicker		
		dtCadastro.init(ano, mes, dia,null);
	}
	
	
	
	
}
