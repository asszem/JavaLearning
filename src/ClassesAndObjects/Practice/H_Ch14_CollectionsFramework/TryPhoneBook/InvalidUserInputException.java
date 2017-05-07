package ClassesAndObjects.Practice.H_Ch14_CollectionsFramework.TryPhoneBook;

public class InvalidUserInputException extends Exception {
  public InvalidUserInputException() {  }

  public InvalidUserInputException(String message) {
    super(message);
  }

  private static final long serialVersionUID = 9876L;

}
