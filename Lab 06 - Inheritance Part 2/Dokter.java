public class Dokter extends Warga {
	private int jumlahPasienDitemui;
	private String penyakitKeahlian;
	private boolean dokterRamah;
	
	// Constructor
	Dokter(String nama, String penyakitKeahlian, boolean dokterRamah) {
		super(nama);
		this.penyakitKeahlian = penyakitKeahlian;
		this.dokterRamah = dokterRamah;
	}

	// Method jika berinteraksi untuk dokter
	@Override
	public void berinteraksi(Warga X){
		if (X instanceof Pasien)
			this.jumlahPasienDitemui++;
		super.addLogInteraksi(X);
			
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public int getJumlahPasienDitemui(){
		return this.jumlahPasienDitemui;
	}

	public String getPenyakitKeahlian(){
		return this.penyakitKeahlian;
	}

	public boolean getDokterRamah(){
		return this.dokterRamah;
	}

}
