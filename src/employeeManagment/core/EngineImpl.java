package employeeManagment.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

public class EngineImpl implements Engine {

  private Controller controller;
  private BufferedReader reader;

  public EngineImpl(Controller controller) {
    this.controller = controller;
    this.reader = new BufferedReader(new InputStreamReader(System.in));
  }

  // Implementation of run() command
  @Override
  public void run() {
    while (true) {
      String result = null;
      try {
        result = processInput();

        if (result.equals("Exit")) {
          break;
        }
      } catch (NullPointerException
          | IllegalArgumentException
          | IllegalStateException
          | IOException e) {
        result = e.getMessage();
      }
      System.out.println(result);
    }
  }

  // Reading, Validating and Processing input
  private String processInput() throws IOException {
    Pair<String, List<String>> pair = parseInput(reader);
    return dispatchCommand(pair.getLeft(), pair.getRight());
  }

  // CREATE
  private String create(String id, String name, int age, double salary) {
    return controller.create(id, name, age, salary);
  }

  // GET EMPLOYEE
  private String get(String id) {
    return controller.get(id);
  }

  // DELETE
  private String delete(String id) {
    return controller.delete(id);
  }

  // UPDATE
  private String update(String id, String name, int age, double salary) {
    return controller.update(id, name, age, salary);
  }

  // GET EMPLOYEES
  private String getEmployees() {
    return controller.getEmployees();
  }

  // LOAD to csv
  private String load(String fileName) throws IOException {
    return controller.load(fileName);
  }

  // SAVE to csv
  private String save(String fileName) throws IOException {
    return controller.save(fileName);
  }

  private Pair<String, List<String>> parseInput(BufferedReader reader) throws IOException {
    String input = reader.readLine();
    List<String> arguments = Arrays.stream(input.split("\\s+")).collect(Collectors.toList());
    String command = arguments.get(0);
    arguments.remove(0);
    return Pair.of(command, arguments);
  }

  // Validating arguments count
  private boolean validateArgsLength(int limit, List<String> arguments) {
    if (arguments.size() != limit) {
      throw new IllegalArgumentException("Invalid number of arguments!");
    }
    return true;
  }

  // Processing command with arguments
  private String dispatchCommand(String command, List<String> arguments) throws IOException {
    switch (command) {
      case "create":
        return executeCommandCreate(arguments);
      case "get":
        return executeCommandGet(arguments);
      case "delete":
        return executeCommandDelete(arguments);
      case "update":
        return executeCommandUpdate(arguments);
      case "load":
        return executeCommandLoad(arguments);
      case "save":
        return executeCommandSave(arguments);
      case "Exit":
        return executeCommandExit();
      default:
        throw new IllegalArgumentException("Invalid command!");
    }
  }

  private String executeCommandCreate(List<String> arguments) {
    String result = null;
    if (validateArgsLength(5, arguments)) {
      String id = arguments.get(1);
      String name = arguments.get(2);
      int age = Integer.parseInt(arguments.get(3));
      double salary = Double.parseDouble(arguments.get(4));
      result = create(id, name, age, salary);
    }
    return result;
  }

  private String executeCommandGet(List<String> arguments) {
    String result = null;
    if (arguments.get(0).equals("employee")) {
      if (validateArgsLength(2, arguments)) {
        String idToAccess = arguments.get(1);
        result = get(idToAccess);
      }
    } else if (arguments.get(0).equals("employees")) {
      if (validateArgsLength(1, arguments)) {
        result = getEmployees();
      }
    }
    return result;
  }

  private String executeCommandDelete(List<String> arguments) {
    String result = null;
    if (validateArgsLength(2, arguments)) {
      String idToDelete = arguments.get(1);
      result = delete(idToDelete);
    }
    return result;
  }

  private String executeCommandUpdate(List<String> arguments) {
    String result = null;
    if (validateArgsLength(5, arguments)) {
      String idToUpdate = arguments.get(1);
      String nameToUpdate = arguments.get(2);
      int ageToUpdate = Integer.parseInt(arguments.get(3));
      double salaryToUpdate = Double.parseDouble(arguments.get(4));
      result = update(idToUpdate, nameToUpdate, ageToUpdate, salaryToUpdate);
    }
    return result;
  }

  private String executeCommandLoad(List<String> arguments) throws IOException {
    String result = null;
    if (validateArgsLength(2, arguments)) {
      String loadingFileName = arguments.get(1);
      result = load(loadingFileName);
    }
    return result;
  }

  private String executeCommandSave(List<String> arguments) throws IOException {
    String result = null;
    if (validateArgsLength(2, arguments)) {
      String savingFileName = arguments.get(1);
      result = save(savingFileName);
    }
    return result;
  }

  private String executeCommandExit() {
    return "Exit";
  }
}
