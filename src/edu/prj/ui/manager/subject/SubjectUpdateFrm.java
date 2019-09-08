package edu.prj.ui.manager.subject;

import javax.swing.*;

import com.liuvei.common.*;
import com.liuvei.common.win.*;

import edu.prj.bean.Subject;
import edu.prj.service.SubjectService;
import edu.prj.service.impl.SubjectServiceImpl;

import java.awt.*;
import java.awt.event.*;

public class SubjectUpdateFrm extends JFrame {
	private JPanel container;

	private JLabel lblSubjectName;
	private JTextField txtSubjectName;

	private JLabel lblStatus;
	private JTextField txtStatus;

	private JButton btnSubmit;
	private JButton btnReset;

	private JLabel lblMsg;
	SubjectListFrm listFrm = null;

	public SubjectUpdateFrm() {
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

		Subject bean = subjectService.load(pk.longValue());
		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应数据不存在，加载数据失败！");
			return;
		}
		if (!this.getTitle().contains("修改")) {
			this.setTitle(this.getTitle() + "--> 正在修改id为【" + pk + "】的教师信息");
		}

		if (bean != null) {
			txtSubjectName.setText(bean.getSubjectName());

			txtStatus.setText(bean.getStatus());

		}
	}

	private void customInitUI() {

		lblSubjectName = new JLabel();
		lblSubjectName.setText("科目名称：");
		lblSubjectName.setBounds(100, 20, 80, 30);
		container.add(lblSubjectName);

		txtSubjectName = new JTextField();
		txtSubjectName.setText("");
		txtSubjectName.setBounds(180, 20, 120, 30);
		container.add(txtSubjectName);

		lblStatus = new JLabel();
		lblStatus.setText("状态：");
		lblStatus.setBounds(100, 80, 80, 30);
		container.add(lblStatus);

		txtStatus = new JTextField();
		txtStatus.setText("");
		txtStatus.setBounds(180, 80, 120, 30);
		container.add(txtStatus);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(160, 130, 60, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(240, 130, 60, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(180, 165, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);


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
		lblMsg.setText("");
	}

	SubjectService subjectService = new SubjectServiceImpl();

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String subjectName = txtSubjectName.getText().trim();
		String status = txtStatus.getText().trim();
		if (subjectName == null || subjectName.isEmpty()) {
			lblMsg.setText("提示：科目名称不能为空。");
			return;
		}

		Subject item = subjectService.loadByName(subjectName);
		if (item != null && item.getId() != pk.longValue()) {
			lblMsg.setText("提示：该科目已存在。");
			return;
		}

		Subject bean = new Subject();
		bean.setId(pk.longValue());
		bean.setSubjectName(subjectName);
		bean.setStatus(status);
		Long result = 0l;
		String errMsg = null;
		try {
			result = subjectService.update(bean);
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
			lblMsg.setText("操作失败");
		}

	}

	private void initUI() {
		// TODO Auto-generated method stub
		int width = 400;
		int height = 250;
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
		this.setTitle("修改科目信息");
	}

}
