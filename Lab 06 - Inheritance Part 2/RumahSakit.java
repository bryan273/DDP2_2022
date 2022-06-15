import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

// Class untuk program berjalan
class RumahSakit {

    private static InputReader in;
    private static PrintWriter out;
    static Map<String, Warga> daftarWarga1 = new HashMap<String, Warga>();
    Warga[] daftarWarga = new Warga[0];

    // Output log untuk pasien dan dokter
    static private void handleLog(Warga X) {
		if (X instanceof Pasien) { // Jika pasien
			out.println(((Pasien)X).getHappiness());
			out.println(((Pasien)X).getStatusSembuh());
		} else { // Jika dokter
			out.println(((Dokter)X).getJumlahPasienDitemui());
		}

        // Output orang yang pernah ditemui
		for (int i = 0; i < X.getLogInteraksi().length; i++){ // ArrayList.size() digunakan untuk mencari ukuran arraylist
			out.println(X.getLogInteraksi()[i]); // ArrayList.get(i) digunakan untuk mengambil isi dari arraylist pada index ke-i
		}
		out.println("------------");;
        
    }

    // Ketentuan ketika saling berinteraksi
    static private void handleInteraksi(Warga X, Warga Y) {
		X.berinteraksi(Y);
		Y.berinteraksi(X);
    }

    // Method untuk menambahkan daftar warga
    private void masukkanKeDaftarWarga(Warga objWarga){
        Warga[] newDaftarWarga = new Warga[this.daftarWarga.length+1];

        // Menambah warga dan panjang array
        for(int i = 0; i < this.daftarWarga.length; i++){
            newDaftarWarga[i] = this.daftarWarga[i];
        }
        this.daftarWarga = newDaftarWarga;

        newDaftarWarga[this.daftarWarga.length -1] = objWarga;
    }

    // Method untuk mendapat objek warga berdasarkan nama
    private Warga getWarga(String nama){
        for(Warga warga: this.daftarWarga){
            if(warga.getNama().equalsIgnoreCase(nama)){
                return warga;
            }
        }
        return null;
    }

    // Method berlangsung main program 
    private void mainProgram(){

        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N;
        
        // Menerima jumlah perintah
        N = in.nextInt();
        for(int tmp=0;tmp<N;tmp++) {
            String event = in.next();

            // Cek instruksi
            if(event.equals("ADD")) {
                String roleWarga = in.next();
                String nama = in.next();

                // Memasukkan objek dokter atau pasien ke array
				if (roleWarga.equals("DOKTER")){
					String penyakitKeahlian = in.next();
					boolean dokterRamah = in.next().equals("Yes") ? true : false;
                    masukkanKeDaftarWarga(new Dokter(nama, penyakitKeahlian, dokterRamah));;
				} else {
					String penyakit = in.next();
                    masukkanKeDaftarWarga(new Pasien(nama, penyakit));
				}
            } else if(event.equals("INTERAKSI")) {
                String X = in.next();
				String Y = in.next();
				handleInteraksi(getWarga(X), getWarga(Y)); 
            } else {
                String X = in.next();
                handleLog(getWarga(X));
            }
        }

        out.flush();
    }

    public static void main(String[] args) throws IOException{
        RumahSakit rs = new RumahSakit();
        rs.mainProgram();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
}