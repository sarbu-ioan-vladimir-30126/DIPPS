package aut.utcluj.isp.ex4;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {

    private final JTextField customerIdTextField = new JTextField();
    private final JLabel customerIdLabel = new JLabel("Enter ID");
    private final JButton customerIdButton = new JButton("Submit");

    //Buy stuff
    private final JComboBox<String> destinationsComboBox = new JComboBox<>(new String[]{"Cluj", "Arad", "Sighisoara"});
    private final JButton buyTicketButton = new JButton("Buy Ticket");
    private final JLabel buyPriceLabel = new JLabel("1000");

    //Table stuff
    private final JTable ticketTable = new JTable();
    private final String[] header = {"ID", "Destination", "Status"};
    private final DefaultTableModel tableModel = new DefaultTableModel(header, 0);

    //Give stuff
    private final JButton giveButton = new JButton("Give");
    private final JTextField giveToIdField = new JTextField();

    //Cancel stuff
    private final JButton cancelTicketButton = new JButton("Cancel Ticket");

    public View(){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(layout);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(3,4,4,3);
        constraints.weightx = 1;

        //Input
        int currentRow = 0;
        constraints.gridx = 0;
        constraints.gridy = currentRow;
        constraints.gridwidth = 2;
        mainPanel.add(customerIdLabel, constraints);
        constraints.gridwidth = 1;

        currentRow++;

        constraints.gridx = 0;
        constraints.gridy = currentRow;
        constraints.gridwidth = 2;
        mainPanel.add(customerIdTextField, constraints);
        constraints.gridwidth = 1;

        constraints.gridx = 2;
        constraints.gridy = currentRow;
        mainPanel.add(customerIdButton, constraints);

        //Buy section
        currentRow++;
        Border buyBorder = BorderFactory.createTitledBorder("Buy new ticket");
        JPanel buyPanel = new JPanel();
        GridBagLayout buyLayout = new GridBagLayout();
        buyPanel.setLayout(buyLayout);

        int buyRow = 0;
        buyPanel.setBorder(buyBorder);
        constraints.gridx = 0;
        constraints.gridy = buyRow;
        constraints.gridwidth = 2;
        buyPanel.add(new JLabel("Pick destination:"), constraints);
        constraints.gridwidth = 1;

        constraints.gridx = 2;
        constraints.gridy = buyRow;
        buyPanel.add(buyPriceLabel, constraints);

        buyRow++;
        constraints.gridx = 2;
        constraints.gridy = buyRow;
        buyPanel.add(buyTicketButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = buyRow;
        constraints.gridwidth = 2;
        buyPanel.add(destinationsComboBox, constraints);
        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = currentRow;
        constraints.gridwidth = 3;
        mainPanel.add(buyPanel, constraints);

        //Table section
        currentRow++;
        constraints.gridx = 0;
        constraints.gridy = currentRow;
        constraints.weighty = 1;
        constraints.gridwidth = 3;
        JScrollPane tableScrollPane = new JScrollPane(ticketTable);
        tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ticketTable.setModel(tableModel);
        ticketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainPanel.add(tableScrollPane, constraints);
        constraints.gridwidth = 1;

        //Give ticket section
        currentRow++;
        int giveRow = 0;
        Border giveBorder = BorderFactory.createTitledBorder("Give your ticket");

        JPanel givePanel = new JPanel();
        givePanel.setBorder(giveBorder);
        GridBagLayout givePanelLayout = new GridBagLayout();
        givePanel.setLayout(givePanelLayout);
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = giveRow;
        constraints.gridwidth = 2;
        givePanel.add(new JLabel("Give selected ticket to:"), constraints);

        constraints.gridx = 2;
        constraints.gridy = giveRow;
        constraints.gridwidth = 1;
        givePanel.add(giveButton, constraints);

        giveRow++;
        constraints.gridx = 0;
        constraints.gridy = giveRow;
        constraints.gridwidth = 2;
        givePanel.add(giveToIdField, constraints);

        constraints.gridx = 2;
        constraints.gridy = giveRow;
        constraints.gridwidth = 1;
        givePanel.add(new JLabel("Found/Not Found"), constraints);

        constraints.gridx = 0;
        constraints.gridy = currentRow;
        constraints.gridwidth = 3;
        mainPanel.add(givePanel, constraints);

        currentRow++;

        constraints.insets = new Insets(3, 3, 10, 3);
        constraints.gridx = 0;
        constraints.gridy = currentRow;
        constraints.gridwidth = 3;
        mainPanel.add(cancelTicketButton, constraints);

        //Frame settings
        setTitle("Ticket manager");
        setDefaultLookAndFeelDecorated(true);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
    }

}
