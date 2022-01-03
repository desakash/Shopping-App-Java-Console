package java_project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class customerDao {
	int i;
	PreparedStatement pstate;
	public void createCustomer(List<customer> custlist)
	{
		Connection con=dbConnection.getConnection();
		for(customer c:custlist)
		{
			try {
				pstate=con.prepareStatement("insert into customer values(?,?,?,?)");
				pstate.setInt(1, c.getCustId());
				pstate.setString(2, c.getCustName());
				pstate.setString(3, c.getMobNo());
				pstate.setString(4, c.getPassword());
				i=pstate.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i>0)
			{
				System.out.println("\nCustomer Sign Up Successful...!!");
			}
			else
			{
				System.out.println("\nCustomer not Signed Up");
			}
		}
	}
	public ResultSet createLogIn(int custId,String password)
	{
		ResultSet rs = null;
		try {
			Connection con=dbConnection.getConnection();
			pstate=con.prepareStatement("select *from customer where custId=? and password=?");
			pstate.setInt(1, custId);
			pstate.setString(2, password);
			rs = pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int checkQty(int productNo,int prodQty)
	{
		int ret=0;
		Connection con=dbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select *from product where prodId=?");
			pstate.setInt(1, productNo);
			ResultSet rs=pstate.executeQuery();
			rs.next();
			if(rs.getInt(4)<prodQty)
			{
				System.out.println("\nSorry...!!This Much Qunatity Is not Available In stock...!!");
				ret=1;
			}
			else
			{
				pstate=con.prepareStatement("update product set stock=? where prodId=?");
				pstate.setInt(1, rs.getInt(4)-prodQty);
				pstate.setInt(2, productNo);
				i=pstate.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public void create_cart(cart c,int demo)
	{
		i=0;
		Connection con=dbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select *from product where prodId=?");
			pstate.setInt(1, c.getProductNo());
			ResultSet rs=pstate.executeQuery();
			if(rs.next()) 
			{
				int ret=checkQty(c.getProductNo(),c.getProdQty());
				if(ret==0)
				{
					String productName=rs.getString(2);
					int prodprice=rs.getInt(3);
					pstate=con.prepareStatement("insert into cart values(?,?,?,?,?)");
					pstate.setInt(1, c.getProductNo());
					pstate.setInt(2, c.getProdQty());
					pstate.setInt(3, prodprice*c.getProdQty());
					pstate.setInt(4, demo);
					pstate.setString(5, productName);
					i=pstate.executeUpdate();
				}
			}
			else
			{
				System.out.println("\nNo product Available for this ID...Enter valid Product ID..!! ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i>0)
		{
			System.out.println("\nProduct added successfully...!!");
		}
		else
		{
			System.out.println("Product Not Added...!!");
		}
	}
	public void display_cart(int demo)
	{
		Connection con=dbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select *from cart where custId=?");
			pstate.setInt(1, demo);
			ResultSet rs=pstate.executeQuery();
			System.out.println("\n\tproduct no\t   product Name\t    product quantity\t\t product price\t\t\tcustomer id");
			System.out.println("\n===========================================================================================");
			while(rs.next())
			{
				System.out.println("\n\t"+rs.getInt(1)+"\t\t\t"+rs.getString(5)+"\t\t\t"+rs.getInt(2)+"\t\t\t"+rs.getInt(3)+"\t\t\t"+rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public bill calculate(int demo)
	{
		Random rd=new Random();
		int total=0;
		double finalTotal=0;
		int billNo=rd.nextInt(10000);
		List<bill> blist=new LinkedList<bill>();
		Connection con=dbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select *from cart where custId=?");
			pstate.setInt(1, demo);
			ResultSet rs=pstate.executeQuery();
			while(rs.next())
			{
				total=total+rs.getInt(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double cgst=total*0.06;
		double sgst=total*0.06;
		finalTotal=total+cgst+sgst;
		bill b=new bill(billNo,cgst, sgst, total, finalTotal);
		//blist.add(b);
		//return blist;
		return b;
	}
	public void createBill(bill b,int demo)
	{
		Connection con=dbConnection.getConnection();

		try {
			pstate=con.prepareStatement("insert into bill values(?,?,?,?,?,?)");
			pstate.setDouble(1, b.getBillNo());
			pstate.setDouble(2, b.getCgst());
			pstate.setDouble(3,b.getSgst());
			pstate.setDouble(4, b.getTotal());
			pstate.setDouble(5, b.getFinalTotal());
			pstate.setInt(6, demo);
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void displayBill(int demo)
	{
		int billNo = 0;
		Connection con=dbConnection.getConnection();

		ResultSet rs;


		try {
			pstate=con.prepareStatement("select *from customer where custId=?");
			pstate.setInt(1, demo);
			rs = pstate.executeQuery();
			rs.next();

			System.out.println("--------------------------------------- CUSTOMER DETAILS-----------------------------------------------------");
			System.out.println("\n");



			System.out.println("\t\t\t\t"+"Customer ID : "+rs.getInt(1));
			System.out.println("\t\t\t\t"+"Customer Name : "+rs.getString(2));
			System.out.println("\t\t\t\t"+"Mobile No. : "+rs.getString(3));

			pstate=con.prepareStatement("select *from bill where custId=?");
			pstate.setInt(1, demo);
			rs=pstate.executeQuery();
			rs.next();
			while(rs.next())
			{
				 billNo=rs.getInt(1);
			}

			System.out.println("  Bill NO :"+billNo);
			System.out.println("==================================================================================================================");
			System.out.println("\n");

			pstate=con.prepareStatement("select *from cart where custId=?");
			pstate.setInt(1, demo);
			rs=pstate.executeQuery();

			System.out.println("\t"+"     product id"+"\t"+    "   product Name"+"\t"+"      Quantity"+"\t"+"        Price");
			System.out.println("------------------------------------------------------------------------------------------------------------------");
			while(rs.next())
			{
				System.out.println("\n");
				System.out.println("\t\t"+rs.getInt(1)+"\t\t"+rs.getString(5)+"\t\t"+rs.getInt(2)+"\t\t"+rs.getInt(3));
			}
			System.out.println("==================================================================================================================");

			pstate=con.prepareStatement("select *from bill where custId=?");
			pstate.setInt(1, demo);
			rs=pstate.executeQuery();
			rs.next();
			System.out.println("\t\t\t\t"+"                   total : "+rs.getDouble(4));

			System.out.println("------------------------------------------------------------------------------------------------------------------");

			System.out.println("\t\t\t\t"+"                   CGST : "+rs.getDouble(2));
			System.out.println("\t\t\t\t"+"                   SGST : "+rs.getDouble(3));

			System.out.println("------------------------------------------------------------------------------------------------------------------");

			System.out.println("\t\t\t\t"+"                   FinalTotal : "+rs.getDouble(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("--------------------------------------THANK YOU COME AGAIN---------------------------------------------------");
	}
	public  boolean isValid(String mobNo)
	{
	     
	    // The given argument to compile() method
	    // is regular expression. With the help of
	    // regular expression we can validate mobile
	    // number.
	    // 1) Begins with 0 or 91
	    // 2) Then contains 7 or 8 or 9.
	    // 3) Then contains 9 digits
	    Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
	 
	    // Pattern class contains matcher() method
	    // to find matching between given number
	    // and regular expression
	    Matcher m = p.matcher(mobNo);
	    return (m.find() && m.group().equals(mobNo));
	}
}
