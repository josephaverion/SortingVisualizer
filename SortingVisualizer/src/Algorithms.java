public class Algorithms {
	
	protected static int current_index	= 0;
	protected static int traversing_index = 0;
	protected static int selected_index	= 0;
	protected static int speed = 25;
	
	private static Visual visual = Main.visual;
	
	/*
	 * current index: left side of array from this index is already sorted
	 * traversing index: the index traverses downward from current index. Checks whether the value at the selected index needs to move further down or not.
	 * selected index: the index that's trying to find the right spot in the left side of the array
	 */
	public static void insertionSort() throws InterruptedException {
		for(current_index = 1; current_index < visual.SIZE; current_index++) {
			traversing_index = current_index;
			while(traversing_index > 0 && visual.bar_height[traversing_index] < visual.bar_height[traversing_index-1]) {
				selected_index = traversing_index - 1;
				swap(traversing_index, selected_index);
				Thread.sleep(speed);
				visual.repaint();
				traversing_index--;	
			}
		}
		
		current_index = 0;
		traversing_index = 0;
		selected_index = 0;
	}
	
	/*
	 * current index: left side of array from this index is already sorted
	 * traversing index: the index that traverses from current index to find the index of the smallest value
	 * selected index: the index of the smallest value
	 */
	public static void selectionSort() throws InterruptedException {
		selected_index = 0;
		for(current_index = 0; current_index < visual.SIZE; current_index++) {
			selected_index = current_index;
			for(traversing_index = current_index; traversing_index < visual.SIZE; traversing_index++) {
				Thread.sleep(speed);	
				if(visual.bar_height[selected_index] > visual.bar_height[traversing_index]) {
					selected_index = traversing_index;
				}
				visual.repaint();
			}
			swap(current_index, selected_index);
			visual.repaint();
		}
		
		current_index = 0;
		traversing_index = 0;
		selected_index = 0;
	}
	
	/*
	 * current index: right side of array from this index is already sorted
	 * traversing index: the index that traverses from beginning to current index. If the next value is bigger than selected index, that value is the new selected index
	 * selected index: the index of the biggest value
	 */
	public static void bubbleSort() throws InterruptedException {
		for(current_index = visual.SIZE - 1; current_index >= 0; current_index--) {
			selected_index = 0;
			for(traversing_index = selected_index + 1; traversing_index <= current_index; traversing_index++) {
				visual.repaint();
				Thread.sleep(speed);	
				if(visual.bar_height[traversing_index] < visual.bar_height[selected_index]) {
						swap(traversing_index, selected_index);
				}
				selected_index++;
			}
		}
		
		current_index = 0;
		traversing_index = 0;
		selected_index = 0;
	}
	
	/*
	 * buildHeap() creates heap
	 * shrinkHeap() removes root and moves to end of the array, and fixes the heap
	 */
	public static void heapSort() throws InterruptedException {
		buildHeap();
		shrinkHeap();
	}
	
	private static void buildHeap() throws InterruptedException {
		int i = 1;
		while(i < visual.SIZE) {
			i++;
			int child = i - 1;
			int parent = (child - 1) / 2;
			while(parent >= 0 && visual.bar_height[parent] < visual.bar_height[child]) {
				swap(parent, child);
				child = parent;
				parent = (child - 1) / 2;
				Thread.sleep(speed);
				visual.repaint();
			}
		}
	}
	
	/*
	 * current index: right side of array from this index is already sorted
	 * traversing index: the index that traverses from the last leaf to the parent
	 * selected index: root
	 */
	private static void shrinkHeap() throws InterruptedException { 
		current_index = visual.SIZE - 1;
		int n = visual.SIZE; 
	    while (n > 0) {
	    	current_index--;
	    	n--; 
	    	swap(0, n); 
	    	int parent = 0; 
	    	while (true) { 
	    		int leftChild = 2 * parent + 1; 
	    		if (leftChild >= n) { 
	    			break;
	    		} 
	    		int rightChild = leftChild + 1; 
	    		int maxChild = leftChild; 
	    		if (rightChild < n && visual.bar_height[leftChild] < visual.bar_height[rightChild]) {
	    			maxChild = rightChild; 
	    			traversing_index = maxChild;
	    			Thread.sleep(speed);
	    			visual.repaint();
	    		} 
	    		if (visual.bar_height[parent] < visual.bar_height[maxChild]) { 
	    			swap(parent, maxChild);
	    			parent = maxChild;
	    			traversing_index = parent;
	    			selected_index = 0;
	    			Thread.sleep(speed);
	    			visual.repaint();
	    			
	    		} else {
	    			break; 
	    		} 
	    	}
	    }
	    
		current_index = 0;
		traversing_index = 0;
		selected_index = 0;
	}
	
	public static void mergeSort() throws InterruptedException {
		mergeSort(0, visual.SIZE - 1);
	}
	
	private static void mergeSort(int start, int end) throws InterruptedException {
		if(end - start < 1) {
			return;
		}
		
		int middle = (end - start + 1) / 2 + start;
		
		mergeSort(start, middle - 1);
		mergeSort(middle, end);
		
		current_index = start;
		merge(start, middle, end);
	}
	
	/*
	 * current index: index where the merging starts
	 * traversing index: traverses through left subarray
	 * selected index: traverses through right subarray
	 */
	private static void merge(int start, int middle, int end) throws InterruptedException {
		int lookup_length = end - start + 1;
		float[] temp = new float[lookup_length];
		
		for(int i = 0; i < lookup_length; i++) {
			temp[i] = visual.bar_height[start + i];
		}
		
		int temp_middle = middle - start;
		int newArrIndex = start;
		int leftArrIndex = 0;
		int rightArrIndex = temp_middle; 
		
		while(leftArrIndex < temp_middle && rightArrIndex < temp.length) {
			
			traversing_index = leftArrIndex + start;
			selected_index = rightArrIndex + start;
				
			if(temp[leftArrIndex] < temp[rightArrIndex]) {
				visual.bar_height[newArrIndex++] = temp[leftArrIndex++];
			} else {
				visual.bar_height[newArrIndex++] = temp[rightArrIndex++];
			}
			Thread.sleep(speed);
			visual.repaint();
			
		}
		
		while(leftArrIndex < temp_middle) {
			traversing_index = leftArrIndex + start;
			visual.bar_height[newArrIndex++] = temp[leftArrIndex++];
			Thread.sleep(speed);
			visual.repaint();
			
		}
		
		while(rightArrIndex < temp.length) {
			selected_index = rightArrIndex + start;
			visual.bar_height[newArrIndex++] = temp[rightArrIndex++];
			Thread.sleep(speed);
			visual.repaint();
		}
		
		current_index = 0;
		traversing_index = 0;
		selected_index = 0;
	}
	
	public static void quickSort() throws InterruptedException {
		quickSort(0, visual.SIZE - 1);
	}
	
	private static void quickSort(int start, int end) throws InterruptedException {
		if(start < end) {
			int pivotIndex = partition(start, end);
			current_index = pivotIndex;
			quickSort(start, pivotIndex - 1);
			quickSort(pivotIndex + 1, end);
		}
		
		current_index = 0;
		traversing_index = 0;
		selected_index = 0;
	}
	
	/*
	 * current index: pivot
	 * traversing index: traverses through left subarray
	 * selected index: traverses through right subarray
	 */
	private static int partition(int start, int end) throws InterruptedException {
		int pivotIndex = median(start, (start + end) / 2, end);
		float pivot = visual.bar_height[pivotIndex];
		swap(start, pivotIndex);
		Thread.sleep(speed);
		visual.repaint();
		traversing_index = start;
		selected_index = end;
		do {
			while(traversing_index < end && pivot >= visual.bar_height[traversing_index]) {
				traversing_index++;
			}
			while(pivot < visual.bar_height[selected_index]) {
				selected_index--;
			}
			if(traversing_index < selected_index) {
				swap(traversing_index, selected_index);
				Thread.sleep(speed);
				visual.repaint();
			}
		} while (traversing_index < selected_index);
		swap(start, selected_index);
		Thread.sleep(speed);
		visual.repaint();
		return selected_index;
	}
	
	// chooses pivot based on the median of beginning, middle, and end value
	private static int median(int l, int m, int r) {
		double left	= visual.bar_height[l];
		double middle = visual.bar_height[m];
		double right = visual.bar_height[r];
		
		if((left > middle) != (left > right)) {
			return l;
		} else if ((middle > left) != (middle > right)) {
			return m;
		} else {
			return r;
		}
	}
	
	// swaps values
	public static void swap(int indexA, int indexB) {
		float temp = visual.bar_height[indexA];
		visual.bar_height[indexA] = visual.bar_height[indexB];
		visual.bar_height[indexB] = temp;
	}
	
	// returns current_index to the visual to paint it
	public static int getCurrentIndex() {
		return current_index;
	}
	
	// returns traversing_index to the visual to paint it
	public static int getTraversingIndex() {
		return traversing_index;
	}
	
	// returns selected_index to the visual to paint it
	public static int getSelectedIndex() {
		return selected_index;
	}
	
	// sets the speed before starting the sort
	public static void setSpeed(int s) {
		speed = s;
	}
}

