package assignments.assignment2;

// Class untuk category buku
public class Category {
    private String name;
    private int point;

    // Constructor category
    public Category(String name, int point){
        this.name = name;
        this.point = point;
    }

    // Memberikan deskripsi kategori
    @Override
    public String toString() {
        String returnString = "Kategori: " + this.name + "\n"+
                              "Point: " + this.point + "\n";
        return returnString;
    }

    // Get category
    public String getCategory(){
        return this.name;
    }

    // Get point
    public long getPoint(){
        return this.point;
    }

}
