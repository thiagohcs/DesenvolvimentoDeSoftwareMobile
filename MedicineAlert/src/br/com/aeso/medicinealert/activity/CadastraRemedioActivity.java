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
import br.com.aeso.medicinealert.R;

public class CadastraRemedioActivity extends Activity {

	private EditText edtNomeUsuario, edtNomeRemedio, edtQdtDias, edtVezesDia,
			edtDosagem;
	private Button btnSalvar;
	private ImageButton imgBtnData, imgBtnHora;
	private String nomeRemedio, nomeUsuario, qtDias, vezesDia, dosagem;

	// private OpenHelper helper;
	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_remedio);

		imgBtnData = (ImageButton) findViewById(R.id.imgBtnData);
		imgBtnHora = (ImageButton) findViewById(R.id.imgBtnHora);

		imgBtnData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CadastraRemedioActivity.this,
						DataActivity.class);
				startActivity(i);
			}
		});
		
		imgBtnHora.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CadastraRemedioActivity.this,
						HoraActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_remedio, menu);
		return true;
	}

	// // Método para ser utilizado no botão "Cancelar"
	// public void voltarMenuPrincipal() {
	// setContentView(R.layout.activity_home);
	// }
	//
	// //Método para acessar o layout de data e hora.
	// public void DataHora(){
	// //setContentView(R.layout.activity_dt_time);
	// }

	// // Método para verificar se existi algum campo vazio.
	// public Boolean camposVazios() {
	// nomeUsuario = edtNomeRemedio.getText().toString().trim();
	// nomeRemedio = edtNomeUsuario.getText().toString().trim();
	// dosagem = edtDosagem.getText().toString().trim();
	// qtDias = edtQtDias.getText().toString().trim();
	// vezesDia = edtVezesDia.getText().toString().trim();
	//
	// if (nomeUsuario.equals("") && nomeRemedio.equals("")
	// && dosagem.equals("") && qtDias.equals("") && vezesDia.equals("")) {
	// return false;
	// }
	// return true;
	// }

}
