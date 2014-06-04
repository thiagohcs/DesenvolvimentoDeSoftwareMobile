package br.com.aeso.medicinealert.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import br.com.aeso.medicinealert.entities.PrescricaoRemedio;

public class BDHelper {

	static final String NOME_TABELA = "PrescricaoRemedio";
//	private static final String INSERT = "INSERT INTO " + NOME_TABELA
//			+ "(usuario, nome_remedio, qtd_dias, qtd_vezes_dias, tipo_dosagem"
//			+ "dosagem, data_inicio, hora_inicio) values(?,?,?,?,?,?,?,?)";
	private SQLiteDatabase bd;
//	private SQLiteStatement insertStmt;
	
	static final String ID = "id";
//	static final String NOME_REMEDIO = "nome_remedio";
//	static final String USUARIO = "usuario";
//	static final String QTD_DIAS = "qtd_Dias";
//	static final String QTD_VEZES_DIAS = "qtd_Vezes_Dias";
//	static final String TIPO_DOSAGEM = "tipo_dosagem";
//	static final String DOSAGEM = "dosagem";
//	static final String DATA_INICIO = "data_inicio";
//	static final String HORA_INICIO = "hora_inicio";

	public BDHelper(Context context) {
		OpenHelper rep = new OpenHelper(context);
		this.bd = rep.getWritableDatabase();
	}

	public void inserir(PrescricaoRemedio remedio) {
		
		ContentValues valores = new ContentValues(); 
		valores.put("usuario", remedio.getUsuario());
		valores.put("nome_remedio", remedio.getNomeRemedio());
		valores.put("qtd_dias", remedio.getQtdDias());
		valores.put("qtd_vezes_dias", remedio.getQtdVezesDia());
		valores.put("tipo_dosagem", remedio.getTipoDosagem());
		valores.put("dosagem", remedio.getDosagem());
		valores.put("data_inicio", remedio.getDtInicio());
		valores.put("hora_inicio", remedio.getHrInicio());
		
		bd.insert(NOME_TABELA, null, valores);
		
//		this.insertStmt.bindString(1, remedio.getUsuario());
//		this.insertStmt.bindString(2, remedio.getNomeRemedio());
//		this.insertStmt.bindLong(3, remedio.getQtdDias());
//		this.insertStmt.bindLong(4, remedio.getQtdVezesDia());
//		this.insertStmt.bindString(5, remedio.getTipoDosagem());
//		this.insertStmt.bindLong(6, remedio.getDosagem());
//		this.insertStmt.bindString(7, remedio.getDtInicio());
//		this.insertStmt.bindString(8, remedio.getHrInicio());
//		this.insertStmt.executeInsert();
		Log.i("Inserido", "Remedio inserido");
	}

//	public List<String> selectAll() {
//		List<String> list = new ArrayList<String>();
//		Cursor cursor = this.db.query(NOME_TABELA, new String[] { "nome" },
//				null, null, null, null, "nome desc");
//		if (cursor.moveToFirst()) {
//			do {
//				list.add(cursor.getString(0));
//			} while (cursor.moveToNext());
//		}
//		if (cursor != null && !cursor.isClosed()) {
//			cursor.close();
//		}
//		return list;
//	}

}
