package M4_Activity;

public class M4_Activity3 extends ATMTransaction{

	public static void main(String[] args) {

		System.out.println("=== ATM BALANCE INQUIRY SYSTEM ===\n");

        System.out.println("--- Test Case 1: Valid Savings Account ---");
        checkBalance("100123456", 15000.00);

        System.out.println("--- Test Case 2: Valid Checking Account ---");
        checkBalance("200987654", 25000.00);

        System.out.println("--- Test Case 3: Invalid Account Number Format ---");
        checkBalance("ABC12345", 15000.00);

        System.out.println("--- Test Case 4: Empty Account Number ---");
        checkBalance("", 15000.00);

	}

}
