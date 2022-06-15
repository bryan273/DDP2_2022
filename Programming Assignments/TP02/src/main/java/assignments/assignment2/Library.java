package assignments.assignment2;

import java.util.HashMap;
import java.util.Scanner;

// Class library untuk menjalankan menu menu yang ada
public class Library {
    private Scanner input = new Scanner(System.in);
    private Member[] members;
    private Book[] books;
    private Category[] categories;
    HashMap<Character, Integer> charToValue = new HashMap<>(36);
    char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Membuat constructir library
    public Library(){
        this.members = new Member[0];
        this.books = new Book[0];
        this.categories = new Category[0];
    }
    public static void main(String[] args) {
        Library program = new Library();
        program.menu();
    }

    // Menu menu yang ada pada Library
    public void menu() {
        int command = 0;
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Peminjaman");
            System.out.println("5. Pengembalian");
            System.out.println("6. Pembayaran Denda");
            System.out.println("7. Detail Anggota");
            System.out.println("8. 3 Peringkat Pertama");
            System.out.println("99. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menu1();
            } else if (command == 2) {
                menu2();
            } else if (command == 3) {
                menu3();
            } else if (command == 4) {
                menu4();
            } else if (command == 5) {
                menu5();
            } else if (command == 6) {
                menu6();
            } else if (command == 7) {
                menu7();
            } else if (command == 8) {
                menu8();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }

        input.close();
    }

    // Menu 1 untuk tambah anggota
    public void menu1(){
        buildMapCharToValue();
        
        System.out.println("---------- Tambah Anggota ----------");

        // Menerima input
        System.out.print("Nama : ");
        String nama = input.nextLine();
        System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
        String programStudi = input.nextLine();
        System.out.print("Angkatan: ");
        String angkatan = input.nextLine();
        System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
        String tanggalLahir = input.nextLine();
        
        // Menggenerate ID
        String id = generateId(programStudi, angkatan, tanggalLahir);
        // Kasus id tidak valid
        if (id.length()!=13)
            System.out.println(id);
        else{
            System.out.printf("Member %s berhasil ditambahkan dengan data:\n", nama);
            System.out.println("ID Anggota: " + id);
            System.out.println("Nama Anggota: " + nama);
            System.out.println("Total Point: 0");
            System.out.println("Denda: 0");

            // Menambahkan panjang array +1 setiap kali data bertambah 
            int len = members.length;
            Member[] membersNew = new Member[len + 1];
            System.arraycopy(members, 0, membersNew, 0, len);
            membersNew[len] = new Member(id, nama, tanggalLahir, programStudi, angkatan);
            members = membersNew.clone();

        }
    }
    
    // Menu 2 untuk tambah kategori
    public void menu2(){
        System.out.println("---------- Tambah Kategori ----------");
        System.out.print("Nama Kategori: ");
        String kategori = input.nextLine();
        System.out.print("Point: ");
        int point = input.nextInt();
        input.nextLine();

        // Mengecek apakah kategori pernah ditambahkan
        for (Category kat: categories){
            if (kat.getCategory().equalsIgnoreCase(kategori)){
                System.out.printf("Kategori %s sudah pernah ditambahkan\n", kat.getCategory());
                return;
            }
        }

        // Menambahkan panjang array +1 setiap kali data bertambah 
        int len = categories.length;
        Category[] categoriesNew = new Category[len + 1];
        System.arraycopy(categories, 0, categoriesNew, 0, len);
        categoriesNew[len] = new Category(kategori, point);
        categories = categoriesNew.clone();

        System.out.printf("Kategori %s dengan %d point berhasil ditambahkan\n", kategori, point);
    }

    // Menu3 untuk menambah buku
    public void menu3(){
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

        // Mengecek apakah buku pernah ditambahkan
        for (Book buk: books){
            if (buk.getTitle().equalsIgnoreCase(judul) && buk.getAuthor().equalsIgnoreCase(penulis)){
                System.out.printf("Buku %s oleh %s sudah pernah ditambahkan\n", judul, penulis);
                return;
            }
        }

        // Mencari kategori buku kalau tersedia
        Category kategoriBuku = searchCategory(kategori);
        if (kategoriBuku==null) return;

        // Mengecek apakah stok masih ada
        if (stok <= 0){
            System.out.println("Stok harus lebih dari 0");
            return;
        }

        // Menambahkan panjang array +1 setiap kali data bertambah 
        int len = books.length;
        Book[] booksNew = new Book[len + 1];
        System.arraycopy(books, 0, booksNew, 0, len);
        booksNew[len] = new Book(judul, penulis, penerbit, stok, kategoriBuku);
        books = booksNew.clone();
        
        System.out.printf("Buku %s oleh %s berhasil ditambahkan\n", judul, penulis);
    }

