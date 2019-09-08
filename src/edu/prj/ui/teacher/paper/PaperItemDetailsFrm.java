package edu.prj.ui.teacher.paper;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.*;

import javax.swing.*;

import com.liuvei.common.win.*;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;

public class PaperItemDetailsFrm extends AFrame {

	public PaperItemDetailsFrm() {
		super.init();
	}

	private JLabel lblMsg;
	private JLabel lblPaperName;
	private JLabel lblSubjectName;

	private JLabel lblAnswer;
	private JTextArea txtQuestion;// 题目

	private JButton btnLast;// 返回上一题
	private JButton btnNext;// 下一题
	private Integer questionNO = 1;// 第几题
	private Integer questionNum;// 题目数

	// 倒计时

	private java.util.List<PaperItem> eiList;
	Timer timer = null;
	public Integer pk;// PaperId
	public PaperListFrm listFrm = null;

	PaperService paperService = new PaperServiceImpl();
	PaperItemService paperItemService = new PaperItemServiceImpl();
	QuestionService questionService = new QuestionServiceImpl();
	StudentService studentService = new StudentServiceImpl();

	public void loadData() {
		if (pk == null) {
			JOptionPane.showMessageDialog(null, "主键值为空，加载数据失败！");
			return;
		}

		Paper bean = paperService.loadBy(pk.longValue());

		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应数据不存在，加载数据失败！");
			return;
		}
		if (!this.getTitle().contains("查看")) {
			this.setTitle(this.getTitle() + "--> 正在查看【" + bean.getPaperName() + "】试卷信息");
		}

		if (bean != null) {
			eiList = paperItemService.listByPaperId(pk.longValue());
			questionNum = bean.getQuestionNum().intValue();

			lblPaperName.setText(lblPaperName.getText() + bean.getPaperName());
			lblSubjectName.setText(lblSubjectName.getText() + bean.getSubjectName());

			initData();
		}
	}

	public void initData() {
		PaperItem bean = eiList.get(questionNO - 1);
		Question question = questionService.load(bean.getQuestionId());
		showItemInit(question.getqType());
		lblMsg.setText("第" + questionNO + "题，共" + questionNum + "题。");
		txtQuestion.setText("\b\b\b\b" + question.getQuestion());
		txtQuestion.append("\r\n\r\nA." + question.getItemA());
		txtQuestion.append("\r\n\r\nB." + question.getItemB());
		if (question.getItemC() != null) {
			txtQuestion.append("\r\n\r\nC." + question.getItemC());
		}
		if (question.getItemD() != null) {
			txtQuestion.append("\r\n\r\nD." + question.getItemD());
		}
		lblAnswer.setText("标准答案：" + question.getAnswer());
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

		lblAnswer.setBounds(300, 420, 200, 30);
		lblAnswer.setFont(new Font("宋体", Font.BOLD, 15));
		container.add(lblAnswer);

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

		this.setTitle("试卷详情");

		lblMsg = new JLabel();
		txtQuestion = new JTextArea();
		btnLast = new JButton();
		btnNext = new JButton();
		lblAnswer = new JLabel();

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
		if (questionNO == questionNum) {
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
		if (listFrm != null) {
			listFrm.setVisible(true);
			this.dispose();
		}
	}

}
