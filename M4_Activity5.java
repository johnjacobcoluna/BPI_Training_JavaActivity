package M4_Activity5;

public class M4_Activity5{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        AccountValidator validate = new AccountValidator();

        System.out.println("=== Account Number Validation Test ===");

        validate.testValidation("Test 1: Valid account (1234567890)", "1234567890");
        validate.testValidation("Test 2: Too short (123)", "123");
        validate.testValidation("Test 3: Contains letters (123ABC7890)", "123ABC7890");
        validate.testValidation("Test 4: Contains space (1234 567890)", "1234 567890");
        validate.testValidation("Test 5: Null value", null);

	}

}
