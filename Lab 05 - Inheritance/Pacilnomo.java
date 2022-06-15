import java.util.Scanner;

public class Pacilnomo {
	private static Aset[] portofolio;
	private static double earnings;

	private static void printSeparator() {
		System.out.println("=".repeat(64));
	}
	
	// Memberi output deskripsi daftar aset
	private static void daftarAset() {
		printSeparator();
		System.out.printf("Kamu memiliki %d total aset:\n", portofolio.length);
		for(Aset a : portofolio) {
			System.out.println("- " + a);
		}
		printSeparator();
	}

	// Memberi output informasi portofolio
	private static void infoPortofolio() {
		int jumlahSaham = 0, jumlahObligasi = 0;
		double netWorth = 0;

		// Mencari jumlah saham dan obligasi
		for (int i=0; i<portofolio.length; i++){
			if (portofolio[i] instanceof Saham)
				jumlahSaham++;
			else
				jumlahObligasi++;
			// Menambah networth dengan jumlah valuasi
			netWorth += portofolio[i].getHarga() * portofolio[i].getJumlah();
		}
		// Menambah jumlah kumulatif dari dividen saham dan bunga obligasi
		netWorth += earnings;

		printSeparator();
		System.out.printf("""
		Info Portofolio
		Jumlah Jenis Saham: %d
		Jumlah Jenis Obligasi: %d
		Total Nilai Portofolio: %.2f
		""", jumlahSaham, jumlahObligasi, netWorth);
		printSeparator();
	}

	// Memanggil method next year pada setiap aset
	private static void nextYear() {
		for (int i=0; i<portofolio.length; i++){
			portofolio[i].nextYear();
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Silakan masukkan banyak aset yang tersedia: ");
		int banyakAset = Integer.parseInt(in.nextLine());
		
		portofolio = new Aset[banyakAset];
		
		// Memasukkan aset ke dalam portofolio
		for(int i = 0; i < banyakAset; i++) {
			System.out.printf("Aset %d: ", i + 1);
			String inp[] = in.nextLine().split("\\s+");
			String namaAset = inp[0], jenisAset = inp[1]; 
			int jumlah = Integer.valueOf(inp[2]); 
			double harga = Double.valueOf(inp[3]);
			
			// Untuk aset saham
			if(jenisAset.equals("SAHAM")) {
				double pertumbuhan = Double.valueOf(inp[4]);
				double dividen = Double.valueOf(inp[5]);
				portofolio[i] = new Saham(namaAset, jumlah, harga, pertumbuhan, dividen);
			// Untuk aset obligasi
			} else if(jenisAset.equals("OBLIGASI")) {
				double bunga = Double.valueOf(inp[4]);
				int maturitas = Integer.valueOf(inp[5]);
				portofolio[i] = new Obligasi(namaAset, jumlah, harga, bunga, maturitas);
			} 
		}

		System.out.print("Selamat datang di...");
		System.out.print(""" 


							 /$$$$$$$                     /$$ /$$                                            
							| $$__  $$                   |__/| $$                                            
							| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$ /$$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$ 
							| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$__  $$ /$$__  $$| $$_  $$_  $$ /$$__  $$
							| $$____/  /$$$$$$$| $$      | $$| $$| $$  \\ $$| $$  \\ $$| $$ \\ $$ \\ $$| $$  \\ $$
							| $$      /$$__  $$| $$      | $$| $$| $$  | $$| $$  | $$| $$ | $$ | $$| $$  | $$
							| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$  | $$|  $$$$$$/| $$ | $$ | $$|  $$$$$$/
							|__/      \\_______/ \\_______/|__/|__/|__/  |__/ \\______/ |__/ |__/ |__/ \\______/ 
																											
                                                                                 
                                                                                 """);
		
																				 
		// Melakukan loop untuk menerima perintah user																		 
		while(true) {
			System.out.printf("""
				Silakan pilih salah satu opsi berikut:
				[1] Daftar aset
				[2] Info portofolio
				[3] Lanjut ke tahun berikutnya
				[*] Keluar\n""", earnings);
			printSeparator();
			System.out.print("Input: ");
			String pilihan = in.nextLine();
			if(pilihan.equals("1")) {
				daftarAset();
			} else if(pilihan.equals("2")) {
				infoPortofolio();
			} else if(pilihan.equals("3")) {
				nextYear();
				System.out.println("Setahun telah berlalu...");
				printSeparator();
			} else {
				System.out.println("Terima kasih telah menggunakan layanan Pacilnomo ~ !");
				break;
			}
		}
		
		in.close();
	}
	
	// Menambah jumlah earnings
	public static void addToEarnings(double jumlah) {
		earnings += jumlah;
	}
}