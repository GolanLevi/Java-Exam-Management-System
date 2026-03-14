package id_211939947;

public class OpenQuestion extends Question {
	private String answer;

	public OpenQuestion(String body,Level level, String answer) {
		super(body, level);
		this.answer = answer;
	}

	public OpenQuestion(OpenQuestion otherQuestion) { // copy constructor.
		super(otherQuestion.getBody(), otherQuestion.getLevel());
		this.answer = otherQuestion.getAnswer();
	}
	
	public OpenQuestion(String body, String answer) {
		super(body);
		this.answer = answer;
	}


	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String toString() {
		return getBody() + "\n# School answer: " + getAnswer() + "\n^ Question level: " + getLevel().name() + "\n";
	}

}
