package edu.cuny.csi.csc330.lab2;

import java.util.Arrays;

public class NumericAnalyzer {
	private int count;
	private int min;
	private int max;
	private int range;
	private int sum;
	private int mean;
	private int median;
	private int variance;
	private int standardDev;
	
	public NumericAnalyzer(int[] numbers) {
	}

	// EXTRA CREDIT
	public static boolean isNumeric(String s) {
		try {
			Integer.parseInt(s);
		}
		catch(NumberFormatException err) {
			return false;
			// return false if given input not an int
		}
		return true;
		// return true if given input is an int
	}
	
	public void analyze(int[] numbers) {	
		
//		// sorts numbers using selection sort
//		sum = 0;
//		for(int i = 0; i < numbers.length; i++) {
//			int min = numbers[i];
//			int element = i;
//			for(int j = i + 1; j < numbers.length; j++) {
//				if(numbers[j] < min) {
//					min = numbers[j];
//					element = j;
//				}
//			}
//			int temp = numbers[i];
//			numbers[i] = min;
//			numbers[element] = temp;
//			sum += numbers[i];
//		}
		
		sum = 0;

		Arrays.sort(numbers);
		
		for(int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}

		count = numbers.length;
		min = numbers[0];
		max = numbers[numbers.length - 1];
		range = max - min;
		mean = sum / count;
		
		// calculates median
		if(numbers.length % 2 == 0) {
			int x = numbers.length / 2;
			median = numbers[x] + numbers[x-1];
			median /= 2;
		}
		// if length is an even number, find the average of the two middle values
		else {
			int x = numbers.length / 2;
			median = numbers[x];

		}
		// if length is an odd number, find the median as usual
		
		int temp;
		
		for(int i = 0; i < numbers.length; i++) {
			temp = numbers[i] - mean;
			temp = temp * temp;
			variance += temp;
			temp = 0;
		}
		variance /= count;
		standardDev = (int) Math.sqrt(variance);
	}
	
	public void display(int[] numbers) {
		System.out.print("\n");
		for(int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i]);
			System.out.printf("%3s", " ");
		}
			System.out.print("\n");
		for(int k = 0; k < 3; k++) {
			System.out.print("----------");
		}
		// because i am a neat freak
		System.out.print("\nCount:");
		System.out.printf("%23s", count);
		System.out.print("\nMin:");
		System.out.printf("%25s", min);
		System.out.print("\nMax:");
		System.out.printf("%25s", max);	
		System.out.print("\nRange:");
		System.out.printf("%23s", range);	
		System.out.print("\nSum:");
		System.out.printf("%25s", sum);		
		System.out.print("\nMean:");
		System.out.printf("%24s", mean);
		System.out.print("\nMedian:");
		System.out.printf("%22s", median);
		System.out.print("\nVariance:");
		System.out.printf("%20s", variance);
		System.out.print("\nStandard Deviation:");
		System.out.printf("%10s", standardDev);
	}
	
	public static void main(String[] args) {

		if(args.length == 0 ) {
			System.err.println("Empty Input!");
			System.exit(1); 
			// exit code for empty data
		}

		int numbers[] = new int[args.length]; 
		for(int i = 0; i < args.length; ++i ) {
			if(!NumericAnalyzer.isNumeric(args[i])) {
				System.err.println("Expecting Numeric Data: " + args[i]);
				System.exit(2); 
				// exit code for invalid data 
		  }
		  numbers[i] = Integer.parseInt(args[i]); 
		  // converting string into int
		}

		NumericAnalyzer analyzer = new NumericAnalyzer(numbers);
		analyzer.analyze(numbers); 
		analyzer.display(numbers); 
		
		System.exit(0); 	
	}

}
