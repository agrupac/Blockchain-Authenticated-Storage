/**
* This class represents an exception for a missing file in AuthDLList.
*/
public class FileNotFoundException extends Exception{
	/**
	* A constructor with the error message "File Not Found!".
	*/
	public FileNotFoundException(){
		System.out.println("File Not Found!");
	}

}
