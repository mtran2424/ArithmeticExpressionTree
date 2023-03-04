import java.util.Stack;
/**
 * Representing an algebraic expression made of nodes in the form
 * of a binary tree
 * @author My Tran
 * @version 1.0
 */
class ExpressionTree
{
	/**
	 * The node at the root of the tree
	 */
	private Node root;

	/**
	 * algebraic expression of the tree in infix notation
	 */
	private String expression;
	
	/**
	 * Default Constructor
	 * Constructs an empty Expression tree with an empty node for a root
	 * and blank expression.
	 */
	public ExpressionTree()
	{
		root = new Node();
		expression = "";
	}

	/**
	 * Non-default Constructor
	 * Constructs a binary tree that forms the given algebraic expression
	 * and stores the given expression in class member "expression".
	 * @param infix String containing an algebraic expression
	 */
	public ExpressionTree(String infix)
	{
		//Tree stores infix of algebraic expression in the variable expression
		expression = infix;

		//The expression in postfix is stored piece by piece form individual nodes
		String[] postfixArray = convertToPostfix().split(" ");
		
		//Stores Nodes created from Strings in postfixArray to form tree
		Stack<Node> postfixStack = new Stack<Node>();

		

		//temporary variables are used to new nodes
		Node newNode, parent;

		//when the index is less than the array length, it is still within the bounds
		//of the array and the condition met, so the loop can iterate through the entire array
		for(int index = 0; index < postfixArray.length; index++)
		{
			//check if the entry is an operator
			switch(postfixArray[index])
			{
				case "*":
				case "/":
				case "+":
				case "-":
				//a new node is made from the operator
				//two nodes on the stack are popped to be the children of the parent to form the
				//tree up to that point and is pushed back to be added onto further
					parent = new Node(postfixArray[index]);
					root = parent;
					parent.rightNode = postfixStack.pop();
					parent.leftNode = postfixStack.pop();
					postfixStack.push(parent);
					break;

				default:
				//if the entry is not an operator, it is assumed to be a term so it is pushed
				//to become part of a node storing an operator
					newNode = new Node(postfixArray[index]);
					postfixStack.push(newNode);
					break;

			}
		}

		//after the algorith, the root is the last thing in the stack so pop it off
		postfixStack.pop();
		
	} //end of Non-default constructor


	/**
	 * Method
	 * Gets and returns the infix expression stored in the tree.
	 * @return the infix expression of the tree in a string
	 */
	public String getInfix()
	{
		return expression;
	}//end of GetInfix


	/**
	 * Method
	 * Gets and returns the root node of the tree.
	 * @return Node object reference
	 */
	public Node getRoot()
	{
		return root;
	}//end of GetRoot
	
	
	/**
	 * Method
	 * Converts an expression in infix notation to postfix notation.
	 * @return The postfix of expression data member in a String
	 */
	private String convertToPostfix()
	{
		//A string variable is needed to store the resultant expression
		String postfix = "";
		
		//expression needs to be separated into parts and array is used to access those parts
		String[] expressionArray = expression.replace("(","(#").replace(")","#)").split("[\\s #]");
		//Stack is used to form postfix based on priority of operators
		Stack<String> operationStack = new Stack<String>();
		

		for(int index = 0; index < expressionArray.length; index++)
		{
			switch(expressionArray[index])
			{
				//multiplication and division gets priority over addition and subtraction
				case "*":
				case "/":
				//operators of equal priority get appended to the string and space is appended
				//it be used later as a delimiter to split the string up
					while(!operationStack.isEmpty() && (operationStack.peek() == "*" ||
					operationStack.peek() == "/"))
					{
						postfix += operationStack.pop();
						postfix += " ";
					}
				//push the current operator to be popped later 
					operationStack.push(expressionArray[index]);
					break;
				case "+":
				case "-":
				//operators of higher and lower priority need to be appended to string
					while(!operationStack.isEmpty() && 
					(operationStack.peek() == "-" ||
					operationStack.peek() == "+" || 
					operationStack.peek() == "*" ||
					operationStack.peek() == "/"))
					{
						postfix += operationStack.pop();
						postfix += " ";
					}
					//push the current operator to be popped later 
					operationStack.push(expressionArray[index]);
					break;
				case "(":
				//goes onto stack to indicate when to stop popping when a closing parenthesis is encountered
					operationStack.push(expressionArray[index]);
					break;

				case ")":
				//if a closing parenthesis is encountered, the nested expression is complete so
				//append everythin between the parentheses
					while(!operationStack.peek().equals("("))
					{
						postfix += operationStack.pop();
						postfix += " ";
					}
					//open parenthesis is not needed in resultant 
					operationStack.pop();
					break;

				default:
				//if not operator or parenthesis, it does not need to go on the stack so append to result
					postfix += expressionArray[index];
					postfix += " ";
					break;

			}
		}

		//after algorithm, there may be operators left on the stack that should be popped and appended
		while(!operationStack.isEmpty())
		{
			postfix += operationStack.pop();
			postfix += " ";
		}


		return postfix;

	}//end of ConvertToPostfix

