package aut.utcluj.isp.ex4;

import java.awt.event.*;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model theModel, View theView) {
        this.model = theModel;
        this.view = theView;
        view.addSubmitButtonListener(new SubmitButtonPressedEventHandler());
        view.addBuyButtonListener(new BuyButtonPressedEventHandler());
        view.addGiveButtonListener(new GiveButtonPressedEventHandler());
        view.addGiveToIdFieldListener(new GiveTextFieldEventHandler());
        view.addCancelButtonListener(new CancelTicketButtonPressedEventHandler());
        view.addDestinationBoxListener(new DestinationBoxSelectionEventHandler());
    }

    private class SubmitButtonPressedEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.customerIdEntered(view.getCustomerId());
            view.addTicketsToTable(model.getCustomerTicketList());
        }
    }

    private class BuyButtonPressedEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.buyTicket(view.getDestinationSelection());
            } catch (RuntimeException re){
                view.updatePriceLabel(re.getMessage());
            }
            view.addTicketsToTable(model.getCustomerTicketList());
        }
    }

    private class DestinationBoxSelectionEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.updatePriceLabel(String.valueOf(model.getTicketPrice(view.getDestinationSelection())));
        }
    }

    private class GiveTextFieldEventHandler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {
            if(model.customerExists(view.getGiveToUsedId())){
                view.setCustomerFoundLabel("Ticket will be sent to " + view.getGiveToUsedId());
            }
            else if(view.getGiveToUsedId().isEmpty()) {
                view.setCustomerFoundLabel("Search for user");
            }
            else {
                view.setCustomerFoundLabel("Ticket will be sent to New User");
            }
        }
    }

    private class GiveButtonPressedEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.giveTicket(view.selectTicketFromTable(), view.getGiveToUsedId());
            view.removeSelectedTicketFromTable();
        }
    }

    private class CancelTicketButtonPressedEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            model.cancelTicket(view.selectTicketFromTable());
            view.removeSelectedTicketFromTable();
        }
    }
}
