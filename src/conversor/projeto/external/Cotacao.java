package conversor.projeto.external;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Cotacao {
	String moedaBase;
	String moedaAlvo;
	String cotacao;

	public void setMoedaBase(String moedaBase) {
		this.moedaBase = moedaBase;
	}
	
	public void setMoedaAlvo(String moedaAlvo) {
		this.moedaAlvo = moedaAlvo;
	}
	
	public String getMoedaBase() {
		return moedaBase;
	}
	
	public String getMoedaAlvo() {
		return moedaAlvo;
	}
	
	public void setCotacao() throws IOException, InterruptedException {
		String url = "https://v6.exchangerate-api.com/v6/565dc70372fb03c9b123ee89/latest/" + moedaBase;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
		
		JsonObject obj = JsonParser.parseString(resp.body()).getAsJsonObject().getAsJsonObject("conversion_rates");
		cotacao = obj.get(moedaAlvo).getAsString();
	}
	
	public String getCotacao() {
		return cotacao;
	}
}
