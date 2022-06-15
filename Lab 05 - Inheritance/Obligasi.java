public class Obligasi extends Aset {
	private double bunga;
	private int maturitas;
	private boolean jatuhTempo = false;
	
	// Membuat constructor obligasi
	Obligasi(String nama, int jumlah, double harga, double bunga, int maturitas) {
		super(nama, jumlah, harga);
		this.bunga = bunga;
		this.maturitas = maturitas;
	}
	
	// Overide method nextyear untuk menambah jumlah tahun serta jumlah bunga ke earnings
	@Override
	public void nextYear() {
		// Menambah tahun dan mengecek jatuh tempo
		super.nextYear();
		if (super.getYear()>this.maturitas)
			this.jatuhTempo = true;

		// Menambahkan bunga ke earnings apabila belum jatuh tempo
		if (!this.jatuhTempo){
			double jumlahBunga = this.bunga * this.harga * this.jumlah;
			Pacilnomo.addToEarnings(jumlahBunga);
		}
	}

	// Override method toString dengan deskripsi obligasi
	@Override
	public String toString() {
		String returnString = this.nama + "\n" +
							  "Tipe: Obligasi\n" +
							  String.format("Harga: %.2f", this.harga) + "\n" +
							  "Jumlah: " + this.jumlah + "\n" + 
							  "Bunga: " + this.bunga + "\n" + 
							  "Jatuh tempo: " + this.jatuhTempo;
		return returnString;  
	}
}
