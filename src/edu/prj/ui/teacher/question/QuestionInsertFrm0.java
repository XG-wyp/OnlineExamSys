package edu.prj.ui.teacher.question;

import javax.swing.*;

import com.liuvei.common.win.*;

import edu.prj.bean.Subject;
import edu.prj.service.SubjectService;
import edu.prj.service.impl.SubjectServiceImpl;

import java.awt.*;
import java.awt.event.*;

public class QuestionInsertFrm0 extends JFrame {
	private JPanel container;

	private JLabel lblQType;
	private JComboBox<IdText> cboQType;

	private JLabel lblSubjectName;
	private JComboBox<IdText> cboSubjectName;

	private JButton btnNext;
	private JButton btnReset;
	private JLabel lblMsg;

	QuestionListFrm listFrm = null;

	public QuestionInsertFrm0() {
		initUI();
		bindEvent();
		customLoad();
	}

	SubjectService subjectService = new SubjectServiceImpl();

	private void bindComBoxDate() {
		java.util.List<IdText> idTextA = new java.util.ArrayList<IdText>();
		idTextA.add(new IdText(1L, "判断题"));
		idTextA.add(new IdText(2L, "单选题"));
//		idTextA.add(new IdText(3L, "多选题"));
		IdTextModel modelA = new IdTextModel(idTextA);
		cboQType.setModel(modelA);
		cboQType.setSelectedIndex(0);

		java.util.List<IdText> idTextB = new java.util.ArrayList<IdText>();
		java.util.List<Subject> listB = subjectService.list();
		for (Subject item : listB) {
			idTextB.add(new IdText(item.getId(), item.getSubjectName()));
		}
		IdTextModel modelB = new IdTextModel(idTextB);
		cboSubjectName.setModel(modelB);
		cboSubjectName.setSelectedIndex(0);
	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 400;
		int height = 300;
		this.setSize(width, height);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);

		this.setResizable(false);

		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);

		lblQType = new JLabel();
		lblQType.setText("题目类型：");
		lblQType.setBounds(80, 20, 80, 30);
		container.add(lblQType);

		cboQType = new JComboBox<IdText>();
		cboQType.setBounds(150, 20, 120, 30);
		container.add(cboQType);

		lblSubjectName = new JLabel();
		lblSubjectName.setText("所属科目：");
		lblSubjectName.setBounds(80, 60, 80, 30);
		container.add(lblSubjectName);

		cboSubjectName = new JComboBox<IdText>();
		cboSubjectName.setBounds(150, 60, 120, 30);
		container.add(cboSubjectName);

		btnNext = new JButton();
		btnNext.setText("下一步");
		btnNext.setBounds(80, 225, 80, 30);
		container.add(btnNext);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(180, 225, 80, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(50, 260, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);

		bindComBoxDate();
	}

	private void bindEvent() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});
		customBindEvent();
	}

	private void customBindEvent() {
		btnNext.addActionListener(e -> {
			btnNext_click(e);
		});
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
	}

	private void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		bindComBoxDate();
	}

	private void btnNext_click(ActionEvent e) {
		// TODO Auto-generated method stub
		IdText itemQType = (IdText) cboQType.getSelectedItem();
		Long qType = itemQType.getId();
		IdText itemSubjectName = (IdText) cboSubjectName.getSelectedItem();
		Long subjectId = itemSubjectName.getId();
		String subjectName = itemSubjectName.getText();
		QuestionInsertFrm1 questionInsertFrm1 = new QuestionInsertFrm1();
		questionInsertFrm1.qType = qType;
		questionInsertFrm1.subjectId = subjectId;
		questionInsertFrm1.subjectName = subjectName;
		questionInsertFrm1.listFrm = listFrm;
		questionInsertFrm1.insertFrm0 = this;
		questionInsertFrm1.reFlash();
		questionInsertFrm1.setVisible(true);
		this.setVisible(false);

	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (listFrm != null) {
			listFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customLoad() {
		// TODO Auto-generated method stub
		this.setTitle("添加题目");
	}

}
