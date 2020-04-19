package dataconnection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;





public class DBConnect {
	private Connection con;
	private Statement stat;
	private ResultSet res;
	
	public DBConnect() {
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/supershop","root","");
			stat = (Statement) con.createStatement();
		}
		catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error in Establashing connection with Database");
		}
	}

	public int login(String name, String pass) {
		String sql = "SELECT * FROM `login` WHERE username='"+name+"' and password='"+pass+"'";
		try {
			res = stat.executeQuery(sql);
			if(res.next()) {
				if(res.getString("type").equals("admin")) {
					return 1;
				}
				else if(res.getString("type").equals("salesman")) {
					return 2;
				}
				
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		return -1;
	}
	
	
	
	public ArrayList<PList> GetPList(String sql){
		ArrayList<PList> list = new ArrayList<PList>();
		
		try{
			res = stat.executeQuery(sql);
			while(res.next())
			{
				PList u = new PList();
				u.pid=res.getInt("p_id");
				u.pname=res.getString("p_name");
				u.category=res.getString("category");
				u.price=res.getInt("p_price");
				u.unit=res.getString("p_unit");
				u.stock=res.getInt("stock");
				list.add(u);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public ArrayList<EList> GetEList(String sql){
		ArrayList<EList> list = new ArrayList<EList>();
		
		try{
			res = stat.executeQuery(sql);
			while(res.next())
			{
				EList u = new EList();
				u.eid=res.getInt("eid");
				u.name=res.getString("name");
				u.contact=res.getString("contact");
				u.address=res.getString("address");
				u.post=res.getString("post");
				u.join=res.getString("djoin");
				u.salary=res.getInt("salary");
				list.add(u);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<PList> GetParchaseList(String sql){
		ArrayList<PList> list = new ArrayList<PList>();
		
		try{
			res = stat.executeQuery(sql);
			while(res.next())
			{
				PList u = new PList();
				u.pid=res.getInt("p_id");
				u.pname=res.getString("p_name");
				u.category=res.getString("category");
				u.price=res.getInt("p_price");
				u.unit=res.getString("p_unit");
				//u.stock=res.getInt("stock");
				list.add(u);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<PList> getCusList(String sql){
		ArrayList<PList> list = new ArrayList<PList>();
		
		try{
			res = stat.executeQuery(sql);
			while(res.next())
			{
				PList u = new PList();
				u.pname=res.getString("p_name");
				u.category=res.getString("p_category");
				u.price=res.getInt("p_price");
				u.unit=res.getString("p_unit");
				//u.stock=res.getInt("stock");
				list.add(u);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	

	public void insert(String sql) {
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int feed(String sql) {
		int i=0;
		try {
			res = stat.executeQuery(sql);
			if(res.next()) {
				i = res.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	
	public String getName(String sql) {
		String i="User";
		try {
			res = stat.executeQuery(sql);
			if(res.next()) {
				i = res.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public void delete(String sql) {
		
			//int dialogbx = JOptionPane.showConfirmDialog (null, "Are You Sure to Delete?","Delete",dialogbx);
			//JOptionPane.showConfirmDialog (null, "Are You Sure to Delete?","Delete",dialogbx);
			if(JOptionPane.showConfirmDialog (null, "Are You Sure to Delete?","Delete",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					stat.executeUpdate(sql);
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				
			} 
	}

	public ArrayList<User> GetUsers(String sql) {
ArrayList<User> list = new ArrayList<User>();
		
		try{
			res = stat.executeQuery(sql);
			while(res.next())
			{
				User u = new User();
				u.id=res.getInt("id");
				u.name=res.getString("name");
				u.username=res.getString("username");
				u.password=res.getString("password");
				u.type=res.getString("type");
				list.add(u);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<BillList> GetBList(String query) {
ArrayList<BillList> list = new ArrayList<BillList>();
		
		try{
			res = stat.executeQuery(query);
			while(res.next())
			{
				BillList u = new BillList();
				u.id=res.getInt("id");
				u.billid=res.getInt("bill_id");
				u.name=res.getString("cus_name");
				u.contact=res.getString("cus_contact");
				u.address=res.getString("cus_address");
				u.pname=res.getString("p_name");
				u.pcategory=res.getString("p_category");
				u.punit=res.getString("p_unit");
				u.price=res.getInt("p_price");
				u.date=res.getString("date");
				list.add(u);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	
}
