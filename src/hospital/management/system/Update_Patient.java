package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Update_Patient extends JFrame {
    Update_Patient() {
        // Image icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon/update.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(620, -30, 200, 200);

        // Panel to add components on it
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 567);
        panel.setOpaque(false);
        panel.add(label);

        JLabel lab = new JLabel("Update Patient Details");
        lab.setBounds(20, 20, 300, 35);
        lab.setFont(new Font("Tahoma", Font.BOLD, 25));
        lab.setForeground(Color.BLACK);
        panel.add(lab);

        JLabel label1 = new JLabel("ID");
        label1.setBounds(20, 100, 150, 15);
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setForeground(Color.BLACK);
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
        comboBox.setBounds(170, 100, 200, 30);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(comboBox);

        JLabel label2 = new JLabel("Name");
        label2.setBounds(20, 150, 150, 15);
        label2.setFont(new Font("Tahoma", Font.BOLD, 16));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        JTextField namef = new JTextField();
        namef.setBounds(170, 150, 200, 30);
        namef.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(namef);

        JLabel label3 = new JLabel("Phone Number");
        label3.setBounds(20, 200, 120, 15);
        label3.setFont(new Font("Tahoma", Font.BOLD, 16));
        label3.setForeground(Color.BLACK);
        panel.add(label3);

        JTextField numberf = new JTextField();
        numberf.setBounds(170, 200, 200, 30);
        numberf.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(numberf);

        JLabel label4 = new JLabel("Gender");
        label4.setBounds(20, 250, 120, 15);
        label4.setFont(new Font("Tahoma", Font.BOLD, 16));
        label4.setForeground(Color.BLACK);
        panel.add(label4);

        JRadioButton r1 = new JRadioButton("Male");
        r1.setBounds(170, 250, 100, 30);
        r1.setFont(new Font("Tahoma", Font.BOLD, 16));
        r1.setForeground(Color.BLACK);
        panel.add(r1);

        JRadioButton r2 = new JRadioButton("Female");
        r2.setBounds(270, 250, 100, 30);
        r2.setFont(new Font("Tahoma", Font.BOLD, 16));
        r2.setForeground(Color.BLACK);
        panel.add(r2);

        JLabel label5 = new JLabel("Room Number");
        label5.setBounds(20, 300, 120, 15);
        label5.setFont(new Font("Tahoma", Font.BOLD, 16));
        label5.setForeground(Color.BLACK);
        panel.add(label5);

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
        roomf.setBounds(170, 300, 200, 30);
        roomf.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(roomf);


        JLabel label6 = new JLabel("Deposite");
        label6.setBounds(20, 350, 120, 15);
        label6.setFont(new Font("Tahoma", Font.BOLD, 16));
        label6.setForeground(Color.BLACK);
        panel.add(label6);

        JTextField depositef = new JTextField();
        depositef.setBounds(170, 350, 200, 30);
        depositef.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(depositef);

        //Update Button
        JButton b1 = new JButton("Update");
        b1.setBounds(50, 450, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = comboBox.getSelectedItem().toString();
                String name = namef.getText();
                String phone = numberf.getText();
                String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : "";
                String roomNum = roomf.getSelectedItem().toString();
                String deposite = depositef.getText();

                if (name.isEmpty() || phone.isEmpty() || gender.isEmpty() || deposite.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                try {
                    Database db = new Database();
                    Statement stmt = db.connection.createStatement();

                    // Check if room is already assigned to another patient
                    String checkRoomQuery = "SELECT * FROM patient_info WHERE room_number = '" + roomNum + "' AND ID != '" + selectedId + "'";
                    ResultSet rs = stmt.executeQuery(checkRoomQuery);

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "This room is already assigned to another patient. Please choose a different room.");
                        return;
                    }

                    // Update patient info
                    String updateQuery = "UPDATE patient_info SET name = '" + name + "', phone_number = '" + phone + "', gender = '" + gender + "', room_number = '" + roomNum + "', deposite = '" + deposite + "' WHERE ID = '" + selectedId + "'";
                    stmt.executeUpdate(updateQuery);

                    JOptionPane.showMessageDialog(null, "Patient information updated successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while updating patient information.");
                }
            }
        });

        //button for Check
        JButton b2 = new JButton("Check");
        b2.setBounds(200, 450, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        panel.add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = comboBox.getSelectedItem().toString();
                try {
                    Database db = new Database();
                    Statement stmt = db.connection.createStatement();
                    String query = "SELECT * FROM patient_info WHERE ID = '" + selectedId + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        namef.setText(rs.getString("name"));
                        numberf.setText(rs.getString("Phone_Number"));
                        String gender = rs.getString("gender");
                        if (gender.equalsIgnoreCase("Male")) {
                            r1.setSelected(true);
                            r2.setSelected(false);
                        } else if (gender.equalsIgnoreCase("Female")) {
                            r2.setSelected(true);
                            r1.setSelected(false);
                        }
                        roomf.setSelectedItem(rs.getString("Room_Number"));
                        depositef.setText(rs.getString("deposite"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No patient found with this ID.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred while fetching data.");
                }
            }
        });

        //Delete
        JButton b3 = new JButton("Delete");
        b3.setBounds(350, 450, 100, 30);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        panel.add(b3);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = comboBox.getSelectedItem().toString();

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this patient?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Database db = new Database();
                        Statement stmt = db.connection.createStatement();

                        String deleteQuery = "DELETE FROM patient_info WHERE ID = '" + selectedId + "'";
                        int rows = stmt.executeUpdate(deleteQuery);

                        if (rows > 0) {
                            JOptionPane.showMessageDialog(null, "Patient deleted successfully.");

                            // Remove deleted ID from ComboBox
                            comboBox.removeItem(selectedId);
                            // Clear fields
                            namef.setText("");
                            numberf.setText("");
                            r1.setSelected(false);
                            r2.setSelected(false);
                            depositef.setText("");

                        } else {
                            JOptionPane.showMessageDialog(null, "Patient not found or could not be deleted.");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error while deleting patient.");
                    }
                }
            }
        });


        //Back
        JButton b_back = new JButton("Back");
        b_back.setBounds(500, 450, 100, 30);
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
        setTitle("Update Patient Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        add(panel);
    }
}
