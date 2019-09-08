package edu.prj.ui;

import javax.swing.*;

import edu.prj.ui.manager.ManagerListFrm;
import edu.prj.ui.manager.classroom.ClassroomListFrm;
import edu.prj.ui.manager.grade.GradeListFrm;
import edu.prj.ui.manager.stu.StudentListFrm;
import edu.prj.ui.manager.subject.SubjectListFrm;
import edu.prj.ui.manager.tea.TeacherListFrm;
import edu.prj.ui.student.exam.ExamListFrm;
import edu.prj.ui.student.exam.OnlinePaperListFrm;
import edu.prj.ui.teacher.question.QuestionListFrm;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;

public class StudentMainFrm extends JFrame {
	private JPanel container;
	private JMenuBar jmbTop;
	private JMenu menuA;// 系统管理
	private JMenuItem menuA1;// 修改密码
	private JMenuItem menuA2;// 注销
	private JMenuItem menuA3;// 退出
	private JMenu menuB;// 考试管理
	private JMenuItem menuB1;// 开始考试
	private JMenuItem menuB3;// 成绩查询
	private JMenu menuC;// 帮助
	private JMenuItem menuC1;// 帮助
	private JMenuItem menuC2;// 关于

	public String loginName = null;
	public LoginFrm loginFrm = null;

	public StudentMainFrm() {
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

		menuA.addSeparator();

		menuA1 = new JMenuItem();
		menuA1.setText("修改密码");
		menuA.add(menuA1);

		menuA.addSeparator();

		menuA2 = new JMenuItem();
		menuA2.setText("注销");
		menuA2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		menuA.add(menuA2);

		menuA3 = new JMenuItem();
		menuA3.setText("退出");
		menuA3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		menuA.add(menuA3);

		menuB = new JMenu();
		menuB.setText("在线考试(A)");
		menuB.setMnemonic(KeyEvent.VK_A);
		jmbTop.add(menuB);

		menuB1 = new JMenuItem();
		menuB1.setText("考试");
		menuB1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK));
		menuB.add(menuB1);

		menuB.addSeparator();

		menuB3 = new JMenuItem();
		menuB3.setText("成绩查询");
		menuB.add(menuB3);

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

		menuA1.addActionListener(new ActionListener() {
			/**
			 * 修改密码
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuA2_click(e);
			}
		});

		menuA2.addActionListener(new ActionListener() {
			/**
			 * 注销
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuA3_click(e);
			}
		});

		menuA3.addActionListener(new ActionListener() {
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
			 * 开始考试
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuB1_click(e);
			}
		});

		menuB3.addActionListener(new ActionListener() {
			/**
			 * 成绩查询
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuB3_click(e);
			}
		});
	}

	protected void menuB3_click(ActionEvent e) {
		// TODO Auto-generated method stub
		ExamListFrm examListFrm = new ExamListFrm(loginName);
		examListFrm.mainFrm = this;
		examListFrm.setVisible(true);
		this.setVisible(false);
	}

	protected void menuB1_click(ActionEvent e) {
		// TODO Auto-generated method stub
		OnlinePaperListFrm opListFrm = new OnlinePaperListFrm();
		opListFrm.mainFrm = this;
		opListFrm.loginName = loginName;
		opListFrm.setVisible(true);
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
		StuChangePwdFrm cpFrm = new StuChangePwdFrm();
		cpFrm.loginName = loginName;
		cpFrm.mainFrm = this;
		cpFrm.setVisible(true);
		this.setVisible(false);

	}

	private void customLoad() {
		// TODO Auto-generated method stub

	}

	public void refreshTitle() {
		this.setTitle(this.getTitle() + "-【" + loginName + "】");
	}

}
