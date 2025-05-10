package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Patient extends JFrame {
    JTextField idf, namef, numberf, genderf, roomf, depositef;
    JLabel id, name, number, gender, room, deposite;
    JButton b_add, b_back;

    Patient() {
        //Image icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon/s.jpg"));
        Image i1 = imageIcon.getImage().getScaledInstance(1000, 667, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(0, 0, 1000, 667);
        add(label);

        //panel to add components on it
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 667);
        panel.setOpaque(false);
        label.add(panel);

        id = new JLabel("ID");
        id.setBounds(50, 20, 200, 30);
        id.setForeground(Color.BLACK);
        id.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(id);

        idf = new JTextField();
        idf.setBounds(200, 20, 200, 30);
        idf.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(idf);

        name = new JLabel("Name");
        name.setBounds(50, 80, 200, 30);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(name);

        namef = new JTextField();
        namef.setBounds(200, 80, 200, 30);
        namef.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(namef);

        number = new JLabel("Phone Number");
        number.setBounds(50, 140, 200, 30);
        number.setForeground(Color.BLACK);
        number.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(number);

        numberf = new JTextField();
        numberf.setBounds(200, 140, 200, 30);
        numberf.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(numberf);

        gender = new JLabel("Gender");
        gender.setBounds(50, 200, 200, 30);
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(gender);

        JRadioButton r1 = new JRadioButton("Male");
        r1.setBounds(200, 200, 100, 30);
        r1.setFont(new Font("Tahoma", Font.BOLD, 17));
        r1.setForeground(Color.BLACK);
        panel.add(r1);

        JRadioButton r2 = new JRadioButton("Female");
        r2.setBounds(300, 200, 100, 30);
        r2.setFont(new Font("Tahoma", Font.BOLD, 17));
        r2.setForeground(Color.BLACK);
        panel.add(r2);

        room = new JLabel("Room Number");
        room.setBounds(50, 260, 200, 30);
        room.setForeground(Color.BLACK);
        room.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(room);

        JComboBox roomf = new JComboBox();
        try {
            Database db = new Database();
            Statement stmt = db.connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Room");
            while (resultSet.next()) {
                roomf.addItem(resultSet.getString("room_num"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        roomf.setBounds(200, 260, 200, 30);
        roomf.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(roomf);

        deposite = new JLabel("Deposite");
        deposite.setBounds(50, 320, 200, 30);
        deposite.setForeground(Color.BLACK);
        deposite.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(deposite);

        depositef = new JTextField();
        depositef.setBounds(200, 320, 200, 30);
        depositef.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(depositef);

        b_add = new JButton("Add");
        b_add.setBounds(100, 450, 160, 30);
        b_add.setBackground(Color.BLACK);
        b_add.setForeground(Color.WHITE);
        panel.add(b_add);
        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database db = new Database();
                    Connection conn = db.connection;

                    String gender_radioBTN = "";
                    if (r1.isSelected()) {
                        gender_radioBTN = "Male";
                    } else if (r2.isSelected()) {
                        gender_radioBTN = "Female";
                    }

                    String s1 = idf.getText(); // ID
                    String s2 = namef.getText();
                    String s3 = numberf.getText();
                    String s4 = gender_radioBTN;
                    String s5 = roomf.getSelectedItem().toString();
                    String s6 = depositef.getText();

                    // 1. Check if ID already exists
                    String checkQuery = "SELECT * FROM patient_info WHERE ID = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                    checkStmt.setString(1, s1);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "This ID already exists. Please enter a different one.");
                    } else {
                        // Check if the selected room is available
                        String checkRoom = "SELECT Availability FROM Room WHERE room_num = ?";
                        PreparedStatement roomStmt = conn.prepareStatement(checkRoom);
                        roomStmt.setString(1, s5);
                        ResultSet roomRs = roomStmt.executeQuery();
                        if (roomRs.next()) {
                            String availability = roomRs.getString("Availability");
                            if (!availability.equalsIgnoreCase("Available")) {
                                JOptionPane.showMessageDialog(null, "This room is not available. Please select another room.");
                                return; // Stop the insertion process
                            }
                        }
                        // 2. Insert if ID doesn't exist
                        String query = "INSERT INTO patient_info (ID, Name, Phone_Number, Gender, Room_Number, Deposite) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(query);
                        insertStmt.setString(1, s1);
                        insertStmt.setString(2, s2);
                        insertStmt.setString(3, s3);
                        insertStmt.setString(4, s4);
                        insertStmt.setString(5, s5);
                        insertStmt.setString(6, s6);
                        insertStmt.executeUpdate();

                        String query2 = "UPDATE Room SET Availability = 'Unavailable' WHERE room_num = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(query2);
                        updateStmt.setString(1, s5);
                        updateStmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Patient Added Successfully");
                        // setVisible(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        b_back = new JButton("Back");
        b_back.setBounds(300, 450, 160, 30);
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

        //frame settings
        getContentPane().setBackground(new Color(3, 153, 169));
        setSize(900, 567);
        setTitle("Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
