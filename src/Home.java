import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import dataconnection.DBConnect;

public class Home {
	private JFrame home;
	private JLabel wlc;
	private JButton product, purchase, employee, cngpass, lgout, quit;
	public static Login l1;
	
	
	public Home(Login hm) {
		
		l1 = hm;
		home = new JFrame("Home");
		home.setVisible(true);
		home.setResizable(false);
		home.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\homelogo.png"));
		home.setBounds(110, 110, 550, 460);
		
		wlc = new JLabel("Welcoome "+getUserName());
		wlc.setBounds(180, 20, 250, 30);
		wlc.setFont(new Font("Algerian", Font.PLAIN,20));
		home.add(wlc);
		
		product = new JButton(new ImageIcon("image/icon\\button_product-list.png"));
		product.setBounds(20, 80, 130, 45);
		product.setContentAreaFilled(false);
		//product.setBorderPainted(true);
		home.add(product);
		product.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				Product.getObj().plist.setVisible(true);
			}
		});
		
		
		purchase = new JButton(new ImageIcon("image/icon\\button_purchase.png"));
		purchase.setBounds(20, 140, 130, 45);
		purchase.setContentAreaFilled(false);
		home.add(purchase);
		purchase.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				Purchase.getObj().plist.setVisible(true);
			}
		});
		
		
		employee = new JButton(new ImageIcon("image/icon\\button_employee.png"));
		employee.setBounds(20, 200, 130, 45);
		employee.setContentAreaFilled(false);
		home.add(employee);
		employee.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				Employee.getObj().plist.setVisible(true);
			}
		});
		
		
		cngpass = new JButton(new ImageIcon("image/icon\\button_change-password.png"));
		cngpass.setBounds(20, 260, 130, 45);
		cngpass.setContentAreaFilled(false);
		home.add(cngpass);
		cngpass.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				ChangePassword.getObj(l1.name.getText()).fpas.setVisible(true);
			}
		});
		
		
		quit = new JButton(new ImageIcon("image/icon\\button_exit.png"));
		quit.setBounds(20, 340, 130, 45);
		quit.setContentAreaFilled(false);
		home.add(quit);
		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				System.exit(0);
			}
		});
		
		
		lgout = new JButton(new ImageIcon("image/icon\\lgout.png"));
		lgout.setBounds(485, 15, 35, 35);
		lgout.setContentAreaFilled(false);
		lgout.setBorderPainted(true);
		home.add(lgout);
		lgout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				home.setVisible(false);
				l1.initialize();
				if(Product.obj!=null) {
					Product.getObj().plist.dispose();
				}
				if(Purchase.obj!=null) {
					Purchase.getObj().plist.dispose();
				}
				if(Employee.obj!=null) {
					Employee.getObj().plist.dispose();
				}
				if(Product.obj!=null) {
					ChangePassword.getObj(l1.name.getText()).fpas.dispose();
				}
			
			}
		});
		
		JLabel supershop = new JLabel();
		supershop.setIcon(new ImageIcon("image/icon\\carticn.png"));
		supershop.setBounds(200, 110, 250, 250);
		home.add(supershop);
		
		JLabel tt = new JLabel();
		tt.setIcon(new ImageIcon("image/icon\\titlehome.png"));
		tt.setBounds(10, 5, 150, 65);
		home.add(tt);
		
		JLabel labelleft = new JLabel();
		labelleft.setIcon(new ImageIcon("image\\login.jpg"));
		labelleft.setBounds(0, 0, 550, 460);
		home.add(labelleft);
		
		
		
		
		
		
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.getContentPane().setLayout(null);
		
		
		
		
		
		
	}
	private String getUserName() {
		return new DBConnect().getName("select name from login where username='"+l1.name.getText()+"'");
	}
	

}
