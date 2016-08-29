package lab3beta2;
public class BTreeNode {

	public int[] key; // stores the key in the current node 
	public BTreeNode[] c;
	boolean isLeaf;
	public int n; 	/** The number of keys stored in the node. */
	private int T; //Each node has at least T-1 and at most 2T-1 keys
	int counter = 0 ;
	int counter3 = 0;

	// Create BTreeNode
	public  BTreeNode(int t){
		T = t;
		isLeaf = true;
		key = new int[2*T-1];	// Max number of keys 
		c = new BTreeNode[2*T];
		n=0;	
	}

	// Checks if current node is full
	public boolean isFull(){
		return	n==(2*T-1);
	}
	// First checks if the current key is a leaf, if so meaning the tree is empty it keeps inserting recursievly until
	// the max keys is reached. I used an integer i to be able to distinguish if the key is < or >  than the current root 
	// its just to place the newKey in the proper position.
	// Now if the root is full it becomes a non-leaf root and then it splits and promotes the mid element to be the new root
	// and it then recurisvely cls the method to check if the children are full.
	public void BTreeInsertNonFull( int newKey) {

		int i= n-1;	// n = the number of current keys stored in the node
		if(isLeaf){
			System.out.println("Current node is a leaf! ");
			// In other words the while loop is checking for elements that are greater than the newKey
			// once the newKey found the element greater than the current element 
			// it assigns the next position of the current element to be the current element
			// Once out of the while loop we replace the current element next position to the new key. .

			while ((i>=0)&& (newKey<key[i])) {
				System.out.println(key[i] + " > " + newKey + " ?");
				key[i+1] = key[i];
				i--;
			}

			n++;
			key[i+1]=newKey;
		}
		else{
			System.out.println("	Current node is NOT a leaf. ");
			System.out.println("	key[" + i + "] = " + key[i]);

			// So essentially what this while loop does is compare the elements in the current node
			// and if the current key is greater than the new key we get in the while loop
			// and then we add the elements in the most left child. 
			while ((i>=0)&& (newKey<key[i])) {
				i--;
			}

			int insertChild = i + 1; // where i is 1 less than the number of keys stored in the node 

			// If the children is full we must split. 
			if(c[insertChild].isFull()){

				System.out.println("child[" + insertChild + "] is FULL THUS WE MUST SPLIT the child and create a new father");
				n++;
				//		System.out.println("----> n value " + n);
				//		System.out.println("----> n value " + c[n]);
				// This next line creates a new child and copies all variables from the full child. 
				// new child[3] = child[2] 
				c[n] = c[n-1];

				// The for loop moves all the children to the left copies child[2] = child[1] && key[2] = key[1]
				// then child[1] = child[0] && key[2] = key[1]
				for(int j = n-1;j>insertChild;j--){
					c[j] =c[j-1];
					key[j] = key[j-1];
				}
				//		System.out.println("What is the value of T? " + T);
				// key[0] = c[0].key[T-1] where T = 3
				// key[0] = c[0].key[2] 
				// We are pretty much creating the root in the next line
				key[insertChild]= c[insertChild].key[T-1];
				// c[0].n = 2 pretty much what this means is we're cutting the full child in half and copying the new father into the existing fathers
				//			System.out.println("c[insertChild].n " + c[insertChild].n);
				c[insertChild].n = T-1;

				BTreeNode newNode = new BTreeNode(T);
				// If necessary we must also copy the Father's children children (or grandchildren) 
				//Traverse up to the middle element and start creating children
				// newNode.c[0] = c[0].c[0+3] == c[0].c[3] 
				// We must also create a new key and set it equal to the c[0] keys

				// This wil be our new right child 
				//			System.out.println("c[insertChild].c[k+T]= " + c[insertChild].c[T]);
				//System.out.println("c[insertChild].key[k+T= " + c[insertChild].key[3]);
				for(int k=0;k<T-1;k++){
					newNode.c[k] = c[insertChild].c[k+T];
					newNode.key[k] = c[insertChild].key[k+T];
				}
				// the next lines essentially assigns
				// newNode.c[2] = c[0].c[5] == null 
				//				System.out.println( "c[insertChild].c[2*T-1] = " +  c[insertChild].c[2*T-1]);
				newNode.c[T-1] = c[insertChild].c[2*T-1];
				newNode.n=T-1;
				newNode.isLeaf = c[insertChild].isLeaf;
				c[insertChild+1]=newNode;
				// if the new key is less than key[0]
				if (newKey <key[insertChild]){
					c[insertChild].BTreeInsertNonFull(newKey);			
				}
				if(newKey > key[insertChild]){
					c[insertChild+1].BTreeInsertNonFull(newKey);	
				}
			}
			else
				c[insertChild].BTreeInsertNonFull(newKey);
		}
	}

