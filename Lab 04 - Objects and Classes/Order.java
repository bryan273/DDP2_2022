public class Order {
    private Barang barang;
    private int banyakBarang;

    // Constructor order
    public Order(Barang barang, int banyakBarang) {
        this.barang = barang;
        this.banyakBarang = banyakBarang;
    }

    // Get barang orderan
    public Barang getBarang() {
        return barang;
    }

    // Get banyak barang orderan
    public int getBanyakBarang(){
        return banyakBarang;
    }

    // Set barang
    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    // Set banyak barang
    public void setBanyakBarang(int banyakBarang){
        this.banyakBarang = banyakBarang;
    }
}