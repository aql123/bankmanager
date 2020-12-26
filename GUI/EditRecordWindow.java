package com.lovoinfo.bankManager.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Iterator;
import javax.swing.*;

import com.lovoinfo.bankManager.data.AccountData;
import com.lovoinfo.bankManager.entity.Account;

public class EditRecordWindow extends RecordWindow implements ActionListener {
	private Account account;
	private int pos;
		
	public EditRecordWindow(JFrame frame, String title, boolean b, Account account){
		super(frame,title,b); //调用父类的构造方法
		
		this.account = account; //获得要修改的帐户
		
		txtId.setEditable(false);	//将帐户文本框设置为不可编辑
		txtMoney.setEditable(false); //将余额文本框设置为不可编辑
		btnSave.addActionListener(this); //为保存按钮添加监听器
		btnCancel.addActionListener(this); //为取消按钮添加监听器
		
		showEditRecord(); //显示该帐户的详细信息
		
		pos = getPos();  //获得该帐户在链表中的位置
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn == btnSave) {
			doSave(); 			//如果点击的是保存按钮，调用保存方法
		} else if (btn == btnCancel) {
			doCancel();			//如果点击的是取消按钮，调用取消方法
		}
	}
	
	/**
	 * 该方法的作用是将要修改帐户的信息显示出来
	 */
	private void showEditRecord() {
		setId(account.getId());
		setAccountName(account.getName());
		setPassword(account.getPassword());
		setPhone(account.getPhone());
		setMoney(account.getMoney());
		setAddress(account.getAddress());
	
	}
	
	/**
	 * @return 该帐户在帐户链表中的位置
	 */
	private int getPos() {
		AccountData data 	= AccountData.getInstance();
		Iterator iterator 	= data.getAccountList().iterator(); 
		int pos				= 0;
		
		pos = 0;
		
		while(iterator.hasNext()){
			if(((Account)iterator.next()).equals(account)) {
				break;
			}
			pos++;
		}
		
		return pos;
	}
	
	/**
	 * 该方法的作用是保存帐户数据
	 */
	protected void doSave() {
		AccountData data 	= AccountData.getInstance();
		Account account		= new Account();
			
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
		
		//设置此帐户的信息
		account.setName(getAccountName());
		account.setId(getId());
		account.setPassword(getPassword());
		account.setPhone(getPhone());
		account.setAddress(getAddress());
		account.setMoney(Double.parseDouble(getMoney()));

		//在链表中更新该帐户的信息
		data.getAccountList().set(pos, account);
		
		//保存对该帐户的更改
		try {
			data.save();
			JOptionPane.showMessageDialog(this, "操作成功!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "操作失败!");
		}
		
		//调用取消方法
		doCancel();
	}
	
	private void doCancel() {
		dispose();
	}

}
