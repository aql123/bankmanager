package com.lovoinfo.bankManager.GUI;

import java.awt.*;
import javax.swing.*;

public class RecordWindow extends JDialog {
	private final static int WIDTH = 540;  //窗体的宽
	private final static int HEIGHT = 210; //窗体的高
	
	protected JLabel labId;
	protected JLabel labName;
	protected JLabel labPassword;
	protected JLabel labAddress;
	protected JLabel labPhone;
	protected JLabel labMoney;

	protected JTextField txtId;
	protected JTextField txtName;
	protected JTextField txtAddress;
	protected JTextField txtPhone;
	protected JTextField txtMoney;
	protected JPasswordField txtPassword;
	
	protected JButton btnSave;
	protected JButton btnCancel;
	
	public RecordWindow(JFrame frame, String title, boolean b){
		super(frame,title,b); //调用父类的构造器
		
		setSize(WIDTH,HEIGHT); //设置窗体大小
		initComponent();	   
		center();
	}
	
	/**
	 * 设置窗体居中
	 */
	public void center() {
		Window win = this.getOwner();
		Dimension dm = win.getSize();
		Point pt = win.getLocation();
		
		setLocation(pt.x + (int)(dm.getWidth()-WIDTH)/2,pt.y + (int)( dm.getHeight()-HEIGHT)/2);	
	}

	/**
	 * 初始化所有控件
	 */
	public void initComponent() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		labName = new JLabel("姓名：");
		labName.setBounds(20,30,40,20);
		contentPane.add(labName);
		txtName = new JTextField();
		txtName.setBounds(60,30,80,20);
		contentPane.add(txtName);
		
		labId = new JLabel("帐号：");
		labId.setBounds(160,30,40,20);
		contentPane.add(labId);
		txtId = new JTextField();
		txtId.setBounds(200,30,100,20);
		contentPane.add(txtId);
				
		labPassword = new JLabel("密码：");
		labPassword.setBounds(20,60,40,20);
		contentPane.add(labPassword);
		txtPassword= new JPasswordField();
		txtPassword.setBounds(60,60,80,20);
		contentPane.add(txtPassword);
		
		labMoney = new JLabel("存款：");
		labMoney.setBounds(160,60,40,20);
		contentPane.add(labMoney);
		txtMoney= new JTextField();
		txtMoney.setBounds(200,60,100,20);
		contentPane.add(txtMoney);
		
		labPhone = new JLabel("电话号码：");
		labPhone.setBounds(320,60,65,20);
		contentPane.add(labPhone);
		txtPhone = new JTextField();
		txtPhone.setBounds(385,60,100,20);
		contentPane.add(txtPhone);
				
		labAddress = new JLabel("家庭住址：");
		labAddress.setBounds(20,95,65,20);
		contentPane.add(labAddress);
		txtAddress= new JTextField();
		txtAddress.setBounds(85,95,400,20);
		contentPane.add(txtAddress);
				
		btnSave = new JButton("保存");
		btnSave.setBounds(170,130,80,30);
		contentPane.add(btnSave);
		
		btnCancel = new JButton("放弃");
		btnCancel.setBounds(290,130,80,30);
		contentPane.add(btnCancel);	
	}

	public String getAddress() {
		return txtAddress.getText().trim();
	}

	public void setAddress(String address) {
		this.txtAddress.setText(address);
	}

	public String getId() {
		return txtId.getText().trim();
	}

	public void setId(String id) {
		this.txtId.setText(id);
	}

	public String getMoney() {
		return txtMoney.getText();
	}

	public void setMoney(double money) {
		this.txtMoney.setText(money + "");
	}

	public String getAccountName() {
		return txtName.getText().trim();
	}

	public void setAccountName(String name) {
		this.txtName.setText(name);
	}

	public String getPassword() {
		if(txtPassword.getPassword().length == 0) {
			return "000000";
		}
		
		return txtPassword.getText();
	}

	public void setPassword(String password) {
		this.txtPassword.setText(password);
	}

	public String getPhone() {
		return txtPhone.getText().trim();
	}

	public void setPhone(String phone) {
		this.txtPhone.setText(phone);
	}
}
