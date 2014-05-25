package br.com.aeso.medicinealert.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class PrescricaoRemedio {

	int id;
	String usuario;
	Date dtInicio;
	Integer qtdDias;
	Integer qtdVezesDia;
	String dosagem;
	String nomeRemedio;
	HorarioRemedio horarioRemedio;
	List<Time> listaHorarioRemedio;

	public PrescricaoRemedio() {
	}

	public HorarioRemedio getHorarioRemedio() {
		return horarioRemedio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHorarioRemedio(HorarioRemedio horarioRemedio) {
		this.horarioRemedio = horarioRemedio;
	}

	public void setQtdDias(Integer qtdDias) {
		this.qtdDias = qtdDias;
	}

	public void setQtdVezesDia(Integer qtdVezesDia) {
		this.qtdVezesDia = qtdVezesDia;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public String getDosagem() {
		return dosagem;
	}

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

	public String getNomeRemedio() {
		return nomeRemedio;
	}

	public void setNomeRemedio(String nomeRemedio) {
		this.nomeRemedio = nomeRemedio;
	}

	public List<Time> getListaHorarioRemedio() {
		return listaHorarioRemedio;
	}

	public void setListaHorarioRemedio(List<Time> listaHorarioRemedio) {
		this.listaHorarioRemedio = listaHorarioRemedio;
	}

	public List<Time> sugerirHorario() {
		return listaHorarioRemedio;
	}

}
