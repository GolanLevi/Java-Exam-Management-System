package id_211939947;

import java.io.Serializable;

public abstract class Question implements Serializable {
	public enum Level {Easy, Medium, Hard} ;
	private Level level;
	private String body;
	
	public Question(String body, Level level) {
		this.body = body;
		this.level = level;
	}

	public Question(Question otherQuestion) { // copy constructor.
		this.body = otherQuestion.getBody(); 
		this.level = otherQuestion.getLevel();
	}
	
	public Question(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public String toString() {
		return "The question" + getBody();
	}

}
