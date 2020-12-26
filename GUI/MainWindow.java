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
	private final static int WIDTH = 800;	//窗体的宽
	private final static int HEIGHT = 600;	//窗体的高
	
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
	 * 初始化菜单
	 */
	public void initMenu() {
		menuBar 			= new JMenuBar();
		systemMenu			= new JMenu("系统选项");
		accountManagerMenu 	= new JMenu("帐户管理");
		queryAccountMenu 	= new JMenu("用户查询");
		queryCountMenu 		= new JMenu("查询统计");
		helpMenu 			= new JMenu("帮助");
		exitMI				= new JMenuItem("退出");
		saveMI 				= new JMenuItem("存款");		
		getMI 				= new JMenuItem("取款");
		newMI 				= new JMenuItem("开户");
		delMI 				= new JMenuItem("销户");
		editMI			 	= new JMenuItem("修改信息");
		queryAccountMI 		= new JMenuItem("用户查询");
		queryCountMI 		= new JMenuItem("查询统计");
		aboutMI 			= new JMenuItem("关于");
				
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
	 * 设置窗体居中
	 */
	public void center() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation((int)(dm.getWidth()-WIDTH)/2,(int)(dm.getHeight()-HEIGHT)/2);	
	}
	
	/**
	 * 加载帐户数据信息
	 */
	public void loadAccountData() {
		data = AccountData.getInstance();
			
		try {
			data.open();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,"找不到文件'data\\account.db'!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"文件存取错误");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this,"数据被破坏!");
		}
		
	}
	
	/**
	 * 加载存取清单数据信息
	 */
	public void loadBillData() {
		billData = BillData.getInstance();
			
		try {
			billData.open();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,"找不到文件'data\\bill.db'!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"文件存取错误");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this,"数据被破坏!");
		}
		
		if(billData.getBillList().size() > 0) {
			Bill.setBillNumber(Integer.parseInt(billData.getBillList().getLast().getBillId()));
		}
	}
	
	/**
	 * 初始化控件
	 */
	public void initComponent() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10,10));
	
		contentPane.add(getTopPanel(),BorderLayout.CENTER); 	//获得上面部分的面板
		contentPane.add(getBottomPanel(),BorderLayout.SOUTH);	//获取下面部分的面板
	}
	
	/**
	 * @return 上面部分的面板
	 */
	private JPanel getTopPanel() {
		if(topPanel != null) {
			return 	topPanel;
		}
				
		final String[] strColName = { "姓名", "帐号", "卡上余额", "家庭住址", "电话"};
		
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
	 * @return 下面部分的面板
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
		
		bottomLPanel.setBorder(BorderFactory.createTitledBorder("选择查找方式"));
		bottomLPanel.setLayout(new GridLayout(2,2));

		rab1 = new JRadioButton("姓名");
		rab2 = new JRadioButton("帐号");
		rab3 = new JRadioButton("家庭住址");
		rab4 = new JRadioButton("电话");
		
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
		
		JLabel label = new JLabel("查找关键字");
		txtInput 	 = new JTextField();
		btnFind 	 = new JButton("查找");
		
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
		
		btnSave			= new JButton("存款");
		btnGet 			= new JButton("取款");
		btnNew			= new JButton("开户");
		btnDel			= new JButton("销户");
		btnEdit			= new JButton("修改信息");
		btnShowAll		= new JButton("显示全部");
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
			//如果用户单击的是按钮，则在按钮中查找
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
			//如果用户单击的是菜单项，则在菜单项中查找
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
	 * 开户
	 */
	private void doAddRecord() {

		JFrame frame = (JFrame)this.getRootPane().getParent();

		//调用开户窗口
		new AddRecordWindow(frame, "开户", true).setVisible(true);
	
	}
	
	/**
	 * 销户
	 */
	private void doDelRecord() {
		int row 			= table.getSelectedRow();
		Iterator iterator 	= data.getAccountList().iterator();
		account				= new Account();
		
		if(row == -1) {
			//如果没有选中记录，提示先选中记录才能销户
			JOptionPane.showMessageDialog(this,"请先选择要删除的帐户!");
		} else {
			//根据选择的行获得标识该帐户的唯一ID
			String delId = (String)dm.getValueAt(table.getSelectedRow(), 1);
		
			//根据ID在帐户链表中找到该帐户并返回该帐户的引用
			while(iterator.hasNext()){
				account = (Account)iterator.next();
				
				if(account.getId().equals(delId)) {
					int i = 0;
					
					//找到要取款的帐户后，要用户输入密码确认进行销户操作
					while(true) { 
						String strPassword = JOptionPane.showInputDialog(this,"请输入密码："); 						
						
						if(strPassword == null) { 
							return;
						} else 	if(account.getPassword().equals(strPassword)) {
							break;
						} else if((++i) < 3) {
							JOptionPane.showMessageDialog(this,"密码错误!请重新输入!");
							continue;
						}
						JOptionPane.showMessageDialog(this,"密码输错三次,帐号被锁定!");
						return;
					}
					
					bill = new Bill();
					
					//生成新的存取清单
					bill.setAccountId(account.getId());
					bill.setMoney(account.getMoney());
					bill.setBalance(0);
					bill.setName(account.getName());
					bill.setType("销户");
					billData.addBill(bill);
					
					//将新的存取清单添加到 存取清单链表中
					data.getAccountList().remove(account);
					
					//显示新的存取清单
					JOptionPane.showMessageDialog(this,"销户成功!");
					JOptionPane.showMessageDialog(this,"存取信息表\n用户名："+ bill.getName()+ "\n帐号："+ bill.getAccountId()
																+ "\n类型："+ bill.getType()+ "\n金额："+ bill.getMoney()
																+ "\n帐户余额："+ bill.getBalance()+ "\n流水号："+ bill.getBillId()
																+ "\n日期："+ bill.getDate()
													);
					
					//保存对帐户链表数据的更改，保存新生成的存取清单
					try {
						billData.save();
						data.save();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(this,"找不到文件!");
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
	
	/**
	 * 存款
	 */
	private void doSaveMoney() {
		int row 			= table.getSelectedRow();
		Iterator iterator 	= data.getAccountList().iterator();
		account				= new Account();
		
		if(row == -1) {
			//如果没有选中记录，提示先选中记录才能存款
			JOptionPane.showMessageDialog(this,"请先选择要存款的帐户!");
		} else {
			//根据选择的行获得标识该帐户的唯一ID
			String delId = (String)dm.getValueAt(table.getSelectedRow(), 1);
		
			//根据ID在帐户链表中找到该帐户并返回该帐户的引用
			while(iterator.hasNext()){
				account = (Account)iterator.next();
				
				if(account.getId().equals(delId)) {
					break;
				}
			}
			
			//找到要存款的帐户后，要用户输入密码确认进行存款操作
			int number = 0;
			while(true) { 
				String strPassword = JOptionPane.showInputDialog(this,"请输入密码："); 						
				
				if(strPassword == null) { 
					return;
				} else 	if(account.getPassword().equals(strPassword)) {
					break;
				} else if((++number) < 3) {
					JOptionPane.showMessageDialog(this,"密码错误!请重新输入!");
					continue;
				}
				JOptionPane.showMessageDialog(this,"密码输错三次,帐号被锁定!");
				return;
			}

			//存款数目只能是数字
			String strNumber = null;
			while (true) {
				strNumber = JOptionPane.showInputDialog(this, "请输入存款金额：");

				if(strNumber == null) {
					return;
				} else if (strNumber.matches("[1-9][0-9]*")) {
					break;
				}

				JOptionPane.showMessageDialog(this, "输入错误!请输入数字!");
			}

			//存款
			account.setMoney(account.getMoney() + Integer.parseInt(strNumber));
			
			bill = new Bill();
			
			//生成新的存取清单
			bill.setAccountId(account.getId());
			bill.setMoney(Integer.parseInt(strNumber));
			bill.setBalance(account.getMoney());
			bill.setName(account.getName());
			bill.setType("存款");
			
			//将新的存取清单添加到 存取清单链表中
			billData.addBill(bill);

			//显示新的存取清单
			JOptionPane.showMessageDialog(this, "存款成功!");
			JOptionPane.showMessageDialog(this,"存取信息表\n用户名："+ bill.getName()+ "\n帐号："+ bill.getAccountId()
														+ "\n类型："+ bill.getType()+ "\n金额："+ bill.getMoney()
														+ "\n帐户余额："+ bill.getBalance()+ "\n流水号："+ bill.getBillId()
														+ "\n日期："+ bill.getDate()
											);
			
			//保存对帐户数据的更改，保存新生成的存取清单
			try {
				billData.save();
				data.save();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "找不到文件!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 取款
	 */
	private void doGetMoney() {
		int row 			= table.getSelectedRow();
		Iterator iterator 	= data.getAccountList().iterator();
		account				= new Account();
		
		if(row == -1) {
			//如果没有选中记录，提示先选中记录才能取款
			JOptionPane.showMessageDialog(this,"请先选择要取款的帐户!");
		} else {
			//根据选择的行获得标识该帐户的唯一ID
			String delId = (String)dm.getValueAt(table.getSelectedRow(), 1);
		
			//根据ID在帐户链表中找到该帐户并返回该帐户的引用
			while(iterator.hasNext()){
				account = (Account)iterator.next();
				
				if(account.getId().equals(delId)) {
					break;
				}
			}
			
			//找到要取款的帐户后，要用户输入密码确认进行取款操作
			int number = 0;
			while(true) { 
				String strPassword = JOptionPane.showInputDialog(this,"请输入密码："); 						
				
				if(strPassword == null) { 
					return;
				} else 	if(account.getPassword().equals(strPassword)) {
					break;
				} else if((++number) < 3) {
					JOptionPane.showMessageDialog(this,"密码错误!请重新输入!");
					continue;
				}
				JOptionPane.showMessageDialog(this,"密码输错三次,帐号被锁定!");
				return;
			}

			//取款数目只能输入数字
			String strNumber = null;
			while (true) {
				strNumber = JOptionPane.showInputDialog(this, "请输入取款金额：");

				if(strNumber == null) {
					return;
				} else if (strNumber.matches("[1-9][0-9]*")) {
					break;
				}

				JOptionPane.showMessageDialog(this, "输入错误!请输入数字!");
			}

			//如果帐户余额不足，则不能进行取款操作，返回
			if(account.getMoney() - Integer.parseInt(strNumber) < account.getMinMoney()) {
				JOptionPane.showMessageDialog(this, "帐户余额不足!");
				return;
			}
			
			//取款
			account.setMoney(account.getMoney() - Integer.parseInt(strNumber));
			
			bill = new Bill();
			
			//生成新的存取清单
			bill.setAccountId(account.getId());
			bill.setMoney(Integer.parseInt(strNumber));
			bill.setBalance(account.getMoney());
			bill.setName(account.getName());
			bill.setType("取款");
			
			//将新的存取清单添加到 存取清单链表中
			billData.addBill(bill);

			//显示新存取清单
			JOptionPane.showMessageDialog(this, "取款成功!");
			JOptionPane.showMessageDialog(this,"存取信息表\n用户名："+ bill.getName()+ "\n帐号："+ bill.getAccountId()
														+ "\n类型："+ bill.getType()+ "\n金额："+ bill.getMoney()
														+ "\n帐户余额："+ bill.getBalance()+ "\n流水号："+ bill.getBillId()
														+ "\n日期："+ bill.getDate()
											);
			//保存对帐户数据的更改，保存新生成的存取清单
			try {
				data.save();
				billData.save();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "找不到文件!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查找
	 */
	private void doFind() {

		//必须输入查询关键字才能进行查询
		if(getInput().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,"请输入查询关键字!");
			return;
		}
		
		Account[] account = new Account[data.getCount()];
		
		data.getAccountList().toArray(account);
		
		//清除表格中以前的数据
		while(table.getRowCount() != 0) {
			dm.removeRow(0);
		}
		
		//根据用户选择的单选按钮  进行查询，在表格中显示符合条件的记录
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
	 * 修改信息
	 */
	private void doEditRecord() {
		Account account 	= new Account();
		Iterator iterator 	= data.getAccountList().iterator();
		int row = table.getSelectedRow();

		if (row == -1) {
			//如果没有选中记录，提示先选中记录才能修改
			JOptionPane.showMessageDialog(this, "请先选择要修改的帐户!");
		} else {
			//根据选择的行获得标识该帐户的唯一ID
			String delId = (String) dm.getValueAt(table.getSelectedRow(), 1);

			//根据ID在帐户链表中找到该帐户并返回该帐户的引用
			while (iterator.hasNext()) {
				account = (Account) iterator.next();

				if (account.getId().equals(delId)) {
					break;
				}
			}
			
			//找到要修改的帐户后，要用户输入密码确认进行修改操作
			int i = 0;
			
			while(true) { 
				String strPassword = JOptionPane.showInputDialog(this,"请输入密码："); 						
				
				if(strPassword == null) { 
					return;
				} else 	if(account.getPassword().equals(strPassword)) {
					break;
				} else if((++i) < 3) {
					JOptionPane.showMessageDialog(this,"密码错误!请重新输入!");
					continue;
				}
				JOptionPane.showMessageDialog(this,"密码输错三次,帐号被锁定!");
				return;
			}
			
			//调用修改记录窗体，传入要修改帐户的引用
			JFrame frame = (JFrame) this.getRootPane().getParent();
			new EditRecordWindow(frame, "修改信息", true, account).setVisible(true);
		}
	}
	
	/**
	 * 显示全部帐户信息
	 */
	private void doShowAll() {
		Account[] account = new Account[data.getCount()];
		
		data.getAccountList().toArray(account);
		
		//清除表格中以前的数据
		while(table.getRowCount() != 0) {
			dm.removeRow(0);
		}
		
		//显示出所有帐户数据
		for(int i=0; i<data.getCount(); i++) {
			String[] strRow = {account[i].getName(), account[i].getId(), account[i].getMoney()+"",
								account[i].getAddress(), account[i].getPhone()};
		
			dm.addRow(strRow);
		}
	}
	
	/**
	 * 统计帐户数量
	 */
	private void doCount() {
		JOptionPane.showMessageDialog(this,"共有"+AccountData.getInstance().getCount()+"个帐户!");
	}
	
	/**
	 * 显示程序相关信息
	 */
	private void doHelp() {
		//显示程序相关信息
	}
	
	/**
	 * 退出
	 */
	private void doExit() {
		System.exit(0);
	}
			
	public String getInput() {
		return txtInput.getText();
	}
}
