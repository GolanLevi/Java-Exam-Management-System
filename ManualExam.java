package id_211939947;

import java.util.Scanner;

public class ManualExam extends Exam {
	private static Scanner s = new Scanner(System.in);

	public ManualExam(int numOfQuestions) {
		super(numOfQuestions);
	}

	@Override
	public Exam createExam(Question[] questions) {
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
		Exam exam = new ManualExam(numOfQuestionsInExam);
		return exam;
	}
}
