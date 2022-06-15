public class Barang {
  
    private String nama;
    private int harga;
    private int beratBarang;
    private int stock;

    // Constrructor class Barang
    public Barang(String nama, int harga, int beratBarang, int stock) {
        this.nama = nama;
        this.harga = harga;
        this.beratBarang = beratBarang;
        this.stock = stock;
    }
    
    // Mengecek apakah stock masih tersedia
    public boolean cekStock(int stock){
        return stock <= this.stock;    
    }
    
    // Get nama barang
    public String getNama() {
        return nama;
    }
    
    // Get stok barang 
    public int getStock(){
        return stock;
    }
  
    // Set stok barang
    public void setStock(int kuantitas){
        this.stock = kuantitas;
    }
    
    // Get berat barang
    public int getBeratBarang(){
        return beratBarang;
    }

    // Get harga barang
    public int getHarga(){
        return harga;
    }
    
}
