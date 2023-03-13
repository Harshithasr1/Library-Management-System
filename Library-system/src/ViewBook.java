import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewBook extends JFrame implements ActionListener{

    private final JPanel contentPane;
    private JTable table;
    JButton b1;

    public static void main(String[] args) {

        new ViewBook().setVisible(true);
    }

    public void book() {
        try {
            conn con = new conn();
            String sql = "select * from book";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            st.close();
            con.c.close();
        } catch (Exception e) {

        }
    }

    public ViewBook() {
        super("Books available");
        setBounds(200, 30, 1010, 520);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        b1 = new JButton("Back");
        b1.addActionListener(this);
        b1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        b1.setBounds(500, 380, 111, 33);
        contentPane.add(b1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(70, 110, 860, 250);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        scrollPane.setViewportView(table);
        book();

    }

    public void actionPerformed(ActionEvent ae){

        try {
            conn con = new conn();

            if(ae.getSource() == b1){
                new Admin_view().setVisible(true);
                this.setVisible(false);
            }
            con.c.close();

        }catch(Exception e){

        }

    }
}