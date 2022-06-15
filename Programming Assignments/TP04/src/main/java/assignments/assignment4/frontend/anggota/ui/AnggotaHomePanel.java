package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.HashMap;
import java.awt.event.*;

// Class for anggota home page
public class AnggotaHomePanel extends SistakaPanel {
    private JLabel label = new JLabel("");

    public AnggotaHomePanel(HomeGUI main) {
        super(main);
        // Set layout 
        setLayout(new GridLayout(11,1, 2,4));
        
        // Add empty space
        JLabel empty_1= new JLabel();
        empty_1.setBorder(new EmptyBorder(40,0,0,0));
        add(empty_1);
        add(empty_1);

        // Set the title label
        JPanel panel = new JPanel(new FlowLayout());
        label.setFont((new Font(label.getName(), Font.BOLD, 16)));
        label.setBorder(new EmptyBorder(20,0,20,0));
        panel.add(label);
        add(panel);

        // Set empty space again
        JLabel empty_2= new JLabel();
        empty_2.setBorder(new EmptyBorder(0,0,40,0));
        add(empty_2);

        // Add each button for directing to each panel
        JButton button;
        String[] menu = {"Peminjaman", "Pengembalian", "Pembayaran Denda",
                        "Detail Anggota","Logout"};
        String[] menu_gui = {"peminjaman", "pengembalian", "pembayaran", 
                            "detailUser", "exit"};
        
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i=0; i<menu.length; i++){
            map.put(menu[i], menu_gui[i]);
        }

        // Add button with its action event
        for (String i: menu){
            button = new JButton(i);
            add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If logout, go back to welcome page
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
        // Refresh text everytime the page is reload
        label.setText("Selamat Datang Kembali "+SistakaNG.getPenggunaLoggedIn().getNama());
    }

}
