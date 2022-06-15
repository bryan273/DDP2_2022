public class Warga {
	private String nama;
	private Warga[] logInteraksi = new Warga[0];

	// Constructor warga
	Warga(String nama) {
		this.nama = nama;
	}

	// Get nama
	public String getNama() {
		return nama;
	}

	// Method sengaja dikosongkan
	public void berinteraksi(Warga X){
	}

	// Method untuk menambah log interaksi
	public void addLogInteraksi(Warga warga){
		// Menambah warga di log dan menambahkan panjang array
		Warga[] newLog = new Warga[this.logInteraksi.length+1];

        for(int i = 0; i < this.logInteraksi.length; i++){
            newLog[i] = this.logInteraksi[i];
        }
        this.logInteraksi = newLog;

        newLog[this.logInteraksi.length -1] = warga;
	}
	
	@Override
	public String toString() {
		return this.nama;
	}

	// Get log interaksi warga
	public Warga[] getLogInteraksi(){
		return this.logInteraksi;
	}
}
