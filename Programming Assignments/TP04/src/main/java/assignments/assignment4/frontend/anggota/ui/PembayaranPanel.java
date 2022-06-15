package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;

// Class to pay loan fine
public class PembayaranPanel extends SistakaPanel {
    private JTextField textDenda = new JTextField("");

    public PembayaranPanel(HomeGUI main) {
        super(main);
        // Set layout and customization
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title to pay fine
        JLabel judul = new JLabel("Bayar Denda");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set label for amount of fine
        JLabel denda = new JLabel("Jumlah Denda");
        c.gridy=1;
        add(denda, c);

        // Set input text for amount of fine
        textDenda.setHorizontalAlignment(JLabel.CENTER);
        c.ipadx = 400;
        c.gridy=2;
        add(textDenda, c);

        // Add panel for bayar and kembali button
        JPanel panel = new JPanel();
        JButton bayar = new JButton("Bayar");
        panel.add(bayar);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.gridy=3;
        c.ipadx=0;
        add(panel, c);

        // Action if bayar is clicked
        bayar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Check whether input is number
                try{
                    Integer.valueOf(textDenda.getText());
                }
                catch(NumberFormatException f){
                    JOptionPane.showMessageDialog(null, 
                    "Jumlah Bayar harus berupa angka!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // If condition satisifed, pay the fine
                long jumlahDenda = Integer.valueOf(textDenda.getText());
                String kasus = SistakaNG.bayarDenda(jumlahDenda);

                JOptionPane.showMessageDialog(null, 
                kasus, "Info", JOptionPane.INFORMATION_MESSAGE);
            }   
        });

        // Go back to home anggota panel if clicked
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        // Refresh the input text
        textDenda.setText("");
    }
}
