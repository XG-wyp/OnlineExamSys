package edu.prj.ui.teacher.paper;

import javax.swing.*;

import com.liuvei.common.*;
import com.liuvei.common.win.*;

import edu.prj.bean.Paper;
import edu.prj.bean.Subject;
import edu.prj.service.*;
import edu.prj.service.impl.*;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 试卷未生成情况下的修改
 * 
 * @author Administrator
 *
 */
public class PaperUpdateFrm extends JFrame {
	private JPanel container;

	private JLabel lblPaperName;// 试卷名称
	private JTextField txtPaperName;

	private JLabel lblSubjectName;
	private JComboBox<IdText> cboSubjectName;

	private JLabel lblTotalScore;
	private JTextField txtTotalScore;

	private JLabel lblQuestionNum;
	private JTextField txtQuestionNum;

	private JLabel lblExamMinute;
	private JTextField txtExamMinute;

	private JLabel lblStartOn;
	private JTextField txtStartYear;
	private JLabel lblAcross0;
	private JTextField txtStartMon;
	private JLabel lblAcross1;
	private JTextField txtStartDay;
	private JLabel lblAcross4;

	private JLabel lblEndOn;
	private JTextField txtEndYear;
	private JLabel lblAcross2;
	private JTextField txtEndMon;
	private JLabel lblAcross3;
	private JTextField txtEndDay;
	private JLabel lblAcross5;

	private JButton btnSubmit;
	private JButton btnReset;

	private JLabel lblMsg;
	PaperListFrm listFrm = null;

	public PaperUpdateFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	public Integer pk;

	public void loadData() {
		if (pk == null) {
			JOptionPane.showMessageDialog(null, "主键值为空，加载数据失败！");
			return;
		}

		Paper bean = paperService.load(pk.longValue());
		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应数据不存在，加载数据失败！");
			return;
		}
		if (!this.getTitle().contains("修改")) {
			this.setTitle(this.getTitle() + "--> 正在修改id为【" + pk + "】的试卷信息");
		}

