package assignments.assignment2;
import java.util.Date;
import java.util.Calendar;

// Class untuk Buku yang dipinjam
public class BookLoan {
    private static long DENDA_PER_HARI = 3000;
    private Member member;
    private Book book;
    private String loanDate;
    private String returnDate;
    private long fine;
    private boolean status;

    // Constructor bookloan
    public BookLoan(Member member,Book book,String loanDate){
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = "-";
        this.fine = 0;
        this.status = true;
    }

    // Get fine
    public long getFine(){
        return this.fine;
    }   

    // Get status
    public boolean getStatus(){
        return this.status;
    }

    // Set status
    public void setStatus(boolean status){
        this.status = status;
    }

    // Get member
    public Member getMember(){
        return this.member;
    }
    
    // Get book
    public Book getBook(){
        return this.book;
    }

    // Set return date
    public void setReturnDate(String date){
        this.returnDate = date;
        this.calculateFine();
    }

    // Menghitung denda
    private void calculateFine(){
        String[] arr1 = this.loanDate.split("/");
        String[] arr2 = this.returnDate.split("/");
        
        // Date untuk loan date
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, Integer.valueOf(arr1[2]));
        calendar1.set(Calendar.MONTH, Integer.valueOf(arr1[1])-1);
        calendar1.set(Calendar.DATE, Integer.parseInt(arr1[0]));
        Date tanggalLoan = calendar1.getTime();

        // Date untuk return date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, Integer.valueOf(arr2[2]));
        calendar2.set(Calendar.MONTH, Integer.valueOf(arr2[1])-1);
        calendar2.set(Calendar.DATE, Integer.parseInt(arr2[0]));
        Date tanggalReturn = calendar2.getTime();

        long diff = tanggalReturn.getTime()-tanggalLoan.getTime();
        long days = diff / (1000 * 60 * 60 * 24);

        // Dikenakan denda bila lebih dari 7 hari
        if (days>7)
            this.fine = (days-7)*DENDA_PER_HARI;
    }

    // Memberikan deskripsi bookloan
    @Override
    public String toString() {
        String returnString = "Tanggal Peminjaman: " + this.loanDate + "\n"+
                              "Tanggal Pengembalian: " + this.returnDate + "\n"+
                              "Denda: Rp " + this.fine + "\n";
        return returnString;
    }
    
}