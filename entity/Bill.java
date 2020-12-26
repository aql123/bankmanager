package com.lovoinfo.bankManager.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 存取清单类
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
	 * @return 返回 accountId。
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId 要设置的 accountId。
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return 返回 balance。
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance 要设置的 balance。
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return 返回 date。
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date 要设置的 date。
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return 返回 money。
	 */
	public double getMoney() {
		return money;
	}

	/**
	 * @param money 要设置的 money。
	 */
	public void setMoney(double money) {
		this.money = money;
	}

	/**
	 * @return 返回 name。
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 type。
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type 要设置的 type。
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return 返回 billId。
	 */
	public String getBillId() {
		return billId;
	}

	/**
	 * @param billNumber 要设置的 billNumber。
	 */
	public static void setBillNumber(int billNumber) {
		Bill.billNumber = billNumber;
	}
	
}
