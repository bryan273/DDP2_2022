package assignments.assignment3;

import assignments.assignment3.buku.*;
import assignments.assignment3.pengguna.*;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

// Class dijalankannya program perpustakaan
public class SistakaNG {
    private static Scanner input = new Scanner(System.in);
    private static HashMap<String, Staf> getStaf = new HashMap<String, Staf>();
    private static HashMap<String, Anggota> getAnggota = new HashMap<String, Anggota>();
    private static ArrayList<Anggota> daftarAnggota = new ArrayList<Anggota>();
    private static ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
    private static ArrayList<Kategori> daftarKategori = new ArrayList<Kategori>();
    private static Pengguna penggunaLoggedIn;

    public static void main(String[] args) {
        System.out.println("Start - Register Staf...");
        registerStaf();
        System.out.println("Done - Register Staf...\n");
        menu();
        input.close();
    }

    // Method untuk mendaftarkan staf-staf ketika program dijalankan
    private static void registerStaf() {
        String[] listNama = new String[]{"Dek Depe", "Dek DePram", "Dek Sofita", "Winter", "Boo"};

        for (int i = 0; i < listNama.length; i++) {
            Staf staf = new Staf(listNama[i]);
            getStaf.put(staf.getId(), staf);
            System.out.println("Berhasil menambahkan staf dengan data:");
            System.out.print(staf);
        }
    }

