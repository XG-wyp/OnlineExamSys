package edu.prj.bean;

import java.io.Serializable;

public class ExamItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4610568576782898971L;

	private Long id;
	private Long examId;
	private Long questionId;
	private String stuAnswer;
	private String stdAnswer;
	private Long stdScore;
	private Long markResult;
	private Long gainScore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getStuAnswer() {
		return stuAnswer;
	}

	public void setStuAnswer(String stuAnswer) {
		this.stuAnswer = stuAnswer;
	}

	public String getStdAnswer() {
		return stdAnswer;
	}

	public void setStdAnswer(String stdAnswer) {
		this.stdAnswer = stdAnswer;
	}

	public Long getStdScore() {
		return stdScore;
	}

	public void setStdScore(Long stdScore) {
		this.stdScore = stdScore;
	}

	public Long getMarkResult() {
		return markResult;
	}

	public void setMarkResult(Long markResult) {
		this.markResult = markResult;
	}

	public Long getGainScore() {
		return gainScore;
	}

	public void setGainScore(Long gainScore) {
		this.gainScore = gainScore;
	}

}
