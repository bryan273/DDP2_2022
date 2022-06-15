package assignments.assignment2;

// Class untuk member yang terdaftar
public class Member {
    private String id;
    private String name;
    private String dateOfBirth;
    private String studyProgram;
    private String angkatan;
    private long fine;
    private int point;
    private BookLoan[] bookLoans;

    // Constructor untuk member
    public Member(String id, String name, String dateOfBirth, 
                  String studyProgram, String angkatan){
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.studyProgram = studyProgram;
        this.angkatan = angkatan;
        this.fine = 0;
        this.point = 0;
        this.bookLoans = new BookLoan[0];
    }

    // Get id
    public String getId(){
        return this.id;
    }

    // Get tanggal lahir
    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    // Get angkatan
    public String getAngkatan(){
        return this.angkatan;
    }

    // Get program studi
    public String getStudyProgram(){
        return this.studyProgram;
    }

    // Get nama member
    public String getName(){
        return this.name;
    }   

    // Get denda member
    public long getFine(){
        return this.fine;
    }

    // Get poin member
    public long getPoint(){
        return this.point;
    }

    // Get buku yang dipinjam
    public BookLoan[] getBookLoans(){
        return this.bookLoans;
    }

    // Meminjam buku
    public void pinjam(Book book, String loanDate) {
        // Menambahkan panjang array +1 setiap kali data bertambah 
        BookLoan loan = new BookLoan(this, book, loanDate);

        int len = this.bookLoans.length;
        BookLoan[] bookLoansNew = new BookLoan[len + 1];
        System.arraycopy(this.bookLoans, 0, bookLoansNew, 0, len);
        bookLoansNew[len] = loan;
        this.bookLoans = bookLoansNew.clone();

        book.setStock(book.getStock()-1);
    }

    // Mengembalikan buku
    public void kembali(Book book, String returnDate) {
        // Mencari index buku
        int idx = 0;
        for (BookLoan loan: this.getBookLoans()){
            if (loan.getBook().getTitle().equals(book.getTitle()) && loan.getStatus())
                break;
            idx++;
        }

        // Memodifikasi poin ,denda ,tanggal return ,dan stok
        bookLoans[idx].setReturnDate(returnDate);
        bookLoans[idx].setStatus(false);
        this.point += bookLoans[idx].getBook().getCategory().getPoint();
        this.fine += bookLoans[idx].getFine();
        book.setStock(book.getStock()+1);
    }

    // Memberikan detail buku member
    public void detail() {
        System.out.print(this);
        System.out.println("Riwayat Peminjaman Buku :");
        for (int i=0; i<bookLoans.length; i++){
            System.out.printf("----------------- %d -----------------\n", i+1);
            System.out.print(bookLoans[i].getBook());
            System.out.print(bookLoans[i].getBook().getCategory());
            System.out.print(bookLoans[i]);   
        }
    }

    // Membayar denda
    public void bayarDenda(long amount) {
        // Jika ada kembalian
        if (amount >= this.getFine()){
            System.out.printf("%s berhasil membayar lunas denda\n", this.getName());
            System.out.printf("Jumlah kembalian: Rp %d\n", amount-this.getFine());
            this.fine = 0;
        }
        // Jika denda yang dibayar kurang 
        else{
            this.fine -= amount;
            System.out.printf("%s berhasil membayar denda sebesar Rp %d\n", this.getName(), amount); 
            System.out.printf("Sisa denda saat ini: Rp %d\n", this.getFine());
        }
    }

    // Memberikan deskripsi member
    @Override
    public String toString() {
        String returnString = "ID Anggota: " + this.id + "\n"+
                              "Nama Anggota: " + this.name + "\n"+
                              "Total Point: " + this.point + "\n"+
                              "Denda: " + this.fine + "\n";      
        return returnString;
    }
}
