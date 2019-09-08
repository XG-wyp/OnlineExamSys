package edu.prj.ui;

import javax.swing.*;

import edu.prj.ui.examdetail.ExamListFrm;
import edu.prj.ui.manager.ManagerListFrm;
import edu.prj.ui.manager.classroom.ClassroomListFrm;
import edu.prj.ui.manager.grade.GradeListFrm;
import edu.prj.ui.manager.stu.StudentListFrm;
import edu.prj.ui.manager.subject.SubjectListFrm;
import edu.prj.ui.manager.tea.TeacherListFrm;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;

public class ManagerMainFrm extends JFrame {
	private JPanel container;
	private JMenuBar jmbTop;
	private JMenu menuA;// 系统管理
	private JMenu menuAa;
	private JMenuItem menuAa1;
	private JMenuItem menuAa2;
	private JMenuItem menuAa3;
	private JMenuItem menuA2;
	private JMenuItem menuA3;
	private JMenuItem menuA4;
	private JMenu menuB;// 基本管理
	private JMenuItem menuB1;
	private JMenuItem menuB2;
	private JMenuItem menuB3;
	private JMenuItem menuB4;
	private JMenu menuC;// 帮助
	private JMenuItem menuC1;
	private JMenuItem menuC2;

	public String loginName = null;
	public LoginFrm loginFrm = null;