	//Prints all keys in the tree in ascending order
	public void printAscendingOrder(){

	if (isLeaf){
			for(int i =0; i<n;i++){
				System.out.print(key[i]+" ");
			}
		}
		else{
			for(int i =0; i<n;i++){
				c[i].printAscendingOrder();
				System.out.print(key[i]+" ");
			}
			c[n].printAscendingOrder();
		}
	}

	public void printNodes(){
		//Prints all keys in the tree, node by node, using preorder
		//It also prints the indicator of whether a node is a leaf
		//Used mostly for debugging purposes
		for(int i =0; i<n;i++){
			if(i == 0){
				System.out.print("[ ");
			}
			System.out.print(key[i]+" ");
			if (!isLeaf){
				for(int j =0; j<=n;j++){
					c[j].printNodes();
				}
			}
			if(i == n-1){
				System.out.print("]");
			}
		}
	}
	// Prints from Largest - to smallest 
	public void printDescendingOrder() {

		if(isLeaf){
			for(int i = n-1; i >= 0 ; i--){
				System.out.print(key[i] + " ");
			}
		}
		else{
			// root.n - 1 
			for(int i = n-1; i >= 0; i--){
				c[i+1].printDescendingOrder();
				System.out.print( key[i] + " ");
			}
			c[0].printDescendingOrder();
		}



	}
	// Finds the smallest element of the tree
	public Integer findMin() {

		if(isLeaf){
			return key[0];
		}
		return c[0].findMin();
	}
	// Finds the largest element of the tree
	public Integer findMax() {
		if(isLeaf){
			return key[n-1];
		}
		return c[n].findMax();
	}

	// Finds the sum of all the keys 
	public int findAllSumKeys() {
		//System.out.println("Node class");
		for(int i = 0 ; i  < n ; i ++){

			//	System.out.println("key[" + i + "]= " + key[i]);
			counter = counter + key[i];
		}
		//System.out.println("The total sum of keys in the children: " + counter);
		return counter;
	}

	// Counts the number of full nodes
	public int numOfNodesFull() {
		System.out.println("numOfNodesFull method  ");
		for(int i = 0 ; i < n; i++) {
			if(isLeaf && c[i].isFull()){
				counter3++;
			}


		}

		return counter3;
	}

	// Print children
	public void printChildren() {
		if(isLeaf){
			for(int i = 0 ; i < n; i++){
				System.out.print(key[i] + " ");
			}

		}
	}

	// Prints the number of nodes in that specific depth 
	public int numberOfNodesDepthD(int depth) {
		int count = 0;
		if(depth==0){
			count = 1;
		}
		else{
			if(!isLeaf){
				for(int i = 0 ; i <= n; i++){
					count = count + c[i].numberOfNodesDepthD(depth-1);
				}
			}
		}
		return count;
	}
	public void search(int searchKey) {

		if(isLeaf){
			for(int i =0; i<n;i++){
				if(key[i] == (searchKey)){
					System.out.println("		 Found! key " + searchKey + " and it happens to be a leaf. ");
					return;
				}

			}

		}

		else{

			for(int i = 0 ; i < n ; i ++){
				if(key[i]==(searchKey)){
					System.out.println(" 		Found! key " + searchKey + " and it happens to be a non-leaf node. ");
					return;
				}
				else{
					c[i].search(searchKey);

				}

				c[n].search(searchKey);

			}
		}

	}
	public String deletions(int deleteKey) {
		if(isLeaf){
			for(int i =0; i<n;i++){
				if(key[i] == (deleteKey)){
					System.out.println("We found key " + deleteKey + " its a leaf! " + key[i]+" ");
				}

			}
		}
		else{
			for(int i = 0 ; i < n ; i ++){
				if(key[i]==(deleteKey)){
					System.out.println("We found the key we want to delete =" + key[i]);
				}
				c[i].deletions(deleteKey);
				System.out.println(key[i] +"<- ");
			}
			c[n].deletions(deleteKey);
		}
		return "blah";
	}
}
