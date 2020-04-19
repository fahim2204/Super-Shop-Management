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

public class BillInfo {
	private Connection con;
	private Statement stat;
	private ResultSet res;
	
	
	JFrame plist;
	private JTable ptable;
	private JComboBox<Object> ptype;
	private JTextField searchProducts;
	static BillInfo obj=null;
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
	private int d;
	
	private JButton btnpUpdate, btnPdelete, btnsrc;
	
	private BillInfo() {
		
		
		font2 = new Font("Siyam Rupali", Font.BOLD, 15);
		
		plist = new JFrame("Bill Info");
		plist.setVisible(true);
		plist.setResizable(false);
		plist.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\billinfo.png"));
		plist.setBounds(600, 200, 850, 500);
		
		ptype = new JComboBox<Object>();
		ptype.setFont(new Font("Verdana", Font.BOLD, 11));
		ptype.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		ptype.setModel(new DefaultComboBoxModel<Object>(new String[] {"Bill Id", "Cus Name", "Cus Contact","Category","Date"}));
		ptype.setBounds(575, 10, 100, 30);
		plist.add(ptype);
		
		searchProducts = new JTextField();
		searchProducts.setBounds(680, 10, 150, 30);
		searchProducts.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));
		searchProducts.setFont(new Font("arial", Font.BOLD,15));
		plist.add(searchProducts);
		
		searchProducts.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
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
		sp.setBounds(280, 50, 560,410);
		sp.setBorder(BorderFactory.createTitledBorder ("Order Info"));
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
					d=(int) ptable.getValueAt(row, 0);
					String query = "select * from customer where id='"+id+"' ";
					res = stat.executeQuery(query);
					
					while (res.next())
							{
								textPid.setText(res.getString("bill_id"));
								textPname.setText(res.getString("cus_name"));
								textPcategory.setText(res.getString("cus_contact"));
								textPprice.setText(res.getString("cus_address"));
								textStock.setText(res.getString("date"));
							}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		sp.setViewportView(ptable);
		this.populateTable();
		
		JDesktopPane pdata = new JDesktopPane();
		pdata.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Bill Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		pdata.setBackground(new Color(82, 197, 242));
		pdata.setBounds(5, 140, 260, 230);
		plist.add(pdata);
		
		
		lblStock = new JLabel("Date");
		lblStock.setBounds(10, 25, 80, 25);
		lblStock.setFont(font2);
		pdata.add(lblStock);
		textStock = new JTextField();
		textStock.setBounds(100, 25, 100, 25);
		textStock.setFont(font2);
		pdata.add(textStock);
		
		lblPid = new JLabel("Bill ID");
		lblPid.setBounds(10, 65, 80, 25);
		lblPid.setFont(font2);
		pdata.add(lblPid);
		textPid = new JTextField();
		textPid.setBounds(100, 65, 130, 25);
		textPid.setFont(font2);
		pdata.add(textPid);
		
		lblPname = new JLabel("Name");
		lblPname.setBounds(10, 100, 80, 25);
		lblPname.setFont(font2);
		pdata.add(lblPname);
		textPname = new JTextField();
		textPname.setBounds(100, 100, 130, 25);
		textPname.setFont(font2);
		pdata.add(textPname);
		
		lblPcategory = new JLabel("Contact");
		lblPcategory.setBounds(10, 135, 80, 25);
		lblPcategory.setFont(font2);
		pdata.add(lblPcategory);
		textPcategory = new JTextField();
		textPcategory.setBounds(100, 135, 130, 25);
		textPcategory.setFont(font2);
		pdata.add(textPcategory);
		
		lblPprice = new JLabel("Address");
		lblPprice.setBounds(10, 170, 80, 25);
		lblPprice.setFont(font2);
		pdata.add(lblPprice);
		textPprice = new JTextField();
		textPprice.setBounds(100, 170, 130, 45);
		textPprice.setFont(font2);
		pdata.add(textPprice);
		
		
		
		
		
		btnpUpdate = new JButton(new ImageIcon("image/icon\\eupdate.png"));
		btnpUpdate.setBounds(30, 400, 95, 40);
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
				
				String sql = "UPDATE customer SET bill_id="+textPid.getText()+",cus_name='"+textPname.getText()+"',cus_contact='"+textPcategory.getText()+"',cus_address='"+textPprice.getText()+"',date='"+textStock.getText()+"' WHERE id="+d+"";
				DBConnect di = new DBConnect();
				di.insert(sql);
				populateTable();
			}
		});
		btnPdelete = new JButton(new ImageIcon("image/icon\\edelete.png"));
		btnPdelete.setBounds(140, 400, 95, 40);
		btnPdelete.setContentAreaFilled(false);
		btnPdelete.setBorderPainted(true);
		plist.add(btnPdelete);
		btnPdelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				String sql = "DELETE FROM customer WHERE id = " +d;
				DBConnect di = new DBConnect();
				di.delete(sql);
				populateTable();
			}
		});
	
		
		JLabel labelproduct = new JLabel();
		labelproduct.setIcon(new ImageIcon("image\\loginBackground.jpg"));
		labelproduct.setBounds(0, 0, 850, 500);
		plist.add(labelproduct);
		
		plist.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		plist.getContentPane().setLayout(null);
		
	}

	private void populateTable() {
		BillInfoTable model = new BillInfoTable((String)ptype.getSelectedItem(),searchProducts.getText());
		ptable.setModel(model);
		
	}
	public static BillInfo getObj() {
		if(obj==null)
			obj = new BillInfo();
		return obj;
		}
}
