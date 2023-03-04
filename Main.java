import java.io.*;
import java.util.Scanner;

/**
 * Driver program for ExpressionTree class
 * @author My Tran
 */
public class Main 
{
	public static void main(String [] args) throws FileNotFoundException
	{
		//read file of expressions to test
		readFile();

		//to test trees for equality
		testEquals();

		//to test methods of Node class
		testNode();

	}//end of main

	

	/**
	 * Function
	 * Creates trees from lines read from a file and prints outputs to console
	 * @throws FileNotFoundException
	 */
	public static void readFile() throws FileNotFoundException
	{
		//Testing traversals and toString

		//File object to store the file for expressions
		File inFile = new File("input.txt");

		//Scanner object to read from input file
		Scanner fin = new Scanner(inFile);

		//Declare temporary variable to read expressions from file
		ExpressionTree tempTree;

		//Loop executes while there is another line of input in the file
		while (fin.hasNextLine())
		{
			//create a tree with an expression read from the file
			tempTree = new ExpressionTree(fin.nextLine());

			System.out.println("\n\nTree Traversals:\n" );
			//perform a pre-order traversal, in-order traversal, and post-order traversal
			//on the expression tree
			tempTree.preOrderTraverse();
			tempTree.inOrderTraverse();
			System.out.println();
			tempTree.postOrderTraverse();
			
			//print out the expression tree using the toString method of the ExpressionTree class
			System.out.println("\n" + tempTree);
		}

		fin.close();
		
	}


	/**
	 * Function
	 * Tests trees for equality with equals method for trees
	 */
	public static void testEquals()
	{
		System.out.println("\nTest for equals method\n");
		//to test if the same trees are the same
		ExpressionTree tree1 = new ExpressionTree("(1 + ( 2 * 3 )) + ((( 4 * 55) + 66) * 77)");
		ExpressionTree tree2 = new ExpressionTree("(1 + ( 2 * 3 )) + ((( 4 * 55) + 66) * 77)");

		System.out.println("Should be the same:");
		if(tree1.equals(tree2))
		{
			System.out.println("Same");
		}
		else
		{
			System.out.println("Not Same");
		}

		//to test if different trees are the same
		tree2 = new ExpressionTree("(22 + ( 23 * 13 )) + ((( 84 * 5) + 6) * 7)");


		System.out.println("Should be not same:");
		if(tree1.equals(tree2))
		{
			System.out.println("Same");
		}
		else
		{
			System.out.println("Not Same");
		}
	}//end of testEquals


	/**
	 * Function
	 * Tests the methods and functionality of Node class
	 */
	public static void testNode()
	{
		System.out.println("\nTest Node Class\n");
		//node to test methods with
		Node testNode = new Node();

		//Key object to be used to test setNode
		Key item = new Key();
		item.setElement("key");

		//test to see if setNode properly sets nodes element to given value
		testNode.setNode(item);

		//test for the toString method to return the element of the node
		//testNode.toString should be "key"
		System.out.println("Value in node is: " + testNode.toString());

		Node testNode2 = testNode.getNode();

		//testNode2.toString should be "key"
		System.out.println("Value in node is: " + testNode2.toString());


		//condition should be true
		if(testNode.equals(testNode2))
		{
			System.out.println("The Nodes are equal");
		}
		else
		{
			System.out.println("The Nodes are inequal");
		}

		//test to see if the non default constructor creates a new node with given value
		Node testNode3 = new Node("not key");

		//testNode3.toString should be "key"
		System.out.println("Value in node is: " + testNode3.toString());



		//condition should be false
		if(testNode.equals(testNode3))
		{
			System.out.println("The Nodes are equal");
		}
		else
		{
			System.out.println("The Nodes are inequal");
		}

	}//end of testNode
}
