package com.config;

import java.sql.*;

public class ConnectionDB {
	public Connection connect;
	public Statement statement;
	public ResultSet rs;
	public ResultSetMetaData rsMetaData;
	public PreparedStatement ps;
	
	public ConnectionDB() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothify", "root", "");
			statement = connect.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getClothesData() {
		try {
			ps = connect.prepareStatement("SELECT * FROM clothes");
			rs = ps.executeQuery();
			rsMetaData = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void InsertMenu(String nama, String kode, int stok, int harga) {
		try {
			ps = connect.prepareStatement("INSERT INTO clothes (menu_nama, menu_kode, menu_stok, menu_harga) VALUES (?,?,?,?)");
			ps.setString(1, nama);
			ps.setString(2, kode);
			ps.setInt(3, stok);
			ps.setInt(4, harga);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateData(String nama, String kode, int stok, int harga) {
		try {
			ps = connect.prepareStatement("UPDATE menu SET menu_nama = ?, menu_stok = ?, menu_kode = ?, menu_harga = ?, WHERE menu_kode = ?");
			ps.setString(1, nama);
			ps.setString(2, kode);
			ps.setInt(3, stok);
			ps.setInt(4, harga);
			
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteMenu(String kode) {
		try {
			ps = connect.prepareStatement("DELETE FROM menu WHERE menu_kode = ?");
			ps.setString(1, kode);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
