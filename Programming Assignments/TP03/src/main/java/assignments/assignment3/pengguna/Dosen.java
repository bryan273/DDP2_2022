package assignments.assignment3.pengguna;
import  assignments.assignment3.buku.Buku;

// Class untuk mengatur hal yang dapat dilakukan dosen
public class Dosen extends Anggota{
    private static int jumlahDosen = 0;
    public static final int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    public static final long BATAS_MAKSIMAL_DENDA = 10000;

    // Constructor dosen
    public Dosen(String nama){
        super("DOSEN-"+(++jumlahDosen), nama);
    }

    // Membuat ID dosen
    protected String generateId(){
        return "DOSEN-"+(jumlahDosen);
    }
    
    // Melakukan pinjam buku
    public String pinjam(Buku buku, String tanggalPeminjaman){
        return super.pinjam(buku, tanggalPeminjaman);   
    }
}
