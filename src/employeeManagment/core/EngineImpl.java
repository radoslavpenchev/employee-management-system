package employeeManagment.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EngineImpl implements Engine {

    private Controller controller;
    private BufferedReader reader;

    public EngineImpl(Controller controller) {
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    //Implementation of run() command
    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals("Exit")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }
            System.out.println(result);
        }
    }

    //Reading, Validating and Processing input
    private String processInput() throws IOException {
        List<String> dataTuple = parseInput(reader);

        String command = dataTuple.get(0);
        dataTuple.remove(0);

        return dispatchCommand(command, dataTuple);

    }

    //CREATE
    private String create(String id, String name, int age, double salary) {
        return controller.create(id, name, age, salary);
    }

    //GET EMPLOYEE
    private String get(String id) {
        return controller.get(id);
    }

    //DELETE
    private String delete(String id) {
        return controller.delete(id);
    }

    //UPDATE
    private String update(String id, String name, int age, double salary) {
        return controller.update(id, name, age, salary);
    }

    //GET EMPLOYEES
    private String getEmployees() {
        return controller.getEmployees();
    }

    //LOAD to csv
    private String load(String fileName) {
        return controller.load(fileName);
    }

    //SAVE to csv
    private String save(String fileName) {
        return controller.save(fileName);
    }

    //Reading input
    private List<String> parseInput(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        String[] tokens = input.split("\\s+");


        return Arrays.stream(tokens).toList();

    }

    //Validating arguments count
    private void validateArgsLength(int limit, List<String> args) {

        if (args.size() != limit){
            throw new IllegalArgumentException("Invalid number of arguments!");

        }
    }


    //Processing command with arguments
    private String dispatchCommand(String command, List<String> data) {

        String result = null;

        switch (command) {
            case "create":
                validateArgsLength(5, data);

                String id = data.get(1);
                String name = data.get(2);
                int age = Integer.parseInt(data.get(3));
                double salary = Double.parseDouble(data.get(4));

                result = create(id, name, age, salary);
                break;

            case "get":

                if (data.get(0).equals("employee")) {
                    validateArgsLength(2, data);

                    String idToAccess = data.get(1);
                    result = get(idToAccess);

                } else if (data.get(0).equals("employees")) {
                    validateArgsLength(1, data);

                    result = getEmployees();
                }
                break;

            case "delete":
                validateArgsLength(2, data);

                String idToDelete = data.get(1);
                
                result = delete(idToDelete);
                break;

            case "update":
                validateArgsLength(5, data);

                String idToUpdate = data.get(1);
                String nameToUpdate = data.get(2);
                int ageToUpdate = Integer.parseInt(data.get(3));
                double salaryToUpdate = Double.parseDouble(data.get(4));

                result = update(idToUpdate, nameToUpdate, ageToUpdate, salaryToUpdate);
                break;

            case "load":
                validateArgsLength(2, data);

                String loadingFileName = data.get(1);

                result = load(loadingFileName);
                break;

            case "save":
                validateArgsLength(2, data);

                String savingFileName = data.get(1);
                result = save(savingFileName);
                break;
        }
        return result;
    }

}
