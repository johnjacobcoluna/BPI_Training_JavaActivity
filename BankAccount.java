package M4_Activity;

public class BankAccount {

	public static String getAccountName(String accountNumber) {
		if (accountNumber.equals("ACC-001")) {
			return "Juan Dela Cruz";
		} else if (accountNumber.equals("ACC-002")) {
			return "Maria Santos";
		} else {
			return null; // Account not found
		}
	}

	public static void testCase(String accountNumber) {
		System.out.println("\nLooking up account: " + accountNumber);
		try {

			String name = getAccountName(accountNumber);
			String upperName = name.toUpperCase();
			System.out.println("Account holder: " + upperName);

		} catch (NullPointerException e) {
			System.err.println("Error: Account not found!");
		}
	}

}