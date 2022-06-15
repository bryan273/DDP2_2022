// Class untuk mengecek apakah mahasiswa sehat
public class Mahasiswa {
    private static int MINIMUM_TINGKAT_KESEHATAN = 70;
    private String nama;
    private int tingkatKesehatan;

    // Constructor mahasiswa
    public Mahasiswa(String nama, int tingkatKesehatan) {
        this.nama = nama;
        this.tingkatKesehatan = tingkatKesehatan;
    }

    // Mendapatkan status kesehatan mahasiswa
    public String getStatus() {
        if (tingkatKesehatan >= MINIMUM_TINGKAT_KESEHATAN)
            return "Layak mengikuti program";
        else
            return "Tidak layak mengikuti program";
    }

    // Get nama
    public String getNama(){
        return this.nama;
    }

    // Untuk mencetak sebagai output di file
    @Override
    public String toString() {
        return String.format("%-24s| %s\n", this.nama, this.getStatus());
    }
}
