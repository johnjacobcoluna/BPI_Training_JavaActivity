package M3_Activity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class M3_Activity3 {
	
	Map<String, Integer> list=new LinkedHashMap<>();
	private String findproduct;
	private Integer price;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int option;
		M3_Activity3 product=new M3_Activity3();
		
		product.productlist();
		
		do 
		{
			System.out.println("======== Select an Option =========");
			System.out.println("| 1 - Search Product               |");
			System.out.println("| 2 - Add a product                |");
			System.out.println("| 3 - Print all products and prices|");
			System.out.println("| 4 - Find the cheapest product    |");
			System.out.println("| 5 - Exit                         |");
			System.out.println("===================================");
			System.out.print("Enter Choice:");
		
				option = sc.nextInt();
				
				switch (option)
				{
				case 1:
					product.searchproductList(sc);
					break;
		        case 2:
		        	product.addproduct(sc);
		        	break;
		        case 3:
		        	product.countproductList();
		        	break;
		        case 4:
		        	product.findcheapestproduct();
		        	break;
		        case 5:
		        	System.out.println("Exiting");
		        	System.exit(0);
		        default:
		            System.out.println("Invalid option! Please try again.\n");
				}
		}
		while (option != 5);
			sc.close();
			System.exit(0);

	}
	public void productlist()
	{
		list.put("Laptop", 50000);
		list.put("Mouse", 7000);
		list.put("Keyboard", 500);
		list.put("Printer",1000);
		list.put("Monitor",600);
		list.put("Speaker",200);
	}
	public void countproductList()
	{
		int i=1;
		
		System.out.println("All Products:");

		for (Map.Entry<String, Integer> list1 : list.entrySet()) 
		{
			System.out.println(i + ". " + list1.getKey() + " - " + list1.getValue());
			i++;
		}
			System.out.println("Total Products:" +list.size());
	}
	
	public void searchproductList(Scanner sc)
	{
		System.out.print("\nEnter product name to search:");
		findproduct=sc.next();
        
        for (Map.Entry<String, Integer> list1 : list.entrySet()) 
        {
	        list.put(list1.getKey(), list1.getValue());
			price = list.get(findproduct);

        }
			if (price != null) 
			{
			    System.out.println("Product found: " + findproduct);
			    System.out.println("Price: " + price);
			} 
			
			else 
			{
			    System.out.println("Product not exist");
			}
	}
	public void addproduct(Scanner sc)
	{
		System.out.print("\nEnter product name you want to add:");
		String addproduct=sc.next();
		System.out.print("\nEnter price:");
		int productprice=sc.nextInt();
		
		 if (list.put(addproduct, productprice) != null)
		 {
			 System.out.println("Product already exists.");
             
         } 
		 else 
         {
			 System.out.println("Product added: " + addproduct);
         }
	}
	public void findcheapestproduct()
	{

		String productname = null;
        int lowestprice = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> list1 : list.entrySet()) {
            if (list1.getValue() < lowestprice) {
            	lowestprice = list1.getValue();
            	productname = list1.getKey();
            }
        }
        System.out.println("Cheapest product: " +productname + " - " + lowestprice);

	}
}
