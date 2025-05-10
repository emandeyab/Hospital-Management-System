package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    JTextField textField;
    JPasswordField passwordField;
    JButton b1, b2;

    Login() {
        setLayout(null);

        //Image icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon/login.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(450, 0, 300, 250);
        add(label);

        //label for username
        JLabel namelabel = new JLabel("User Name");
        namelabel.setBounds(100, 80, 200, 30);
        namelabel.setForeground(Color.BLACK);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(namelabel);

        //label for password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 155, 200, 30);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(passwordLabel);

        //text field for username
        textField = new JTextField();
        textField.setBounds(225, 80, 200, 30);
        textField.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(textField);

        //text field for password
        passwordField = new JPasswordField();
        passwordField.setBounds(225, 155, 200, 30);
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(passwordField);

        //button for login
        b1 = new JButton("Login");
        b1.setBounds(150, 250, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database db = new Database();
                    Connection conn = db.connection;

                    String user = textField.getText();
                    String pass = new String(passwordField.getPassword());

                    String query = "SELECT * FROM login WHERE username = ? AND ps = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, user);
                    pstmt.setString(2, pass);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        new Reception();
                        //JOptionPane.showMessageDialog(null, "Login Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(b1);

        //button for cancel
        b2 = new JButton("Cancel");
        b2.setBounds(300, 250, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(b2);

        //frame settings
        getContentPane().setBackground(new Color(3, 153, 169));
        setSize(750, 350);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
