import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dataconnection.*;


public class CustomerList extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"Product Name","Category","Price","unit"};
	private ArrayList<PList> cuslist = new ArrayList<PList>();
	
	
	public CustomerList(int key) {
		String query = "select * from customer where bill_id="+key;	
		DBConnect da = new DBConnect();
		cuslist = da.getCusList(query);
	}
	

	public int getColumnCount() {
		return colNames.length;
	}
	
	public String getColumnName(int col) {
		return colNames[col];
	}


	public int getRowCount() {
		return cuslist.size();
	}
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(cuslist.size()==0)
			return null;
		
		
		try
		{
			PList u = cuslist.get(row);
			switch(col)
			{
				case 0:
					return u.pname;
				case 1:
					return u.category;
				case 2:
					return u.price;
				case 3:
					return u.unit;
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

