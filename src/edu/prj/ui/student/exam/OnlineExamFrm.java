package edu.prj.ui.student.exam;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.*;

import javax.swing.*;

import com.liuvei.common.win.*;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;

public class OnlineExamFrm extends AFrame {

	public OnlineExamFrm() {
		super.init();
	}

	private JLabel lblMsg;
	private JLabel lblCountDown;// 倒计时
	private JTextArea txtQuestion;// 题目
	private ButtonGroup btnChoise;// 选项模块
	private JRadioButton rdoItemA;// 选项A
	private JRadioButton rdoItemB;// 选项B
	private JRadioButton rdoItemC;// 选项C
	private JRadioButton rdoItemD;// 选项D

	private JButton btnLast;// 返回上一题
	private JButton btnNext;// 下一题
	private JButton btnSubmit;// 交卷
	private Integer questionNO = 1;// 第几题
	private Integer questionNum;// 题目数

	// 倒计时
	public Integer time;
	private Integer hour;
	private Integer minute;
	private Integer second;
	private java.util.List<ExamItem> eiList;
	Timer timer = null;
	public OnlinePaperListFrm listFrm = null;
	public Integer pk;// ExamId
	ExamService examService = new ExamServiceImpl();
	PaperService paperService = new PaperServiceImpl();
	PaperItemService paperItemService = new PaperItemServiceImpl();
	ExamItemService examItemService = new ExamItemServiceImpl();
	QuestionService questionService = new QuestionServiceImpl();

	public void loadData() {
		if (pk == null) {
			JOptionPane.showMessageDialog(null, "主键值为空，加载数据失败！");
			return;
		}

		Exam bean = examService.loadById(pk.longValue());

		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应数据不存在，加载数据失败！");
			return;
		}
		

