import java.io.*;
import java.util.*;

// Class pengecekan kesehatan
public class HealthWorthinessChecker{

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner inputFile = null;
        PrintWriter outputFile = null;
        int jumlahMahasiswa = 0;

        System.out.println("Selamat datang di program Health Worthiness Checker.");
        System.out.println("-------------------------------------------------------");

        System.out.print("Silakan masukkan nama file masukan: ");
        String inputFileName = in.next();
        System.out.print("Silakan masukkan nama file keluaran: ");
        String outputFileName = in.next();

        System.out.println("-------------------------------------------------------");

        in.close();

        // Mengecek apakah file ada
        try {
            inputFile = new Scanner(new File(inputFileName));
            System.out.println("Data sedang diproses, harap menunggu...");

            // Mendapatkan jumlah mahasiswa
            String line = inputFile.nextLine();
            jumlahMahasiswa = Integer.valueOf(line);
            
            // Inisiasi array mahasiswa
            ArrayList<Mahasiswa> listMahasiswa = new ArrayList<Mahasiswa>();

            // Melakukan looping sampai file dibaca habis
            while (inputFile.hasNextLine()) {
                // Mendapat nama dan number
                String name = inputFile.nextLine();
                String number = inputFile.nextLine();
                
                // Menambahkan tingkat kesehatan
                String[] listNumber = number.split(" ");
                int temp = 0;
                for (int i=0; i<listNumber.length; i++)
                    temp += Integer.valueOf(listNumber[i]);             

                // Menambahkan objek mahasiswa ke array
                listMahasiswa.add(new Mahasiswa(name, temp*2));
            }

            // Proses data mahasiswa
            System.out.println("\nLOG:");
            for (int i = 0; i < jumlahMahasiswa; i++) {
                // Mengecek apakah mahasiswa layak mengikuti program
                try {
                    // Throw HealthinessUnworthyException bila siswa tidak layak
                    if ((listMahasiswa.get(i).getStatus()).equals("Layak mengikuti program"))
                        System.out.println(listMahasiswa.get(i).getNama()+": LAYAK");
                    else
                        throw new HealthinessUnworthyException(listMahasiswa.get(i).getNama());
                // Mengeluarkan message error
                } catch (HealthinessUnworthyException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Output ke teks
            outputFile = new PrintWriter(new File(outputFileName));

            outputFile.write("Nama Mahasiswa          | Status\n");
            outputFile.write("-------------------------------------------------------\n");
            
            // Memasukkan output ke out file
            for (int i = 0; i < jumlahMahasiswa; i++) {
                outputFile.write(listMahasiswa.get(i).toString());
            }
            System.out.println("\nData mahasiswa berhasil diproses!");
        
        // Bila file tidak ditemukkan
        } catch (FileNotFoundException e){
            System.out.println("ERROR: File masukan tidak ditemukan.");
        // Menutup program
        } finally {
            if (inputFile != null) {
                inputFile.close();
            }
            if (outputFile != null) {
                outputFile.close();
            }
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Terima kasih telah menggunakan program Health Worthiness Checker.");
    }
}

// Class exception untuk orang yang tidak layak mengikuti program
class HealthinessUnworthyException extends Exception {
    public HealthinessUnworthyException(String nama) {
        super(nama + ": TIDAK LAYAK");
    }
}
  
