package id_211939947;

import java.io.Serializable;

public class Answer implements Serializable {
	private String body;
	private boolean isCorrect = false;

	public Answer(String body) {
		this.body = body;
	}

	public Answer(Answer otherAnswer) { // copy constructor.
		this.body = otherAnswer.getBody();
		this.isCorrect = otherAnswer.getIsCorrect();
	}

	public Answer(String body, boolean bool) { // only Open Question
		this.body = body;
		this.isCorrect = true;
	}

	public String getBody() {
		return body;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String toString() {
		String isCorrectStr = getIsCorrect() ? "Correct" : "Incorrect";
		return body + " (" + isCorrectStr + ") ";
	}
}
