package conversor.projeto.external;

import java.io.IOException;

public interface ExchangeRateApi {
	public double consultaCotacao(String moedaBase, String moedaAlvo) throws IOException, InterruptedException;
}
