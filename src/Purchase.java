import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import dataconnection.*;

public class Purchase {
	private Statement stat;
	private ResultSet res;
	private Connection con;
	
	JFrame plist;
	private JTable ptable;
	private JComboBox<Object> ptype;
	private JTextField searchProducts;
	
	private JLabel lblPname,lblCname,lblContact,lblAddress;
	private JLabel lblPcategory;
	private JLabel lblPprice;
	private JLabel lblUnit;
	private JTextField textPname,textCname,textContact,textAddress;
	private JTextField textPcategory;
	private JTextField textPprice;
	private JTextField textUnit;
	private Font font2;
	static Purchase obj=null;
	private JButton btnCadd, btnsrc, btnrefresh, btnCart;
	public static int billId = 1,stock=0,pid=0;
	
	public Purchase() {
		
		
			String sql = "SELECT max(bill_id) From customer";
			DBConnect ci = new DBConnect();
			billId = ci.feed(sql);
			billId++;
		
		font2 = new Font("Siyam Rupali", Font.BOLD, 15);
		
		plist = new JFrame("Purchase");
		plist.setVisible(true);
		plist.setResizable(false);
		plist.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
		plist.setBounds(700, 160, 700, 450);
		
		ptype = new JComboBox<Object>();
		ptype.setFont(new Font("Verdana", Font.BOLD, 11));
		ptype.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		ptype.setModel(new DefaultComboBoxModel<Object>(new String[] {"Product Id", "Product Name", "Product Category"}));
		ptype.setBounds(400, 10, 95, 30);
		plist.add(ptype);
		
		searchProducts = new JTextField();
		searchProducts.setBounds(500, 10, 155, 30);
		searchProducts.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));
		searchProducts.setFont(new Font("arial", Font.BOLD,12));
		plist.add(searchProducts);
		searchProducts.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				loadTable();
			}
		});
		
		
		btnsrc = new JButton(new ImageIcon("image/icon\\search.png"));
		btnsrc.setBounds(660, 10, 32, 32);
		btnsrc.setContentAreaFilled(false);
		btnsrc.setBorderPainted(true);
		plist.add(btnsrc);
		btnsrc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				loadTable();
			}
		});
		
		searchProducts.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
	              btnsrc.doClick();
	            }
	         }
		});
		
		ptable = new JTable();
		ptable.setBackground(Color.white);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(400, 50, 290, 360);
		sp.setBorder(BorderFactory.createTitledBorder ("Products Table"));
		plist.add(sp);
		ptable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				try {	
					
					con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/supershop","root","");
					stat = (Statement) con.createStatement();
					
					int row = ptable.getSelectedRow();
					String p_id_=(ptable.getModel().getValueAt(row, 0)).toString();
					String query = "select * from productlist where p_id='"+p_id_+"' ";
					pid=(int) ptable.getValueAt(row, 0);
					res = stat.executeQuery(query);
					
					while (res.next())
							{
								stock=res.getInt("stock");
								textPname.setText(res.getString("p_name"));
								textPcategory.setText(res.getString("category"));
								textPprice.setText(res.getString("p_price"));
								textUnit.setText(res.getString("p_unit"));
							}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		sp.setViewportView(ptable);
		this.loadTable();
		
		
		JDesktopPane cdata = new JDesktopPane();
		cdata.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Customer Data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		cdata.setBackground(new Color(219, 234, 107));
		cdata.setBounds(5, 30, 205, 120);
		plist.add(cdata);
		
		lblCname = new JLabel("Name");
		lblCname.setBounds(10, 25, 60, 22);
		lblCname.setFont(font2);
		cdata.add(lblCname);
		textCname = new JTextField();
		textCname.setBounds(80, 25, 110, 22);
		textCname.setFont(font2);
		cdata.add(textCname);
		
		lblContact = new JLabel("Contact");
		lblContact.setBounds(10, 55, 70, 22);
		lblContact.setFont(font2);
		cdata.add(lblContact);
		textContact = new JTextField();
		textContact.setBounds(80, 55, 110, 22);
		textContact.setFont(font2);
		cdata.add(textContact);
		
		lblAddress = new JLabel("address");
		lblAddress.setBounds(10, 85, 70, 22);
		lblAddress.setFont(font2);
		cdata.add(lblAddress);
		textAddress = new JTextField();
		textAddress.setBounds(80, 85, 110, 22);
		textAddress.setFont(font2);
		cdata.add(textAddress);
		

		
		
		
		JDesktopPane pdata = new JDesktopPane();
		pdata.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Product Data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		pdata.setBackground(new Color(111, 255, 149));
		pdata.setBounds(5, 180, 205, 150);
		plist.add(pdata);
		
		lblPname = new JLabel("Name");
		lblPname.setBounds(10, 25, 60, 22);
		lblPname.setFont(font2);
		pdata.add(lblPname);
		textPname = new JTextField();
		textPname.setBounds(80, 25, 110, 22);
		textPname.setFont(font2);
		pdata.add(textPname);
		
		lblPcategory = new JLabel("Category");
		lblPcategory.setBounds(10, 55, 70, 22);
		lblPcategory.setFont(font2);
		pdata.add(lblPcategory);
		textPcategory = new JTextField();
		textPcategory.setBounds(80, 55, 110, 22);
		textPcategory.setFont(font2);
		pdata.add(textPcategory);
		
		lblUnit = new JLabel("Unit");
		lblUnit.setBounds(10, 85, 70, 22);
		lblUnit.setFont(font2);
		pdata.add(lblUnit);
		textUnit = new JTextField();
		textUnit.setBounds(80, 85, 110, 22);
		textUnit.setFont(font2);
		pdata.add(textUnit);
		
		lblPprice = new JLabel("Price");
		lblPprice.setBounds(10, 115, 70, 22);
		lblPprice.setFont(font2);
		pdata.add(lblPprice);
		textPprice = new JTextField();
		textPprice.setBounds(80, 115, 110, 22);
		textPprice.setFont(font2);
		pdata.add(textPprice);
		
		
		btnrefresh = new JButton(new ImageIcon("image/icon\\refresh.png"));
		btnrefresh.setBounds(640, 30, 40, 40);
		btnrefresh.setContentAreaFilled(false);
		btnrefresh.setBorderPainted(true);
		plist.add(btnrefresh);
		btnrefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				loadTable();
			}
		});
		btnCadd = new JButton(new ImageIcon("image/icon\\addcart.png"));
		btnCadd.setBounds(270, 170, 60, 60);
		btnCadd.setContentAreaFilled(false);
		btnCadd.setBorderPainted(true);
		plist.add(btnCadd);
		btnCadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				
				if(stock>0) {
				String sql = "INSERT INTO customer(bill_id, cus_name, cus_contact, cus_address, p_name, p_category, p_unit, p_price, date) VALUES ('"+billId+"','"+textCname.getText()+"','"+textContact.getText()+"','"+textAddress.getText()+"','"+textPname.getText()+"','"+textPcategory.getText()+"','"+textUnit.getText()+"',"+textPprice.getText()+",now())";
				DBConnect di = new DBConnect();
				di.insert(sql);
				loadTable();
				stock--;
				di.insert("UPDATE productlist SET stock='"+stock+"'where p_id='"+pid+"'");
				}
				else
					JOptionPane.showMessageDialog(null,"This Product is Out 0f Stock");
			}
		});
		
		btnCart = new JButton(new ImageIcon("image/icon\\cart.png"));
		btnCart.setBounds(320, 345, 60, 60);
		btnCart.setContentAreaFilled(false);
		btnCart.setBorderPainted(true);
		plist.add(btnCart);
		btnCart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				new BillPage();
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
		PurchaseList model = new PurchaseList((String)ptype.getSelectedItem(),searchProducts.getText());
		ptable.setModel(model);
		
	}
	public static Purchase getObj() {
		if(obj==null)
			obj = new Purchase();
		return obj;
		}
}
