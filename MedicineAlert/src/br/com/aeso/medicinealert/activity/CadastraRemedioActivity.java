package br.com.aeso.medicinealert.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import br.com.aeso.medicinealert.R;
import br.com.aeso.medicinealert.entities.PrescricaoRemedio;

public class CadastraRemedioActivity extends Activity {

	private EditText edtNomeUsuario, edtNomeRemedio, edtQtDias, edtVezesDia,edtDosagem;
	private Button btnSalvar;
	private RadioGroup radioOpcoesDosagem;
	private ImageButton btnData, btnHora;
	private String nomeRemedio, nomeUsuario, dosagem; 
	Integer qtDias, vezesDia;
	PrescricaoRemedio prescricaoRemedio;
	// private OpenHelper helper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_remedio);
		
		edtNomeUsuario = (EditText) findViewById(R.id.edtNomeUsuario);
		edtNomeRemedio = (EditText) findViewById(R.id.edtNomeRemedio);
		edtQtDias = (EditText) findViewById(R.id.edtQtdDias);
		edtVezesDia = (EditText) findViewById(R.id.edtVezesDia);
		edtDosagem = (EditText) findViewById(R.id.edtDosagem);
		btnData = (ImageButton) findViewById(R.id.btnData);
		btnHora = (ImageButton) findViewById(R.id.btnHora);
		btnSalvar = (Button) findViewById(R.id.btnSalvar);

		btnData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CadastraRemedioActivity.this,
						DataActivity.class);
				startActivity(i);
			}
		});

		btnHora.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CadastraRemedioActivity.this,
						HoraActivity.class);
				startActivity(i);
			}
		});
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				prescricaoRemedio = new PrescricaoRemedio();
				
				if(ValidaEditText("nomeUsuario", edtNomeUsuario) != false){
					prescricaoRemedio.setUsuario(edtNomeUsuario.getText().toString());
				}else if(ValidaEditText("nomeRemedio", edtNomeRemedio) != false){
					prescricaoRemedio.setNomeRemedio(edtNomeRemedio.getText().toString());
				}else if (ValidaEditText("qtDias", edtQtDias) != false){
					prescricaoRemedio.setQtdDias(Integer.parseInt(edtQtDias.getText().toString()));
				}else if (ValidaEditText("vezesDia", edtVezesDia) != false){
					prescricaoRemedio.setQtdVezesDia(Integer.parseInt(edtVezesDia.getText().toString()));
				}else if (ValidaEditText("dosagem", edtDosagem) != false){
					// verificar o tipo da dosagem
					prescricaoRemedio.setDosagem(edtVezesDia.getText().toString());
				}
				// setar no objeto a data e hora de inicio para tomar o remedio
				// chamar o repositorio para poder salvar no banco
				Toast t = new Toast(getApplicationContext());
				t.setText("Remédio cadastrado");
				t.show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_remedio, menu);
		return true;
	}

	
	// Método genérico para verificar se existe algum editText vazio e mostrar um Toast com o erro
	public boolean ValidaEditText(String nomeCampo, EditText campo) {
		if (nomeCampo.equals("nomeUsuario") && campo.toString().trim().equals("") || campo.toString().equals(null)){
			Toast t = new Toast(getApplicationContext());
			t.setText("O nome do usuário não foi informado");
			t.show();
			return false;
		}else if (nomeCampo.equals("nomeRemedio") && campo.toString().trim().equals("") || campo.toString().equals(null)){
			Toast t = new Toast(getApplicationContext());
			t.setText("O nome do remédio não foi informado");
			t.show();
			return false;
		}else if (nomeCampo.equals("qtDias") && campo.toString().trim().equals("") || campo.toString().equals(null)){
			Toast t = new Toast(getApplicationContext());
			t.setText("A quantidade de dias não foi informado");
			t.show();
			return false;
		}else if (nomeCampo.equals("vezesDia") && campo.toString().trim().equals("") || campo.toString().equals(null)){
			Toast t = new Toast(getApplicationContext());
			t.setText("A quantidade de vezes ao dia não foi informado");
			t.show();
			return false;
		}else if (nomeCampo.equals("dosagem") && campo.toString().trim().equals("") || campo.toString().equals(null)){
			Toast t = new Toast(getApplicationContext());
			t.setText("A dosagem não foi informado");
			t.show();
			return false;
		}
		return true;
	}
}
