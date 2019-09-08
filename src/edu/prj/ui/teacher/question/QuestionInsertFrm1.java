package edu.prj.ui.teacher.question;

import javax.swing.*;

import com.liuvei.common.*;
import com.liuvei.common.win.*;

import edu.prj.bean.Question;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.QuestionServiceImpl;

import java.awt.*;
import java.awt.event.*;

public class QuestionInsertFrm1 extends JFrame {
	// 9
	private JPanel container;

	private JLabel lblQuestion;
	private JTextArea txtQuestion;

	private JLabel lblItemA;
	private JTextField txtItemA;

	private JLabel lblItemB;
	private JTextField txtItemB;

	private JLabel lblItemC;
	private JTextField txtItemC;

	private JLabel lblItemD;
	private JTextField txtItemD;

	private JLabel lblItemE;
	private JTextField txtItemE;

	private JLabel lblItemF;
	private JTextField txtItemF;

	private JLabel lblAnswer;
	private JTextField txtAnswer;

	private JLabel lblTag;
	private JTextArea txtTag;

	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnBack;

	private JLabel lblMsg;
	QuestionListFrm listFrm = null;
	public QuestionInsertFrm0 insertFrm0 = null;
	public Long qType;
	public Long subjectId;
	public String subjectName;

	public QuestionInsertFrm1() {
		initUI();
		customLoad();
	}

	private void customInitUI() {
		container = new JPanel();
		this.add(container);
		container.setLayout(null);

		lblQuestion = new JLabel();
		lblQuestion.setText("题目");
		lblQuestion.setBounds(130, 40, 80, 30);
		container.add(lblQuestion);

		txtQuestion = new JTextArea();
		txtQuestion.setText("");
		txtQuestion.setBounds(20, 70, 250, 100);
		txtQuestion.setLineWrap(true);
//		txtQuestion.setEditable(false);
		container.add(txtQuestion);

	}

	/**
	 * 判断题界面
	 */
	private void judgeInitUI() {
		lblItemA = new JLabel();
		lblItemA.setText("选项A：");
		lblItemA.setBounds(290, 70, 80, 30);
		container.add(lblItemA);

		txtItemA = new JTextField();
		txtItemA.setText("");
		txtItemA.setBounds(350, 70, 120, 30);
		container.add(txtItemA);

		lblItemB = new JLabel();
		lblItemB.setText("选项B：");
		lblItemB.setBounds(290, 110, 80, 30);
		container.add(lblItemB);

		txtItemB = new JTextField();
		txtItemB.setText("");
		txtItemB.setBounds(350, 110, 120, 30);
		container.add(txtItemB);

	}

	/**
	 * 单选题界面
	 */
	private void choiseInitUI() {

		lblItemA = new JLabel();
		lblItemA.setText("选项A：");
		lblItemA.setBounds(290, 70, 80, 30);
		container.add(lblItemA);

		txtItemA = new JTextField();
		txtItemA.setText("");
		txtItemA.setBounds(350, 70, 120, 30);
		container.add(txtItemA);

		lblItemB = new JLabel();
		lblItemB.setText("选项B：");
		lblItemB.setBounds(290, 110, 80, 30);
		container.add(lblItemB);

		txtItemB = new JTextField();
		txtItemB.setText("");
		txtItemB.setBounds(350, 110, 120, 30);
		container.add(txtItemB);

		lblItemC = new JLabel();
		lblItemC.setText("选项C：");
		lblItemC.setBounds(290, 150, 80, 30);
		container.add(lblItemC);

		txtItemC = new JTextField();
		txtItemC.setText("");
		txtItemC.setBounds(350, 150, 120, 30);
		container.add(txtItemC);

		lblItemD = new JLabel();
		lblItemD.setText("选项D：");
		lblItemD.setBounds(290, 190, 80, 30);
		container.add(lblItemD);

		txtItemD = new JTextField();
		txtItemD.setText("");
		txtItemD.setBounds(350, 190, 120, 30);
		container.add(txtItemD);

	}

	private void customBindEvent() {
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
		btnBack.addActionListener(e -> {
			btnBack_click(e);
		});
	}

