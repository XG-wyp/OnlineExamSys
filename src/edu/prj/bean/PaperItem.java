package edu.prj.bean;

import java.io.Serializable;

public class PaperItem implements Serializable {

	/**
	 * 试卷试题
	 */
	private static final long serialVersionUID = -2265282410753721143L;

	private Long id;
	private Long paperId;
	private Long questionId;
	private String answer;
	private Long score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

}
