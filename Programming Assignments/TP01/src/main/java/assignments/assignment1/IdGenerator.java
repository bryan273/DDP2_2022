package assignments.assignment1;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// Class untuk membuat dan memvalidasi Id Mahasiswa
public class IdGenerator {
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /*
     * Method untuk menerima dan melakukan perintah di menu
     * Code pada method main tidak boleh diganti sama sekali!
     */
    public static void main(String[] args) {
        // Membuat isi hashmap
        buildMapCharToValue();
        Scanner input = new Scanner(System.in);
        System.out.println("Selamat Datang di Perpustakaan!");

        boolean shouldTerminateProgram = false;

        // Melakukan loop bila user belum meminta berhenti
        while (!shouldTerminateProgram) {
            // Memberi dan menerima pilihan menu
            printMenu();
            System.out.print("Menu yang anda pilih: ");
            int menu = input.nextInt();
            input.nextLine();

            // Menu untuk membuat ID Anggota
            if (menu == 1) {
                System.out.print("Program Studi: ");
                String programStudi = input.nextLine();
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();
                System.out.print("Tanggal Lahir: ");
                String tanggalLahir = input.nextLine();

                System.out.println(generateId(programStudi, angkatan, tanggalLahir));
                
            // Menu untuk memvalidasi ID Anggota
            } else if (menu == 2) {
                System.out.print("ID Anggota: ");
                String idAnggota = input.nextLine();

                System.out.print("Validitas: ");
                System.out.println(checkValidity(idAnggota));

            // Menu untuk meng-terminate program
            } else {
                shouldTerminateProgram = true;
                System.out.println("Sampai jumpa!");
            }
        }

        input.close();
    }

    /*
     * Method buildMapCodeToNumber adalah method untuk membuat mapping reference numbers Code 93
     */
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    /*
     * Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
     */
    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    /*
     * Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
     */
    private static int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    /*
     * Method printMenu untuk memberi pilihan menu
     */
    private static void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("Menu yang tersedia:");
        System.out.println("1. Generate ID anggota untuk anggota baru");
        System.out.println("2. Check validitas ID anggota");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
    }

    /*
     * Method generateId adalah method untuk membuat ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     */
    public static String generateId(String programStudi, String angkatan, String tanggalLahir){
        // Mengecek apakah format input valid
        if (validateData(programStudi, angkatan, tanggalLahir)){
            
            // Mengubah format date dd/mm/yyyy menjadi ddmmyyyy
            String[] dateArr = tanggalLahir.split("/");
            String date;
            date = String.join("", dateArr);
            date = date.substring(0,4) + date.substring(6, 8);

            // Membuat id Anggota
            String id;
            id =  programStudi + angkatan.substring(2, 4) + date;
            id = id + checkSumC(id);
            id = id + checkSumK(id);
            return "ID Anggota: " + id;
        }
        else
            return "Input tidak valid!";
    }

    /*
     * Method checkUpper untuk mengecek apakah character uppercase pada index tertentu
     */
    public static boolean checkUpper(String character, int[] idx){
        // Iterasi tiap index untuk mengecek uppercase
        for (int i : idx){
            if (!Character.isUpperCase(character.charAt(i)))
                return false;
        }
        return true;
    }

    /*
     * Method checkNumber untuk mengecek apakah character is number pada index tertentu
     */
    public static boolean checkNumber(String character, int start, int end){
        // Mengecek apakah string dapat di parse menjadi integer pada subset tertentu
        try{
            Integer.parseInt(character.substring(start, end));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    /*
     * Method checkUpperNumber untuk mengecek apakah character is number atau upper
     */
    public static boolean checkUpperNumber(String character, int start, int end){
        // Mengecek apakah string adalah angka atau upper atau gabungan keduanya
        for (int i=start; i< end; i++){
            if (!(Character.isDigit(character.charAt(i)) || 
                Character.isUpperCase(character.charAt(i))))
                return false;     
        }
        return true;
    }

    /*
     * Method checkValidity adalah method untuk mengecek validitas ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     */
    public static boolean checkValidity(String idAnggota) {
        // Melakukan validasi panjang kode , uppercase , dan number
        int[] idxUpperPrefix = new int[]{0,1,2};
        if (!(idAnggota.length()==13 
              && checkUpper(idAnggota, idxUpperPrefix) 
              && checkNumber(idAnggota, 3, 11)
              && (checkUpperNumber(idAnggota, 11, 13))))
            return false;

        // Melakukan validasi untuk check C dan K
        Character c = idAnggota.charAt(11);
        Character k = idAnggota.charAt(12);
        Character checkC = checkSumC(idAnggota);
        Character checkK = checkSumK(idAnggota);

        if (c.equals(checkC) && k.equals(checkK))
            return true;
        return false;
    }

    /*
     * Method validateData unutk mengecek format input benar atau tidak
     */
    public static boolean validateData(String programStudi, String angkatan, String tanggalLahir){
        if (checkName(programStudi)&&checkGen(angkatan)&&checkDate(tanggalLahir))
            return true;
        else
            return false;
    }

    /*
     * Method checkName untuk mengecek apakah nama prodi valid
     */
    public static boolean checkName(String rawName){
        ArrayList<String> name = new ArrayList<String>(Arrays.asList("SIK","SSI","MIK","MTI","DIK"));
        if (name.contains(rawName))
          return true;
        return false;
    }

    /*
     * Method checkGen untuk mengecek apakah angkatan valid
     */
    public static boolean checkGen(String rawGen){
        int gen = Integer.parseInt(rawGen);
        if (rawGen.length()==4 && 2000<=gen && gen<=2021)
          return true;
        return false;
    }

    /*
     * Method checkDate untuk mengecek apakah date valid
     */
    public static boolean checkDate(String rawDate){
    
        try {
          // Memvalidasi format date dd/mm/yyyy
          String date = rawDate.substring(0,2)+rawDate.substring(3,5)+rawDate.substring(6,10); 
          String punc = ""+rawDate.charAt(2)+rawDate.charAt(5);
          
          // Jika date tidak dapat diparse , maka lanjut ke exception
          Integer.parseInt(date);
          if (!punc.equals("//"))
            return false;
          return true;
        }
        catch(Exception e) {
          return false;
        }
    } 

    /*
     * Method checkSumC untuk mengecek nilai C
     */
    public static char checkSumC(String idAnggota){
        // Menjumlahkan perkalian 11 sampai 1 dari kiri ke kanan kode kemudian mod 36
        String id11 = idAnggota.substring(0,11);
        int sumC = 0;

        for (int i=1; i<12; i++){
            Character val = id11.charAt(11-i);
            sumC += i*getValueFromChar(val);
        }
        return getCharFromValue(sumC%36); 
    }

    /*
     * Method checkSumK untuk mengecek nilai K
     */
    public static char checkSumK(String idAnggota){
        // Menjumlahkan perkalian 12 sampai 1 dari kiri ke kanan kode kemudian mod 36
        String id12 = idAnggota.substring(0,12);
        int sumK = 0;
        
        for (int i=1; i<13; i++){
            Character val = id12.charAt(12-i);
            sumK += i*getValueFromChar(val);
        }
        return getCharFromValue(sumK%36); 
    }
}