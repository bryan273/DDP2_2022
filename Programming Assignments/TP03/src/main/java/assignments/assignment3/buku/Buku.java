package assignments.assignment3.buku;

import assignments.assignment3.pengguna.Anggota;
import assignments.assignment3.pengguna.CanBorrow;
import java.util.ArrayList; 

// Class untuk mengatur atribut pada buku
public class Buku {
    private String judul;
    private String penulis;
    private String penerbit;
    private int stokAwal;
    private int stok;
    private Kategori kategori;
    private ArrayList<CanBorrow> daftarPeminjam = new ArrayList<CanBorrow>();
    private ArrayList<Anggota> daftarPeminjamAktif = new ArrayList<Anggota>();

    // Constructor buku
    public Buku(String judul, String penulis, String penerbit, int stokAwal, Kategori kategori){
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stokAwal = stokAwal;
        this.stok = stokAwal;
        this.kategori = kategori;
    }

    // Deskripsi buku
    public String toString(){
        String returnString = "Judul Buku: " + this.judul + "\n"+
                              "Penulis Buku: " + this.penulis + "\n"+
                              "Penerbit Buku: " + this.penerbit + "\n"+
                              this.kategori;
        return returnString;
    }

    // Menambah anggota peminjam buku
    public void tambahPeminjam(Anggota anggota){
        daftarPeminjam.add(anggota);
    }

    // Get jumlah peminjam buku
    public int getJumlahPeminjam(){
        return daftarPeminjam.size();
    }

    // Get daftar peminjam buku
    public ArrayList<CanBorrow> getDaftarPeminjam(){
        return daftarPeminjam;
    }

    // Menambah anggota peminjam buku yang masih aktif
    public void tambahPeminjamAktif(Anggota anggota){
        daftarPeminjamAktif.add(anggota);
    }

    // Get jumlah peminjam buku yang masih aktif
    public int getJumlahPeminjamAktif(){
        return daftarPeminjamAktif.size();
    }

    // Hapus peminjam yang masi aktif
    public void hapusPeminjamAktif(Anggota anggota){
        daftarPeminjamAktif.remove(anggota);
    }

    // Get judul
    public String getJudul(){
        return this.judul;
    }

    // Get stock
    public int getStok(){
        return this.stok;
    }

    // Get stock awal
    public int getStokAwal(){
        return this.stokAwal;
    }

    // Set stok
    public void setStok(int stok){
        this.stok = stok;
    }

    // Get penulis
    public String getPenulis(){
        return this.penulis;
    }

    // Get penerbit
    public String getPenerbit(){
        return this.penerbit;
    }

    // Get kategori
    public Kategori getKategori(){
        return this.kategori;
    }
}
