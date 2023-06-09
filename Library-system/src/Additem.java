import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Additem extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField t1,t4,t3;
    private JButton b1,b2;
    JComboBox<String> comboBox, comboBox1, comboBox2;
        
    public static void main(String[] args) {

		new Additem().setVisible(true);
    }

    public void random() {
        Random rd = new Random();
		t1.setText("" + rd.nextInt(1000 + 1));
    }

    public Additem() {
		super("Add Item");
	setBounds(600, 200, 500, 400);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JLabel l1 = new JLabel("Item Name");
	l1.setFont(new Font("Tahoma", Font.BOLD, 14));
	l1.setBounds(73, 150, 90, 22);
	contentPane.add(l1);

	JLabel l3 = new JLabel("Best Sellers");
	l3.setFont(new Font("Tahoma", Font.BOLD, 14));
	l3.setBounds(73, 117, 90, 22);
	contentPane.add(l3);

	comboBox2 = new JComboBox<>();
	comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{"0", "1"}));
	comboBox2.setBounds(169, 126, 198, 20);
	contentPane.add(comboBox2);

	JLabel l2 = new JLabel("ISBN");
	l2.setFont(new Font("Tahoma", Font.BOLD, 14));
	l2.setBounds(73, 183, 90, 22);
	contentPane.add(l2);

	JLabel l4 = new JLabel("Item_id");
	l4.setFont(new Font("Tahoma", Font.BOLD, 14));
	l4.setBounds(73, 51, 90, 22);
	contentPane.add(l4);

	JLabel l5 = new JLabel("Category");
	l5.setFont(new Font("Tahoma", Font.BOLD, 14));
	l5.setBounds(73, 84, 90, 22);
	contentPane.add(l5);

	JLabel l6 = new JLabel("Status");
	l6.setFont(new Font("Tahoma", Font.BOLD, 14));
	l6.setBounds(73, 219, 90, 22);
	contentPane.add(l6);

	t1 = new JTextField();
	t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t1.setColumns(10);
	t1.setBounds(169, 54, 198, 20);
	contentPane.add(t1);

	/*t2 = new JTextField();
	t2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t2.setColumns(10);
	t2.setBounds(169, 120, 198, 20);
	contentPane.add(t2);*/

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
	comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Books", "Reference books", "Audio/Video materials", "Current Best sellers", "Magazines"}));
	comboBox.setBounds(169, 87, 198, 20);
	contentPane.add(comboBox);

	comboBox1 = new JComboBox<>();
	comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Available", "Not Available"}));
	comboBox1.setBounds(169, 219, 198, 20);
	contentPane.add(comboBox1);

    b1 = new JButton("Add");
	b1.addActionListener(this);
	b1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
	b1.setBounds(102, 290, 100, 33);
   	contentPane.add(b1);

	b2 = new JButton("Back");
	b2.addActionListener(this);
	b2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
	b2.setBounds(212, 290, 100, 33);
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
                String sql = "insert into item(item_id, category, best_sell, item_name, isbn, status) values(?, ?, ?, ?, ?, ?)";
		PreparedStatement st = con.c.prepareStatement(sql);
        // st.setInt(1, Integer.parseInt(textField.getText()));
		st.setString(1, t1.getText());
		st.setString(2, (String) comboBox.getSelectedItem());
		st.setString(3, (String) comboBox2.getSelectedItem());
		st.setString(4, t3.getText());
		st.setString(5, t4.getText());
		st.setString(6, (String) comboBox1.getSelectedItem());

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