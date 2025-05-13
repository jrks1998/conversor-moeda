package conversor.projeto.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import conversor.projeto.exception.MenorQueZeroException;
import conversor.projeto.external.Cotacao;
import conversor.projeto.model.Historico;
import conversor.projeto.persistence.ArquivoHistorico;

public class ConversorMoeda {
	public void Conversor(double valor, Cotacao c) throws IOException, InterruptedException, MenorQueZeroException {
		if (valor < 0) {
			throw new MenorQueZeroException("valor menor que zero não permitdo");
		} else {
			c.setCotacao();
			if (valor == 0) {
				System.out.println("0 " + c.getMoedaBase() + " = 0" + c.getMoedaAlvo());
			} else {
				double valorConvertido = valor * Double.valueOf(c.getCotacao());
				System.out.println(valor + " " + c.getMoedaBase() + " = " + valorConvertido + " " + c.getMoedaAlvo());
			}
			ArquivoHistorico arquivoHistorico = new ArquivoHistorico();
			Historico historico = new Historico(c.getMoedaBase(), c.getMoedaAlvo(), valor, c.getCotacao(), valor * Double.valueOf(c.getCotacao()), LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yy")), LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			arquivoHistorico.gravarDados(historico);
		}
	}
}
