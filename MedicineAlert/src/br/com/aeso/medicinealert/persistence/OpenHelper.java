package br.com.aeso.medicinealert.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class OpenHelper extends SQLiteOpenHelper {

	private static final String NOME_BD = "MedicineAlert";
	private static final int VERSAO_BD = 6;

	public OpenHelper(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase bd) {
		bd.execSQL("CREATE TABLE " + BDHelper.NOME_TABELA + "("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "usuario TEXT, " 
				+ "nome_remedio TEXT, " 
				+ "qtd_dias INTEGER, "
				+ "qtd_vezes_dias INTEGER, "
				+ "tipo_dosagem TEXT, " 
				+ "dosagem INTEGER, " 
				+ "data_inicio TEXT, "
				+ "hora_inicio TEXT);");
		Log.d("[OpdenHelper]", "banco criado");
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
		bd.execSQL("drop table " + BDHelper.NOME_TABELA + " ;");
		onCreate(bd);
	}

}
