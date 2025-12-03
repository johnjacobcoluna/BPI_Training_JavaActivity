/**
 * 
 */
package m1javaactivity;
import java.util.Scanner;
/**
 * 
 */
public class M1_Activity2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your age:");
		
		String age=sc.nextLine();
		
		int ageint=Integer.parseInt(age);
		double agedouble=ageint;
		
		System.out.println("Your Age as int: "+ageint);
		System.out.println("Your Age as dobule: "+agedouble);
		sc.close();
	}

}
