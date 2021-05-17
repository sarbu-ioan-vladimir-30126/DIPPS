package aut.utcluj.isp.ex2;

/**
 * @author stefan
 */
public class AirplaneTicket extends Ticket {
    private String destination;
    private String customerName;
    private Double price;
    private String id;

    public AirplaneTicket(String id, String customerName, Double price, String destination) {
        super(id,customerName,price);
        this.destination = destination;
        this.customerName = customerName;
        this.price = price;
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }
}
