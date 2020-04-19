import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dataconnection.*;


public class BillInfoTable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"ID","Bill ID","Cus Name","Contact","Address","P_Name","P_Category","Unit","price","Date"};
	private ArrayList<BillList> elist = new ArrayList<BillList>();
	
	public BillInfoTable(String pt, String key) {
		String query = "select * from customer";
		if(!key.equals("") && pt.equals("Bill Id"))
			query = query + " where bill_id = '"+key+"'";
		else if(!key.equals("") && pt.equals("Cus Name"))
			query = query + " where cus_name like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Cus Contact"))
			query = query + " where cus_contact like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Category"))
			query = query + " where p_category like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Date"))
			query = query + " where date = '"+key+"'";
		
		DBConnect da = new DBConnect();
		elist = da.GetBList(query);
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
			BillList u = elist.get(row);
			switch(col)
			{
				case 0:
					return u.id;
				case 1:
					return u.billid;
				case 2:
					return u.name;
				case 3:
					return u.contact;
				case 4:
					return u.address;
				case 5:
					return u.pname;
				case 6:
					return u.pcategory;
				case 7:
					return u.punit;
				case 8:
					return u.price;
				case 9:
					return u.date;
				
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
