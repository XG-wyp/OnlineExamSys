package edu.prj.ui.student.exam;

import javax.swing.*;
import javax.swing.table.*;
import com.liuvei.common.win.*;
import java.util.*;
import java.util.List;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.*;
import edu.prj.ui.manager.*;
import edu.prj.ui.teacher.paper.PaperInsertFrm;
import edu.prj.ui.teacher.paper.PaperUpdateFrm;
import edu.prj.ui.teacher.paper.PaperUpdateFrm2;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class OnlinePaperListFrm extends JFrame {
	public StudentMainFrm mainFrm = null;
	private JPanel container;
	private JLabel lblTitle;
	private JLabel lblSearch;
	private JTextField txtSearch;

	private JButton btnSearch;
	private JButton btnReset;
	private JButton btnInsert;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JPopupMenu tblObjMenu = null;

	public LoginFrm loginFrm = null;
	public String nickName;
	public String loginName;

	public OnlinePaperListFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();
		

		JMenuItem examMenuItem = new JMenuItem("开始考试");
		tblObjMenu.add(examMenuItem);
		examMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				examMenuItem_click(e);
			}
		});

	}

	StudentService studentService = new StudentServiceImpl();
	ExamService examService = new ExamServiceImpl();

	protected void examMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = tblObj.getSelectedRow();
		if (index != -1) {
			TableModel model = tblObj.getModel();
			Object obj = model.getValueAt(index, 0);
			Long paperId = Long.valueOf(obj.toString());
			Paper paper = paperService.load(paperId);
			if (!isInclude(paper)) {
				JOptionPane.showMessageDialog(null, "不在规定时间内，无法考此卷。");
				return;
			}
			Student stu = studentService.loadByName(loginName);
			Exam item = examService.load(paperId, stu.getId());
			if (item != null) {
				JOptionPane.showMessageDialog(null, "该卷已考过，不能再考");
				return;
			}
			Exam bean = new Exam();
			bean.setPaperId(paperId);
			bean.setStudentId(stu.getId());
			bean.setSubjectId(paper.getSubjectId());
			bean.setStartOn(paper.getStartOn());
			bean.setEndOn(paper.getEndOn());
			Long result = examService.insert(bean);
			if (!(result > 0)) {
				JOptionPane.showMessageDialog(null, "操作失败。");
				return;
			}
			bean = examService.load(result);
			Long pk = bean.getId();
			if (pk > 0) {
				ReadyExamFrm reFrm = new ReadyExamFrm();
				reFrm.pk = pk.intValue();
				reFrm.listFrm = this;
				reFrm.loadData();
				reFrm.setVisible(true);
				this.setVisible(false);
			}
		}
	}

	private Boolean isInclude(Paper paper) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String[] startOn = df.format(paper.getStartOn()).split("/");
		String[] nowOn = df.format(new Date()).split("/");
		String[] endOn = df.format(paper.getEndOn()).split("/");

		int[] start = strToInt(startOn);
		int[] now = strToInt(nowOn);
		int[] end = strToInt(endOn);
		if (now[0] < start[0] || now[0] > end[0]) {
			return false;
		}
		if (now[1] < start[1] || now[1] > end[1]) {
			return false;
		}
		if (now[2] < start[2] || now[2] > end[2]) {
			return false;
		}

		return true;
	}

	private int[] strToInt(String[] str) {
		int[] time = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			time[i] = Integer.valueOf(str[i]);
		}
		return time;
	}

	PaperService paperService = new PaperServiceImpl();
	QuestionService questionService = new QuestionServiceImpl();
	PaperItemService paperItemService = new PaperItemServiceImpl();

	

	private void initTableUI() {
		tblObj = new JTable();
		pnlTablePane = new JScrollPane(tblObj);
		pnlTablePane.setBounds(8, 100, 780, 300);
		container.add(pnlTablePane);

		showListData();
		createTblObjMenu();
	}

	private void showListData() {
		PaperService paperService = new PaperServiceImpl();
		List<Paper> list = null;
		String paperName = txtSearch.getText().trim();
		list = paperService.listForStudent(paperName);
		ATableModel<Paper> tableModel = null;
		tableModel = new ATableModel<Paper>(list, 5) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

			@Override
			public Object getPropValue(Paper arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == 0) {
					return arg0.getId();
				} else if (arg1 == 1) {
					return arg0.getPaperName();
				} else if (arg1 == 2) {
					return arg0.getSubjectName();
				} else if (arg1 == 3) {
					return df.format(arg0.getStartOn());
				} else if (arg1 == 4) {
					return df.format(arg0.getEndOn());
				}
				return null;
			}

			@Override
			public String getTitle(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 0) {
					return "Id";
				} else if (arg0 == 1) {
					return "试卷名称";
				} else if (arg0 == 2) {
					return "科目名称";
				} else if (arg0 == 3) {
					return "有效开始时间";
				} else if (arg0 == 4) {
					return "有效结束时间";
				}
				return null;
			}

		};
		tblObj.setModel(tableModel);
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

		lblTitle = new JLabel();
		lblTitle.setText("试卷信息");
		lblTitle.setFont(new Font("楷体", Font.BOLD, 26));
		lblTitle.setBounds(300, 5, 300, 80);
		container.add(lblTitle);

		lblSearch = new JLabel();
		lblSearch.setText("试卷名称：");
		lblSearch.setBounds(5, 460, 80, 30);
		container.add(lblSearch);

		// 搜索文本框
		txtSearch = new JTextField();
		txtSearch.setBounds(70, 460, 180, 30);
		container.add(txtSearch);

		// 搜索按钮
		btnSearch = new JButton();
		btnSearch.setText("查询");
		btnSearch.setBounds(260, 460, 70, 30);
		container.add(btnSearch);

		// 重置按钮
		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(340, 460, 70, 30);
		container.add(btnReset);

		

		initTableUI();
	}

	private void bindEvent() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});
		btnSearch.addActionListener(e -> {
			btnSearch_clich(e);
		});

		btnReset.addActionListener(e -> {
			btnReset_clich(e);
		});

		
		tblObj.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					int focusedRowIndex = tblObj.rowAtPoint(e.getPoint());
					if (focusedRowIndex == -1) {
						return;
					}
					tblObj.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);

					tblObjMenu.show(tblObj, e.getX(), e.getY());
				}
			}
		});
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (e.getWindow() == this) {
			if (mainFrm != null) {
				this.dispose();// 关闭当前窗体
				mainFrm.setVisible(true);
			}
		}
	}

	
	public void btnReset_clich(ActionEvent e) {
		// TODO Auto-generated method stub
		txtSearch.setText("");
		showListData();
	}

	public void btnSearch_clich(ActionEvent e) {
		// TODO Auto-generated method stub
		showListData();
	}

	private void customLoad() {
		// TODO Auto-generated method stub

	}

}
