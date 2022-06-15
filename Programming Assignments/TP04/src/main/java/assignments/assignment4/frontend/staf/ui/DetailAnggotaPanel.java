package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

// Class to see the detail of the user
public class DetailAnggotaPanel extends SistakaPanel {
    private JComboBox<String> comboId;
    private JTextPane detail;
    private JScrollPane scrollPane;

    public DetailAnggotaPanel(HomeGUI main) {
        super(main);

        // Set the layout and customization
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set the label for the tile
        JLabel judul = new JLabel("Lihat Detail Anggota");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set the label for choosing id
        JLabel id = new JLabel("Pilih id Anggota");
        c.gridy=1;
        add(id, c);
        
        // Make a combobox that consists list of user
        String[] empty = {""};
        comboId = new JComboBox<String>(empty);
        c.gridy=2;
        add(comboId, c);

        // Make text panel for the detail of user
        detail=new JTextPane();
        detail.setOpaque(false);
        detail.setContentType("text/html");
        detail.setText("");
        detail.setMaximumSize(new Dimension(200, 200));
        detail.setEditable(false);

        // Make scroll panel in case the text is long
        scrollPane = new JScrollPane(detail);
        scrollPane.setPreferredSize(new Dimension(100,150));
        c.ipadx=250;
        c.ipady=250;
        c.gridy = 3;
        add(scrollPane, c);

        // Panel for lihat and kembali button 
        JPanel panel = new JPanel();
        JButton lihat = new JButton("Lihat");
        panel.add(lihat);

        JButton kembali = new JButton("Kembali");
        panel.add(kembali);

        c.ipady=0;
        c.gridy=4;
        c.ipadx=0;
        add(panel, c);

        // Action if lihat button is clicked
        lihat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // If there aren't any user
                if (comboId.getSelectedItem().toString().equals("")){
                    JOptionPane.showMessageDialog(null, 
                    "Silahkan memilih ID Anggota!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Choose the selected user and display the detail
                Anggota anggota = SistakaNG.findAnggota(comboId.getSelectedItem().toString());
                String detailAnggota = anggota.detail();
                detail.setText(detailAnggota);
            }   
        });

        // If kembali is clicked
        kembali.addActionListener(new ActionListener() {
            @Override
            // Back to staf home panel
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        // Refresh the text
        detail.setText("");
        // Check whether there are registered user
        if (SistakaNG.getDaftarAnggota().size()>0){
            // Delete the previous registered user and update it again
            String[] daftarNamaAnggota = new String[SistakaNG.getDaftarAnggota().size()];
            ArrayList<Anggota> listAnggota = new ArrayList<Anggota>(SistakaNG.getDaftarAnggota());

            for (int i=0; i<listAnggota.size(); i++){
                daftarNamaAnggota[i]=listAnggota.get(i).getId();
            }
            comboId.removeAllItems();
            for(String s:daftarNamaAnggota){
                comboId.addItem(s);
            }

            comboId.setSelectedIndex(0);

        }
        else{
            // Refresh with empty combobox if it is still empty
            String[] empty = {""};
            comboId.removeAllItems();
            for(String s:empty){
                comboId.addItem(s);
            }
        }
    }
}
