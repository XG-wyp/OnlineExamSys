package edu.prj.ui.manager.grade;

import javax.swing.*;

import com.liuvei.common.*;
import com.liuvei.common.win.*;

import edu.prj.bean.Grade;
import edu.prj.bean.Manager;
import edu.prj.service.GradeService;
import edu.prj.service.ManagerService;
import edu.prj.service.impl.GradeServiceImpl;
import edu.prj.service.impl.ManagerServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GradeUpdateFrm extends JFrame {
	private JPanel container;

	private JLabel lblGradeName;
	private JTextField txtGradeName;

	private JButton btnSubmit;
	private JButton btnReset;

	private JLabel lblMsg;
	public String loginName;
	GradeListFrm listFrm = null;

	public GradeUpdateFrm() {
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

		Grade bean = gradeService.load(pk.longValue());
		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应数据不存在，加载数据失败！");
			return;
		}
		if (!this.getTitle().contains("修改")) {
			this.setTitle(this.getTitle() + "--> 正在修改id为【" + pk + "】的教师信息");
		}

		if (bean != null) {
			txtGradeName.setText(bean.getGradeName());

		}
	}

	private void customInitUI() {

		lblGradeName = new JLabel();
		lblGradeName.setText("年级名称：");
		lblGradeName.setBounds(90, 60, 80, 30);
		container.add(lblGradeName);

		txtGradeName = new JTextField();
		txtGradeName.setText("");
		txtGradeName.setBounds(160, 60, 120, 30);
		container.add(txtGradeName);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(100, 130, 60, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(180, 130, 60, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(80, 165, 180, 30);
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

	GradeService gradeService = new GradeServiceImpl();
	ManagerService managerService = new ManagerServiceImpl();

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String gradeName = txtGradeName.getText().trim();

		if (gradeName == null || gradeName.isEmpty()) {
			lblMsg.setText("提示：年级名称不能为空。");
			return;
		}

		Grade item = gradeService.loadByName(gradeName);
		if (item != null && item.getId() != pk.longValue()) {
			lblMsg.setText("提示：该年级已存在。");
			return;
		}

		Grade bean = new Grade();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date updateOn =df.parse(df.format(new Date()));
			bean.setUpdateOn(updateOn);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bean.setGradeName(gradeName);
		bean.setId(pk.longValue());
		Manager mgr = managerService.loadByName(loginName);
		bean.setUpdateBy(mgr.getNickName());
		Long result = 0l;
		String errMsg = null;
		try {
			result = gradeService.update(bean);
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
		this.setTitle("修改年级");
	}

}
