package com.lovoinfo.bankManager;

import com.lovoinfo.bankManager.GUI.*;

public class Loader {

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow("������Ŀ����ϵͳ");
		LoginWindow login = new LoginWindow("������Ŀ����ϵͳ--��½",mainWindow);
		
		login.setVisible(true);
	}

}
	