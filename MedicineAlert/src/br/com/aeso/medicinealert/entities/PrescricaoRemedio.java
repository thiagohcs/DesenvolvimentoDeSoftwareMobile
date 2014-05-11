package br.com.aeso.medicinealert.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class PrescricaoRemedio {
	
	String usuario;
	Date dtInicio;
	Integer qtdDias;
	Integer qtdVezesDia;
	Float dosagem;
	String nomeRemedio;
	List listaHorarioRemedio;
	
	public PrescricaoRemedio(){}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public int getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(int qtdDias) {
		this.qtdDias = qtdDias;
	}

	public int getQtdVezesDia() {
		return qtdVezesDia;
	}

	public void setQtdVezesDia(int qtdVezesDia) {
		this.qtdVezesDia = qtdVezesDia;
	}

	public float getDosagem() {
		return dosagem;
	}

	public void setDosagem(float dosagem) {
		this.dosagem = dosagem;
	}

	public String getNomeRemedio() {
		return nomeRemedio;
	}

	public void setNomeRemedio(String nomeRemedio) {
		this.nomeRemedio = nomeRemedio;
	}

	public List getListaHorarioRemedio() {
		return listaHorarioRemedio;
	}

	public void setListaHorarioRemedio(List listaHorarioRemedio) {
		this.listaHorarioRemedio = listaHorarioRemedio;
	}
	
	public List<Time> sugerirHorario(){
		return listaHorarioRemedio;
	}
	
	
	
	
}
