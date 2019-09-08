package edu.prj.bean;

import java.io.Serializable;
import java.util.Date;

public class Paper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1225285897163802545L;

	private Long id;
	private String paperName;
	private Long totalScore;
	private Long perScore;
	private Long questionNum;
	private Long examMinute;
	private Date startOn;
	private Date endOn;
	private Long hasGenerate;
	private Long subjectId;
	private String subjectName;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

	public Long getPerScore() {
		return perScore;
	}

	public void setPerScore(Long perScore) {
		this.perScore = perScore;
	}

	public Long getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Long questionNum) {
		this.questionNum = questionNum;
	}

	public Long getExamMinute() {
		return examMinute;
	}

	public void setExamMinute(Long examMinute) {
		this.examMinute = examMinute;
	}

	public Date getStartOn() {
		return startOn;
	}

	public void setStartOn(Date startOn) {
		this.startOn = startOn;
	}

	public Date getEndOn() {
		return endOn;
	}

	public void setEndOn(Date endOn) {
		this.endOn = endOn;
	}

	public Long getHasGenerate() {
		return hasGenerate;
	}

	public void setHasGenerate(Long hasGenerate) {
		this.hasGenerate = hasGenerate;
	}

}
