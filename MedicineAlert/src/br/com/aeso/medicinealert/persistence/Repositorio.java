package br.com.aeso.medicinealert.persistence;

import br.com.aeso.medicinealert.entities.PrescricaoRemedio;

public class Repositorio implements IRepositorio{
	
	private static Repositorio instancia = null;
	
	private Repositorio() {}
	
	static Repositorio getInstancia(){
		if(instancia == null){
			instancia = new Repositorio();
			return instancia;
		}
		return instancia;
	}
	
	@Override
	public void cadastrarPrescricaoRemedio(PrescricaoRemedio prescRemedio) {
		
		
	}

	@Override
	public PrescricaoRemedio consultarPrescricaoRemedio(
			PrescricaoRemedio nomeRemedio) {
		
		return null;
	}

	@Override
	public void removerPrescricaoRemedio(PrescricaoRemedio nomeRemedio) {
		
		
	}



}
