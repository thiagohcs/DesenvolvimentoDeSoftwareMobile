package br.com.aeso.medicinealert.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import br.com.aeso.medicinealert.R;
import br.com.aeso.medicinealert.entities.PrescricaoRemedio;
import br.com.aeso.medicinealert.persistence.BDHelper;

public class CadastraRemedioActivity extends Activity {

	private EditText edtNomeUsuario, edtNomeRemedio, edtQtDias, edtVezesDia, edtDosagem;
	private Button btnSalvar;
	private ImageButton btnData, btnHora;
	private String dataIncio, horaInicio, tipoDosagem;
	PrescricaoRemedio prescricaoRemedio;
	BDHelper rep;
	private int anoSelecionado, mesSelecionado, diaSelecionado,horaSelecionado, minutoSelecionado;
	private int anoView, mesView, diaView, horaView, minutoView;
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;

	public CadastraRemedioActivity() {
		final Calendar c = Calendar.getInstance();
		diaView = c.get(Calendar.DAY_OF_MONTH);
		mesView = c.get(Calendar.MONTH);
		anoView = c.get(Calendar.YEAR);
		horaView = c.get(Calendar.HOUR_OF_DAY);
		minutoView = c.get(Calendar.MINUTE);
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int yearSelected,
				int monthOfYear, int dayOfMonth) {
			anoSelecionado = yearSelected;
			mesSelecionado = monthOfYear;
			diaSelecionado = dayOfMonth;
			dataIncio = diaSelecionado + "/" + mesSelecionado + "/"+ anoSelecionado;
			Log.i("Data ", dataIncio);
		}
	};
	
	 private TimePickerDialog.OnTimeSetListener mTimeSetListener =
             new TimePickerDialog.OnTimeSetListener() {
                 public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                     horaSelecionado = hourOfDay;
                     minutoSelecionado = min;
                     horaInicio = horaSelecionado + ":" + minutoSelecionado;
                     Log.i("Hora ", horaInicio);
                   }
             };

	private OnClickListener onClickSalvar = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			prescricaoRemedio = new PrescricaoRemedio();
			// validando dados para inserir o objeto no banco
			prescricaoRemedio.setUsuario(edtNomeUsuario.getText().toString());
			prescricaoRemedio.setNomeRemedio(edtNomeRemedio.getText().toString());
			prescricaoRemedio.setQtdDias(Integer.parseInt(edtQtDias.getText().toString()));
			prescricaoRemedio.setQtdVezesDia(Integer.parseInt(edtVezesDia.getText().toString()));
			prescricaoRemedio.setTipoDosagem(tipoDosagem);
			prescricaoRemedio.setDosagem(Integer.parseInt(edtDosagem.getText().toString()));
			prescricaoRemedio.setDtInicio(dataIncio);
			prescricaoRemedio.setHrInicio(horaInicio);
			
			
			if(!(prescricaoRemedio.getUsuario().equals(null) && 
					prescricaoRemedio.getNomeRemedio().equals(null)&&
					prescricaoRemedio.getQtdDias().equals(null)&&
					prescricaoRemedio.getQtdVezesDia().equals(null)&&
					prescricaoRemedio.getTipoDosagem().equals(null)&&
					prescricaoRemedio.getDosagem().equals(null)&&
					prescricaoRemedio.getDtInicio().equals(null)&&
					prescricaoRemedio.getHrInicio().equals(null))){
					
					rep.inserir(prescricaoRemedio);
					messagem("Remédio cadastrado");
			}else{
				messagem("Existem campos que não foram preenchidos");
			}
		}
	};

	private OnClickListener onClickHora = new OnClickListener() {
		@Override
		public void onClick(View v) {
			showDialog(TIME_DIALOG_ID);
		}
	};

	private OnClickListener onClickData = new OnClickListener() {
		@Override
		public void onClick(View v) {
			showDialog(DATE_DIALOG_ID);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		dataIncio = data.getExtras().getString("Data Inicio");
		Log.i("Data Inicio", dataIncio);
	};

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
		rep = new BDHelper(getApplicationContext());
		int idRadioSelecionado = ((RadioGroup) findViewById(R.id.radioOpcoesDosagem))
				.getCheckedRadioButtonId();
		if (idRadioSelecionado == R.id.radioComprimido) {
			tipoDosagem = "comprimido";
		} else {
			tipoDosagem = "líquido";
		}

		btnData.setOnClickListener(onClickData);
		btnHora.setOnClickListener(onClickHora);
		btnSalvar.setOnClickListener(onClickSalvar);

	}

	@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                        mDateSetListener,
                        anoView, mesView, diaView);
        case TIME_DIALOG_ID:
            return new TimePickerDialog(this,
                    mTimeSetListener, horaView, minutoView, false);
        }
        return null;
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_remedio, menu);
		return true;
	}

	// Método genérico para verificar se existe algum editText vazio e mostrar um Toast com o erro
	public boolean ValidaEditText(String nomeCampo, EditText campo) {
		if (nomeCampo.equals("nomeUsuario")&& campo.toString().trim().equals("")|| campo.toString().equals(null)) {
			messagem("O nome do usuário não foi informado");
			return false;
		} else if (nomeCampo.equals("nomeRemedio")&& campo.toString().trim().equals("")|| campo.toString().equals(null)) {
			messagem("O nome do remédio não foi informado");
			return false;
		} else if (nomeCampo.equals("qtDias") && campo.toString().trim().equals("") || campo.toString().equals(null)) {
			messagem("A quantidade de dias não foi informado");
			return false;
		} else if (nomeCampo.equals("vezesDia")
				&& campo.toString().trim().equals("")
				|| campo.toString().equals(null)) {
			messagem("A quantidade de vezes ao dia não foi informado");
			return false;
		} else if (nomeCampo.equals("dosagem")
				&& campo.toString().trim().equals("")
				|| campo.toString().equals(null)) {
			messagem("A dosagem não foi informado");
			return false;
		}
		return true;
	}
	
	public Toast messagem(CharSequence text){
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		return toast;
	}
	
}
