/**
* The <code>Passenger</code> class implements a instance of Passenger
* objects. This class contains data variables such as 
* how many are in the group,their destination, and time they arrive at stop
*    
*Recitation number: 08
* @author D.J.Y David Justin Yu
*    e-mail: david.yu@stonybrook.edu
*    Stony Brook ID:111922653
**/
package homework4_214;

/**
 * Our Passenger class, in which we hold data about the Passenger
 * @author David Yu
 *
 */
public class Passenger {

	private int group,destination,timeArrived,timeWaited;
	//group is how many people are in the group
	//destination is what stop they are going to
	//timeArrived is what time they get to the bus stop
	//timeWaited is how long they wait for the bus to pick them up
	
	
	/**
	 * default constructor
	 */
	public Passenger() {
		group = -1;
		destination =-1;
		timeArrived = -1;
		timeWaited = -1;
		
	}
	/**
	 * constructor in which we use primariliy, which takes parameters
	 * 
	 * @param group
	 * how many is in the group
	 * @param destination
	 * what stop they want to get off at
	 * @param timeArrived
	 * the time they arrive
	 */
	public Passenger(int group, int destination, int timeArrived) {
		super();
		this.group = group;
		this.destination = destination;
		this.timeArrived = timeArrived;
	}
	/**
	 * getter for group, which is how many people are in the group
	 * @return group
	 */
	public int getGroup() {
		return group;
	}

	/**
	 * setter for group variable
	 * @param group
	 */
	public void setGroup(int group) {
		this.group = group;
	}

	/**
	 * getter for what destination the passenger is going to
	 * @return destination
	 */
	public int getDestination() {
		return destination;
	}

	/**
	 * setter for destination, which is what stop they want to go to
	 * @param destination
	 */
	public void setDestination(int destination) {
		this.destination = destination;
	}

	/**
	 * getter for what time they arrive at the stop
	 * @return timeArrived
	 */
	public int getTimeArrived() {
		return timeArrived;
	}

	/**
	 * setter for what time they arrive at the stop
	 * @param timeArrived
	 */
	public void setTimeArrived(int timeArrived) {
		this.timeArrived = timeArrived;
	}

	/**
	 * getter for how long they waited for the bus to pick them up
	 * @return timeWaited
	 */
	public int getTimeWaited() {
		return timeWaited;
	}

	/**
	 * setter for the time they waited
	 * @param timeWaited
	 */
	public void setTimeWaited(int timeWaited) {
		this.timeWaited = timeWaited;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String stops[]  = {"South P", "West", "SAC", "Chapin","South P","PathMart", "Walmart", "Target"};
		String temp = "[" + group +"," + destination + "(" + stops[destination] +")" +"," + timeArrived +"]";
		return temp;
	}
	

}
