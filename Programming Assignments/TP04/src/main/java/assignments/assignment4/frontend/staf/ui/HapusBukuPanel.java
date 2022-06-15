package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Class to remove book
public class HapusBukuPanel extends SistakaPanel {
    private JComboBox<String> comboBuku;

    public HapusBukuPanel(HomeGUI main) {
        super(main);

        // Set layout and customization
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set label for title
        JLabel judul = new JLabel("Hapus Buku");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set label name for book
        JLabel nama = new JLabel("Buku");
        c.gridy=1;
        add(nama, c);
        
        // Set combobox for the book that will be selected
        String[] empty = {""};
        comboBuku = new JComboBox<String>(empty);
        c.gridy=2;
        add(comboBuku, c);

        // Set a panel for hapus and kembali panel
        JPanel panel = new JPanel();
        JButton hapus = new JButton("Hapus");
        panel.add(hapus);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=3;
        c.ipadx=0;
        add(panel, c);

        // Action if hapus is clicked
        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If there aren't any book in the list
                if (comboBuku.getSelectedItem().toString().equals("")){
                    JOptionPane.showMessageDialog(null, 
                    "Silahkan memilih buku!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Get the book object from the selected book
                String[] bukuRemoved = comboBuku.getSelectedItem().toString().split(" oleh ");
                Buku buku = SistakaNG.findBuku(bukuRemoved[0], bukuRemoved[1]);

                // Check if the book is borrowed
                if (buku.getStokAwal() != buku.getStok()) 
                    JOptionPane.showMessageDialog(null, 
                    String.format("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam%n",buku.getJudul(), buku.getPenulis()),
                    "Info", JOptionPane.INFORMATION_MESSAGE);
                else{
                    // If all of the condition satisfied, delete the book
                    SistakaNG.deleteBuku(buku);
                    JOptionPane.showMessageDialog(null, 
                    String.format("Buku %s oleh %s berhasil dihapus%n", buku.getJudul(), buku.getPenulis()), 
                    "Info", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                }
            }   
        });

        // Action to return to the staf home page
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        // Check whether there are book in the list
        if (SistakaNG.getDaftarBuku().size()>0){
            // Remove the book , then update it again to the combobox
            String[] daftarDeskripsiBuku = new String[SistakaNG.getDaftarBuku().size()];
            ArrayList<Buku> listBuku = new ArrayList<Buku>(SistakaNG.getDaftarBuku());

            for (int i=0; i<listBuku.size(); i++){
                daftarDeskripsiBuku[i]=listBuku.get(i).getJudul() + " oleh " +listBuku.get(i).getPenulis();
            }
            comboBuku.removeAllItems();
            for(String s:daftarDeskripsiBuku){
                comboBuku.addItem(s);
            }

            comboBuku.setSelectedIndex(0);

        }
        else{
            // Remove and update if there aren't any books
            String[] empty = {""};
            comboBuku.removeAllItems();
            for(String s:empty){
                comboBuku.addItem(s);
            }
        }
    }
}
