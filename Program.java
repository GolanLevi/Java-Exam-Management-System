package id_211939947;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import id_211939947.Question.Level;

public class Program {

	private static Scanner s;
	private static Answer[] stockOfAnswers;
	private static Question[] stockOfQuestions;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		s = new Scanner(System.in);
		stockOfAnswers = createStockOfAnswers();

		File f = new File("StockOfQuestions.dat");
		if (!f.exists()) {
			stockOfQuestions = createStockOfQuestions();
			writeStockOfQuestionsIntoFile();
		} else {
			stockOfQuestions = readStockOfQuestionsFromFile();
		}

		System.out.println("press '1' to Manual exam, '2' to Automatic exam");
		int chosenExam = s.nextInt();
		Exam exam = null;
		chosenExam = checkSelectedExam(chosenExam);
		if (chosenExam == 1) {
			exam = new ManualExam(0);
			exam = exam.createExam(stockOfQuestions);
			addQuestionsToManualExam(exam);
		} else {
			exam = new AutomaticExam(0);
			exam = exam.createExam(stockOfQuestions);
			addQuestionsToAutomaticExam(exam);
		}

		final int EXIT = -1;
		int answer;

		do {
			System.out.println();
			System.out.println("Choose one of the following options:");
			System.out.println("1)  Print exam to console");
			System.out.println("2)  Print exam to file");
			System.out.println("3)  Print answers to file");
			System.out.println("4)  Add new question to exam");
			System.out.println("5)  Add answer to existing multiple choice question in exam");
			System.out.println("6)  Remove question from exam");
			System.out.println("7)  Remove answer from existing multiple choice question");
			System.out.println("8)  Write new question to Exam");
			System.out.println(EXIT + ") Exit");
			System.out.print("Enter your choice --> ");
			answer = s.nextInt();
			s.nextLine();

			switch (answer) {
				case 1:
					printExamToConsole(exam);
					break;
				case 2:
					printExamIntoFile(exam);
					break;
				case 3:
					printAnswersIntoFile(exam);
					break;
				case 4:
					addNewQuestionToExam(exam);
					break;
				case 5:
					addAnswerToExistingMultiQuestionInExam(exam);
					break;
				case 6:
					removeMultiQuestionFromExam(exam);
					break;
				case 7:
					removeAnswerFromExistingMultiQuestion(exam);
					break;
				case 8:
					writeNewQuestion(exam);
					break;
				case EXIT:
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
		} while (answer != EXIT);
	} // main

	private static Question[] readStockOfQuestionsFromFile()
			throws IOException, FileNotFoundException, ClassNotFoundException {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("StockOfQuestions.dat"));
		Question[] stockQuestionsFromFile = (Question[]) inFile.readObject();
		inFile.close();

