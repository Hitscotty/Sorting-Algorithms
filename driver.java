package HW7;

/**
 * @author Scotty
 */

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Vector;

public class driver extends SortingAlgorithms {

	/**
	 * create fields that will be in constant use
	 */
	public static Random rand = new Random();
	private static int size;
	private static long start;
	private static long finish;
	private static long time;
	private static long[] average = new long[10];
	private static long[] SD = new long[10];
	private static Vector<Person> sample;

	/**
	 * methods that create the contact and use the vector
	 * 
	 * @return
	 */
	public static String randomName() {
		String type = "random";
		String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "w",
				"x", "y", "z" };
		String name = "";

		switch (type) {
		case "alpha":
			for (int i = 0; i < 6; i++) {
				name += alphabet[rand.nextInt(25)];
			}
			return name;
		case "random":
			for (int i = 0; i < 6; i++) {
				name += alphabet[rand.nextInt(25)];

			}
			return name;

		case "reverse":
		}
		return name;
	}

	public static String randomPhone() {
		String phone = "";
		while (phone.length() < 12) {
			if (phone.length() == 3 || phone.length() == 7) {
				phone += "-";
			}
			phone += rand.nextInt(9);
		}

		return phone;
	}

	/**
	 * creates the vectors by creating an array and then organizing it inserts
	 * the elements in array into vector returns vector for organizing
	 * 
	 * @param amount
	 * @param type
	 * @return
	 */

	public static Vector<Person> generateVector(int amount, String type) {

		Vector<Person> myVector = new Vector<Person>();
		Person[] bank = new Person[amount];
		String name;
		String phone;
		size = amount;
		Person person;
		Person temp;
		boolean flag = true;

		// add random people into an array
		for (int i = 0; i < amount; i++) {
			name = randomName();
			phone = randomPhone();
			person = new Person(name, phone);
			bank[i] = person;
		}

		// sort the array
		while (flag) {
			flag = false;
			for (int j = 0; j < bank.length - 1; j++) {
				if (bank[j].getKey().compareTo(bank[j + 1].getKey()) > 0) {
					temp = bank[j]; // swap elements
					bank[j] = bank[j + 1];
					bank[j + 1] = temp;
					flag = true; // shows a swap occurred
				}
			}
		}

		switch (type) {
		case "alphabetical":
			myVector.clear();
			for (int i = 0; i < amount; i++) {
				myVector.add(i, bank[i]);
			}
			return myVector;
		case "random":
			myVector.clear();
			for (int i = 0; i < amount; i++) {
				name = randomName();
				phone = randomPhone();

				person = new Person(name, phone);
				myVector.add(i, person);
			}
			return myVector;

		case "reverse":
			myVector.clear();
			for (int i = amount - 1, j = 0; i >= 0 && j < amount - 1; i--, j++) {
				myVector.add(j, bank[i]);
			}
		}
		return myVector;
	}

	/**
	 * displays the vector for debugging
	 * 
	 * @param myVector
	 */
	public static void display(Vector myVector) {
		for (int i = 0; i < myVector.size(); i++) {
			System.out.println("The value: " + myVector.get(i));

		}
	}

	/**
	 * these methods create the test cases and use the
	 * mergesort/QuickSort types//HeapSort
	 */
	private static Vector<Person> vectorCases(int num, String sort, String type) {
		switch (sort) {
		case "MergeSort":
			sample = generateVector(num, type);
			mergeSort(sample, 0, sample.size()-1);
			return null;
		case "QuickSort":
			sample = generateVector(num,type);
			quickSort(sample, 0, sample.size()-1, "first");
			return null;
		case "QuickSort2":
			sample = generateVector(num,type);
			quickSort(sample, 0, sample.size()-1, "mid");
			return null;
		case "QuickSort3":
			sample = generateVector(num,type);
			quickSort(sample, 0, sample.size()-1, "random");
		return null;
		case "HeapSort":
			heapSort(generateVector(num, type));
		return null;
	}
		return null;
	}



	/**
	 * method that puts everything into a for loop to run each test case ten
	 * times
	 * 
	 * @param string
	 * */
	public static void runTestCases(int num, String sort) {
		// -----------------------------------------
		// Test case 1 : alphabetical order
		// -----------------------------------------
		System.out.printf(sort
				+ "   |%8d%8d%8d%8d%8d%8d%8d%8d%8d%8d\t|%s\t%s\n", 1, 2, 3, 4,
				5, 6, 7, 8, 9, 10, "Average", "Standard Deviation");
		System.out
				.println("-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("TestCase 1: ");
		for (int i = 0; i < 10; i++) {
			start = System.nanoTime();
			vectorCases(num, sort, "alphabetical");
			finish = System.nanoTime();
			time = finish - start;
			average[i] = time;
			System.out.printf("| %-4d", time);

		}
		System.out.printf("\t|%f\t %f\n", average(average),
				standardDeviation(average));

		// -----------------------------------------
		// Test cast 2: randomly
		// -----------------------------------------
		System.out
				.println("-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("TestCase 2: ");
		for (int i = 0; i < 10; i++) {
			start = System.nanoTime();
			vectorCases(num, sort, "random");
			finish = System.nanoTime();
			time = finish - start;
			average[i] = time;
			System.out.printf("| %4d", time);
		}

		System.out.printf("\t|%f\t %f\n", average(average),
				standardDeviation(average));

		// -----------------------------------------
		// test case 3: reverse alphabetical
		// -----------------------------------------
		System.out
				.println("-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("TestCase 3: ");
		for (int i = 0; i < 10; i++) {
			start = System.nanoTime();
			vectorCases(num, sort, "reverse");
			finish = System.nanoTime();
			time = finish - start;
			average[i] = time;
			System.out.printf("| %4d", time);
		}
		System.out.printf("\t|%f\t %f\n", average(average),
				standardDeviation(average));
	}

	public static void main(String[] args) {
		runTestCases(10, "MergeSort");
		System.out.printf("\n\n");

		System.out.println("'FIRST'");
		runTestCases(10, "QuickSort");
		System.out.printf("\n\n");

		System.out.println("'MIDDLE'");
		runTestCases(10, "QuickSort1");
		System.out.printf("\n\n");
		
		System.out.println("'RANDOM'");
		runTestCases(10, "QuickSort2");
		System.out.printf("\n\n");
		
		runTestCases(10, "HeapSort");
		System.out.printf("\n\n");
		
	}
}
