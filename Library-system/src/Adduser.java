import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Adduser extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField t1,t2,t3,t4,t5,t6,t7;
	private JButton b1,b2,b3;

	public static void main(String[] args) {
		new Adduser().setVisible(true);
	}

	public void random() {
		Random rd = new Random();
		t1.setText("" + rd.nextInt(1000 + 1));
	}

	public Adduser() {
		super("Create Account");
		setBounds(600, 200, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel l1 = new JLabel("Password");
		l1.setFont(new Font("Tahoma", Font.BOLD, 14));
		l1.setBounds(73, 117, 90, 22);
		contentPane.add(l1);

		JLabel l2 = new JLabel("Name");
		l2.setFont(new Font("Tahoma", Font.BOLD, 14));
		l2.setBounds(73, 150, 90, 22);
		contentPane.add(l2);

		JLabel l3 = new JLabel("Address");
		l3.setFont(new Font("Tahoma", Font.BOLD, 14));
		l3.setBounds(73, 183, 90, 22);
		contentPane.add(l3);

		JLabel l4 = new JLabel("Card no");
		l4.setFont(new Font("Tahoma", Font.BOLD, 14));
		l4.setBounds(73, 51, 90, 22);
		contentPane.add(l4);

		JLabel l5 = new JLabel("Username");
		l5.setFont(new Font("Tahoma", Font.BOLD, 14));
		l5.setBounds(73, 84, 90, 22);
		contentPane.add(l5);

		t7 = new JTextField();
		t7.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t7.setColumns(10);
		t7.setBounds(169, 86, 198, 20);
		contentPane.add(t7);

		JLabel l6 = new JLabel("Phone");
		l6.setFont(new Font("Tahoma", Font.BOLD, 14));
		l6.setBounds(73, 214, 90, 22);
		contentPane.add(l6);

		t5 = new JTextField();
		t5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t5.setColumns(10);
		t5.setBounds(169, 214, 198, 20);
		contentPane.add(t5);

		JLabel l7 = new JLabel("Age");
		l7.setFont(new Font("Tahoma", Font.BOLD, 14));
		l7.setBounds(73, 244, 90, 22);
		contentPane.add(l7);

		t6 = new JTextField();
		t6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t6.setColumns(10);
		t6.setBounds(169, 244, 198, 20);
		contentPane.add(t6);

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

		b1 = new JButton("Add");
		b1.addActionListener(this);
		b1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		b1.setBounds(102, 300, 100, 33);
		contentPane.add(b1);

		b2 = new JButton("Next");
		b2.addActionListener(this);
		b2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		b2.setBounds(212, 300, 100, 33);
		contentPane.add(b2);

		b3 = new JButton("Back");
		b3.addActionListener(this);
		b3.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		b3.setBounds(165, 340, 100, 33);
		contentPane.add(b3);

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
				String sql = "insert into a_user(card_no, a_name, pwd, name, address, phone, age) values(?,?,?, ?, ?, ?, ?)";
				PreparedStatement st = con.c.prepareStatement(sql);
				// st.setInt(1, Integer.parseInt(textField.getText()));
				st.setString(1, t1.getText());
				st.setString(2, t7.getText());
				st.setString(3, t2.getText());
				st.setString(4, t3.getText());
				st.setString(5, t4.getText());
				st.setString(6, t5.getText());
				st.setString(7, t6.getText());

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

			if(ae.getSource() == b3){
				new Login_admin().setVisible(true);
				this.setVisible(false);
			}

			con.c.close();
		}catch(Exception e){

		}
	}
}