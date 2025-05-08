package conversor.projeto.main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import conversor.projeto.exception.MenorQueZeroException;
import conversor.projeto.model.Historico;
import conversor.projeto.persistence.ArquivoHistorico;
import conversor.projeto.service.ConversorMoeda;

public class Principal {
	public static void main(String[] args) throws IOException, InterruptedException, MenorQueZeroException {
		menu();
	}
	
	private static void menu() throws IOException, InterruptedException, MenorQueZeroException {
		ConversorMoeda conversor = new ConversorMoeda();
		ArquivoHistorico arquivoHistorico = new ArquivoHistorico();
		int opc = 0;
		double valor = 0;
		opcoesMenu();
		Scanner s = new Scanner(System.in);
		opc = s.nextInt();
		while(opc < 1 || opc > 6) {
			System.out.println("Opção inválida. Selecione uma opção entre 1 e 6");
			opcoesMenu();
			opc = s.nextInt();
		}
		
		switch(opc) {
		case 1:
			System.out.print("Informe o valor em peso argentino que será convertido em real brasileiro: ");
			valor = s.nextDouble();
			s.close();
			try {
				double valorConvertido = conversor.Conversor(valor, "ARS", "BRL");
				System.out.println(valor  + " ARS = " + valorConvertido + " BRL");
				Historico historico = new Historico("ARS", "BRL", valor, valorConvertido / valor, LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy")), LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				arquivoHistorico.gravaDados(historico);
			} catch (MenorQueZeroException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 2:
			System.out.print("Informe o valor em real que será convertido em peso argentino: ");
			valor = s.nextDouble();
			s.close();
			try {
				System.out.println(valor  + " BRL = " + conversor.Conversor(valor, "BRL", "ARS") + " ARS");
			} catch (MenorQueZeroException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 3:
			System.out.print("Informe o valor em dólar que será convertido em peso argentino: ");
			valor = s.nextDouble();
			s.close();
			try {
				System.out.println(valor  + " USD = " + conversor.Conversor(valor, "USD", "ARS") + " ARS");
			} catch (MenorQueZeroException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 4:
			System.out.print("Informe o valor em peso argentino que será convertido em dólar: ");
			valor = s.nextDouble();
			s.close();
			try {
				System.out.println(valor  + " ARS = " + conversor.Conversor(valor, "ARS", "USD") + " USD");
			} catch (MenorQueZeroException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 5:
			System.out.print("Informe o valor em real brasileiro que será convertido em dólar: ");
			valor = s.nextDouble();
			s.close();
			try {
				System.out.println(valor  + " BRL = " + conversor.Conversor(valor, "BRL", "USD") + " USD");
			} catch (MenorQueZeroException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 6:
			System.out.print("Informe o valor em dólar que será convertido em real brasileiro: ");
			valor = s.nextDouble();
			s.close();
			try {
				System.out.println(valor  + " USD = " + conversor.Conversor(valor, "USD", "BRL") + " BRL");
			} catch (MenorQueZeroException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
	}
	
	private static void opcoesMenu() {
		System.out.println("CONVERSOR DE MOEDA\n");
		System.out.println("1 - PESO ARGENTINO -> REAL BRASILEIRO");
		System.out.println("2 - REAL BRASILEIRO -> PESO ARGENTINO");
		System.out.println("3 - DÓLAR -> PESO ARGENTINO");
		System.out.println("4 - PESO ARGENTINO -> DÓLAR");
		System.out.println("5 - REAL BRASILEIRO -> DÓLAR");
		System.out.println("6 - DÓLAR -> REAL BRASILEIRO\n");
		System.out.print("Escolha uma opção: ");
	}
}
