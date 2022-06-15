package assignments.assignment3.pengguna;

// Abstract class untuk membuat abstraksi tentang pengguna SistakaNG
public abstract class Pengguna {
    private String id;
    private String nama;
    protected abstract String generateId();
    public abstract String toString();

    // Constructor pengguna
    public Pengguna(String id, String nama){
        this.id = id;
        this.nama = nama;
    }

    // Get id
    public String getId(){
        return id;
    }

    // Get nama
    public String getNama(){
        return nama;
    }
}
