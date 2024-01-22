package exceptions;

public class BarnOwerflowException extends RuntimeException {
    @Override
    public String toString() {
        return "The barn is full!!!";
    }
}