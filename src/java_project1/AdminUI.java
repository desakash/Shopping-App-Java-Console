package java_project1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdminUI {
	Scanner sc=new Scanner(System.in);
	productDao pd=new productDao();

	public void	addProduct() 
	{
		List<product> prodlist=new LinkedList<product>();
		do {
			System.out.println("Enter product ID");
			int prodId=sc.nextInt();
			System.out.println("Enter product name :");
			String prodName=sc.next();
			System.out.println("Enter product Price :");
			double prodPrice=sc.nextDouble();
			System.out.println("Enter Available Stock :");
			int stock=sc.nextInt();
			product p=new product(prodId, prodName, prodPrice,stock);
			prodlist.add(p);
			System.out.println("\nDo you want add  more products...type YES");
		}while(sc.next().equals("yes"));
		pd.createProduct(prodlist);
	}
	public void delete()
	{
		System.out.println("\nEnter product ID to delete Product :");
		int prodId=sc.nextInt();
		pd.deleteProduct(prodId);
	}
	public void updatePrice()
	{
		System.out.println("Enter Product ID and New Price to Update product :");
		int prodId=sc.nextInt();
		double prodPrice=sc.nextDouble();
		pd.updateProductPrice(prodId, prodPrice);
	}
	public void updateStock()
	{
		System.out.println("Enter Product ID and New Stock to Update :");
		int prodId=sc.nextInt();
		int stock=sc.nextInt();
		pd.updateProductStock(prodId, stock);
	}
	public void search()
	{
		System.out.println("\nEnter product ID to search :");
		int prodId=sc.nextInt();
		List<product> prodlist=pd.searchProduct(prodId);
		System.out.println("\n========================Product Details===========================");
		for(product p:prodlist)
		{
			System.out.println("\n\t\t"+p.getProdId()+"\t"+p.getProdName()+"\t"+p.getProdPrice()+"\t"+p.getStock());
		}
		System.out.println("==================================================================================");
		
	}
	public void display()
	{
		List<product> prodlist=pd.displayAllProducts();
		System.out.println("\n===================Available Products In Shoppinhg Mall=====================");
		for(product p:prodlist)
		{
			System.out.println("\n\t\t\t"+p.getProdId()+"\t"+p.getProdName()+"\t"+p.getProdPrice()+"\t\t"+p.getStock());
		}
		System.out.println("\n==================================================================================");
	}
}
