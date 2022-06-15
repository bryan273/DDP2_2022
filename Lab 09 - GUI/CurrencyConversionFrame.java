import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

// Class to convert currency
public class CurrencyConversionFrame extends JFrame {
    // Preparation on components
    JLabel labelTitle, labelFrom, labelTo;
    JButton convertButton, exitButton;
    String[] currency = {"Rupiah", "Euro", "US Dollar"};
    HashMap<String, Integer> kurs = new HashMap<String, Integer>();

    // Constructor to make window
    public CurrencyConversionFrame() {
        // Hashmap for currency value
        kurs.put("Rupiah", 1);
        kurs.put("Euro", 15000);
        kurs.put("US Dollar", 14000);

        // Arrange frame setting
        setSize(450, 300);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle("Currency Converter");

        // Make center frame title
        labelTitle = new JLabel();
        labelTitle.setText("Welcome to Currency Converter");
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        // Make first panel covering dropdown and text
        JPanel panel_1 = new JPanel(new GridLayout(2,1));
        JPanel panel_11 = new JPanel();
        JPanel panel_12 = new JPanel();

        // "From" part
        // Make subpanels for label, combobox and textfield
        labelFrom = new JLabel("From");
        JComboBox<String> dropDownFrom = new JComboBox<>(currency); 
        JTextField textFrom = new JTextField(18);
        panel_11.add(labelFrom);
        panel_11.add(dropDownFrom);
        panel_11.add(textFrom);

        // "To" part
        // Make subpanels for label, combobox and textfield
        labelTo = new JLabel("To    ");
        JComboBox<String> dropDownTo = new JComboBox<>(currency); 
        JTextField textTo = new JTextField(18);
        textTo.setEditable(false);
        panel_12.add(labelTo);
        panel_12.add(dropDownTo);
        panel_12.add(textTo);

        // Add to first panel
        panel_1.add(panel_11);
        panel_1.add(panel_12);

        // Make second panels covering button
        JPanel panel_2 = new JPanel(); 
        JPanel panel_21 =new JPanel();
        // Set empty space 
        panel_21.setBorder(new EmptyBorder(8,700,8,700));

        // Make convert and exit button, then add to second panel
        convertButton = new JButton("Convert");
        exitButton = new JButton("Exit");
        panel_2.add(panel_21);
        panel_2.add(convertButton);
        panel_2.add(exitButton);

        // Add to main frame
        add(labelTitle);
        add(panel_1);
        add(panel_2);
        setVisible(true);

        // Converter condition if clicked
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Get selected item and text , then convert it
                String tipeCurrencyFrom = dropDownFrom.getSelectedItem().toString();
                String tipeCurrencyTo = dropDownTo.getSelectedItem().toString();
                String originalValue = textFrom.getText();
                String result = ConvertCurrency(originalValue, tipeCurrencyFrom, tipeCurrencyTo);

                // Shows result to text
                textTo.setText(result);
            }
        });

        // Exit condition if clicked
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Terminate program
                dispose();
            }
        });
    }
    
    // Method currency converter
    public String ConvertCurrency(String originalValue, String tipeCurrencyFrom, String tipeCurrencyTo) {
        double value = Double.valueOf(originalValue);
        double fromValue = value * kurs.get(tipeCurrencyFrom);
        double convertedValue = fromValue / kurs.get(tipeCurrencyTo);
        return String.format("%.2f", convertedValue);
    }
    
}
