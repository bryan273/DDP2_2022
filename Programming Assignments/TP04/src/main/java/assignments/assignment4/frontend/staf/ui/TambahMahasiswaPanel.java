package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Mahasiswa;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.InsetsUIResource;

// Class to add students
public class TambahMahasiswaPanel extends SistakaPanel {
    private JTextField textNama = new JTextField("");
    private JTextField textTanggal = new JTextField("");
    private JComboBox<String> comboAngkatan;
    private JTextField textAngkatan = new JTextField("");

    public TambahMahasiswaPanel(HomeGUI main) {
        super(main);
        // Set layout and customization
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title label
        JLabel judul = new JLabel("Tambah Mahasiswa");
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

        // Set label for date
        JLabel tanggal = new JLabel("Tanggal Lahir (DD/MM/YYYY)");
        c.gridy=3;
        c.ipadx = 0;
        add(tanggal, c);

        // Set input text for date
        textTanggal.setHorizontalAlignment(JLabel.CENTER);
        c.gridy=4;
        c.ipadx = 400;
        add(textTanggal, c);

        // Set label for study program
        JLabel prodi = new JLabel("Program Studi");
        c.gridy=5;
        c.ipadx = 0;
        add(prodi, c);

        // Set combobox for study program
        String[] listAngkatan = {"SIK", "SSI", "MIK", "MTI", "DIK"};
        comboAngkatan = new JComboBox<String>(listAngkatan);
        c.gridy=6;
        add(comboAngkatan, c);

        // Set the label for generation
        JLabel angkatan = new JLabel("Angkatan");
        c.gridy=7;
        add(angkatan, c);

        // Set input text for generation
        textAngkatan.setHorizontalAlignment(JLabel.CENTER);
        c.gridy=8;
        c.ipadx = 400;
        add(textAngkatan, c);

        // Set the panel for tambah and kembali button
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

                // Check whether condition to add students is satisfied
                Mahasiswa anggota = SistakaNG.addMahasiswa(textNama.getText(), textTanggal.getText(), 
                                  comboAngkatan.getSelectedItem().toString(), textAngkatan.getText());
                if (anggota == null)
                    // If condition is not satisfied
                    JOptionPane.showMessageDialog(null, 
                    "Tidak dapat menambahkan mahasiswa silahkan periksa kembali input anda!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                else
                    // Condition is satisfied
                    JOptionPane.showMessageDialog(null, 
                    "Berhasil menambahkan mahasiswa dengan id "+anggota.getId(), 
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
         });

         // Go back to home staf page
         kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
         });
    }

    @Override
    public void refresh() {
        // Refresh input text field
        textNama.setText("");
        textTanggal.setText("");
        textAngkatan.setText("");
        comboAngkatan.setSelectedIndex(0);
    }
}
