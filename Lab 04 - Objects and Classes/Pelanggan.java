public class Pelanggan {
    
    private String nama;
    private int uang;
    private Order[] keranjang;
    private final int kapasitasKeranjang = 5000;
    private int kapasitasSekarang = 0;
    private int panjangOrderan = 0;
    private int totalHarga;

    // Constructor pelanggan
    public Pelanggan(String nama, int uang, int kapasitas) {
        this.nama = nama;
        this.uang = uang;
        this.keranjang = new Order[kapasitas];
        this.totalHarga = 0;
    }
    
    // Method untuk menambah barang ke dalam keranjang
    public String addBarang(Barang barang, int banyakBarang){
        // Mengecek stock barang
        if (!barang.cekStock(banyakBarang))
            return "Stock "+barang.getNama()+ " kurang\n";
        
        // Mengecek jika kapasitas tidak muat
        int banyakBarangAwal = Integer.valueOf(banyakBarang);
        int beratTambahan = banyakBarang * barang.getBeratBarang();
        if (beratTambahan+kapasitasSekarang > kapasitasKeranjang){
            // Memperbaharui berapa barang yang masih muat
            banyakBarang = (int)(kapasitasKeranjang-kapasitasSekarang)/barang.getBeratBarang();
        }

        int idx = checkOrder(barang, keranjang);
        // Membuat barang baru di keranjang
        if (idx==0){
            panjangOrderan += 1;
            keranjang[panjangOrderan-1] = new Order(barang, banyakBarang);
        }
        // Menambah jumlah barang lama jika sudah terdapat dalam keranjang
        else{
            keranjang[idx].setBanyakBarang(keranjang[idx].getBanyakBarang()+banyakBarang);
        }

        // Return type jika berat kelebihan
        if (beratTambahan+kapasitasSekarang > kapasitasKeranjang){
            // Memperbaharui kapasitasSekarang , stock
            this.kapasitasSekarang += banyakBarang * barang.getBeratBarang();
            this.totalHarga += banyakBarang * barang.getHarga();
            barang.setStock(barang.getStock()-banyakBarang);
            return "Maaf "+banyakBarangAwal+" "+barang.getNama()+" terlalu berat, tetapi "
                    +banyakBarang+" "+barang.getNama()+" berhasil ditambahkan\n";
        }
        
        // Memperbaharui kapasitasSekarang , stock
        this.kapasitasSekarang += banyakBarang * barang.getBeratBarang();
        this.totalHarga += banyakBarang * barang.getHarga();
        barang.setStock(barang.getStock()-banyakBarang);

        return this.nama+" berhasil menambahkan "+banyakBarang+" "+barang.getNama()+"\n";
    }
    
    // Mengecek panjang orderan
    public int getPanjangOrderan(){      
        return this.panjangOrderan;
    }

    // Mengecek total harga barang
    public int getTotalHarga(){      
        return this.totalHarga;
    }

    // Set total harga barang
    public void setTotalHargaNull(){      
        this.totalHarga = 0;
    }

    // Set kapasitasSekarang null
    public void setKapasitasNull(){      
        this.kapasitasSekarang = 0;
    }
    
    // Mengecek jumlah uang pelanggan sekarang
    public String cekUang(){
        return "Uang "+this.nama+" sekarang "+this.getUang()+"\n";
    }

    // Get nama pelanggan 
    public String getNama() {
        return this.nama;
    }

    // Set nama pelanggan
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Get uang pelanggan
    public int getUang() {
        return this.uang;
    }

    // Set uang pelanggan
    public void setUang(int uang) {
        this.uang = uang;
    }

    // Get keranjang pelanggan
    public Order[] getKeranjang() {
        return keranjang;
    }

    // Set keranjang pelanggan kosong
    public void setKeranjangNull(int kapasitas) {
        this.keranjang = new Order[kapasitas];
        this.panjangOrderan = 0;
    }

    // Cek apakah barang terdapat di keranjang
    private int checkOrder(Barang barang, Order[] keranjang){
        for (int i=0; i<this.panjangOrderan; i++){
            if (barang.getNama().equals(keranjang[i].getBarang().getNama()))
                return i;
        }
        return 0;
    }

}