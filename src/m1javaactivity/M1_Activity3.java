/**
 * 
 */
package m1javaactivity;
import java.util.Scanner;
/**
 * 
 */
public class M1_Activity3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter first integer:");
		int firstint=sc.nextInt();
		System.out.print("Enter second integer:");
		int secint=sc.nextInt();
		
		int sum = SumNumber(firstint, secint);
		int diff = DiffNumber(firstint, secint);
		int prod = ProdNumber(firstint, secint);
		
		System.out.println("Sum:" +sum);
		System.out.println("Difference:" +diff);
		System.out.println("Product:" +prod);
		
		sc.close();
	}
	public static int SumNumber(int num1, int num2)
	{
		return num1 + num2;
	}
	 
	public static int DiffNumber(int num1, int num2)
	{
		return num1 - num2;
	}
	 
	public static int ProdNumber(int num1, int num2)
	{
		return num1 * num2;
	}
}
