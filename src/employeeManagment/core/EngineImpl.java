package employeeManagment.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine{

private Controller controller;
private BufferedReader reader;

public EngineImpl (Controller controller){
    this.controller = controller;
    this.reader = new BufferedReader(new InputStreamReader(System.in));
}


    @Override
    public void run() {
    while (true){
        String result = null;
        try {
            result = processInput();

            if (result.equals("Exit")){
                break;
            }
        }catch (NullPointerException | IllegalArgumentException | IOException e){
            result=e.getMessage();
        }
        System.out.println(result);
    }
    }

    private String processInput() throws IOException {
    String input = this.reader.readLine();
    String[] tokens = input.split("\\s+");

    String command = tokens[0];
    String result = null;
    String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

    switch (command){
        case "create":
            if (data.length!=5){
                throw new IllegalArgumentException("Invalid number of arguments!");
            }
            result = create(data);
            break;

        case "get":

            if (data[0].equals("employee")) {
                if (data.length != 2) {
                    throw new IllegalArgumentException("Invalid number of arguments!");
                }
                result = get(data);

            } else if (data[0].equals("employees")) {
                if (data.length != 1) {
                    throw new IllegalArgumentException("Invalid number of arguments!");
                }
                result = getEmployees();
            }
            break;

        case "delete":
            if (data.length!=1){
                throw new IllegalArgumentException("Invalid number of arguments!");
            }
            result= delete(data);
            break;

        case "update":
            if (data.length!=4){
                throw new IllegalArgumentException("Invalid number of arguments!");
            }
            result = update(data);
            break;

        case "load":
            if (data.length!=1){
                throw new IllegalArgumentException("Invalid number of arguments!");
            }
            result = load(data);
            break;

        case "save":
            if (data.length!=1){
                throw new IllegalArgumentException("Invalid number of arguments!");
            }
            result = save(data);
            break;
    }
    return result;
    }

    private String create(String[] data){
        return controller.create(data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]));
    }

    private String get(String[] data){
    return controller.get(data[1]);
    }

    private String delete(String[] data){
    return controller.delete(data[1]);
    }

    private String update(String[] data){
        return controller.update(data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]));
    }

    private String getEmployees(){
    return controller.getEmployees();
    }

    private String load(String[] data){
    return controller.load(data[1]);
    }

    private String save(String[] data){
    return controller.save(data[1]);
    }
}
