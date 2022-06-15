package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;

// Class to add category
public class TambahKategoriPanel extends SistakaPanel {
    private JTextField textNama = new JTextField("");
    private JTextField textPoin = new JTextField("");

    public TambahKategoriPanel(HomeGUI main) {
        super(main);
        // Set layout and customization 
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title label
        JLabel judul = new JLabel("Tambah Kategori");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);
    
        // Set label for nama
        JLabel nama = new JLabel("Nama");
        c.gridy=1;
        add(nama, c);

        // Set input text for nama
        textNama.setHorizontalAlignment(JLabel.CENTER);
        c.ipadx = 400;
        c.gridy=2;
        add(textNama, c);

        // Set label for poin
        JLabel poin = new JLabel("Poin");
        c.gridy=3;
        c.ipadx = 0;
        add(poin, c);

        // Set input text for poin
        textPoin.setHorizontalAlignment(JLabel.CENTER);
        c.gridy=4;
        c.ipadx = 400;
        add(textPoin, c);

        // Set panel for tambah and kembali button
        JPanel panel = new JPanel();
        JButton tambah = new JButton("Tambah");
        panel.add(tambah);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=9;
        c.ipadx=0;
        add(panel, c);

        // Action if tambah is clicked
        tambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Check if input field is empty
                if (textNama.getText().equals("")|| textPoin.getText().equals("")){
                    JOptionPane.showMessageDialog(null, 
                        "Input tidak boleh kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Check if poin is number
                try{
                    Integer.valueOf(textPoin.getText());
                }
                catch(NumberFormatException f){
                    JOptionPane.showMessageDialog(null, 
                    "Poin harus berupa angka!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Check whether category condition is satisfied
                Kategori kategori = SistakaNG.addKategori(textNama.getText(), Integer.valueOf(textPoin.getText()));
                if (kategori == null){
                    // If category has been added before
                    kategori = SistakaNG.findKategori(textNama.getText());

                    JOptionPane.showMessageDialog(null, 
                    String.format("Kategori %s sudah pernah ditambahkan%n", kategori.getNama()), 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else
                    // If category successfully added
                    JOptionPane.showMessageDialog(null, 
                    String.format("Kategori %s dengan poin %d berhasil ditambahkan%n", kategori.getNama(), 
                    kategori.getPoin()), "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
         });

         // Action to return to the staf home panel
         kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
         });
    }

    @Override
    public void refresh() {
        // Refresh the text field
        textNama.setText("");
        textPoin.setText("");
    }
}
