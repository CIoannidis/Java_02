
public class Main {
	public static void main (String[] args) {
		
		int N = 1000000;
		int L = 100;
		ArrayBST abst = new ArrayBST(N);
		BST bst = new BST();
		ArrayHeap aheap = new ArrayHeap(N);
		Heap heap = new Heap();
		FileManager fm = new FileManager("keys_1000000_BE.bin");
		FileManager fm2 = new FileManager("keys_del_100_BE.bin");
		////////////////////////////////////////////
		//Reading the files
		long start, end;
		int[] nums = new int[N+1];
		int[] del = new int[L];		
		for(int i = 0; i < N; i++) {
			nums[i] = fm.readInt();
		}
		for(int i = 0; i < L; i++) {
			del[i] = fm2.readInt();
		}
		///////////////////////////////////////////////////////////////
		//Inserting into ArrayBST
		start = System.nanoTime();
		for(int i = 0; i < N; i++) {
			abst.insert(nums[i]);						
		}
		end = System.nanoTime();
		System.out.println("\nInsertion Time ABST: " + (end-start));
		
		System.out.println("Insertion comparisons: " + abst.getComp());
		System.out.println("Avg num of comparisons per insertion: " + abst.getComp()/1000000);
		abst.setComp(0);		
		
		//Deleting from ArrayBST
		start = System.nanoTime();
		for(int i = 0; i < L; i++) {
			abst.delete(del[i]);
		}
		end = System.nanoTime();
		System.out.println("Deletion Time ABST: " + (end-start));
		
		System.out.println("Deletion comparisons: " + abst.getComp());
		System.out.println("Avg num of comparisons per deletion: " + abst.getComp()/100);
		abst.setComp(0);
		/////////////////////////////////////////////////////////////////
		//Inserting into BST
		start = System.nanoTime();
		for(int i = 0; i < N; i++) {
			bst.insert(nums[i]);
		}
		end = System.nanoTime();
		System.out.println("\nInsertion Time BST: " + (end-start));
		System.out.println("Insertion comparisons: " + bst.getComp());
		System.out.println("Avg num of comparisons per insertion: " + bst.getComp()/1000000);
		bst.setComp(0);	
		
		//Deleting from BST
		start = System.nanoTime();
		for(int i = 0; i < L; i++) {
			bst.deleteKey(del[i]);
		}
		end = System.nanoTime();
		System.out.println("Deletion Time BST: " + (end-start));
		
		System.out.println("Deletion comparisons: " + bst.getComp());
		System.out.println("Avg num of comparisons per deletion: " + bst.getComp()/100);
		bst.setComp(0);
		///////////////////////////////////////////////////////////////
		//Inserting in ArrayHeap
		start = System.nanoTime();
		for(int i = 0; i < N; i++) {
			aheap.insert(nums[i]);
		}
		end = System.nanoTime();
		System.out.println("\nInsertion Time AHeap: " + (end-start));
		System.out.println("Insertion comparisons: " + aheap.getComp());
		System.out.println("Avg num of comparisons per insertion: " + aheap.getComp()/1000000);
		aheap.setComp(0);
		//Deleting from ArrayHeap
		start = System.nanoTime();
		for(int i = 0; i < L; i++) {
			aheap.extractMax();
		}
		end = System.nanoTime();
		System.out.println("Deletion Time AHeap: " + (end-start));
		
		System.out.println("Deletion comparisons: " + aheap.getComp());
		System.out.println("Avg num of comparisons per deletion: " + aheap.getComp()/100);
		aheap.setComp(0);
		//Inserting Everything At Once ArrayHeap
		start = System.nanoTime();
		aheap.buildheap(nums, N);
		end = System.nanoTime();
		System.out.println("Time Built Heap from array: " + (end-start));
		aheap.setComp(0);
		///////////////////////////////////////////////////////////////////
		//Inserting in Heap
		start = System.nanoTime();
		for(int i = 0; i < N; i++) {
			heap.insert(nums[i]);
		}
		end = System.nanoTime();
		System.out.println("\nInsertion Time Heap: " + (end-start));
		System.out.println("Insertion comparisons: " + heap.getComp());
		System.out.println("Avg num of comparisons per insertion: " + heap.getComp()/1000000);
		heap.setComp(0);
		//Deleting from ArrayHeap
		start = System.nanoTime();
		for(int i = 0; i < L; i++) {
			heap.extractMax();
		}
		end = System.nanoTime();
		System.out.println("Deletion Time Heap: " + (end-start));
		System.out.println("Deletion comparisons: " + heap.getComp());
		System.out.println("Avg num of comparisons per deletion: " + heap.getComp()/100);
		aheap.setComp(0);
		
				
		Heap bt = new Heap();
		bt.root = bt.createBT(nums);
		start = System.nanoTime();
		bt.buildheap(bt.root, N);
		end = System.nanoTime();
		System.out.println("Time of buildHeap dynamically from BT: " + (end-start));
			
		
//		abst.insert(69);
//		abst.insert(71);
//		abst.insert(60);
//		abst.insert(67);
//		abst.insert(68);
//		abst.insert(66);
//		abst.insert(65);
//		abst.insert(64);
//		
//		bst.insert(69);
//		bst.insert(71);
//		bst.insert(60);
//		bst.insert(67);
//		bst.insert(68);
//		bst.insert(66);
//		bst.insert(65);
//		bst.insert(64);
//		
//		System.out.println();
//		abst.printAll(abst.getRoot());
//		System.out.println();
//		bst.inorder_Recursive(bst.root);
//		
//		abst.delete(5);
//		abst.delete(66);
//		bst.deleteKey(5);
//		bst.deleteKey(66);
//		System.out.println();
//		abst.printAll(abst.getRoot());
//		System.out.println();
//		bst.inorder_Recursive(bst.root);
//		
//		aheap.insert(69);
//		aheap.insert(71);
//		aheap.insert(60);
//		aheap.insert(67);
//		aheap.insert(68);
//		aheap.insert(66);
//		aheap.insert(65);
//		aheap.insert(64);
//		aheap.insert(34);
//		
//		System.out.println("\n\nArray Heap:");
//		aheap.print();
//		
//		System.out.println(aheap.extractMax());
//		
//		System.out.println();
//		aheap.print();
//		
//		int[] array = new int[] {69, 71, 60, 67, 68, 66, 65, 64, 0, 0};
//		aheap.buildheap(array, 8);
//		aheap.print();
//		
//		heap.insert(69);
//		heap.insert(71);
//		heap.insert(60);
//		heap.insert(67);
//		heap.insert(68);
//		heap.insert(66);
//		heap.insert(65);
//		heap.insert(64);
//		heap.insert(34);
//		
//		System.out.println("\n\nHeap:");
//		heap.print();
//		
//		System.out.println(heap.extractMax());
//		
//		System.out.println();
//		heap.print();
//		
//		//heap.buildheap(array, 8);
//		//heap.print();
	}


}
