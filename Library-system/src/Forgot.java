import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.awt.event.*;

public class Forgot extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField t1,t3,t4,t5;
    private JButton b1,b2,b3;

    public static void main(String[] args) {
	new Forgot().setVisible(true);
    }

    public Forgot() {
	super("Forgot Password");
	setBounds(500, 200, 590, 390);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(null);

	JLabel l1 = new JLabel("Username");
	l1.setFont(new Font("Tahoma", Font.BOLD, 13));
	l1.setBounds(109, 83, 87, 29);
	contentPane.add(l1);

	JLabel l3 = new JLabel("Your Security Question");
	l3.setFont(new Font("Tahoma", Font.BOLD, 13));
	l3.setBounds(109, 122, 156, 27);
	contentPane.add(l3);

	JLabel l4 = new JLabel("Answer");
	l4.setFont(new Font("Tahoma", Font.BOLD, 13));
	l4.setBounds(109, 154, 104, 21);
	contentPane.add(l4);

	JLabel l5 = new JLabel("Password");
	l5.setFont(new Font("Tahoma", Font.BOLD, 13));
	l5.setBounds(109, 192, 104, 21);
	contentPane.add(l5);

	t1 = new JTextField();
	t1.setFont(new Font("Tahoma", Font.BOLD, 13));
	t1.setBounds(277, 88, 139, 20);
	contentPane.add(t1);
	t1.setColumns(10);

	t3 = new JTextField();
	t3.setEditable(false);
	t3.setFont(new Font("Tahoma", Font.BOLD, 12));
	t3.setColumns(10);
	t3.setBounds(277, 123, 221, 20);
	contentPane.add(t3);

	t4 = new JTextField();
	t4.setFont(new Font("Tahoma", Font.BOLD, 13));
	t4.setColumns(10);
	t4.setBounds(277, 161, 139, 20);
	contentPane.add(t4);

	t5 = new JTextField();
	t5.setEditable(false);
	t5.setFont(new Font("Tahoma", Font.BOLD, 13));
	t5.setColumns(10);
	t5.setBounds(277, 193, 139, 20);
	contentPane.add(t5);

	b1 = new JButton("Search");
	b1.addActionListener(this);
	b1.setFont(new Font("Tahoma", Font.BOLD, 12));
	b1.setBounds(428, 83, 80, 29);
	contentPane.add(b1);

	b2 = new JButton("Retrieve");
	b2.addActionListener(this);
	b2.setFont(new Font("Tahoma", Font.BOLD, 12));
	b2.setBounds(426, 188, 85, 29);
	contentPane.add(b2);

	b3 = new JButton("Back");
	b3.addActionListener(this);
	b3.setFont(new Font("Tahoma", Font.BOLD, 13));
	b3.setBounds(233, 270, 101, 29);
	contentPane.add(b3);

    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            conn con = new conn();
            if(ae.getSource() == b1){
			String sql = "select * from account where username=?";
			PreparedStatement st = con.c.prepareStatement(sql);

			st.setString(1, t1.getText());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
						t3.setText(rs.getString("sec_q"));
			}

            }
            if(ae.getSource() == b2){
			String sql = "select * from account where sec_ans=?";
			PreparedStatement st = con.c.prepareStatement(sql);

			st.setString(1, t4.getText());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
							t5.setText(rs.getString("password"));
			}

            }
            if(ae.getSource() == b3){
				new Login_user().setVisible(true);
                this.setVisible(false);
			
            }
        }catch(Exception e){
            
        }
    }

}