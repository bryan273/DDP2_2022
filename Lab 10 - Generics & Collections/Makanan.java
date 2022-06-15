public class Makanan extends Pesanan {
    // Food attributes
    private int tingkatKepedasan;

    // Constructor food
    public Makanan(String nama, int harga, int prioritas, int tingkatKepedasan) {
        super(nama, harga, prioritas);
        this.tingkatKepedasan = tingkatKepedasan;
    }

    // Description of food
    @Override
    public String toString() {
        return this.getNama() + " level "+ this.tingkatKepedasan;
    }
}
