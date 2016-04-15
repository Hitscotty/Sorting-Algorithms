package HW7;

import java.util.Random;
import java.util.Vector;

public class SortingAlgorithms extends stats {
	private static Vector<Person> myVector = new Vector<Person>();
	private static Vector<Person> tempMergVec = new Vector<Person>();
	private static int length;
	private static Vector<Person> vector;
	private static int size;
	private static Vector<Person> temp = new Vector<Person>();
	private static Vector<Person> temp2 = new Vector<Person>();
	private static Random rand = new Random();

	/**
	 * MergeSort
	 * 
	 * @param inputVector
	 */

	public static void swap(Vector<Person> sortVector, int i, int j) {

		if (!temp.isEmpty()) {
			temp.clear();
		}
		temp.add(sortVector.get(i));
		sortVector.set(i, sortVector.get(j));
		sortVector.set(j, temp.firstElement());

	}

	public static void mergeSort(Vector<Person> sortVector, int first, int last) {
		if (first < last) {
			int mid = first + (last - first) / 2;
			mergeSort(sortVector, first, mid);
			mergeSort(sortVector, mid + 1, last);

			Vector<Person> tempVector = null;
			for (int i = first; i <= last; i++) {
				tempVector = (Vector<Person>) sortVector.clone();
			}
			int newLow = first;
			int newMid = mid + 1;
			int tempLow = first;
			while (newLow <= mid && newMid <= last) {
				if (tempVector.get(newLow).getName()
						.compareTo(tempVector.get(newMid).getName()) <= 0) {
					sortVector.set(tempLow, tempVector.get(newLow));
					newLow++;
				} else {
					sortVector.set(tempLow, tempVector.get(newMid));
					newMid++;
				}
				tempLow++;
			}
			while (newLow <= mid) {
				sortVector.set(tempLow, tempVector.get(newLow));
				tempLow++;
				newLow++;
			}
		}
	}

	//----------------------------------------------------------------------------------------

	public static void quickSort(Vector<Person> sortVector, int first,
			int last, String type) {
		if (first >= last || sortVector.isEmpty()) {
			return;
		} else if (!temp.isEmpty()) {
			temp.clear();
		}

		switch (type) {
		case "first":
			temp.add(sortVector.get(first));
			break;
		case "mid":
			temp.add(sortVector.get(first + (last - first) / 2));
			break;
		case "random":
			temp.add(sortVector.get(rand.nextInt((last - first) + 1) + first));
			break;
		}

		int low = first;
		int high = last;
		while (low <= high) {

			while (sortVector.get(low).getName()
					.compareTo(temp.firstElement().getName()) < 0) {
				low++;
			}

			while (sortVector.get(high).getName()
					.compareTo(temp.firstElement().getName()) > 0) {
				high--;
			}

			if (low <= high) {
				temp2.add(0, sortVector.get(low));
				sortVector.set(low, sortVector.get(high));
				sortVector.set(high, temp2.firstElement());
				low++;
				high--;
			}
		}
		// call quickSort() method recursively
		if (first < high) {
			quickSort(sortVector, first, high, type);
		}
		if (low < last) {
			quickSort(sortVector, low, last, type);
		}
	}

	/**
	 * HeapSort
	 */
	//----------------------------------------------------------------------------------------

	public static void maxHeap(Vector<Person> myVector, int i) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int max = i;

		if (left <= size
				&& myVector.get(left).getName()
						.compareTo(myVector.get(i).getName()) > 0) {
			max = left;
		}

		if (right <= size
				&& myVector.get(right).getName()
						.compareTo(myVector.get(max).getName()) > 0) {
			max = right;
		}

		if (max != i) {
			swap(myVector, i, max);
			maxHeap(myVector, max);
		}

	}

	public static void heapSort(Vector<Person> myVector) {
		size = myVector.size() - 1;

		for (int i = size / 2; i >= 0; i--) {
			maxHeap(myVector, i);
		}

		for (int i = size; i > 0; i--) {
			swap(myVector, 0, i);
			size = size - 1;
			maxHeap(myVector, 0);
		}
	}

}
