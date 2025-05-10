package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Department extends JFrame {
    Department() {
        // Image icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon/dep.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600, 0, 200, 200);

        // Panel to add components on it
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 567);
        panel.setOpaque(false);
        panel.add(label);

        JLabel lab = new JLabel("Department Information");
        lab.setBounds(20, 20, 320, 35);
        lab.setForeground(Color.BLACK);
        lab.setFont(new Font("Tahoma", Font.BOLD, 25));
        panel.add(lab);

        // Table
        JTable table = new JTable();
        table.setBounds(20, 200, 500, 200);
        table.setBackground(new Color(173, 216, 230));
        table.setForeground(Color.BLACK);
        panel.add(table);

        try {
            Database db = new Database();
            Connection conn = db.connection;
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM department";
            ResultSet resultSet = stmt.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Department ID");
        label1.setBounds(20, 170, 150, 15);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        JLabel label2 = new JLabel("Department Name");
        label2.setBounds(185, 170, 150, 15);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        JLabel label3 = new JLabel("Phone Number");
        label3.setBounds(355, 170, 120, 15);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.BLACK);
        panel.add(label3);

        JButton b_back = new JButton("Back");
        b_back.setBounds(200, 400, 160, 30);
        b_back.setBackground(Color.BLACK);
        b_back.setForeground(Color.WHITE);
        panel.add(b_back);
        b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reception();
                setVisible(false);
            }
        });

        // Frame settings
        getContentPane().setBackground(new Color(173, 216, 230));
        setSize(800, 567);
        setTitle("Department");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        add(panel);
    }
}
