package com.example.quizicka;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestaoCincoActivity extends Activity {
	
	Button confirmar;
	int acertos;
	
	private OnClickListener onClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(QuestaoCincoActivity.this, 
					ResultadoActivity.class);
			int idRadioSelecionado = ((RadioGroup) findViewById(R.id.radioGroupQ5))
					.getCheckedRadioButtonId();
			if(idRadioSelecionado==R.id.opcaoQuatroQuestaoCinco){
				acertou().show();
				acertos++;
			}else{
				errou().show();
			}
			intent.putExtra("Acertos", acertos);
			startActivity(intent);
			
		}
	};
	
	public Toast acertou(){
		Context context = getApplicationContext();
		CharSequence text = "Acertou!";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		return toast;
	}
	
	public Toast errou(){
		Context context = getApplicationContext();
		CharSequence text = "Errou!";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		return toast;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questao_cinco);
		
		acertos = this.getIntent().getIntExtra("Acertos", acertos);
		confirmar = (Button) findViewById(R.id.buttonConfirmaQ5);
		confirmar.setOnClickListener(onClick);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questao_cinco, menu);
		return true;
	}

}
