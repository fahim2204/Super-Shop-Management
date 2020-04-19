import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Admin {
	
	private Login l1;
	private JFrame admin;
	private JButton user, bill, empsal;
	private JButton lgout;
	
	
	public Admin(Login hm) {
		this.l1 = hm;
		
		admin = new JFrame("Admin Page");
		admin.setVisible(true);
		admin.setResizable(false);
		admin.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
		admin.setBounds(300, 200, 350, 500);
				
		
		bill = new JButton(new ImageIcon("image/icon\\button_bill-info.png"));
		bill.setBounds(105, 100, 140, 50);
		bill.setContentAreaFilled(false);
		admin.add(bill);
		bill.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				BillInfo.getObj().plist.setVisible(true);
			}
		});
		
		
		user = new JButton(new ImageIcon("image/icon\\button_user-info.png"));
		user.setBounds(105, 200, 140, 50);
		user.setContentAreaFilled(false);
		admin.add(user);
		user.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				UserInfo.getObj().plist.setVisible(true);
			}
		});
		
		
		empsal = new JButton(new ImageIcon("image/icon\\button_employee2.png"));
		empsal.setBounds(105, 300, 140, 50);
		empsal.setContentAreaFilled(false);
		admin.add(empsal);
		empsal.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				EmpInfo.getObj().plist.setVisible(true);
			}
		});
		
		
		lgout = new JButton(new ImageIcon("image/icon\\lgout.png"));
		lgout.setBounds(290, 15, 35, 35);
		lgout.setContentAreaFilled(false);
		lgout.setBorderPainted(true);
		admin.add(lgout);
		lgout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				admin.setVisible(false);
				l1.initialize();
				if(BillInfo.obj!=null) {
					BillInfo.getObj().plist.dispose();
				}
				if(UserInfo.obj!=null) {
					UserInfo.getObj().plist.dispose();
				}
				if(EmpInfo.obj!=null) {
					EmpInfo.getObj().plist.dispose();
				}				
			}
		});
		
				
		JLabel labelLogin = new JLabel();
		labelLogin.setIcon(new ImageIcon("image\\adminbackground.jpg"));
		labelLogin.setBounds(0, 0, 450, 500);
		admin.add(labelLogin);
		
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.getContentPane().setLayout(null);
		
	}
}
