/**
 * Partially completed Direction ENUM 
 */

package edu.cuny.csi.csc330.lab5;

import edu.cuny.csi.csc330.util.Randomizer;


public enum Direction {
	 NONE, NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST;
	 // !!! Add 4 more Direction Values - NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST
	 
	 // methods 
	 public Direction getFavorite() {
		 return SOUTH;  // It's getting cold! ... 
	 }
	 
	 public Direction getNextRandom() {
		 	
			int direction = Randomizer.generateInt(1, 8); 
		
			if(direction == 1) {		// north 
				return NORTH;
			}
			else if(direction == 2) {	// east 
				return EAST; 
			}
			else if(direction == 3) {   // south 
				return SOUTH; 
			}
			else if(direction == 4) {	// west 
				return WEST; 
			}
			else if(direction == 5) {	// northeast 
				return NORTHEAST; 
			}
			else if(direction == 6) {	// southeast 
				return SOUTHEAST; 
			}
			else if(direction == 7) {	// southwest 
				return SOUTHWEST; 
			}
			else {						// northwest 
				return NORTHWEST; 
			}
	 }
	 
	 public static void main(String [] args)  {
		 
		 int c = 0; 
		 while(c++ < 100) {  
			 System.out.println(Direction.NONE.getNextRandom() );
			 
		 }	 
	 }
}