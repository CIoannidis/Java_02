
public class Heap {
	
	private int comp;
	
	public class Node{
		private int key;
		Node parent;
		Node left;
		Node right;
		
		public Node(int key, Node p) {
			this.key = key;
			this.left = null;
			this.right = null;
			this.parent = p;
		}
		
		public int getKey() {
			return key;
		}
		
		public void setKey(int key) {
			this.key = key;
		}
		
		// Returning position of parent
		private Node parent() { return parent; }

		// Returning left children
		private Node leftChild() { return left; }

		// Returning right children
		private Node rightChild(){ return right; }

		// Returning true of given node is leaf
		private boolean isLeaf()
		{
			return left == null && right == null;
		}
	}
	
	public Node root;
	
	private int size;	//Size of the heap by num of elements
	private int depth;	//The depth of the heap
	private int cur;	//This points to the current node
	//Example: Size is 23, that means depth is floor(log(23))+1 with a base of 2, which equals floor[ln(23)/ln(2)]+1
	//this equals 4+1=5=depth. Cur operates like a pointer/counter of the elements on the deepest depth. 
	//That means cur=(size)-(all elements of (depth-1)) = 23-2^4=23-16 = 7. Cur can currently have a value of [0,15] in this depth
	//Traversing the tree to insert a new node means reading both depth and cur.

	// Constructor to initialize an
	// empty max heap with given maximum
	// capacity
	public Heap()
	{
		this.size = 0;
		this.depth = 1;
		this.cur = 0;
	}

	// Swapping nodes
	private void swap(Node n1, Node n2)
	{
		int tmp;
		tmp = n1.getKey();
		n1.setKey(n2.getKey());
		n2.setKey(tmp);
	}
	//This method calculates the depth of the heap where depth 1 equals root
	private void calcDepth() {
		depth =(int)Math.floor(Math.log(size)/Math.log(2))+1;
	}
	//This method increases cur
	private void incCur() {
		cur++;
		//if cur goes over the limit, it has to initialize back to 0 since the heap is full at that depth. Depth increases and cur goes to 0
		if(cur >= Math.pow(2, depth-1)) {
			cur = 0;
		}
	}
	
	//Decrease cur
	private void decCur() {
		cur--;
		if(cur < 0 ) {
			cur = (int)Math.pow(2, depth-1)-1;
		}
	}

	// Recursive function to max heapify given subtree
	private void maxHeapify(Node r)
	{
		if (r.isLeaf()) {
			return;
		}
		incComp();
		if(r.left != null && r.getKey() < r.left.getKey()) {
			incComp();
			if (r.right != null && r.left.getKey() < r.right.getKey()) {
				swap(r, r.right);
				maxHeapify(r.right);
			}
			else {
				swap(r, r.left);
				maxHeapify(r.left);
				
			}
		}
		else if (r.right != null && r.getKey() < r.right.getKey()) {
			incComp();
			swap(r, r.right);
			maxHeapify(r.right);
		}
		incComp();
	}

//		// Put element in its correct place
//		private void maxHeapify(int pos) {
//		//Assert.notFalse((pos >= 0) && (pos < n), "Illegal heap position");
//		  while (!isLeaf(pos)) {
//		    int j = leftChild(pos);
//		    if ((j<(size-1)) && (Heap[j] < Heap[j+1]))
//		      j++; 	// j is now index of child with the greater value
//		    if (Heap[pos] > Heap[j]) return; // Done
//		    swap(pos, j);
//		    pos = j;  	// Move down
//		  }
//		}
	
	// Inserts a new element to max heap
	public void insert(int element) {
		root = insertRec(element, root, depth, cur);
	}
	
	private Node insertRec(int element, Node root, int d, int c)
	{
		if(root == null) {
			root = new Node(element, null);
			size++;
			calcDepth();
			incCur();
			return root;
		}
		//if cur<2^(depth-2) means we're in the left subtree
		if(c < Math.pow(2, d-2)) {
			//Since we're in the left subtree, cur doesn't need re-adjustment, but we're traversing further down so depth is decreased
			root.left = insertRec(element, root.left, d-1, c);
			root.left.parent = root;
			incComp();
			if(root.getKey() < root.left.getKey()) swap(root, root.left);
		}
		else {
			//We're in the right subtree. Cur is decreased by 2^(depth-2) and we're traversing further down so depth is decreased
			root.right = insertRec(element, root.right, d-1, (int)(c - Math.pow(2, d-2)));
			root.right.parent = root;
			incComp();
			if(root.getKey() < root.right.getKey()) swap(root, root.right);
		}
		return root;
		// Traverse up and fix violated property
	}

	// To display heap
	public void print(){
		printRec(root);
	}
	private void printRec(Node root)
	{
		 if (!root.isLeaf()) { 
			 System.out.print("Parent Node : " + root.getKey() );
			 if(root.left != null) {
					System.out.print( " Left Child Node: " + root.left.getKey());
				 }
			 if(root.right != null) { 
					System.out.print( " Right Child Node: " + root.right.getKey());
			}
			 System.out.println();
			 if(root.left != null) {
			 	printRec(root.left); 
			 }
			 if(root.right != null) { 
	         	printRec(root.right);
			 }
			 
			 
	     } 
		
	}

	// Remove an element from max heap
	public int extractMax() {
		int max = root.getKey();
		size--;
		calcDepth();
		decCur();
		root = extractMaxRec(root, depth, cur);
		maxHeapify(root);
		return max;
	}
	private Node extractMaxRec(Node root, int d, int c)
	{
		if(root.isLeaf()) {
			swap(this.root, root);
			
			//System.out.println("new root: " + this.root.getKey() + " del: " + root.getKey() + " depth: " + depth + " cur: " + cur);
			return null;
		}
		if(c < Math.pow(2, d-2)) {
			root.left = extractMaxRec(root.left, d-1, c);
		}
		else {
			root.right = extractMaxRec(root.right, d-1, (int)(c - Math.pow(2, d-2)));
		}
		return root;
	}

	public void buildheap(Node r, int s)           // Heapify contents of Heap
	  { root = r;
	  	size = s;
		buildheapRec(root);
	  }
	//BuildHeap
	private void buildheapRec(Node r) {
		if(r.isLeaf()) return;
		buildheapRec(r.left);
		if(r.right != null) 
			buildheapRec(r.right);
		maxHeapify(r);
	}
	//Creating a BT in order to be able to heapify it all together and not one by one
	public Node createBT(int[] array) {
		int l, r, p;
		Node[] aNodes = new Node[array.length];
		for(int i = 0; i < array.length; i++) {
			if(aNodes[i] == null) aNodes[i] = new Node(0, null);
			aNodes[i].key = array[i];
			p = (i-1)/2;
			if(p >= 0) aNodes[i].parent = aNodes[p];
			l = 2*i+1;
			r = 2*i+2;
			if(l < array.length) {
				if(aNodes[l] == null) aNodes[l] = new Node(0, null);
				aNodes[i].left = aNodes[l];
			}
			if(r < array.length) {
				if(aNodes[r] == null) aNodes[r] = new Node(0, null);
				aNodes[i].right = aNodes[r];
			}
		}
		return aNodes[0];
	}
	//Methods used for calculating the number of comparisons in the algorithm
	public void setComp(int comp) {
		this.comp = comp;
	}
	public int getComp() {
		return this.comp;
	}
	public void incComp() {
		comp++;
	}

}
