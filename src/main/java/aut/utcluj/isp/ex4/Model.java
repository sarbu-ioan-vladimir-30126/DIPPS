package aut.utcluj.isp.ex4;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final AirplaneTicketController ticketController = new AirplaneTicketController();
    private final List<String> customerIds = new ArrayList<>();
    private String currentId;

    public void customerIdEntered(String id){
        if(customerIds.stream().noneMatch(id::equals)){
            customerIds.add(id);
        }
        currentId = id;
    }

    public void buyTicket(final String destination) throws NoTicketAvailableException, NoDestinationAvailableException {
        ticketController.buyTicket(destination, currentId);
    }

    public List<AirplaneTicket> getCustomerTicketList(){
        return ticketController.groupTicketsByCustomerId().get(currentId);
    }

    public boolean customerExists(String customerId){
        return customerIds.stream().anyMatch(customerId::equals);
    }

    public void giveTicket(String ticketId, String customerId){
        if(!customerExists(customerId)){
            customerIds.add(customerId);
        }
        ticketController.changeTicketCustomerId(ticketId, customerId);
    }

    public void cancelTicket(String ticketId){
        ticketController.cancelTicket(ticketId);
    }

    public double getTicketPrice(String destination){
        return ticketController.getTicketPrice(destination);
    }
}
