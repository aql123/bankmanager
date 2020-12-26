package com.lovoinfo.bankManager.GUI;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import com.lovoinfo.bankManager.data.*;
import com.lovoinfo.bankManager.entity.*;

/**
 * 开户窗体
 */
public class AddRecordWindow extends RecordWindow implements ActionListener {
	private AccountData data;
	private Account account;
	private BillData billData;
	private Bill bill;
	
	public AddRecordWindow(JFrame frame, String title, boolean b){
		super(frame,title,b); //调用父类的构造器
		
		data 		= AccountData.getInstance();	//获得AccountData类的实例
		billData 	= BillData.getInstance();		//获得BillData类的实例
		
		if(data.getAccountList().size() > 0) {
			Account.setAccountNumber(Integer.parseInt(data.getAccountList().getLast().getId()));
		}
		
		account 	= new Account();
				
		txtId.setEditable(false);  		//将帐号文本框设置为不可编辑
		txtId.setText(account.getId());	//设置帐号文本框的内容
		btnSave.addActionListener(this);//为保存按钮添加监听器
		btnCancel.addActionListener(this);//为取消按钮添加监听器
	}


	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn == btnSave) {
			doSave(); 		//如果点击的是保存按钮，调用保存方法
		} else if(btn == btnCancel) {
			doCancel();		//如果点击的是取消按钮，调用取消方法
		}
	}
	
	/**
	 * 该方法的作用是保存帐户数据
	 */
	private void doSave() {
				
		//如果没有输入姓名，提醒用户输入
		if(getAccountName().length() == 0) {
			JOptionPane.showMessageDialog(this,"请输入你的真实姓名!");
			return;
		} 
		
		//验证密码是否合法
		if(!getPassword().matches("[0-9]{6}")) {
			JOptionPane.showMessageDialog(this,"请输入六位数字的密码!");
			return;
		}
		
		//如果没有输入家庭住址，提醒用户输入
		if(getAddress().length() == 0) {
			JOptionPane.showMessageDialog(this,"请输入你的家庭住址!");
			return;
		}
		
		//验证电话是否有效
		if(getPhone().length() > 0  && !getPhone().matches("[0-9]{7}[0-9]*")) {
			JOptionPane.showMessageDialog(this,"请输入正确的电话号码!");
			return;
		}
		
		//验证是否输入正确的存款
		if(!getMoney().matches("[1-9][0-9]*")) {
			JOptionPane.showMessageDialog(this,"请输入正确的存款!");
			return;
		}
			
		//设置此帐户的信息
		account.setName(getAccountName());
		account.setId(getId());
		account.setPassword(getPassword());
		account.setPhone(getPhone());
		account.setAddress(getAddress());
		account.setMoney(Double.parseDouble(getMoney()));

		//将帐户数据添加到帐户数据链表
		data.addAccount(account); 
		
		//保存新建帐户信息
		try {
			data.save(); 
			JOptionPane.showMessageDialog(this, "操作成功!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "操作失败!");
		}
		
		bill = new Bill();
		
		//生成存取清单
		bill.setAccountId(account.getId());
		bill.setMoney(account.getMoney());
		bill.setBalance(account.getMoney());
		bill.setName(account.getName());
		bill.setType("开户");
		
		//将新的存取清单添加到存取清单链表
		billData.addBill(bill);
		
		//保存新生成的存取清单
		try {
			billData.save();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,"找不到文件!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"错误的IO操作!");
		}
		
		//打印新生成的存取清单
		JOptionPane.showMessageDialog(this,"存取信息表\n用户名："+ bill.getName()+ "\n帐号："+ bill.getAccountId()
													+ "\n类型："+ bill.getType()+ "\n金额："+ bill.getMoney()
													+ "\n帐户余额："+ bill.getBalance()+ "\n流水号："+ bill.getBillId()
													+ "\n日期："+ bill.getDate()
										);
		
		//调用取消方法
		doCancel();
	}
		
	private void doCancel() {
		dispose();
	}
}
