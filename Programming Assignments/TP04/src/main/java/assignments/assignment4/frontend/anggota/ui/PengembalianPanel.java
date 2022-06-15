package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Class to return the book
public class PengembalianPanel extends SistakaPanel {
    private JComboBox<String> comboBuku;
    private JTextField textTanggal = new JTextField("");

    public PengembalianPanel(HomeGUI main) {
        super(main);
        // Set layout and customization
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title label
        JLabel judul = new JLabel("Pengembalian Buku");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set label for books
        JLabel nama = new JLabel("Buku");
        c.gridy=1;
        add(nama, c);
        
        // Set combobox for books
        String[] empty = {""};
        comboBuku = new JComboBox<String>(empty);
        c.gridy=2;
        add(comboBuku, c);

        // Set label for return date
        JLabel tanggal = new JLabel("Tanggal Pengembalian (DD/MM/YYYY)");
        c.gridy=3;
        add(tanggal, c);

        // Set input text for return date
        textTanggal.setHorizontalAlignment(JLabel.CENTER);
        c.ipadx = 400;
        c.gridy=4;
        add(textTanggal, c);

        // Set panel for kembalikan and kembali button
        JPanel panel = new JPanel();
        JButton kembalikan = new JButton("Kembalikan");
        panel.add(kembalikan);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=5;
        c.ipadx=0;
        add(panel, c);

        // Action if kembalikan is clicked
        kembalikan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If there aren't any books
                if (comboBuku.getSelectedItem().toString().equals("")){
                    JOptionPane.showMessageDialog(null, 
                    "Silahkan memilih buku!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // If the date is not valid
                if (!SistakaPanel.isDateValid(textTanggal.getText())){
                    JOptionPane.showMessageDialog(null, 
                    "Tanggal yang dimasukan harus dalam format DD/MM/YYYY",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Return book if the condition is satisfied
                String[] bukuKembali = comboBuku.getSelectedItem().toString().split(" oleh ");
                Buku buku = SistakaNG.findBuku(bukuKembali[0], bukuKembali[1]);
                String kasus = SistakaNG.kembalikanBuku(buku, textTanggal.getText());

                JOptionPane.showMessageDialog(null, 
                kasus, "Info", JOptionPane.INFORMATION_MESSAGE);
            }   
        });

        // Return back to anggota home page
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        // Refresh text
        textTanggal.setText("");
        // Check whether there are any books
        if (SistakaNG.getDaftarBuku().size()>0){
            // Remove and update the books again for the combobox
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
            // Remove and update the empty combobox
            String[] empty = {""};
            comboBuku.removeAllItems();
            for(String s:empty){
                comboBuku.addItem(s);
            }
        }
    }
}
