package edu.cuny.csi.csc330.lab5;

import java.util.Scanner;

public class DrunkWalkTester {
	
	private Scanner input;

	public DrunkWalkTester() {
		init();  
	}
	
	private void init() {
		input = new Scanner(System.in);
	}
	
	public void runTest(int steps ) { 	
		System.out.print("Enter the starting avenue value: ");
		int avenue = input.nextInt();
		System.out.print("Enter the starting street value: ");
		int street = input.nextInt();
		
		// start test 
		// make the drunk with initial position
		DrunkWalker billy = new DrunkWalker(avenue, street);

		// have him move/step 200 time 
		billy.fastForward(steps);
		
		// get his current location
		String location = billy.getLocation();
		
		// get distance from start
		int distance = billy.howFar();

		System.out.println("Billy's location: " + location);
		System.out.println("That's " + distance + " blocks from start.");
		
		billy.displayWalkDetails();
		
		// end test 
		
		/*
		 * Expand the test above to include the following ... 
		 * Create a 2nd instances of DrunkWalker - harvey  
		 * Have then race each - which instance (billy or harvey)  
		 * manages to walk a greater distance with 200 steps?
		 */
		
		DrunkWalker billyJr = new DrunkWalker(avenue, street);
		DrunkWalker harvey = new DrunkWalker(avenue, street);
		
		billyJr.fastForward(200);
		harvey.fastForward(200);
		
		int distance1 = billyJr.howFar();
		int distance2 = harvey.howFar();
		
		System.out.println("BillyJr's location: " + billyJr.getLocation());
		System.out.println("That's " + distance1 + " blocks from start.");
		billyJr.displayWalkDetails();
		System.out.println("Harvey's location: " + harvey.getLocation());
		System.out.println("That's " + distance2 + " blocks from start.");
		harvey.displayWalkDetails();
		
		if(distance1 > distance2) {
			System.out.println("billyJr wins the drunken race!");
		}
		else if(distance1 < distance2) {
			System.out.println("harvey wins the drunken race!");
		}
		else {
			System.out.println("do my eyes deceive me?? it's a tie!");
		}
	}

	/**
	 * @param args 
	 * 
	 */
	public static void main(String[] args) {
		DrunkWalkTester tester = new DrunkWalkTester();
		
		int steps = 2000; 
		if(args.length == 1) {
			steps = Integer.parseInt(args[0]);
		}
		
		tester.runTest(steps); 
		
		System.exit(0);
	}
}
