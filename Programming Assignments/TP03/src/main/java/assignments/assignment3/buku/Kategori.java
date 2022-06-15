package assignments.assignment3.buku;

// Class untuk mengatur atribut kategori
public class Kategori {
    private String nama;
    private int poin;

    // Constructor kategori
    public Kategori(String nama, int poin){
        this.nama = nama;
        this.poin = poin;
    }

    // Get kategori
    public String getKategori(){
        return this.nama;
    }

    // Get poin
    public int getPoin(){
        return this.poin;
    }

    // Deskripsi kategori
    public String toString(){
        String returnString = "Kategori: " + this.nama + "\n"+
                              "Poin: " + this.poin + "\n";
        return returnString;
    }


}
