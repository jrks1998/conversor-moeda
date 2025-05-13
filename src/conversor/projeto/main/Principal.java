package conversor.projeto.main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import conversor.projeto.exception.MenorQueZeroException;
import conversor.projeto.external.Cotacao;
import conversor.projeto.model.Historico;
import conversor.projeto.persistence.ArquivoHistorico;
import conversor.projeto.service.ConversorMoeda;

public class Principal {
	public static void main(String[] args) throws IOException, InterruptedException, MenorQueZeroException {
		menu();
	}
	
	private static void menu() throws IOException, InterruptedException {
		ArquivoHistorico arquivoHistorico = new ArquivoHistorico();
		int opc = 0;
		opcoesMenu();
		Scanner s = new Scanner(System.in);
		opc = s.nextInt();
		while(opc < 1 || opc > 7) {
			if (opc == 0) {
				arquivoHistorico.exluirArquivo();
				System.out.println("SAINDO");
				break;
			}
			System.out.println("Opção inválida. Selecione uma opção entre 1 e 6");
			opcoesMenu();
			opc = s.nextInt();
		}
		
		switch(opc) {
		case 1:
			consulta("ARS", "BRL");
			break;
			
		case 2:
			consulta("BRL", "ARS");
			break;
			
		case 3:
			consulta("USD", "ARS");
			break;
			
		case 4:
			consulta("ARS", "USD");
			break;
			
		case 5:
			consulta("BRL", "USD");
			break;
			
		case 6:
			consulta("USD", "BRL");
			break;
			
		case 7:
			arquivoHistorico.lerDados();
			break;
			
		case 0:
			opc = 0;
			break;
		}
		
		if (opc != 0) {
			menu();
		}
	}
	
	private static void opcoesMenu() {
		System.out.println("CONVERSOR DE MOEDA\n");
		System.out.println("1 - PESO ARGENTINO -> REAL BRASILEIRO");
		System.out.println("2 - REAL BRASILEIRO -> PESO ARGENTINO");
		System.out.println("3 - DÓLAR -> PESO ARGENTINO");
		System.out.println("4 - PESO ARGENTINO -> DÓLAR");
		System.out.println("5 - REAL BRASILEIRO -> DÓLAR");
		System.out.println("6 - DÓLAR -> REAL BRASILEIRO");
		System.out.println("7 - EXIBIR HISTÓRICO DE CONVERSÕES");
		System.out.println("0 - SAIR\n");
		System.out.print("Escolha uma opção: ");
	}
	
	private static void consulta(final String moedaBase, final String moedaAlvo) throws IOException, InterruptedException {
		Cotacao cotacao = new Cotacao();
		ConversorMoeda conversor = new ConversorMoeda();
		double valor;
		Scanner s = new Scanner(System.in);
		System.out.print("Informe o valor em " + moedaBase + " que será convertido em " + moedaAlvo + ": ");
		valor = s.nextDouble();
		try {
			cotacao.setMoedaBase(moedaBase);
			cotacao.setMoedaAlvo(moedaAlvo);
			cotacao.setCotacao();
			conversor.Conversor(valor, cotacao);
		} catch (MenorQueZeroException e) {
			System.out.println(e.getMessage());
		}
	}
}
