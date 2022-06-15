package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Class to add books
public class TambahBukuPanel extends SistakaPanel {
    private JTextField textJudul = new JTextField("");
    private JTextField textPenulis = new JTextField("");
    private JTextField textPenerbit = new JTextField("");
    private JComboBox<String> comboKategori;
    private JTextField textStok = new JTextField("");
    GridBagConstraints c = new GridBagConstraints();

    public TambahBukuPanel(HomeGUI main) {
        super(main);

        // Set layout and customization
        setLayout(new GridBagLayout());
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set label for title
        JLabel judul = new JLabel("Tambah Buku");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set label book title
        JLabel judulBuku = new JLabel("Nama");
        c.gridy=1;
        add(judulBuku, c);

        // Set text for book title input
        textJudul.setHorizontalAlignment(JLabel.CENTER);
        c.ipadx = 400;
        c.gridy=2;
        add(textJudul, c);

        // Set author label
        JLabel penulis = new JLabel("Penulis");
        c.gridy=3;
        c.ipadx = 0;
        add(penulis, c);

        // Set text for author name input
        textPenulis.setHorizontalAlignment(JLabel.CENTER);
        c.gridy=4;
        c.ipadx = 400;
        add(textPenulis, c);

        // Set label for publisher
        JLabel penerbit = new JLabel("Penerbit");
        c.gridy=5;
        c.ipadx = 0;
        add(penerbit, c);

        // Set text for publisher input
        textPenerbit.setHorizontalAlignment(JLabel.CENTER);
        c.gridy=6;
        c.ipadx = 400;
        add(textPenerbit, c);

        // Set label for category
        JLabel kategori = new JLabel("Kategori");
        c.gridy=7;
        c.ipadx = 0;
        add(kategori, c);

        // Set combobox for category list
        String[] empty = {""};
        comboKategori = new JComboBox<String>(empty);
        c.gridy=8;
        add(comboKategori, c);

        // Set label for stock
        JLabel stok = new JLabel("Stok");
        c.gridy=9;
        add(stok, c);

        // Set text for stock input
        textStok.setHorizontalAlignment(JLabel.CENTER);
        c.gridy=10;
        c.ipadx = 400;
        add(textStok, c);

        // Set panel for tambah and kembali button
        JPanel panel = new JPanel();
        JButton tambah = new JButton("Tambah");
        panel.add(tambah);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=11;
        c.ipadx=0;
        add(panel, c);

        // Action if tambah is clicked
        tambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check whether there are empty field
                if (textJudul.getText().equals("")|| textPenulis.getText().equals("")|| 
                    textPenerbit.getText().equals("")|| comboKategori.getSelectedItem().toString().equals("")||
                    textStok.getText().equals("")){
                    JOptionPane.showMessageDialog(null, 
                        "Input tidak boleh kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                    }

                // Check whether stock input is valid
                try{
                    Integer.valueOf(textStok.getText());
                }
                catch(NumberFormatException f){
                    JOptionPane.showMessageDialog(null, 
                    "Stok harus berupa angka!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Make a booj object
                Buku buku = SistakaNG.addBuku(textJudul.getText(), textPenulis.getText(), textPenerbit.getText(), 
                                  comboKategori.getSelectedItem().toString(),Integer.valueOf(textStok.getText()));
                
                // Check for invalid case
                if (buku == null){
                    String text ="";
                    Buku bukuSearched = SistakaNG.findBuku(textJudul.getText(), textPenulis.getText());

                    // If books has been added
                    if (bukuSearched != null)
                        text = String.format("Buku %s oleh %s sudah pernah ditambahkan%n", bukuSearched.getJudul(), bukuSearched.getPenulis());

                    // If stock is not available
                    if (Integer.valueOf(textStok.getText()) <= 0) 
                        text = String.format("Stok harus lebih dari 0!");

                    JOptionPane.showMessageDialog(null, 
                            text, "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else
                    // Add book if all conditions are satisfied
                    JOptionPane.showMessageDialog(null, 
                    String.format("Buku %s oleh %s berhasil ditambahkan%n", textJudul.getText(), textPenulis.getText()), 
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Action to return to staff home panel
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
        });
    }


    @Override
    public void refresh() {
        // Refresh text field
        textJudul.setText("");
        textPenulis.setText("");
        textPenerbit.setText("");
        textStok.setText("");

        // Check whether the category list is available
        if (SistakaNG.getDaftarKategori().size()>0){
            // Delete the combobox value and update it again
            String[] daftarNamaKategori = new String[SistakaNG.getDaftarKategori().size()];
            ArrayList<Kategori> listKategori = new ArrayList<Kategori>(SistakaNG.getDaftarKategori());

            for (int i=0; i<listKategori.size(); i++){
                daftarNamaKategori[i]=listKategori.get(i).getNama();
            }

            comboKategori.removeAllItems();
            for(String s:daftarNamaKategori){
                comboKategori.addItem(s);
            }

            comboKategori.setSelectedIndex(0);

        }
        else{
            // Delete and update the empty value
            String[] empty = {""};
            comboKategori.removeAllItems();
            for(String s:empty){
                comboKategori.addItem(s);
            }
        }
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
    }
}
