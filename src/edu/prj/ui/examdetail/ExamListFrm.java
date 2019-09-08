package edu.prj.ui.examdetail;

import javax.swing.*;
import javax.swing.table.*;
import com.liuvei.common.win.*;
import java.util.*;
import java.util.List;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.*;

import java.awt.*;
import java.awt.event.*;

public class ExamListFrm extends JFrame {
	public TeacherMainFrm teaMainFrm = null;
	public ManagerMainFrm mgrMainFrm = null;
	private JPanel container;
	private JLabel lblTitle;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JLabel lblSearch1;
	private JTextField txtSearch1;
	private JButton btnSearch;
	private JButton btnReset;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JPopupMenu tblObjMenu = null;

	public String loginName;

	public ExamListFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();

		JMenuItem detailsMenuItem = new JMenuItem("试卷详情");
		tblObjMenu.add(detailsMenuItem);
		detailsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				detailsMenuItem_click(e);
			}
		});
	}

	protected void detailsMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = tblObj.getSelectedRow();
		if (index != -1) {
			TableModel model = tblObj.getModel();
			Object obj = model.getValueAt(index, 0);
			Integer pk = Integer.valueOf(obj.toString());

			if (pk > 0) {
				PaperDetailsFrm frm = new PaperDetailsFrm();
				frm.listFrm = this;
				frm.pk = pk;
				frm.loadData();
				frm.setVisible(true);
				this.setVisible(false);
			}
		}
	}

	private void initTableUI() {
		tblObj = new JTable();
		int height = 300;
		pnlTablePane = new JScrollPane(tblObj);
		pnlTablePane.setBounds(10, 100, 760, height);
		container.add(pnlTablePane);

		showListData();
		createTblObjMenu();
	}

	StudentService studentService = new StudentServiceImpl();

	private void showListData() {
		ExamService examService = new ExamServiceImpl();
		List<Exam> list = null;
		String subjectName = txtSearch.getText().trim();
		String nickName = txtSearch1.getText().trim();
		list = examService.listByName(subjectName, nickName);
		ATableModel<Exam> tableModel = null;
		tableModel = new ATableModel<Exam>(list, 6) {

			@Override
			public Object getPropValue(Exam arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == 0) {
					return arg0.getId();
				} else if (arg1 == 1) {
					return arg0.getStudentId();
				} else if (arg1 == 2) {
					return arg0.getStudentName();
				} else if (arg1 == 3) {
					return arg0.getSubjectName();
				} else if (arg1 == 4) {
					return arg0.getPaperName();
				} else if (arg1 == 5) {
					return arg0.getTotalScore();
				}
				return null;
			}

			@Override
			public String getTitle(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 0) {
					return "id";
				} else if (arg0 == 1) {
					return "学生Id";
				} else if (arg0 == 2) {
					return "学生姓名";
				} else if (arg0 == 3) {
					return "科目";
				} else if (arg0 == 4) {
					return "试卷名";
				} else if (arg0 == 5) {
					return "总分";
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

		this.setResizable(true);

		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);

		lblTitle = new JLabel();
		lblTitle.setText("成绩信息");
		lblTitle.setFont(new Font("楷体", Font.BOLD, 26));
		lblTitle.setBounds(300, 5, 300, 80);
		container.add(lblTitle);

		lblSearch = new JLabel();
		lblSearch.setText("科目：");
		lblSearch.setBounds(20, 460, 40, 30);
		container.add(lblSearch);

		lblSearch1 = new JLabel();
		lblSearch1.setText("昵称：");
		lblSearch1.setBounds(20, 500, 40, 30);
		container.add(lblSearch1);

		// 搜索文本框
		txtSearch = new JTextField();
		txtSearch.setBounds(70, 460, 180, 30);
		container.add(txtSearch);

		txtSearch1 = new JTextField();
		txtSearch1.setBounds(70, 500, 180, 30);
		container.add(txtSearch1);

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
			if (teaMainFrm != null) {
				this.dispose();// 关闭当前窗体
				teaMainFrm.setVisible(true);
			}
			if (mgrMainFrm != null) {
				this.dispose();// 关闭当前窗体
				mgrMainFrm.setVisible(true);
			}
		}
	}

	public void btnReset_clich(ActionEvent e) {
		// TODO Auto-generated method stub
		txtSearch.setText("");
		txtSearch1.setText("");
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
