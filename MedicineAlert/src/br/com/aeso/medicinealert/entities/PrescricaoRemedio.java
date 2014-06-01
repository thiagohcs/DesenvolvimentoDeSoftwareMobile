package br.com.aeso.medicinealert.entities;


public class PrescricaoRemedio {

	int id;
	String usuario,nomeRemedio;
	String dtInicio, hrInicio;
	Integer qtdDias, qtdVezesDia;
	String tipoDosagem, dosagem;


	public PrescricaoRemedio() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getHrInicio() {
		return hrInicio;
	}

	public void setHrInicio(String hrInicio) {
		this.hrInicio = hrInicio;
	}

	public Integer getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(Integer qtdDias) {
		this.qtdDias = qtdDias;
	}

	public Integer getQtdVezesDia() {
		return qtdVezesDia;
	}

	public void setQtdVezesDia(Integer qtdVezesDia) {
		this.qtdVezesDia = qtdVezesDia;
	}

	public String getTipoDosagem() {
		return tipoDosagem;
	}

	public void setTipoDosagem(String tipoDosagem) {
		this.tipoDosagem = tipoDosagem;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public String getNomeRemedio() {
		return nomeRemedio;
	}

	public void setNomeRemedio(String nomeRemedio) {
		this.nomeRemedio = nomeRemedio;
	}
	
	
	
	
}
