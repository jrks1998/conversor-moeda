package conversor.projeto.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record Historico(String moedaBase, String moedaAlvo, double valor, double cotacao, String data, String hora) {

}
