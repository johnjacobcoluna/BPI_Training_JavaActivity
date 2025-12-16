package M3_Activity;

import java.util.HashSet;
//import java.util.LinkedHashSet;
import java.util.Scanner;

public class M3_Activity2 {

	private HashSet<String> list=new HashSet<>();
	private String findproduct;
	//private LinkedHashSet<String> list=new LinkedHashSet<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int option;
		M3_Activity2 product=new M3_Activity2();
		product.productlist();
		do 
		{
			System.out.println("======== Select an Option ========");
			System.out.println("| 1 - Search Product              |");
			System.out.println("| 2 - Add a product               |");
			System.out.println("| 3 - Print all products and count|");
			System.out.println("| 4 - Exit                        |");
			System.out.println("==================================");
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
		        	System.out.println("Exiting");
		        	System.exit(0);
		        default:
		            System.out.println("Invalid option! Please try again.\n");
				}
		}
		while (option != 4);
			sc.close();
			System.exit(0);
	}
	public void productlist()
	{
		list.add("Laptop");
		list.add("Mouse");
		list.add("Keyboard");
		list.add("Printer");
		list.add("Monitor");
	}
	public void countproductList()
	{

		int i=1;
		
		System.out.println("All Products:");
		for(String list1:list)
		{	
			System.out.println(i+". "+list1);
			i++;
		}
		System.out.println("Total Count of Products:" +list.size());
	}
	
	public void searchproductList(Scanner sc)
	{
		System.out.print("\nEnter product name to search:");
		findproduct=sc.next();

		boolean found = false;
        
        for (String product : list) {
            if (product.equalsIgnoreCase(findproduct)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Product found: " + findproduct);
        } else {
            System.out.println("Product not exist");
        }

	}
	public void addproduct(Scanner sc)
	{
		System.out.print("\nEnter product name you want to add:");
		String addproduct=sc.next();

		 if (list.add(addproduct))
		 {
             System.out.println("Product added: " + addproduct);
         } 
		 else 
         {
             System.out.println("Product already exists.");
         }
	}
}
