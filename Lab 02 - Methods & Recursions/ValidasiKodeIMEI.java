import java.util.Scanner;
import java.util.Arrays;

// Class untuk memvalidasi kode IMEI
public class ValidasiKodeIMEI{

  /** Return true jika kode memenuhi ketentuan */
  public static boolean isValid(int[] kode) {
    return cekPrefix(kode)
        && (getPanjangKode(kode) == 11)
        && ((jumlahDoublePosisiGanjil(kode) + jumlahPosisiGenap(kode)) % 10 == 0);
  }

  /** Menjumlahkan double digit posisi ganjil*/
  public static int jumlahDoublePosisiGanjil(int[] kode) {
    int odd = kode[0]*2;

    // Bila digit*2 lebih dari 9 , dijumlahkan tiap digitnya
    if (odd > 9){
      String strOdd = "" + odd;
      odd = (strOdd.charAt(0)-'0')+ (strOdd.charAt(1)-'0');
    }  
    // Base Case
    if (kode.length == 1)
      return odd;
    
    // Rekursi
    int[] temp = Arrays.copyOfRange(kode, 2, kode.length);
    return odd + jumlahDoublePosisiGanjil(temp);
  }

  /** Menjumlahkan digit posisi genap */
  public static int jumlahPosisiGenap(int[] kode) {
    // Base Case
    if (kode.length == 3)
      return kode[1];

    // Rekursi
    int[] temp = Arrays.copyOfRange(kode, 2, kode.length);
    return kode[1] + jumlahPosisiGenap(temp);
  }

  /** Return true jika kode merupakan prefix yang valid */
  public static boolean cekPrefix(int[] kode) {
    // Mengecek apakah prefix 18 / 2
    if ((kode[0]==1 && kode[1]==8) || kode[0]==2)
        return true;
    return false;
  }

  /** Me-return panjang kode */
  public static int getPanjangKode(int[] kode) {
    return kode.length;
  }

  /** Mengecek apakah kode sesuai dengan ketentuan IMEI */
  public static void main(String[] args) {

    // Menerima input jumlah iterasi
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    while (n-- > 0) {

      // Menerima input kode yang akan divalidasi
      long kode = sc.nextLong();
      String strKode = Long.toString(kode);

      // Mengubah kode menjadi array of int
      int[] arrKode = new int[strKode.length()];
      for (int i = 0; i < strKode.length(); i++){
        arrKode[i] = Integer.parseInt(""+strKode.charAt(i)) ;
      }

      // Memberi output apakah kode valid
      System.out.println(isValid(arrKode) ? "YES" : "NO");
    }
    sc.close();
  }
}