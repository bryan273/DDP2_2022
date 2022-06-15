package assignments.assignment4.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.plaf.InsetsUIResource;

// Class for welcome panel
public class WelcomePanel extends SistakaPanel {
    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Welcome Label with modified font
        JLabel welcome = new JLabel("Welcome to SistakaNG");
        welcome.setFont((new Font("Helvetica", Font.BOLD, 24)));
        c.ipady = 40;
        c.gridy= 0;
        add(welcome, c);

        // Make empty space with border
        c.gridy=1;
        JPanel panel =new JPanel();
        panel.setBorder(new EmptyBorder(100,50,90,50));
        add(panel, c);

        // Make login button
        JButton login = new JButton("Login");
        c.ipady = 10;
        c.ipadx = 270;
        c.gridy = 2;
        c.insets = new InsetsUIResource(10, 0, 10, 0);
        add(login, c);

        // Action if user click login
        login.addActionListener(new ActionListener() {
            @Override
            // Set panel to login panel
            public void actionPerformed(ActionEvent e) {
                homeGUI.setPanel("login"); 
            }
        });

        // Exit button
        JButton exit = new JButton("Exit");
        c.ipadx = 280;
        c.gridy = 3;
        c.weighty = 0;
        add(exit, c);
        
        // Action if exit
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeGUI.exit(); 
            }
        });
        
        setVisible(true);
    }

    @Override
    public void refresh() {
        // ignored
    }
}
