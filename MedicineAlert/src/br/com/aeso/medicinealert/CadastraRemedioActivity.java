package br.com.aeso.medicinealert;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class CadastraRemedioActivity extends Activity {

	private TextView tvData;
	private Button btVoltMenu;
	private DatePicker dtCadastro;
	private Button btConfCadastro;
	private EditText etNomeRemedio,etDosagem,etQtDias,etNomeUsuario;
	private String nomeRemedio,nomeUsuario,qtDias,dosagem;
	
	private int ano;
	private int mes;
	private int dia;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_remedio);
		
		etNomeRemedio = (EditText) findViewById(R.id.txtNomeRemedio);
	    etDosagem = (EditText) findViewById(R.id.txtDosagem);
	    etQtDias = (EditText) findViewById(R.id.txtQtDias);
	    etNomeUsuario = (EditText) findViewById(R.id.txtNomeUsuario);
		
		
		btVoltMenu = (Button) findViewById(R.id.cancCadastro);
		btConfCadastro = (Button) findViewById(R.id.confCadastro);
		
		btVoltMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				voltarMenuPrincipal();
			}
		});
		
		btConfCadastro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (camposVazios() != false) {
				AlertDialog.Builder caixaAlerta = 	
					new AlertDialog.Builder(CadastraRemedioActivity.this);
					caixaAlerta.setMessage("Cadastro feito com sucesso!");
					caixaAlerta.show();
				}else{
					AlertDialog.Builder caixaAlerta = 	
							new AlertDialog.Builder(CadastraRemedioActivity.this);
							caixaAlerta.setMessage("Preencha todos os campos");
							caixaAlerta.show();	
				}
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_remedio, menu);
		return true;
	}
	
	//Método para ser utilizado no botão "Cancelar"
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
		    //Mês começa com 0 , basta apenas adicionar mais 1.
		.append(dia).append("-").append(mes + 1).append("-").append(ano).append(" ")
				
				);
		
		//Inserir a data correta dentro do datePicker		
		dtCadastro.init(ano, mes, dia,null);
	}
	//Método para verificar se existi algum campo vazio.
	public Boolean camposVazios(){
		
		nomeRemedio = etNomeUsuario.getText().toString().trim();
		nomeUsuario = etNomeRemedio.getText().toString().trim();
		dosagem = etDosagem.getText().toString().trim();
		qtDias = etQtDias.getText().toString().trim();
		
		if (nomeRemedio.equals("") && nomeUsuario.equals("") && dosagem.equals("") && qtDias.equals("")) {
			return false;
		}
		
		return true;
	}
	
	
}
