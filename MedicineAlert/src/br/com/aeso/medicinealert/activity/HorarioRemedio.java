package br.com.aeso.medicinealert.activity;

import java.sql.Date;
import java.sql.Time;

public class HorarioRemedio {

	Date data;
	Time hora;

	public HorarioRemedio() {
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
