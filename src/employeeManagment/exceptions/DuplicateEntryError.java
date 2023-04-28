package employeeManagment.exceptions;

public class DuplicateEntryError extends Exception {
  public DuplicateEntryError(String message) {
    super(message);
  }
}
