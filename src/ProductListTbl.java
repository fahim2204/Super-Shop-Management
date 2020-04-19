import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dataconnection.*;


public class ProductListTbl extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"PID","Product Name","Category","Price","unit","stock"};
	private ArrayList<PList> plist = new ArrayList<PList>();
	
	public ProductListTbl(String pt, String key) {
		String query = "select * from productlist";
		if(!key.equals("") && pt.equals("Product Id"))
			query = query + " where p_id like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Product Name"))
			query = query + " where p_name like '%"+key+"%'";
		else if(!key.equals("") && pt.equals("Product Category"))
			query = query + " where category like '%"+key+"%'";
		
		DBConnect da = new DBConnect();
		plist = da.GetPList(query);
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
		return plist.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(plist.size()==0)
			return null;
		
		
		try
		{
			PList u = plist.get(row);
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
				case 5:
					return u.stock;
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
