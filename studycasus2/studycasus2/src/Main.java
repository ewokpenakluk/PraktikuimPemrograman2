

import controller.EmployeeController;
import view.EmployeeView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set up the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            EmployeeView view = new EmployeeView();
            new EmployeeController(view);
            view.setVisible(true);
        });
    }
}
