import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Checkout_Details extends JFrame implements ActionListener{

    private final JPanel contentPane;
    private JTextField t1,t2,t3,t4,t5,t7,t8,t9;
    private JTable table;

    private final JButton b1, b6, b7,b8;

    public static void main(String[] args) {

        new Checkout_Details().setVisible(true);
    }

    public Checkout_Details() {
        super("Checkout Details");
        setBounds(200, 30, 650, 580);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("Card number:");
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(64, 33, 102, 22);
        contentPane.add(l1);

        JLabel l9 = new JLabel("Item Name:");
        l9.setFont(new Font("Tahoma", Font.BOLD, 14));
        l9.setBounds(330, 165, 102, 22);
        contentPane.add(l9);

        t9 = new JTextField();
        t9.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t9.setColumns(10);
        t9.setBounds(415, 164, 180, 25);
        contentPane.add(t9);

        JLabel l2 = new JLabel("User Name:");
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(64, 67, 102, 22);
        contentPane.add(l2);

        JLabel l3 = new JLabel("Address:");
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(64, 100, 102, 22);
        contentPane.add(l3);

        JLabel l4 = new JLabel("Phone no:");
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(64, 135, 102, 22);
        contentPane.add(l4);

        JLabel l5 = new JLabel("Age:");
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(64, 165, 102, 22);
        contentPane.add(l5);

        JLabel l7 = new JLabel("Due date:");
        l7.setFont(new Font("Tahoma", Font.BOLD, 14));
        l7.setBounds(64, 195, 102, 22);
        contentPane.add(l7);

        t7 = new JTextField();
        t7.setEditable(false);
        t7.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t7.setColumns(10);
        t7.setBounds(174, 195, 150, 20);
        contentPane.add(t7);

        b1 = new JButton("Search");
        b1.addActionListener(this);
        b1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b1.setBounds(450, 200, 95, 25);
        contentPane.add(b1);

        JLabel l8 = new JLabel("Overdue fine:");
        l8.setFont(new Font("Tahoma", Font.BOLD, 14));
        l8.setBounds(64, 225, 102, 22);
        contentPane.add(l8);

        t8 = new JTextField();
        t8.setEditable(false);
        t8.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t8.setColumns(10);
        t8.setBounds(174, 225, 150, 20);
        contentPane.add(t8);

        t1 = new JTextField();
        t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t1.setBounds(174, 36, 150, 20);
        contentPane.add(t1);
        t1.setColumns(10);

        t2 = new JTextField();
        t2.setEditable(false);
        t2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t2.setColumns(10);
        t2.setBounds(174, 70, 150, 20);
        contentPane.add(t2);

        t3 = new JTextField();
        t3.setEditable(false);
        t3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t3.setColumns(10);
        t3.setBounds(174, 103, 150, 20);
        contentPane.add(t3);

        t4 = new JTextField();
        t4.setEditable(false);
        t4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t4.setColumns(10);
        t4.setBounds(174, 135, 150, 20);
        contentPane.add(t4);

        t5 = new JTextField();
        t5.setEditable(false);
        t5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t5.setColumns(10);
        t5.setBounds(174, 165, 150, 20);
        contentPane.add(t5);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(58, 310, 290, 180);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        scrollPane.setViewportView(table);

        JLabel l6 = new JLabel("Checked_out items:");
        l6.setFont(new Font("Tahoma", Font.BOLD, 14));
        l6.setBounds(64, 280, 180, 22);
        contentPane.add(l6);

        b6 = new JButton("Enter");
        b6.addActionListener(this);
        b6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b6.setBounds(340, 35, 95, 20);
        contentPane.add(b6);

        b7 = new JButton("Close");
        b7.addActionListener(this);
        b7.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b7.setBounds(420, 350, 100, 41);
        contentPane.add(b7);

        b8 = new JButton("Back");
        b8.addActionListener(this);
        b8.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b8.setBounds(420, 420, 100, 41);
        contentPane.add(b8);

    }

    public void actionPerformed(ActionEvent ae){

        try {
            conn con = new conn();

            if (ae.getSource() == b7) {
                this.setVisible(false);
                new Admin_view().setVisible(true);
            }

            if (ae.getSource() == b8) {
                this.setVisible(false);
                new Checkoutitem().setVisible(true);
            }

            if(ae.getSource() == b6){    //enter

                String sql = "select * from user where card_no = ?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t2.setText(rs.getString("name"));
                    t3.setText(rs.getString("address"));
                    t4.setText(rs.getString("phone"));
                    t5.setText(rs.getString("age"));
                }

                st.close();
                rs.close();

                String sql1 = "select * from checkout where card_no = ?";
                PreparedStatement st1 = con.c.prepareStatement(sql1);
                st1.setString(1, t1.getText());
                ResultSet rs1 = st1.executeQuery();
                rs1.next();
                t7.setText(rs1.getString("due"));

                st1.close();
                rs1.close();

                String sql12 = "select iname from checkout where card_no = ?";
                PreparedStatement st12 = con.c.prepareStatement(sql12);
                st12.setString(1, t1.getText());
                ResultSet rs12 = st12.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs12));

                st12.close();
                rs12.close();

            }

            if(ae.getSource() == b1){    //search

                String sql = "select * from checkout where iname = ?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t9.getText());
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t7.setText(rs.getString("due"));
                    t8.setText(rs.getString("fine"));
                }

                st.close();
                rs.close();
            }
            con.c.close();
        }catch(Exception e){

        }

    }
}
