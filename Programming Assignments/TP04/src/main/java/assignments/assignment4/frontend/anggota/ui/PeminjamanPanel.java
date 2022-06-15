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

// Class to borrow book
public class PeminjamanPanel extends SistakaPanel {
    private JComboBox<String> comboBuku;
    private JTextField textTanggal = new JTextField("");
    
    public PeminjamanPanel(HomeGUI main) {
        super(main);
        // Set layout and customization
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title to borrow book
        JLabel judul = new JLabel("Pinjam Buku");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set label for book name
        JLabel nama = new JLabel("Buku");
        c.gridy=1;
        add(nama, c);
        
        // Set combobox to choose book
        String[] empty = {""};
        comboBuku = new JComboBox<String>(empty);
        c.gridy=2;
        add(comboBuku, c);

        // Set the loan date label
        JLabel tanggal = new JLabel("Tanggal Peminjaman (DD/MM/YYYY)");
        c.gridy=3;
        add(tanggal, c);

        // Set the input text for loan date
        textTanggal.setHorizontalAlignment(JLabel.CENTER);
        c.ipadx = 400;
        c.gridy=4;
        add(textTanggal, c);

        // Set panel to pinjam and kembali button
        JPanel panel = new JPanel();
        JButton pinjam = new JButton("Pinjam");
        panel.add(pinjam);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=5;
        c.ipadx=0;
        add(panel, c);

        // Action if pinjam is clicked
        pinjam.addActionListener(new ActionListener() {
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
                
                // Borrow books if condition is satisfied
                String[] bukuPinjam = comboBuku.getSelectedItem().toString().split(" oleh ");
                Buku buku = SistakaNG.findBuku(bukuPinjam[0], bukuPinjam[1]);
                String kasus = SistakaNG.pinjamBuku(buku, textTanggal.getText());

                JOptionPane.showMessageDialog(null, 
                kasus, "Info", JOptionPane.INFORMATION_MESSAGE);
            }   
        });

        // Go back to anggota home page if clicked
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        // Refresh text input
        textTanggal.setText("");
        // Check whether the book list is available
        if (SistakaNG.getDaftarBuku().size()>0){
            // Remove and update the combobox again , everytime being reload
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
            // Remove and update the empty value, if there aren't any books
            String[] empty = {""};
            comboBuku.removeAllItems();
            for(String s:empty){
                comboBuku.addItem(s);
            }
        }
    }
}
