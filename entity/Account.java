package com.lovoinfo.bankManager.entity;

import java.io.Serializable;

/**
 * 帐户类
 */
public class Account implements Serializable {
	
	private static final double minMoney = 1; //帐户最少余额
	private static int accountNumber; 
	
	private String name;
	private String password;
	private String id;
	private String address;
	private String phone;
	private double money;
	
	public Account() {
		accountNumber++;
		
		id = accountNumber + "";
	}

	/**
	 * @param accountNumber 要设置的 accountNumber。
	 */
	public static void setAccountNumber(int accountNumber) {
		Account.accountNumber = accountNumber;
	}

	/**
	 * @return 返回 address。
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address 要设置的 address。
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return 返回 id。
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id。
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return 返回 password。
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 要设置的 password。
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return 返回 phone。
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone 要设置的 phone。
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return 返回 minMoney。
	 */
	public static double getMinMoney() {
		return minMoney;
	}
	
}
