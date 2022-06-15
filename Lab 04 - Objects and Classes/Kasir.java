import java.io.*;
import java.util.*;

public class Kasir {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    //Gunakan out sebagai pengganti System.out
    //out ini akan menahan output sampai dia di-(close/flush)
    //Contoh jika ingin print("merah"), maka tulis out.print("merah")
    
    static Barang[] barang;
    static Pelanggan[] pelanggan;
    static int N, M;

    // Mencari nama pelanggan
    static Pelanggan cariPelanggan(String nama) {
        for (Pelanggan p: pelanggan) {
            if (nama.equals(p.getNama())) {
                return p;
            }
        }
        return null;
    }
    
    // Mencari nama barang
    static Barang cariBarang(String namaBarang) {
        for (Barang b: barang) {
            if (namaBarang.equals(b.getNama())) {
                return b;
            }
        }
        return null;
    }

    // Method untuk mengecek isi keranjang dan harga
    static void kasir(Pelanggan K){
        // Jika tidak ada barang
        if (K.getPanjangOrderan()==0){
            out.print("Maaf tidak ada barang di keranjang "+K.getNama()+"\n");
        }
        // Jika uang kurang
        else if (K.getUang() - K.getTotalHarga()< 0){
            out.print("Maaf "+K.getNama()+" tidak memiliki cukup uang\n");
        }
        // Melihat harga barang belanjanan
        else{
            K.setUang(K.getUang()-K.getTotalHarga());
            out.print("Pembelian "+K.getNama()+" berhasil:\n");
            for (int i=0; i<K.getPanjangOrderan(); i++){
                Order isi = K.getKeranjang()[i];
                out.print("* "+isi.getBarang().getNama()+" "+
                isi.getBanyakBarang()+" = "+(isi.getBarang().getHarga()*isi.getBanyakBarang())+"\n");
            }    
            out.print("* Total Belanjaan = "+K.getTotalHarga()+"\n");
            out.print("* Sisa Uang = "+K.getUang()+"\n");
            K.setKeranjangNull(barang.length);
            K.setTotalHargaNull();
            K.setKapasitasNull();
        }
    }
    
    // Method main
    public static void main(String[] args) {
        N = in.nextInt();
        barang = new Barang[N];
        // Menge-set Barang barang yang tersedia
        for (int i = 0; i < N; i++) {
            String namaBarang = in.next();
            int hargaBarang = in.nextInt();
            int beratBarang = in.nextInt();
            int stock = in.nextInt();
            
            barang[i] = new Barang(namaBarang, hargaBarang, beratBarang, stock);
        }
        
        M = in.nextInt();
        pelanggan = new Pelanggan[M];
        // Menge-set pelanggan yang ingin memesan
        for (int j = 0; j < M; j++) {
            String namaPelanggan = in.next();
            int uang = in.nextInt();
            
            pelanggan[j] = new Pelanggan(namaPelanggan, uang, barang.length);
        }
        
        int P = in.nextInt();
        // Menerima perintah
        for (int k = 0; k < P; k++) {
            String command = in.next();

            // Perintah untuk menambah barang
            if (command.equals("ADD")) {
                String namaPelanggan = in.next();
                String namaBarang = in.next();
                int banyakBarangDiambil = in.nextInt();
                
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.print(plg.addBarang(cariBarang(namaBarang), banyakBarangDiambil));
            }
            
            // Perintah untuk mengecek total harga
            if (command.equals("TOTAL_HARGA")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.printf("Total harga belanjaan %s adalah %d%n", plg.getNama(), plg.getTotalHarga());
            }
            
            // Mengecek harga belanjaan
            if (command.equals("KASIR")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                kasir(plg);
            }
            
            // Mengecek uang pelanggan
            if (command.equals("CEK_UANG")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.print(plg.cekUang());
            }
        }
        
        // don't forget to close/flush the output
        out.close(); 
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
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