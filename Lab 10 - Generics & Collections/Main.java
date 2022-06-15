import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    // Make variable for food and drink order list
    private static DaftarPesanan<Pesanan> daftarMakanan = new DaftarPesanan<>();
    private static DaftarPesanan<Pesanan> daftarMinuman = new DaftarPesanan<>();

    // Main program
    public static void main(String[] args) {

        // Get the amount of food and drinks that will be added
        int jumlahMakanan = in.nextInt();
        int jumlahMinuman = in.nextInt();

        // Loop for receiving food order
        for (int i = 0; i < jumlahMakanan; i++) {
            // Receive user input
            String namaMakanan = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            int tingkatKepedasan = in.nextInt();

            // Add food to food order list
            Makanan makanan = new Makanan(namaMakanan, harga, prioritas, tingkatKepedasan);
            daftarMakanan.tambahPesanan(makanan);
        }

        // Loop for receiving drinks order
        for (int i = 0; i < jumlahMinuman; i++) {
            // Receive user input
            String namaMinuman = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            boolean isPakeEs = in.next().equals("YES");

            // Add drinks to drinks order list
            Minuman minuman = new Minuman(namaMinuman, harga, prioritas, isPakeEs);
            daftarMinuman.tambahPesanan(minuman);
        }

        // Set food and drink list as iterator
        daftarMakanan.setIterator();
        daftarMinuman.setIterator();

        while (true) {
            String command = in.next();
            if (command.equals("KELUAR")) {
                break;
            }

            String tipe = in.next();

            // Check whether type is food or drink
            if (tipe.equals("MAKANAN")) {
                // Serve food if still available on food list
                Pesanan serveMakanan = daftarMakanan.nextPesanan();
                if (serveMakanan==null)
                    out.println("Semua pesanan makanan telah disajikan!");
                else
                    out.println(serveMakanan+" telah disajikan.");

            } else {
                // Serve drink if still available on drink list
                Pesanan serveMinuman = daftarMinuman.nextPesanan();
                if (serveMinuman==null)
                    out.println("Semua pesanan minuman telah disajikan!");
                else
                    out.println(serveMinuman+" telah disajikan.");

            }
        }

        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    private static class InputReader {
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
