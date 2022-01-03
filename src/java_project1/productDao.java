package java_project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class productDao {
	int i;
	PreparedStatement pstate;
	public void createProduct(List<product> prodlist)
	{
		Connection con=dbConnection.getConnection();
		for(product p:prodlist)
		{
			try {
				pstate=con.prepareStatement("insert into product values(?,?,?,?)");
				pstate.setInt(1, p.getProdId());
				pstate.setString(2, p.getProdName());
				pstate.setDouble(3, p.getProdPrice());
				pstate.setInt(4, p.getStock());
				i=pstate.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(i>0)
		{
			System.out.println("product Saved SUCCESSFULLY...!!");
		}
		else
		{
			System.out.println("Product not saved...!!");
		}
	}
	public void deleteProduct(int prodId)
	{
		Connection con=dbConnection.getConnection();
		try {
			pstate=con.prepareStatement("delete from product where prodId=?");
			pstate.setInt(1, prodId);
			i=pstate.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i>0)
		{
			System.out.println("Product Deleted Successfully...!!");
		}
		else
		{
			System.out.println("Product Not Deleted..!!");
		}
		
	}
	public void updateProductPrice(int prodId,double prodPrice)
	{
		Connection con=dbConnection.getConnection();
		try {
			pstate=con.prepareStatement("update product set prodPrice=? where prodId=?");
			pstate.setDouble(1, prodPrice);
			pstate.setInt(2, prodId);
			i=pstate.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i>0)
		{
			System.out.println("Product Price Updated Successfully...!!");
		}
		else
		{
			System.out.println("Product Price Not Updated..!!");
		}
	}
	public void updateProductStock(int prodId,int stock)
	{
		Connection con=dbConnection.getConnection();
		try {
			pstate=con.prepareStatement("update product set stock=? where prodId=?");
			pstate.setInt(1, stock);
			pstate.setInt(2, prodId);
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i>0)
		{
			System.out.println("\nProduct Stock Updated Successfully...!!");
		}
		else
		{
			System.out.println("\nError..!! Product Stock Not Updated...!!");
		}
	}
	public List<product> searchProduct(int prodId)
	{
		Connection con=dbConnection.getConnection();
		List<product> prodlist=new LinkedList<product>();
		try {
			pstate=con.prepareStatement("select *from product where prodId=?");
			pstate.setInt(1, prodId);
			ResultSet rs=pstate.executeQuery();
			if(rs.next())
			{
				product p=new product(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
				prodlist.add(p);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prodlist;
	}
	public List<product> displayAllProducts()
	{
		Connection con=dbConnection.getConnection();
		List<product> prodlist=new LinkedList<product>();
		String str="select *from product";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(str);
			while(rs.next())
			{
				product p=new product(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
				prodlist.add(p);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prodlist;
	}
}
