package java_project1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class shoppingMain {

	public static void main(String[] args) {
		AdminUI au=new AdminUI();

		Scanner sc=new Scanner(System.in);
		do {
		System.out.println("-----------------------------------------WELCOME TO SHOPPING APP---------------------------------------------");
		System.out.println("1-Administration\n2-Customer");
		System.out.println("Enter your Choice...");
		int ch=sc.nextInt();
		if(ch==1)
		{
			do 
			{
				System.out.println("1-Add Product\n2-Delete Product\n3-Update Product price\n4-Update stock\n5-search Product\n6-Display Available Products");
				System.out.println("Enter your Choice...");
				int choice=sc.nextInt();
				if(choice==1)
				{
					au.addProduct();
				}
				else if(choice==2)
				{
					au.delete();
				}
				else if(choice==3)
				{
					au.updatePrice();
				}
				else if(choice==4)
				{
					au.updateStock();
				}
				else if(choice==5)
				{
					au.search();
				}
				else if(choice==6)
				{
					au.display();
				}
				else
				{
					System.out.println("Invalid Choice...!!");
				}
				System.out.println("\nDo you want to continue with main menu...type yes");
			}while(sc.next().equals("yes"));

			System.out.println("\nAdmin Log out Successful...!!");

		}
		else if(ch==2)
		{
			CustomerUI cu=new CustomerUI();
			do
			{
				System.out.println("1- Sign Up to Shopping Portal\n2- Log In to Shopping Portal");
				System.out.println("\nEnter Your Choice :");
				int choice2=sc.nextInt();
				if(choice2==1)
				{
					cu.addCustomer();
				}
				else if(choice2==2)
				{
					ResultSet rs=cu.login();
					try {
						if(rs.next())
						{
							System.out.println("Customer Log In Successful...!!");
							do
							{
								System.out.println("\n1-Add products to cart\n2- Display Cart\n3-Generate Bill");
								int ch1=sc.nextInt();
								int demo=rs.getInt(1);
								if(ch1==1)
								{
									au.display();
									cu.addProduct_To_Cart(demo);
								}
								else if(ch1==2)
								{

									cu.displayCart_UI(demo);
								}
								else if(ch1==3)
								{
									cu.bill(demo);
								}
								System.out.println("\nDo you want to continue with customer menu...type yes");
							}while(sc.next().equals("yes"));
						}
						else
						{
							System.out.println("Enter valid customer ID or Password..!!");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("\nDo you want to continue shopping...type yes");
			}while(sc.next().equals("yes"));

			System.out.println("\nCostumer Log Out...!!");
		}
		System.out.println("\nDo you want to continue with Shopping Portal..type yes");
		}while(sc.next().equals("yes"));
	}
}
