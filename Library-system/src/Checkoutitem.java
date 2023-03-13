import com.toedter.calendar.JDateChooser;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;

public class Checkoutitem extends JFrame implements ActionListener{

	private JPanel contentPane;
	JDateChooser dateChooser;
	private JTextField t1,t2,t3,t6,t8;
	private JButton b1,b3,b4,b5;

	public static void main(String[] args) {
		new Checkoutitem().setVisible(true);

	}

	public Checkoutitem() {
		super("Checkout Items");
		setBounds(300, 200, 500, 450);
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

		JLabel l4 = new JLabel("Issue date");
		l4.setFont(new Font("Tahoma", Font.BOLD, 14));
		l4.setBounds(47, 175, 100, 23);
		contentPane.add(l4);

		JLabel l6 = new JLabel("Card no");
		l6.setFont(new Font("Tahoma", Font.BOLD, 14));
		l6.setBounds(47, 30, 100, 23);
		contentPane.add(l6);

		t1 = new JTextField();
		t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t1.setBounds(140, 66, 86, 20);
		contentPane.add(t1);

		b1 = new JButton("Search");
		b1.addActionListener(this);
		b1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		b1.setBounds(250, 59, 100, 30);
		contentPane.add(b1);

		t2 = new JTextField();
		t2.setEditable(false);
		t2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t2.setBounds(140, 100, 208, 20);
		contentPane.add(t2);
		t2.setColumns(10);

		t3 = new JTextField();
		t3.setEditable(false);
		t3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t3.setColumns(10);
		t3.setBounds(140, 131, 208, 20);
		contentPane.add(t3);

		dateChooser = new JDateChooser();
		dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		dateChooser.setForeground(new Color(105, 105, 105));
		dateChooser.setBounds(140, 175, 200, 29);
		contentPane.add(dateChooser);

		t6 = new JTextField();
		t6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t6.setColumns(10);
		t6.setBounds(140, 33, 86, 20);
		contentPane.add(t6);

		b3 = new JButton("Checkout");
		b3.addActionListener(this);
		b3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		b3.setBounds(47, 257, 118, 33);
		contentPane.add(b3);

		b4 = new JButton("Back");
		b4.addActionListener(this);
		b4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		b4.setBounds(299, 257, 118, 33);
		contentPane.add(b4);

		b5 = new JButton("Checkout detais");
		b5.addActionListener(this);
		b5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		b5.setBounds(148, 310, 150, 33);
		contentPane.add(b5);

	}

