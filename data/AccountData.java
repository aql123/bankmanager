package com.lovoinfo.bankManager.data;

import com.lovoinfo.bankManager.entity.Account;
import java.util.*;
import java.io.*;

public class AccountData {
	private LinkedList<Account> listAccount = new LinkedList<Account>(); //存储帐户数据的链表，此链表只能存放Account类
	private File file;
	private static AccountData _this; 
	
	/**
	 * 隐藏AccountData类的构造器
	 * @param filename 存放帐户数据的文件名
	 */
	private AccountData(String filename){
		file = new File(filename);
	}
	
	/**
	 * 获得AccountData类的实例
	 * @return AccountData类的实例
	 */
	public static AccountData getInstance() {
		if(_this == null) {
			_this = new AccountData("data\\account.db");
		} 
		
		return _this;
	}
	
	/**
	 * 添加帐户，存储到链表中
	 * @param account 新的帐户
	 */
	public void addAccount(Account account) {
		listAccount.add(account);
	}
	
	/**
	 * 读取帐户信息，保存到链表
	 * @throws FileNotFoundException 找不到文件
	 * @throws IOException 读写错误
	 * @throws ClassNotFoundException 文件被破坏
	 */
	public void open() throws FileNotFoundException,IOException, ClassNotFoundException {
		FileInputStream fis 	= new FileInputStream(file);
		ObjectInputStream ois	= new ObjectInputStream(fis);
		
		listAccount = (LinkedList<Account>)ois.readObject();
		
		ois.close();
	}
	
	/**
	 * 保存帐户信息到文件
	 * @throws FileNotFoundException 找不到文件
	 * @throws IOException 读写错误
	 */
	public void save() throws FileNotFoundException,IOException
	{
		FileOutputStream fos 	= new FileOutputStream(file);
		ObjectOutputStream oos	= new ObjectOutputStream(fos);
		
		oos.writeObject(listAccount);
		
		oos.close();
	}
	
	/**
	 * @return  帐户数量
	 */
	public int getCount()
	{
		return listAccount.size();
	}
	
	/**
	 * @return 存放帐户数据的链表
	 */
	public LinkedList<Account> getAccountList()
	{
		return listAccount;
	}

}
