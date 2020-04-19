import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import dataconnection.*;

public class BillPage {
	JFrame plist;
	private JTable ptable;
	private JLabel lblPname,lblCname,lblContact,lblAddress;
	private JLabel lblPcategory;
	private JLabel lblUnit;
	private JTextField textPname,textCname,textContact,textAddress;
	private JTextField textPcategory;
	private JTextField textUnit;
	private Font font2;
	
	private JButton btnCart;
	public static int billId = 1;
    static int sum,result;
	
	public BillPage() {
		
			DBConnect ci = new DBConnect();
			billId = ci.feed("SELECT max(bill_id) From customer");
			sum=ci.feed("select sum(p_price) from customer where bill_id="+billId);
			

		
		font2 = new Font("Siyam Rupali", Font.BOLD, 15);
		
		plist = new JFrame("Bill");
		plist.setVisible(true);
		plist.setResizable(false);
		plist.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
		plist.setBounds(950, 320, 700, 450);
		
		
		
		ptable = new JTable();
		ptable.setBackground(Color.white);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(400, 50, 290, 360);
		sp.setBorder(BorderFactory.createTitledBorder ("Products Table"));
		plist.add(sp);
		sp.setViewportView(ptable);
		this.loadTable();
		
		
		JDesktopPane cdata = new JDesktopPane();
		cdata.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Customer Data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		cdata.setBackground(new Color(3, 224, 255));
		cdata.setBounds(15, 30, 225, 135);
		plist.add(cdata);
		
		lblCname = new JLabel("Name");
		lblCname.setBounds(10, 25, 60, 25);
		lblCname.setFont(font2);
		cdata.add(lblCname);
		textCname = new JTextField();
		textCname.setBounds(80, 25, 130, 25);
		textCname.setFont(font2);
		cdata.add(textCname);
		
		textCname.setText(ci.getName("Select cus_name from customer where bill_id="+billId));
		
		lblContact = new JLabel("Contact");
		lblContact.setBounds(10, 60, 70, 25);
		lblContact.setFont(font2);
		cdata.add(lblContact);
		textContact = new JTextField();
		textContact.setBounds(80, 60, 130, 25);
		textContact.setFont(font2);
		cdata.add(textContact);
		
		textContact.setText(ci.getName("Select cus_contact from customer where bill_id="+billId));
		
		lblAddress = new JLabel("address");
		lblAddress.setBounds(10, 95, 70, 25);
		lblAddress.setFont(font2);
		cdata.add(lblAddress);
		textAddress = new JTextField();
		textAddress.setBounds(80, 95, 130, 25);
		textAddress.setFont(font2);
		cdata.add(textAddress);
		
		textAddress.setText(ci.getName("Select cus_address from customer where bill_id="+billId));
		
		
		
		JDesktopPane pdata = new JDesktopPane();
		pdata.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Bill Payment", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		pdata.setBackground(new Color(99, 230, 35));
		pdata.setBounds(15, 180, 250, 140);
		plist.add(pdata);
		
		lblPname = new JLabel("Total(TK)");
		lblPname.setBounds(10, 25, 80, 25);
		lblPname.setFont(font2);
		pdata.add(lblPname);
		textPname = new JTextField();
		textPname.setBounds(90, 25, 130, 25);
		textPname.setFont(font2);
		pdata.add(textPname);
		
		textPname.setText(String.valueOf(sum));
		
		lblPcategory = new JLabel("Payment");
		lblPcategory.setBounds(10, 60, 80, 25);
		lblPcategory.setFont(font2);
		pdata.add(lblPcategory);
		textPcategory = new JTextField();
		textPcategory.setBounds(90, 60, 130, 25);
		textPcategory.setFont(font2);
		pdata.add(textPcategory);
		textPcategory.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
	            	textUnit.setText(String.valueOf(Integer.parseInt(textPcategory.getText())-sum));
	            }
	         }
	      });
	  
		
		lblUnit = new JLabel("Change");
		lblUnit.setBounds(10, 95, 80, 25);
		lblUnit.setFont(font2);
		pdata.add(lblUnit);
		textUnit = new JTextField();
		textUnit.setBounds(90, 95, 130, 25);
		textUnit.setFont(font2);
		pdata.add(textUnit);
		
		btnCart = new JButton(new ImageIcon("image/icon\\payment.png"));
		btnCart.setBounds(75, 340, 130,50);
		btnCart.setContentAreaFilled(false);
		btnCart.setBorderPainted(true);
		plist.add(btnCart);
		btnCart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				JOptionPane.showMessageDialog(null, "Payment Succesfull");
				plist.dispose();
			}
		});
		
		
		
		JLabel labelproduct = new JLabel();
		labelproduct.setIcon(new ImageIcon("image\\loginBackground.jpg"));
		labelproduct.setBounds(0, 0, 700, 450);
		plist.add(labelproduct);
		
		plist.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		plist.getContentPane().setLayout(null);
		
	}

	private void loadTable() {
		CustomerList model = new CustomerList(billId);
		ptable.setModel(model);
		
	}
}
