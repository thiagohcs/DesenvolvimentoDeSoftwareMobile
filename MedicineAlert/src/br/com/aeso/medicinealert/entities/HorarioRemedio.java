package br.com.aeso.medicinealert.entities;

import java.sql.Date;
import java.sql.Time;

public class HorarioRemedio {

	int id; 
	Date data;
	Time hora;

	public HorarioRemedio() {
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public void cadastarHorario(Date data, Time hora) {

	}

}
