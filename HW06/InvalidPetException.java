public class InvalidPetException extends RuntimeException {
    // Constructors
    public InvalidPetException(String s) {
        super(s);
    }

    public InvalidPetException() {
        super("Your pet is invalid!");
    }
}
