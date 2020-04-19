import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dataconnection.*;


public class PurchaseList extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"PID","Product Name","Category","Price","unit"};
	private ArrayList<PList> parchaselist = new ArrayList<PList>();
	
	public PurchaseList(String pt, String key) {
		String query = "select * from productlist";
		if(!key.equals("") && pt.equals("Product Id"))
			query = query + " where p_id like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Product Name"))
			query = query + " where p_name like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Product Category"))
			query = query + " where category like '%"+key+"%'";
		
		DBConnect da = new DBConnect();
		parchaselist = da.GetParchaseList(query);
	}

	public int getColumnCount() {
		return colNames.length;
	}
	
	public String getColumnName(int col) {
		return colNames[col];
	}


	public int getRowCount() {
		return parchaselist.size();
	}
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(parchaselist.size()==0)
			return null;
		
		
		try
		{
			PList u = parchaselist.get(row);
			switch(col)
			{
				case 0:
					return u.pid;
				case 1:
					return u.pname;
				case 2:
					return u.category;
				case 3:
					return u.price;
				case 4:
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

