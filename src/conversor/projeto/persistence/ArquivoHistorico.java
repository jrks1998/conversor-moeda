package conversor.projeto.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import conversor.projeto.model.Historico;

public class ArquivoHistorico {
	private static final String ARQUIVO = "log.json";
	
	public void gravaDados(Historico historico) throws IOException {
		File arq = new File(ARQUIVO);
		arq.createNewFile();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = new FileWriter(arq, true);
		writer.write(gson.toJson(historico) + "\n");
		writer.close();
	}
}