		return stockQuestionsFromFile;
	}

	private static void writeStockOfQuestionsIntoFile() throws IOException, FileNotFoundException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("StockOfQuestions.dat"));
		outFile.writeObject(stockOfQuestions);
		outFile.close();
	}

	public static void printExamToConsole(Exam exam) {
		System.out.println("\n" + exam.toString());
	}

	// ***************************************************************************//

	public static void writeNewQuestion(Exam exam) {
		System.out.println("Enter '1' to add multiple choice question, '0' to open question: \n");
		int chosenQuestion = s.nextInt();
		if (chosenQuestion == 1) {
			System.out.println("Enter the question body: \n");
			String body = s.next();
			System.out.println("Enter the difficulty level of the question: \n");
			Level level = Level.valueOf(s.next());
			MultiQuestion newQuestion = new MultiQuestion(body, level, 0);
			addMultiQuestionToExam(exam, newQuestion, level);
		} else {
			System.out.println("Enter the question body: \n");
			String body = s.next();
			System.out.println("Enter the School answer: \n");
			String schoolAnswer = s.next();
			System.out.println("Enter the difficulty level of the question: \n");
			Level level = Level.valueOf(s.next());
			OpenQuestion newQuestion = new OpenQuestion(body, level, schoolAnswer);
			addOpenQuestionToExam(exam, newQuestion, level);

		}

	}

	// ***************************************************************************//

	public static void addMultiQuestionToExam(Exam exam, MultiQuestion question, Level level) { // add Multi Question to
																								// Stock
		int nextEmptyIndex = nextEmptyIndexInExam(exam);
		if (nextEmptyIndex != -1) {
			exam.getQuestions()[nextEmptyIndex] = new MultiQuestion(question);
		} else {
			Question newExamArray[] = Arrays.copyOf(exam.getQuestions(), exam.getQuestions().length + 1);
			exam.setQuestions(newExamArray);
			newExamArray[newExamArray.length - 1] = new MultiQuestion(question);
			question.setLevel(level);
		}
	}

	public static void addOpenQuestionToExam(Exam exam, OpenQuestion question, Level level) { // add Open Question to
																								// Stock
		int nextEmptyIndex = nextEmptyIndexInExam(exam);
		if (nextEmptyIndex != -1) {
			exam.getQuestions()[nextEmptyIndex] = new OpenQuestion(question);
		} else {
			Question newExamArray[] = Arrays.copyOf(exam.getQuestions(), exam.getQuestions().length + 1);
			exam.setQuestions(newExamArray);
			newExamArray[newExamArray.length - 1] = new OpenQuestion(question);
			question.setLevel(level);
		}
	}

	public static int nextEmptyIndexInExam(Exam exam) {
		for (int i = 0; i < exam.getQuestions().length; i++) {
			if (exam.getQuestions()[i] == null) {
				return i;
			}
		}

		return -1;
	}
	// ***************************************************************************//
	// only Multi

	public static void addAnswerToExistingMultiQuestionInExam(Exam exam) {
		System.out.println("\nWhich question would you like to change in your exam?");
		int questionIndex = s.nextInt();

		questionIndex = checkMultiQuestionIndex(questionIndex, exam);

		MultiQuestion selectedQuestion = (MultiQuestion) exam.getQuestions()[questionIndex - 1];
		int numOfAnswers = ((MultiQuestion) selectedQuestion).getAnswers().length;
		numOfAnswers = checkNumOfAnswers(numOfAnswers);

		System.out.println("\nWhich answer would you like to add?");
		printStockOfAnswers();

		int selectedAnswerIndex = s.nextInt();
		int numOfAnswersInStock = stockOfAnswers.length;
		selectedAnswerIndex = checkSelectedAnswer(selectedAnswerIndex, numOfAnswersInStock);

		Answer answer = new Answer(stockOfAnswers[selectedAnswerIndex - 1]);

		System.out.println("\nIf this is a correct answer, press '1', else press '0':");
		int isCorrectInt = s.nextInt();
		isCorrectInt = checkIsCorrectInput(isCorrectInt);

		boolean isCorrect = isCorrectInt == 1 ? true : false;

		selectedQuestion.addAnswer(answer, isCorrect);
	}

	// ***************************************************************************//

	public static int checkIsCorrectInput(int isCorrectInt) {
		while (isCorrectInt != 1 && isCorrectInt != 0) {
			System.out.println("\nWrong input, If this is a correct answer, press '1', else press '0':");
			isCorrectInt = s.nextInt();
		}
		return isCorrectInt;
	}

	// ***************************************************************************//

	public static int checkNumOfAnswers(int numOfAnswers) {
		while (numOfAnswers > 10) {
			System.out.println("\nThe number of the answer must be lower than 10, please enter again: \n");
			numOfAnswers = s.nextInt();
		}
		return numOfAnswers;
	}

	// ***************************************************************************//

	public static int checkSelectedAnswer(int selectedAnswerIndex, int MaxNumOfAnswers) {
		while (selectedAnswerIndex > MaxNumOfAnswers || selectedAnswerIndex < 1) {
			System.out.println(
					"\nThe number of the answer must be between 1 - " + MaxNumOfAnswers + " , please enter again: \n");
			selectedAnswerIndex = s.nextInt();
		}
		return selectedAnswerIndex;
	}

	// ***************************************************************************//

	public static int checkMultiQuestionIndex(int questionIndex, Exam exam) {
		int numOfQuestionsInExam = exam.getQuestions().length;
		while (questionIndex > numOfQuestionsInExam || questionIndex < 1
				|| exam.getQuestions()[questionIndex - 1] instanceof OpenQuestion) {
			if (questionIndex > numOfQuestionsInExam || questionIndex < 1) {
				System.out.println("\nThe number of the question must be between 1 - " + numOfQuestionsInExam
						+ " , please enter again: \n");
			} else {
				System.out.println("\nThe question must be multiple choice question,  please enter again: \n");
			}

			questionIndex = s.nextInt();

		}

		return questionIndex;
	}

	// ***************************************************************************//

	// ***************************************************************************//

	public static void addNewQuestionToExam(Exam exam)
			throws FileNotFoundException, ClassNotFoundException, IOException { // both
		System.out.println("\nWhich question would you like to add in your exam?");
		printStockOfQuestions();

		int questionIndex = s.nextInt();
		int numOfQuestionInStock = stockOfQuestions.length;
		questionIndex = checkSelectedQuestion(questionIndex, numOfQuestionInStock);

		if (stockOfQuestions[questionIndex - 1] instanceof OpenQuestion) {
			OpenQuestion question = new OpenQuestion((OpenQuestion) stockOfQuestions[questionIndex - 1]);
			exam.addOpenQuestion(question, question.getLevel());
		} else {
			MultiQuestion question = new MultiQuestion((MultiQuestion) stockOfQuestions[questionIndex - 1]);
			addAnswerToMultiQuestionInExam(question);
			exam.addMultiQuestion(question, question.getLevel());
		}
	}
	// ***************************************************************************//

	public static int checkSelectedQuestion(int questionIndex, int MaxNumOfQuestion) {
		while (questionIndex > MaxNumOfQuestion || questionIndex < 1) {
			System.out.println("\nThe number of the question must be between 1 - " + MaxNumOfQuestion
					+ " , please enter again: \n");
			questionIndex = s.nextInt();
		}
		return questionIndex;
	}

	// ***************************************************************************//

	// ***************************************************************************//
	// // only Multi!!

	public static void removeAnswerFromExistingMultiQuestion(Exam exam) {
		System.out.println("\nWhich question would you like to change in your exam?");
		int questionIndex = s.nextInt();

		questionIndex = checkMultiQuestionIndex(questionIndex, exam);
		MultiQuestion selectedQuestion = (MultiQuestion) exam.getQuestions()[questionIndex - 1];

		System.out.println("\nWhich answer would you like to remove?");
		int answerToRemoveIndex = s.nextInt();

		int numOfAnswersInSelectedQuestion = selectedQuestion.getAnswers().length;
		answerToRemoveIndex = checkSelectedAnswer(answerToRemoveIndex, numOfAnswersInSelectedQuestion);

		Answer answerToRemove = selectedQuestion.getAnswers()[answerToRemoveIndex - 1];
		selectedQuestion.removeAnswer(answerToRemove);
	}

	// ***************************************************************************//

	// ***************************************************************************//

	public static void removeMultiQuestionFromExam(Exam exam) {
		System.out.println("\nWhich question would you like to change in your exam?");
		int questionIndex = s.nextInt();
		int numOfQuestions = exam.getQuestions().length;

		questionIndex = checkSelectedQuestion(questionIndex, numOfQuestions);

		if (exam.getQuestions()[questionIndex - 1] instanceof OpenQuestion) {
			Question selectedQuestion = exam.getQuestions()[questionIndex - 1];
			exam.removeOpenQuestion((OpenQuestion) selectedQuestion);
		} else {
			Question selectedQuestion = exam.getQuestions()[questionIndex - 1];
			exam.removeMultiQuestion((MultiQuestion) selectedQuestion);
		}
	}

	// ***************************************************************************//

	// ***************************************************************************//

	public static String printAnswersToConsole(Exam exam) {
		Question[] questions = exam.getQuestions();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < questions.length; i++) {
			str.append((i + 1) + ") " + questions[i].getBody() + " - \n");
			if (questions[i] instanceof MultiQuestion) {
				str.append(((MultiQuestion) questions[i]).answersToString());
			} else {
				str.append(((OpenQuestion) questions[i]).getAnswer());
			}

		}
		System.out.println(str);
		return str.toString();
	}

	public static void printAnswersIntoFile(Exam exam) throws FileNotFoundException {
		String fileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm'.txt'").format(new Date());
		File file = new File("answers_" + fileName);
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.print(printAnswersToConsole(exam));
		printWriter.close();
	}

	public static void printExamIntoFile(Exam exam) throws FileNotFoundException {
		String fileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm'.txt'").format(new Date());
		File file = new File("exam_" + fileName);
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.print(exam.toString());
		printWriter.close();
	}

	// ***************************************************************************//

	// ***************************************************************************//

	public static Answer[] createStockOfAnswers() {
		Answer[] stockOfAnswers = new Answer[10];
		stockOfAnswers[0] = new Answer("Blue");
		stockOfAnswers[1] = new Answer("Red");
		stockOfAnswers[2] = new Answer("Pink");
		stockOfAnswers[3] = new Answer("Yellow");
		stockOfAnswers[4] = new Answer("Green");
		stockOfAnswers[5] = new Answer("28");
		stockOfAnswers[6] = new Answer("29");
		stockOfAnswers[7] = new Answer("32");
		stockOfAnswers[8] = new Answer("33");
		stockOfAnswers[9] = new Answer("77");

		return stockOfAnswers;
	}

	public static Question[] createStockOfQuestions() throws FileNotFoundException, IOException {
		Question[] stockOfQuestions = new Question[11];
		stockOfQuestions[0] = new MultiQuestion("What is the color of the sun? ", Level.Easy, 0);
		stockOfQuestions[1] = new MultiQuestion("What is the color of the sea?", Level.Hard, 0);
		stockOfQuestions[2] = new MultiQuestion("How many teeth does an adult have?", Level.Medium, 0);
		stockOfQuestions[3] = new MultiQuestion("How many days are there in February?", Level.Easy, 0);
		stockOfQuestions[4] = new OpenQuestion("How can our school be more environmentally friendly?", Level.Easy,
				"Recycling bins and natural energy");
		stockOfQuestions[5] = new OpenQuestion("In football, what is offside?", Level.Hard,
				"The player is closer to the opposing team's goal line than both the ball and the penultimate player of the opposing team.");
		stockOfQuestions[6] = new OpenQuestion("Where is the Bermuda Triangle?", Level.Medium,
				"The Bermuda Triangle is an area in the Atlantic Ocean");
		stockOfQuestions[7] = new MultiQuestion("77 - 44 = ?", Level.Hard, 0);
		stockOfQuestions[8] = new MultiQuestion("If you mix red and blue do you get?", Level.Easy, 0);
		stockOfQuestions[9] = new MultiQuestion("15 + 13 = ?", Level.Medium, 0);
		stockOfQuestions[10] = new MultiQuestion("If a kilo of tomatoes costs NIS 4, will 7 kilos cost?", Level.Hard,
				0);

		return stockOfQuestions;
	}

	// ***************************************************************************//

	public static void addQuestionsToManualExam(Exam exam)
			throws FileNotFoundException, ClassNotFoundException, IOException { // both multiQuestions and
																				// openQuestions.
		int numOfQuestions = exam.getQuestions().length;
		for (int i = 0; i < numOfQuestions; i++) {
			System.out.println("\nEnter the number of question " + (i + 1) + " of " + numOfQuestions + ":");
			printStockOfQuestions();

			int selectedQuestionIndex = s.nextInt();
			int numOfQuestionsInStock = stockOfQuestions.length;
			selectedQuestionIndex = checkSelectedQuestion(selectedQuestionIndex, numOfQuestionsInStock) - 1;

			if (stockOfQuestions[selectedQuestionIndex] instanceof MultiQuestion) {
				MultiQuestion question = new MultiQuestion((MultiQuestion) stockOfQuestions[selectedQuestionIndex]);
				addAnswerToMultiQuestionInExam(question);
				exam.addMultiQuestion(question, question.getLevel());
			} else {
				OpenQuestion question = new OpenQuestion((OpenQuestion) stockOfQuestions[selectedQuestionIndex]);
				exam.addOpenQuestion(question, question.getLevel());
			}
		}
	}

	// ***************************************************************************//

	public static void addQuestionsToAutomaticExam(Exam exam) {
		addRandomQuestionsToAutomaticExam(exam);
		addRandomAnswersToMultiQuestionsInAutomaticExam(exam);
	}

	private static void addRandomAnswersToMultiQuestionsInAutomaticExam(Exam exam) {
		Random r = new Random();
		Question[] questionsInExam = exam.getQuestions();
		for (Question question : questionsInExam) {
			if (question instanceof MultiQuestion) {
				// Adds 4 answers to a question
				for (int j = 0; j < 4; j++) { // Only 4 answers are required
					int selectedAnswerIndex = r.nextInt(stockOfAnswers.length);
					Answer answer = new Answer(stockOfAnswers[selectedAnswerIndex]);
					((MultiQuestion) question).addAnswer(answer, answer.getIsCorrect());
				}

				// Sets one answer to true
				int selectedTrueAnswerIndex = r.nextInt((((MultiQuestion) question).getAnswers().length) - 1) + 1;
				((MultiQuestion) question).getAnswers()[selectedTrueAnswerIndex].setIsCorrect(true);

				if (selectedTrueAnswerIndex != 1) { // Index #1 is the index of the default answer "There are no correct
													// answers".
					((MultiQuestion) question).getAnswers()[1].setIsCorrect(false);
				}
			}
		}
	}

	private static void addRandomQuestionsToAutomaticExam(Exam exam) {
		Random r = new Random();
		int checkArr[] = new int[stockOfQuestions.length];
		int numOfQuestionsInExam = exam.getQuestions().length;
		int questionNumber = 0;
		for (int i = 0; i < numOfQuestionsInExam; i++) {
			while (checkArr[questionNumber] != 0) {
				questionNumber = r.nextInt(stockOfQuestions.length);
			}
			if (stockOfQuestions[questionNumber] instanceof MultiQuestion) {
				exam.addMultiQuestion((MultiQuestion) stockOfQuestions[questionNumber],
						stockOfQuestions[questionNumber].getLevel());
			} else {
				exam.addOpenQuestion((OpenQuestion) stockOfQuestions[questionNumber],
						stockOfQuestions[questionNumber].getLevel());
			}

			checkArr[questionNumber] = 1;
		}
	}

	// ***************************************************************************//

	public static void printStockOfQuestions() {
		for (int j = 0; j < stockOfQuestions.length; j++) {
			System.out.println((j + 1) + ". " + stockOfQuestions[j].getBody());
		}
	}

	// ***************************************************************************//
	// only Multi
	public static void addAnswerToMultiQuestionInExam(MultiQuestion question) {
		int numOfAnswers = 0;
		boolean isValid = false;

		while (!isValid) {
			try {
				System.out.println("\nHow many answers do you want to enter in the question?");
				numOfAnswers = s.nextInt();
				s.nextLine();
				if (numOfAnswers < 3) {
					throw new NumOfAnswersInMultiQuestionException();
				}
				isValid = true;
			} catch (NumOfAnswersInMultiQuestionException e) {
				System.out.println(e.toString());
			}
		}

		numOfAnswers = checkNumOfAnswers(numOfAnswers);

		for (int i = 0; i < numOfAnswers; i++) {
			System.out.println("\nEnter the number of answer " + (i + 1) + " of " + numOfAnswers + ":");
			printStockOfAnswers();

			int selectedAnswerIndex = s.nextInt();
			int numOfAnswersInStock = stockOfAnswers.length;
			selectedAnswerIndex = checkSelectedAnswer(selectedAnswerIndex, numOfAnswersInStock) - 1;

			Answer answer = new Answer(stockOfAnswers[selectedAnswerIndex]);

			System.out.println("\nIf this is a correct answer, press '1', else press '0': \n");
			int isCorrectInt = s.nextInt();
			isCorrectInt = checkIsCorrectInput(isCorrectInt);
			boolean isCorrect = isCorrectInt == 1 ? true : false;

			question.addAnswer(answer, isCorrect);
		}
	}

	// ***************************************************************************//

	public static void printStockOfAnswers() {
		for (int j = 0; j < stockOfAnswers.length; j++) {
			System.out.println((j + 1) + ". " + stockOfAnswers[j].getBody());
		}
	}

	public static int checkSelectedExam(int chosenExam) {
		while (chosenExam != 1 && chosenExam != 2) {
			System.out.println("\nWrong input, Press '1' to Manual exam, '2' to Automatic exam");
			chosenExam = s.nextInt();
		}
		return chosenExam;
	}

}