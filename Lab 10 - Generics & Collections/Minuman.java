public class Minuman extends Pesanan {
    // Drink attributes
    private boolean isPakeEs;

    // Drink constructor
    public Minuman(String nama, int harga, int prioritas, boolean isPakeEs) {
        super(nama, harga, prioritas);
        this.isPakeEs = isPakeEs;
    }

    // Drink description
    @Override
    public String toString() {
        if (this.isPakeEs)
            return this.getNama()+" dingin";
        else
            return this.getNama()+" hangat";
    }
}
