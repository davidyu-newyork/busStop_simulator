/**
* The <code>Bus</code> class implements a instance of Bus
* objects. This is where we have a "bus" that has data variables such as what stop they are going to,
* their capacity, current passengers, and what type of bus it is
*    
*Recitation number: 08
* @author D.J.Y David Justin Yu
*    e-mail: david.yu@stonybrook.edu
*    Stony Brook ID:111922653
**/
package homework4_214;

/**
 * Our Bus class, in which we extend the Passenger Queue to add functionality to hold the passengers
 * @author David Yu
 *
 */
public class Bus extends PassengerQueue{
	private String inRoute[]  = {"South P", "West", "SAC", "Chapin"}; //the stops in the inroute
	private String outRoute[] = {"South P", "PathMart", "Walmart", "Target"}; //the stops in the outroute
	private int nextStop,toNextStop,timeToRest; 
	//nextStop is the stop that the bus is going to
	//toNextStop is how long till the bus arrives at the next stop
	//timeToRest is how long the bus is resting for
	
	private int route; // 0 is in bus, 1 is outroute
	private final int capacity; //capacity is how many passenger the bus can hold
	private int currentPassengers=0; //current passsengers is how many passengers are on the bus

	/**
	 * Constructor in which we construct our bus object with certain inputs
	 * @param capacity
	 * @param timeToRest
	 * @param route
	 * @param nextStopTime
	 */
	public Bus(int capacity, int timeToRest, int route, int nextStopTime) {
		super();
		this.route = route;
		this.nextStop = 0;
		this.toNextStop = nextStopTime;
		this.timeToRest = timeToRest;
		this.capacity = capacity;
	}
	/**
	 * getter method for timeToRest
	 * @return timeToRest
	 */
	public int getTimeToRest() {
		return timeToRest;
	}
	/**
	 * setter method for timeToRest
	 * @param timeToRest
	 */
	public void setTimeToRest(int timeToRest) {
		this.timeToRest = timeToRest;
	}
	/**
	 * getter method for this bus's nextStop
	 * @return nextStop
	 */
	public int getNextStop() {
		return nextStop;
	}
	/**
	 * setter method for NextStop
	 * @param nextStop
	 */
	public void setNextStop(int nextStop) {
		this.nextStop = nextStop;
	}
	/**
	 * method in which it ticks the timeToRest down by 1
	 */
	public void timeRestDown() {
		timeToRest--;
	}
	/**
	 * method in which it ticks the timeStopDown down by 1
	 */
	public void timeStopDown() {
		toNextStop--;
	}
	/* (non-Javadoc)
	 * @see homework4_214.PassengerQueue#enqueue(homework4_214.Passenger)
	 */
	@Override
	public void enqueue(Passenger p) {
		// TODO Auto-generated method stub
		super.enqueue(p);
		currentPassengers += p.getGroup();
	}
	
	/**
	 * method in which we use to "unload" a passenger from the bus
	 * @param dest 
	 * the stop in which we are at
	 * @return how many passengers we deloaded
	 */
	public int unload(int dest) {
		int countPassengers = 0;
		for (int i = 0; i < size(); i++) {
			if (get(i).getDestination() == dest) {
				countPassengers += get(i).getGroup();
				remove(i);
				i--;
				
			}
			
		}
		currentPassengers -=countPassengers;
		return countPassengers;
	}
	/**
	 * getter method for currentPassengers
	 * @return currentPassengers
	 */
	public int getCurrentPassengers() {
		return currentPassengers;
	}
	/**
	 * setter method for currentPassengers
	 * @param currentPassengers
	 */
	public void setCurrentPassengers(int currentPassengers) {
		this.currentPassengers = currentPassengers;
	}
	/**
	 * getter method for toNextStop
	 * @return toNextStop
	 */
	public int getToNextStop() {
		return toNextStop;
	}
	/**
	 * setter method for toNextStop
	 * @param toNextStop
	 */
	public void setToNextStop(int toNextStop) {
		this.toNextStop = toNextStop;
	}
	

}
