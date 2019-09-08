package edu.prj.bean;

import java.io.Serializable;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5282049121844513129L;

	private Long id;
	private Long qType;
	private String question;
	private String itemA;
	private String itemB;
	private String itemC;
	private String itemD;
	private String itemE;
	private String itemF;
	private String answer;
	private Long subjectID;
	private String tag;
	private String subject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getqType() {
		return qType;
	}

	public void setqType(Long qType) {
		this.qType = qType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getItemA() {
		return itemA;
	}

	public void setItemA(String itemA) {
		this.itemA = itemA;
	}

	public String getItemB() {
		return itemB;
	}

	public void setItemB(String itemB) {
		this.itemB = itemB;
	}

	public String getItemC() {
		return itemC;
	}

	public void setItemC(String itemC) {
		this.itemC = itemC;
	}

	public String getItemD() {
		return itemD;
	}

	public void setItemD(String itemD) {
		this.itemD = itemD;
	}

	public String getItemE() {
		return itemE;
	}

	public void setItemE(String itemE) {
		this.itemE = itemE;
	}

	public String getItemF() {
		return itemF;
	}

	public void setItemF(String itemF) {
		this.itemF = itemF;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Long subjectID) {
		this.subjectID = subjectID;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
