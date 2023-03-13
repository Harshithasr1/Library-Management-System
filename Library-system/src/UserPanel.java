import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPanel extends JFrame implements ActionListener{

    private final JPanel contentPane;
    private final JButton b1;
    private final JButton b2;
    private final JButton b3;
    private final JButton b4;
    private final JButton b6, b7,b9;

    public static void main(String[] args) {

        new UserPanel().setVisible(true);
    }

    public UserPanel() {

        setBounds(480, 30, 550, 580);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("User View");
        l1.setForeground(new Color(204, 51, 102));
        l1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 30));
        l1.setBounds(185, 10, 701, 80);
        contentPane.add(l1);

        b1 = new JButton("Checkout Items");
        b1.addActionListener(this);
        b1.setBounds(180, 120, 143, 41);
        contentPane.add(b1);

        b2 = new JButton("Fine Payment");
        b2.addActionListener(this);
        b2.setBounds(180, 170, 143, 41);
        contentPane.add(b2);

        b3 = new JButton("Return items");
        b3.addActionListener(this);
        b3.setBounds(180, 220, 143, 41);
        contentPane.add(b3);

        b4 = new JButton("Renew items");
        b4.addActionListener(this);
        b4.setBounds(180, 270, 143, 41);
        contentPane.add(b4);

        b6 = new JButton("Back");
        b6.addActionListener(this);
        b6.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        b6.setBounds(350, 440, 100, 30);
        contentPane.add(b6);

        b7 = new JButton("Request items");
        b7.addActionListener(this);
        b7.setBounds(180, 320, 143, 41);
        contentPane.add(b7);

        b9 = new JButton("My Library Card");
        b9.addActionListener(this);
        b9.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        b9.setBounds(80, 440, 150, 30);
        contentPane.add(b9);

    }

    public void actionPerformed(ActionEvent ae){

        try {
            conn con = new conn();

            if (ae.getSource() == b1) {                  //checkout

                this.setVisible(false);
                new Checkoutitem().setVisible(true);

            }

            if(ae.getSource() == b2){           //Fine
                this.setVisible(false);
                new Fines().setVisible(true);
            }

            if (ae.getSource() == b3) {         //Return
                this.setVisible(false);
                new Returnitem().setVisible(true);

            }

            if (ae.getSource() == b4) {          //Renew
                this.setVisible(false);
                new Renewitem().setVisible(true);
            }

            if(ae.getSource() == b6){           //back
                new Login_user().setVisible(true);
                this.setVisible(false);
            }

            if (ae.getSource() == b7) {         //Request
                this.setVisible(false);
                new Requestitem().setVisible(true);
            }

            if (ae.getSource() == b9) {         //Request
                this.setVisible(false);
                new My_card().setVisible(true);
            }

            con.c.close();
        }catch(Exception e){

        }

    }
}
