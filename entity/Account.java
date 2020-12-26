package com.lovoinfo.bankManager.entity;

import java.io.Serializable;

/**
 * �ʻ���
 */
public class Account implements Serializable {
	
	private static final double minMoney = 1; //�ʻ��������
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
	 * @param accountNumber Ҫ���õ� accountNumber��
	 */
	public static void setAccountNumber(int accountNumber) {
		Account.accountNumber = accountNumber;
	}

	/**
	 * @return ���� address��
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address Ҫ���õ� address��
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return ���� id��
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id Ҫ���õ� id��
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return ���� password��
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password Ҫ���õ� password��
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return ���� phone��
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone Ҫ���õ� phone��
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return ���� minMoney��
	 */
	public static double getMinMoney() {
		return minMoney;
	}
	
}
