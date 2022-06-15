package assignments.assignment3.pengguna;
import  assignments.assignment3.buku.Buku;

// Interface untuk blueprint method pinjam dan kembali
public interface CanBorrow {
    String pinjam(Buku buku, String tanggalPeminjaman);
    String kembali(Buku buku, String tanggalPengembalian);

}
