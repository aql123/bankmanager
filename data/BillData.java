package com.lovoinfo.bankManager.data;

import java.io.*;
import java.util.LinkedList;
import com.lovoinfo.bankManager.entity.Bill;

public class BillData {
	private LinkedList<Bill> listBill = new LinkedList<Bill>(); //�洢��ȡ�嵥���ݵ�����������ֻ�ܴ��Bill��
	private File file;
	private static BillData _this;
	
	/**
	 * ����BillData��Ĺ�����
	 * @param filename ��Ŵ�ȡ�嵥���ݵ��ļ���
	 */
	private BillData(String filename){
		file = new File(filename);
	}
	
	/**
	 * @return BillData��ʵ��
	 */
	public static BillData getInstance() {
		if(_this == null) {
			_this = new BillData("data\\bill.db");
		} 
		
		return _this;
	}
	
	/**
	 * ��Ӵ�ȡ�嵥���洢��������
	 * @param bill �µĴ�ȡ�嵥
	 */
	public void addBill(Bill bill) {
		listBill.add(bill);
	}
	
	/**
	 * ��ȡ��ȡ�嵥���ݣ����浽����
	 * @throws FileNotFoundException �Ҳ����ļ�
	 * @throws IOException ��д����
	 * @throws ClassNotFoundException �ļ����ƻ�
	 */
	public void open() throws FileNotFoundException,IOException, ClassNotFoundException {
		FileInputStream fis 	= new FileInputStream(file);
		ObjectInputStream ois	= new ObjectInputStream(fis);
		
		listBill = (LinkedList<Bill>)ois.readObject();
		
		ois.close();
	}
	
	/**
	 * �����ȡ�嵥���ݵ��ļ�
	 * @throws FileNotFoundException �Ҳ����ļ�
	 * @throws IOException ��д����
	 */
	public void save() throws FileNotFoundException,IOException
	{
		FileOutputStream fos 	= new FileOutputStream(file);
		ObjectOutputStream oos	= new ObjectOutputStream(fos);
		
		oos.writeObject(listBill);
		
		oos.close();
	}
	
	/**
	 * @return ��Ŵ�ȡ�嵥������
	 */
	public LinkedList<Bill> getBillList()
	{
		return listBill;
	}

}
