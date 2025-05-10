package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Discharge extends JFrame {
    Discharge() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 750, 400);
        panel.setOpaque(false);
        add(panel);

        JLabel lab = new JLabel("Check Out");
        lab.setBounds(20, 20, 300, 35);
        lab.setFont(new Font("Tahoma", Font.BOLD, 25));
        lab.setForeground(Color.BLACK);
        panel.add(lab);

        //label1
        JLabel label1 = new JLabel("Customer Idt");
        label1.setBounds(80, 80, 200, 30);
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JComboBox comboBox = new JComboBox();
        try {
            Database db = new Database();
            Statement stmt = db.connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM patient_info");
            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString("ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comboBox.setBounds(250, 80, 200, 30);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(comboBox);

        //label2
        JLabel label2 = new JLabel("Room Number");
        label2.setBounds(80, 130, 200, 30);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        //room
        JLabel room = new JLabel("");
        room.setBounds(250, 130, 200, 30);
        room.setForeground(Color.BLACK);
        room.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(room);

        //label3
        JLabel label3 = new JLabel("Deposite");
        label3.setBounds(80, 180, 200, 30);
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);

        //deposite
        JLabel deposite = new JLabel("");
        deposite.setBounds(250, 180, 200, 30);
        deposite.setForeground(Color.BLACK);
        deposite.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(deposite);

        //label4
        JLabel label4 = new JLabel("Remaining Amount");
        label4.setBounds(80, 230, 200, 30);
        label4.setForeground(Color.BLACK);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label4);


        //outime
        JLabel Remaining = new JLabel("");
        Remaining.setBounds(250, 230, 250, 30);
        Remaining.setForeground(Color.BLACK);
        Remaining.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Remaining);

        //button
        JButton b1 = new JButton("Discharge");
        b1.setBounds(80, 300, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database db = new Database();
                    Connection conn = db.connection;

                    String selectedID = comboBox.getSelectedItem().toString();
                    String roomNumber = room.getText();
                    String deleteQuery = "DELETE FROM Patient_Info WHERE ID = ?";
                    PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                    deleteStmt.setString(1, selectedID);
                    deleteStmt.executeUpdate();

                    String updateRoomQuery = "UPDATE Room SET Availability = 'Available' WHERE room_num = ?";
                    PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomQuery);
                    updateRoomStmt.setString(1, roomNumber);
                    updateRoomStmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "The patient has successfully paid the remaining amount.");
                    comboBox.removeItem(selectedID);
                    room.setText("");
                    deposite.setText("");
                    Remaining.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });

        //button
        JButton b2 = new JButton("Check");
        b2.setBounds(250, 300, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        panel.add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database db = new Database();
                    Connection conn = db.connection;
                    Statement stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery("select * from Patient_Info where id = " + comboBox.getSelectedItem());
                    String roomNumber = "";
                    String depositeStr = "";

                    while (resultSet.next()) {
                        roomNumber = resultSet.getString("Room_Number");
                        depositeStr = resultSet.getString("deposite");
                        room.setText(roomNumber);
                        deposite.setText(depositeStr);
                    }

                    Statement stmt2 = conn.createStatement();
                    ResultSet rs = stmt2.executeQuery("select Price from Room where room_num = '" + roomNumber + "'");
                    if (rs.next()) {
                        String priceStr = rs.getString("Price");
                        double price = Double.parseDouble(priceStr);
                        double depositeVal = Double.parseDouble(depositeStr);
                        double remaining = price - depositeVal;
                        Remaining.setText(String.valueOf(remaining));
                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        //button
        JButton b3 = new JButton("Back");
        b3.setBounds(420, 300, 100, 30);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.white);
        panel.add(b3);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reception();
                setVisible(false);
            }
        });

        //frame settings
        getContentPane().setBackground(new Color(3, 153, 169));
        setSize(750, 400);
        setTitle("Discharge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
