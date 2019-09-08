package edu.prj.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.*;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;

public class LoginFrm extends JFrame {
	ManagerService managerServiceImpl = new ManagerServiceImpl();
	TeacherService teacherService = new TeacherServiceImpl();
	StudentService studentService = new StudentServiceImpl();
	private JPanel container;
	private JLabel lblLoginName;
	private JLabel lblPassword;
	private JLabel lblType;
	private JLabel lblUnderText;
	private JTextField txtLoginName;
	private JPasswordField txtPassword;
	private JComboBox<IdText> cboType;
	private JButton btnSubmit;
	private JButton btnReset;

	public LoginFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	private void bindComBoxData() {
		java.util.List<IdText> idText = new java.util.ArrayList<IdText>();
		idText.add(new IdText(0l, "管理员"));
		idText.add(new IdText(1l, "教师"));
		idText.add(new IdText(2l, "学生"));
		IdTextModel model = new IdTextModel(idText);
		cboType.setModel(model);
		cboType.setSelectedIndex(0);
	}

	public void initUI() {
		int width = 400;
		int height = 300;
		this.setSize(width, height);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);
		this.setTitle("系统登陆");
		this.setResizable(false);

		container = new JPanel();
		container.setLayout(null);// 绝对布局

		this.add(container);

		lblLoginName = new JLabel();
		lblLoginName.setText("账号：");
		lblLoginName.setBounds(80, 30, 80, 50);
		container.add(lblLoginName);

		lblPassword = new JLabel();
		lblPassword.setText("密码：");
		lblPassword.setBounds(80, 80, 80, 50);
		container.add(lblPassword);

		lblType = new JLabel();
		lblType.setText("身份：");
		lblType.setBounds(80, 130, 80, 50);
		container.add(lblType);

		lblUnderText = new JLabel();
		lblUnderText.setBounds(80, 230, 230, 50);
		lblUnderText.setForeground(Color.RED);
		container.add(lblUnderText);

		txtLoginName = new JTextField();
		txtLoginName.setBounds(130, 40, 180, 30);
		container.add(txtLoginName);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(130, 90, 180, 30);
		container.add(txtPassword);

		cboType = new JComboBox<IdText>();
		cboType.setBounds(130, 140, 90, 30);
		container.add(cboType);

		btnSubmit = new JButton();
		btnSubmit.setText("登陆");
		btnSubmit.setBounds(130, 190, 80, 40);
		btnSubmit.setMnemonic(KeyEvent.VK_ENTER);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 190, 80, 40);
		container.add(btnReset);
	}

	public void bindEvent() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSubmit_click(e);
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnReset_click(e);
			}

		});
	}

	public void customLoad() {
		lblUnderText.setText("");
		bindComBoxData();
	}

	public void btnSubmit_click(ActionEvent e) {
		IdText itemType = (IdText) cboType.getSelectedItem();
		Integer type = itemType.getId().intValue();
		String loginName = txtLoginName.getText().trim();
		String loginPass = txtPassword.getText().trim();

		if (loginName.isEmpty() || loginName == null) {
			lblUnderText.setText("提示：请输入账号。");
			return;
		}
		if (loginPass.isEmpty() || loginPass == null) {
			lblUnderText.setText("提示：请输入密码。");
			return;
		}
		loginPass = SysFun.md5(loginPass);
		boolean isPass;
		switch (type) {
		case 0:
			Manager bean = new Manager();
			bean.setLoginName(loginName);
			bean.setLoginPwd(loginPass);
			isPass = managerServiceImpl.login(bean);
			bean = managerServiceImpl.loadByName(loginName);
			if (isPass) {

				if (bean.getIsDisabled() == 1) {
					JOptionPane.showMessageDialog(null, "该用户已被禁用！");
					btnReset_click(e);
					return;
				}
				ManagerMainFrm mainFrm = new ManagerMainFrm();
				mainFrm.loginFrm = this;
				mainFrm.loginName = loginName;
				mainFrm.refreshTitle();
				mainFrm.setVisible(true);
				this.setVisible(false);
				btnReset_click(null);
			}
			break;
		case 1:
			Teacher bean1 = new Teacher();
			bean1.setLoginName(loginName);
			bean1.setLoginPwd(loginPass);
			isPass = teacherService.login(bean1);
			bean1 = teacherService.loadByName(loginName);
			if (isPass) {

				if (bean1.getIsDisabled() == 1) {
					JOptionPane.showMessageDialog(null, "该用户已被禁用！");
					btnReset_click(e);
					return;
				}
				TeacherMainFrm teachermainFrm = new TeacherMainFrm();
				teachermainFrm.loginFrm = this;
				teachermainFrm.loginName = loginName;
				teachermainFrm.refreshTitle();
				teachermainFrm.setVisible(true);
				this.setVisible(false);
				btnReset_click(null);
			}
			break;
		case 2:
			Student bean11 = new Student();
			bean11.setLoginName(loginName);
			bean11.setLoginPwd(loginPass);
			isPass = studentService.login(bean11);
			bean11 = studentService.loadByName(loginName);
			if (isPass) {

				if (bean11.getIsDisabled() == 1) {
					JOptionPane.showMessageDialog(null, "该用户已被禁用！");
					btnReset_click(e);
					return;
				}
				StudentMainFrm studentmainFrm = new StudentMainFrm();
				studentmainFrm.loginFrm = this;
				studentmainFrm.loginName = loginName;
				studentmainFrm.refreshTitle();
				studentmainFrm.setVisible(true);
				this.setVisible(false);
				btnReset_click(null);
			}
			break;
		}
		Manager bean0 = managerServiceImpl.loadByName(loginName, type);
		if (bean0 == null) {
			lblUnderText.setText("登陆失败。101");
			return;
		}
		if (!bean0.getLoginPwd().equals(loginPass)) {
			lblUnderText.setText("登陆失败。102");
			return;

		}
	}

	public void btnReset_click(ActionEvent e) {
		txtLoginName.setText("");
		txtPassword.setText("");
		lblUnderText.setText("");
		bindComBoxData();

	}
}
