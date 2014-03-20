package com.example.quizicka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends Activity {

	TextView resultado;
	int resul;
	String resposta;
	Button sair;
	Button jogarNovamente;

	private OnClickListener onClickSair = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	};

	private OnClickListener onClickJogarNovamente = new OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(ResultadoActivity.this,
					MainActivity.class);
			startActivity(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		resul = this.getIntent().getIntExtra("Acertos", resul);
		sair = (Button) findViewById(R.id.Sair);
		jogarNovamente = (Button) findViewById(R.id.JogarNovamente);
		resultado = (TextView) findViewById(R.id.textViewMsg);
		String resposta = "Você acertou " + Integer.toString(resul)
				+ " questões";
		resultado.setText(resposta);

		jogarNovamente.setOnClickListener(onClickJogarNovamente);

		sair.setOnClickListener(onClickSair);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	}

}
