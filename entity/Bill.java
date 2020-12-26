package com.lovoinfo.bankManager.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * ��ȡ�嵥��
 */
public class Bill implements Serializable {
	private static int billNumber;
	private String billId;
	private String type;
	private String name;
	private String accountId;
	private double money; 
	private double balance;
	private Date date;
	
	public Bill() {
		billNumber++;
		
		billId = billNumber+"";
		date = new Date();
	}

	/**
	 * @return ���� accountId��
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId Ҫ���õ� accountId��
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return ���� balance��
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance Ҫ���õ� balance��
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return ���� date��
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date Ҫ���õ� date��
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return ���� money��
	 */
	public double getMoney() {
		return money;
	}

	/**
	 * @param money Ҫ���õ� money��
	 */
	public void setMoney(double money) {
		this.money = money;
	}

	/**
	 * @return ���� name��
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Ҫ���õ� name��
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return ���� type��
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type Ҫ���õ� type��
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return ���� billId��
	 */
	public String getBillId() {
		return billId;
	}

	/**
	 * @param billNumber Ҫ���õ� billNumber��
	 */
	public static void setBillNumber(int billNumber) {
		Bill.billNumber = billNumber;
	}
	
}
