package br.com.aeso.medicinealert.business;

import br.com.aeso.medicinealert.entities.PrescricaoRemedio;
import br.com.aeso.medicinealert.exception.ExcecaoRegraDeNegocio;
import br.com.aeso.medicinealert.persistence.FactoryRepositorio;
import br.com.aeso.medicinealert.persistence.IRepositorio;

public class Controlador {

	private static Controlador instanciaUnica = null;
	private IRepositorio repositorio;

	public static Controlador getInstanciaUnica() {
		if (instanciaUnica == null) {
			instanciaUnica = new Controlador();
		}
		return instanciaUnica;
	}

	private Controlador() {
		repositorio = FactoryRepositorio.obterRepositorio();
	}

	private void validador(PrescricaoRemedio pRemedio)
			throws ExcecaoRegraDeNegocio {
		if (pRemedio.getUsuario().trim().equals("")
				|| pRemedio.getUsuario().trim().equals(null)) {
			throw new ExcecaoRegraDeNegocio(
					"O nome do usuario não foi informado");
		}
		if (pRemedio.getNomeRemedio().trim().equals("")
				|| pRemedio.getNomeRemedio().trim().equals(null)) {
			throw new ExcecaoRegraDeNegocio(
					"O nome do remédio não foi informado");
		}
//		if (pRemedio.getDosagem().toString().trim().equals("")
//				|| pRemedio.getDosagem().toString().trim().equals(null)) {
//			throw new ExcecaoRegraDeNegocio(
//					"A dosagem do remédio não foi informada");
//		}
//		if (pRemedio.getQtdDias().toString().trim().equals("")
//				|| pRemedio.getQtdDias().toString().trim().equals(null)) {
//			throw new ExcecaoRegraDeNegocio(
//					"A quantidade de dias não foi informado");
//		}
//		if (pRemedio.getQtdVezesDia().toString().trim().equals("")
//				|| pRemedio.getQtdVezesDia().toString().trim().equals(null)) {
//			throw new ExcecaoRegraDeNegocio(
//					"A quantidade de vezes ao dia não foi informado");
//		}
		if (pRemedio.getDtInicio().toString().trim().equals("")
				|| pRemedio.getDtInicio().toString().trim().equals(null)) {
			throw new ExcecaoRegraDeNegocio(
					"A data de inicio não foi informado");
		}
	}

}
