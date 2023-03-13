import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

public class Fines extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField t1;
    private JButton b1, b2, b3;

    public static void main(String[] args) {

        new Fines().setVisible(true);
    }

    public Fines() {
        super("Fines");
        setBounds(450, 300, 617, 363);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("Item id:");
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(100, 50, 102, 22);
        contentPane.add(l1);

        t1 = new JTextField();
        t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t1.setBounds(100, 85, 130, 30);
        contentPane.add(t1);
        t1.setColumns(10);

        b1 = new JButton("Pay");
        b1.addActionListener(this);
        b1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b1.setBounds(130, 240, 100, 30);
        contentPane.add(b1);

        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b2.setBounds(270, 240, 100, 30);
        contentPane.add(b2);

        b3 = new JButton("Check");
        b3.addActionListener(this);
        b3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b3.setBounds(290, 85, 100, 30);
        contentPane.add(b3);
    }

    public void actionPerformed(ActionEvent ae) {

        try {
            conn con = new conn();
            if (ae.getSource() == b3) {

                String sql = "select due from checkout where item_id = ?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                ResultSet rs = st.executeQuery();

                Date d1 = null;

                while (rs.next()) {
                    d1 = rs.getDate(1);
                }
                st.close();
                rs.close();

                String sql1 = "select rdate from returnitem where item_id = ?";
                PreparedStatement st1 = con.c.prepareStatement(sql1);
                st1.setString(1, t1.getText());
                ResultSet rs1 = st1.executeQuery();

                Date d2 = null;

                while (rs1.next()) {
                    d2 = rs1.getDate(1);
                }

                //subtract the dates and store in diff
                long diff = d2.getTime() - d1.getTime();
                int days=(int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                double fine = (days) * 0.10;
                System.out.print(days + "\n");
                System.out.print(fine);

                st1.close();
                rs.close();

                String sql2 = "select price from item where item_id = ?";
                PreparedStatement st2 = con.c.prepareStatement(sql2);
                st2.setString(1, t1.getText());
                ResultSet rs2 = st2.executeQuery();
                rs2.next();
                int p = rs2.getInt(1);

                if(fine <= p){

                    String sql7 = "update checkout set fine = '" + fine + "' where item_id = ?";
                    PreparedStatement st7 = con.c.prepareStatement(sql7);
                    st7.setString(1, t1.getText());
                    st7.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Overdue Fine: $ " + fine);

                }else
                    JOptionPane.showMessageDialog(null,"Fine exceeds price of overdue item! \n Overdue Fine: $ " + p);

            }

            if (ae.getSource() == b1) {
                    JOptionPane.showMessageDialog(null, "Payment Successful!..");
            }

            if (ae.getSource() == b2) {
                new UserPanel().setVisible(true);
                this.setVisible(false);
            }

        } catch (Exception e) {

        }
    }
}