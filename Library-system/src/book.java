import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class book extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField t1,t2,t3,t4,t5,t6,t7;
    private JButton b1,b2;
    JComboBox<String> comboBox;

    public static void main(String[] args) {
        new book().setVisible(true);
    }

    public void random() {
        Random rd = new Random();
        t1.setText("" + rd.nextInt(1000 + 1));
    }

    public book() {
        super("Add new books");
        setBounds(600, 200, 500, 510);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel l1 = new JLabel("Name");
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(73, 117, 90, 22);
        contentPane.add(l1);

        JLabel l2 = new JLabel("ISBN");
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(73, 150, 90, 22);
        contentPane.add(l2);

        JLabel l3 = new JLabel("Author");
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(73, 183, 90, 22);
        contentPane.add(l3);

        JLabel l4 = new JLabel("Item_id");
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(73, 51, 90, 22);
        contentPane.add(l4);

        JLabel l5 = new JLabel("Category");
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(73, 84, 90, 22);
        contentPane.add(l5);

        JLabel l6 = new JLabel("Edition");
        l6.setFont(new Font("Tahoma", Font.BOLD, 14));
        l6.setBounds(73, 214, 90, 22);
        contentPane.add(l6);

        t5 = new JTextField();
        t5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t5.setColumns(10);
        t5.setBounds(169, 214, 198, 20);
        contentPane.add(t5);

        JLabel l7 = new JLabel("Price");
        l7.setFont(new Font("Tahoma", Font.BOLD, 14));
        l7.setBounds(73, 244, 90, 22);
        contentPane.add(l7);

        t6 = new JTextField();
        t6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t6.setColumns(10);
        t6.setBounds(169, 244, 198, 20);
        contentPane.add(t6);

        JLabel l8 = new JLabel("Best sellers");
        l8.setFont(new Font("Tahoma", Font.BOLD, 14));
        l8.setBounds(73, 278, 100, 22);
        contentPane.add(l8);

        t7 = new JTextField();
        t7.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t7.setColumns(10);
        t7.setBounds(169, 278, 198, 20);
        contentPane.add(t7);

        t1 = new JTextField();
        t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t1.setColumns(10);
        t1.setBounds(169, 54, 198, 20);
        contentPane.add(t1);

        t2 = new JTextField();
        t2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t2.setColumns(10);
        t2.setBounds(169, 120, 198, 20);
        contentPane.add(t2);

        t3 = new JTextField();
        t3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t3.setColumns(10);
        t3.setBounds(169, 153, 198, 20);
        contentPane.add(t3);

        t4 = new JTextField();
        t4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        t4.setColumns(10);
        t4.setBounds(169, 186, 198, 20);
        contentPane.add(t4);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Books", "Best_Sellers"}));
        comboBox.setBounds(169, 87, 198, 20);
        contentPane.add(comboBox);

        b1 = new JButton("Add");
        b1.addActionListener(this);
        b1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        b1.setBounds(102, 400, 100, 33);
        contentPane.add(b1);

        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        b2.setBounds(212, 400, 100, 33);
        contentPane.add(b2);

        JPanel panel = new JPanel();
        panel.setBounds(20, 29, 398, 344);
        contentPane.add(panel);

        panel.setBackground(Color.WHITE);
        contentPane.setBackground(Color.WHITE);
        random();

    }

    public void actionPerformed(ActionEvent ae){
        try{
            conn con = new conn();
            if(ae.getSource() == b1){
                String sql = "insert into book(item_id, category, bname, isbn, author, edition, price, best sellers) values(?,?,?, ?, ?, ?, ?, ?)";
                PreparedStatement st = con.c.prepareStatement(sql);
                // st.setInt(1, Integer.parseInt(textField.getText()));
                st.setString(1, t1.getText());
                st.setString(2, (String) comboBox.getSelectedItem());
                st.setString(3, t2.getText());
                st.setString(4, t3.getText());
                st.setString(5, t4.getText());
                st.setString(6, t5.getText());
                st.setString(7, t6.getText());
                st.setString(8, t7.getText());

                int rs = st.executeUpdate();
                if (rs > 0)
                    JOptionPane.showMessageDialog(null, "Successfully Added!");
                else
                    JOptionPane.showMessageDialog(null, "Error!");

                st.close();
            }
            if(ae.getSource() == b2){
                new Admin_view().setVisible(true);
                this.setVisible(false);
            }
            con.c.close();
        }catch(Exception e){

        }
    }
}