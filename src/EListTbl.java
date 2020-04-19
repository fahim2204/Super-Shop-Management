import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dataconnection.*;


public class EListTbl extends AbstractTableModel {
	
	
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"EID","Name","Contact","Address","post"};
	private ArrayList<EList> elist = new ArrayList<EList>();
	
	public EListTbl(String pt, String key) {
		String query = "select * from employee";
		if(!key.equals("") && pt.equals("Name"))
			query = query + " where name like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Contact"))
			query = query + " where contact like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Address"))
			query = query + " where address like '%"+key+"%'";
		
		DBConnect da = new DBConnect();
		elist = da.GetEList(query);
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
		return elist.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(elist.size()==0)
			return null;
		
		
		try
		{
			EList u = elist.get(row);
			switch(col)
			{
				case 0:
					return u.eid;
				case 1:
					return u.name;
				case 2:
					return u.contact;
				case 3:
					return u.address;
				case 4:
					return u.post;
				
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
