package M4_Activity;

public class M4_Activity4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AccountValidator validate = new AccountValidator();
		String[] cases = { "1234567890", // valid
				"123", // too short
				null // null
		};

		for (String accountNumber : cases) {
			try {
				validate.validateAccountNumber(accountNumber);
			} catch (NullPointerException npe) {
				System.out.println("Error: " + npe.getMessage());
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

	}

}
