package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Peminjaman;
import assignments.assignment3.buku.Buku;
import java.util.HashMap;
import java.util.ArrayList;

// Class untuk mengatur hal yang dapat dilakukan anggota SistakaNG
public abstract class Anggota extends Pengguna implements CanBorrow, Comparable<Anggota>{
    protected int denda = 0;
    protected int poin  = 0;
    protected ArrayList<Peminjaman> daftarPeminjaman = new ArrayList<Peminjaman>();
    private static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    private static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Constructor Anggota
    public Anggota(String id, String nama){
        super(id, nama);
    }

    // Menerapkan override fungsi compareTo untuk membandingkan dua anggota
    public int compareTo(Anggota other){
        // Kasus bila poin lebih kecil dan lebih besar
        if(this.poin < other.poin)
            return -1;
        else if (this.poin > other.poin)
            return 1;
        else{
            // Kasus bila poin sama , dibandingkan dengan nama
            if (this.getNama().compareTo(other.getNama())>0)
                return -1;
            else
                return 1;
        }
    }

    // Melihat detail anggota
    public void detail(){
        System.out.print(this);
        System.out.println("Riwayat Peminjaman Buku:");

        // Melihat riwayat peminjaman
        if (daftarPeminjaman.size() > 0){
            for (int i=0; i<daftarPeminjaman.size(); i++){
                System.out.printf("----------------- %d -----------------\n", i+1);
                System.out.print(daftarPeminjaman.get(i));   
            }
        }
        else 
            System.out.println("Belum pernah meminjam buku");
    }

    // Method untuk membayar denda
    public String bayarDenda(long jumlahBayar){
        // Mengecek apakah member memiliki denda
        if (this.denda == 0)
            return String.format("%s tidak memiliki denda", this.getNama());

        // Jika ada kembalian
        if (jumlahBayar >= this.denda){
            long kembalian = jumlahBayar-this.denda;
            this.denda = 0;
            return String.format("%s berhasil membayar lunas denda\n", this.getNama())+
                   String.format("Jumlah kembalian: Rp%d", kembalian);   
        }
        // Jika denda yang dibayar kurang 
        else{
            this.denda -= jumlahBayar;
            return String.format("%s berhasil membayar denda sebesar Rp%d\n", this.getNama(), jumlahBayar)+ 
                   String.format("Sisa denda saat ini: Rp%d", this.denda);
        }
    }
    
    // Method untuk meminjam buku
    public String pinjam(Buku buku, String tanggalPeminjaman) {
        // Menambahkan nama peminjam pada buku
        Peminjaman peminjaman = new Peminjaman(this, buku, tanggalPeminjaman);
        buku.tambahPeminjam(this);
        buku.tambahPeminjamAktif(this);

        // Menambah daftar peminjaman dan mengurangi stok buku
        daftarPeminjaman.add(peminjaman);
        buku.setStok(buku.getStok()-1);
        return "";
    }

    // Method untuk mengembalikan buku
    public String kembali(Buku buku, String tanggalPengembalian){
        // Mencari index buku
        int idx = 0;
        for (Peminjaman pinjam: this.daftarPeminjaman){
            Buku bukuPinjam = pinjam.getBuku();
            if (bukuPinjam.getJudul().equals(buku.getJudul()) && 
                bukuPinjam.getPenulis().equals(buku.getPenulis()) &&
                pinjam.getStatus())
                break;
            idx++;
        }
        
        // Memodifikasi poin ,denda ,tanggal return ,dan stok
        Peminjaman peminjaman = daftarPeminjaman.get(idx);
        peminjaman.kembalikanBuku(tanggalPengembalian);
        buku.hapusPeminjamAktif(this);

        this.poin += peminjaman.getBuku().getKategori().getPoin();
        this.denda += peminjaman.getDenda();
        buku.setStok(buku.getStok()+1);

        return String.format("Buku %s berhasil dikembalikan oleh %s dengan denda Rp%d\n", 
                            buku.getJudul(), this.getNama(), this.denda);
    }

    // Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    // Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
    private static int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    // Method generateId adalah method untuk membuat ID keanggotaan perpustakaan
    protected static String generateId(String programStudi, String angkatan, String tanggalLahir){
        // Mengecek apakah format input valid
        buildMapCharToValue();
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
            return id;
        }
        else
            return "Tidak dapat menambahkan anggota silahkan periksa kembali input anda!";
    }

    // Membuat mapping char ke value
    private static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    // Method validateData unutk mengecek format input benar atau tidak
    private static boolean validateData(String programStudi, String angkatan, String tanggalLahir){
        return (checkName(programStudi)&&checkGen(angkatan)&&checkDate(tanggalLahir));
    }

    // Method checkName untuk mengecek apakah nama prodi valid    
    private static boolean checkName(String rawName){
        String[] contenName = new String[]{"SIK","SSI","MIK","MTI","DIK"};
        for (String name: contenName){
            if (name.equals(rawName))
                return true;
        }
        return false;   
    }

    // Method checkGen untuk mengecek apakah angkatan valid    
    private static boolean checkGen(String rawGen){
        int gen = Integer.parseInt(rawGen);
        return (rawGen.length()==4 && 2000<=gen && gen<=2021);
    }

    // Method checkDate untuk mengecek apakah date valid
    private static boolean checkDate(String rawDate){

        try {
          // Memvalidasi format date dd/mm/yyyy
          String date = rawDate.substring(0,2)+rawDate.substring(3,5)+rawDate.substring(6,10); 
          String punc = ""+rawDate.charAt(2)+rawDate.charAt(5);
          
          // Jika date tidak dapat diparse , maka lanjut ke exception
          Integer.parseInt(date);
          return punc.equals("//");
        }
        catch(Exception e) {
          return false;
        }
    } 

    // Method checkSumC untuk mengecek nilai C
    private static char checkSumC(String idAnggota){
        // Menjumlahkan perkalian 11 sampai 1 dari kiri ke kanan kode kemudian mod 36
        String id11 = idAnggota.substring(0,11);
        int sumC = 0;

        for (int i=1; i<12; i++){
            Character val = id11.charAt(11-i);
            sumC += i*getValueFromChar(val);
        }
        return getCharFromValue(sumC%36); 
    }

    // Method checkSumK untuk mengecek nilai K    
    private static char checkSumK(String idAnggota){
        // Menjumlahkan perkalian 12 sampai 1 dari kiri ke kanan kode kemudian mod 36
        String id12 = idAnggota.substring(0,12);
        int sumK = 0;
        
        for (int i=1; i<13; i++){
            Character val = id12.charAt(12-i);
            sumK += i*getValueFromChar(val);
        }
        return getCharFromValue(sumK%36); 
    }

    // Get poin
    public int getPoin(){
        return this.poin;
    }

    // Get denda
    public long getDenda(){
        return this.denda;
    }

    // Get daftar peminjaman
    public ArrayList<Peminjaman> getDaftarPeminjaman(){
        return this.daftarPeminjaman;
    }

    // Deskripsi anggota
    public String toString(){
        String returnString = "ID Anggota: " + this.getId() + "\n"+
                              "Nama Anggota: " + this.getNama() + "\n"+
                              "Total Poin: " + this.poin + "\n"+
                              "Denda: Rp" + this.denda + "\n";      
        return returnString;
    }

}
