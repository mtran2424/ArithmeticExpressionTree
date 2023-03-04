/**
 * Representing a node with two child nodes and storing some
 * data
 * @author My Tran
 * @version 1.0
 */
class Node
{
	//Represents the element of data being stored into the node
	Key element;
	
	//variables for the left and right children of the current node
	Node leftNode, rightNode;
	

	/**
	 * Default Constructor
	 * Constructs a node with an element of a default value.
	 */
	public Node()
	{
		element = new Key();
	}
	

	/**
	 * Non-default Constructor
	 * Constructs a node with an element set to a value specified by the caller.
	 * @param value Value of element given as a String
	 */
	public Node(String value)
	{
		element = new Key();
		element.setElement(value);
	}
	
	/**
	 * Method
	 * Returns the current object.
	 * @return current node
	 */
	public Node getNode()
	{
		return this;
	}//end of getNode
	
	/**
	 * Method
	 * Sets the element of the node to a specified value.
	 * @param value what the element is to be set to
	 */
	public void setNode(Key value)
	{
		element.setElement(value.toString());
	}//end of setNode
	
	/**
	 * Method
	 * Returns the element stored in the Node as a string.
	 * @return the value of the element to the caller
	 */@Override
	public String toString()
	{
		return element.toString();
	}//end of overridden toString



	/**
	 * Method
	 * Determines if two nodes are equal. Two nodes are equal if their key elements are equal.
	 * @param otherNode
	 * @return true if the nodes are the same... false otherwise
	 */@Override
	public boolean equals(Object o)
	{
		//if the value in other node is the same as this node, return true
		//false otherwise
		if(this.toString().equals(o.toString()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}//end of overridden equals
	
}