		if (bean != null) {
			eiList = examItemService.listByExamId(pk.longValue());
			questionNum = examItemService.countByExamId(pk.longValue()).intValue();
			initData();
			timer.start();
		}
	}

	public void initData() {
		ExamItem bean = eiList.get(questionNO - 1);
		Question question = questionService.load(bean.getQuestionId());
		showItemInit(question.getqType());
		lblMsg.setText("第" + questionNO + "题，共" + questionNum + "题。");
		txtQuestion.setText("\b\b\b\b" + question.getQuestion());
		rdoItemA.setText("A." + question.getItemA());
		rdoItemB.setText("B." + question.getItemB());
		if (rdoItemC != null) {
			rdoItemC.setText("C." + question.getItemC());
		}
		if (rdoItemD != null) {
			rdoItemD.setText("D." + question.getItemD());
		}
		btnChoise.clearSelection();
		if (bean.getStuAnswer() != null) {
			switch (bean.getStuAnswer()) {
			case "A":
				rdoItemA.setSelected(true);
				break;
			case "B":
				rdoItemB.setSelected(true);
				break;
			case "C":
				rdoItemC.setSelected(true);
				break;
			case "D":
				rdoItemD.setSelected(true);
				break;
			}
		}
		this.validate();
		this.repaint();
	}

	// 选项布局
	public void showItemInit(Long qType) {

		lblMsg.setBounds(200, 50, 400, 50);
		lblMsg.setFont(new Font("宋体", Font.BOLD, 20));
		container.add(lblMsg);

		txtQuestion.setBounds(200, 90, 400, 100);
		txtQuestion.setLineWrap(true);
		txtQuestion.setEditable(false);
		txtQuestion.setBackground(new Color(238, 238, 238));
		container.add(txtQuestion);

		rdoItemA.setBounds(200, 200, 400, 30);
		container.add(rdoItemA);

		rdoItemB.setBounds(200, 240, 400, 30);
		container.add(rdoItemB);

		btnChoise.add(rdoItemA);
		btnChoise.add(rdoItemB);
		if (qType == 1 && rdoItemC != null) {
			container.remove(rdoItemC);
			container.remove(rdoItemD);
			btnChoise.remove(rdoItemC);
			btnChoise.remove(rdoItemD);
		}
		if (qType == 2) {

			rdoItemC.setBounds(200, 280, 400, 30);
			container.add(rdoItemC);

			rdoItemD.setBounds(200, 320, 400, 30);
			container.add(rdoItemD);

			btnChoise.add(rdoItemC);
			btnChoise.add(rdoItemD);
		}

		btnLast.setText("上一题");
		btnLast.setBounds(20, 500, 80, 30);
		container.add(btnLast);

		btnNext.setText("下一题");
		btnNext.setBounds(680, 500, 80, 30);
		container.add(btnNext);

		btnSubmit.setText("交卷");
		btnSubmit.setBounds(365, 500, 80, 30);
		container.add(btnSubmit);

	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub
		int width = 800;
		int height = 600;
		resetSize(width, height);
		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);

		lblCountDown = new JLabel();
		lblCountDown.setBounds(600, 20, 600, 50);
		lblCountDown.setText("提示");
		container.add(lblCountDown);

		lblMsg = new JLabel();
		txtQuestion = new JTextArea();
		rdoItemA = new JRadioButton();
		rdoItemB = new JRadioButton();
		btnChoise = new ButtonGroup();
		rdoItemC = new JRadioButton();
		rdoItemD = new JRadioButton();
		btnLast = new JButton();
		btnNext = new JButton();
		btnSubmit = new JButton();
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer_tick(e);
			}
		});

	}

	public void bindEvent() {
		btnNext.addActionListener(e -> {
			btnNext_click(e);
		});

		btnLast.addActionListener(e -> {
			btnLast_click(e);
		});

		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
	}

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String stuAnswer = "";
		if (rdoItemA.isSelected()) {
			stuAnswer = "A";
		}
		if (rdoItemB.isSelected()) {
			stuAnswer = "B";
		}
		if (rdoItemC != null) {
			if (rdoItemC.isSelected()) {
				stuAnswer = "C";
			}
		}
		if (rdoItemD != null) {
			if (rdoItemD.isSelected()) {
				stuAnswer = "D";
			}
		}
		if (!stuAnswer.isEmpty()) {
			ExamItem bean = eiList.get(questionNO - 1);

			if (bean.getStuAnswer() == null || !bean.getStuAnswer().equalsIgnoreCase(stuAnswer)) {
				bean.setStuAnswer(stuAnswer);
				if (bean.getStdAnswer().equalsIgnoreCase(stuAnswer)) {
					bean.setMarkResult(1l);
					bean.setGainScore(bean.getStdScore());
				} else {
					bean.setMarkResult(0l);
					bean.setGainScore(0l);
				}
				examItemService.update(bean);
			}
		}
		Long result = examItemService.countNull(pk.longValue());
		String message = "";
		if ((questionNum - result) > 0) {
			message = "还有" + (questionNum - result) + "道题未写，确认交卷？";
		} else {
			message = "确认交卷？";
		}
		int option = JOptionPane.showConfirmDialog(null, message, "系统提示", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			Long trueItem = examItemService.countTrue(pk.longValue());
			Long totalScore = eiList.get(0).getStdScore() * trueItem;
			Exam exam = examService.load(pk.longValue());
			exam.setTotalScore(totalScore);
			examService.update(exam);
			JOptionPane.showMessageDialog(null, "考试结束，" + totalScore + "分");
			if (listFrm != null) {
				listFrm.setVisible(true);
				this.dispose();
			}

		}
	}

	private void btnLast_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String stuAnswer = "";
		if (rdoItemA.isSelected()) {
			stuAnswer = "A";
		}
		if (rdoItemB.isSelected()) {
			stuAnswer = "B";
		}
		if (rdoItemC != null) {
			if (rdoItemC.isSelected()) {
				stuAnswer = "C";
			}
		}
		if (rdoItemD != null) {
			if (rdoItemD.isSelected()) {
				stuAnswer = "D";
			}
		}
		if (!stuAnswer.isEmpty()) {
			ExamItem bean = eiList.get(questionNO - 1);
			if (bean.getStuAnswer() == null || !bean.getStuAnswer().equalsIgnoreCase(stuAnswer)) {
				bean.setStuAnswer(stuAnswer);
				if (bean.getStdAnswer().equalsIgnoreCase(stuAnswer)) {
					bean.setMarkResult(1l);
					bean.setGainScore(bean.getStdScore());
				} else {
					bean.setMarkResult(0l);
					bean.setGainScore(0l);
				}
				examItemService.update(bean);
			}
		}
		if (questionNO == 1) {
			JOptionPane.showMessageDialog(null, "这是第一题。");
			return;
		}
		questionNO--;
		initData();
	}

	private void btnNext_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String stuAnswer = "";
		if (rdoItemA.isSelected()) {
			stuAnswer = "A";
		}
		if (rdoItemB.isSelected()) {
			stuAnswer = "B";
		}
		if (rdoItemC != null) {
			if (rdoItemC.isSelected()) {
				stuAnswer = "C";
			}
		}
		if (rdoItemD != null) {
			if (rdoItemD.isSelected()) {
				stuAnswer = "D";
			}
		}
		if (!stuAnswer.isEmpty()) {
			ExamItem bean = eiList.get(questionNO - 1);

			if (bean.getStuAnswer() == null || !bean.getStuAnswer().equalsIgnoreCase(stuAnswer)) {
				bean.setStuAnswer(stuAnswer);
				if (bean.getStdAnswer().equalsIgnoreCase(stuAnswer)) {
					bean.setMarkResult(1l);
					bean.setGainScore(bean.getStdScore());
				} else {
					bean.setMarkResult(0l);
					bean.setGainScore(0l);
				}
				examItemService.update(bean);
			}
		}
		if (questionNO == questionNum) {
			JOptionPane.showMessageDialog(null, "这是最后一题。");
			return;
		}
		questionNO++;
		initData();
	}

	protected void timer_tick(ActionEvent e) {
		// TODO Auto-generated method stub
		hour = time / 3600;
		minute = time % 3600 / 60;
		second = time % 3600 % 60;
		lblCountDown.setText("剩余时间：" + stringTime(hour) + ":" + stringTime(minute) + ":" + stringTime(second));
		if (time == 0) {
			timer.stop();
			timeOut();
		}
		time--;
	}

	public void timeOut() {
		Long trueItem = examItemService.countTrue(pk.longValue());
		Long totalScore = eiList.get(0).getStdScore() * trueItem;
		Exam exam = examService.load(pk.longValue());
		exam.setTotalScore(totalScore);
		JOptionPane.showMessageDialog(null, "考试时间到，考试结束，" + totalScore + "分");
		if (listFrm != null) {
			listFrm.setVisible(true);
			this.dispose();
		}
	}

	private String stringTime(int time) {
		String time0 = "";
		if (time < 10) {
			time0 = "0" + time;
		} else {
			time0 = time + "";
		}
		return time0;
	}

	@Override
	protected void initLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void window_closing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		Long result = examItemService.countNull(pk.longValue());
		String message = "";
		if ((questionNum - result) > 0) {
			message = "还有" + (questionNum - result) + "道题未写，确认交卷？";
		} else {
			message = "确认交卷？";
		}
		int option = JOptionPane.showConfirmDialog(null, message, "系统提示", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			Long trueItem = examItemService.countTrue(pk.longValue());
			Long totalScore = eiList.get(0).getStdScore() * trueItem;
			Exam exam = examService.load(pk.longValue());
			exam.setTotalScore(totalScore);
			JOptionPane.showMessageDialog(null, "考试结束，" + totalScore + "分");
			if (listFrm != null) {
				listFrm.setVisible(true);
				this.dispose();
			}

		}
	}

}