    // Menu 4 untuk meminjam buku
    public void menu4(){
        System.out.println("---------- Peminjaman Buku ----------");
        System.out.print("ID Anggota: ");
        String id = input.nextLine();
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();
        System.out.print("Tanggal Peminjaman: ");
        String tanggal = input.nextLine();

        // Mencari index members dari ID
        int idxId = searchId(id);
        if (idxId < 0) return;

        // Mencari index buku dari judul dan penulis
        int idxBuku = searchBook(judul, penulis);
        if (idxBuku < 0) return;

        // Mengecek stok
        boolean flag = checkStock(judul, penulis);
        if (!flag) return;

        // Mengecek apakah buku yang dipinjam >3
        flag = checkLoanMax(idxId);
        if (!flag) return;

        // Mengecek denda yang ada pada member
        flag = checkFine(idxId);
        if (!flag) return;

        // Mengecek apakah buku sudah pernah dipinjam
        flag = checkIsLoanBook(judul, penulis, idxId);
        if (!flag) return;        

        // Meminjam buku kalau semua kasus valid
        members[idxId].pinjam(books[idxBuku], tanggal);
        System.out.printf("%s berhasil meminjam Buku %s!\n", members[idxId].getName(), judul);
    }

    // Menu 5 untuk mengembalikan buku
    public void menu5(){
        System.out.println("---------- Pengembalian Buku ----------");
        System.out.print("ID Anggota: ");
        String id = input.nextLine();
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine().trim();
        System.out.print("Tanggal Pengembalian: ");
        String tanggal = input.nextLine();

        // Mencari index members dari ID
        int idxId = searchId(id);
        if (idxId < 0) return;

        // Mencari index buku dari judul dan penulis
        int idxBuku = searchBook(judul, penulis);
        if (idxBuku < 0) return;

        // Mengecek apakah buku sudah tidak dipinjam
        boolean isLoan = checkNotLoanBook(judul, penulis, idxId);
        if (!isLoan) return;
        
        // Bila kasus valid mengembalikan buku
        members[idxId].kembali(books[idxBuku], tanggal);
        System.out.printf("Buku %s berhasil dikembalikan oleh %s dengan denda Rp %d!\n", 
                            judul, members[idxId].getName(), members[idxId].getFine());
    }
    
    // Menu 6 untuk membayar denda
    public void menu6(){
        System.out.println("---------- Pembayaran Denda ----------");
        System.out.print("ID Anggota: ");
        String id = input.nextLine();
        System.out.print("Jumlah: ");
        long jumlah = input.nextInt();
        input.nextLine();
        
        // Mencari index members dari ID
        int idxId = searchId(id);
        if (idxId < 0) return;

        // Mengecek apakah member memiliki denda
        if (members[idxId].getFine() == 0){
            System.out.printf("%s tidak memiliki denda\n", members[idxId].getName());
            return;
        }

        // Membayar denda
        members[idxId].bayarDenda(jumlah);
    }

    // Menu 7 untuk melihat detail anggota
    public void menu7(){
        System.out.println("---------- Detail Anggota ----------");
        System.out.print("ID Anggota: ");
        String id = input.nextLine();

        // Mencari index members dari ID
        int idxId = searchId(id);
        if (idxId < 0) return;

        // Melihat deskripsi member
        members[idxId].detail();
    }

    // Menu 8 untuk melihat peringkat anggota
    public void menu8(){
        System.out.println("---------- Peringkat Anggota ----------");
        
        // Kasus bila tidak ada member
        if (members.length<=0){
            System.out.println("Belum ada anggota yang terdaftar pada sistem");
            return;
        }
        
        // Mengurutkan member dan memberi output peringkat member
        Member[] sortMembers = sortArray(members);
        for (int i=0; i<3; i++){
            System.out.printf("----------------- %d -----------------\n", i+1);
            System.out.print(sortMembers[i]);
        }
    }

    // Mencari apakah category ada
    private Category searchCategory(String kategori){
        for (Category kat: categories){
            if (kat.getCategory().equalsIgnoreCase(kategori))
                return kat;
        }
        System.out.printf("Kategori %s tidak ditemukan\n", kategori);
        return null;
    }

    // Mengeceek apakah fine lebih dari 5000
    private boolean checkFine(int idxId){
        if (members[idxId].getFine() >= 5000){
            System.out.println("Denda lebih dari Rp 5000");  
            return false;
        }
        return true;
    }

    // Mengecek apakah stok masih ada
    private boolean checkStock(String judul, String penulis){
        for (Book buk: books){
            if (buk.getStock() <= 0 && buk.getTitle().equals(judul) && buk.getAuthor().equals(penulis)){
                System.out.printf("Buku %s oleh %s tidak tersedia\n", judul, penulis);
                return false;
            }
        }
        return true;
    }

