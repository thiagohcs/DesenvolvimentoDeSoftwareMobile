package br.com.aeso.medicinealert.activity;

import java.util.Calendar;

import br.com.aeso.medicinealert.R;

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
			edtVezesDia, dtInicio;
	private TextView tvData;
	private DatePicker dtCadastro;
	private Button btnSalvar, btnCancelar;
	private String nomeRemedio, nomeUsuario, qtDias, dosagem, vezesDia;
	private int ano;
	private int mes;
	private int dia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_remedio);

		edtNomeUsuario = (EditText) findViewById(R.id.txtUsuario);
		edtNomeRemedio = (EditText) findViewById(R.id.edtNomeRemedio);
		edtDosagem = (EditText) findViewById(R.id.txtDosagemRemedio);
		edtQtDias = (EditText) findViewById(R.id.txtQtDiasRemedio);
		edtVezesDia = (EditText) findViewById(R.id.txtVezesDia);
		dtInicio = (EditText) findViewById(R.id.edtDataInicio);

		btnSalvar = (Button) findViewById(R.id.btnSalvar);
		btnCancelar = (Button) findViewById(R.id.btnCancelar);

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

	public void setCurrentDateOnView() {

		dtInicio = (EditText) findViewById(R.id.txtDataInicio);
		dtCadastro = (DatePicker) findViewById(R.id.dataCadastro);

		final Calendar c = Calendar.getInstance();
		ano = c.get(Calendar.YEAR);
		mes = c.get(Calendar.MONTH);
		dia = c.get(Calendar.DAY_OF_MONTH);

		// Inserir a data correta no textView
		dtInicio.setText(new StringBuilder()
				// Mês começa com 0 , basta apenas adicionar mais 1.
				.append(dia).append("-").append(mes + 1).append("-")
				.append(ano).append(" "));
		// Inserir a data correta dentro do datePicker
		dtCadastro.init(ano, mes, dia, null);
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
