
public class ArrayHeap {
	private int[] Heap;
	private int size;
	private int maxsize;
	private int comp;

	// Constructor to initialize an
	// empty max heap with given maximum
	// capacity
	public ArrayHeap(int maxsize)
	{
		// This keyword refers to current instance itself
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new int[this.maxsize];
	}

	// Returning position of parent
	private int parent(int pos) { return (pos - 1) / 2; }

	// Returning left children
	private int leftChild(int pos) { return (2 * pos) + 1; }

	// Returning right children
	private int rightChild(int pos){ return (2 * pos) + 2; }

	// Returning true of given node is leaf
	private boolean isLeaf(int pos)
	{
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	// Swapping nodes
	private void swap(int fpos, int spos)
	{
		int tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	// Recursive function to max heapify given subtree
	private void maxHeapify(int pos)
	{
		incComp();
		if (isLeaf(pos)) {
			return;
		}
		incComp();
		//In case one or both of the children is greater than the parent
		if (Heap[pos] < Heap[leftChild(pos)] || Heap[pos] < Heap[rightChild(pos)]) {
			incComp();
			//Finding which child is greater
			if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
				swap(pos, leftChild(pos));
				maxHeapify(leftChild(pos));
				
			}
			else {
				swap(pos, rightChild(pos));
				maxHeapify(rightChild(pos));
			}
		}
	}

//	// Put element in its correct place
//	private void maxHeapify(int pos) {
//	//Assert.notFalse((pos >= 0) && (pos < n), "Illegal heap position");
//	  while (!isLeaf(pos)) {
//	    int j = leftChild(pos);
//	    if ((j<(size-1)) && (Heap[j] < Heap[j+1]))
//	      j++; 	// j is now index of child with the greater value
//	    if (Heap[pos] > Heap[j]) return; // Done
//	    swap(pos, j);
//	    pos = j;  	// Move down
//	  }
//	}
	
	// Inserts a new element to max heap
	public void insert(int element)
	{
		Heap[size] = element;

		// Traverse up and fix violated property
		int current = size;
		while (Heap[current] > Heap[parent(current)]) {
			incComp();
			swap(current, parent(current));
			current = parent(current);
		}
		incComp();
		size++;
	}

	// To display heap
	public void print()
	{
	
	for(int i=0;i<size/2;i++){

			System.out.print("Parent Node : " + Heap[i] );
			
			if(leftChild(i)<size) //if the child is out of the bound of the array
			System.out.print( " Left Child Node: " + Heap[leftChild(i)]);
			
			if(rightChild(i)<size) //if the right child index must not be out of the index of the array
				System.out.print(" Right Child Node: "+ Heap[rightChild(i)]);
			
				System.out.println(); //for new line
			
		}
		
	}

	// Remove an element from max heap
	public int extractMax()
	{
		int popped = Heap[0];
		//System.out.println("swapping: " + Heap[0] + " <- " + Heap[size-1]);
		Heap[0] = Heap[--size];
		//System.out.println("swapping: " + Heap[0] + " <- " + Heap[size+1]);
		maxHeapify(0);
		return popped;
	}

	public void buildheap(int[] h, int s)    // Heapify contents of Heap
	  { Heap = h;
	  	size = s;
		for (int i=size/2-1; i>=0; i--) {
			maxHeapify(i); }
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
