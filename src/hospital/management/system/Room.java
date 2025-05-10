package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Room extends JFrame {
    Room() {

        // Image icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon/roomm.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(570, 120, 200, 200);

        // Panel to add components on it
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 567);
        panel.setOpaque(false);
        panel.add(label);

        JLabel lab = new JLabel("Room Information");
        lab.setBounds(20, 20, 300, 35);
        lab.setFont(new Font("Tahoma", Font.BOLD, 25));
        lab.setForeground(Color.BLACK);
        panel.add(lab);


        // Table
        JTable table = new JTable();
        table.setBounds(20, 160, 500, 200);
        table.setBackground(new Color(173, 216, 230));
        table.setForeground(Color.BLACK);
        panel.add(table);

        try {
            Database db = new Database();
            Connection conn = db.connection;
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM room";
            ResultSet resultSet = stmt.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Room Num");
        label1.setBounds(20, 130, 80, 15);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        JLabel label2 = new JLabel("Availability");
        label2.setBounds(145, 130, 80, 15);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        JLabel label3 = new JLabel("Price");
        label3.setBounds(270, 130, 80, 15);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.BLACK);
        panel.add(label3);

        JLabel label4 = new JLabel("Room Type");
        label4.setBounds(395, 130, 80, 15);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.BLACK);
        panel.add(label4);

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
        //setUndecorated(true);
        // Frame settings
        getContentPane().setBackground(new Color(173, 216, 230));
        setSize(800, 567);
        setTitle("Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        add(panel);
    }
}
