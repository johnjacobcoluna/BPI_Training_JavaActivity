package M4_Activity;

public class ATMSystem {

	static double[] accounts = { 10000, 15000, 20000 };

	public static void processWithdrawal(String accountIndex, String amountInput) {

		System.out.println("Account=" + accountIndex + ", Amount=" + amountInput);
		try {
			int index = Integer.parseInt(accountIndex);
			double amount = Double.parseDouble(amountInput);

			double balance = accounts[index];

			System.out.printf("Current balance: P%.2f%n", balance);
			System.out.printf("Withdrawal: P%.2f%n", amount);

			if (amount > balance) {
				System.out.println("Insufficient funds! Cannot withdraw P" + amount);
			} else {
				balance -= amount;
				accounts[index - 1] = balance;
				System.out.printf("New balance: P%.2f%n", balance);
				System.out.println("Withdrawal successful!");
			}
		} catch (NumberFormatException e) {
			System.err.println("Error: Invalid input!");
			System.out.println("Please enter valid numbers.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error: Account not found!");
			System.out.println("Invalid account index.");
		} catch (Exception e) {
			System.err.println("Transaction failed! Unexpected error occurred.");
		}
		System.out.println();

	}
}
