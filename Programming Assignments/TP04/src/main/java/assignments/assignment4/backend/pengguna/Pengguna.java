package assignments.assignment4.backend.pengguna;

public abstract class Pengguna {
    private String id;
    private String nama;

    protected Pengguna(String nama) {
        this.nama = nama;
    }

    protected abstract String generateID();

    @Override
    public abstract String toString();

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public abstract String getTipe();
}
