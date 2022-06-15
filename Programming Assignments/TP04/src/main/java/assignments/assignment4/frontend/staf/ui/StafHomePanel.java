package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.border.*;

// Class for the staf home page
public class StafHomePanel extends SistakaPanel {
    private JLabel label = new JLabel("");

    public StafHomePanel(HomeGUI main) {
        super(main);

        // Add layout and customization
        setLayout(new GridLayout(11,1, 2,4));

        // Add panel for space border
        JPanel panel = new JPanel(new FlowLayout());
        label.setFont((new Font(label.getName(), Font.BOLD, 16)));
        label.setBorder(new EmptyBorder(0,20,0,0));
        panel.add(label);
        add(panel);

        // Add the button and the panel from the button
        JButton button;
        String[] menu = {"Tambah Mahasiswa", "Tambah Dosen", "Tambah Kategori", "Tambah Buku", "Hapus Buku",
                        "3 Peringkat Pertama", "Detail Anggota", "Daftar Peminjaman Buku", "Logout"};
        String[] menu_gui = {"tambahMhs", "tambahDosen", "tambahKategori", "tambahBuku", "hapusBuku",
                            "peringkat", "detailAnggota", "daftarPeminjam", "exit"};
        
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i=0; i<menu.length; i++){
            map.put(menu[i], menu_gui[i]);
        }

        // Add every button with its action if clicked
        for (String i: menu){
            button = new JButton(i);
            add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If logout is clicked, then go to welcome page
                    if (i.equals("Logout"))
                        main.setPanel("welcome");
                    else
                        main.setPanel(map.get(i));
                }
             });
        }
        setVisible(true);

    }

    @Override
    public void refresh() {
        // Refresh the user logged in label
        label.setText("Selamat Datang Kembali "+SistakaNG.getPenggunaLoggedIn().getNama());
    }

}
