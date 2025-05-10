package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame {
    Reception() {
        setLayout(null);

        //Image icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon/c.jpg"));
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

        JLabel lab = new JLabel("Welcome to the Hospital Reception");
        lab.setBounds(20, 20, 400, 30);
        lab.setFont(new Font("Tahoma", Font.BOLD, 22));
        lab.setForeground(Color.BLACK);
        panel.add(lab);


        //button for add patient
        JButton b1 = new JButton("Add new patient");
        b1.setBounds(20, 100, 160, 30);
        b1.setBackground(new Color(3, 153, 169));
        b1.setForeground(Color.BLACK);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Patient();
                setVisible(false);
            }
        });

        //button for add Room
        JButton b2 = new JButton("Show Rooms");
        b2.setBounds(20, 180, 160, 30);
        b2.setBackground(new Color(3, 153, 169));
        b2.setForeground(Color.BLACK);
        panel.add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Room();
                setVisible(false);
            }
        });

        //button for add Department
        JButton b3 = new JButton("Show Departments");
        b3.setBounds(20, 260, 160, 30);
        b3.setBackground(new Color(3, 153, 169));
        b3.setForeground(Color.BLACK);
        panel.add(b3);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Department();
                setVisible(false);
            }
        });

        //button for add Show Employee Info
        JButton b4 = new JButton("Show Employee Info");
        b4.setBounds(200, 100, 160, 30);
        b4.setBackground(new Color(3, 153, 169));
        b4.setForeground(Color.BLACK);
        panel.add(b4);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Employees();
                setVisible(false);
            }
        });

        //button for add Show Patient Info
        JButton b5 = new JButton("Show Patient Info");
        b5.setBounds(200, 180, 160, 30);
        b5.setBackground(new Color(3, 153, 169));
        b5.setForeground(Color.BLACK);
        panel.add(b5);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Patient_info();
                setVisible(false);
            }
        });

        //button for add Patient Discharge
        JButton b6 = new JButton("Patient Discharge");
        b6.setBounds(200, 260, 160, 30);
        b6.setBackground(new Color(3, 153, 169));
        b6.setForeground(Color.BLACK);
        panel.add(b6);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Discharge();
                setVisible(false);
            }
        });

        //button for Update Patient Details
        JButton b7 = new JButton("Update Patient Details");
        b7.setBounds(380, 180, 160, 30);
        b7.setBackground(new Color(3, 153, 169));
        b7.setForeground(Color.BLACK);
        panel.add(b7);
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Update_Patient();
                setVisible(false);
            }
        });


        //button for add Logout
        JButton b10 = new JButton("Logout");
        b10.setBounds(20, 400, 160, 30);
        b10.setBackground(new Color(136, 142, 144));
        b10.setForeground(Color.BLACK);
        panel.add(b10);
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });

        //frame settings
        getContentPane().setBackground(new Color(3, 153, 169));
        setSize(950, 617);
        setTitle("Reception");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
