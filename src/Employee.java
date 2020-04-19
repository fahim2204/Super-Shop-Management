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

public class Employee {
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
	private JLabel lblStock;
	private JTextField textPid;
	private JTextField textPname;
	private JTextField textPcategory;
	private JTextField textPprice;
	private JTextField textStock;
	private Font font2;
	static Employee obj=null;
	private JButton btnPadd, btnpUpdate, btnPdelete, btnrefresh, btnsrc;
	
	public Employee() {
		
		
		font2 = new Font("Siyam Rupali", Font.BOLD, 15);
		
		plist = new JFrame("Employee Data");
		plist.setVisible(true);
		plist.setResizable(false);
		plist.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
		plist.setBounds(680, 140, 700, 450);
		
		JLabel picn = new JLabel();
		picn.setIcon(new ImageIcon("image/icon\\employee.png"));
		picn.setBounds(150, 280, 130, 130);
		plist.add(picn);
		
		ptype = new JComboBox<Object>();
		ptype.setFont(new Font("Verdana", Font.BOLD, 11));
		ptype.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		ptype.setModel(new DefaultComboBoxModel<Object>(new String[] {"Name", "Contact", "Address"}));
		ptype.setBounds(6, 10, 100, 35);
		plist.add(ptype);
		
		searchProducts = new JTextField();
		searchProducts.setBounds(112, 10, 150, 35);
		searchProducts.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));
		searchProducts.setFont(new Font("arial", Font.BOLD,15));
		plist.add(searchProducts);
		
		
		btnsrc = new JButton(new ImageIcon("image/icon\\search.png"));
		btnsrc.setBounds(265, 10, 32, 32);
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
		
		
		btnrefresh = new JButton(new ImageIcon("image/icon\\crefresh.png"));
		btnrefresh.setBounds(354, 386, 20, 20);
		btnrefresh.setContentAreaFilled(false);
		btnrefresh.setBorderPainted(true);
		plist.add(btnrefresh);
		btnrefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				populateTable();
			}
		});
		
		ptable = new JTable();
		ptable.setBackground(Color.white);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(350, 10, 340, 400);
		sp.setBorder(BorderFactory.createTitledBorder ("Employee Data"));
		plist.add(sp);
		ptable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {	
					//new DBConnect();
					//plist = da.GetPList(query);
					
					con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/supershop","root","");
					stat = (Statement) con.createStatement();
					
					int row = ptable.getSelectedRow();
					String id=(ptable.getModel().getValueAt(row, 0)).toString();
					String query = "select * from employee where eid='"+id+"' ";
					//PreparedStatement pst = connection.prepareStatement(query);
					res = stat.executeQuery(query);
					
					while (res.next())
							{
								textPid.setText(res.getString("eid"));
								textPname.setText(res.getString("name"));
								textPcategory.setText(res.getString("contact"));
								textPprice.setText(res.getString("address"));
								textStock.setText(res.getString("post"));
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
		pdata.setBounds(2, 60, 300, 215);
		plist.add(pdata);
		
		lblPid = new JLabel("EID");
		lblPid.setBounds(10, 25, 80, 25);
		lblPid.setFont(font2);
		pdata.add(lblPid);
		textPid = new JTextField();
		textPid.setBounds(100, 25, 160, 25);
		textPid.setFont(font2);
		pdata.add(textPid);
		
		lblPname = new JLabel("Name");
		lblPname.setBounds(10, 65, 80, 25);
		lblPname.setFont(font2);
		pdata.add(lblPname);
		textPname = new JTextField();
		textPname.setBounds(100, 65, 160, 25);
		textPname.setFont(font2);
		pdata.add(textPname);
		
		lblPcategory = new JLabel("Contact");
		lblPcategory.setBounds(10, 100, 80, 25);
		lblPcategory.setFont(font2);
		pdata.add(lblPcategory);
		textPcategory = new JTextField();
		textPcategory.setBounds(100, 100, 160, 25);
		textPcategory.setFont(font2);
		pdata.add(textPcategory);
		
		lblPprice = new JLabel("Address");
		lblPprice.setBounds(10, 135, 80, 25);
		lblPprice.setFont(font2);
		pdata.add(lblPprice);
		textPprice = new JTextField();
		textPprice.setBounds(100, 135, 160, 25);
		textPprice.setFont(font2);
		pdata.add(textPprice);
		
		
		lblStock = new JLabel("post");
		lblStock.setBounds(10, 170, 80, 25);
		lblStock.setFont(font2);
		pdata.add(lblStock);
		textStock = new JTextField();
		textStock.setBounds(100, 170, 160, 25);
		textStock.setFont(font2);
		pdata.add(textStock);
		
		
		btnPadd = new JButton(new ImageIcon("image/icon\\eadd.png"));
		btnPadd.setBounds(10, 280, 110, 40);
		btnPadd.setContentAreaFilled(false);
		btnPadd.setBorderPainted(true);
		plist.add(btnPadd);
		btnPadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				String sql = "INSERT INTO employee(eid, name, contact, address, post) VALUES ("+textPid.getText()+",'"+textPname.getText()+"','"+textPcategory.getText()+"','"+textPprice.getText()+"','"+textStock.getText()+"')";
				DBConnect di = new DBConnect();
				di.insert(sql);
				populateTable();
			}
		});
		btnpUpdate = new JButton(new ImageIcon("image/icon\\eupdate.png"));
		btnpUpdate.setBounds(10, 325, 110, 40);
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
				
				String sql = "UPDATE employee SET name='"+textPname.getText()+"',contact='"+textPcategory.getText()+"',address='"+textPprice.getText()+"',post='"+textStock.getText()+"' WHERE eid="+textPid.getText()+"";
				DBConnect di = new DBConnect();
				di.insert(sql);
				populateTable();
			}
		});
		btnPdelete = new JButton(new ImageIcon("image/icon\\edelete.png"));
		btnPdelete.setBounds(10, 370, 110, 40);
		btnPdelete.setContentAreaFilled(false);
		btnPdelete.setBorderPainted(true);
		plist.add(btnPdelete);
		btnPdelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				String sql = "DELETE FROM employee WHERE eid = " + textPid.getText();
				DBConnect di = new DBConnect();
				di.delete(sql);
				populateTable();
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
		EListTbl model = new EListTbl((String)ptype.getSelectedItem(),searchProducts.getText());
		ptable.setModel(model);
		
	}
	public static Employee getObj() {
		if(obj==null)
			obj = new Employee();
		return obj;
		}
}
