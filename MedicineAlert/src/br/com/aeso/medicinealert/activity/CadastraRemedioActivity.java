package br.com.aeso.medicinealert.activity;

import java.util.Calendar;

import br.com.aeso.medicinealert.R;
import br.com.aeso.medicinealert.entities.PrescricaoRemedio;
import br.com.aeso.medicinialert.bancodedados.OpenHelper;
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

	private EditText edtNomeUsuario, edtNomeRemedio, edtDosagem, edtQtDias,
			edtVezesDia;
	private TextView tvData;
	private Button btnSalvar, btnCancelar,btnDataHoraInicio;
	private String nomeRemedio, nomeUsuario, qtDias, dosagem, vezesDia;
	private OpenHelper helper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_remedio);

		edtNomeUsuario = (EditText) findViewById(R.id.txtUsuario);
		edtNomeRemedio = (EditText) findViewById(R.id.edtNomeRemedio);
		edtDosagem = (EditText) findViewById(R.id.txtDosagemRemedio);
		edtQtDias = (EditText) findViewById(R.id.txtQtDiasRemedio);
		edtVezesDia = (EditText) findViewById(R.id.txtVezesDia);
	
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
		btnCancelar = (Button) findViewById(R.id.btnCancelar);
		btnDataHoraInicio = (Button) findViewById(R.id.btDtTmInicio);
		
		btnDataHoraInicio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		           DataHora();
			}
		});

		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				voltarMenuPrincipal();
			}
		});

		btnSalvar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (camposVazios() != false) {
					PrescricaoRemedio prescricaoRemedio = new PrescricaoRemedio();
					
					
					String usuario = edtNomeUsuario.getText().toString();
					String remedio = edtNomeRemedio.getText().toString();
					String txtQtdVezesDia = edtQtDias.getText().toString();
					String dosagem = edtDosagem.getText().toString();
					String txtQtdDias = edtQtDias.getText().toString();
					
					int qtdVezesDias = Integer.parseInt(txtQtdVezesDia);
					int qtdDias = Integer.parseInt(txtQtdDias);
					
					prescricaoRemedio.setNomeRemedio(remedio);
					prescricaoRemedio.setUsuario(usuario);
					prescricaoRemedio.setQtdVezesDia(qtdVezesDias);
					prescricaoRemedio.setDosagem(dosagem);
					prescricaoRemedio.setQtdDias(qtdDias);
					//prescricaoRemedio.setHorarioRemedio(horarioRemedio);
					
					helper.AdicionarPrescricaoRemedio(prescricaoRemedio);
					
					AlertDialog.Builder caixaAlerta = new AlertDialog.Builder(
							CadastraRemedioActivity.this);
					caixaAlerta.setMessage("Cadastro feito com sucesso!");
					caixaAlerta.show();
				} else {
					AlertDialog.Builder caixaAlerta = new AlertDialog.Builder(
							CadastraRemedioActivity.this);
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

	// Método para ser utilizado no botão "Cancelar"
	public void voltarMenuPrincipal() {
		setContentView(R.layout.activity_home);
	}
	
	//Método para acessar o layout de data e hora.
	public void DataHora(){
		setContentView(R.layout.activity_dt_time);
	}

	// Método para verificar se existi algum campo vazio.
	public Boolean camposVazios() {
		nomeUsuario = edtNomeRemedio.getText().toString().trim();
		nomeRemedio = edtNomeUsuario.getText().toString().trim();
		dosagem = edtDosagem.getText().toString().trim();
		qtDias = edtQtDias.getText().toString().trim();
		vezesDia = edtVezesDia.getText().toString().trim();

		if (nomeUsuario.equals("") && nomeRemedio.equals("") 
				&& dosagem.equals("") && qtDias.equals("") && vezesDia.equals("")) {
			return false;
		}
		return true;
	}

}