    // Method untuk mencetak menu utama dari SistakaNG
    public static void menu() {
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            int command = 0;
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menuLogin();
            } else if (command == 2) {
                System.out.println("Terima kasih telah menggunakan SistakaNG. Sampai jumpa di lain kesempatan!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method untuk mencetak menu login
    public static void menuLogin() {
        boolean isLoginSuccess = false;
        while (!isLoginSuccess) {
            System.out.println("Masukkan ID Anda untuk login ke sistem");
            System.out.print("ID: ");
            String id = input.nextLine();

            // Mengecek pengguna mana yang log in
            if (id.startsWith("STAF-"))
                penggunaLoggedIn = getStaf.get(id);
            else
                penggunaLoggedIn = getAnggota.get(id);

            // Mengecek apakah pengguna yang log in valid
            if (penggunaLoggedIn==null)
                System.out.println("Pengguna dengan ID "+id+" tidak ditemukan");
            else{
                System.out.println("Halo, "+penggunaLoggedIn.getNama()+"! Selamat datang di SistakaNG");  
                break;
            }          
        }
        showMenu();
    }

    // Method untuk mencetak menu yang dapat diakses berdasarkan jenis penggunaLoggedIn
    private static void showMenu() {
        if (penggunaLoggedIn instanceof Staf) {
            showMenuStaf();
        } else {
            showMenuAnggota();
        }
    }

    // Method untuk mencetak menu staf dari SistakaNG
    private static void showMenuStaf() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("================ Menu Staf ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. 3 Peringkat Pertama");
            System.out.println("6. Detail Anggota");
            System.out.println("7. Daftar Peminjam Buku");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menu1_staf();
            } else if (command == 2) {
                menu2_staf();
            } else if (command == 3) {
                menu3_staf();
            } else if (command == 4) {
                menu4_staf();
            } else if (command == 5) {
                menu5_staf();
            } else if (command == 6) {
                menu6_staf();
            } else if (command == 7) {
                menu7_staf();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method untuk mencetak menu anggota dari SistakaNG
    private static void showMenuAnggota() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("================ Menu Anggota ================\n");
            System.out.println("1. Peminjaman");
            System.out.println("2. Pengembalian");
            System.out.println("3. Pembayaran Denda");
            System.out.println("4. Detail Anggota");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menu1_anggota();
            } else if (command == 2) {
                menu2_anggota();
            } else if (command == 3) {
                menu3_anggota();
            } else if (command == 4) {
                menu4_anggota();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Menu 1 untuk tambah anggota
    public static void menu1_staf(){
        
        System.out.println("---------- Tambah Anggota ----------");

        System.out.print("Tipe Anggota: ");
        String tipe = input.nextLine();

        // Mengecek tipe anggota
        if (tipe.equals("Mahasiswa")){
            // Menerima input
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
            String programStudi = input.nextLine();
            System.out.print("Angkatan: ");
            String angkatan = input.nextLine();
            System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
            String tanggalLahir = input.nextLine();
            
            // Menggenerate ID
            Mahasiswa mahasiswa = new Mahasiswa(nama, tanggalLahir, programStudi, angkatan);
            String id = mahasiswa.getId();
            getAnggota.put(id, mahasiswa);

            // Kasus id tidak valid
            if (id.length()!=13)
                System.out.println(id);
            else{
                // Menambahkan mahasiswa ke daftar
                System.out.println("Berhasil menambahkan mahasiswa dengan data:");
                System.out.print(mahasiswa);
                daftarAnggota.add(mahasiswa);
            }
        }
        else if (tipe.equals("Dosen")){
            // Menerima input
            System.out.print("Nama: ");
            String nama = input.nextLine();
            Dosen dosen = new Dosen(nama);

            System.out.println("Berhasil menambahkan dosen dengan data:");
            System.out.print(dosen);

            // Menambahkan dosen ke daftar
            getAnggota.put(dosen.getId(), dosen);
            daftarAnggota.add(dosen);
        }
        else{
            System.out.println("Tipe Anggota "+tipe+" tidak valid!");
            return;
        }
    }

    // Menu 2 untuk tambah kategori
    public static void menu2_staf(){
        System.out.println("---------- Tambah Kategori ----------");
        System.out.print("Nama Kategori: ");
        String kategori = input.nextLine();
        System.out.print("Poin: ");
        int poin = input.nextInt();
        input.nextLine();

        // Mengecek apakah kategori pernah ditambahkan
        for (Kategori kat: daftarKategori){
            if (kat.getKategori().equalsIgnoreCase(kategori)){
                System.out.printf("Kategori %s sudah pernah ditambahkan\n", kat.getKategori());
                return;
            }
        }

        // Menambahkan kategori ke daftar
        daftarKategori.add(new Kategori(kategori, poin));
        System.out.printf("Kategori %s dengan poin %d berhasil ditambahkan\n", kategori, poin);
    }

    // Menu3 untuk menambah buku
    public static void menu3_staf(){
        System.out.println("---------- Tambah Buku ----------");
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();
        System.out.print("Penerbit: ");
        String penerbit = input.nextLine();
        System.out.print("Kategori: ");
        String kategori = input.nextLine();
        System.out.print("Stok: ");
        int stok = input.nextInt();
        input.nextLine();

        // Mencari kategori buku kalau tersedia
        Kategori kategoriBuku = cariKategori(kategori);
        if (kategoriBuku==null) return;

        // Mengecek apakah stok masih ada
        if (stok <= 0){
            System.out.println("Stok harus lebih dari 0");
            return;
        }

        // Mengecek apakah buku pernah ditambahkan
        for (Buku buku: daftarBuku){
            if (buku.getJudul().equalsIgnoreCase(judul) && buku.getPenulis().equalsIgnoreCase(penulis)){
                System.out.printf("Buku %s oleh %s sudah pernah ditambahkan\n", 
                                    buku.getJudul(), buku.getPenulis());
                return;
            }
        }

        // Menambahkan buku ke dalam daftar
        daftarBuku.add(new Buku(judul, penulis, penerbit, stok, kategoriBuku)); 
        System.out.printf("Buku %s oleh %s berhasil ditambahkan\n", judul, penulis);
    }

    public static void menu4_staf(){
        System.out.println("---------- Hapus Buku ----------");
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();
        
        // Mencari index buku dari judul dan penulis
        int idxBuku = cariBuku(judul, penulis);
        if (idxBuku < 0) return;

        // Mengecek apakah masih ada yang meminjam buku
        if (daftarBuku.get(idxBuku).getJumlahPeminjamAktif()>0){
            System.out.printf("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam\n",
                               judul, penulis);
            return;
        }

        // Menghapus buku dari daftar
        System.out.printf("Buku %s oleh %s berhasil dihapus\n",
                        daftarBuku.get(idxBuku).getJudul(), daftarBuku.get(idxBuku).getPenulis());
        daftarBuku.remove(idxBuku);
    }

    // Menu 5 untuk melihat peringkat anggota
    public static void menu5_staf(){
        System.out.println("---------- Peringkat Anggota ----------");
        
        // Kasus bila tidak ada anggota
        if (daftarAnggota.size()<=0){
            System.out.println("Belum ada anggota yang terdaftar pada sistem");
            return;
        }
        
        int len = daftarAnggota.size();
        if (daftarAnggota.size() > 3)
            len=3;

        // Mengurutkan anggota dan memberi output peringkat 3 anggota teratas
        ArrayList<Anggota> sortMembers = sortAnggota(daftarAnggota);
        for (int i=0; i<len; i++){
            System.out.printf("----------------- %d -----------------\n", i+1);
            System.out.print(sortMembers.get(i));
        }
    }

    // Menu 6 untuk melihat detail anggota
    public static void menu6_staf(){
        System.out.println("---------- Detail Anggota ----------");
        System.out.print("ID Anggota: ");
        String id = input.nextLine();
        
        // Mengecek validitas keberadaan anggota
        Anggota anggota = getAnggota.get(id);
        if (anggota==null)
            System.out.printf("Anggota dengan ID %s tidak ditemukan\n", id);
        else
            anggota.detail();
    } 

    // Menu 7 untuk melihat daftar peminjam buku
    public static void menu7_staf(){
        System.out.println("---------- Daftar Peminjam Buku ----------");
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();

        // Mencari index buku dari judul dan penulis
        int idxBuku = cariBuku(judul, penulis);
        if (idxBuku < 0) return;
 
        Buku buku = daftarBuku.get(idxBuku);
        System.out.print(buku);
        System.out.println("---------- Daftar Peminjam ----------");

        // Melihat daftar peminjam buku bila valid
        if (buku.getJumlahPeminjam()==0)
            System.out.println("Belum ada anggota yang meminjam buku "+judul);
        else {
            ArrayList<CanBorrow> daftarPeminjam = buku.getDaftarPeminjam();
            for (int i=0; i<daftarPeminjam.size(); i++){
                System.out.printf("----------------- %d -----------------\n", i+1);
                System.out.print((Anggota)(daftarPeminjam.get(i)));
            }
        }
    }

    // Menu 1 untuk meminjam buku
    public static void menu1_anggota(){
        System.out.println("---------- Peminjaman Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();
        System.out.print("Tanggal Peminjaman: ");
        String tanggal = input.nextLine();

        // Mencari index buku dari judul dan penulis
        int idxBuku = cariBuku(judul, penulis);
        if (idxBuku < 0) return;

        // Mengecek stok
        boolean flag = cekStok(judul, penulis);
        if (!flag) return;

        // Mengecek apakah buku yang dipinjam sudah maksimum
        flag = cekMaksPeminjaman((Anggota)penggunaLoggedIn);
        if (!flag) return;

        // Mengecek denda yang ada pada anggota
        flag = cekDenda((Anggota)penggunaLoggedIn);
        if (!flag) return;

        // Mengecek apakah buku sudah pernah dipinjam
        flag = cekBukuSedangDipinjam(judul, penulis, (Anggota)penggunaLoggedIn);
        if (!flag) return;        

        // Meminjam buku kalau semua kasus valid
        ((Anggota)penggunaLoggedIn).pinjam(daftarBuku.get(idxBuku), tanggal);
        System.out.printf("%s berhasil meminjam Buku %s!\n", ((Anggota)penggunaLoggedIn).getNama(), judul);
    }

    // Menu 2 untuk mengembalikan buku
    public static void menu2_anggota(){
        System.out.println("---------- Pengembalian Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine().trim();
        System.out.print("Tanggal Pengembalian: ");
        String tanggal = input.nextLine();

        // Mencari index buku dari judul dan penulis
        int idxBuku = cariBuku(judul, penulis);
        if (idxBuku < 0) return;
        
        // Mengecek apakah buku sudah tidak dipinjam
        boolean isLoan = cekBukuTidakDipinjam(judul, penulis, (Anggota)penggunaLoggedIn);
        if (!isLoan) return;

        // Bila kasus valid mengembalikan buku
        ((Anggota)penggunaLoggedIn).kembali(daftarBuku.get(idxBuku), tanggal);
        System.out.printf("Buku %s berhasil dikembalikan oleh %s dengan denda Rp%d!\n", 
                            daftarBuku.get(idxBuku).getJudul(), ((Anggota)penggunaLoggedIn).getNama(), 
                            ((Anggota)penggunaLoggedIn).getDenda());
    }

    // Menu 3 untuk membayar denda
    public static void menu3_anggota(){
        System.out.println("---------- Pembayaran Denda ----------");
        System.out.print("Jumlah: ");
        long jumlah = input.nextInt();
        input.nextLine();

        // Membayar denda
        System.out.println(((Anggota)penggunaLoggedIn).bayarDenda(jumlah));
    }

    // Menu 4 untuk melihat detail anggota
    public static void menu4_anggota(){
        // Melihat deskripsi anggota
        ((Anggota)penggunaLoggedIn).detail();
    }

    // Mencari apakah kategori ada
    private static Kategori cariKategori(String kategori){
        for (Kategori kat: daftarKategori){
            if (kat.getKategori().equalsIgnoreCase(kategori))
                return kat;
        }
        System.out.printf("Kategori %s tidak ditemukan\n", kategori);
        return null;
    }

    // Mencari buku dari judul dan penulis
    private static int cariBuku(String judul, String penulis){
        int idx = 0;
        for (Buku buku: daftarBuku){
            if (buku.getJudul().equalsIgnoreCase(judul) && buku.getPenulis().equalsIgnoreCase(penulis))
                return idx; 
            idx++;
        }
        System.out.printf("Buku %s oleh %s tidak ditemukan\n", judul, penulis);
        return -1;
    }

    // Mengecek apakah stok masih ada
    private static boolean cekStok(String judul, String penulis){
        for (Buku buku: daftarBuku){
            if (buku.getStok() <= 0 && buku.getJudul().equals(judul) && buku.getPenulis().equals(penulis)){
                System.out.printf("Buku %s oleh %s tidak tersedia\n", judul, penulis);
                return false;
            }
        }
        return true;
    }
    
    // Mengecek apakah buku sudah dipinjam
    private static boolean cekBukuSedangDipinjam(String judul, String penulis, Anggota anggota){
        for (Peminjaman peminjaman: anggota.getDaftarPeminjaman()){
            Buku buku = peminjaman.getBuku();
            if (buku.getJudul().equalsIgnoreCase(judul) && buku.getPenulis().equalsIgnoreCase(penulis) && peminjaman.getStatus()){
                System.out.printf("Buku %s oleh %s sedang dipinjam\n", 
                                    buku.getJudul(), buku.getPenulis());
                return false;
            }
        }
        return true;
    }

    // Mengecek jika buku tidak sedang dipinjam
    private static boolean cekBukuTidakDipinjam(String judul, String penulis, Anggota anggota){
        for (Peminjaman peminjaman: anggota.getDaftarPeminjaman()){
            Buku buku = peminjaman.getBuku();
            if (buku.getJudul().equalsIgnoreCase(judul) && 
                buku.getPenulis().equalsIgnoreCase(penulis) && peminjaman.getStatus())
                return true;
        }
        System.out.printf("Buku %s tidak sedang dipinjam oleh %s\n", judul, anggota.getNama());
        return false;
    }

    // Mengecek apakah buku yang dipinjam sudah maksimum
    private static boolean cekMaksPeminjaman(Anggota anggota){
        // Melihat berapa buku yang sudah dipinjam
        int statusTrue = 0;
        for (Peminjaman peminjaman : anggota.getDaftarPeminjaman()){
            if (peminjaman.getStatus())
                statusTrue++;
        }

        // Mengecek jumlah peminjaman maksimum buku dari mahasiswa dan dosen
        int peminjamanMaks = 0;
        if (anggota instanceof Mahasiswa)
            peminjamanMaks = Mahasiswa.BATAS_JUMLAH_PEMINJAMAN_BUKU;
        else
            peminjamanMaks = Dosen.BATAS_JUMLAH_PEMINJAMAN_BUKU;

        // Kasus bila buku yang dipinjam sudah maksimal
        if (statusTrue == peminjamanMaks){
            System.out.println("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal");
            return false;
        }
        return true;
    }

    // Mengeceek apakah denda lebih dari maksimum denda
    private static boolean cekDenda(Anggota anggota){
        // Mengecek denda maksimum untuk masing masing anggota
        long dendaMaks = 0;
        if (anggota instanceof Mahasiswa)
            dendaMaks = Mahasiswa.BATAS_MAKSIMAL_DENDA;
        else
            dendaMaks = Dosen.BATAS_MAKSIMAL_DENDA;

        // Kasus bila denda sudah melewati denda maksimal
        if (anggota.getDenda() >= dendaMaks){
            System.out.println("Denda lebih dari Rp"+dendaMaks);  
            return false;
        }
        return true;
    }

    // Mengurutkan anggota sesuai peringkat poin
    private static ArrayList<Anggota> sortAnggota(ArrayList<Anggota> daftarAnggota){        
        int len = daftarAnggota.size();
        if (daftarAnggota.size() > 3)
            len=3;
        
        // Menerapkan sorting pada anggota
        for (int i = 0; i < len; i++) {     
            for (int j = i+1; j < daftarAnggota.size(); j++) {     
                if(daftarAnggota.get(i).compareTo(daftarAnggota.get(j)) < 0){    
                    Anggota temp = daftarAnggota.get(i);    
                    daftarAnggota.set(i, daftarAnggota.get(j));    
                    daftarAnggota.set(j, temp);    
                }
            }     
        }    
        return daftarAnggota;
    }   
}
