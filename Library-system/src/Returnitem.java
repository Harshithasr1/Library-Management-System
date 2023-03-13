import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.*;

public class Returnitem extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private JButton b1,b2,b3;
	private JDateChooser dateChooser;

	public static void main(String[] args) {

		new Returnitem().setVisible(true);
	}

	public Returnitem() {
		super("Return Item");
		setBounds(450, 300, 617, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Item id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(52, 52, 87, 24);
		contentPane.add(lblNewLabel);

		JLabel lblCardno = new JLabel("Card no");
		lblCardno.setForeground(Color.BLACK);
		lblCardno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCardno.setBounds(260, 52, 71, 24);
		contentPane.add(lblCardno);

		JLabel lblCat = new JLabel("Category");
		lblCat.setForeground(Color.BLACK);
		lblCat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCat.setBounds(52, 98, 71, 24);
		contentPane.add(lblCat);

		JLabel lblName = new JLabel("Item name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(310, 98, 87, 24);
		contentPane.add(lblName);

		JLabel lblissue = new JLabel("Issue date");
		lblissue.setForeground(Color.BLACK);
		lblissue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblissue.setBounds(52, 143, 87, 24);
		contentPane.add(lblissue);

		JLabel lblreturn = new JLabel("Return date");
		lblreturn.setForeground(Color.BLACK);
		lblreturn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblreturn.setBounds(52, 188, 105, 29);
		contentPane.add(lblreturn);

		t1 = new JTextField();
		t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t1.setBounds(128, 56, 105, 20);
		contentPane.add(t1);
		t1.setColumns(10);

		t2 = new JTextField();
		t2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		t2.setBounds(320, 56, 93, 20);
		contentPane.add(t2);
		t2.setColumns(10);

		b1 = new JButton("Search");
		b1.addActionListener(this);
		b1.setBounds(443, 52, 105, 29);
		contentPane.add(b1);

		t3 = new JTextField();
		t3.setEditable(false);
		t3.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		t3.setBounds(128, 102, 162, 20);
		contentPane.add(t3);
		t3.setColumns(10);

		t4 = new JTextField();
		t4.setEditable(false);
		t4.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		t4.setColumns(10);
		t4.setBounds(389, 102, 162, 20);
		contentPane.add(t4);

		t5 = new JTextField();
		t5.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		t5.setEditable(false);
		t5.setColumns(10);
		t5.setBounds(132, 147, 168, 20);
		contentPane.add(t5);

		dateChooser = new JDateChooser();
		dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		dateChooser.setBounds(160, 194, 172, 29);
		contentPane.add(dateChooser);

		b2 = new JButton("Return");
		b2.addActionListener(this);
		b2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		b2.setBounds(369, 179, 149, 30);
		contentPane.add(b2);

		b3 = new JButton("Back");
		b3.addActionListener(this);
		b3.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		b3.setBounds(369, 231, 149, 30);
		contentPane.add(b3);

	}

	public void actionPerformed(ActionEvent ae){
		try{
			conn con = new conn();
			if(ae.getSource() == b1){
				String sql = "select * from checkout where item_id = ? and card_no =?";
				PreparedStatement st = con.c.prepareStatement(sql);
				st.setString(1, t1.getText());
				st.setString(2, t2.getText());
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					t3.setText(rs.getString("iname"));
					t4.setText(rs.getString("category"));
					t5.setText(rs.getString("idate"));
				}
				st.close();
				rs.close();

			}
			if(ae.getSource() == b2){
				String sql = "insert into returnitem(item_id, card_no, iname, category, idate, rdate) values(?, ?, ?, ?, ?, ?)";
				PreparedStatement st = con.c.prepareStatement(sql);
				st.setString(1, t1.getText());
				st.setString(2, t2.getText());
				st.setString(3, t3.getText());
				st.setString(4, t4.getText());
				st.setString(5, t5.getText());
				java.util.Date utilDate = dateChooser.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				st.setDate(6, sqlDate);

				int i = st.executeUpdate();
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "Item returned..");

				} else
					JOptionPane.showMessageDialog(null, "Error");

			}
			if(ae.getSource() == b3){
				new UserPanel().setVisible(true);
				this.setVisible(false);

			}
		}catch(Exception e){

		}
	}
}