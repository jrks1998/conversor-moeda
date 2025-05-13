package conversor.projeto.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import conversor.projeto.model.Historico;

public class ArquivoHistorico {
	private static final String ARQUIVO = "log.json";
	private ArrayList<Historico> logs = new ArrayList<Historico>();
	private File ARQ = new File(ARQUIVO);
	
	public void gravarDados(Historico historico) throws IOException {
		ARQ.createNewFile();
		Gson gson = new Gson();
		FileWriter writer = new FileWriter(ARQ, true);
		writer.write(gson.toJson(historico) + "\n");
		writer.close();
	}
	
	public void lerDados() throws IOException {
		if (ARQ.exists()) {
			Gson gson = new Gson();
			BufferedReader reader = new BufferedReader(new FileReader(new File(ARQUIVO)));
			String linha;
			System.out.println("HISTÓRICO DE CONVERSÕES\n");
			while ((linha = reader.readLine()) != null) {
				Historico historico = gson.fromJson(linha, Historico.class);
				logs.add(historico);
			}
			reader.close();
			
			for (Historico log : logs) {
				System.out.println(log);
			}
		} else {
			System.out.println("SEM HISTÓRICO DE CONVERSÕES\n");
		}
	}
	
	public void exluirArquivo() {
		if (ARQ.exists()) {
			System.out.println("EXCLUINDO HISTÓRICO DE CONVERSÃO");
			ARQ.delete();
		}
	}
}
