package edu.prj.ui.student.exam;

import javax.swing.*;

import com.liuvei.common.win.IdText;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class ReadyExamFrm extends JFrame {
	private JPanel container;

	private JLabel lblPaperName;
	private JLabel lblSubjectName;
	private JLabel lblStudentName;
	private JLabel lblStudentId;
	private JLabel lblStartOn;
	private JLabel lblEndOn;
	private JLabel lblExamMinute;

	private JButton btnStart;

	public OnlinePaperListFrm listFrm = null;
	public Integer time;

	public ReadyExamFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	public Integer pk;
	ExamService examService = new ExamServiceImpl();
	PaperService paperService = new PaperServiceImpl();
	PaperItemService paperItemService = new PaperItemServiceImpl();
	ExamItemService examItemService = new ExamItemServiceImpl();

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
		if (!this.getTitle().contains("修改")) {
			this.setTitle(
					this.getTitle() + "--> 学生【" + bean.getStudentName() + "】正在进行【" + bean.getPaperName() + "】试卷考试");
		}

		if (bean != null) {
			Paper paper = paperService.load(bean.getPaperId());
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			lblPaperName.setText(bean.getPaperName());
			lblSubjectName.setText(lblSubjectName.getText() + bean.getSubjectName());
			lblStudentName.setText(lblStudentName.getText() + bean.getStudentName());
			lblStudentId.setText(lblStudentId.getText() + bean.getStudentId());
			lblExamMinute.setText(lblExamMinute.getText() + paper.getExamMinute() + "分");
			time = paper.getExamMinute().intValue() * 60;
			lblStartOn.setText(lblStartOn.getText() + df.format(bean.getStartOn()));
			lblEndOn.setText(lblEndOn.getText() + df.format(bean.getEndOn()));
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 800;
		int height = 600;
		this.setSize(width, height);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);

		this.setResizable(false);

		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);

		lblPaperName = new JLabel("", JLabel.CENTER);
		lblPaperName.setText("");
		lblPaperName.setFont(new Font("楷体", Font.BOLD, 60));

		lblPaperName.setBounds(0, 80, width, 60);
		container.add(lblPaperName);

		// 科目名称
		lblSubjectName = new JLabel("", JLabel.CENTER);
		lblSubjectName.setText("科目：");
		lblSubjectName.setFont(new Font("楷体", Font.BOLD, 30));
		lblSubjectName.setBounds(0, 170, width, 40);
		container.add(lblSubjectName);

		// 学生姓名
		lblStudentName = new JLabel("", JLabel.CENTER);
		lblStudentName.setText("姓名：");
		lblStudentName.setFont(new Font("楷体", 0, 20));
		lblStudentName.setBounds(0, 230, width, 30);
		container.add(lblStudentName);

		lblStudentId = new JLabel("", JLabel.CENTER);
		lblStudentId.setText("学号：");
		lblStudentId.setFont(new Font("楷体", 0, 20));
		lblStudentId.setBounds(0, 270, width, 30);
		container.add(lblStudentId);

		lblStartOn = new JLabel("", JLabel.CENTER);
		lblStartOn.setText("有效开始时间：");
		lblStartOn.setFont(new Font("楷体", Font.BOLD, 25));
		lblStartOn.setBounds(0, 350, width, 40);
		container.add(lblStartOn);

		lblEndOn = new JLabel("", JLabel.CENTER);
		lblEndOn.setText("有效结束日期：");
		lblEndOn.setFont(new Font("楷体", Font.BOLD, 25));
		lblEndOn.setBounds(0, 390, width, 40);
		container.add(lblEndOn);

		lblExamMinute = new JLabel("", JLabel.CENTER);
		lblExamMinute.setText("考试时间：");
		lblExamMinute.setFont(new Font("楷体", Font.BOLD, 25));
		lblExamMinute.setBounds(0, 310, width, 40);
		container.add(lblExamMinute);

		btnStart = new JButton();
		btnStart.setText("开始考试");
		btnStart.setBounds(350, 460, 100, 30);
		container.add(btnStart);

	}

	private void bindEvent() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});

		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnStart_click(e);
			}
		});
	}

	protected void btnStart_click(ActionEvent e) {
		// TODO Auto-generated method stub
		Exam exam = examService.load(pk.longValue());
		java.util.List<PaperItem> piList = paperItemService.listByPaperId(exam.getPaperId());
		PaperItem paperItem = null;
		ExamItem bean = new ExamItem();
		bean.setExamId(pk.longValue());
		Long result;
		for (int i = 0; i < piList.size(); i++) {
			paperItem = piList.get(i);
			bean.setQuestionId(paperItem.getQuestionId());
			bean.setStdAnswer(paperItem.getAnswer());
			bean.setStdScore(paperItem.getScore());
			result = examItemService.insert(bean);
			if (!(result > 0)) {
				JOptionPane.showMessageDialog(null, "操作失败");
				return;
			}
		}

		OnlineExamFrm onlinExamFrm = new OnlineExamFrm();
		onlinExamFrm.pk = pk;
		onlinExamFrm.time = time;
		onlinExamFrm.listFrm = listFrm;
		onlinExamFrm.loadData();
		onlinExamFrm.bindEvent();
		onlinExamFrm.setVisible(true);
		this.setVisible(false);
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (listFrm != null) {
			listFrm.setVisible(true);
			examService.delete(pk.longValue());
			this.dispose();
		}
	}

	private void customLoad() {
		// TODO Auto-generated method stub

	}

}
