public class Pesanan implements Comparable<Pesanan> {
    // Order attributes
    private String nama; 
    private int harga;
    private int prioritas;

    // Order constructor
    public Pesanan(String nama, int harga, int prioritas) {
        this.nama = nama;
        this.harga = harga;
        this.prioritas = prioritas;
    }

    // compareTo method to compare order priority  
    @Override
    public int compareTo(Pesanan o) {
        if (this.prioritas < o.getPrioritas())
            return -1;
        else if (this.prioritas == o.getPrioritas())
            return 0;
        else
            return 1;
    }

    // Get priority
    public int getPrioritas(){
        return this.prioritas;
    }

    // Get order name
    public String getNama(){
        return this.nama;
    }

    // Get order price
    public long getHarga(){
        return this.harga;
    }
}