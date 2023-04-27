package com.project;

import java.util.Vector;
import com.config.ConnectionDB;
import com.utility.Menu;

public class Main {

	
	public Vector<Menu> MenuData = new Vector<Menu>();
	private ConnectionDB connectDB;
	
	public Main() {
		connectDB = new ConnectionDB();
		new Menu(MenuData, connectDB);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