	/**
	 * Method
	 * Initializes variables and calls recursive method. Wrapper method 
	 * for recursive inorder traversal method. 
	 */
	public void inOrderTraverse()
	{
		if(root != null)
		{
			rInOrderTraverse(root);
		}
	}//end of inOrderTraverse

	
	/**
	 * Method
	 * Recursively performs an in-order traversal of the expression tree.
	 * @param currentNode Node the recursive call will be based around
	 */
	private void rInOrderTraverse(Node currentNode)
	{
		if(currentNode.leftNode != null)
		{
			rInOrderTraverse(currentNode.leftNode);
		}

		System.out.print(currentNode.toString()+" ");
		
		if(currentNode.rightNode != null)
		{
			rInOrderTraverse(currentNode.rightNode);
		}
		
	}//end of rInOrderTraverse



	
	/**
	 * Method
	 * Initializes variables and calls recursive method. Wrapper method 
	 * for recursive pre-order traversal method. 
	 */
	public void preOrderTraverse()
	{
		String preOrder = "";

		if(root != null)
		{
			preOrder = rPreOrderTraverse(root, preOrder);
		}

		System.out.println(preOrder);

	} //end of preOrderTraverse



	
	/**
	 * Method
	 * Recursively performs an pre-order traversal of the expression tree.
	 * @param currentNode Node the recursive call will be based around
	 */
	private String rPreOrderTraverse(Node currentNode, String pre)
	{
		pre += currentNode.toString() + " ";
		
		if(currentNode.leftNode != null)
		{
			pre = rPreOrderTraverse(currentNode.leftNode, pre);
		}
		
		if(currentNode.rightNode != null)
		{
			pre = rPreOrderTraverse(currentNode.rightNode, pre);
			
		}

		return pre;
		
	}//end of rPreOrderTraverse
	

	/**
	 * Method
	 * Initializes variables and calls recursive method. Wrapper method 
	 * for recursive post-order traversal method.
	 */
	public void postOrderTraverse()
	{
		String postOrder = "";

		if(root != null)
		{
			postOrder = rPostOrderTraverse(root, postOrder);
		}

		System.out.println(postOrder);
	}//end of postOrderTraverse


	/**
	 * Method
	 * Recursively performs postorder traverse through the current expression tree
	 * displaying the expression on the console.
	 * @param currentNode Node the recursive call will be based around
	 */
	private String rPostOrderTraverse(Node currentNode, String post)
	{
			
		if(currentNode.leftNode != null)
		{
			post = rPostOrderTraverse(currentNode.leftNode, post);
		}
		
		if(currentNode.rightNode != null)
		{
			post = rPostOrderTraverse(currentNode.rightNode, post);
		}
		
		return post += currentNode.toString() + " ";
		
	}//end of rPostOrderTraverse
	
	
	/**
	 * Method
	 * Creates and returns a string containing information about the current object.
	 * @return String containing the tree class type, infix, postfix, prefix of the tree expression
	 */@Override
	public String toString()
	{
		//variables for postfix and prefix
		String postfix = "";
		String prefix = "";
		
		//return formatted output for the class object
		return "Type:\t\t" + getClass().getSimpleName() + "\nInfix:\t\t"  
		+ expression + "\nPrefix:\t\t" + rPreOrderTraverse(root, prefix)
		+ "\nPostfix:\t" + rPostOrderTraverse(root, postfix);
		
	}//end of toString



	/**
	 * Method
	 * Determines if two trees are equal. Trees are equal if they have nodes all the same nodes
	 * at the same locations on their respective trees. Wrapper method for a recursive equals method.
	 * @param otherTree takes another tree as an argument to be compared to
	 */
	public boolean equals(ExpressionTree otherTree)
	{
		//trees are not the same if...
		//they are not the same tree type
		//do not have the same nodes
		//are not the same expression
		if(!this.getClass().getSimpleName().equals(otherTree.getClass().getSimpleName()) 
		|| !expression.equals(otherTree.expression))
		{
			return false;
		}
		else
		{
			return rEquals(root, otherTree.getRoot());
		}
	}//end of overridden equals


	/**
	 * Method
	 * Recursively traverses to each node in postorder fashion
	 * checking both the nodes in their corresponding locations
	 * @param currentNode1 node used to traverse through the this tree
	 * @param currentNode2 node used to traverse the tree being compared
	 * @return true or false depending on if the trees equivalent or not
	 */
	public boolean rEquals(Node currentNode1, Node currentNode2)
	{
		
		if(currentNode1.leftNode != null && currentNode2.leftNode != null)
		{
			if(!rEquals(currentNode1.leftNode, currentNode2.leftNode))
			{
				
				return false;
			}
		}
		
		if(currentNode1.rightNode != null && currentNode2.rightNode != null)
		{
			if(!rEquals(currentNode1.leftNode, currentNode2.leftNode))
			{
				
				return false;
			}
		}
		
		if(currentNode1.equals(currentNode2))
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
		
		

	}//end of rEquals
}