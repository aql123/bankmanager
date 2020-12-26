package com.lovoinfo.bankManager.data;

import com.lovoinfo.bankManager.entity.Account;
import java.util.*;
import java.io.*;

public class AccountData {
	private LinkedList<Account> listAccount = new LinkedList<Account>(); //�洢�ʻ����ݵ�����������ֻ�ܴ��Account��
	private File file;
	private static AccountData _this; 
	
	/**
	 * ����AccountData��Ĺ�����
	 * @param filename ����ʻ����ݵ��ļ���
	 */
	private AccountData(String filename){
		file = new File(filename);
	}
	
	/**
	 * ���AccountData���ʵ��
	 * @return AccountData���ʵ��
	 */
	public static AccountData getInstance() {
		if(_this == null) {
			_this = new AccountData("data\\account.db");
		} 
		
		return _this;
	}
	
	/**
	 * ����ʻ����洢��������
	 * @param account �µ��ʻ�
	 */
	public void addAccount(Account account) {
		listAccount.add(account);
	}
	
	/**
	 * ��ȡ�ʻ���Ϣ�����浽����
	 * @throws FileNotFoundException �Ҳ����ļ�
	 * @throws IOException ��д����
	 * @throws ClassNotFoundException �ļ����ƻ�
	 */
	public void open() throws FileNotFoundException,IOException, ClassNotFoundException {
		FileInputStream fis 	= new FileInputStream(file);
		ObjectInputStream ois	= new ObjectInputStream(fis);
		
		listAccount = (LinkedList<Account>)ois.readObject();
		
		ois.close();
	}
	
	/**
	 * �����ʻ���Ϣ���ļ�
	 * @throws FileNotFoundException �Ҳ����ļ�
	 * @throws IOException ��д����
	 */
	public void save() throws FileNotFoundException,IOException
	{
		FileOutputStream fos 	= new FileOutputStream(file);
		ObjectOutputStream oos	= new ObjectOutputStream(fos);
		
		oos.writeObject(listAccount);
		
		oos.close();
	}
	
	/**
	 * @return  �ʻ�����
	 */
	public int getCount()
	{
		return listAccount.size();
	}
	
	/**
	 * @return ����ʻ����ݵ�����
	 */
	public LinkedList<Account> getAccountList()
	{
		return listAccount;
	}

}
