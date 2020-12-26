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
		super(frame,title,b); //���ø���Ĺ��췽��
		
		this.account = account; //���Ҫ�޸ĵ��ʻ�
		
		txtId.setEditable(false);	//���ʻ��ı�������Ϊ���ɱ༭
		txtMoney.setEditable(false); //������ı�������Ϊ���ɱ༭
		btnSave.addActionListener(this); //Ϊ���水ť��Ӽ�����
		btnCancel.addActionListener(this); //Ϊȡ����ť��Ӽ�����
		
		showEditRecord(); //��ʾ���ʻ�����ϸ��Ϣ
		
		pos = getPos();  //��ø��ʻ��������е�λ��
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn == btnSave) {
			doSave(); 			//���������Ǳ��水ť�����ñ��淽��
		} else if (btn == btnCancel) {
			doCancel();			//����������ȡ����ť������ȡ������
		}
	}
	
	/**
	 * �÷����������ǽ�Ҫ�޸��ʻ�����Ϣ��ʾ����
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
	 * @return ���ʻ����ʻ������е�λ��
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
	 * �÷����������Ǳ����ʻ�����
	 */
	protected void doSave() {
		AccountData data 	= AccountData.getInstance();
		Account account		= new Account();
			
		//���û�����������������û�����
		if(getAccountName().length() == 0) {
			JOptionPane.showMessageDialog(this,"�����������ʵ����!");
			return;
		} 
			
		//��֤�����Ƿ�Ϸ�
		if(!getPassword().matches("[0-9]{6}")) {
			JOptionPane.showMessageDialog(this,"��������λ���ֵ�����!");
			return;
		}
		
		//���û�������ͥסַ�������û�����
		if(getAddress().length() == 0) {
			JOptionPane.showMessageDialog(this,"��������ļ�ͥסַ!");
			return;
		}
		
		//��֤�绰�Ƿ���Ч
		if(getPhone().length() > 0  && !getPhone().matches("[0-9]{7}[0-9]*")) {
			JOptionPane.showMessageDialog(this,"��������ȷ�ĵ绰����!");
			return;
		}
		
		//���ô��ʻ�����Ϣ
		account.setName(getAccountName());
		account.setId(getId());
		account.setPassword(getPassword());
		account.setPhone(getPhone());
		account.setAddress(getAddress());
		account.setMoney(Double.parseDouble(getMoney()));

		//�������и��¸��ʻ�����Ϣ
		data.getAccountList().set(pos, account);
		
		//����Ը��ʻ��ĸ���
		try {
			data.save();
			JOptionPane.showMessageDialog(this, "�����ɹ�!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "����ʧ��!");
		}
		
		//����ȡ������
		doCancel();
	}
	
	private void doCancel() {
		dispose();
	}

}
