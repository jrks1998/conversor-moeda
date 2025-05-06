package conversor.projeto.exception;

public class MenorQueZeroException extends Exception {
	private String message;
	
	public MenorQueZeroException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
