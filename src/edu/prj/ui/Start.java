package edu.prj.ui;

import edu.prj.ui.examdetail.PaperDetailsFrm;
import edu.prj.ui.manager.*;
import edu.prj.ui.manager.classroom.*;
import edu.prj.ui.manager.grade.*;
import edu.prj.ui.manager.subject.*;
import edu.prj.ui.student.exam.ReadyExamFrm;
import edu.prj.ui.teacher.question.QuestionInsertFrm1;
import edu.prj.ui.teacher.paper.PaperInsertFrm;
import edu.prj.ui.teacher.question.QuestionInsertFrm0;
import edu.prj.ui.teacher.question.QuestionListFrm;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginFrm frm = new LoginFrm();
//		TeacherMainFrm frm=new TeacherMainFrm();
//		ManagerListFrm frm=new ManagerListFrm();
//		GradeInsertFrm frm = new GradeInsertFrm();
//		ChangePwdFrm frm=new ChangePwdFrm();
//		GradeListFrm frm=new GradeListFrm();
//		QuestionListFrm frm = new QuestionListFrm();
//		ClassroomInsertFrm frm=new ClassroomInsertFrm();
//		QuestionInsertFrm0 frm = new QuestionInsertFrm0();
//		PaperInsertFrm frm=new PaperInsertFrm();
//		ReadyExamFrm frm=new ReadyExamFrm();
//		PaperDetailsFrm frm = new PaperDetailsFrm();
//		frm.pk = 21;
//		frm.loadData();
		frm.setVisible(true);
	}

}
