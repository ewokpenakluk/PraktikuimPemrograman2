package controller;

import model.Employee;
import model.EmployeeMapper;
import model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import view.EmployeeView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class EmployeeController {

    private final EmployeeView view;
    private final SqlSession sqlSession;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeView view) {
        this.view = view;

        // Initialize the session and mapper
        this.sqlSession = MyBatisUtil.getSqlSession();
        this.employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        // Load initial data
        loadEmployeeTable();

        // Attach listeners to view
        view.addAddEmployeeListener(new AddEmployeeListener());
        view.addUpdateEmployeeListener(new UpdateEmployeeListener());
        view.addDeleteEmployeeListener(new DeleteEmployeeListener());
        view.addRefreshButtonListener(new RefreshButtonListener());
        view.addTableSelectionListener(new TableSelectionListener());
    }

    private void loadEmployeeTable() {
        try {
            List<Employee> employees = employeeMapper.getAllEmployees();
            view.updateEmployeeTable(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class AddEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getNameField().getText();
                String position = view.getPositionField().getText();
                String department = view.getDepartmentField().getText();
                int age = Integer.parseInt(view.getAgeField().getText());
                BigDecimal salary = new BigDecimal(view.getSalaryField().getText());

                Employee employee = new Employee(name, position, department, age, salary);
                employeeMapper.addEmployee(employee);
                sqlSession.commit(); // Commit changes to database
                loadEmployeeTable();
                view.clearInputFields();
            } catch (Exception ex) {
                sqlSession.rollback(); // Rollback in case of error
                ex.printStackTrace();
            }
        }
    }

    class UpdateEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) view.getTable().getValueAt(selectedRow, 0);
                    String name = view.getNameField().getText();
                    String position = view.getPositionField().getText();
                    String department = view.getDepartmentField().getText();
                    int age = Integer.parseInt(view.getAgeField().getText());
                    BigDecimal salary = new BigDecimal(view.getSalaryField().getText());

                    Employee employee = new Employee(name, position, department, age, salary);
                    employee.setId(id);
                    employeeMapper.updateEmployee(employee);
                    sqlSession.commit(); // Commit changes to database
                    loadEmployeeTable();
                    view.clearInputFields();
                }
            } catch (Exception ex) {
                sqlSession.rollback(); // Rollback in case of error
                ex.printStackTrace();
            }
        }
    }

    class DeleteEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) view.getTable().getValueAt(selectedRow, 0);
                    employeeMapper.deleteEmployee(id);
                    sqlSession.commit(); // Commit changes to database
                    loadEmployeeTable();
                    view.clearInputFields();
                }
            } catch (Exception ex) {
                sqlSession.rollback(); // Rollback in case of error
                ex.printStackTrace();
            }
        }
    }

    class RefreshButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadEmployeeTable();
            view.clearInputFields();
        }
    }

    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String name = view.getTable().getValueAt(selectedRow, 1).toString();
                    String position = view.getTable().getValueAt(selectedRow, 2).toString();
                    String department = view.getTable().getValueAt(selectedRow, 3).toString();
                    int age = Integer.parseInt(view.getTable().getValueAt(selectedRow, 4).toString());
                    BigDecimal salary = new BigDecimal(view.getTable().getValueAt(selectedRow, 5).toString());

                    view.getNameField().setText(name);
                    view.getPositionField().setText(position);
                    view.getDepartmentField().setText(department);
                    view.getAgeField().setText(String.valueOf(age));
                    view.getSalaryField().setText(String.valueOf(salary));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Ensure proper cleanup
    public void closeSession() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
