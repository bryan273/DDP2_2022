package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;

// Class for user detail
public class DetailUserPanel extends SistakaPanel {
    private JTextPane detail;
    private JScrollPane scrollPane;

    public DetailUserPanel(HomeGUI main) {
        super(main);
        // Set layout and customization
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new InsetsUIResource(2, 0, 2, 0);
        c.ipady = 8;
        c.anchor=GridBagConstraints.CENTER;

        // Set title label
        JLabel judul = new JLabel("Lihat Detail Anggota");
        judul.setFont((new Font(judul.getName(), Font.BOLD,24)));
        c.gridy=0;
        add(judul, c);

        // Set text panel for detail
        detail=new JTextPane();
        detail.setOpaque(false);
        detail.setContentType("text/html");
        detail.setText("");
        detail.setMaximumSize(new Dimension(200, 200));
        detail.setEditable(false);

        // Set scroll pane if text is long
        scrollPane = new JScrollPane(detail);
        scrollPane.setPreferredSize(new Dimension(100,150));
        c.ipadx=200;
        c.ipady=200;
        c.gridy = 1;
        add(scrollPane, c);

        // Set kembali button
        JButton kembali = new JButton("Kembali");
        c.gridy=2;
        c.ipady=0;
        c.ipadx=0;
        add(kembali, c);

        // Return back to anggota home page if clicked
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        // Refresh the detail everytime being reload
        String detailAnggota = ((Anggota)SistakaNG.getPenggunaLoggedIn()).detail();
        detail.setText(detailAnggota);
    }
}
