package br.com.aeso.medicinealert.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RepositorioGenerico extends SQLiteOpenHelper {

	private static final String NOME_BD = "MedicineAlert";
	private static final int VERSAO_BD = 1;

	public RepositorioGenerico(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + Repositorio.NOME_TABELA + "("
				+ Repositorio.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ Repositorio.USUARIO + "TEXT, " 
				+ Repositorio.NOME_REMEDIO + "TEXT, " 
				+ Repositorio.QTD_DIAS + " INTEGER, "
				+ Repositorio.QTD_VEZES_DIAS + " INTEGER, "
				+ Repositorio.TIPO_DOSAGEM + " TEXT, " 
				+ Repositorio.DOSAGEM + " DOUBLE, " 
				+ Repositorio.DATA_INICIO + "TEXT, "
				+ Repositorio.HORA_INICIO + "TEXT)");
		Log.d("[OpdenHelper]", "banco criado");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("[RepositorioGenerico]", "banco atualizado");

	}

}
