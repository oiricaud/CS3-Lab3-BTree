package lab3beta2;


public class BTree {
	private BTreeNode root;
	private int T; 
	public int height;
	public int counter = 0;
	
	
	public BTree(int t){
		root = new BTreeNode(t);
		T = t;
		height = 0;
	}

	public void printHeight(){
		System.out.println("Tree height is "+height);
	}

	public void insert(int newKey){
		System.out.println("************* begin");
		System.out.println("Inserting new key " + newKey);
		if(root.isFull()){
			System.out.println("Root is full:");
			//System.out.print("root --> ");
			//root.print();
			//System.out.println("");
			//int median = root.key.length/(2);
			//	int promote = root.key[median];
			//	System.out.println("Root is full we must promote the mid node: " + promote);
			split();
			height++;
		}
		root.BTreeInsertNonFull(newKey);
		System.out.print("print current node-->");
		root.printNodes();
		System.out.println("");
		//	System.out.print("Current keys -->")	r
		//root.printNodes();
		//	System.out.println("");
		System.out.println("**** end");
		System.out.println("");

	}
	public void printAscendingOrder(){
		System.out.print("	Bonus) Ascending Order --> ");
		// Wrapper for node print method
		root.printAscendingOrder();
	}
	public void printNodes(){
		// Wrapper for node print method
		root.printNodes();
	}

	public void printDescendingOrder(){
		System.out.print(" 		Descending Order --> ");
		root.printDescendingOrder();
	}
	// Split method creates 2 children, left child and right child.
	// It then assigns the number of keys each child which is T-1;
	// and then I assign the median, which is the key that will be promoted
	// I then traverse up to the mid element and assign all of the root children
	// to be now the left child. Then I grab all of the keys the root has to be 
	// now the left child's key, except the mid element. Then I traverse from
	// the mid element up until I reach the last element of the root and do the exact
	// same thing as I did for the left child

	private void split() {
		System.out.println("SPLIT METHOD ");
		System.out.println("");
		BTreeNode leftChild = new BTreeNode(T);
		BTreeNode rightChild = new BTreeNode(T);
		leftChild.isLeaf = root.isLeaf;
		rightChild.isLeaf = root.isLeaf;

		leftChild.n = T-1;
		rightChild.n = T-1;
		int median = T-1;
		// Add elements up to the middle element and store them in the left child 
		for(int i = 0 ; i < T-1 ; i ++){
			leftChild.c[i] = root.c[i];
			leftChild.key[i] = root.key[i];

		}
		



		for(int j = median+1; j < root.n; j++){
			rightChild.c[j-median-1] = root.c[j];
			rightChild.key[j-median-1] = root.key[j];
		}
		rightChild.c[median]=root.c[root.n];
		System.out.print("Print left child--> ");
		leftChild.printNodes();
		System.out.println("");
		System.out.print("Print right child-->");
		rightChild.printNodes();
		System.out.println("");
		root.key[0]= root.key[median];
		root.n = 1;
		root.c[0] = leftChild;
		root.c[1] = rightChild;

		System.out.print("Print father of the left and right child--> ");
		root.printNodes();
		System.out.println("");
		root.isLeaf = false;
	}

	// This is where most of the data gets printed // 
	public void treeInfo() {

		System.out.println("[Tree info] ");
		/** Change the following to find the number of nodes at a specific depth-level
		 * 	To also count the number of nodes that contain exact n-keys
		 *  and to also search for a specific key.
		 */
		int depth = 2;
		int keys = 3;
		int searchKey = 1;
		int deleteKey = 8;

		System.out.println("		T = " + T + ".");
		System.out.println("       	Every internal node other than the root has at least: " + ( T - 1 )+ " key(s).");
		System.out.println("       	Every internal node other than the root has at least " + T + " children.");
		System.out.println(	"	"	+		(2*T-1) + " is the maximum number of keys the tree can have.");
		System.out.println(	"	"	+		(T-1) + " is the mininum number of keys the tree can have.");
		System.out.println("	An internal node may have at most " + (2*T) + " children");
		System.out.println("	The height of the tree is:---> " + height);
		System.out.print("	The root is---------------------------------------->[ " );
		printTheRoot();
		System.out.print("]");
		System.out.println("");
		System.out.println("");
		System.out.println("	TREE: " );
		printTheChildren(root);
		System.out.println("");
		findMin(root);
		counter =0;
		findMax(root);
		counter=0;
		System.out.println("	9) Total Number of Nodes: " + findNumberofNodes(root));
		counter=0;
		System.out.println("	10) Total Number of Keys is: " + findNumberofKeys(root));
		counter=0;
		System.out.println("	11) Sum of all keys is: " + findAllSumKeys(root));
		counter=0;
		System.out.println("	12) The number of of leaves is: "  + NumberofLeaves(root));
		counter=0;
		System.out.println("	13) The number of full nodes is: " + numOfNodesFull(root));
		counter=0;
		System.out.println("    	14) The number of nodes in the tree that have the minimum number of keys is: " + NumberOfMinKeys(root));
		counter=0;

		System.out.println("        15) The number of nodes in the tree that have the exact n keys is: " + keys + " is: " + ExactlyNKeys(root,  keys));
		counter=0;

		numberOfNodesDepthD(root,  depth);
		System.out.print("       17) Searching for key " + searchKey + "... p.s if nothing prints it means it wasn't found. ");
		root.search(searchKey);

		/****** The DELETE METHOD needs to be fixed!! ******/ 
		//System.out.println("       17) Delete  " + deleteKey + root.deletions(deleteKey));
		//System.out.println("       17) return true if found else false-->  " + deleteKey + root.deletions(deleteKey));

	}

