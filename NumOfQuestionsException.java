package id_211939947;

public class NumOfQuestionsException extends ExamException {
	
	public NumOfQuestionsException() {
		super("The num of questions must be between 1-10");
	}
	
	public String toString() {
		return "The num of questions must be between 1-10";
	}
}
