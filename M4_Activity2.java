package M4_Activity;

public class M4_Activity2 extends ATMSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== ATM Withdrawal System ===\n");

		System.out.println("--- Test 1: Valid Withdrawal ---");
		processWithdrawal("1", "5000");

		System.out.println("--- Test 2: Invalid Account Index ---");
		processWithdrawal("abc", "5000");

		System.out.println("--- Test 3: Account Not Found ---");
		processWithdrawal("10", "5000");

		System.out.println("--- Test 4: Insufficient Funds ---");
		processWithdrawal("1", "20000");

		System.out.println("=== All tests completed! ===");
	}

}
