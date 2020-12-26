package com.lovoinfo.bankManager.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.Properties;

public class LoginWindow extends JFrame implements ActionListener{
	private final static int WIDTH = 300; //����Ŀ�
	private final static int HEIGHT = 200;//����ĸ�
	
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
	 * ���췽��
	 * @param title ����ı���
	 * @param mainWindow  �����������
	 */
	public LoginWindow(String title, MainWindow mainWindow) {
		this.mainWindow = mainWindow; //�������������� 
		
		setTitle(title);
		setSize(WIDTH, HEIGHT);
		setResizable(false);	//����Ĵ�С���ɸı�
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initComponent();
		center();
		loadUserData();
	}
	
	/**
	 * ���ô������
	 */
	public void center() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation((int)(dm.getWidth()-WIDTH)/2,(int)(dm.getHeight()-HEIGHT)/2);	
	}
	
	/**
	 * ��ʼ�����пؼ�
	 */
	public void initComponent() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		
		labelUsername	= new JLabel("����Ա�ʺţ�");
		labelPassword	= new JLabel("����Ա���룺");
		txtUsername		= new JTextField();
		txtPassword		= new JPasswordField();
		btnConfirm		= new JButton("ȷ��");
		btnCancel		= new JButton("�˳�");
		
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
			doLogin();	//��ȷ����������֤�û���������
		} else if(btn == btnCancel) {
			doExit();	//���˳��������˳�����
		}
	}
	
	/**
	 * ���ع���Ա�������ļ�
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
			JOptionPane.showMessageDialog(this,"�Ҳ����ļ�'data\\user.db'!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��֤�û���������
	 * @return �Ƿ�ͨ����֤
	 */
	private boolean doLogin() {
		boolean pass = false;
		
		count++;
		
		if(username.equals(getUsername()) && password.equals(getPassword())) {
			//ͨ����֤�����ͷ��Լ�����ʾ������
			pass = true;
			this.dispose();
			mainWindow.setVisible(true);
		} else if(count == 3) {
			//����������� ���˳�ϵͳ
			JOptionPane.showMessageDialog(this,"����������������˳�ϵͳ!");
			doExit();
		} else {		
			//��ʾ��Ϣ
			JOptionPane.showMessageDialog(this,"������û���������!");
		}
		
		//����û���������������е�����
		setUsername("");
		setPassword("");
		
		return pass;
	}
	
	/**
	 * �˳�ϵͳ  
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
