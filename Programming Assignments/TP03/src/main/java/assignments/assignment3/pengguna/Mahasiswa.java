package assignments.assignment3.pengguna;
import  assignments.assignment3.buku.Buku;

// Class untuk mengatur hal yang dapat dilakukan mahasiswa
public class Mahasiswa extends Anggota{
    public static final int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    public static final long BATAS_MAKSIMAL_DENDA = 5000;
    private String tanggalLahir;
    private String programStudi;
    private String angkatan;

    // Constructor untuk mahasiswa
    public Mahasiswa(String nama, String tanggalLahir, 
                  String programStudi, String angkatan){
        super(generateId(programStudi, angkatan, tanggalLahir), nama);
        this.tanggalLahir = tanggalLahir;
        this.programStudi = programStudi;
        this.angkatan = angkatan;
    }

    // Membuat ID mahasiswa
    protected String generateId(){
        return super.generateId(programStudi, angkatan, tanggalLahir);
    }

    // Meminjam buku
    public String pinjam(Buku buku, String tanggalPeminjaman){
        return super.pinjam(buku, tanggalPeminjaman);
    }
}
