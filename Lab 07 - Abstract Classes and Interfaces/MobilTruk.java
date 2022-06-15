public class MobilTruk extends Mobil{ 

    // Constructor mobil truk
    public MobilTruk(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        super(nama, engineBehaviour, bahanBakar, "Truk");
    }

    // Mengisi bahan bakar mobil truk
    @Override
    public String isiBahanBakar(){
        // Memastikan mobil menyala
        if (this.getIsOn())
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak meledak.";
        this.setPersenFuel(100);
        return this.getBahanBakar() + " sekarang sudah penuh, mobil dapat digaskeun kembali!";
    }

    // TMelakukan simulasi mobil truk
    @Override
    public String[] simulasi(){
        String[] output = new String[7];

        // Memasukkan simulasi ke array
        output[0] = this.start();
        for(int i=0; i<4; i++)
            output[i+1] = this.gas();
        output[5] = this.stop();
        output[6] = this.isiBahanBakar();
        return output;
    }


}