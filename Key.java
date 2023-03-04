/**
 * Representing a datatype for general use. Hides datatype in class.
 * @author My Tran
 * @version 1.0
 */
public class Key 
{
	//Data member of class key
	String element; 
	
	/**
	 * Method
	 * Sets the element of the key object to specified value.
	 * @param value value the element is to be given
	 */
	public void setElement(String value)
	{
		element = value;
	}//end of setElement
	

	/**
	 * Method
	 * Returns element of the key object
	 * @return value of the element
	 */
	public String toString()
	{
		return element;
	}//end of toString
}
