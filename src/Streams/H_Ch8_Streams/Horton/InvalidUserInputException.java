package Streams.H_Ch8_Streams.Horton;

public class InvalidUserInputException extends Exception {
  public InvalidUserInputException() {
    super("Invalid user exception");
  }

  public InvalidUserInputException(String message) {
    super(message);
  }
  private static final long serialVersionUID = 90001L;
}
