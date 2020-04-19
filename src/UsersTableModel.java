import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dataconnection.DBConnect;
import dataconnection.User;


public class UsersTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"ID","Name","User Name","Password","Type"};
	private ArrayList<User> users = new ArrayList<User>();
	
	public UsersTableModel(String pt,String key) {
		String query = "select * from login";
		if(!key.equals("") && pt.equals("Name"))
			query = query + " where name like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("User Name"))
			query = query + " where username like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Type"))
			query = query + " where type like '%"+key+"%'";
		
		DBConnect da = new DBConnect();
		users = da.GetUsers(query);
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
	}
	
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return colNames[col];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return users.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(users.size()==0)
			return null;
		
		
		try
		{
			User u = users.get(row);
			switch(col)
			{
				case 0:
					return u.id;
				case 1:
					return u.name;
				case 2:
					return u.username;
				case 3:
					return u.password;
				case 4:
					return u.type;
				default:
					return null;
			}
			
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	

}
