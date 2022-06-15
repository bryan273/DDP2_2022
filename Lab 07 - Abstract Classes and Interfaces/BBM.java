public class BBM implements EngineBehaviour {
    // Menyalakan mesin
    public String start(Mobil mobil){    
        return mobil.getNama() + " menyalakan mesin, NGENG!";
    }

    // Mengurangi persentase bensin
    public int gas(int persenFuel){
        return persenFuel - 25;
    }

    // Memberhentikan mesin 
    public String stop(Mobil mobil){
        return mobil.getNama() + " mesin mati, mobil istirahat dulu.";
    }  
}