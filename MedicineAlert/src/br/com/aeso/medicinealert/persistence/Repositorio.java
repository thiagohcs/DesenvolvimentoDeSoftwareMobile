package br.com.aeso.medicinealert.persistence;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class Repositorio {

	static final String NOME_TABELA = "PrescricaoRemedio";
	private static final String INSERT = "INSERT INTO " + NOME_TABELA
			+ "(usuario, nome_remedio, qtd_Dias, qtd_Vezes_Dias, tipo_dosagem"
			+ "dosagem, data_inicio, hora_inicio) values(?,?,?,?,?,?,?,?)";
	private Context context;
	private SQLiteDatabase db;
	private SQLiteStatement insertStmt;
	static final String ID = "id";
	static final String NOME_REMEDIO = "nome_remedio";
	static final String USUARIO = "usuario";
	static final String QTD_DIAS = "qtd_Dias";
	static final String QTD_VEZES_DIAS = "qtd_Vezes_Dias";
	static final String TIPO_DOSAGEM = "tipo_dosagem";
	static final String DOSAGEM = "dosagem";
	static final String DATA_INICIO = "data_inicio";
	static final String HORA_INICIO = "hora_inicio";

	public Repositorio(Context context) {
		this.context = context;
		RepositorioGenerico rep = new RepositorioGenerico(this.context);
		this.db = rep.getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
	}

	public void inserir(String usuario, String nomeRemedio, int qtdDias,
			int qtdVezesDias, String tipoDosagem, float dosagem,
			String dtInicio, String hrInicio) {
		
		this.insertStmt.bindString(1, usuario);
		this.insertStmt.bindString(2, nomeRemedio);
		this.insertStmt.bindLong(3, qtdDias);
		this.insertStmt.bindLong(4, qtdVezesDias);
		this.insertStmt.bindString(5, tipoDosagem);
		this.insertStmt.bindDouble(6, dosagem);
		this.insertStmt.bindString(7, dtInicio);
		this.insertStmt.bindString(8, hrInicio);
		this.insertStmt.executeInsert();
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
