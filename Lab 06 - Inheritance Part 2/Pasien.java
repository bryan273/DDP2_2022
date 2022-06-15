public class Pasien extends Warga {
	private int happiness;
	private String penyakit;
	private boolean pasienSembuh;

	// Constructor
	Pasien(String nama, String penyakit) {
		super(nama);
		this.penyakit = penyakit;
	}

	// Method jika pasien berinteraksi
	@Override
	public void berinteraksi(Warga X) {
		// Berinteraksi dengan dokter
		if (X instanceof Dokter){
			// Jika dokter bisa menyembuhkan penyakit
			if (((Dokter)X).getPenyakitKeahlian().equalsIgnoreCase(this.penyakit) && !this.pasienSembuh){
				this.pasienSembuh = true;
				this.happiness+=20;
			} checkLimit();
		
			// Kondisi jika dokter ramah
			if (((Dokter)X).getDokterRamah())
				this.happiness+=10;
			else
				this.happiness-=5;
			checkLimit();
			
			super.addLogInteraksi(X);
		}
		// Jika berinteraksi dengan pasien
		else{
			this.happiness+=5;
			checkLimit();
			super.addLogInteraksi(X);
		}

	}

	@Override
	public String toString() {
		return super.toString();
	}

	// Cek limitation dari happiness
	private void checkLimit(){
		// Limitation happiness
		if (this.happiness < 0)
		this.happiness = 0;

		if (this.happiness > 100)
			this.happiness = 100;
	}

	public int getHappiness() {
		return this.happiness;
	}

	public boolean getStatusSembuh() {
		return this.pasienSembuh;
	}

	public String getPenyakit() {
		return this.penyakit;
	}

}
