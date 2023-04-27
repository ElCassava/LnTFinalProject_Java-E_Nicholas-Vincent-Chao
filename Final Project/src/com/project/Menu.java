package com.project;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import com.config.ConnectionDB;

public class Menu extends JFrame implements ActionListener{
	
	public Vector<Menu> MenuData;
	private ConnectionDB connectDB;
	
	private JPanel topPanel, botPanel;
	private JLabel titleLbl;
	private JButton enterBtn;
	
	void menuView() {
		// Top Panel
		topPanel = new JPanel();
		titleLbl = new JLabel("Pudding");
		titleLbl.setFont(new Font("", Font.BOLD, 64));
		topPanel.add(titleLbl);
		
		// Bot Panel
		botPanel = new JPanel();
		enterBtn = new JButton("Enter");
		enterBtn.addActionListener(this);
		botPanel.add(enterBtn);
		
		add(topPanel, BorderLayout.NORTH);
		add(botPanel, BorderLayout.SOUTH);
	}
	
	void menuFrame() {
		setTitle("Pudding");
		setSize(350, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Menu(Vector<Menu> MenuData, ConnectionDB connectDB) {
		this.MenuData = MenuData;
		this.connectDB = connectDB;
		
		fillDataVector();
		menuView();
		menuFrame();
	}
	
	private void fillDataVector() {
		connectDB.rs = connectDB.getMenuData();
		try {
			while (connectDB.rs.next()) {
				int id = Integer.valueOf(String.valueOf(connectDB.rs.getObject(1)));
				String name = String.valueOf(connectDB.rs.getObject(2));
				String brand = String.valueOf(connectDB.rs.getObject(3));
				String size = String.valueOf(connectDB.rs.getObject(4));
				int price = Integer.valueOf(String.valueOf(connectDB.rs.getObject(5)));
				
				MenuData.add(new Menu(kode, nama, stok, harga));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterBtn) {
			new tableMenu(MenuData, connectDB);
			setVisible(false);
		}
	}
	
}