    // Mengecek apakah buku yang dipinjam melebihi 3
    private boolean checkLoanMax(int idxId){
        int statusTrue = 0;
        for (BookLoan loan : members[idxId].getBookLoans()){
            if (loan.getStatus())
                statusTrue++;
        }
        if (statusTrue == 3){
            System.out.println("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal");
            return false;
        }
        return true;
    }
    
    // Mengecek apakah buku sudah dipinjam
    private boolean checkIsLoanBook(String judul, String penulis, int idxId){
        for (BookLoan loan: members[idxId].getBookLoans()){
            Book buku = loan.getBook();
            if (buku.getTitle().equalsIgnoreCase(judul) && buku.getAuthor().equalsIgnoreCase(penulis) && loan.getStatus()){
                System.out.printf("Buku %s oleh %s sedang dipinjam\n", judul, penulis);
                return false;
            }
        }
        return true;
    }

    // Mengecek jika buku tidak dipinjam
    private boolean checkNotLoanBook(String judul, String penulis, int idxId){
        for (BookLoan loan: members[idxId].getBookLoans()){
            Book buku = loan.getBook();
            if (buku.getTitle().equalsIgnoreCase(judul) && 
                buku.getAuthor().equalsIgnoreCase(penulis) && loan.getStatus() && loan.getStatus())
                return true;
        }
        System.out.printf("Buku %s tidak sedang dipinjam\n", judul);
        return false;
    }

    // Mencari member dari ID
    private int searchId(String id){
        int idx = 0;
        for (Member mem: members){
            if (mem.getId().equals(id))
                return idx;
            idx++;
        }
        System.out.printf("Anggota dengan ID %s tidak ditemukan\n", id);
        return -1;
    }

    // Mencari buku dari judul dan penulis
    private int searchBook(String judul, String penulis){
        int idx = 0;
        for (Book buk: books){
            if (buk.getTitle().equalsIgnoreCase(judul) && buk.getAuthor().equalsIgnoreCase(penulis))
                return idx; 
            idx++;
        }
        System.out.printf("Buku %s oleh %s tidak ditemukan\n", judul, penulis);
        return -1;
    }

    // Membuat mapping char ke value
    private void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    // Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
    private char getCharFromValue(int value) {
        return valueToChar[value];
    }

    // Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
    private int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    // Method generateId adalah method untuk membuat ID keanggotaan perpustakaan
    private String generateId(String programStudi, String angkatan, String tanggalLahir){
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
            return id;
        }
        else
            return "Tidak dapat menambahkan anggota silahkan periksa kembali input anda!";
    }

    // Method validateData unutk mengecek format input benar atau tidak
    private boolean validateData(String programStudi, String angkatan, String tanggalLahir){
        return (checkName(programStudi)&&checkGen(angkatan)&&checkDate(tanggalLahir));
    }

    // Method checkName untuk mengecek apakah nama prodi valid    
    private boolean checkName(String rawName){
        String[] contenName = new String[]{"SIK","SSI","MIK","MTI","DIK"};
        for (String name: contenName){
            if (name.equals(rawName))
                return true;
        }
        return false;   
    }

    // Method checkGen untuk mengecek apakah angkatan valid    
    private boolean checkGen(String rawGen){
        int gen = Integer.parseInt(rawGen);
        return (rawGen.length()==4 && 2000<=gen && gen<=2021);
    }

    // Method checkDate untuk mengecek apakah date valid
    private boolean checkDate(String rawDate){

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
    private char checkSumC(String idAnggota){
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
    private char checkSumK(String idAnggota){
        // Menjumlahkan perkalian 12 sampai 1 dari kiri ke kanan kode kemudian mod 36
        String id12 = idAnggota.substring(0,12);
        int sumK = 0;
        
        for (int i=1; i<13; i++){
            Character val = id12.charAt(12-i);
            sumK += i*getValueFromChar(val);
        }
        return getCharFromValue(sumK%36); 
    }

    // Mengurutkan member sesuai peringkat poin
    private Member[] sortArray(Member[] members){        
        members = members.clone();

        for (int i = 0; i < 3; i++) {     
            for (int j = i+1; j < members.length; j++) {     
                if(members[i].getPoint() < members[j].getPoint()){    
                    Member temp = members[i];    
                    members[i] = members[j];    
                    members[j] = temp;    
                } 
                // Jika poin sama , diurutkan berdasar abjad
                else if(members[i].getPoint() == members[j].getPoint()){   
                    if(members[i].getName().compareTo(members[j].getName())>0){
                        Member temp = members[i];    
                        members[i] = members[j];    
                        members[j] = temp;  
                    }
                }
            }     
        }    
        return members;
    }   
    
}
