import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class My_card extends JFrame implements ActionListener{

    private final JPanel contentPane;
    private JTextField t1,t2,t3,t4,t5;

    private final JButton b6,b8;

    public static void main(String[] args) {

        new My_card().setVisible(true);
    }

    public My_card() {
        super("Library Card");
        setBounds(260, 130, 530, 380);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("Card number:");
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(64, 33, 102, 22);
        contentPane.add(l1);

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

        b6 = new JButton("Enter");
        b6.addActionListener(this);
        b6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b6.setBounds(340, 35, 95, 20);
        contentPane.add(b6);

        b8 = new JButton("Back");
        b8.addActionListener(this);
        b8.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b8.setBounds(180, 230, 100, 41);
        contentPane.add(b8);

    }

    public void actionPerformed(ActionEvent ae){

        try {
            conn con = new conn();

            if (ae.getSource() == b8) {         //back
                this.setVisible(false);
                new UserPanel().setVisible(true);
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

            }
            con.c.close();
        }catch(Exception e){

        }

    }
}
