import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private DefaultTableModel model;
    private JTable table;

    public MainFrame() {
        setTitle("Aplikasi Pengelolaan Data Karyawan");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem addEmployeeMenu = new JMenuItem("Tambah Karyawan");
        addEmployeeMenu.addActionListener(e -> showAddEmployeeForm());
        menu.add(addEmployeeMenu);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Nama");
        model.addColumn("Umur");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Departemen");
        model.addColumn("Keterampilan");
        model.addColumn("Gaji");

        table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private void showAddEmployeeForm() {
        JDialog dialog = new JDialog(this, "Tambah Data Karyawan", true);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(10, 2, 10, 10));

        // Input Fields
        JTextField nameField = new JTextField();
        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(18, 18, 65, 1));
        JRadioButton maleRadio = new JRadioButton("Laki-laki");
        JRadioButton femaleRadio = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        JComboBox<String> departmentBox = new JComboBox<>(new String[]{"IT", "HR", "Finance", "Marketing"});
        JTextArea skillsArea = new JTextArea(3, 20);
        JCheckBox javaCheck = new JCheckBox("Java");
        JCheckBox pythonCheck = new JCheckBox("Python");
        JCheckBox sqlCheck = new JCheckBox("SQL");

        JSlider salarySlider = new JSlider(3000000, 20000000, 5000000);
        salarySlider.setMajorTickSpacing(5000000);
        salarySlider.setPaintTicks(true);
        salarySlider.setPaintLabels(true);

        JButton saveButton = new JButton("Simpan");

        // Add components to dialog
        dialog.add(new JLabel("Nama:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Umur:"));
        dialog.add(ageSpinner);
        dialog.add(new JLabel("Jenis Kelamin:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        dialog.add(genderPanel);
        dialog.add(new JLabel("Departemen:"));
        dialog.add(departmentBox);
        dialog.add(new JLabel("Keterampilan:"));
        dialog.add(skillsArea);
        dialog.add(javaCheck);
        dialog.add(pythonCheck);
        dialog.add(sqlCheck);
        dialog.add(new JLabel("Gaji:"));
        dialog.add(salarySlider);
        dialog.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = (int) ageSpinner.getValue();
                String gender = maleRadio.isSelected() ? "Laki-laki" : "Perempuan";
                String department = (String) departmentBox.getSelectedItem();
                String skills = skillsArea.getText();
                int salary = salarySlider.getValue();

                // Add data to table
                model.addRow(new Object[]{name, age, gender, department, skills, salary});
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
