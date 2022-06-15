public class Listrik implements EngineBehaviour{
    //  Menyalakan listrik
    public String start(Mobil mobil){    
        return mobil.getNama() + " menyalakan listrik, SIAP DIGAS!";
    }

    // Mengurangi persentase daya
    public int gas(int persenFuel){
        return persenFuel - 20;
    }

    // Memberhentikan mobil 
    public String stop(Mobil mobil){
        return mobil.getNama() + " listrik dimatikan, mobil telah berhenti.";
    }
}