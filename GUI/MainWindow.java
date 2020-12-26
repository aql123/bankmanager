package com.lovoinfo.bankManager.GUI;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.lovoinfo.bankManager.data.*;
import com.lovoinfo.bankManager.entity.*;


public class MainWindow extends JFrame implements ActionListener{
	private final static int WIDTH = 800;	//����Ŀ�
	private final static int HEIGHT = 600;	//����ĸ�
	
	private AccountData data;
	private BillData billData;
	private Bill bill;
	private Account account;
	private JTable table;
	private DefaultTableModel dm;
	
	private JMenuBar menuBar;
	
	private JMenu systemMenu;
	private JMenu accountManagerMenu;
	private JMenu queryAccountMenu;
	private JMenu queryCountMenu;
	private JMenu helpMenu;
	
	private JMenuItem exitMI;
	private JMenuItem saveMI;
	private JMenuItem getMI;
	private JMenuItem newMI;
	private JMenuItem delMI;
	private JMenuItem editMI;
	private JMenuItem queryAccountMI;
	private JMenuItem queryCountMI;
	private JMenuItem aboutMI;
	
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel bottomLPanel;
	private JPanel bottomMPanel;
	private JPanel bottomRPanel;
		
	private JRadioButton rab1;
	private JRadioButton rab2;
	private JRadioButton rab3;
	private JRadioButton rab4;
	
	private JTextField txtInput;
	private JButton btnFind;
	
	private JButton btnSave;
	private JButton btnGet;
	private JButton btnNew;
	private JButton btnDel;
	private JButton btnEdit;
	private JButton btnShowAll;
	
	public MainWindow(String title) {
		setTitle(title);
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initMenu();
		initComponent();
		center();
		loadAccountData();
		loadBillData();
	}
	
	/**
	 * ��ʼ���˵�
	 */
	public void initMenu() {
		menuBar 			= new JMenuBar();
		systemMenu			= new JMenu("ϵͳѡ��");
		accountManagerMenu 	= new JMenu("�ʻ�����");
		queryAccountMenu 	= new JMenu("�û���ѯ");
		queryCountMenu 		= new JMenu("��ѯͳ��");
		helpMenu 			= new JMenu("����");
		exitMI				= new JMenuItem("�˳�");
		saveMI 				= new JMenuItem("���");		
		getMI 				= new JMenuItem("ȡ��");
		newMI 				= new JMenuItem("����");
		delMI 				= new JMenuItem("����");
		editMI			 	= new JMenuItem("�޸���Ϣ");
		queryAccountMI 		= new JMenuItem("�û���ѯ");
		queryCountMI 		= new JMenuItem("��ѯͳ��");
		aboutMI 			= new JMenuItem("����");
				
		exitMI.addActionListener(this);
		saveMI.addActionListener(this);
		getMI.addActionListener(this);
		newMI.addActionListener(this);
		delMI.addActionListener(this);
		editMI.addActionListener(this);
		queryAccountMI.addActionListener(this);
		queryCountMI.addActionListener(this);
		aboutMI.addActionListener(this);
		
		systemMenu.add(exitMI);
		accountManagerMenu.add(saveMI);
		accountManagerMenu.add(getMI);		
		accountManagerMenu.addSeparator();		
		accountManagerMenu.add(newMI);
		accountManagerMenu.add(delMI);
		accountManagerMenu.addSeparator();
		accountManagerMenu.add(editMI);
		queryAccountMenu.add(queryAccountMI);		
		queryCountMenu.add(queryCountMI);				
		helpMenu.add(aboutMI);
		menuBar.add(systemMenu);
		menuBar.add(accountManagerMenu);
		menuBar.add(queryAccountMenu);
		menuBar.add(queryCountMenu);		
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
	}
	
