package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Dosen;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;

// Class to add lecturer
public class TambahDosenPanel extends SistakaPanel {
    private JTextField textNama = new JTextField("");

    public TambahDosenPanel(HomeGUI main) {
        super(main);
        // Set layout and customization
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title label
        JLabel judul = new JLabel("Tambah Dosen");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set nama label
        JLabel nama = new JLabel("Nama");
        c.gridy=1;
        add(nama, c);

        // Set text input for nama 
        textNama.setHorizontalAlignment(JLabel.CENTER);
        c.ipadx = 400;
        c.gridy=2;
        add(textNama, c);

        // Set panel for tambah and kembali button
        JPanel panel = new JPanel();
        JButton tambah = new JButton("Tambah");
        panel.add(tambah);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=3;
        c.ipadx=0;
        add(panel, c);

        // Action if tambah is clicked
        tambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check whether input is empty 
                if (textNama.getText().equals(""))
                    JOptionPane.showMessageDialog(null, 
                    "Tidak dapat menambahkan dosen silahkan periksa kembali input anda!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                else{
                    // Add dosen if input is not empty
                    Dosen dosen = SistakaNG.addDosen(textNama.getText());
                    JOptionPane.showMessageDialog(null, 
                    "Berhasil menambahkan dosen dengan id "+dosen.getId(), 
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
         });

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
        textNama.setText("");
    }
}
