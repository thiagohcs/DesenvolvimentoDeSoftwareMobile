package br.com.aeso.medicinealert.persistence;

import br.com.aeso.medicinealert.entities.HorarioRemedio;
import br.com.aeso.medicinealert.entities.PrescricaoRemedio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Repositorio extends SQLiteOpenHelper {

	private static final String nomeBd = "medicineAlert";
	private static final int versaoBd = 20;
	private static final String TB_HorarioRemedio = "HorarioRemedio";
	private static final String TB_PrescricaoMedica = "PrescricaoMedica";
	private static final String HorarioRemedio_ID = "id";
	private static final String PrescricaoMedica_ID = "id";
	private static final String NOME = "nome";
	private static final String USUARIO = "usuario";
	private static final String QTD_DIAS = "qtDias";
	private static final String QTD_VEZES_DIAS = "qtdVezesDias";
	private static final String DOSAGEM = "dosagem";
	private static final String DATA = "data";
	private static final String HORA = "hora";
	

	public Repositorio(Context context) {
		super(context, nomeBd, null, versaoBd);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TB_HorarioRemedio
				+ "("+HorarioRemedio_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + DATA
				+ "DATE, " + HORA + "TIME)");

		db.execSQL("CREATE TABLE " + TB_PrescricaoMedica + "("+PrescricaoMedica_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + "TEXT, "
				+ USUARIO + "TEXT, " + QTD_DIAS + " INTEGER, " + QTD_VEZES_DIAS
				+ " INTEGER, " + DOSAGEM
				+ " FLOAT, "
				+
				// "remedio"+ " TEXT, "+
				PrescricaoMedica_ID + "INTEGER);"
				+ "FOREIGN KEY(id) REFERENCES " + TB_HorarioRemedio + " ("
				+ PrescricaoMedica_ID + ");");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TB_HorarioRemedio);
		db.execSQL("DROP TABLE IF EXISTS " + TB_PrescricaoMedica);
	}

	public void AdicionarHoraDataInicio(HorarioRemedio horarioRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DATA, horarioRemedio.getData().toString());
		cv.put(HORA, horarioRemedio.getHora().toString());
		db.insert(TB_HorarioRemedio, null, cv);
		db.close();
	}

	public Cursor getAllHorarioRemedio() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("SELECT " + HorarioRemedio_ID + " as _id, " + DATA+", "
				+ HORA + " from " + TB_HorarioRemedio, new String[] {});
		return cur;
	}

	public int UpdateHorarioRemedio(HorarioRemedio horarioRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DATA, horarioRemedio.getData().toString());
		cv.put(HORA, horarioRemedio.getHora().toString());
		return db.update(TB_HorarioRemedio, cv, HorarioRemedio_ID + " =?",
				new String[] { String.valueOf(horarioRemedio.getId()) });

	}

	public void DeleteHorarioRemedio(HorarioRemedio horarioRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TB_HorarioRemedio, HorarioRemedio_ID + "=?",
				new String[] { String.valueOf(horarioRemedio.getId()) });
		db.close();
	}

	public void AdicionarPrescricaoRemedio(PrescricaoRemedio prescricaoRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(USUARIO, prescricaoRemedio.getUsuario());
		cv.put(NOME, prescricaoRemedio.getNomeRemedio());
		cv.put(QTD_DIAS, prescricaoRemedio.getQtdDias());
		cv.put(QTD_VEZES_DIAS, prescricaoRemedio.getQtdVezesDia());
		cv.put(DOSAGEM, prescricaoRemedio.getDosagem());
		cv.put(PrescricaoMedica_ID, prescricaoRemedio.getHorarioRemedio()
				.toString());
		db.insert(TB_PrescricaoMedica, null, cv);
		db.close();
	}

	public Cursor getPrescricaoRemedio() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("SELECT " + PrescricaoMedica_ID + " as _id ," + PrescricaoMedica_ID+", "
				+ USUARIO+", " + QTD_DIAS+", " + QTD_VEZES_DIAS+", " + DOSAGEM+", "
				+ HorarioRemedio_ID + " FROM " + TB_PrescricaoMedica,
				new String[] {});
		return cur;
	}

	public int UpdatePrescricaoRemedio(PrescricaoRemedio prescricaoRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(USUARIO, prescricaoRemedio.getUsuario());
		cv.put(NOME, prescricaoRemedio.getNomeRemedio());
		cv.put(QTD_DIAS, prescricaoRemedio.getQtdDias());
		cv.put(QTD_VEZES_DIAS, prescricaoRemedio.getQtdVezesDia());
		cv.put(DOSAGEM, prescricaoRemedio.getDosagem());
		cv.put(HorarioRemedio_ID, prescricaoRemedio.getHorarioRemedio()
				.toString());
		return db.update(TB_PrescricaoMedica, cv, PrescricaoMedica_ID + "=?",
				new String[] { String.valueOf(prescricaoRemedio.getId()) });
	}

	public void DeletePrescricaoRemedio(PrescricaoRemedio prescricaoRemedio) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TB_PrescricaoMedica, PrescricaoMedica_ID + "=?",
				new String[] { String.valueOf(prescricaoRemedio.getId()) });
		db.close();

	}

}
