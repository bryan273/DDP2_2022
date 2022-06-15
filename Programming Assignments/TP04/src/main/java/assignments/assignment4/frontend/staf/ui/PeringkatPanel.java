package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;

// Class to check the rank of the users
public class PeringkatPanel extends SistakaPanel {
    private JTextPane nama;
    private JScrollPane scrollPane;

    public PeringkatPanel(HomeGUI main) {
        super(main);

        // Set layout and customization
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title 
        JLabel judul = new JLabel("Peringkat");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set text panel for user rank
        String peringkat = SistakaNG.handleRankingAnggota();
        nama = new JTextPane();
        nama.setOpaque(false);
        nama.setContentType("text/html");
        nama.setText(peringkat);
        nama.setMaximumSize(new Dimension(200, 200));
        nama.setEditable(false);

        // Set scroll pane in case the text is long
        scrollPane = new JScrollPane(nama);
        scrollPane.setPreferredSize(new Dimension(100,150));
        c.ipadx=150;
        c.ipady=250;
        c.gridy = 1;
        add(scrollPane, c);

        // Add the kembali button
        JButton kembali = new JButton("Kembali");
        c.ipadx=0;
        c.ipady=0;
        c.gridy=2;
        add(kembali,c);

        // Action if kembali is clicked
        kembali.addActionListener(new ActionListener() {
            @Override
            // Go back to staf home page
            public void actionPerformed(ActionEvent e) {
                main.setPanel("staf");
            }
         });
    }

    @Override
    public void refresh() {
        // Refresh the user rank everytime switching page
        String peringkat = SistakaNG.handleRankingAnggota();
        nama.setText(peringkat);
    }

}
