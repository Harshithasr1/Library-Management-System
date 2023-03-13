import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Admin_view extends JFrame implements ActionListener{

        private final JPanel contentPane;
        private final JButton b1, b2, b3, b4, b5, b6,b7;

        public static void main(String[] args) {

                new Admin_view().setVisible(true);
        }

        public Admin_view() {

                setBounds(200, 30, 800, 480);
                contentPane = new JPanel();
                setContentPane(contentPane);
                contentPane.setLayout(null);

                JMenuBar menuBar = new JMenuBar();
                menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 0), new Color(128, 128, 128)));
                menuBar.setBackground(Color.CYAN);
                menuBar.setBounds(0, 10, 1000, 35);
                contentPane.add(menuBar);

                JMenu mnExit = new JMenu("Exit");
                mnExit.setFont(new Font("Trebuchet MS", Font.BOLD, 17));


                JMenuItem mntmLogout = new JMenuItem("Logout");
                mntmLogout.setBackground(new Color(211, 211, 211));
                mntmLogout.setForeground(new Color(105, 105, 105));
                mntmLogout.addActionListener(this);
                mnExit.add(mntmLogout);

                JMenuItem mntmExit = new JMenuItem("Exit");
                mntmExit.setForeground(new Color(105, 105, 105));
                mntmExit.setBackground(new Color(211, 211, 211));
                mntmExit.addActionListener(this);
                mnExit.add(mntmExit);

                JMenu mnFind = new JMenu("Find");
                mnFind.setFont(new Font("Trebuchet MS", Font.BOLD, 17));

                JMenuItem finditem = new JMenuItem("Find Item");
                finditem.addActionListener(this);
                finditem.setBackground(new Color(211, 211, 211));
                finditem.setForeground(Color.DARK_GRAY);
                mnFind.add(finditem);

                JMenuItem finduser = new JMenuItem("Find User");
                finduser.setBackground(new Color(211, 211, 211));
                finduser.setForeground(Color.DARK_GRAY);
                finduser.addActionListener(this);
                mnFind.add(finduser);

                menuBar.add(mnFind);
                menuBar.add(mnExit);

                JLabel l1 = new JLabel("Admin View");
                l1.setForeground(new Color(204, 51, 102));
                l1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 30));
                l1.setBounds(268, 30, 701, 80);
                contentPane.add(l1);

                b1 = new JButton("Add Items");
                b1.addActionListener(this);
                b1.setBounds(200, 190, 159, 40);
                contentPane.add(b1);

                b2 = new JButton("Add Users");
                b2.addActionListener(this);
                b2.setBounds(430, 190, 167, 36);
                contentPane.add(b2);

                b3 = new JButton("View Books");
                b3.addActionListener(this);
                b3.setBounds(20, 270, 167, 36);
                contentPane.add(b3);

                b4 = new JButton("View A/V");
                b4.addActionListener(this);
                b4.setBounds(207, 270, 167, 36);
                contentPane.add(b4);

                b7 = new JButton("User Info");
                b7.addActionListener(this);
                b7.setBounds(307, 345, 167, 36);
                contentPane.add(b7);

                b5 = new JButton("Add Books");
                b5.addActionListener(this);
                b5.setBounds(387, 270, 175, 36);
                contentPane.add(b5);

                b6 = new JButton("Add Audio/video");
                b6.addActionListener(this);
                b6.setBounds(585, 270, 167, 36);
                contentPane.add(b6);

        }

        public void actionPerformed(ActionEvent ae){
                String msg = ae.getActionCommand();
                if(msg.equals("Logout")){
                        setVisible(false);
                        new Login_user().setVisible(true);
                }else if(msg.equals("Exit")){
                        System.exit(ABORT);

                }else if(msg.equals("Find Item")){
                        setVisible(false);
                        new FindItem().setVisible(true);
                }
                else if(msg.equals("Find User")){
                        setVisible(false);
                        new FindUser().setVisible(true);
                }

                if(ae.getSource() == b1){
                        this.setVisible(false);
                        new Additem().setVisible(true);
                }

                if(ae.getSource() == b2){
                        this.setVisible(false);
                        new Adduser().setVisible(true);
                }

                if(ae.getSource() == b3){
                        this.setVisible(false);
                        new ViewBook().setVisible(true);
                }

                if(ae.getSource() == b4){
                        this.setVisible(false);
                        new ViewAudioVideo().setVisible(true);
                }

                if(ae.getSource() == b5){
                        this.setVisible(false);
                        new book().setVisible(true);
                }

                if(ae.getSource() == b6){
                        this.setVisible(false);
                        new audiovideo().setVisible(true);
                }

                if(ae.getSource() == b7){
                        this.setVisible(false);
                        new Checkout_Details().setVisible(true);
                }
        }
}
