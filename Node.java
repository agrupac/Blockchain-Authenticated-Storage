/**
* This class represents a node in the AuthDLList.
*/
public class Node{
	/**
	* The data associated with a node.
	*/
	public String file;
	/**
	* A pointer to the previous node in the list.
	*/
	public Node previous;
	/**
	* A pointer to the next node in the list.
	*/
	public Node next;
	/**
	* The unique fingerprint of each node.
	*/
	public String digest;

}