	private void btnBack_click(ActionEvent e) {
		// TODO Auto-generated method stub
		if (insertFrm0 != null) {
			insertFrm0.setVisible(true);
		}
		this.dispose();
	}

	private void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		txtAnswer.setText("");
		txtItemA.setText("");
		txtItemB.setText("");
		if (txtItemC != null) {
			txtItemC.setText("");
		}
		if (txtItemD != null) {
			txtItemD.setText("");
		}
		txtQuestion.setText("");
		txtTag.setText("");
	}

	QuestionService questionService = new QuestionServiceImpl();

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub

		String question = txtQuestion.getText().trim();
		String itemA = txtItemA.getText().trim();
		String itemB = txtItemB.getText().trim();
		String answer = txtAnswer.getText().trim();
		String tag = txtTag.getText().trim();
		if (question == null || question.isEmpty()) {
			lblMsg.setText("提示：题目不能为空");
			return;
		}
		if (itemA == null || itemA.isEmpty()) {
			lblMsg.setText("提示：选项A不能为空");
			return;
		}
		if (itemB == null || itemB.isEmpty()) {
			lblMsg.setText("提示：选项B不能为空");
			return;
		}
		if (answer == null || answer.isEmpty()) {
			lblMsg.setText("提示：答案不能为空");
			return;
		}
		Question item = questionService.loadByName(question);
		if (item != null) {
			lblMsg.setText("提示：该题已存在。");
			return;
		}

		Question bean = new Question();
		bean.setqType(qType);
		bean.setQuestion(question);
		bean.setItemA(itemA);
		bean.setItemB(itemB);
		if (txtItemC != null) {
			String itemC = txtItemC.getText().trim();
			bean.setItemC(itemC);
		}
		if (txtItemD != null) {
			String itemD = txtItemD.getText().trim();
			bean.setItemD(itemD);
		}
		bean.setAnswer(answer);
		bean.setSubjectID(subjectId);
		bean.setTag(tag);

		Long result = 0l;
		String errMsg = null;
		try {
			result = questionService.insert(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}

		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功！");
			int option = JOptionPane.showConfirmDialog(null, "是否继续添加题目", "提示", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				btnReset_click(null);
			} else {
				if (listFrm != null) {
					listFrm.btnReset_clich(null);
					listFrm.setVisible(true);
				}
				this.dispose();
			}
		} else {
			lblMsg.setText("操作失败。");
		}

	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 500;
		int height = 400;
		this.setSize(width, height);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);

		this.setResizable(false);

		customInitUI();

	}

	private void bindEvent() {
		// TODO Auto-generated method stub
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});
		customBindEvent();
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (insertFrm0 != null) {
			insertFrm0.setVisible(true);
		}
		this.dispose();
	}

	public void reFlash() {
		if (qType == 1) {
			this.setTitle("添加题目-" + subjectName + "-判断题");
			judgeInitUI();
		} else if (qType == 2) {
			this.setTitle("添加题目-" + subjectName + "-选择题");
			choiseInitUI();
		}

		lblAnswer = new JLabel();
		lblAnswer.setText("答案：");
		lblAnswer.setBounds(60, 180, 80, 30);
		container.add(lblAnswer);

		txtAnswer = new JTextField();
		txtAnswer.setText("");
		txtAnswer.setBounds(120, 180, 120, 30);
		container.add(txtAnswer);

		lblTag = new JLabel();
		lblTag.setText("标签");
		lblTag.setBounds(130, 220, 80, 30);
		container.add(lblTag);

		txtTag = new JTextArea();
		txtTag.setText("");
		txtTag.setBounds(20, 250, 250, 100);
		container.add(txtTag);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(310, 300, 60, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(390, 300, 60, 30);
		container.add(btnReset);

		btnBack = new JButton();
		btnBack.setText("返回");
		btnBack.setBounds(10, 10, 60, 30);
		container.add(btnBack);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(200, 10, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);

		bindEvent();
	}

	private void customLoad() {
		// TODO Auto-generated method stub
	}

}
