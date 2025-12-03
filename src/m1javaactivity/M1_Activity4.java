/**
 * 
 */
package m1javaactivity;
import java.util.Scanner;
/**
 * 
 */
public class M1_Activity4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter your age:");
		int age=sc.nextInt();
		
		if (age <18)
		{
			System.out.print("Minor");
		}
		else if (age >= 18 && age <=59)
		{
			System.out.print("Adult");
		}
		else
			System.out.print("Senior");
		
		sc.close();
	}

}
