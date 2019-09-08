package edu.prj.ui.manager.classroom;

import javax.swing.*;

import com.liuvei.common.*;
import com.liuvei.common.win.*;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;

import java.awt.*;
import java.awt.event.*;

public class ClassroomUpdateFrm extends JFrame {
	private JPanel container;

	private JLabel lblRoomName;
	private JTextField txtRoomName;

	private JLabel lblGradeName;
	private JComboBox<IdText> cboGradeName;

	private JButton btnSubmit;
	private JButton btnReset;

	private JLabel lblMsg;
	ClassroomListFrm listFrm = null;

	public ClassroomUpdateFrm() {
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

		Classroom bean = classroomService.load(pk.longValue());
		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应数据不存在，加载数据失败！");
			return;
		}
		if (!this.getTitle().contains("修改")) {
			this.setTitle(this.getTitle() + "--> 正在修改id为【" + pk + "】的教师信息");
		}

		if (bean != null) {
			txtRoomName.setText(bean.getRoomName());
			cboGradeName.getModel().setSelectedItem(new IdText(bean.getGradeId(), ""));
			this.repaint();
		}
	}

	GradeService gradeService = new GradeServiceImpl();

	private void bindComBoxData() {
		java.util.List<IdText> idText = new java.util.ArrayList<IdText>();
		java.util.List<Grade> list = gradeService.list();
		for (Grade item : list) {
			idText.add(new IdText(item.getId(), item.getGradeName()));
		}
		IdTextModel model = new IdTextModel(idText);
		cboGradeName.setModel(model);
	}

	private void customInitUI() {

		lblRoomName = new JLabel();
		lblRoomName.setText("班级名称：");
		lblRoomName.setBounds(75, 20, 80, 30);
		container.add(lblRoomName);

		txtRoomName = new JTextField();
		txtRoomName.setText("");
		txtRoomName.setBounds(160, 20, 120, 30);
		container.add(txtRoomName);

		lblGradeName = new JLabel();
		lblGradeName.setText("年级名称：");
		lblGradeName.setBounds(75, 82, 80, 30);
		container.add(lblGradeName);

		cboGradeName = new JComboBox<IdText>();
		cboGradeName.setBounds(160, 80, 80, 30);
		container.add(cboGradeName);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(80, 140, 60, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(160, 140, 60, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
//		lblMsg.setText("提示信息");
		lblMsg.setBounds(80, 175, 180, 30);
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
		bindComBoxData();
		loadData();
		lblMsg.setText("");
	}

	ClassroomService classroomService = new ClassroomServiceImpl();

	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String roomName = txtRoomName.getText().trim();
		IdText itemGradeId = (IdText) cboGradeName.getSelectedItem();
		Long gradeId = itemGradeId.getId();
		if (roomName == null || roomName.isEmpty()) {
			lblMsg.setText("提示：班级名称不能为空。");
			return;
		}

		Classroom item = classroomService.loadByName(roomName);
		if (item != null && item.getId() != pk.longValue()) {
			lblMsg.setText("提示：该班级已存在。");
			return;
		}

		Classroom bean = new Classroom();
		bean.setId(pk.longValue());
		bean.setRoomName(roomName);
		bean.setGradeId(gradeId);
		Long result = 0l;
		String errMsg = null;
		try {
			result = classroomService.update(bean);
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
		this.setTitle("修改班级信息");
	}

}
