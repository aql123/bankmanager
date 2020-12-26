package com.lovoinfo.bankManager.GUI;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import com.lovoinfo.bankManager.data.*;
import com.lovoinfo.bankManager.entity.*;

/**
 * ��������
 */
public class AddRecordWindow extends RecordWindow implements ActionListener {
	private AccountData data;
	private Account account;
	private BillData billData;
	private Bill bill;
	
	public AddRecordWindow(JFrame frame, String title, boolean b){
		super(frame,title,b); //���ø���Ĺ�����
		
		data 		= AccountData.getInstance();	//���AccountData���ʵ��
		billData 	= BillData.getInstance();		//���BillData���ʵ��
		
		if(data.getAccountList().size() > 0) {
			Account.setAccountNumber(Integer.parseInt(data.getAccountList().getLast().getId()));
		}
		
		account 	= new Account();
				
		txtId.setEditable(false);  		//���ʺ��ı�������Ϊ���ɱ༭
		txtId.setText(account.getId());	//�����ʺ��ı��������
		btnSave.addActionListener(this);//Ϊ���水ť��Ӽ�����
		btnCancel.addActionListener(this);//Ϊȡ����ť��Ӽ�����
	}


	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn == btnSave) {
			doSave(); 		//���������Ǳ��水ť�����ñ��淽��
		} else if(btn == btnCancel) {
			doCancel();		//����������ȡ����ť������ȡ������
		}
	}
	
	/**
	 * �÷����������Ǳ����ʻ�����
	 */
	private void doSave() {
				
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
		
		//��֤�Ƿ�������ȷ�Ĵ��
		if(!getMoney().matches("[1-9][0-9]*")) {
			JOptionPane.showMessageDialog(this,"��������ȷ�Ĵ��!");
			return;
		}
			
		//���ô��ʻ�����Ϣ
		account.setName(getAccountName());
		account.setId(getId());
		account.setPassword(getPassword());
		account.setPhone(getPhone());
		account.setAddress(getAddress());
		account.setMoney(Double.parseDouble(getMoney()));

		//���ʻ�������ӵ��ʻ���������
		data.addAccount(account); 
		
		//�����½��ʻ���Ϣ
		try {
			data.save(); 
			JOptionPane.showMessageDialog(this, "�����ɹ�!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "����ʧ��!");
		}
		
		bill = new Bill();
		
		//���ɴ�ȡ�嵥
		bill.setAccountId(account.getId());
		bill.setMoney(account.getMoney());
		bill.setBalance(account.getMoney());
		bill.setName(account.getName());
		bill.setType("����");
		
		//���µĴ�ȡ�嵥��ӵ���ȡ�嵥����
		billData.addBill(bill);
		
		//���������ɵĴ�ȡ�嵥
		try {
			billData.save();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,"�Ҳ����ļ�!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"�����IO����!");
		}
		
		//��ӡ�����ɵĴ�ȡ�嵥
		JOptionPane.showMessageDialog(this,"��ȡ��Ϣ��\n�û�����"+ bill.getName()+ "\n�ʺţ�"+ bill.getAccountId()
													+ "\n���ͣ�"+ bill.getType()+ "\n��"+ bill.getMoney()
													+ "\n�ʻ���"+ bill.getBalance()+ "\n��ˮ�ţ�"+ bill.getBillId()
													+ "\n���ڣ�"+ bill.getDate()
										);
		
		//����ȡ������
		doCancel();
	}
		
	private void doCancel() {
		dispose();
	}
}
