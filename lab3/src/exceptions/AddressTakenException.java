package exceptions;

public class AddressTakenException extends Exception {
    @Override
    public String toString() {
        return "Address already taken!!!";
    }
}
