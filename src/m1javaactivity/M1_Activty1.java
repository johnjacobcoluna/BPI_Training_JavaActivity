package m1javaactivity;
import java.util.Scanner;
public class M1_Activty1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 
		Scanner sc=new Scanner(System.in);
		System.out.println("What is your name?");
		String name=sc.nextLine();
		System.out.println("Hello "+name);
		sc.close();
	}

}
