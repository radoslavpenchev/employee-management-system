package employeeManagment;

import employeeManagment.core.Controller;
import employeeManagment.core.ControllerImpl;
import employeeManagment.core.Engine;
import employeeManagment.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
