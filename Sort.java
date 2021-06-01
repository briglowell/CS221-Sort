import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221, bLowell
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		int mid = list.size()/2;
		// base case
		if (list.size() < 2) {
			return;
		}
		
		IndexedUnsortedList<T> leftList = newList();
		IndexedUnsortedList<T> rightList = newList();
		 
		// splits list into two separate lists
		
		for (int i = 0; i < mid; i++) {
			leftList.add(list.removeFirst());
		}
		while(!list.isEmpty()) {
			rightList.add(list.removeFirst());
		}
		
		// apply merge sort algorithm recursively to both seperate lists. 
		mergesort(leftList);
		mergesort(rightList);
		
		//reassemble the original list
		while (!leftList.isEmpty() && !rightList.isEmpty()) {
			if (leftList.first().compareTo(rightList.first()) < 0) {
				list.add(leftList.removeFirst());
			}else {
				list.add(rightList.removeFirst());
			}
		}
		while (!leftList.isEmpty()) {
			list.add(leftList.removeFirst());
		}
		while (!rightList.isEmpty()) { 
			list.add(rightList.removeFirst());
		}
	}
		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		
		int mid = list.size()/2;
		// base case
		if (list.size() < 2) {
			return;
		}
		
		IndexedUnsortedList<T> leftList = newList();
		IndexedUnsortedList<T> rightList = newList();
		 
		// splits list into two separate lists
		
		for (int i = 0; i < mid; i++) {
			leftList.add(list.removeFirst());
		}
		while(!list.isEmpty()) {
			rightList.add(list.removeFirst());
		}
		
		// apply merge sort algorithm recursively to both seperate lists. 
		mergesort(leftList, c);
		mergesort(rightList, c);
		
		//reassemble the original list
		while (!leftList.isEmpty() && !rightList.isEmpty()) {
			if (c.compare(leftList.first(), rightList.first()) < 0) {
				list.add(leftList.removeFirst());
			}else {
				list.add(rightList.removeFirst());
			}
		}
		while (!leftList.isEmpty()) {
			list.add(leftList.removeFirst());
		}
		while (!rightList.isEmpty()) { 
			list.add(rightList.removeFirst());
		}
	}
	
}