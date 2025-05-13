package conversor.projeto.model;

public record Historico(String moedaBase, String moedaAlvo, double valor, String cotacao, double valorConvertido, String data, String hora) {
	@Override
	public String toString() {
		return "MOEDA BASE: " + moedaBase + "\n" +
			   "MOEDA ALVO: " + moedaAlvo + "\n" +
			   "VALOR: " + valor + "\n" +
			   "COTAÇÃO: " + cotacao + "\n" +
			   "VALOR CONVERTIDO: " + valorConvertido + "\n" +
			   "DATA: " + data + "\n" +
			   "HORA: " + hora + "\n";
	}
}
