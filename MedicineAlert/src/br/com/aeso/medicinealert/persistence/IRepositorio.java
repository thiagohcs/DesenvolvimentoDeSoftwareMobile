package br.com.aeso.medicinealert.persistence;

import br.com.aeso.medicinealert.entities.PrescricaoRemedio;

public interface IRepositorio {
	
	void cadastrarPrescricaoRemedio(PrescricaoRemedio prescRemedio);
	PrescricaoRemedio consultarPrescricaoRemedio(PrescricaoRemedio nomeRemedio);
	void removerPrescricaoRemedio(PrescricaoRemedio nomeRemedio); 
}
