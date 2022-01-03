package java_project1;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {
	Scanner sc=new Scanner(System.in);
	customerDao cd=new customerDao();
	public void addCustomer()
	{
		List<customer> custlist=new LinkedList<customer>();
		System.out.println("Enter customer ID ");
		int custId=sc.nextInt();
		System.out.println("Enter customer Name");
		String custName=sc.next();
		System.out.println("Enter mobile Number of Customer");
		String mobNo=sc.next();
		if(cd.isValid(mobNo))
		{
			System.out.println("Enter Password to create Shopping Account :");
			String password=sc.next();
			customer c=new customer(custId, custName, mobNo, password);
			custlist.add(c);
			cd.createCustomer(custlist);
		}
		else
		{
			System.out.println("Enter Valid Mobile Number...!!");
		}
		
	}
	public ResultSet login()
	{
		System.out.println("\nEnter Customer ID :");
		int custId=sc.nextInt();
		System.out.println("Enter Your Password :");
		String password=sc.next();
		ResultSet rs=cd.createLogIn(custId, password);
		return rs;
	}
	public void addProduct_To_Cart(int demo)
	{
		do
		{
			System.out.println("\nEnter product ID to add product in cart :  ");
			int productNo=sc.nextInt();
			
			System.out.println("\nEnter Quantity of Product :");
			int prodQty=sc.nextInt();
			if(prodQty==0)
			{
				System.out.println("\nQuantity Should Be Greater Than 0");
				return;
			}
			cart c=new cart(productNo,prodQty);
			cd.create_cart(c,demo);
			System.out.println("\nDo you want add  more products in cart...type YES");
		}while(sc.next().equals("yes"));
	}
	public void displayCart_UI(int demo)
	{
		System.out.println("====================================Customer Cart=====================================");
		cd.display_cart(demo);
	}
	public void bill(int demo)
	{
		bill b=cd.calculate(demo);
		cd.createBill(b,demo);
		cd.displayBill(demo);
	}
}
