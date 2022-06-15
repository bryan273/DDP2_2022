package assignments.assignment2;

// Class untuk buku yang tersedia
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int stok;
    private Category category;

    // Constructor buku
    public Book(String title, String author, String publisher, int stok, Category category){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.stok = stok;
        this.category = category;
    }

    // Get title
    public String getTitle(){
        return this.title;
    }

    // Get stock
    public int getStock(){
        return this.stok;
    }

    // Set stock
    public void setStock(int stok){
        this.stok = stok;
    }

    // Get penulis
    public String getAuthor(){
        return this.author;
    }

    // Get publisher
    public String getPublisher(){
        return this.publisher;
    }

    // Get kategori
    public Category getCategory(){
        return this.category;
    }

    // Memberi deskripsi buku
    @Override
    public String toString() {
        String returnString = "Judul Buku: " + this.title + "\n"+
                              "Penulis Buku: " + this.author + "\n"+
                              "Penerbit Buku: " + this.publisher + "\n";
        return returnString;
    }
}
