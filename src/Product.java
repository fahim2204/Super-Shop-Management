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

public class Product {
	private Connection con;
	private Statement stat;
	private ResultSet res;
	
	
	JFrame plist;
	private JTable ptable;
	private JComboBox<Object> ptype;
	private JTextField searchProducts;
	
	private JLabel lblPid;
	private JLabel lblPname;
	private JLabel lblPcategory;
	private JLabel lblPprice;
	private JLabel lblUnit;
	private JLabel lblStock;
	private JTextField textPid;
	private JTextField textPname;
	private JTextField textPcategory;
	private JTextField textPprice;
	private JTextField textUnit;
	private JTextField textStock;
	private Font font2;
	static Product obj=null;
	
	private JButton btnPadd, btnpUpdate, btnPdelete, btnrefresh, btnsrc, clcbtn;
	
	private Product() {
		
		
		font2 = new Font("Siyam Rupali", Font.BOLD, 15);
		
		plist = new JFrame("Product List");
		plist.setVisible(false);
		plist.setResizable(false);
		plist.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
		plist.setBounds(661, 110, 700, 450);
		
		JLabel picn = new JLabel();
		picn.setIcon(new ImageIcon("image/icon\\shopbag.png"));
		picn.setBounds(290, 20, 40, 40);
		plist.add(picn);
		JLabel pti = new JLabel("Product List");
		pti.setBounds(335, 20, 120, 25);
		pti.setFont(new Font("Siyam Rupali", Font.BOLD, 18));
		pti.setForeground(Color.white);
		plist.add(pti);
		
		ptype = new JComboBox<Object>();
		ptype.setFont(new Font("Verdana", Font.BOLD, 11));
		ptype.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		ptype.setModel(new DefaultComboBoxModel<Object>(new String[] {"Product Id", "Product Name", "Product Category"}));
		ptype.setBounds(10, 10, 130, 25);
		plist.add(ptype);
		
		searchProducts = new JTextField();
		searchProducts.setBounds(10, 40, 150, 30);
		searchProducts.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));
		searchProducts.setFont(new Font("arial", Font.BOLD,15));
		plist.add(searchProducts);
		
		
		btnsrc = new JButton(new ImageIcon("image/icon\\search.png"));
		btnsrc.setBounds(165, 39, 32, 32);
		btnsrc.setContentAreaFilled(false);
		btnsrc.setBorderPainted(true);
		plist.add(btnsrc);
		
		searchProducts.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				populateTable();
			}
		});
		btnsrc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				populateTable();
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
		sp.setBounds(285, 75, 400, 335);
		sp.setBorder(BorderFactory.createTitledBorder ("Products Table"));
		plist.add(sp);
		ptable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {	
					con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/supershop","root","");
					stat = (Statement) con.createStatement();
					
					int row = ptable.getSelectedRow();
					String p_id_=(ptable.getModel().getValueAt(row, 0)).toString();
					res = stat.executeQuery("select * from productlist where p_id='"+p_id_+"' ");
					
					while (res.next())
							{
								textPid.setText(res.getString("p_id"));
								textPname.setText(res.getString("p_name"));
								textPcategory.setText(res.getString("category"));
								textPprice.setText(res.getString("p_price"));
								textUnit.setText(res.getString("p_unit"));
								textStock.setText(res.getString("stock"));
							}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		sp.setViewportView(ptable);
		this.populateTable();
			
		
		/*ptable = new JTable();
		ptable.setBackground(Color.white);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(290, 75, 400, 335);
		plist.add(sp);
		sp.setViewportView(ptable);
		this.populateTable();*/
			
		
		JDesktopPane pdata = new JDesktopPane();
		pdata.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Product Data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		pdata.setBackground(new Color(0, 128, 255));
		pdata.setBounds(2, 100, 260, 245);
		plist.add(pdata);
		
		lblPid = new JLabel("Product Id");
		lblPid.setBounds(10, 25, 80, 25);
		lblPid.setFont(font2);
		pdata.add(lblPid);
		textPid = new JTextField();
		textPid.setBounds(100, 25, 130, 25);
		textPid.setFont(font2);
		pdata.add(textPid);
		
		lblPname = new JLabel("Name");
		lblPname.setBounds(10, 65, 80, 25);
		lblPname.setFont(font2);
		pdata.add(lblPname);
		textPname = new JTextField();
		textPname.setBounds(100, 65, 130, 25);
		textPname.setFont(font2);
		pdata.add(textPname);
		
		lblPcategory = new JLabel("Category");
		lblPcategory.setBounds(10, 100, 80, 25);
		lblPcategory.setFont(font2);
		pdata.add(lblPcategory);
		textPcategory = new JTextField();
		textPcategory.setBounds(100, 100, 130, 25);
		textPcategory.setFont(font2);
		pdata.add(textPcategory);
		
		lblPprice = new JLabel("Price");
		lblPprice.setBounds(10, 135, 80, 25);
		lblPprice.setFont(font2);
		pdata.add(lblPprice);
		textPprice = new JTextField();
		textPprice.setBounds(100, 135, 130, 25);
		textPprice.setFont(font2);
		pdata.add(textPprice);
		
		lblUnit = new JLabel("Unit");
		lblUnit.setBounds(10, 170, 80, 25);
		lblUnit.setFont(font2);
		pdata.add(lblUnit);
		textUnit = new JTextField();
		textUnit.setBounds(100, 170, 130, 25);
		textUnit.setFont(font2);
		pdata.add(textUnit);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 205, 80, 25);
		lblStock.setFont(font2);
		pdata.add(lblStock);
		textStock = new JTextField();
		textStock.setBounds(100, 205, 130, 25);
		textStock.setFont(font2);
		pdata.add(textStock);
		
		
		btnrefresh = new JButton(new ImageIcon("image/icon\\refresh.png"));
		btnrefresh.setBounds(640, 30, 40, 40);
		btnrefresh.setContentAreaFilled(false);
		btnrefresh.setBorderPainted(true);
		plist.add(btnrefresh);
		btnrefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				populateTable();
			}
		});
		btnPadd = new JButton(new ImageIcon("image/icon\\plus.png"));
		btnPadd.setBounds(210, 355, 50, 50);
		btnPadd.setContentAreaFilled(false);
		btnPadd.setBorderPainted(true);
		plist.add(btnPadd);
		btnPadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				String sql = "INSERT INTO productlist VALUES ("+textPid.getText()+",'"+textPname.getText()+"','"+textPcategory.getText()+"','"+textPprice.getText()+"','"+textUnit.getText()+"','"+textStock.getText()+"')";
				DBConnect di = new DBConnect();
				di.insert(sql);
				populateTable();
			}
		});
		btnpUpdate = new JButton(new ImageIcon("image/icon\\update.png"));
		btnpUpdate.setBounds(145, 355, 50, 50);
		btnpUpdate.setContentAreaFilled(false);
		btnpUpdate.setBorderPainted(true);
		plist.add(btnpUpdate);
		btnpUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				int row = ptable.getSelectedRow();

				if(row<0)
				{
					JOptionPane.showMessageDialog(null, "Please Select A Row First");
					return;
				}
				
				String sql = "UPDATE productlist SET P_name='"+textPname.getText()+"',category='"+textPcategory.getText()+"',p_price="+textPprice.getText()+",p_unit='"+textUnit.getText()+"',stock="+textStock.getText()+" WHERE p_id="+textPid.getText()+"";
				DBConnect di = new DBConnect();
				di.insert(sql);
				populateTable();
			}
		});
		btnPdelete = new JButton(new ImageIcon("image/icon\\delete.png"));
		btnPdelete.setBounds(80, 355, 50, 50);
		btnPdelete.setContentAreaFilled(false);
		btnPdelete.setBorderPainted(true);
		plist.add(btnPdelete);
		btnPdelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				String sql = "DELETE FROM productlist WHERE p_id = " + textPid.getText();
				DBConnect di = new DBConnect();
				di.delete(sql);
				populateTable();
			}
		});
		
		clcbtn = new JButton(new ImageIcon("image/icon\\clc.png"));
		clcbtn.setBounds(15, 355, 50, 50);
		//clcbtn.setOpaque(true);
		clcbtn.setContentAreaFilled(false);
		clcbtn.setBorderPainted(true);
		plist.add(clcbtn);
		clcbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				textPid.setText("");
				textPname.setText("");
				textPcategory.setText("");
				textPprice.setText("");
				textUnit.setText("");
				textStock.setText("");
			}
			
		});
		
		
		
		JLabel labelproduct = new JLabel();
		labelproduct.setIcon(new ImageIcon("image\\loginBackground.jpg"));
		labelproduct.setBounds(0, 0, 700, 450);
		plist.add(labelproduct);
		
		plist.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		plist.getContentPane().setLayout(null);
		
	}

	private void populateTable() {
		ProductListTbl model = new ProductListTbl((String)ptype.getSelectedItem(),searchProducts.getText());
		ptable.setModel(model);
		
	}
	public static Product getObj() {
		if(obj==null)
			obj = new Product();
		return obj;
		}
}
