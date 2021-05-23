package aut.utcluj.isp.ex4;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author stefan
 */
public class AirplaneTicketController {
    /**
     * Default number of tickets when a new instance is created
     */
    public static final int DEFAULT_NUMBER_OF_TICKETS = 10;
    private final List<AirplaneTicket> tickets = new ArrayList<>();

    /**
     * Generate default tickets
     */
    public AirplaneTicketController(){
        generateTickets();
    }
    
    private void generateTickets() {
        for (int i = 0; i < DEFAULT_NUMBER_OF_TICKETS; i++) {
            String destination;
            double price;

            if (i < 3) {
                destination = "Cluj-Napoca";
                price = 10d;
            } else if (i < 6) {
                destination = "Baia Mare";
                price = 20d;
            } else {
                destination = "Timisoara";
                price = 15d;
            }

            final AirplaneTicket airplaneTicket = new AirplaneTicket("ID-" + i, price, destination);
            airplaneTicket.setStatus(TicketStatus.NEW);

            tickets.add(airplaneTicket);
        }
    }

    public List<AirplaneTicket> getTickets() {
        return tickets;
    }

    /**
     * Get ticket details by ticket id
     *
     * @param ticketId - unique ticket id
     * @return the ticket with the specified id
     * @apiNote: this method should throw {@link NoTicketAvailableException} exception if ticket not found
     */
    public AirplaneTicket getTicketDetails(final String ticketId) {
        for(AirplaneTicket at: tickets){
            if(ticketId.equals(at.getId())){
                return at;
            }
        }
        throw new NoTicketAvailableException("Ticket not found!!");
    }

    public double getTicketPrice(String destination){
        if("Cluj-Napoca".equals(destination)){
            return 10d;
        }
        else if("Baia Mare".equals(destination)){
            return 20d;
        }
        else if("Timisoara".equals(destination)){
            return 15d;
        }
        return 0d;
    }

    /**
     * Buy ticket with specific destination
     * Ticket information should be updated: customer name and status {@link TicketStatus#ACTIVE}
     *
     * @param destination - destination
     * @param customerId  - customer name
     * @apiNote: this method should throw the following exceptions:
     * {@link NoDestinationAvailableException} - if destination not supported by AirplaneTicketController
     * {@link NoTicketAvailableException} - if destination exists but no ticket with NEW status available
     */
    public void buyTicket(final String destination, final String customerId) {
        boolean flagDestination = true;
        int flagStatus = 1;
        for(AirplaneTicket at: tickets){
            if(destination.equals(at.getDestination())){
                flagDestination = false;
                flagStatus = 2;
                if(at.getStatus().equals(TicketStatus.NEW)){
                    flagStatus = 3;
                    at.setStatus(TicketStatus.ACTIVE);
                    at.setCustomerId(customerId);
                    break;
                }
            }
        }
        if(flagDestination){
            throw new NoDestinationAvailableException("This destination doesn't exist!");
        }
        if(flagStatus == 2){
            throw new NoTicketAvailableException("This ticket is not available!Sorry, sold out");
        }
    }

    /**
     * Cancel specific ticket
     * Status of the ticket should be set to {@link TicketStatus#CANCELED}
     *
     * @param ticketId - unique ticket id
     * @apiNote: this method should throw the following exceptions:
     * {@link NoTicketAvailableException} - if ticket with this id does not exist
     * {@link TicketNotAssignedException} - if ticket is not assigned to any user
     */
    public void cancelTicket(final String ticketId) {
        boolean flagId = true;
        int flagStatus = 1;
        for(AirplaneTicket airplaneTicket: tickets){
            if(ticketId.equals(airplaneTicket.getId())){
                flagId=false;
                flagStatus=2;
                if(airplaneTicket.getStatus().equals(TicketStatus.ACTIVE)){
                    flagStatus = 3;
                    airplaneTicket.setStatus(TicketStatus.CANCELED);
                    break;
                }
            }
        }
        if(flagId){
            throw new NoTicketAvailableException("This ticket doesn't exist!");
        }
        if(flagStatus == 2){
            throw new TicketNotAssignedException("This ticket is not assigned!");
        }
    }

    /**
     * Change ticket customer name by specific ticket id
     *
     * @param ticketId   - unique ticket id
     * @param customerId - unique customer name
     * @apiNote: this method should throw the following exceptions:
     * {@link NoTicketAvailableException} - if ticket with this id does not exist
     * {@link TicketNotAssignedException} - if ticket is not assigned to any user
     */
    public void changeTicketCustomerId(final String ticketId, final String customerId) {
        boolean flagId = true;
        int flagStatus = 1;
        for(AirplaneTicket airplanTicket: tickets){
            if(ticketId.equals(airplanTicket.getId())){
                flagId=false;
                flagStatus=2;
                if(airplanTicket.getStatus().equals(TicketStatus.ACTIVE)){
                    flagStatus = 3;
                    airplanTicket.setCustomerId(customerId);
                    break;
                }
            }
        }
        if(flagId){
            throw new NoTicketAvailableException("This ticket doesn't exist!");
        }
        if(flagStatus == 2){
            throw new TicketNotAssignedException("This ticket is not assigned!");
        }
    }

    /**
     * This method should filter all tickets by provided status.
     * An empty list should be returned if no tickets available with desired status
     *
     * @param status - ticket status
     * @return a list of tickets with the specified status
     */
    public List<AirplaneTicket> filterTicketsByStatus(final TicketStatus status) {
        return tickets.stream().filter(p -> p.getStatus().equals(status)).collect(Collectors.toList());
    }

    /**
     * Return the tickets grouped by customer id
     *
     * @return dictionary where the key is the customer name and the value is a list of tickets for that customer
     * @apiNote: only tickets with available name should be returned
     */
    public Map<String, List<AirplaneTicket>> groupTicketsByCustomerId() {
        return tickets.stream()
                .filter(t -> t.getCustomerId() != null)
                .collect(Collectors.groupingBy(AirplaneTicket::getCustomerId));
    }
}
