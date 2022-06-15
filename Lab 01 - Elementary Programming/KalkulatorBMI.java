import java.util.Scanner;

// Class untuk menghitung kriteria beran badan seseorang sesuai BMI
public class KalkulatorBMI{
   public static void main(String[] args) {

      // Memberi output sesuai ketentuan
      System.out.print("Selamat datang di program kalkulator BMI!\n"+
      "--------------------------------------------------------\n"+
      "Masukkan jumlah mahasiswa yang akan dihitung datanya: "); 
       
      // Menerima input jumlah mahasiswa dari user
      Scanner input = new Scanner(System.in);
      int n = input.nextInt();
      int low = 0; int med = 0; int high = 0; int extreme = 0;
      
      // Melakukan looping sesuai dengan jumlah mahasiswa
      for (int i=1; i<n+1; i++){
         System.out.printf("--------------------DATA MAHASISWA %d--------------------\n",i);
         System.out.print("Standar pengukuran apakah yang digunakan? ");

         // Menerima input standar yang digunakan
         String standar = input.next();
         
         // Membuat variabel tinggi , massa , dan satuan standar
         double massa = 1.0;
         double tinggi = 1e-2;
         String satuanMassa = "kilogram";
         String satuanTimggi = "sentimeter";
         
         // Jika standarnya IMPERIAL, maka menyesuaikan nilai ukuran massa , tinggi , dan satuan
         // sesuai dengan pengukuran yang digunakan
         if (standar.equals("IMPERIAL")){
            massa *= 703;
            tinggi *= 1e2;
            satuanMassa = "pon";
            satuanTimggi = "inci";
         }

         // Menerima input massa dan tinggi
         System.out.printf("Masukkan massa tubuh mahasiswa (%s): ", satuanMassa);
         massa *= input.nextDouble();
         System.out.printf("Masukkan tinggi tubuh mahasiswa (%s): ", satuanTimggi);
         tinggi *= input.nextDouble();
         
         // Menghitung bmi
         double bmi = massa / Math.pow(tinggi, 2);
         
         // Mencari jenis berat badan orang sesuai bmi
         if(bmi < 18.5)
            low++;
         else if (18.5 <= bmi && bmi < 25)
            med++;
         else if (25.0 <= bmi && bmi < 30)
            high++;
         else
            extreme++;      
      }

      // Memberi output ringkasan data
      System.out.printf("---------------------RINGKASAN DATA---------------------\n"+
         "Berikut merupakan ringkasan hasil pengukuran BMI dari %d mahasiswa.\n"+
         "Jumlah mahasiswa dengan berat badan di bawah normal: %d\n"+
         "Jumlah mahasiswa dengan berat badan normal: %d\n"+
         "Jumlah mahasiswa dengan berat badan di atas normal: %d\n"+
         "Jumlah mahasiswa obesitas: %d\n"+
         "--------------------------------------------------------\n"+
         "Terima kasih telah menggunakan program kalkulator BMI!",
         n, low, med, high, extreme);   

      input.close();
   }
}