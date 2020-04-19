import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;

import dataconnection.*;

public class UserInfo {
	private Connection con;
	private Statement stat;
	private ResultSet res;
	
	
	JFrame plist;
	private JTable ptable;
	private JComboBox<Object> ptype;
	private JTextField searchProducts;
	static UserInfo obj=null;
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
	
	private JButton btnPadd, btnpUpdate, btnPdelete, btnrefresh, btnsrc;
	
	private UserInfo() {
		
		
		font2 = new Font("Siyam Rupali", Font.BOLD, 15);
		
		plist = new JFrame("User Data");
		plist.setVisible(true);
		plist.setResizable(false);
		plist.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
		plist.setBounds(850, 200, 660, 370);
		
		ptype = new JComboBox<Object>();
		ptype.setFont(new Font("Verdana", Font.BOLD, 11));
		ptype.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		ptype.setModel(new DefaultComboBoxModel<Object>(new String[] {"Name", "User Name", "Type"}));
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
		btnrefresh.setBounds(324, 306, 20, 20);
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
		sp.setBounds(320, 10, 330,320);
		sp.setBorder(BorderFactory.createTitledBorder ("User Info"));
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
					String query = "select * from login where id='"+id+"' ";
					//PreparedStatement pst = connection.prepareStatement(query);
					res = stat.executeQuery(query);
					
					while (res.next())
							{
								textPid.setText(res.getString("id"));
								textPname.setText(res.getString("name"));
								textPcategory.setText(res.getString("username"));
								textPprice.setText(res.getString("password"));
								textStock.setText(res.getString("type"));
							}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		sp.setViewportView(ptable);
		this.populateTable();
		
		JDesktopPane pdata = new JDesktopPane();
		pdata.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "User Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		pdata.setBackground(new Color(34, 230, 168));
		pdata.setBounds(2, 60, 300, 215);
		plist.add(pdata);
		
		lblPid = new JLabel("ID");
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
		
		lblPcategory = new JLabel("User Name");
		lblPcategory.setBounds(10, 100, 80, 25);
		lblPcategory.setFont(font2);
		pdata.add(lblPcategory);
		textPcategory = new JTextField();
		textPcategory.setBounds(100, 100, 160, 25);
		textPcategory.setFont(font2);
		pdata.add(textPcategory);
		
		lblPprice = new JLabel("Password");
		lblPprice.setBounds(10, 135, 80, 25);
		lblPprice.setFont(font2);
		pdata.add(lblPprice);
		textPprice = new JTextField();
		textPprice.setBounds(100, 135, 160, 25);
		textPprice.setFont(font2);
		pdata.add(textPprice);
		
		
		lblStock = new JLabel("Type");
		lblStock.setBounds(10, 170, 80, 25);
		lblStock.setFont(font2);
		pdata.add(lblStock);
		textStock = new JTextField();
		textStock.setBounds(100, 170, 160, 25);
		textStock.setFont(font2);
		pdata.add(textStock);
		
		
		btnPadd = new JButton(new ImageIcon("image/icon\\eadd.png"));
		btnPadd.setBounds(5, 280, 95, 40);
		btnPadd.setContentAreaFilled(false);
		btnPadd.setBorderPainted(true);
		plist.add(btnPadd);
		btnPadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				String sql = "INSERT INTO login VALUES ("+textPid.getText()+",'"+textPname.getText()+"','"+textPcategory.getText()+"','"+textPprice.getText()+"','"+textStock.getText()+"')";
				DBConnect di = new DBConnect();
				di.insert(sql);
				populateTable();
			}
		});
		btnpUpdate = new JButton(new ImageIcon("image/icon\\eupdate.png"));
		btnpUpdate.setBounds(105, 280, 95, 40);
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
				
				String sql = "UPDATE login SET name='"+textPname.getText()+"',username='"+textPcategory.getText()+"',password='"+textPprice.getText()+"',type='"+textStock.getText()+"' WHERE id="+textPid.getText()+"";
				DBConnect di = new DBConnect();
				di.insert(sql);
				populateTable();
			}
		});
		btnPdelete = new JButton(new ImageIcon("image/icon\\edelete.png"));
		btnPdelete.setBounds(205, 280, 95, 40);
		btnPdelete.setContentAreaFilled(false);
		btnPdelete.setBorderPainted(true);
		plist.add(btnPdelete);
		btnPdelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				String sql = "DELETE FROM login WHERE id = " + textPid.getText();
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
		UsersTableModel model = new UsersTableModel((String)ptype.getSelectedItem(),searchProducts.getText());
		ptable.setModel(model);
		
	}
	public static UserInfo getObj() {
		if(obj==null)
			obj = new UserInfo();
		return obj;
		}
}
