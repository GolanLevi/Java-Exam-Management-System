package id_211939947;

import java.util.Scanner;

public class AutomaticExam extends Exam {
	private static Scanner s = new Scanner(System.in);

	public AutomaticExam(int numOfQuestions) {
		super(numOfQuestions);
	}

	public Exam createExam(Question[] stockOfQuestions) {
		int numOfQuestionsInExam = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.println("How many questions will be in the exam?");
			numOfQuestionsInExam = s.nextInt();
			s.nextLine();
			try {
				if (numOfQuestionsInExam > 10) {
					throw new NumOfQuestionsException();
				}
				isValid = true;
			} catch (NumOfQuestionsException e) {
				System.out.println(e.toString());
			}
		}

		Exam exam = new AutomaticExam(numOfQuestionsInExam);
		return exam;

	}
}
