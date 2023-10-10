import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PizzaGUIFrame extends JFrame {
    JPanel centerPnl;
    JPanel eastPnl;
    JPanel crustPnl;
    JPanel sizePnl;
    JPanel toppingPnl;
    JPanel orderPnl;
    JPanel buttonPnl;
    JTextArea orderArea;
    JScrollPane orderScroll;
    ButtonGroup crustBtnGrp;
    JRadioButton crustRadBtn1, crustRadBtn2, crustRadBtn3;
    String[] sizeArray = { "Small", "Medium", "Large", "Super" };
    JComboBox sizeComboBox;
    JCheckBox toppingCheckBox1, toppingCheckBox2, toppingCheckBox3, toppingCheckBox4, toppingCheckBox5, toppingCheckBox6;
    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;
    public PizzaGUIFrame() {
        createCenterPanel();
        createEastPanel();
        createCrustPanel();
        createSizePanel();
        createToppingPanel();
        createOrderPanel();
        createButtonPanel();

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        setTitle("Pizza Order");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createCenterPanel() {
        centerPnl = new JPanel();
        centerPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerPnl.setLayout(new GridLayout(3,1));

        add(centerPnl, BorderLayout.CENTER);
    }

    private void createEastPanel() {
        eastPnl = new JPanel();
        eastPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        eastPnl.setLayout(new BorderLayout());

        add(eastPnl, BorderLayout.EAST);
    }

    private void createCrustPanel() {
        crustPnl = new JPanel();
        crustPnl.setBorder(BorderFactory.createTitledBorder("Crust"));
        crustRadBtn1 = new JRadioButton("Thin");
        crustRadBtn1.setActionCommand("Thin");
        crustRadBtn2 = new JRadioButton("Regular");
        crustRadBtn2.setActionCommand("Regular");
        crustRadBtn3 = new JRadioButton("Deep-dish");
        crustRadBtn3.setActionCommand("Deep-dish");
        crustBtnGrp = new ButtonGroup();

        crustBtnGrp.add(crustRadBtn1);
        crustBtnGrp.add(crustRadBtn2);
        crustBtnGrp.add(crustRadBtn3);
        crustPnl.add(crustRadBtn1);
        crustPnl.add(crustRadBtn2);
        crustPnl.add(crustRadBtn3);
        centerPnl.add(crustPnl);
    }

    private void createSizePanel() {
        sizePnl = new JPanel();
        sizePnl.setBorder(BorderFactory.createTitledBorder("Size"));
        sizeComboBox = new JComboBox(sizeArray);

        sizePnl.add(sizeComboBox);
        centerPnl.add(sizePnl);
    }

    private void createToppingPanel() {
        toppingPnl = new JPanel();
        toppingPnl.setBorder(BorderFactory.createTitledBorder("Topping"));
        toppingCheckBox1 = new JCheckBox("Pepperoni");
        toppingCheckBox2 = new JCheckBox("Sausage");
        toppingCheckBox3 = new JCheckBox("Onion");
        toppingCheckBox4 = new JCheckBox("Mushroom");
        toppingCheckBox5 = new JCheckBox("Pebbles");
        toppingCheckBox6 = new JCheckBox("Pineapple");

        toppingPnl.add(toppingCheckBox1);
        toppingPnl.add(toppingCheckBox2);
        toppingPnl.add(toppingCheckBox3);
        toppingPnl.add(toppingCheckBox4);
        toppingPnl.add(toppingCheckBox5);
        toppingPnl.add(toppingCheckBox6);
        centerPnl.add(toppingPnl);
    }

    private void createOrderPanel() {
        orderPnl = new JPanel();
        orderArea = new JTextArea(40, 34);
        orderArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        orderScroll = new JScrollPane(orderArea);

        orderPnl.add(orderScroll);
        eastPnl.add(orderPnl, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        buttonPnl = new JPanel();
        orderBtn = new JButton("Order");
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");

        orderBtn.addActionListener((ActionEvent ae) -> printOrder());
        clearBtn.addActionListener((ActionEvent ae) -> clearOrder());
        quitBtn.addActionListener((ActionEvent ae) -> quitApp());

        buttonPnl.add(orderBtn);
        buttonPnl.add(clearBtn);
        buttonPnl.add(quitBtn);
        eastPnl.add(buttonPnl, BorderLayout.SOUTH);
    }

    private void printOrder() {
        String chosenCrust = crustBtnGrp.getSelection() != null ? crustBtnGrp.getSelection().getActionCommand() : "";
        String chosenSize = (String)sizeComboBox.getSelectedItem();
        ArrayList<String> chosenToppings = new ArrayList<String>();
        if (toppingCheckBox1.isSelected()) {
            chosenToppings.add(toppingCheckBox1.getActionCommand());
        }
        if (toppingCheckBox2.isSelected()) {
            chosenToppings.add(toppingCheckBox2.getActionCommand());
        }
        if (toppingCheckBox3.isSelected()) {
            chosenToppings.add(toppingCheckBox3.getActionCommand());
        }
        if (toppingCheckBox4.isSelected()) {
            chosenToppings.add(toppingCheckBox4.getActionCommand());
        }
        if (toppingCheckBox5.isSelected()) {
            chosenToppings.add(toppingCheckBox5.getActionCommand());
        }
        if (toppingCheckBox6.isSelected()) {
            chosenToppings.add(toppingCheckBox6.getActionCommand());
        }

        int sizePrice = 8 + 4 * (sizeComboBox.getSelectedIndex());
        int toppingPrice = chosenToppings.size();
        int subTotalPrice = sizePrice + toppingPrice;
        double tax = 0.07 * subTotalPrice;
        double totalPrice = subTotalPrice + tax;

        String format = "%-20s %-10s %n";
        String order = "============================\n" +
                        String.format(format, chosenSize + " " + chosenCrust, "$" + sizePrice);
        for (String topping : chosenToppings) {
            order += String.format(format, topping, "$1");
        }
        order += "\n" + String.format(format, "Sub-total:", "$" + subTotalPrice) +
                String.format(format, "Tax:", "$" + String.format("%.2f", tax)) +
                "----------------------------\n" +
                String.format(format,"Total:", "$" + String.format("%.2f", totalPrice)) +
                "============================";

        orderArea.setText(order);
    }

    private void clearOrder() {
        orderArea.setText("");
    }

    private void quitApp() {
        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