		if (bean != null) {
			txtPaperName.setText(bean.getPaperName());
			txtExamMinute.setText(bean.getExamMinute().toString());
			cboSubjectName.getModel().setSelectedItem(new IdText(bean.getSubjectId(), ""));
			txtTotalScore.setText(bean.getTotalScore().toString());
			txtQuestionNum.setText(bean.getQuestionNum().toString());

			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

			String[] startOn = df.format(bean.getStartOn()).split("/");
			txtStartYear.setText(startOn[0]);
			txtStartMon.setText(startOn[1]);
			txtStartDay.setText(startOn[2]);

			String[] endOn = df.format(bean.getEndOn()).split("/");
			txtEndYear.setText(endOn[0]);
			txtEndMon.setText(endOn[1]);
			txtEndDay.setText(endOn[2]);
			this.repaint();
		}
	}

	SubjectService subjectService = new SubjectServiceImpl();

	private void bindComBoxDate() {

		java.util.List<IdText> idTextB = new java.util.ArrayList<IdText>();
		java.util.List<Subject> listB = subjectService.list();
		for (Subject item : listB) {
			idTextB.add(new IdText(item.getId(), item.getSubjectName()));
		}
		IdTextModel modelB = new IdTextModel(idTextB);
		cboSubjectName.setModel(modelB);
		cboSubjectName.setSelectedIndex(0);
	}

	private void customInitUI() {

		lblPaperName = new JLabel();
		lblPaperName.setText("试卷名称：");
		lblPaperName.setBounds(100, 20, 80, 30);
		container.add(lblPaperName);

		txtPaperName = new JTextField();
		txtPaperName.setText("");
		txtPaperName.setBounds(180, 20, 120, 30);
		container.add(txtPaperName);

		lblExamMinute = new JLabel();
		lblExamMinute.setText("考试时长/分：");
		lblExamMinute.setBounds(85, 60, 100, 30);
		container.add(lblExamMinute);

		txtExamMinute = new JTextField();
		txtExamMinute.setText("");
		txtExamMinute.setBounds(180, 60, 120, 30);
		container.add(txtExamMinute);

		lblSubjectName = new JLabel();
		lblSubjectName.setText("所属科目：");
		lblSubjectName.setBounds(100, 100, 80, 30);
		container.add(lblSubjectName);

		cboSubjectName = new JComboBox<IdText>();
		cboSubjectName.setBounds(180, 100, 120, 30);
		container.add(cboSubjectName);

		lblTotalScore = new JLabel();
		lblTotalScore.setText("试卷总分：");
		lblTotalScore.setBounds(100, 140, 80, 30);
		container.add(lblTotalScore);

		txtTotalScore = new JTextField();
		txtTotalScore.setText("");
		txtTotalScore.setBounds(180, 140, 120, 30);
		container.add(txtTotalScore);

		lblQuestionNum = new JLabel();
		lblQuestionNum.setText("题目数：");
		lblQuestionNum.setBounds(115, 180, 80, 30);
		container.add(lblQuestionNum);

		txtQuestionNum = new JTextField();
		txtQuestionNum.setText("");
		txtQuestionNum.setBounds(180, 180, 120, 30);
		container.add(txtQuestionNum);

		// 有效开始日期
		lblStartOn = new JLabel();
		lblStartOn.setText("有效开始日期：");
		lblStartOn.setBounds(75, 220, 120, 30);
		container.add(lblStartOn);

		txtStartYear = new JTextField();
		txtStartYear.setText("");
		txtStartYear.setBounds(180, 220, 40, 30);
		container.add(txtStartYear);

		lblAcross0 = new JLabel();
		lblAcross0.setText("年");
		lblAcross0.setBounds(220, 220, 30, 30);
		container.add(lblAcross0);

		txtStartMon = new JTextField();
		txtStartMon.setText("");
		txtStartMon.setBounds(240, 220, 40, 30);
		container.add(txtStartMon);

		lblAcross1 = new JLabel();
		lblAcross1.setText("月");
		lblAcross1.setBounds(280, 220, 120, 30);
		container.add(lblAcross1);

		txtStartDay = new JTextField();
		txtStartDay.setText("");
		txtStartDay.setBounds(300, 220, 40, 30);
		container.add(txtStartDay);

		lblAcross4 = new JLabel();
		lblAcross4.setText("日");
		lblAcross4.setBounds(340, 220, 120, 30);
		container.add(lblAcross4);

		// 有效结束日期
		lblEndOn = new JLabel();
		lblEndOn.setText("有效结束日期：");
		lblEndOn.setBounds(75, 260, 120, 30);
		container.add(lblEndOn);

		txtEndYear = new JTextField();
		txtEndYear.setText("");
		txtEndYear.setBounds(180, 260, 40, 30);
		container.add(txtEndYear);

		lblAcross2 = new JLabel();
		lblAcross2.setText("年");
		lblAcross2.setBounds(220, 260, 120, 30);
		container.add(lblAcross2);

		txtEndMon = new JTextField();
		txtEndMon.setText("");
		txtEndMon.setBounds(240, 260, 40, 30);
		container.add(txtEndMon);

		lblAcross3 = new JLabel();
		lblAcross3.setText("月");
		lblAcross3.setBounds(280, 260, 120, 30);
		container.add(lblAcross3);

		txtEndDay = new JTextField();
		txtEndDay.setText("");
		txtEndDay.setBounds(300, 260, 40, 30);
		container.add(txtEndDay);

		lblAcross5 = new JLabel();
		lblAcross5.setText("日");
		lblAcross5.setBounds(340, 260, 120, 30);
		container.add(lblAcross5);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(160, 300, 60, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(240, 300, 60, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(180, 330, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);

		bindComBoxDate();

	}

	private void customBindEvent() {
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
	}

	private void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		loadData();
	}

	PaperService paperService = new PaperServiceImpl();
	QuestionService questionService = new QuestionServiceImpl();

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String paperName = txtPaperName.getText().trim();
		String strExamMinute = txtExamMinute.getText().trim();
		String strTotalScore = txtTotalScore.getText().trim();
		String strQuestionNum = txtQuestionNum.getText().trim();
		IdText subjectName = (IdText) cboSubjectName.getSelectedItem();
		Long subjectId = subjectName.getId();
		String strStartYear = txtStartYear.getText().trim();
		String strStartMon = txtStartMon.getText().trim();
		String strStartDay = txtStartDay.getText().trim();
		String strEndYear = txtEndYear.getText().trim();
		String strEndMon = txtEndMon.getText().trim();
		String strEndDay = txtEndDay.getText().trim();
		if (paperName == null || paperName.isEmpty()) {
			lblMsg.setText("提示：试卷名称不能为空。");
			return;
		}
		if (strExamMinute == null || strExamMinute.isEmpty()) {
			lblMsg.setText("提示：考试时长不能为空。");
			return;
		}
		Long examMinute = Long.valueOf(strExamMinute);

		if (strTotalScore == null || strTotalScore.isEmpty()) {
			lblMsg.setText("提示：试卷总分不能为空。");
			return;
		}
		Long totalScore = Long.valueOf(strTotalScore);

		if (strQuestionNum == null || strQuestionNum.isEmpty()) {
			lblMsg.setText("提示：题目数不能为空。");
			return;
		}
		Long questionNum = Long.valueOf(strQuestionNum);
		if (questionNum > questionService.countBySubjectId(subjectId)) {
			lblMsg.setText("提示：题库中该科目总题数不足" + questionNum);
			return;
		}
		if (strQuestionNum == null || strQuestionNum.isEmpty()) {
			lblMsg.setText("提示：题目数不能为空。");
			return;
		}
		if (strStartYear == null || strStartYear.isEmpty()) {
			lblMsg.setText("提示：有效开始日期不能为空。");
			return;
		}
		int StartYear = Integer.valueOf(strStartYear);

		if (strStartMon == null || strStartMon.isEmpty()) {
			lblMsg.setText("提示：有效开始日期不能为空。");
			return;
		}
		int StartMon = Integer.valueOf(strStartMon);

		if (strStartDay == null || strStartDay.isEmpty()) {
			lblMsg.setText("提示：有效开始日期不能为空。");
			return;
		}
		int StartDay = Integer.valueOf(strStartDay);
		if (!isLegal(StartYear, StartMon, StartDay, "有效开始")) {
			return;
		}

		if (strEndYear == null || strEndYear.isEmpty()) {
			lblMsg.setText("提示：有效结束日期不能为空。");
			return;
		}
		int EndYear = Integer.valueOf(strEndYear);

		if (strEndMon == null || strEndMon.isEmpty()) {
			lblMsg.setText("提示：有效结束日期不能为空。");
			return;
		}
		int EndMon = Integer.valueOf(strEndMon);

		if (strEndDay == null || strEndDay.isEmpty()) {
			lblMsg.setText("提示：有效结束日期不能为空。");
			return;
		}
		int EndDay = Integer.valueOf(strEndDay);
		if (!isLegal(EndYear, EndMon, EndDay, "有效结束")) {
			return;
		}

		Paper item = paperService.loadByName(paperName);
		if (item != null && item.getId() != pk.longValue()) {
			lblMsg.setText("提示：该试卷已存在。");
			return;
		}

		String startOn = strStartYear + "/" + strStartMon + "/" + strStartDay;

		String endOn = strEndYear + "/" + strEndMon + "/" + strEndDay;

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		Paper bean = new Paper();
		bean.setPaperName(paperName);
		bean.setExamMinute(examMinute);
		bean.setTotalScore(totalScore);
		bean.setPerScore(totalScore / questionNum);
		bean.setQuestionNum(questionNum);
		try {
			bean.setStartOn(df.parse(startOn));
			bean.setEndOn(df.parse(endOn));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bean.setSubjectId(subjectId);
		bean.setId(pk.longValue());
		Long result = 0l;
		String errMsg = null;
		try {
			result = paperService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}

		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功！");
			if (listFrm != null) {
				listFrm.btnReset_clich(null);
				listFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败！");
		}

	}

	private Boolean isLegal(int year, int mon, int day, String str) {

		int[] monthLengh = new int[] { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year < 0) {
			lblMsg.setText("提示：" + str + "年份不合法。");
			return false;
		}
		if (mon < 1 || mon > 12) {
			lblMsg.setText("提示：" + str + "月份不合法。");
			return false;
		}
		Boolean isOK = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
		if (isOK) {
			monthLengh[1] = 29;
		} else {
			monthLengh[1] = 28;
		}
		if (day < 0 || day > monthLengh[mon - 1]) {
			lblMsg.setText("提示：" + str + "日不合法。");
			return false;
		}
		return true;
	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 400;
		int height = 400;
		this.setSize(width, height);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);

		this.setResizable(false);

		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);

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
		if (listFrm != null) {
			listFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customLoad() {
		// TODO Auto-generated method stub
		this.setTitle("修改试卷信息");
	}

}
