package edu.prj.ui.teacher.paper;

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

import java.awt.*;
import java.awt.event.*;

public class PaperListFrm extends JFrame {
	public TeacherMainFrm mainFrm = null;
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

	public PaperListFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();
		JMenuItem deleteMenuItem = new JMenuItem("删除");
		tblObjMenu.add(deleteMenuItem);
		deleteMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteMenuItem_click(e);
			}

		});

		JMenuItem updateMenuItem = new JMenuItem("修改");
		tblObjMenu.add(updateMenuItem);
		updateMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateMenuItem_click(e);
			}
		});

		JMenuItem generateMenuItem = new JMenuItem("生成试卷");
		tblObjMenu.add(generateMenuItem);
		generateMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				generateMenuItem_click(e);
			}
		});

		JMenuItem detailMenuItem = new JMenuItem("试卷试题详情");
		tblObjMenu.add(detailMenuItem);
		detailMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				detailMenuItem_click(e);
			}
		});
	}

	protected void detailMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = tblObj.getSelectedRow();
		if (index != -1) {
			TableModel model = tblObj.getModel();
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf(obj.toString());
			Object obj0 = model.getValueAt(index, 8);
			Long hasGenerate = Long.valueOf(obj0.toString());
			if (hasGenerate == 0) {
				JOptionPane.showMessageDialog(null, "该卷未生成，无法查看试题详情。");
				return;
			}
			if (pk > 0) {
				PaperItemDetailsFrm frm = new PaperItemDetailsFrm();
				frm.listFrm = this;
				frm.pk = pk.intValue();
				frm.loadData();
				frm.setVisible(true);
				this.setVisible(false);
			}

		}

	}

	PaperService paperService = new PaperServiceImpl();
	QuestionService questionService = new QuestionServiceImpl();
	PaperItemService paperItemService = new PaperItemServiceImpl();

	protected void generateMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = tblObj.getSelectedRow();
		if (index != -1) {
			TableModel model = tblObj.getModel();
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf(obj.toString());
			Paper paper = paperService.loadById(pk);
			if (paper.getHasGenerate() == 0) {
				int option = JOptionPane.showConfirmDialog(null, "是否生成试卷", "系统提示", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					if (generate(paper)) {
						JOptionPane.showMessageDialog(null, "生成成功");
						showListData();
					} else {
						JOptionPane.showMessageDialog(null, "生成失败");
					}

				}
			} else {
				JOptionPane.showMessageDialog(null, "该试卷已生成");
			}

		}
	}

	private boolean generate(Paper paper) {
		Long questionNum = paper.getQuestionNum();
		List<Question> questions = questionService.listBySubjectId(paper.getSubjectId());
		PaperItem bean = new PaperItem();
		bean.setPaperId(paper.getId());
		bean.setScore(paper.getPerScore());
		Question question = null;
		int num;
		for (int i = 0; i < questionNum; i++) {
			num = (int) (Math.random() * questions.size());
			question = questions.get(num);
			bean.setQuestionId(question.getId());
			bean.setAnswer(question.getAnswer());
			Long result = paperItemService.insert(bean);
			if (!(result > 0)) {
				return false;
			}
			questions.remove(num);
		}
		paper.setHasGenerate(1l);
		paperService.update(paper);
		return true;
	}

	protected void deleteMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = tblObj.getSelectedRow();
		if (index != -1) {
			TableModel model = tblObj.getModel();
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf(obj.toString());

			String title = "系统提示";
			String message = "请确认是否删除选中的数据";
			int option = JOptionPane.YES_NO_OPTION;
			int buttonValue = JOptionPane.showConfirmDialog(null, message, title, option);

			if (buttonValue == JOptionPane.YES_OPTION) {
				PaperService paperService = new PaperServiceImpl();
				Long result = paperService.delete(pk);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					showListData();
				} else {
					JOptionPane.showMessageDialog(null, "该卷已生成，删除失败！");
				}
			}
		}
	}

	protected void updateMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = tblObj.getSelectedRow();
		if (index != -1) {
			TableModel model = tblObj.getModel();
			Object obj = model.getValueAt(index, 0);
			Integer pk = Integer.valueOf(obj.toString());

			if (pk > 0) {
				Paper bean = paperService.load(pk.longValue());
				if (bean.getHasGenerate() == 0) {
					PaperUpdateFrm paperUpdateFrm = new PaperUpdateFrm();
					paperUpdateFrm.pk = pk;
					paperUpdateFrm.loadData();
					paperUpdateFrm.listFrm = this;
					this.setVisible(false);
					paperUpdateFrm.setVisible(true);
				} else {
					PaperUpdateFrm2 paperUpdateFrm = new PaperUpdateFrm2();
					paperUpdateFrm.pk = pk;
					paperUpdateFrm.loadData();
					paperUpdateFrm.listFrm = this;
					this.setVisible(false);
					paperUpdateFrm.setVisible(true);
				}

			}
		}
	}

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
		list = paperService.listByName(paperName);
		ATableModel<Paper> tableModel = null;
		tableModel = new ATableModel<Paper>(list, 9) {

			@Override
			public Object getPropValue(Paper arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == 0) {
					return arg0.getId();
				} else if (arg1 == 1) {
					return arg0.getPaperName();
				} else if (arg1 == 2) {
					return arg0.getTotalScore();
				} else if (arg1 == 3) {
					return arg0.getPerScore();
				} else if (arg1 == 4) {
					return arg0.getQuestionNum();
				} else if (arg1 == 5) {
					return arg0.getExamMinute();
				} else if (arg1 == 6) {
					return arg0.getStartOn();
				} else if (arg1 == 7) {
					return arg0.getEndOn();
				} else if (arg1 == 8) {
					return arg0.getHasGenerate();
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
					return "试卷总分";
				} else if (arg0 == 3) {
					return "每题分数";
				} else if (arg0 == 4) {
					return "题目数";
				} else if (arg0 == 5) {
					return "考试时间";
				} else if (arg0 == 6) {
					return "试卷有效开始日期";
				} else if (arg0 == 7) {
					return "试卷有效结束日期";
				} else if (arg0 == 8) {
					return "是否已生成";
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
		lblSearch.setBounds(10, 460, 80, 30);
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

		// 添加按钮
		btnInsert = new JButton();
		btnInsert.setText("添加");
		btnInsert.setBounds(420, 460, 70, 30);
		container.add(btnInsert);

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

		btnInsert.addActionListener(e -> {
			btnInsert_clich(e);
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

	private void btnInsert_clich(ActionEvent e) {
		// TODO Auto-generated method stub
		PaperInsertFrm paperInsertFrm = new PaperInsertFrm();
		paperInsertFrm.listFrm = this;
		paperInsertFrm.setVisible(true);
		this.setVisible(false);
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
