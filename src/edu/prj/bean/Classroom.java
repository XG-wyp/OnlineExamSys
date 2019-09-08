package edu.prj.bean;

public class Classroom implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2085385153505347853L;

	private Long id;
	private String roomName;
	private Long gradeId;
	private String gradeName;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
}
