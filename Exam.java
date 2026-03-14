package id_211939947;

import java.util.Arrays;

import id_211939947.Question.Level;

public abstract class Exam implements Examable {
	private Question[] questions;

	public Exam(int numOfQuestions) {
		this.questions = new Question[numOfQuestions];
	}
	
	

	public Question[] getQuestions() {
		return questions;
	}

	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}

	public String toString() {
		int questionNumber = 1;
		StringBuffer str = new StringBuffer();
		str.append("--- Exam: ---\n");
		for (int i = 0; i < questions.length; i++) {
			if (questions[i] != null) {
				str.append(questionNumber + ") " + questions[i].toString() + "\n");
				questionNumber++;
			}
		}

		return str.toString();
	}

	public void removeMultiQuestion(MultiQuestion question) {// remove Multi Question
		for (int i = 0; i < questions.length; i++) {
			if (questions[i].getBody() == question.getBody()) {
				((MultiQuestion) questions[i]).removeAllAnswers();
				questions[i] = null;
			}
		}
	}

	public void removeOpenQuestion(OpenQuestion question) {// remove Open Question
		for (int i = 0; i < questions.length; i++) {
			if (questions[i].getBody() == question.getBody()) {
				questions[i] = null;
			}
		}
	}

	public void addMultiQuestion(MultiQuestion question, Level level) { // add Multi Question
		int nextEmptyIndex = nextEmptyIndexInQuestionArray();
		if (nextEmptyIndex != -1) {
			questions[nextEmptyIndex] = new MultiQuestion(question);
		} else {
			Question newQuestions[] = Arrays.copyOf(questions, questions.length + 1);
			newQuestions[newQuestions.length - 1] = new MultiQuestion(question);
			setQuestions(newQuestions);
			question.setLevel(level);
		}
	}

	public void addOpenQuestion(OpenQuestion question, Level level) { // add Open Question
		int nextEmptyIndex = nextEmptyIndexInQuestionArray();
		if (nextEmptyIndex != -1) {
			questions[nextEmptyIndex] = new OpenQuestion(question);
		} else {
			Question newQuestions[] = Arrays.copyOf(questions, questions.length + 1);
			newQuestions[newQuestions.length - 1] = new OpenQuestion(question);
			setQuestions(newQuestions);
			question.setLevel(level);
		}
	}

	public int nextEmptyIndexInQuestionArray() {
		for (int i = 0; i < questions.length; i++) {
			if (questions[i] == null) {
				return i;
			}
		}

		return -1;
	}

}
