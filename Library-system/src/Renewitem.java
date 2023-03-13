import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Renewitem extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField t1,t2,t3,t4,t5;
    private JButton b1,b3,b4;

    public static void main(String[] args) {
        new Renewitem().setVisible(true);

    }

    public Renewitem() {
        super("Renew Item");
        setBounds(300, 200, 420, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("Item id");
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(47, 63, 100, 23);
        contentPane.add(l1);

        JLabel l2 = new JLabel("Category");
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(47, 97, 100, 23);
        contentPane.add(l2);

        JLabel l3 = new JLabel("Item name");
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(47, 131, 100, 23);
        contentPane.add(l3);

        JLabel l7 = new JLabel("Issue date");
        l7.setFont(new Font("Tahoma", Font.BOLD, 14));
        l7.setBounds(47, 165, 100, 23);
        contentPane.add(l7);

        JLabel l8 = new JLabel("Due date");
        l8.setFont(new Font("Tahoma", Font.BOLD, 14));
        l8.setBounds(47, 198, 100, 23);
        contentPane.add(l8);

        t1 = new JTextField();
        t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t1.setBounds(126, 66, 86, 20);
        contentPane.add(t1);

        b1 = new JButton("Search");
        b1.addActionListener(this);
        b1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b1.setBounds(234, 59, 100, 30);
        contentPane.add(b1);

        t2 = new JTextField();
        t2.setEditable(false);
        t2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t2.setBounds(126, 100, 208, 20);
        contentPane.add(t2);
        t2.setColumns(10);

        t3 = new JTextField();
        t3.setEditable(false);
        t3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t3.setColumns(10);
        t3.setBounds(126, 131, 208, 20);
        contentPane.add(t3);

        t4 = new JTextField();
        t4.setEditable(false);
        t4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t4.setColumns(10);
        t4.setBounds(126, 168, 208, 20);
        contentPane.add(t4);

        t5 = new JTextField();
        t5.setEditable(false);
        t5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t5.setColumns(10);
        t5.setBounds(126, 200, 208, 20);
        contentPane.add(t5);

        b3 = new JButton("Renew");
        b3.addActionListener(this);
        b3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b3.setBounds(47, 340, 118, 33);
        contentPane.add(b3);

        b4 = new JButton("Back");
        b4.addActionListener(this);
        b4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b4.setBounds(199, 340, 100, 33);
        contentPane.add(b4);
    }

    public void actionPerformed(ActionEvent ae){
        try{
            conn con = new conn();
            if(ae.getSource() == b1){           //Search
                String sql = "select * from checkout where item_id = ?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t2.setText(rs.getString("category"));
                    t3.setText(rs.getString("iname"));
                    t4.setText(rs.getString("idate"));
                    t5.setText(rs.getString("due"));
                }
                st.close();
                rs.close();

            }

            if(ae.getSource() == b3){           //renew
            try{
                String sql = "insert into renewitem(item_id, category, iname, idate) values(?, ?, ?, ?)";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                st.setString(2, t2.getText());
                st.setString(3, t3.getText());
                st.setString(4, t4.getText());
                st.executeUpdate();

                boolean renew = false;
                boolean reserve = false;
                java.util.Date due_date;

                String sql5 = "select * from checkout where item_id = ?";
                PreparedStatement st5 = con.c.prepareStatement(sql5);
                st5.setString(1, t1.getText());
                ResultSet rs5 = st5.executeQuery();
                rs5.next();

                due_date = rs5.getDate(6);
                renew = rs5.getBoolean(7);
                reserve = rs5.getBoolean(9);

                Date d1 = rs5.getDate(6);
                Date d2 = rs5.getDate(5);

                long diff = d1.getTime() - d2.getTime();
                int days = (int) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

                if (reserve == true){

                    JOptionPane.showMessageDialog(null,"Request Denied! \n Item has an outstanding request!\n Please return it!");
                }
                else if (renew == false && reserve == false) {

                    Calendar c = Calendar.getInstance();
                    c.setTime(due_date);
                    c.add(Calendar.DATE, days);

                    String sql7 = "update checkout set due = '" + due_date + "', renewed ='" + 1 + "' where item_id = ?";
                    PreparedStatement st7 = con.c.prepareStatement(sql7);
                    st7.setString(1, t1.getText());
                    st7.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Item Renewed!");
                    st7.close();

                }
                else if (renew == true) {
                    JOptionPane.showMessageDialog(null, "Request Denied! \n Item can only be renewed once!");
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            }
            if(ae.getSource() == b4){
                new UserPanel().setVisible(true);
                this.setVisible(false);

            }

            con.c.close();
        }catch(Exception e){

        }
    }
}