	/**
	 * ���ô������
	 */
	public void center() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation((int)(dm.getWidth()-WIDTH)/2,(int)(dm.getHeight()-HEIGHT)/2);	
	}
	
	/**
	 * �����ʻ�������Ϣ
	 */
	public void loadAccountData() {
		data = AccountData.getInstance();
			
		try {
			data.open();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,"�Ҳ����ļ�'data\\account.db'!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"�ļ���ȡ����");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this,"���ݱ��ƻ�!");
		}
		
	}
	
	/**
	 * ���ش�ȡ�嵥������Ϣ
	 */
	public void loadBillData() {
		billData = BillData.getInstance();
			
		try {
			billData.open();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,"�Ҳ����ļ�'data\\bill.db'!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"�ļ���ȡ����");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this,"���ݱ��ƻ�!");
		}
		
		if(billData.getBillList().size() > 0) {
			Bill.setBillNumber(Integer.parseInt(billData.getBillList().getLast().getBillId()));
		}
	}
	
	/**
	 * ��ʼ���ؼ�
	 */
	public void initComponent() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10,10));
	
		contentPane.add(getTopPanel(),BorderLayout.CENTER); 	//������沿�ֵ����
		contentPane.add(getBottomPanel(),BorderLayout.SOUTH);	//��ȡ���沿�ֵ����
	}
	
	/**
	 * @return ���沿�ֵ����
	 */
	private JPanel getTopPanel() {
		if(topPanel != null) {
			return 	topPanel;
		}
				
		final String[] strColName = { "����", "�ʺ�", "�������", "��ͥסַ", "�绰"};
		
		topPanel = new JPanel();
		table 	 = new JTable();
		dm 		 = (DefaultTableModel) table.getModel();
		
		for (int i = 0; i < strColName.length; i++) {
			dm.addColumn(strColName[i]);
		}

		table.setRowHeight(30);
		table.setShowGrid(true);
		topPanel.setLayout(new GridLayout(1,1));

		topPanel.add(new JScrollPane(table));

		return topPanel;
	}
	
	/**
	 * @return ���沿�ֵ����
	 */
	private JPanel getBottomPanel() {
		if(bottomPanel != null) {
			return bottomPanel;
		}
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3));
		
		bottomPanel.add(getBottomLeftPanel());
		bottomPanel.add(getBottomMiddlePanel());
		bottomPanel.add(getBottomRightPanel());
		
		return bottomPanel;
	}
	
	private JPanel getBottomLeftPanel() {
		bottomLPanel = new JPanel();
		
		bottomLPanel.setBorder(BorderFactory.createTitledBorder("ѡ����ҷ�ʽ"));
		bottomLPanel.setLayout(new GridLayout(2,2));

		rab1 = new JRadioButton("����");
		rab2 = new JRadioButton("�ʺ�");
		rab3 = new JRadioButton("��ͥסַ");
		rab4 = new JRadioButton("�绰");
		
		ButtonGroup  btnGroup = new ButtonGroup();
		
		rab1.setSelected(true);
		btnGroup.add(rab1);
		btnGroup.add(rab2);
		btnGroup.add(rab3);
		btnGroup.add(rab4);
		bottomLPanel.add(rab1);
		bottomLPanel.add(rab2);
		bottomLPanel.add(rab3);
		bottomLPanel.add(rab4);
		
		return bottomLPanel;
	}
	
	private JPanel getBottomMiddlePanel() {
		bottomMPanel = new JPanel();
		bottomMPanel.setLayout(null);
		
		JLabel label = new JLabel("���ҹؼ���");
		txtInput 	 = new JTextField();
		btnFind 	 = new JButton("����");
		
		label.setBounds(95,0,100,20);
		txtInput.setBounds(50,20,150,25);
		btnFind.setBounds(100,50,60,25);
		btnFind.setMargin(new Insets(2,10,2,10));
		btnFind.addActionListener(this);
		
		bottomMPanel.add(label);
		bottomMPanel.add(txtInput);
		bottomMPanel.add(btnFind);
		
		return bottomMPanel;
	}
	
	private JPanel getBottomRightPanel() {
		bottomRPanel = new JPanel();
		bottomRPanel.setLayout(null);
		
		btnSave			= new JButton("���");
		btnGet 			= new JButton("ȡ��");
		btnNew			= new JButton("����");
		btnDel			= new JButton("����");
		btnEdit			= new JButton("�޸���Ϣ");
		btnShowAll		= new JButton("��ʾȫ��");
		JPanel panel	= new JPanel();
		
		panel.setBounds(0,5,250,100);
		btnShowAll.setMargin(new Insets(2,10,2,10));
		btnSave.setMargin(new Insets(2,10,2,10));
		btnGet.setMargin(new Insets(2,10,2,10));
		btnNew.setMargin(new Insets(2,10,2,10));
		btnDel.setMargin(new Insets(2,10,2,10));
		btnEdit.setMargin(new Insets(2,10,2,10));
		btnShowAll.addActionListener(this);
		btnSave.addActionListener(this);
		btnGet.addActionListener(this);
		btnNew.addActionListener(this);
		btnDel.addActionListener(this);
		btnEdit.addActionListener(this);
		
		panel.add(btnSave);
		panel.add(btnGet);
		panel.add(btnNew);
		panel.add(btnDel);
		panel.add(btnEdit);
		panel.add(btnShowAll);
		bottomRPanel.add(panel);
		
		return bottomRPanel;
	}
	
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() instanceof JButton ) {
			//����û��������ǰ�ť�����ڰ�ť�в���
			JButton btn = (JButton)e.getSource();
			
			if (btn == btnFind) {
				doFind();
			} else if (btn == btnSave) {
				doSaveMoney();
			} else if(btn == btnGet) {
				doGetMoney();
			} else if(btn == btnNew) {
				doAddRecord();
			} else if(btn == btnDel) {
				doDelRecord();
			} else if(btn == btnEdit) {
				doEditRecord();
			} else if(btn == btnShowAll) {
				doShowAll();
			} 
		} else if (e.getSource() instanceof JMenuItem) {
			//����û��������ǲ˵�����ڲ˵����в���
			JMenuItem mi = (JMenuItem)e.getSource();
			
			if(mi == saveMI) {
				doSaveMoney();
			} else if(mi == getMI) {
				doGetMoney();
			} else if(mi == newMI) {
				doAddRecord();
			} else if(mi == delMI) {
				doDelRecord();
			} else if(mi == editMI) {
				doEditRecord();
			} else if(mi == queryAccountMI) {
				doFind();
			} else if(mi == queryCountMI) {
				doCount();
			} else if(mi == aboutMI) {
				doHelp();
			} else if(mi == exitMI) {
				doExit();
			}
		}
	}
	
	/**
	 * ����
	 */
	private void doAddRecord() {

		JFrame frame = (JFrame)this.getRootPane().getParent();

		//���ÿ�������
		new AddRecordWindow(frame, "����", true).setVisible(true);
	
	}
	
	/**
	 * ����
	 */
	private void doDelRecord() {
		int row 			= table.getSelectedRow();
		Iterator iterator 	= data.getAccountList().iterator();
		account				= new Account();
		
		if(row == -1) {
			//���û��ѡ�м�¼����ʾ��ѡ�м�¼��������
			JOptionPane.showMessageDialog(this,"����ѡ��Ҫɾ�����ʻ�!");
		} else {
			//����ѡ����л�ñ�ʶ���ʻ���ΨһID
			String delId = (String)dm.getValueAt(table.getSelectedRow(), 1);
		
			//����ID���ʻ��������ҵ����ʻ������ظ��ʻ�������
			while(iterator.hasNext()){
				account = (Account)iterator.next();
				
				if(account.getId().equals(delId)) {
					int i = 0;
					
					//�ҵ�Ҫȡ����ʻ���Ҫ�û���������ȷ�Ͻ�����������
					while(true) { 
						String strPassword = JOptionPane.showInputDialog(this,"���������룺"); 						
						
						if(strPassword == null) { 
							return;
						} else 	if(account.getPassword().equals(strPassword)) {
							break;
						} else if((++i) < 3) {
							JOptionPane.showMessageDialog(this,"�������!����������!");
							continue;
						}
						JOptionPane.showMessageDialog(this,"�����������,�ʺű�����!");
						return;
					}
					
					bill = new Bill();
					
					//�����µĴ�ȡ�嵥
					bill.setAccountId(account.getId());
					bill.setMoney(account.getMoney());
					bill.setBalance(0);
					bill.setName(account.getName());
					bill.setType("����");
					billData.addBill(bill);
					
					//���µĴ�ȡ�嵥��ӵ� ��ȡ�嵥������
					data.getAccountList().remove(account);
					
					//��ʾ�µĴ�ȡ�嵥
					JOptionPane.showMessageDialog(this,"�����ɹ�!");
					JOptionPane.showMessageDialog(this,"��ȡ��Ϣ��\n�û�����"+ bill.getName()+ "\n�ʺţ�"+ bill.getAccountId()
																+ "\n���ͣ�"+ bill.getType()+ "\n��"+ bill.getMoney()
																+ "\n�ʻ���"+ bill.getBalance()+ "\n��ˮ�ţ�"+ bill.getBillId()
																+ "\n���ڣ�"+ bill.getDate()
													);
					
					//������ʻ��������ݵĸ��ģ����������ɵĴ�ȡ�嵥
					try {
						billData.save();
						data.save();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(this,"�Ҳ����ļ�!");
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
	
	/**
	 * ���
	 */
	private void doSaveMoney() {
		int row 			= table.getSelectedRow();
		Iterator iterator 	= data.getAccountList().iterator();
		account				= new Account();
		
		if(row == -1) {
			//���û��ѡ�м�¼����ʾ��ѡ�м�¼���ܴ��
			JOptionPane.showMessageDialog(this,"����ѡ��Ҫ�����ʻ�!");
		} else {
			//����ѡ����л�ñ�ʶ���ʻ���ΨһID
			String delId = (String)dm.getValueAt(table.getSelectedRow(), 1);
		
			//����ID���ʻ��������ҵ����ʻ������ظ��ʻ�������
			while(iterator.hasNext()){
				account = (Account)iterator.next();
				
				if(account.getId().equals(delId)) {
					break;
				}
			}
			
			//�ҵ�Ҫ�����ʻ���Ҫ�û���������ȷ�Ͻ��д�����
			int number = 0;
			while(true) { 
				String strPassword = JOptionPane.showInputDialog(this,"���������룺"); 						
				
				if(strPassword == null) { 
					return;
				} else 	if(account.getPassword().equals(strPassword)) {
					break;
				} else if((++number) < 3) {
					JOptionPane.showMessageDialog(this,"�������!����������!");
					continue;
				}
				JOptionPane.showMessageDialog(this,"�����������,�ʺű�����!");
				return;
			}

			//�����Ŀֻ��������
			String strNumber = null;
			while (true) {
				strNumber = JOptionPane.showInputDialog(this, "���������");

				if(strNumber == null) {
					return;
				} else if (strNumber.matches("[1-9][0-9]*")) {
					break;
				}

				JOptionPane.showMessageDialog(this, "�������!����������!");
			}

			//���
			account.setMoney(account.getMoney() + Integer.parseInt(strNumber));
			
			bill = new Bill();
			
			//�����µĴ�ȡ�嵥
			bill.setAccountId(account.getId());
			bill.setMoney(Integer.parseInt(strNumber));
			bill.setBalance(account.getMoney());
			bill.setName(account.getName());
			bill.setType("���");
			
			//���µĴ�ȡ�嵥��ӵ� ��ȡ�嵥������
			billData.addBill(bill);

			//��ʾ�µĴ�ȡ�嵥
			JOptionPane.showMessageDialog(this, "���ɹ�!");
			JOptionPane.showMessageDialog(this,"��ȡ��Ϣ��\n�û�����"+ bill.getName()+ "\n�ʺţ�"+ bill.getAccountId()
														+ "\n���ͣ�"+ bill.getType()+ "\n��"+ bill.getMoney()
														+ "\n�ʻ���"+ bill.getBalance()+ "\n��ˮ�ţ�"+ bill.getBillId()
														+ "\n���ڣ�"+ bill.getDate()
											);
			
			//������ʻ����ݵĸ��ģ����������ɵĴ�ȡ�嵥
			try {
				billData.save();
				data.save();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "�Ҳ����ļ�!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ȡ��
	 */
	private void doGetMoney() {
		int row 			= table.getSelectedRow();
		Iterator iterator 	= data.getAccountList().iterator();
		account				= new Account();
		
		if(row == -1) {
			//���û��ѡ�м�¼����ʾ��ѡ�м�¼����ȡ��
			JOptionPane.showMessageDialog(this,"����ѡ��Ҫȡ����ʻ�!");
		} else {
			//����ѡ����л�ñ�ʶ���ʻ���ΨһID
			String delId = (String)dm.getValueAt(table.getSelectedRow(), 1);
		
			//����ID���ʻ��������ҵ����ʻ������ظ��ʻ�������
			while(iterator.hasNext()){
				account = (Account)iterator.next();
				
				if(account.getId().equals(delId)) {
					break;
				}
			}
			
			//�ҵ�Ҫȡ����ʻ���Ҫ�û���������ȷ�Ͻ���ȡ�����
			int number = 0;
			while(true) { 
				String strPassword = JOptionPane.showInputDialog(this,"���������룺"); 						
				
				if(strPassword == null) { 
					return;
				} else 	if(account.getPassword().equals(strPassword)) {
					break;
				} else if((++number) < 3) {
					JOptionPane.showMessageDialog(this,"�������!����������!");
					continue;
				}
				JOptionPane.showMessageDialog(this,"�����������,�ʺű�����!");
				return;
			}

			//ȡ����Ŀֻ����������
			String strNumber = null;
			while (true) {
				strNumber = JOptionPane.showInputDialog(this, "������ȡ���");

				if(strNumber == null) {
					return;
				} else if (strNumber.matches("[1-9][0-9]*")) {
					break;
				}

				JOptionPane.showMessageDialog(this, "�������!����������!");
			}

			//����ʻ����㣬���ܽ���ȡ�����������
			if(account.getMoney() - Integer.parseInt(strNumber) < account.getMinMoney()) {
				JOptionPane.showMessageDialog(this, "�ʻ�����!");
				return;
			}
			
			//ȡ��
			account.setMoney(account.getMoney() - Integer.parseInt(strNumber));
			
			bill = new Bill();
			
			//�����µĴ�ȡ�嵥
			bill.setAccountId(account.getId());
			bill.setMoney(Integer.parseInt(strNumber));
			bill.setBalance(account.getMoney());
			bill.setName(account.getName());
			bill.setType("ȡ��");
			
			//���µĴ�ȡ�嵥��ӵ� ��ȡ�嵥������
			billData.addBill(bill);

			//��ʾ�´�ȡ�嵥
			JOptionPane.showMessageDialog(this, "ȡ��ɹ�!");
			JOptionPane.showMessageDialog(this,"��ȡ��Ϣ��\n�û�����"+ bill.getName()+ "\n�ʺţ�"+ bill.getAccountId()
														+ "\n���ͣ�"+ bill.getType()+ "\n��"+ bill.getMoney()
														+ "\n�ʻ���"+ bill.getBalance()+ "\n��ˮ�ţ�"+ bill.getBillId()
														+ "\n���ڣ�"+ bill.getDate()
											);
			//������ʻ����ݵĸ��ģ����������ɵĴ�ȡ�嵥
			try {
				data.save();
				billData.save();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "�Ҳ����ļ�!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ����
	 */
	private void doFind() {

		//���������ѯ�ؼ��ֲ��ܽ��в�ѯ
		if(getInput().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,"�������ѯ�ؼ���!");
			return;
		}
		
		Account[] account = new Account[data.getCount()];
		
		data.getAccountList().toArray(account);
		
		//����������ǰ������
		while(table.getRowCount() != 0) {
			dm.removeRow(0);
		}
		
		//�����û�ѡ��ĵ�ѡ��ť  ���в�ѯ���ڱ������ʾ���������ļ�¼
		if(rab1.isSelected()) {		
			for(int i=0; i<data.getCount(); i++) {
				if(account[i].getName().equals(getInput())) {
					String[] strRow = {account[i].getName(), account[i].getId(), account[i].getMoney()+"",
										account[i].getAddress(), account[i].getPhone()};
	
					dm.addRow(strRow);
				}
			}
		} else if(rab2.isSelected()) {	
			for(int i=0; i<data.getCount(); i++) {
				if(account[i].getId().equals(getInput())) {
					String[] strRow = {account[i].getName(), account[i].getId(), account[i].getMoney()+"",
										account[i].getAddress(), account[i].getPhone()};

					dm.addRow(strRow);
				}
			}
		} else if(rab3.isSelected()) {
			for(int i=0; i<data.getCount(); i++) {
				if(account[i].getAddress().indexOf(getInput()) != -1) {
					String[] strRow = {account[i].getName(), account[i].getId(), account[i].getMoney()+"",
										account[i].getAddress(), account[i].getPhone()};
				
					dm.addRow(strRow);
				}
			}
		} else if(rab4.isSelected()) {
			for(int i=0; i<data.getCount(); i++) {
				if(account[i].getPhone().equals(getInput())) {
					String[] strRow = {account[i].getName(), account[i].getId(), account[i].getMoney()+"",
										account[i].getAddress(), account[i].getPhone()};
				
					dm.addRow(strRow);
				}
			}
	
		}
	}
	
	/**
	 * �޸���Ϣ
	 */
	private void doEditRecord() {
		Account account 	= new Account();
		Iterator iterator 	= data.getAccountList().iterator();
		int row = table.getSelectedRow();

		if (row == -1) {
			//���û��ѡ�м�¼����ʾ��ѡ�м�¼�����޸�
			JOptionPane.showMessageDialog(this, "����ѡ��Ҫ�޸ĵ��ʻ�!");
		} else {
			//����ѡ����л�ñ�ʶ���ʻ���ΨһID
			String delId = (String) dm.getValueAt(table.getSelectedRow(), 1);

			//����ID���ʻ��������ҵ����ʻ������ظ��ʻ�������
			while (iterator.hasNext()) {
				account = (Account) iterator.next();

				if (account.getId().equals(delId)) {
					break;
				}
			}
			
			//�ҵ�Ҫ�޸ĵ��ʻ���Ҫ�û���������ȷ�Ͻ����޸Ĳ���
			int i = 0;
			
			while(true) { 
				String strPassword = JOptionPane.showInputDialog(this,"���������룺"); 						
				
				if(strPassword == null) { 
					return;
				} else 	if(account.getPassword().equals(strPassword)) {
					break;
				} else if((++i) < 3) {
					JOptionPane.showMessageDialog(this,"�������!����������!");
					continue;
				}
				JOptionPane.showMessageDialog(this,"�����������,�ʺű�����!");
				return;
			}
			
			//�����޸ļ�¼���壬����Ҫ�޸��ʻ�������
			JFrame frame = (JFrame) this.getRootPane().getParent();
			new EditRecordWindow(frame, "�޸���Ϣ", true, account).setVisible(true);
		}
	}
	
	/**
	 * ��ʾȫ���ʻ���Ϣ
	 */
	private void doShowAll() {
		Account[] account = new Account[data.getCount()];
		
		data.getAccountList().toArray(account);
		
		//����������ǰ������
		while(table.getRowCount() != 0) {
			dm.removeRow(0);
		}
		
		//��ʾ�������ʻ�����
		for(int i=0; i<data.getCount(); i++) {
			String[] strRow = {account[i].getName(), account[i].getId(), account[i].getMoney()+"",
								account[i].getAddress(), account[i].getPhone()};
		
			dm.addRow(strRow);
		}
	}
	
	/**
	 * ͳ���ʻ�����
	 */
	private void doCount() {
		JOptionPane.showMessageDialog(this,"����"+AccountData.getInstance().getCount()+"���ʻ�!");
	}
	
	/**
	 * ��ʾ���������Ϣ
	 */
	private void doHelp() {
		//��ʾ���������Ϣ
	}
	
	/**
	 * �˳�
	 */
	private void doExit() {
		System.exit(0);
	}
			
	public String getInput() {
		return txtInput.getText();
	}
}
