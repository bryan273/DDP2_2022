public class MobilAir extends Mobil {
    
    // Constructor mobil air
    public MobilAir(String nama, EngineBehaviour engineBehaviour, String bahanBakar){
        super(nama, engineBehaviour, bahanBakar, "Air");
    }

    // Mengisi bahan bakar mobil air
    @Override
    public String isiBahanBakar(){
        // Memastikan mobil nyala
        if (this.getIsOn())
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak tenggelam.";
        this.setPersenFuel(100);
        return this.getBahanBakar() + " sekarang sudah penuh, mobil dapat digunakan kembali!";
    }

    // Melakukan simulasi mobil air
    @Override
    public String[] simulasi(){
        String[] output = new String[8];

        // Memasukkan simulasi ke array
        output[0] = this.start();
        for(int i=0; i<5; i++)
            output[i+1] = this.gas();
        output[6] = this.stop();
        output[7] = this.isiBahanBakar();
        return output;
    }   
}