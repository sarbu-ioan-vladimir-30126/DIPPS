package aut.utcluj.isp.ex3;

/**
 * @author stefan
 */
public class AirplaneTicket {
    private final String id;
    private final Double price;
    private  String destination;


    public AirplaneTicket(String id, Double price, String destination) {
        this.id = id;
        this.price = price;
        this.destination = destination;
    }

    public String getId() {
       return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
