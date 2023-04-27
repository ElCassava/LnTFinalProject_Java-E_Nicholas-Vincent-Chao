package com.utility;

public class Menu {
	
		private String nama;
		private String kode;
		private int stok;
		private int harga;
			
		public Menu(String nama, String kode, int stok, int harga) {
			this.nama = nama;
			this.kode = kode;
			this.stok = stok;
			this.harga = harga;
		}

		public String getNama() {
			return nama;
		}

		public void setName(String nama) {
			this.nama = nama;
		}

		public String getKode() {
			return kode;
		}

		public int getStok() {
			return stok;
		}
		public void setStok(int stok) {
			this.stok = stok;
		}

		public int getHarga() {
			return harga;
		}

		public void setPrice(int harga) {
			this.harga = harga;
		}
	}

