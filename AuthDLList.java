/**
* This class represents a doubly-linked list.
*
* @author Aidan Grupac
*/
public class AuthDLList{
	/**
	* The initial digest string, as there is no previous digest for the first node to use.
	*/
	public static final String startDigest = "123456789";
	/**
	* The first node in the list.
	*/
	public Node head;
	/**
	* The last node in the list.
	*/
	public Node tail;
	/**
	* Checks the integrity of the list by confirming that each node's digest has not been changed.
	*
	* @param currentList the list to verify.
	* @param check the proofCheck to compare with the final digest.
	* @return true if checks succeed.
	* @throws IntegrityCheckFailedException if any check fails.
	*/
	public static boolean verifyIntegrity(AuthDLList currentList, String check) throws IntegrityCheckFailedException {

		Node currNode = currentList.head;
		boolean initial = true;
		while(currNode != null){

			if(initial){
				String hash = Hashing.cryptHash(AuthDLList.startDigest + "&" + currNode.file);
				if(!currNode.digest.equals(hash)) throw new IntegrityCheckFailedException();
				initial = false;
				currNode = currNode.next;

			}else if(currNode == currentList.tail){
				if(!currNode.digest.equals(check)) throw new IntegrityCheckFailedException();
				currNode = currNode.next;

			}else{
				String hash = Hashing.cryptHash(currNode.previous.digest + "&" + currNode.file);
				if(!currNode.digest.equals(hash)) throw new IntegrityCheckFailedException();
				currNode = currNode.next;
			}
		}
		return true;
	}
	/**
	* Appends a node to the end of the list.
	*
	* @param data the file associated with the node.
	* @param check the proofCheck to verify the list with.
	* @return tail.digest the new proofCheck.
	* @throws IntegrityCheckFailedException if verifyIntegrity fails.
	*/
	public String insertFileNode(String data, String check) throws IntegrityCheckFailedException {

		verifyIntegrity(this, check);

		Node newNode = new Node();
		newNode.file = data;
		//if list is empty
		if(head == null){
			head = newNode;
			tail = head;
			newNode.previous = null;
			newNode.next = null;
			newNode.digest = Hashing.cryptHash(startDigest + "&" + data);
		}else{
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
			newNode.next = null;
			newNode.digest = Hashing.cryptHash(tail.previous.digest + "&" + data);
		}

		return tail.digest;
	}
	/**
	* Removes the first node in the list.
	*
	* @param check the proofCheck to verify the list with.
	* @return null if list contained only one node, otherwise tail.digest the new proofCheck.
	* @throws IntegrityCheckFailedException if verifyIntegrity fails.
	* @throws EmptyDLListException if list is empty.
	*/
	public String deleteFirstFile(String check) throws IntegrityCheckFailedException, EmptyDLListException {

		if(head == null && tail == null) throw new EmptyDLListException();

		verifyIntegrity(this, check);

		Node temp = head;
		//single element list
		if(head == tail){
			head = null;
			tail = null;
			return null;
		}
		else{
			head.next.previous = null;
			head = head.next;
		}

		Node currNode = head;
		boolean initial = true;
		while(currNode != null){

			if(initial){
				currNode.digest = Hashing.cryptHash(AuthDLList.startDigest + "&" + currNode.file);
				initial = false;
				currNode = currNode.next;
			}else{
				currNode.digest = Hashing.cryptHash(currNode.previous.digest + "&" + currNode.file);
				currNode = currNode.next;
			}
		}

		return tail.digest;
	}
	/**
	* Removes the last node in the list.
	*
	* @param check the proofCheck to verify the list with.
	* @return null if list contained only one node, otherwise tail.digest the new proofCheck.
	* @throws IntegrityCheckFailedException if verifyIntegrity fails.
	* @throws EmptyDLListException if list is empty.
	*/
	public String deleteLastFile(String check) throws IntegrityCheckFailedException, EmptyDLListException {

		if(head == null && tail == null) throw new EmptyDLListException();

		verifyIntegrity(this, check);

		Node temp = head;
		//single element list
		if(head == tail){
			head = null;
			tail = null;
			return null;
		}
		else{
			tail.previous.next = null;
			tail = tail.previous;
		}

		Node currNode = head;
		boolean initial = true;
		while(currNode != null){

			if(initial){
				currNode.digest = Hashing.cryptHash(AuthDLList.startDigest + "&" + currNode.file);
				initial = false;
				currNode = currNode.next;
			}else{
				currNode.digest = Hashing.cryptHash(currNode.previous.digest + "&" + currNode.file);
				currNode = currNode.next;
			}
		}

		return tail.digest;
	}
	/**
	* Retrieves a node from the list based on given file.
	*
	* @param current the list to search.
	* @param check the proofCheck to verify the list with.
	* @param file the file of the node to search for.
	* @return currNode the found node.
	* @throws IntegrityCheckFailedException if verifyIntegrity fails.
	* @throws FileNotFoundException if the file's node is not in the list.
	*/
	public static Node retrieveNodeFile(AuthDLList current, String check, String file) throws IntegrityCheckFailedException, FileNotFoundException{

		verifyIntegrity(current, check);

		Node currNode = current.head;
		while(currNode != null){

			if(currNode.file == file){
				return currNode;
			}
			currNode = currNode.next;
		}

		throw new FileNotFoundException();
	}

}
