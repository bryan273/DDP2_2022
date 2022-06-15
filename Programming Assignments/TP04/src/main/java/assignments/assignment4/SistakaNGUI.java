package assignments.assignment4;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;

import javax.swing.*;

// Main class untuk call program
public class SistakaNGUI {
    public static void main(String[] args) {
        // Make frame and its characteristic
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SistakaNG");
        SistakaNG.registerStaf();

        // Switch to welcome panel
        HomeGUI home = new HomeGUI(frame);
        home.setPanel("welcome");
        frame.setVisible(true);
    }
}
