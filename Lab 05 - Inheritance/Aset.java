public class Aset {
	public String nama;
	public int jumlah;
	public double harga;
	private int tahun = 0;
	
	// Constructor untuk aset
	Aset(String nama, int jumlah, double harga) {
		this.nama = nama;
		this.jumlah = jumlah;
		this.harga = harga;
	}
	
	// Increment tahun
	public void nextYear(){
		this.tahun++;
	}

	// Get jumlah tahun
	public int getYear(){
		return this.tahun;
	}

	// Get harga aset
	public double getHarga(){
		return this.harga;
	}

	// Get jumlah aset
	public int getJumlah(){
		return this.jumlah;
	}
}
