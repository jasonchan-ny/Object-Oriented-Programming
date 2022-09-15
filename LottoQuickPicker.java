/**
 * LAB 3 -  Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import java.util.Date;
import java.util.Arrays;
import java.text.DecimalFormat;

import edu.cuny.csi.csc330.util.Randomizer;

public class LottoQuickPicker {
	
	// constants  specific to current game - BUT NOT ALL GAMES 
	public final static int DEFAULT_GAME_COUNT = 1; 
	private final static String GAME_NAME = "LOTTO."; 
	private final static int SELECTION_POOL_SIZE = 59; 
	private final static int SELECTION_COUNT = 6; 
	private int gameCount = 0;

	public LottoQuickPicker() {
		init(DEFAULT_GAME_COUNT); 
	}
	
	public LottoQuickPicker(int games) {
		init(games); 
	}
  
	private void init(int games) {
		this.gameCount = games;
	}
	
	private void generateGame() {
		// array to hold lotto numbers
		int arr[] = new int[SELECTION_COUNT];
		
		for(int i = 1; i <= gameCount; i++) {
			System.out.printf("%6s", "(" + i + ")");
			for(int j = 0; j < SELECTION_COUNT; j++) {
			
				int temp = 	Randomizer.generateInt(1, SELECTION_POOL_SIZE);
				
				boolean contains = true;
				
				while(contains) {
					contains = Arrays.asList(arr).contains(temp);
					arr[j] = temp;
				}
			}
			//display current lotto numbers
			displayNumbers(arr);
		}
		//display odds of winning
		DecimalFormat df = new DecimalFormat("#,###");
		System.out.println("\nOdds of Winning: 1 in " + df.format(calculateOdds()));
		System.out.println("Are you sure it's your lucky day?\n");
	}
	
	public void displayTicket() {
		// display ticket heading 
		displayHeading(); 

		// display and generate game
		generateGame();
		
		// display ticket footer 
		displayFooter(); 
		
		return;
	}
	
	protected void displayHeading() {
		System.out.println(
				"----------------------------------\n" + 
				"------------- " + GAME_NAME + " -------------");
		Date currDate = new Date();
		System.out.printf("%3s", "");
		System.out.println(currDate + "\n");
	}
	
	protected void displayFooter() {
		System.out.println(
				"------ " + "(c) S.I. Corner Deli" + " ------" + 
				"\n----------------------------------"); 
	}
	
	protected void displayNumbers(int arr[]) {
		Arrays.sort(arr);
		for(int k = 0; k < arr.length; k++) {
			System.out.printf("%2s", "");
			System.out.printf("%02d", arr[k]);
		}
		System.out.print("\n");
	}
	
	/**
	 * 
	 * @return
	 */
	
	// multiply by amount of numbers available
	// subtract total amount by 1
	// rinse and repeat
	// able to repeat for as long as selection count allows
	// divides by factorial calculated from the factorial method below
	// finally return odds
	private long calculateOdds() {
		int division = SELECTION_POOL_SIZE;
		long odds = 1;
		for(int i = 0; i < SELECTION_COUNT; i++) {
			odds *= division;
			division--;
		}
		odds /= factorial();
		return odds;
	}
	
	private long factorial() {
		long result = 1;
		
		for(int factor = 2; factor <= SELECTION_COUNT; factor++)
			result *= factor;
		
		return result;
	}  

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = DEFAULT_GAME_COUNT; 
		
		if(args.length > 0) {  // if user provided an arg, assume it to be a game count
			numberOfGames = Integer.parseInt(args[0]);  // [0] is the 1st element!
		}

		LottoQuickPicker lotto = new LottoQuickPicker(numberOfGames);
		lotto.displayTicket(); 
	}
}