	public ManagerMainFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 800;
		int height = 600;
		this.setSize(width, height);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);

		this.setTitle("主菜单");
		this.setResizable(false);

		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(new BorderLayout());
		initMenuBar();
	}

	private void initMenuBar() {
		jmbTop = new JMenuBar();
		jmbTop.setBounds(0, 0, 800, 20);
		container.add(jmbTop, "North");

		menuA = new JMenu();
		menuA.setText("系统管理(S)");
		menuA.setMnemonic(KeyEvent.VK_S);
		jmbTop.add(menuA);

		menuAa = new JMenu();
		menuAa.setText("用户管理");
		menuA.add(menuAa);

		menuAa1 = new JMenuItem();
		menuAa1.setText("管理员管理");
		menuAa1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK));
		menuAa.add(menuAa1);

		menuAa2 = new JMenuItem();
		menuAa2.setText("教师管理");
		menuAa2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK));
		menuAa.add(menuAa2);

		menuAa3 = new JMenuItem();
		menuAa3.setText("学生管理");
		menuAa3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		menuAa.add(menuAa3);

		menuA.addSeparator();

		menuA2 = new JMenuItem();
		menuA2.setText("修改密码");
		menuA.add(menuA2);

		menuA.addSeparator();

		menuA3 = new JMenuItem();
		menuA3.setText("注销");
		menuA3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		menuA.add(menuA3);

		menuA4 = new JMenuItem();
		menuA4.setText("退出");
		menuA4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		menuA.add(menuA4);

		menuB = new JMenu();
		menuB.setText("基本管理(A)");
		menuB.setMnemonic(KeyEvent.VK_A);
		jmbTop.add(menuB);

		menuB1 = new JMenuItem();
		menuB1.setText("年级管理");
		menuB1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
		menuB.add(menuB1);

		menuB2 = new JMenuItem();
		menuB2.setText("班级管理");
		menuB2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		menuB.add(menuB2);

		menuB3 = new JMenuItem();
		menuB3.setText("科目管理");
		menuB3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK));
		menuB.add(menuB3);

		menuB.addSeparator();

		menuB4 = new JMenuItem();
		menuB4.setText("成绩查询");
		menuB.add(menuB4);

		menuC = new JMenu();
		menuC.setText("帮助(H)");
		menuC.setMnemonic(KeyEvent.VK_H);
		jmbTop.add(menuC);

		menuC1 = new JMenuItem();
		menuC1.setText("帮助");
		menuC.add(menuC1);

		menuC2 = new JMenuItem();
		menuC2.setText("关于");
		menuC.add(menuC2);
	}

	private void bindEvent() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});

		menuAa1.addActionListener(new ActionListener() {
			/**
			 * 管理员管理
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuAa1_click(e);
			}
		});

		menuAa2.addActionListener(new ActionListener() {
			/**
			 * 教师管理
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuAa2_click(e);
			}
		});

		menuAa3.addActionListener(new ActionListener() {
			/**
			 * 学生管理
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuAa3_click(e);
			}
		});

		menuA2.addActionListener(new ActionListener() {
			/**
			 * 修改密码
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuA2_click(e);
			}
		});

		menuA3.addActionListener(new ActionListener() {
			/**
			 * 注销
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuA3_click(e);
			}
		});

		menuA4.addActionListener(new ActionListener() {
			/**
			 * 退出
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuA4_click(e);
			}
		});

		menuB1.addActionListener(new ActionListener() {
			/**
			 * 年级管理
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuB1_click(e);
			}
		});

		menuB2.addActionListener(new ActionListener() {
			/**
			 * 班级管理
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuB2_click(e);
			}
		});

		menuB3.addActionListener(new ActionListener() {
			/**
			 * 科目管理
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuB3_click(e);
			}
		});

		menuB4.addActionListener(new ActionListener() {
			/**
			 * 成绩查询
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuB4_click(e);
			}
		});
	}

	protected void menuB4_click(ActionEvent e) {
		// TODO Auto-generated method stub
		ExamListFrm examListFrm = new ExamListFrm();
		examListFrm.loginName = loginName;
		examListFrm.mgrMainFrm = this;
		examListFrm.setVisible(true);
		this.setVisible(false);
	}

	protected void menuB3_click(ActionEvent e) {
		// TODO Auto-generated method stub
		SubjectListFrm subjectListFrm = new SubjectListFrm();
		subjectListFrm.mainFrm = this;
		subjectListFrm.setVisible(true);
		this.setVisible(false);
	}

	protected void menuB2_click(ActionEvent e) {
		// TODO Auto-generated method stub
		ClassroomListFrm classroomList = new ClassroomListFrm();
		classroomList.mainFrm = this;
		classroomList.setVisible(true);
		this.setVisible(false);

	}

	protected void menuB1_click(ActionEvent e) {
		// TODO Auto-generated method stub
		GradeListFrm gradeListFrm = new GradeListFrm();
		gradeListFrm.loginName = loginName;
		gradeListFrm.mainFrm = this;
		gradeListFrm.setVisible(true);
		this.setVisible(false);
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		int option = JOptionPane.showConfirmDialog(this, "确定注销系统？", "提示", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			if (e.getWindow() == this) {
				if (loginFrm != null) {
					this.dispose();// 关闭当前窗体
					loginFrm.setVisible(true);
				}
			}
		}
	}

	protected void menuA4_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int option = JOptionPane.showConfirmDialog(this, "确定退出系统？", "提示", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	protected void menuA3_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int option = JOptionPane.showConfirmDialog(this, "确定注销系统？", "提示", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			if (loginFrm != null) {
				this.dispose();// 关闭当前窗体
				loginFrm.setVisible(true);
			}
		}
	}

	protected void menuA2_click(ActionEvent e) {
		// TODO Auto-generated method stub
		MgrChangePwdFrm cpFrm = new MgrChangePwdFrm();
		cpFrm.loginName = loginName;
		cpFrm.mainFrm = this;
		cpFrm.setVisible(true);
		this.setVisible(false);

	}

	protected void menuAa3_click(ActionEvent e) {
		// TODO Auto-generated method stub
		StudentListFrm studentListFrm = new StudentListFrm();
		studentListFrm.mainFrm = this;
		studentListFrm.setVisible(true);
		this.setVisible(false);
	}

	protected void menuAa2_click(ActionEvent e) {
		// TODO Auto-generated method stub
		TeacherListFrm teacherListFrm = new TeacherListFrm();
		teacherListFrm.mainFrm = this;
		teacherListFrm.setVisible(true);
		this.setVisible(false);
	}

	protected void menuAa1_click(ActionEvent e) {
		// TODO Auto-generated method stub
		ManagerListFrm mangerListFrm = new ManagerListFrm();
		mangerListFrm.mainFrm = this;
		mangerListFrm.setVisible(true);
		this.setVisible(false);
	}

	private void customLoad() {
		// TODO Auto-generated method stub

	}

	public void refreshTitle() {
		this.setTitle(this.getTitle() + "-【" + loginName + "】");
	}

}
