/**
* This class represents an exception for when verifyIntegrity method fails.
*/
public class IntegrityCheckFailedException extends Exception{
	/**
	* A constructor with the error message "Authentication Failed!".
	*/
	public IntegrityCheckFailedException(){
		System.out.println("Authentication Failed!");
	}

}
