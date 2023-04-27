package com.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import com.config.ConnectionDB;
import com.utility.Menu;

public class TableMenu extends JFrame implements ActionListener, MouseInputListener{
	
	public Vector<Menu> MenuData;
	private ConnectionDB connectDB;
	
	private JPanel topPanel, midPanel, contentPanel, formPanel, tablePanel, botPanel, botTPanel, botBPanel,
		idLblPanel, nameLblPanel, brandLblPanel, sizeLblPanel, priceLblPanel,
		idFieldPanel, nameFieldPanel, brandFieldPanel, sizeFieldPanel, priceFieldPanel;
	private JLabel titleLbl, idLbl, nameLbl, brandLbl, sizeLbl, priceLbl;
	private JTextField idField, nameField, brandField, sizeField, priceField;
	private JTable clothesTable;
	private JScrollPane scrollPane;
	private JButton addBtn, updateBtn, deleteBtn, clearBtn, backBtn;
	
	private Vector<Object> column, row;
	private Vector<Vector<Object>> data;
	
	private int harga, stok;
	private String kode, nama;
	
	public TableMenu(Vector<Menu> MenuData, ConnectionDB connectDB) {
		this.MenuData = MenuData;
		this.connectDB = connectDB;
		
		clothesView();
		clothesFrame();
	}
	
	void clothesView() {
		
		// Top Panel
		topPanel = new JPanel();
		titleLbl = new JLabel("Menu");
		titleLbl.setFont(new Font("", Font.BOLD, 48));
		topPanel.add(titleLbl);
		
		// Mid Panel
		midPanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(1, 2));
		
		// Left Panel
		formPanel = new JPanel(new GridLayout(5, 2));
		
		// Kode Menu
		idLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		idFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		idLbl = new JLabel("Kode :");
		idLblPanel.add(idLbl);
		idField = new JTextField();
		idField.setEditable(false);
		idField.setPreferredSize(new Dimension(150, 25));
		idFieldPanel.add(idField);
		
		// Nama Menu
		nameLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		nameLbl = new JLabel("Nama :");
		nameLblPanel.add(nameLbl);
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(150, 25));
		nameFieldPanel.add(nameField);
		
		// Harga Menu
		brandLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		brandFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
				
		brandLbl = new JLabel("Harga :");
		brandLblPanel.add(brandLbl);
		brandField = new JTextField();
		brandField.setPreferredSize(new Dimension(150, 25));
		brandFieldPanel.add(brandField);
		
		// Stok Menu
		sizeLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		sizeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						
		sizeLbl = new JLabel("Stok :");
		sizeLblPanel.add(sizeLbl);
		sizeField = new JTextField();
		sizeField.setPreferredSize(new Dimension(150, 25));
		sizeFieldPanel.add(sizeField);
		
		
		formPanel.add(idLblPanel);
		formPanel.add(idFieldPanel);
		formPanel.add(nameLblPanel);
		formPanel.add(nameFieldPanel);
		formPanel.add(brandLblPanel);
		formPanel.add(brandFieldPanel);
		formPanel.add(sizeLblPanel);
		formPanel.add(sizeFieldPanel);
		formPanel.add(priceLblPanel);
		formPanel.add(priceFieldPanel);
		
		// Right Panel
		column = new Vector<Object>();
		column.add("Kode");
		column.add("Nama");
		column.add("Harga");
		column.add("Stok");
		
		data = new Vector<Vector<Object>>();
		for (Menu menu : MenuData) {
			row = new Vector<Object>();
			row.add(menu.getKode());
			row.add(menu.getNama());
			row.add(menu.getStok());
			row.add(menu.getHarga());
			data.add(row);
		}
		
		clothesTable = new JTable(data, column);
		clothesTable.addMouseListener(this);
		scrollPane = new JScrollPane(clothesTable);
		tablePanel = new JPanel();
		tablePanel.add(scrollPane);
		
		contentPanel.add(formPanel);
		contentPanel.add(tablePanel);
		
		midPanel.add(contentPanel);
		
		// Bot Panel
		botPanel = new JPanel(new GridLayout(2, 1));
		
		// Bottom Top Panel
		botTPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		addBtn = new JButton("Add");
		addBtn.addActionListener(this);
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(this);
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(this);
		
		botTPanel.add(addBtn);
		botTPanel.add(updateBtn);
		botTPanel.add(deleteBtn);
		botTPanel.add(clearBtn);
		
		// Bottom Bottom Panel
		botBPanel = new JPanel();
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		botBPanel.add(backBtn);
		
		botPanel.add(botTPanel);
		botPanel.add(botBPanel);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
	}
	
	void MenuFrame() {
		setTitle("Pudding");
		setSize(1140, 768);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			String idStr = idField.getText();
			nama = nameField.getText();
			kode = brandField.getText();
			stok = sizeField.getText();
			harga = priceField.getText();
			
			// Add Menu Data to Vector
			Menu addNewMenu = new Menu(0, nama, kode, stok, harga);
			MenuData.add(addNewMenu);
			
			// Add Menu to Database
			connectDB.InsertMenuData(name, kode, stok, harga);
			
			MenuData.clear();
			setVisible(false);
			new Menu(MenuData, connectDB);
		}
			else if (e.getSource() == clearBtn) {
			idField.setText("");
			nameField.setText("");
			brandField.setText("");
			sizeField.setText("");
			priceField.setText("");
		}
		else if (e.getSource() == backBtn) {
			MenuData.clear();
			setVisible(false);
			new Menu(MenuData, connectDB);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == MenuTable) {
			int row = MenuTable.getSelectedRow();
			kodeField.setText(MenuTable.getValueAt(row, 0).toString());
			namaField.setText(MenuTable.getValueAt(row, 1).toString());
			stokField.setText(MenuTable.getValueAt(row, 2).toString());
			hargaField.setText(MenuTable.getValueAt(row, 3).toString());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
