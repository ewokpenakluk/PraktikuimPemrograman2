package view;

import model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class EmployeeView extends JFrame {

    private JTable table;
    private JTextField nameField, positionField, departmentField, ageField, salaryField;
    private JButton addButton, updateButton, deleteButton, refreshButton;
    private JPanel panel;

    public EmployeeView() {
        setTitle("Employee Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Position", "Department", "Age", "Salary"}
        ));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Position:"));
        positionField = new JTextField();
        formPanel.add(positionField);

        formPanel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        formPanel.add(departmentField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        formPanel.add(salaryField);

        addButton = new JButton("Add Employee");
        updateButton = new JButton("Update Employee");
        deleteButton = new JButton("Delete Employee");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPositionField() {
        return positionField;
    }

    public JTextField getDepartmentField() {
        return departmentField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getSalaryField() {
        return salaryField;
    }

    public JTable getTable() {
        return table;
    }

    public void addAddEmployeeListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateEmployeeListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteEmployeeListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    public void updateEmployeeTable(List<Employee> employees) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Employee employee : employees) {
            model.addRow(new Object[]{
                employee.getId(), employee.getName(), employee.getPosition(),
                employee.getDepartment(), employee.getAge(), employee.getSalary()
            });
        }
    }

    public void clearInputFields() {
        nameField.setText("");
        positionField.setText("");
        departmentField.setText("");
        ageField.setText("");
        salaryField.setText("");
    }
}
