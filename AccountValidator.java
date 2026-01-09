package M4_Activity5;

public class AccountValidator {
	public void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
		if (accountNumber == null) {
			throw new NullPointerException("Account number cannot be null");
		}

		if (isDigit(accountNumber) == false) {
			throw new InvalidAccountNumberException("Account number must contain only digits");
		}

		if (accountNumber.length() != 10) {
			throw new InvalidAccountNumberException("Account number must be exactly 10 digits");
		}

		System.out.println("Valid account: " + accountNumber);
	}

	public void testValidation(String testName, String accountNumber) {
		System.out.println("\n" + testName);
		try {
			validateAccountNumber(accountNumber);
		} catch (InvalidAccountNumberException e) {
			System.out.println("Warning: " + e.getMessage());
		} catch (InvalidAccountFormatException e) {
			System.out.println("Warning: " + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Warning: " + e.getMessage());
		}

	}

	public boolean isDigit(String accountNumber) {
		for (char c : accountNumber.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}
