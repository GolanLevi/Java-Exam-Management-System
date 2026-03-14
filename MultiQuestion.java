package id_211939947;
import java.util.Arrays;

public class MultiQuestion extends Question {
	private Answer[] answers;
	private int numOfCorrectAnswers;

	public MultiQuestion(String body,Level level, int numOfAnswers) {
		super(body, level);
		this.answers = new Answer[numOfAnswers + 2]; // 2 default answers: "More than one correct answer" and "There are
		// no correct answers".
		numOfCorrectAnswers = 0;
		addDefaultAnswers();
	}
	
	public MultiQuestion(MultiQuestion otherQuestion) { // copy constructor.
		super(otherQuestion.getBody(), otherQuestion.getLevel());
		this.answers = otherQuestion.getAnswers();
		this.numOfCorrectAnswers = otherQuestion.getNumOfCorrectAnswers();
	}

	public void addDefaultAnswers() {
		Answer moreThanOneCorrectAnswer = new Answer("More than one correct answer");
		Answer noCorrectAnswers = new Answer("There are no correct answers");
		this.answers[0] = moreThanOneCorrectAnswer;
		this.answers[0].setIsCorrect(false);
		this.answers[1] = noCorrectAnswers;
		this.answers[1].setIsCorrect(true);
	}

	public void updateDefaultAnswers() {
		if (numOfCorrectAnswers > 1) {
			this.answers[0].setIsCorrect(true);
			this.answers[1].setIsCorrect(false);
		} else if (numOfCorrectAnswers < 1) {
			this.answers[0].setIsCorrect(false);
			this.answers[1].setIsCorrect(true);
		} else {
			this.answers[0].setIsCorrect(false);
			this.answers[1].setIsCorrect(false);
		}
	}

	public Answer[] getAnswers() {
		return answers;
	}

	public int getNumOfCorrectAnswers() {
		return numOfCorrectAnswers;
	}

	public void setNumOfCorrectAnswers(int numOfCorrectAnswers) {
		this.numOfCorrectAnswers = numOfCorrectAnswers;
	}

	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}

	public String toString() {
		int answerNumber = 1;
		StringBuffer str = new StringBuffer();
		str.append(getBody() + "\n");
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] != null) {
				str.append(answerNumber + ". " + answers[i].toString() + "\n");
				answerNumber++;
			}
		}
		
		str.append("@ Question level: " + getLevel().name() + "\n");
		return str.toString();
	}

	public String answersToString() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] != null) {
				str.append((char) (i + 97) + ". " + answers[i].toString() + "\n");
			}
		}

		return str.toString();
	}

	public void removeAnswer(Answer answer) {
		for (int i = 0; i < answers.length; i++) {
			if (answers[i].getBody() == answer.getBody()) {
				if (answers[i].getIsCorrect() == true) {
					numOfCorrectAnswers--;
				}
				updateDefaultAnswers();
				answers[i] = null;
			}
		}
	}

	public void removeAllAnswers() {
		for (int i = 0; i < answers.length; i++) {
			answers[i] = null;
		}
	}

	public void addAnswer(Answer answer, boolean isCorrect) {
		int nextEmptyIndex = nextEmptyIndexInAnswerArray();
		if (nextEmptyIndex != -1) {
			answers[nextEmptyIndex] = new Answer(answer);
			answers[nextEmptyIndex].setIsCorrect(isCorrect);
		} else {
			Answer newAnswers[] = Arrays.copyOf(answers, answers.length + 1);
			newAnswers[newAnswers.length - 1] = new Answer(answer);
			newAnswers[newAnswers.length - 1].setIsCorrect(isCorrect);
			setAnswers(newAnswers);
		}

		if (isCorrect == true) {
			numOfCorrectAnswers++;
		}

		updateDefaultAnswers();
	}

	public int nextEmptyIndexInAnswerArray() {
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == null) {
				return i;
			}
		}

		return -1;
	}
}
