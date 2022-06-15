import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

public class DaftarPesanan<T extends Pesanan> {
    // Order list attributes
    List<T> pesanans;
    Iterator<T> iteratorPesanan;

    // Order list constructor
    public DaftarPesanan() {
        pesanans = new ArrayList<T>();
    }

    // Add order list
    public void tambahPesanan(T pesanan) {
        pesanans.add(pesanan);
    }

    // Get next order if available
    public T nextPesanan() {
        if (iteratorPesanan.hasNext())
            return iteratorPesanan.next();
        else
            return null;
    }

    // Sort and make order as iterator
    public void setIterator(){
        Collections.sort(pesanans);
        iteratorPesanan = pesanans.iterator();
    }
}
