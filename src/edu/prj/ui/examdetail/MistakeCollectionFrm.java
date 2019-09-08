package edu.prj.ui.examdetail;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.*;

import javax.swing.*;

import com.liuvei.common.win.*;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;

public class MistakeCollectionFrm extends AFrame {

	public MistakeCollectionFrm() {
		super.init();
	}

	private JLabel lblMsg;
	private JLabel lblPaperName;
	private JLabel lblSubjectName;
	private JLabel lblStuId;
	private JLabel lblStuName;
	private JLabel lblStuAnswer;
	private JLabel lblStdAnswer;
	private JTextArea txtQuestion;// 题目

	private JButton btnLast;// 返回上一题
	private JButton btnNext;// 下一题
	private Integer questionNO = 1;// 第几题

	// 倒计时

	private java.util.List<ExamItem> eiList = new java.util.ArrayList<ExamItem>();
	private java.util.List<ExamItem> examItemList;
	private java.util.List<Integer> wrongNum = new java.util.ArrayList<Integer>();
	Timer timer = null;
	public Integer pk;// ExamId
	public PaperDetailsFrm detailFrm = null;

	ExamService examService = new ExamServiceImpl();
	PaperService paperService = new PaperServiceImpl();
	PaperItemService paperItemService = new PaperItemServiceImpl();
	ExamItemService examItemService = new ExamItemServiceImpl();
	QuestionService questionService = new QuestionServiceImpl();
	StudentService studentService = new StudentServiceImpl();

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
		Paper paper = paperService.loadBy(bean.getPaperId());
		Student stu = studentService.load(bean.getStudentId());
		if (!this.getTitle().contains("查看")) {
			this.setTitle(
					this.getTitle() + "--> 正在查看学生【" + stu.getNickName() + "】的【" + paper.getSubjectName() + "】考试错题集");
		}

		if (bean != null) {
			examItemList = examItemService.listByExamId(pk.longValue());
			for (int i = 0; i < examItemList.size(); i++) {
				if (examItemList.get(i).getMarkResult() == 0) {
					eiList.add(examItemList.get(i));
					wrongNum.add(i + 1);
				}
			}

			lblPaperName.setText(lblPaperName.getText() + paper.getPaperName());
			lblSubjectName.setText(lblSubjectName.getText() + paper.getSubjectName());
			lblStuId.setText(lblStuId.getText() + stu.getId());
			lblStuName.setText(lblStuName.getText() + stu.getNickName());
			initData();
		}
	}

	public void initData() {
		ExamItem bean = eiList.get(questionNO - 1);
		Question question = questionService.load(bean.getQuestionId());
		showItemInit(question.getqType());
		lblMsg.setText("第" + wrongNum.get(questionNO) + "题。");
		txtQuestion.setText("\b\b\b\b" + question.getQuestion());
		txtQuestion.append("\r\n\r\nA." + question.getItemA());
		txtQuestion.append("\r\n\r\nB." + question.getItemB());
		if (question.getItemC() != null) {
			txtQuestion.append("\r\n\r\nC." + question.getItemC());
		}
		if (question.getItemD() != null) {
			txtQuestion.append("\r\n\r\nD." + question.getItemD());
		}
		lblStuAnswer.setText("学生答案：" + bean.getStuAnswer());
		lblStdAnswer.setText("标准答案：" + bean.getStdAnswer());
		this.validate();
		this.repaint();
	}

	// 选项布局
	public void showItemInit(Long qType) {

		lblMsg.setBounds(200, 50, 400, 40);
		lblMsg.setFont(new Font("宋体", Font.BOLD, 20));
		container.add(lblMsg);

		txtQuestion.setBounds(200, 90, 400, 200);
		txtQuestion.setLineWrap(true);
		txtQuestion.setEditable(false);
		txtQuestion.setBackground(new Color(238, 238, 238));
		container.add(txtQuestion);

		lblStuAnswer.setBounds(300, 390, 200, 30);
		lblStuAnswer.setFont(new Font("宋体", Font.BOLD, 15));
		container.add(lblStuAnswer);

		lblStdAnswer.setBounds(300, 420, 200, 30);
		lblStdAnswer.setFont(new Font("宋体", Font.BOLD, 15));
		container.add(lblStdAnswer);

		btnLast.setText("上一题");
		btnLast.setBounds(20, 500, 80, 30);
		container.add(btnLast);

		btnNext.setText("下一题");
		btnNext.setBounds(680, 500, 80, 30);
		container.add(btnNext);

	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub
		int width = 800;
		int height = 600;
		resetSize(width, height);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);
		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);

		lblPaperName = new JLabel();
		lblPaperName.setBounds(10, 10, 200, 30);
		lblPaperName.setText("试卷名：");
		lblPaperName.setFont(new Font("宋体", 0, 15));
		container.add(lblPaperName);

		lblSubjectName = new JLabel();
		lblSubjectName.setBounds(180, 10, 200, 30);
		lblSubjectName.setText("科目名：");
		lblSubjectName.setFont(new Font("宋体", 0, 15));
		container.add(lblSubjectName);

		lblStuId = new JLabel();
		lblStuId.setBounds(400, 10, 200, 30);
		lblStuId.setText("学号：");
		lblStuId.setFont(new Font("宋体", 0, 15));
		container.add(lblStuId);

		lblStuName = new JLabel();
		lblStuName.setBounds(500, 10, 200, 30);
		lblStuName.setText("姓名：");
		lblStuName.setFont(new Font("宋体", 0, 15));
		container.add(lblStuName);

		this.setTitle("试卷详情");

		lblMsg = new JLabel();
		txtQuestion = new JTextArea();
		btnLast = new JButton();
		btnNext = new JButton();
		lblStuAnswer = new JLabel();
		lblStdAnswer = new JLabel();

	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		btnNext.addActionListener(e -> {
			btnNext_click(e);
		});

		btnLast.addActionListener(e -> {
			btnLast_click(e);
		});
	}

	private void btnLast_click(ActionEvent e) {
		// TODO Auto-generated method stub
		if (questionNO == 1) {
			JOptionPane.showMessageDialog(null, "这是第一题。");
			return;
		}
		questionNO--;
		initData();
	}

	private void btnNext_click(ActionEvent e) {
		// TODO Auto-generated method stub
		if (questionNO == eiList.size() - 1) {
			JOptionPane.showMessageDialog(null, "这是最后一题。");
			return;
		}
		questionNO++;
		initData();
	}

	@Override
	protected void initLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void window_closing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		if (detailFrm != null) {
			detailFrm.setVisible(true);
			this.dispose();
		}
	}

}
