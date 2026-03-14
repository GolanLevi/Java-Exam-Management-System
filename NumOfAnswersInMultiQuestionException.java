package id_211939947;

public class NumOfAnswersInMultiQuestionException extends ExamException {

	public NumOfAnswersInMultiQuestionException() {
		super("The num of answers must be greater than 3");
	}
	
	public String toString() {
		return "The num of answers must be greater than 3";
	}
}
