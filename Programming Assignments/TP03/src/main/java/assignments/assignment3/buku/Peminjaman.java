package assignments.assignment3.buku;

import assignments.assignment3.pengguna.Anggota;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Class untuk mengatur peminjaman
public class Peminjaman {
    private static final long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian = "-";
    private long denda = 0;
    private boolean status = true;

    // Constructor peminjaman
    public Peminjaman(Anggota anggota,Buku buku,String tanggalPeminjaman){
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.tanggalPengembalian = "-";
        this.status = true;
    }

    // Deskripsi peminjaman
    public String toString(){
        String returnString = this.buku +
                              "Tanggal Peminjaman: " + this.tanggalPeminjaman + "\n"+
                              "Tanggal Pengembalian: " + this.tanggalPengembalian + "\n"+
                              "Denda: Rp" + this.denda + "\n";
        return returnString;
    }

    // Mengembalikan buku yang dipinjam 
    public void kembalikanBuku(String tanggalPengembalian){
        this.status = false;
        this.tanggalPengembalian = tanggalPengembalian;
        this.denda = hitungDenda();
    }

    // Menghitung denda
    public long hitungDenda(){
        long days = 0;

        // Menghitung perbedaan hari peminjaman dan pengembalian buku
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date tanggalLoan = format.parse(tanggalPeminjaman);
            Date tanggalReturn= format.parse(tanggalPengembalian);

            long diff = tanggalReturn.getTime()-tanggalLoan.getTime();
            days = diff / (1000 * 60 * 60 * 24);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        // Dikenakan denda bila lebih dari 7 hari
        if (days>7)
            return (days-7)*DENDA_PER_HARI;
        else
            return this.denda;
    }

    // Get denda
    public long getDenda(){
        return this.denda;
    }

    // Get buku
    public Buku getBuku(){
        return this.buku;
    }

    // Get status
    public boolean getStatus(){
        return this.status;
    }

    // Get anggota
    public Anggota getAnggota(){
        return this.anggota;
    }
}
