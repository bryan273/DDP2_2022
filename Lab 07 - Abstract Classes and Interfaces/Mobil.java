import java.util.HashMap;

abstract class Mobil {
    private String nama;
    private int persenFuel;
    private String bahanBakar;
    private String jenis;
    private EngineBehaviour engineBehaviour;
    private boolean isOn;

    // Constructor mobil
    protected Mobil (String nama, EngineBehaviour engineBehaviour, String bahanBakar, String jenis){
        this.nama = nama;
        this.engineBehaviour = engineBehaviour;
        this.bahanBakar = bahanBakar;
        this.jenis = jenis;
        this.persenFuel = 100;
    }

    // Menyalakan mobil
    public String start(){
        this.isOn = true;

        // Memilih mobil berbahan BBM atau Listrik
        if (engineBehaviour instanceof Listrik)
            return ((Listrik)engineBehaviour).start(this);
        else
            return ((BBM)engineBehaviour).start(this);   
    }

    // Menjalankan mobil
    public String gas(){
        // Mobil diharuskan menyala dan bensin ada
        if (!this.isOn)
            return "Nyalakan mobil dulu!";
        if (persenFuel==0)
            return "Bensin habis!";

        // Membuat hashmap untuk tempat mobil 
        HashMap<String, String> tempat = new HashMap<String, String>();
        tempat.put("Air", "Laut");
        tempat.put("Terbang", "Langit");
        tempat.put("Truk", "Jalan Raya");

        // Menjalankan mobil sesuai jenis
        if (engineBehaviour instanceof Listrik)
            this.persenFuel = ((Listrik)engineBehaviour).gas(this.persenFuel);
        else
            this.persenFuel = ((BBM)engineBehaviour).gas(this.persenFuel);

        return String.format("%s digas dengan cepat di %s! Bahan bakar mobil %s sekarang %d%%."
                            ,this.nama, tempat.get(this.jenis), this.bahanBakar, this.persenFuel);
    }
  
    // Memberhentikan mobil
    public String stop(){
        this.isOn = false;
        
        // Memberhentikan mobil sesuai jenis
        if (engineBehaviour instanceof Listrik)
            return ((Listrik)engineBehaviour).stop(this);
        else
            return ((BBM)engineBehaviour).stop(this);   
    }
  
    public abstract String isiBahanBakar();
    public abstract String[] simulasi();

    // Getter & Setter
    public String getNama() {
        return nama; 
    }

    public int getPersenFuel() {
        return persenFuel; 
    }

    public void setPersenFuel(int persenFuel) {
        this.persenFuel = persenFuel;
    }

    public String getBahanBakar() {
        return bahanBakar; 
    }
  
    public boolean getIsOn() {
        return isOn; 
    }

}