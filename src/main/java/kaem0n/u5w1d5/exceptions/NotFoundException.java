package kaem0n.u5w1d5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("ID " + id + " didn't produce any results.");
    }
}
