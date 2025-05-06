package conversor.projeto.service;

import java.io.IOException;

import conversor.projeto.exception.MenorQueZeroException;
import conversor.projeto.external.Cotacao;

public class ConversorMoeda {
	public double Conversor(double valor, String moedaBase, String moedaAlvo) throws IOException, InterruptedException, MenorQueZeroException {
		if (valor < 0) {
			throw new MenorQueZeroException("valor menor que zero não permitdo");
		}
		else if (valor == 0) {
			return 0;
		} else {
			Cotacao cotacao = new Cotacao();
			return valor * cotacao.consultaCotacao(moedaBase, moedaAlvo);
		}
	}
}
