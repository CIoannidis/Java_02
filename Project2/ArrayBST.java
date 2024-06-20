
public class ArrayBST {
	private int avail;
	private int root;
	private int array[][];
	private int comp;
	
	//Making an array of Nx3 size
	public ArrayBST(int N) {
		array = new int [N][3];
		root = Integer.MIN_VALUE; // min value of integer is the null value
		avail = 0;
		//Initialize the array
		for(int i = 0; i < N; i++) {
			array[i][2] = i+1;
		}
		array[N-1][2] = Integer.MIN_VALUE;
	}
	//Element insertion
	public int insert(int key) {
		return root = insertRec(root, key);
	}
	//Traversing array incursively, and inserting the element 
	private int insertRec(int root, int key) {
		
		if(root == Integer.MIN_VALUE) {
			root = getNode();
			
			array[root][0] = key;
			array[root][1] = Integer.MIN_VALUE;
			array[root][2] = Integer.MIN_VALUE;
			return root;
		}

		//Checking if it has to be swapped due to it's value
		if(array[root][0] > key) {
			incComp();
			array[root][1] = insertRec(array[root][1], key);
		}
		else if(array[root][0] < key) {
			incComp();
			incComp();
			array[root][2] = insertRec(array[root][2], key);
		}
		else {
			incComp();
			incComp();
		}
		return root;
	}
	
	public int delete(int key) {
		return deleteRec(root, key);
	}
	
	private int deleteRec(int root, int key)  { 
        //tree is empty
		
        if (root == Integer.MIN_VALUE) return root;
        
        //traverse the tree
        if (key < array[root][0]) { //traverse left subtree 
        	incComp();
            array[root][1] = deleteRec(array[root][1], key); 
        }
        else if (key > array[root][0]) {  //traverse right subtree
        	incComp();
        	array[root][2] = deleteRec(array[root][2], key);
        }
        else  { 
        	incComp();
        	incComp();
            // node contains only one child
            if (array[root][1] == Integer.MIN_VALUE) {
            	
            	free_node(root);
                return array[root][2]; 
            }
            else if (array[root][2] == Integer.MIN_VALUE) {
            	
            	free_node(root);
                return array[root][1]; 
            }

   
            // node has two children; 
            //get inorder successor (min value in the right subtree) 
            array[root][0] = minValue(array[root][2]); 
   
            // Delete the inorder successor 
            array[root][2] = deleteRec(array[root][2], array[root][0]); 
        } 
        return root; 
    } 
	
	int minValue(int root)  { 
        //initially minval = root
        int minval = array[root][0]; 
        //find minval
        while (array[root][1] != Integer.MIN_VALUE)  { 
        	root = array[root][1];
            minval = array[root][0]; 
        } 
        return minval; 
    } 
	
	private int getNode() {
		if(avail == Integer.MIN_VALUE) return avail;
		int temp = avail;
		avail = array[avail][2];
		return temp;
	}
	
	private int free_node(int p) {
		array[p][2] = avail;
		avail = p;
		return avail;
	}
	
	public void printAll(int r) {
		if(r != Integer.MIN_VALUE) {
			printAll(array[r][1]);
			System.out.print(array[r][0] + " ");
			printAll(array[r][2]);
		}
		
	}
	/////////////////////////////////////////////
    boolean search(int key)  { 
        root = search_Recursive(root, key); 
        if (root!= Integer.MIN_VALUE)
            return true;
        else
            return false;
    } 
   
    //recursive insert function
    int search_Recursive(int root, int key)  { 
        // Base Cases: root is null or key is present at root 
        if (root==Integer.MIN_VALUE || array[root][0]==key) 
            return root; 
        // val is greater than root's key 
        if (array[root][0] > key) 
            return search_Recursive(array[root][1], key); 
        // val is less than root's key 
        return search_Recursive(array[root][2], key); 
    } 
	//////////////////////////////////////////////
	public int getRoot() {
		return this.root;
	}
	
	public int getAvail() {
		return this.avail;
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
