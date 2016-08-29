// ***************************************************************************************************************************************************** //
// Author: Oscar Ivan Ricaud.
	/*Assignment: Lab 3
	Instructor: Professor Julio Urenda
	TA: Saiful Abu
	Course 2302
	Data of Last modification July 8th, 2016
	*/

// Program purpose:
	/*Is to be able to understand how B-Tree's work. I implemented many methods including search, insertion, count the number of leaf's and
	 * much more. Note~ Deletion method needs to be completed 
// How to operate BTree.java:
	/* Simply click the play button
	 */
//***************************************************************************************************************************************************** //

package lab3beta2;

public class TestHere {

	public static void main(String[] args) {
		// Create BTree x
		BTree x = new BTree(2);
		System.out.println("");
	
		int [] list = {1, 2, 3, 4, 5, 6, 7, 8};
		// Insert Elements to the tree 
		for(int i = 0; i < list.length; i++){
			x.insert(list[i]);
		}
		System.out.println("Below is just a walkthrough of how the B-TREE is being implemented. ");
		System.out.println("Everything works except Searching and Deletion I have not given up! I will keep working on it.");
		//Tree Information is at the bottom of the console!
		x.treeInfo();
		x.printAscendingOrder();
		System.out.println(""); 
		x.printDescendingOrder();
	}
}
