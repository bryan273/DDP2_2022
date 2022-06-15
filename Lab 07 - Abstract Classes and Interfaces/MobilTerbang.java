public class MobilTerbang extends Mobil{
    
    // Constructor mobil terbang
    public MobilTerbang(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        super(nama, engineBehaviour, bahanBakar, "Terbang");
    }

    // Mengisi bahan bakar mobil terbang
    @Override
    public String isiBahanBakar(){
        // Memastikan mobil menyala
        if (this.getIsOn())
            return "Mobil masih terbang, matikan terlebih dahulu agar tidak jatuh.";
        this.setPersenFuel(100);
        return this.getBahanBakar() + " sekarang sudah penuh, mobil dapat terbang kembali!";
    }

    // Melakukan simulasi mobil terbang
    @Override
    public String[] simulasi(){
        String[] output = new String[5];

        // Memasukkan simulasi ke array
        output[0] = this.start();
        for(int i=0; i<2; i++)
            output[i+1] = this.gas();
        output[3] = this.stop();
        output[4] = this.isiBahanBakar();
        return output;
    }
  
}