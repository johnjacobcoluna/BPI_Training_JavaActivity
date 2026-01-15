package M4_Activity6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M4_Activity6 {

	@FunctionalInterface
	interface BankTestOperation {
		void execute() throws InvalidAmountException, InsufficientFundsException;
	}

	private static final Logger logger = LoggerFactory.getLogger(M4_Activity6.class);

	public static void runTest(BankTestOperation operation, String operationName) {
		try {
			operation.execute();
		} catch (InvalidAmountException e) {
			logger.error("{} failed: {}", operationName, e.getMessage());
			e.printStackTrace(); // to print the stack trace exactly as in expected output
		} catch (InsufficientFundsException e) {
			logger.error("{} failed: {}", operationName, e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BankAccount account = new BankAccount();

		// Sequence and amounts exactly as per the expected output
		runTest(() -> account.deposit(5000), "Deposit");
		runTest(() -> account.withdraw(3000), "Withdrawal");
		runTest(() -> account.deposit(-500), "Deposit");
		runTest(() -> account.withdraw(20000), "Withdrawal");
		runTest(() -> account.deposit(60000), "Deposit");
	}
}
