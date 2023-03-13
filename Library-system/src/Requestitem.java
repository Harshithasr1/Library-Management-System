import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class Requestitem extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField t1,t2,t3,t4;
    private JButton b1,b3,b4;

    public static void main(String[] args) {
        new Requestitem().setVisible(true);
    }

    public Requestitem() {
        super("Request Item");
        setBounds(300, 200, 400, 400);
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

        JLabel l4 = new JLabel("Status");
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(47, 165, 100, 23);
        contentPane.add(l4);

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

        b3 = new JButton("Request");
        b3.addActionListener(this);
        b3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b3.setBounds(57, 247, 118, 33);
        contentPane.add(b3);

        b4 = new JButton("Back");
        b4.addActionListener(this);
        b4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b4.setBounds(210, 247, 100, 33);
        contentPane.add(b4);
    }

    public void actionPerformed(ActionEvent ae){
        try{
            conn con = new conn();
            if(ae.getSource() == b1){
                String sql = "select * from item where item_id = ?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t2.setText(rs.getString("category"));
                    t3.setText(rs.getString("item_name"));
                    t4.setText(rs.getString("status"));

                }
                st.close();
                rs.close();

            }

            if(ae.getSource() == b3){
                try{
                    String sql7 = "update checkout set requested ='" + 1 + "' where item_id = ?";
                    PreparedStatement st7 = con.c.prepareStatement(sql7);
                    st7.setString(1, t1.getText());
                    st7.executeUpdate();

                    String sql = "insert into requestitem(item_id, category, iname, status) values(?, ?, ?, ?)";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setString(1, t1.getText());
                    st.setString(2, t2.getText());
                    st.setString(3, t3.getText());
                    st.setString(4, t4.getText());

                    int i = st.executeUpdate();

                    if (i > 0)
                        JOptionPane.showMessageDialog(null, "Request Successful..!");
                    else
                        JOptionPane.showMessageDialog(null, "Error");
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
