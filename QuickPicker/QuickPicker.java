package edu.cuny.csi.csc330.lab6;

import edu.cuny.csi.csc330.lab3.LottoQuickPicker;
import edu.cuny.csi.csc330.util.Randomizer;
import java.util.ResourceBundle;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickPicker {
	public final static int DEFAULT_GAME_COUNT = 1;
	public final static String DEFAULT_GAME_NAME = "MegaMillions";
	private String gameName;
	private String vendorName;
	private int gameCount;
	private int selectionPoolSize;
	private int selectionCount;
	private int selectionPoolSize2;
	private int selectionCount2;
	
	private void initFromGameBundle(String p) throws QuickPickerException {
		
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(p);

			if(bundle.containsKey("GameName")) {
				gameName = bundle.getString("GameName");
			}
			if(bundle.containsKey("Pool1")) {
				String[] split = bundle.getString("Pool1").split("/");
				selectionCount = Integer.parseInt(split[0]);
				selectionPoolSize = Integer.parseInt(split[1]);
			}
			if(bundle.containsKey("Pool2")) {
				String[] split = bundle.getString("Pool2").split("/");
				selectionCount2 = Integer.parseInt(split[0]);
				selectionPoolSize2 = Integer.parseInt(split[1]);
			}
			if(bundle.containsKey("Vendor")) {
				vendorName = bundle.getString("Vendor");
			}
		}
		catch(Exception e) {
			throw new QuickPickerException("Error: Unable to find game!", QuickPickerException.INVALID_GAME_NAME);
		}
	}
	
	public QuickPicker() {
	}
	
	public QuickPicker(String p, int games) throws QuickPickerException {
		initFromGameBundle(p);
		init(games);
	}
	
	private void init(int games) {
		this.gameCount = games;
	}
	
	private void setVendor(String vendorName) {
		this.vendorName = vendorName;
	}
	
	private void generateGame() {
		int currPoolSize;
		int currCount;
		int rand = Randomizer.generateInt(1,2);
		
		if (rand == 1) {
			currPoolSize = selectionPoolSize;
			currCount = selectionCount;
		}
		else {
			currPoolSize = selectionPoolSize2;
			currCount = selectionCount2;
		}
		
		for(int i = 1; i <= gameCount; i++) {
			System.out.printf("%6s", "(" + i + ")");
	
			int arr[] = generateRandom(currPoolSize, currCount);
			
			//display current lotto numbers
			displayNumbers(arr);
		}
		
		//display odds of winning
		DecimalFormat df = new DecimalFormat("#,###");
		System.out.println("\nOdds of Winning: 1 in " + df.format(calculateOdds(currPoolSize, currCount)));
		System.out.println("Are you sure it's your lucky day?\n");
	}
	
	private int[] generateRandom(int size, int count) {
		int arr[] = new int[count];
		boolean contains = true;
		
		for(int j = 0; j < count; j++) {
			
			int temp = Randomizer.generateInt(1, size);
			
			while(this.isUnique(arr, temp)) {
				arr[j] = temp;
			}
			
		}	
		return arr;
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
				"--------- " + gameName + " ---------");
		Date currDate = new Date();
		System.out.printf("%3s", "");
		System.out.println(currDate + "\n");
	}
	
	protected void displayFooter() {
		System.out.println(
				"------ " + vendorName + " ------" + 
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
	
	private boolean isUnique(int[] arr, int x) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == x) {
				return false;
			}
		}
		return true;
	}
	
	private long calculateOdds(int size, int count) {
		int division = size;
		long odds = 1;
		for(int i = 0; i < count; i++) {
			odds *= division;
			division--;
		}
		odds /= factorial(count);
		return odds;
	}
	
	private long factorial(int count) {
		long result = 1;
		
		for(int factor = 2; factor <= count; factor++)
			result *= factor;
		
		return result;
	}
	
	public static void main(String[] args) {
		int numberOfGames = DEFAULT_GAME_COUNT;
		String path = DEFAULT_GAME_NAME;
		
		if(args.length > 0) {  // if user provided args, assume it to be new parameters
			numberOfGames = Integer.parseInt(args[0]);  // [0] accepts the game count
			path = args[1]; // [1] accepts the game
		}

		try {
			QuickPicker lotto = new QuickPicker(path, numberOfGames);
			lotto.displayTicket(); 
		}
		catch(QuickPickerException e) {
			System.out.println(e);
		}
	}
}
