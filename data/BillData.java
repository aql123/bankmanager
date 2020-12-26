package com.lovoinfo.bankManager.data;

import java.io.*;
import java.util.LinkedList;
import com.lovoinfo.bankManager.entity.Bill;

public class BillData {
	private LinkedList<Bill> listBill = new LinkedList<Bill>(); //存储存取清单数据的链表，此链表只能存放Bill类
	private File file;
	private static BillData _this;
	
	/**
	 * 隐藏BillData类的构造器
	 * @param filename 存放存取清单数据的文件名
	 */
	private BillData(String filename){
		file = new File(filename);
	}
	
	/**
	 * @return BillData的实例
	 */
	public static BillData getInstance() {
		if(_this == null) {
			_this = new BillData("data\\bill.db");
		} 
		
		return _this;
	}
	
	/**
	 * 添加存取清单，存储到链表中
	 * @param bill 新的存取清单
	 */
	public void addBill(Bill bill) {
		listBill.add(bill);
	}
	
	/**
	 * 读取存取清单数据，保存到链表
	 * @throws FileNotFoundException 找不到文件
	 * @throws IOException 读写错误
	 * @throws ClassNotFoundException 文件被破坏
	 */
	public void open() throws FileNotFoundException,IOException, ClassNotFoundException {
		FileInputStream fis 	= new FileInputStream(file);
		ObjectInputStream ois	= new ObjectInputStream(fis);
		
		listBill = (LinkedList<Bill>)ois.readObject();
		
		ois.close();
	}
	
	/**
	 * 保存存取清单数据到文件
	 * @throws FileNotFoundException 找不到文件
	 * @throws IOException 读写错误
	 */
	public void save() throws FileNotFoundException,IOException
	{
		FileOutputStream fos 	= new FileOutputStream(file);
		ObjectOutputStream oos	= new ObjectOutputStream(fos);
		
		oos.writeObject(listBill);
		
		oos.close();
	}
	
	/**
	 * @return 存放存取清单的链表
	 */
	public LinkedList<Bill> getBillList()
	{
		return listBill;
	}

}
