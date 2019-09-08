package edu.prj.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TmplFrm extends JFrame {
	private JPanel container;

	public TmplFrm() {
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

		this.setResizable(false);

		container = new JPanel();
		container.setSize(width, height);
		this.add(container);
		container.setLayout(null);
	}

	private void bindEvent() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void customLoad() {
		// TODO Auto-generated method stub

	}

}
