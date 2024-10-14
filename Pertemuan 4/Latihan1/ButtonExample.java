import javax.swing.*;
import java.awt.event.*;

public class ButtonExample {
    public static void main(String[] args) {
        // Membuat frame dengan judul "Button Example"
        JFrame frame = new JFrame("Button Example");

        // Membuat tombol dengan teks "Click Me"
        JButton button = new JButton("Click Me");

        // Menambahkan ActionListener ke JButton
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
            }
        });

        // Menetapkan posisi dan ukuran tombol
        button.setBounds(50, 50, 150, 30);

        // Menambahkan tombol ke frame
        frame.add(button);

        // Menetapkan ukuran frame
        frame.setSize(300, 200);

        // Menonaktifkan layout manager
        frame.setLayout(null);

        // Menampilkan frame
        frame.setVisible(true);

        // Menutup aplikasi saat frame ditutup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}