package com.example.quizicka;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ResultadoActivity extends Activity {

	TextView resultado;
	int resul;
	String resposta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		resul = this.getIntent().getIntExtra("Acertos", resul);
		resultado = (TextView) findViewById(R.id.textViewMsg);
		String resposta = "Você acertou "+Integer.toString(resul)+" questões";
	resultado.setText(resposta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	}

}
