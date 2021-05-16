package aut.utcluj.isp.ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stefan
 */
public class AirplaneTicketController {
    private final List<AirplaneTicket> tickets;

    public AirplaneTicketController() {
        tickets = new ArrayList<>();
    }

    /**
     * Add new airplane ticket
     *
     * @param airplaneTicket - airplane ticket object
     */
    public void addNewTicket(final AirplaneTicket airplaneTicket) {
        tickets.add(airplaneTicket);
    }

    /**
     * Get all tickets
     *
     */
    public List<AirplaneTicket> getTickets() {
        return tickets;
    }

    /**
     * Return total number of tickets
     */
    public int getTotalNumberOfTickets() {
      return tickets.size();
    }

    /**
     * Remove a specific ticket by ticket id
     *
     * @param ticketId - unique ticket id
     */
    public void removeTicketById(final String ticketId) {
        tickets.stream().filter(e -> e.getId().equals(ticketId))
                        .findFirst()
                        .ifPresent(tickets::remove);
    }

    /**
     * Update destination for a specific ticket
     *
     * @param ticketId    - unique ticket id
     * @param destination - new destination
     */
    public void updateTicketDestination(final String ticketId, final String destination) {
        tickets.stream().filter(e -> e.getId().equals(ticketId))
                        .findFirst()
                        .ifPresent(e -> e.setDestination(destination));
    }

    /**
     * Filter airplane tickets with price between [minPrice, maxPrice]
     *
     * @param minPrice - ticket min prince
     * @param maxPrice - ticket max price
     *
     */
    public List<AirplaneTicket> filterTicketsBetweenMinMaxPrice(final Double minPrice, final Double maxPrice) {
       return tickets.stream().filter(e -> e.getPrice() >= minPrice & e.getPrice() <= maxPrice)
                              .collect(Collectors.toList());
    }

    /**
     * Filter airplane tickets with price between [minPrice, maxPrice] and destination
     *
     * @param minPrice    - ticket min price
     * @param maxPrice    - ticket max price
     * @param destination - destination
     *
     */
    public List<AirplaneTicket> filterTicketsWithPriceAndDestination(final Double minPrice, final Double maxPrice, final String destination) {
        return tickets.stream().filter(e -> e.getPrice() >= minPrice & e.getPrice() <= maxPrice)
                               .filter(e -> e.getDestination().equals(destination))
                               .collect(Collectors.toList());

    }
}
