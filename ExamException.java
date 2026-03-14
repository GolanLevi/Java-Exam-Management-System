package id_211939947;

public class ExamException extends Exception{
	
	public ExamException(String msg) {
		super(msg);
	}
	
	public ExamException() {
		super("Manually Error!");
	}
	
	public String toString() {
		return "Manually Error!";
	}
}
