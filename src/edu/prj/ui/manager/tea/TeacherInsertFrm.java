package edu.prj.ui.manager.tea;

import javax.swing.*;

import com.liuvei.common.*;
import com.liuvei.common.win.*;

import edu.prj.bean.Teacher;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherServiceImpl;

import java.awt.*;
import java.awt.event.*;

public class TeacherInsertFrm extends JFrame {
	private JPanel container;

	private JLabel lblLoginName;
	private JTextField txtLoginName;

	private JLabel lblLoginPwd;
	private JPasswordField txtLoginPwd;

	private JLabel lblLoginPwd1;
	private JPasswordField txtLoginPwd1;

	private JLabel lblNickName;
	private JTextField txtNickName;

	private JLabel lblIsDisabled;
	private JComboBox<IdText> cboIsDisabled;

	private JButton btnSubmit;
	private JButton btnReset;

	private JLabel lblMsg;
	TeacherListFrm listFrm = null;

	public TeacherInsertFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	private void bindComBoxData() {
		java.util.List<IdText> idText = new java.util.ArrayList<IdText>();
		idText.add(new IdText(0L, "否"));
		idText.add(new IdText(1L, "是"));
		IdTextModel model = new IdTextModel(idText);
		cboIsDisabled.setModel(model);
		cboIsDisabled.setSelectedIndex(0);
	}

	private void customInitUI() {

		lblLoginName = new JLabel();
		lblLoginName.setText("账号：");
		lblLoginName.setBounds(100, 20, 80, 30);
		container.add(lblLoginName);

		txtLoginName = new JTextField();
		txtLoginName.setText("");
		txtLoginName.setBounds(160, 20, 120, 30);
		container.add(txtLoginName);

		lblLoginPwd = new JLabel();
		lblLoginPwd.setText("密码：");
		lblLoginPwd.setBounds(100, 60, 80, 30);
		container.add(lblLoginPwd);

		txtLoginPwd = new JPasswordField();
		txtLoginPwd.setText("");
		txtLoginPwd.setBounds(160, 60, 120, 30);
		container.add(txtLoginPwd);

		lblLoginPwd1 = new JLabel();
		lblLoginPwd1.setText("再次输入密码：");
		lblLoginPwd1.setBounds(50, 100, 100, 30);
		container.add(lblLoginPwd1);

		txtLoginPwd1 = new JPasswordField();
		txtLoginPwd1.setText("");
		txtLoginPwd1.setBounds(160, 100, 120, 30);
		container.add(txtLoginPwd1);

		lblNickName = new JLabel();
		lblNickName.setText("昵称：");
		lblNickName.setBounds(100, 140, 80, 30);
		container.add(lblNickName);

		txtNickName = new JTextField();
		txtNickName.setText("");
		txtNickName.setBounds(160, 140, 120, 30);
		container.add(txtNickName);

		lblIsDisabled = new JLabel();
		lblIsDisabled.setText("是否禁用：");
		lblIsDisabled.setBounds(75, 180, 80, 30);
		container.add(lblIsDisabled);

		cboIsDisabled = new JComboBox<IdText>();
		cboIsDisabled.setBounds(160, 180, 60, 30);
		container.add(cboIsDisabled);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(80, 225, 60, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(160, 225, 60, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(50, 260, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);

		bindComBoxData();

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
		txtLoginPwd.setText("");
		txtLoginName.setText("");
		txtLoginPwd1.setText("");
		txtNickName.setText("");
		bindComBoxData();
	}

	TeacherService teacherService = new TeacherServiceImpl();

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String loginName = txtLoginName.getText().trim();
		String loginPwd = txtLoginPwd.getText().trim();
		String loginPwd1 = txtLoginPwd1.getText().trim();
		String nickName = txtNickName.getText().trim();
		IdText itemIsDisabled = (IdText) cboIsDisabled.getSelectedItem();
		Long isDisabled = itemIsDisabled.getId();
		if (loginName == null || loginName.isEmpty()) {
			lblMsg.setText("提示：账号不能为空。");
			return;
		}
		if (loginPwd == null || loginPwd.isEmpty()) {
			lblMsg.setText("提示：密码不能为空。");
			return;
		}
		if (!loginPwd.equals(loginPwd1)) {
			lblMsg.setText("提示：两次密码输入不一致。");
			return;
		}
		if (nickName == null || nickName.isEmpty()) {
			nickName = loginName;
		}
		Teacher item = teacherService.loadByName(loginName);
		if (item != null) {
			lblMsg.setText("提示：该账户已存在。");
			return;
		}

		Teacher bean = new Teacher();
		bean.setLoginName(loginName);
		bean.setLoginPwd(SysFun.md5(loginPwd));
		bean.setNickName(nickName);
		bean.setIsDisabled(isDisabled);
		Long result = 0l;
		String errMsg = null;
		try {
			result = teacherService.insert(bean);
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

		}

	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 400;
		int height = 330;
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
		this.setTitle("添加教师信息");
	}

}
