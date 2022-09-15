package edu.cuny.csi.csc330.lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrunkWalker {
	
	private Intersection startIntersection;
	private Intersection currentIntersection;
	private Map<String, ArrayList<Intersection>> visits = new HashMap<>();
	
	public Map<String, ArrayList<Intersection>> getVisits() {
		return visits;
	}
	
	public DrunkWalker(int avenue, int street) {	
		startIntersection = new Intersection(avenue, street);
		currentIntersection = new Intersection(avenue, street);
	}
	
	public void step() {
		takeAStep(); 
		
		Intersection newIntersection = currentIntersection.clone();
		
		if(visits.containsKey(currentIntersection.getKey())) {
			ArrayList<Intersection> locations = visits.get(currentIntersection.getKey());
			locations.add(newIntersection);
			visits.put(newIntersection.getKey(), locations);
		} 
		else {
			visits.put(newIntersection.getKey(), new ArrayList<>(Arrays.asList(newIntersection)));
		}
	}
	
	private void takeAStep() {
		Direction dir = Direction.NONE.getNextRandom(); 
		
		int currAvenue = currentIntersection.getAvenue();
		int currStreet = currentIntersection.getStreet();

		switch(dir) {
			case NORTH:
				currentIntersection.setAvenue(currAvenue + 1);
				break;
			case EAST:
				currentIntersection.setStreet(currStreet + 1);
				break;
			case SOUTH:
				currentIntersection.setAvenue(currAvenue - 1);
				break;
			case WEST:
				currentIntersection.setStreet(currStreet - 1);
				break;
			case NORTHEAST:
				currentIntersection.setAvenue(currAvenue + 1);
				currentIntersection.setStreet(currStreet + 1);
				break;
			case SOUTHEAST:
				currentIntersection.setAvenue(currAvenue - 1);
				currentIntersection.setStreet(currStreet + 1);
				break;
			case SOUTHWEST:
				currentIntersection.setAvenue(currAvenue - 1);
				currentIntersection.setStreet(currStreet - 1);
				break;
			case NORTHWEST:
				currentIntersection.setAvenue(currAvenue + 1);
				currentIntersection.setStreet(currStreet - 1);
				break;
		}
	}

	public void fastForward(int steps ) {
		for(int i = 0; i < steps; i++) {
			step();
		}
	}
	
	public void displayWalkDetails() {
		System.out.println("Starting Location: " + startIntersection);
		System.out.println("Current Location: " + currentIntersection);
		System.out.println("Distance: " + howFar());
		System.out.println("Unique Intersections Visited: " + getUniqueVisits().size());
		System.out.println("Intersections Re-visited: " + getRevisits().size());	
	}
	
	public int howFar() {
		int x = startIntersection.getAvenue();
		int y = startIntersection.getStreet();

		int newX = currentIntersection.getAvenue();
		int newY = currentIntersection.getStreet();
	 
		int distance = (Math.abs(x - newX) ) + (Math.abs(y - newY));
		
		return distance;
	}
	
	public List<Intersection> getUniqueVisits() {
		ArrayList<Intersection> uniqueVisits = new ArrayList<>();
		for (List<Intersection> visitsForLocation : visits.values()) {
			if (visitsForLocation.size() == 1) {
				uniqueVisits.add(visitsForLocation.get(0));
			}
		}
		return uniqueVisits;
	}
	
	public List<Intersection> getRevisits() {
		ArrayList<Intersection> revisits = new ArrayList<>();
		for (List<Intersection> visitsForLocation : visits.values()) {
			if (visitsForLocation.size() > 1) {
				revisits.add(visitsForLocation.get(0));
			}
		}
		return revisits;
	}
	
	public String getLocation() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		return "" + currentIntersection;
	}

	public static void main(String[] args) {
		
		// create Drunkard with initial position (ave,str)
		DrunkWalker billy = new DrunkWalker(6,23);
		
		for(int i = 1 ; i <= 3 ; ++i ) {
			billy.step(); 
			System.out.printf("billy's location after %d steps: %s\n",
					i, billy.getLocation() );
		}
			
		// get his current location
		String location = billy.getLocation();
		// get distance from start
		int distance = billy.howFar();
		System.out.println("Current location after fastForward(): " + location);
		System.out.println("That's " + distance + " blocks from start.");
		

		// have him move 25  random intersections
		billy.fastForward(25);
		billy.displayWalkDetails();
		
	}

}
