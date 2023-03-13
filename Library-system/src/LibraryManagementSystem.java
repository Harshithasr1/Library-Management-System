import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LibraryManagementSystem extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1,b2;

    public LibraryManagementSystem() {

        setSize(1000,580);
        setLayout(null);
        setLocation(170,100);

        b1 = new JButton("Admin Login");
        b2 = new JButton("User Login");

        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/first.png"));
        Image i3 = i1.getImage().getScaledInstance(1000, 580,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        l1 = new JLabel(i2);

        b1.setBounds(100,200,200,60);
        l1.setBounds(0, 0, 1000, 580);

        b2.setBounds(100,280,200,60);
        l1.setBounds(0, 0, 1000, 580);

        l1.add(b1);
        add(l1);

        l1.add(b2);
        add(l1);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1) {
            new Login_admin().setVisible(true);
            this.setVisible(false);
        }

        if(ae.getSource() == b2) {
            new Login_user().setVisible(true);
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        LibraryManagementSystem window = new LibraryManagementSystem();
        window.setVisible(true);
    }
}