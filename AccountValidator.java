package M4_Activity;

public class AccountValidator {
	public void validateAccountNumber(String accountNumber) throws Exception {
		if (accountNumber == null) {
			throw new NullPointerException("Cannot be null");
		}
		if (accountNumber.length() != 10) {
			throw new Exception("Must be 10 digits");
		}

		System.out.println("Valid account: " + accountNumber);
	}

}
