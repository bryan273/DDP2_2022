package assignments.assignment3.pengguna;

// Class untuk mengatur hal yang dapat dilakukan staf
public class Staf extends Pengguna {
    private static int jumlahStaf = 0;
    
    // Constructor staf
    public Staf(String nama){
        super("STAF-"+(++jumlahStaf), nama);
    }
    
    // Membuat ID staf
    protected String generateId(){
        return "STAF-"+(jumlahStaf);
    } 

    // Deskripsi staf
    public String toString(){
        String returnString = "ID Staf: " + getId() + "\n"+
                              "Nama Staf: " + getNama() + "\n";
        return returnString;
    }
}
