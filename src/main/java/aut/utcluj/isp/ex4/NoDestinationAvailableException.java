package aut.utcluj.isp.ex4;

/**
 * @author stefan
 */
public class NoDestinationAvailableException extends RuntimeException {

    public NoDestinationAvailableException(String error) {
        System.out.println(error);
    }
    
}
