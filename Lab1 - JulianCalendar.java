package test;

public class JulianCalendar {
	
	// Max number of Days in a month 
	static private  final int MAX_DAY = 31; 
	
	// abbreviated Month names 
	static private  final String [] MONTH_NAMES = {
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	};
	
	// day length of each month 
	static private final  int [] MONTH_SIZES = {
		31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	};

	/**
	 * display The "DAY" Column Heading 
	 */
	static private void displayDayHeading() {
		System.out.printf("%6s", "Day");
		
	}
	
	/**
	 * display Month names between Day .... Day
	 */
	static private void displayHeading() {
		displayDayHeading(); 
		for(int i = 0 ; i < MONTH_NAMES.length ; ++i )  {
			System.out.printf("%5s", MONTH_NAMES[i]);
		}
		
		displayDayHeading(); 
	}
	

	static public void display() {
		displayHeading(); // display heading 
		
		for(int day = 1; day <= MAX_DAY; day++) {
			System.out.printf("\n%6d", day);
			
			int num = 0;
			
			for(int month = 0; month < 12; month++) {
				if(day > MONTH_SIZES[month]) {
					System.out.printf("%5s", " ");
				}
				else {
					System.out.printf("%2s", " ");
					System.out.printf("%03d", num + day);
				}
				num += MONTH_SIZES[month];	
			}	
			System.out.printf("%6d", day);	
		}
	}
	
//				while(month < 12) {
//					for(int i = 0; i <= MONTH_SIZES[month]; i++) {
//						if(num < 10) {
//							String str = "00" + num;
//							System.out.print("\t");
//							System.out.print(str);
//							num++;
//						}
//						else if(num < 100) {
//							String str = "0" + num;
//							System.out.print("\t");
//							System.out.print(str);
//							num++;
//						}
//						else {
//							System.out.print("\t");
//							System.out.print(num);
//							num++;
//						}	
//						month++;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method 
		JulianCalendar.display(); 
	}
}