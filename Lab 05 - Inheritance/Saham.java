public class Saham extends Aset {
	private double dividen;
	private double pertumbuhan;
	
	// Membuat constructor saham
	Saham(String nama, int jumlah, double harga, double pertumbuhan, double dividen) {
		super(nama, jumlah, harga);
		this.dividen = dividen;
		this.pertumbuhan = pertumbuhan;
	}

	@Override
	public void nextYear() {
		// Memodifikasi pertumbuhan dan menambahkan tahun
		super.nextYear();
		grow();

		// Mengubah harga sesuai pertumbuhan
		double jumlahPertumbuhan = this.pertumbuhan*this.harga;
		this.harga += jumlahPertumbuhan;

		// Menambahkan jumlah dividen ke earnings
		double jumlahDividen = this.dividen * this.harga * this.jumlah;
		Pacilnomo.addToEarnings(jumlahDividen);
	}

	// Linear congruential generator for subsequent growth
	private void grow() {
		int a = 0x4b;
		int c = 0x4a;
		int m = 2;
		pertumbuhan = ((a * pertumbuhan + c) % m) - 1;
		this.pertumbuhan = pertumbuhan < 0 ? pertumbuhan % -m : pertumbuhan;
	}

	// Override method toString dengan deskripsi saham
	@Override
	public String toString() {
		String returnString = this.nama + "\n" +
							  "Tipe: Saham\n" +
							  String.format("Harga: %.2f", this.harga) + "\n" +
							  "Jumlah: " + this.jumlah + "\n" +
							  "Dividen: " + this.dividen + "\n" +
							  "Pertumbuhan: " + this.pertumbuhan;
		return returnString;  
	}
}
