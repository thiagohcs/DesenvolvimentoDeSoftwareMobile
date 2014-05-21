package br.com.aeso.medicinealert.persistence;

import br.com.aeso.medicinealert.entities.HorarioRemedio;
import br.com.aeso.medicinealert.entities.PrescricaoRemedio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Repositorio extends SQLiteOpenHelper {

	private static final String nomeBd = "medicineAlert.bd";
	private static final int versaoBd = 20;

	public Repositorio(Context context) {
		super(context, nomeBd, null, versaoBd);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + "HorarioRemedio"
				+ "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + "data"
				+ "DATE, " + "hora" + "TIME)");

		db.execSQL("CREATE TABLE " + "PrescricaoMedica" + "(id "
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + "nome" + "TEXT, "
				+ "usuario" + "TEXT, " + "qtDias" + " INTEGER, " + "qtVezesDia"
				+ " INTEGER, " + "dosagem"
				+ " FLOAT, "
				+
				// "remedio"+ " TEXT, "+
				"idHorarioRemedio" + "INTEGER);"
				+ "FOREIGN KEY(id) REFERENCES " + "HorarioRemedio" + " ("
				+ "id" + ");");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + "HorarioRemedio");
		db.execSQL("DROP TABLE IF EXISTS " + "PrescricaoMedica");
	}

	public void AdicionarHoraDataInicio(HorarioRemedio horarioRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("data", horarioRemedio.getData().toString());
		cv.put("hora", horarioRemedio.getHora().toString());
		db.insert("HorarioRemedio", null, cv);
		db.close();
	}

	public Cursor getAllHorarioRemedio() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("SELECT " + "id" + " as _id, " + "data, "
				+ "hora" + " from " + " HorarioRemedio", new String[] {});
		return cur;
	}

	public int UpdateHorarioRemedio(HorarioRemedio horarioRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("data", horarioRemedio.getData().toString());
		cv.put("hora", horarioRemedio.getHora().toString());
		return db.update("HorarioRemedio", cv, "id" + " =?",
				new String[] { String.valueOf(horarioRemedio.getId()) });

	}

	public void DeleteHorarioRemedio(HorarioRemedio horarioRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("HorarioRemedio", "id" + "=?",
				new String[] { String.valueOf(horarioRemedio.getId()) });
		db.close();
	}

	public void AdicionarPrescricaoRemedio(PrescricaoRemedio prescricaoRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("usuario", prescricaoRemedio.getUsuario());
		cv.put("nome", prescricaoRemedio.getNomeRemedio());
		cv.put("qtDia", prescricaoRemedio.getQtdDias());
		cv.put("qtVezesDia", prescricaoRemedio.getQtdVezesDia());
		cv.put("dosagem", prescricaoRemedio.getDosagem());
		cv.put("idHorarioRemedio", prescricaoRemedio.getHorarioRemedio()
				.toString());
		db.insert("PrescricaoRemedio", null, cv);
		db.close();
	}

	public Cursor getPrescricaoRemedio() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("SELECT " + "id" + " as _id ," + "nome, "
				+ "usuario, " + "qtDias, " + "qtVezesDia, " + "dosagem, "
				+ "idHorarioRemedio" + " FROM " + "PrescricaoRemedio",
				new String[] {});
		return cur;
	}

	public int UpdatePrescricaoRemedio(PrescricaoRemedio prescricaoRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("usuario", prescricaoRemedio.getUsuario());
		cv.put("nome", prescricaoRemedio.getNomeRemedio());
		cv.put("qtDia", prescricaoRemedio.getQtdDias());
		cv.put("qtVezesDia", prescricaoRemedio.getQtdVezesDia());
		cv.put("dosagem", prescricaoRemedio.getDosagem());
		cv.put("idHorarioRemedio", prescricaoRemedio.getHorarioRemedio()
				.toString());
		return db.update("PrescricaoRemedio", cv, "id" + "=?",
				new String[] { String.valueOf(prescricaoRemedio.getId()) });
	}

	public void DeletePrescricaoRemedio(PrescricaoRemedio prescricaoRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("PrescricaoRemedio", "id" + "=?",
				new String[] { String.valueOf(prescricaoRemedio.getId()) });
		db.close();

	}

}