	public void actionPerformed(ActionEvent ae){

		try{
			conn con = new conn();
			if(ae.getSource() == b1){ 		//search
				String sql = "select category,item_name from item where item_id = ?";
				PreparedStatement st = con.c.prepareStatement(sql);
				st.setString(1, t1.getText());
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					t2.setText(rs.getString("category"));
					t3.setText(rs.getString("item_name"));
				}
				st.close();
				rs.close();
			}

			if(ae.getSource() == b3){  //checkout
				try {
					String sql1 = "select category from item where item_id = ?";
					PreparedStatement st1 = con.c.prepareStatement(sql1);
					st1.setString(1, t1.getText());
					ResultSet rs1 = st1.executeQuery();
					String cat = "";
					while(rs1.next()) {
						cat = rs1.getString(1);
					}

					if(cat.equals("Magazines") || cat.equals("Reference books")){
						JOptionPane.showMessageDialog(null, "Reference Books and Magazines can not be checked out..!");
					}

					else if(cat.equals("Books")) {
						String sql5 = "select best_sell from item where item_id = ?";
						PreparedStatement st5 = con.c.prepareStatement(sql5);
						st5.setString(1, t1.getText());
						ResultSet rs5 = st5.executeQuery();
						rs5.next();
						Boolean bs = rs5.getBoolean(1);

						if (bs) {
							String sql2 = "insert into checkout(card_no, item_id, category, iname, idate, due) values(?, ?, ?, ?, ?, ?)";
							PreparedStatement st2 = con.c.prepareStatement(sql2);
							st2.setString(1, t6.getText());
							st2.setString(2, t1.getText());
							st2.setString(3, t2.getText());
							st2.setString(4, t3.getText());
							java.util.Date utilDate = dateChooser.getDate();
							java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
							st2.setDate(5, sqlDate);

							Calendar calendar = Calendar.getInstance();
							calendar.setTime(sqlDate);
							calendar.add(Calendar.DATE, 14);
							java.util.Date date = calendar.getTime();
							java.sql.Date due_date = new java.sql.Date(date.getTime());
							st2.setDate(6, due_date);

							st2.executeUpdate();

							JOptionPane.showMessageDialog(null, "Selected item: Best sellers \n Due date: " + due_date);
						}
						else {
							String sql2 = "insert into checkout(card_no, item_id, category, iname, idate, due) values(?, ?, ?, ?, ?, ?)";
							PreparedStatement st2 = con.c.prepareStatement(sql2);
							st2.setString(1, t6.getText());
							st2.setString(2, t1.getText());
							st2.setString(3, t2.getText());
							st2.setString(4, t3.getText());
							java.util.Date utilDate = dateChooser.getDate();
							java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
							st2.setDate(5, sqlDate);

							Calendar calendar = Calendar.getInstance();
							calendar.setTime(sqlDate);
							calendar.add(Calendar.DATE, 21);
							java.util.Date date = calendar.getTime();
							java.sql.Date due_date = new java.sql.Date(date.getTime());
							st2.setDate(6, due_date);

							int i = st2.executeUpdate();

							String sql4 = "select status from item where item_id = ?";
							PreparedStatement st4 = con.c.prepareStatement(sql4);
							st4.setString(1, t1.getText());
							ResultSet rs4 = st4.executeQuery();
							rs4.next();
							String status = rs4.getString(1);

							if (status.contains("Not Available")) {
								JOptionPane.showMessageDialog(null, "This item is not available! Please request for it");
							}

							else if (i > 0)
								JOptionPane.showMessageDialog(null, "Checkout Successful..! \n Due date: " + due_date);
							else
								JOptionPane.showMessageDialog(null, "Error");

							st2.close();
							st4.close();
							rs4.close();
						}
					}
					else if(cat.equals("Audio/Video materials")){
						String sql2 = "insert into checkout(card_no, item_id, category, iname, idate, due) values(?, ?, ?, ?, ?, ?)";
						PreparedStatement st2 = con.c.prepareStatement(sql2);
						st2.setString(1, t6.getText());
						st2.setString(2, t1.getText());
						st2.setString(3, t2.getText());
						st2.setString(4, t3.getText());
						java.util.Date utilDate = dateChooser.getDate();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						st2.setDate(5, sqlDate);

						Calendar calendar = Calendar.getInstance();
						calendar.setTime(sqlDate);
						calendar.add(Calendar.DATE, 14);
						java.util.Date date = calendar.getTime();
						java.sql.Date due_date = new java.sql.Date(date.getTime());
						st2.setDate(6, due_date);


						int i = st2.executeUpdate();
						if (i > 0)

							JOptionPane.showMessageDialog(null, "Checkout Successful..! \n Due date: " + due_date);

						else
							JOptionPane.showMessageDialog(null, "Error");

						st2.close();

					}

					int age = 0;
					String sql = "select age from user where card_no = ?";
					PreparedStatement st = con.c.prepareStatement(sql);
					st.setString(1, t6.getText());
					ResultSet rs = st.executeQuery();

					while (rs.next()) {
						age = rs.getInt(1);
					}
					if (age <= 12) {
						int items = 0;
						String sql3 = "select count(*) from checkout where card_no = ?";
						PreparedStatement st3 = con.c.prepareStatement(sql3);
						st3.setString(1, t6.getText());
						ResultSet rs3 = st3.executeQuery();

						while (rs3.next()) {
							items = rs3.getInt(1);
						}
						if(items >= 5){
							JOptionPane.showMessageDialog(null,"Limit exceeded! \n Age should be more than 12");
							return;
						}

					}

				}
				catch(Exception e){
					e.printStackTrace();
				}
			}

				if(ae.getSource() == b4){
				new UserPanel().setVisible(true);
				this.setVisible(false);
			}

			if(ae.getSource() == b5){
				new Checkout_Details().setVisible(true);
				this.setVisible(false);
			}


			con.c.close();
		}catch(Exception e){

		}
	}
}
