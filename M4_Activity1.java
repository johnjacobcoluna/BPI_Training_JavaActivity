
package M4_Activity;
import java.util.Scanner;
public class M4_Activity1 extends BankAccount{

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		BankAccount bank=new BankAccount();
		System.out.println("=== Bank Account Name Display ===");

		testCase("ACC-001");
		//testCase("ACC-002");
		testCase("ACC-999");

		System.out.println("\n=== Program completed successfully! ===");
	}

}
