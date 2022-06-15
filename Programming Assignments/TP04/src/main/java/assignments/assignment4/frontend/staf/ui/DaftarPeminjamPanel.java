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

// Class to check the borrower list
public class DaftarPeminjamPanel extends SistakaPanel {
    private JComboBox<String> comboBuku;
    private JTextPane daftar;
    private JScrollPane scrollPane;

    public DaftarPeminjamPanel(HomeGUI main) {
        super(main);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Make label for title
        JLabel judul = new JLabel("Lihat Daftar Peminjam");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Make label for choosing book
        JLabel pilihBuku = new JLabel("Pilih buku");
        c.gridy=1;
        add(pilihBuku, c);
        
        // Make combobox that will be consisted of books
        String[] empty = {""};
        comboBuku = new JComboBox<String>(empty);
        c.gridy=2;
        add(comboBuku, c);

        // Make text panel for the detail of borrowers
        daftar=new JTextPane();
        daftar.setOpaque(false);
        daftar.setContentType("text/html");
        daftar.setText("");
        daftar.setMaximumSize(new Dimension(200, 200));
        daftar.setEditable(false);

        // Make scroll panel in case the text is long
        scrollPane = new JScrollPane(daftar);
        scrollPane.setPreferredSize(new Dimension(100,150));
        c.ipadx=250;
        c.ipady=250;
        c.gridy = 3;
        add(scrollPane, c);

        // Make Panel for lihat and kembali button
        JPanel panel = new JPanel();
        JButton lihat = new JButton("Lihat");
        panel.add(lihat);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=4;
        c.ipadx=0;
        c.ipady=0;
        add(panel, c);

        // Action if lihat is clicked
        lihat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check whether there are books or not
                if (comboBuku.getSelectedItem().toString().equals("")){
                    JOptionPane.showMessageDialog(null, 
                    "Silahkan memilih buku!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Set list of people who borrowed the selected book
                String[] bukuDetail = comboBuku.getSelectedItem().toString().split(" oleh ");
                Buku buku = SistakaNG.findBuku(bukuDetail[0], bukuDetail[1]);
                daftar.setText(SistakaNG.daftarPeminjam(buku));
            }   
        });

        // Action if kembali is clicked
        kembali.addActionListener(new ActionListener() {
            @Override
            // Go back to staf home page panel
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        // If panel is refresh, refresh text
        daftar.setText("");
        
        // Check whether there are books available
        if (SistakaNG.getDaftarBuku().size()>0){
            String[] daftarDeskripsiBuku = new String[SistakaNG.getDaftarBuku().size()];
            ArrayList<Buku> listBuku = new ArrayList<Buku>(SistakaNG.getDaftarBuku());

            // Set the combobox with a list of book 
            for (int i=0; i<listBuku.size(); i++){
                daftarDeskripsiBuku[i]=listBuku.get(i).getJudul() + " oleh " +listBuku.get(i).getPenulis();
            }

            // Remove the value and place it again to refresh the value
            comboBuku.removeAllItems();
            for(String s:daftarDeskripsiBuku){
                comboBuku.addItem(s);
            }
            comboBuku.setSelectedIndex(0);

        }
        else{
            // If there isn't any books available
            String[] empty = {""};
            comboBuku.removeAllItems();
            for(String s:empty){
                comboBuku.addItem(s);
            }
        }
    }
}