	private void findMin(BTreeNode root) {
		// TODO Auto-generated method stub
		System.out.println("	7) The mininum element of the BTree is: " + root.findMin());		
	}

	private void findMax(BTreeNode root) {
		// TODO Auto-generated method stub
		System.out.println("	8) The maximum element of the BTree is: " + root.findMax());
	}

	private int findNumberofNodes(BTreeNode node) {

		if(node.isLeaf){
			counter++;
		}
		else{
			counter++;
			for(int i = 0 ; i < node.n+1; i++){

				//findNumberofNodes(root.c[i]);
				//= counter4 + root.findNumberofNodes();
				if(!(node.c[i].isLeaf)){
					//System.out.println("counter4b: " + counter4);
					findNumberofNodes(node.c[i]);

				}
				else{
					counter++;
					//System.out.println("counter4c: " + counter4);
				}
			}
		}
		//System.out.println("counter4d: " + counter4);
		return counter;
	}
	// The following method finds the number of keys in the tree.
	private int findNumberofKeys(BTreeNode node) {
		// count the number of keys in the leafs
		if(node.isLeaf){
			for(int i = 0; i < node.n; i++){
				counter++;
			}
		}
		else{
			// count the number of keys in the non-leaf nodes 
			for(int i = 0 ; i < node.n; i++){
				counter++;
				findNumberofKeys(node.c[i]);	
			}
			findNumberofKeys(node.c[node.n]);
		}
		return counter;
	}
	// The following method finds the sum of all the keys 
	private int findAllSumKeys(BTreeNode node) {

		if(node == null){
			return counter;
		}
		else{
			if(node.isLeaf){
				//	System.out.println("root is a leaf");
				counter = counter + node.findAllSumKeys();
				//System.out.println("counter1:  " + counter);
			}
			else{
				for(int i = 0; i < node.n; i++){
					//System.out.println("	key3: " + root.key[i]);
					counter = counter + node.key[i];
				}
				for(int i = 0 ; i < node.n+1; i++){
					//System.out.println("	counter3: " + counter);
					findAllSumKeys(node.c[i]);
				}
			}
		}
		return counter;
	}
	// Finds the number of nodes at that specific level
	public void numberOfNodesDepthD(BTreeNode node7, int depth) {

		System.out.println("	16) The number of nodes in the tree that are in depth " + depth + " level is: " +  node7.numberOfNodesDepthD(depth) +  " total nodes."  );
	}
	// Exactly that contains n- keys
	public int ExactlyNKeys(BTreeNode node, int keys) {
		// TODO Auto-generated method stub
		if(node == null){
			return counter;
		}
		else{
			if(node.n == keys){
				counter++;
			}
			for(int i = 0 ; i < node.n+1; i++){
				ExactlyNKeys(node.c[i], keys);
			}
		}
		return counter;

	}
	// Number of Min keys 
	private int NumberOfMinKeys(BTreeNode node5) {
		if(node5 == null){
			return counter;
		}
		else{
			if(node5.n == T-1){
				counter++;
			}
			for(int i = 0 ; i < node5.n+1; i++){
				NumberOfMinKeys(node5.c[i]);
			}
		}
		return counter;
	}
	private int numOfNodesFull(BTreeNode node4) {
		if(node4 == null){
			return counter;
		}
		if(node4.isFull()){
			//System.out.println("Node is FULL ");
			counter++;
		}
		else{
			for(int i = 0 ; i  < node4.n+1; i++){
				numOfNodesFull(node4.c[i]);
			}
		}
		return counter;
	}
	// Counts the number of leaves 
	private int NumberofLeaves(BTreeNode node) {
		if(node.isLeaf){
			counter++;
		}
		else{
			for(int i = 0 ; i < node.n+1; i++){
				NumberofLeaves(node.c[i]);
			}
		}
		return counter;
	}
	private void printTheChildren(BTreeNode node) {

		if(node.isLeaf){
			System.out.print("		[");
			for(int i = 0 ; i < node.n; i++){
				if(i == 0){
				}
				System.out.print(node.key[i] + " ");
			}
			System.out.print("]");

		}

		else{
			System.out.print("			[" + node.key[0] + "]");	
			System.out.println("");
			for(int i = 0 ; i <= node.n; i++){
				printTheChildren(node.c[i]);
			}
			System.out.println("  ");
		}
	}

	private void printTheRoot() {
		for(int i = 0; i < root.n; i++){
			System.out.print(root.key[i] + " ");
		}
	}
}