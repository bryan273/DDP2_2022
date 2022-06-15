package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.backend.pengguna.Staf;

import javax.swing.plaf.InsetsUIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Login Panel for user/staf login
public class LoginPanel extends SistakaPanel {
    // Create attribute for text
    private JTextField text = new JTextField("");

    public LoginPanel(HomeGUI main){
        super(main);
        // Set Layout
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // Make label for command and modify font
        JLabel label = new JLabel("Masukkan ID Anda untuk login ke sistem");
        label.setFont((new Font(label.getName(), Font.BOLD, 15)));
        c.gridy = 0;
        add(label,c);

        // Set text with input in the middle
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont((new Font(text.getName(), Font.BOLD, 13)));
        c.insets = new InsetsUIResource(4, 0, 4, 0);
        c.gridy = 1;
        c.ipadx = 400;
        c.ipady = 10;
        add(text, c);

        // Login button
        JButton login = new JButton("Login");
        c.ipadx = 0;
        c.gridy = 2;
        c.ipady = 3;
        add(login, c);
        
        // Action if login clicked
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user from the inputted id
                String value = text.getText();
                Pengguna pengguna = SistakaNG.handleLogin(value);

                // Check whether the id is valid
                if (value.equals(""))
                    JOptionPane.showMessageDialog(null, "Harap masukan id anda pada kotak diatas!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                else if (pengguna==null)
                    JOptionPane.showMessageDialog(null, "Pengguna dengan ID "+value+" tidak ditemukan",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                else{
                    // If valid , then set the user who is logged in
                    main.setUser(pengguna);
                    text.setText("");

                    // Changed to Panel based on the user type
                    if (pengguna instanceof Staf)
                        main.setPanel("staf");
                    else
                        main.setPanel("anggota");
                }
            }
         });

        setVisible(true);

    }

    @Override
    public void refresh() {
        // ignored
    }
}
