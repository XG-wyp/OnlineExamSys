package edu.prj.ui;

import javax.swing.*;

import com.liuvei.common.SysFun;

import edu.prj.bean.Student;
import edu.prj.service.StudentService;
import edu.prj.service.impl.StudentServiceImpl;

import java.awt.*;
import java.awt.event.*;

public class StuChangePwdFrm extends JFrame {
	private JPanel container;

	private JLabel lblOldPwd;
	private JPasswordField txtOldPwd;

	private JLabel lblNewPwd;
	private JPasswordField txtNewPwd;

	private JLabel lblNewPwd1;
	private JPasswordField txtNewPwd1;

	private JButton btnSubmit;
	private JButton btnReset;

	private JLabel lblMsg;

	public String loginName = null;
	public StudentMainFrm mainFrm = null;

	public StuChangePwdFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 400;
		int height = 300;
		this.setSize(width, height);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x, y);

		this.setResizable(false);

		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);

		lblOldPwd = new JLabel();
		lblOldPwd.setText("旧密码：");
		lblOldPwd.setBounds(120, 50, 80, 30);
		container.add(lblOldPwd);

		txtOldPwd = new JPasswordField();
		txtOldPwd.setBounds(180, 50, 120, 30);
		container.add(txtOldPwd);

		lblNewPwd = new JLabel();
		lblNewPwd.setText("新密码：");
		lblNewPwd.setBounds(120, 90, 80, 30);
		container.add(lblNewPwd);

		txtNewPwd = new JPasswordField();
		txtNewPwd.setBounds(180, 90, 120, 30);
		container.add(txtNewPwd);

		lblNewPwd1 = new JLabel();
		lblNewPwd1.setText("再次输入新密码：");
		lblNewPwd1.setBounds(70, 130, 110, 30);
		container.add(lblNewPwd1);

		txtNewPwd1 = new JPasswordField();
		txtNewPwd1.setBounds(180, 130, 120, 30);
		container.add(txtNewPwd1);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(120, 200, 60, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(200, 200, 60, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(90, 235, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);

	}

	private void bindEvent() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (mainFrm != null) {
			mainFrm.setVisible(true);
		}
		this.dispose();
	}

	private void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		txtOldPwd.setText("");
		txtNewPwd.setText("");
		txtNewPwd1.setText("");
	}

	StudentService studentService = new StudentServiceImpl();

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		Student bean = studentService.loadByName(loginName);
		String oldPwd = txtOldPwd.getText().trim();
		String newPwd = txtNewPwd.getText().trim();
		String newPwd1 = txtNewPwd1.getText().trim();

		if (oldPwd == null || oldPwd.isEmpty()) {
			lblMsg.setText("旧密码不能为空。");
			return;
		}
		if (!SysFun.md5(oldPwd).equals(bean.getLoginPwd())) {
			lblMsg.setText("旧密码不正确。");
			return;
		}
		if (newPwd == null || newPwd.isEmpty()) {
			lblMsg.setText("新密码不能为空。");
			return;
		}
		if (!newPwd.equals(newPwd1)) {
			lblMsg.setText("两次新密码不一致");
			return;
		}
		bean.setLoginPwd(SysFun.md5(newPwd));
		Long result = studentService.update(bean);
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功！");
			if (mainFrm != null) {
				mainFrm.setVisible(true);
			}
			this.dispose();
		} else {

		}

	}

	private void customLoad() {
		// TODO Auto-generated method stub
		this.setTitle("修改密码");
	}

}
