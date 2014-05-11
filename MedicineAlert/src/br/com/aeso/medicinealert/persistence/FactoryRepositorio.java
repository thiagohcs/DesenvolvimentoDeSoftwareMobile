package br.com.aeso.medicinealert.persistence;

public class FactoryRepositorio {
	
	private FactoryRepositorio(){}
	
	public static IRepositorio obterRepositorio(){
		return Repositorio.getInstancia();
	}
}
