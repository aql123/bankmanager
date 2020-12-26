package com.lovoinfo.bankManager;

import com.lovoinfo.bankManager.GUI.*;

public class Loader {

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow("银行帐目管理系统");
		LoginWindow login = new LoginWindow("银行帐目管理系统--登陆",mainWindow);
		
		login.setVisible(true);
	}

}
	