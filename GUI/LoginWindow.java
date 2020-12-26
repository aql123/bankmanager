package com.lovoinfo.bankManager.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.Properties;

public class LoginWindow extends JFrame implements ActionListener{
	private final static int WIDTH = 300; //窗体的宽
	private final static int HEIGHT = 200;//窗体的高
	
	private int count = 0;
	
	private String username;
	private String password;
	
	private JLabel labelUsername;
	private JLabel labelPassword;
	
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	private MainWindow mainWindow;
	
	/**
	 * 构造方法
	 * @param title 窗体的标题
	 * @param mainWindow  主窗体的引用
	 */
	public LoginWindow(String title, MainWindow mainWindow) {
		this.mainWindow = mainWindow; //获得主窗体的引用 
		
		setTitle(title);
		setSize(WIDTH, HEIGHT);
		setResizable(false);	//窗体的大小不可改变
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initComponent();
		center();
		loadUserData();
	}
	
	/**
	 * 设置窗体居中
	 */
	public void center() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation((int)(dm.getWidth()-WIDTH)/2,(int)(dm.getHeight()-HEIGHT)/2);	
	}
	
	/**
	 * 初始化所有控件
	 */
	public void initComponent() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		
		labelUsername	= new JLabel("管理员帐号：");
		labelPassword	= new JLabel("管理员密码：");
		txtUsername		= new JTextField();
		txtPassword		= new JPasswordField();
		btnConfirm		= new JButton("确定");
		btnCancel		= new JButton("退出");
		
		labelUsername.setBounds(40,40,80,20);	
		txtUsername.setBounds(120,40,120,20);		
		labelPassword.setBounds(40,70,80,20);		
		txtPassword.setBounds(120,70,120,20);		
		btnConfirm.setBounds(90,120,50,20);
		btnConfirm.setMargin(new Insets(0,0,0,0));
		btnConfirm.addActionListener(this);		
		btnCancel.setBounds(170,120,50,20);
		btnCancel.setMargin(new Insets(0,0,0,0));
		btnCancel.addActionListener(this);
		
		contentPane.add(labelUsername);
		contentPane.add(txtUsername);
		contentPane.add(labelPassword);
		contentPane.add(txtPassword);
		contentPane.add(btnConfirm);
		contentPane.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn == btnConfirm) {
			doLogin();	//点确定，调用验证用户名和密码
		} else if(btn == btnCancel) {
			doExit();	//点退出，调用退出方法
		}
	}
	
	/**
	 * 加载管理员的数据文件
	 */
	private void loadUserData() {
		try {
			FileInputStream fis = new FileInputStream("data\\user.db");
			Properties pro 		= new Properties();
			
			pro.load(fis);
			fis.close();
			
			username = pro.getProperty("username");
			password = pro.getProperty("password");						
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,"找不到文件'data\\user.db'!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证用户名和密码
	 * @return 是否通过验证
	 */
	private boolean doLogin() {
		boolean pass = false;
		
		count++;
		
		if(username.equals(getUsername()) && password.equals(getPassword())) {
			//通过验证，则释放自己，显示主窗体
			pass = true;
			this.dispose();
			mainWindow.setVisible(true);
		} else if(count == 3) {
			//三次输入错误 ，退出系统
			JOptionPane.showMessageDialog(this,"连续三次输入错误，退出系统!");
			doExit();
		} else {		
			//提示信息
			JOptionPane.showMessageDialog(this,"错误的用户名或密码!");
		}
		
		//清空用户名和密码输入框中的内容
		setUsername("");
		setPassword("");
		
		return pass;
	}
	
	/**
	 * 退出系统  
	 */
	private void doExit() {
		System.exit(0);
	}

	
	private String getPassword() {
		return txtPassword.getText();
	}

	private void setPassword(String password) {
		this.txtPassword.setText(password);
	}

	private void setUsername(String username) {
		this.txtUsername.setText(username);
	}

	private String getUsername() {
		return txtUsername.getText();
	}
}
