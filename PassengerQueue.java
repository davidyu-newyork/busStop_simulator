/**
* The <code>PassengerQueue</code> class implements a instance of PassengerQueue
* objects. This is a Queue, which I impletmented using ArrayList
*    
*Recitation number: 08
* @author D.J.Y David Justin Yu
*    e-mail: david.yu@stonybrook.edu
*    Stony Brook ID:111922653
**/
package homework4_214;

import java.util.ArrayList;

/**
 * this class is the PassengerQueue, in which we make a queue of passengers
 * we extend ArrayList, because thats the data structure we use to implement the queue
 * @author David Yu
 *
 */
public class PassengerQueue extends ArrayList<Passenger>{
	/**
	 * default constructor
	 */
	public PassengerQueue() {
		
	}
	  /**
	   * enqueueing a passenger onto the queue
	 * @param p
	 */
	public void enqueue(Passenger p) {
		  add(p);
	  }
	  /**
	   * dequeue the first passenger in stop
	 * @return temp
	 */
	public Passenger dequeue() {
		  Passenger temp = get(0);
		  remove(0);
		  return temp;
		  
		  
	  }
	  /**
	   * dequeue at specific spot in queue
	 * @param i
	 * i is what index we want to dequeue at
	 * @return temp
	 */
	public Passenger dequeue(int i) {
		  Passenger temp = get(i);
		  remove(i);
		  return temp;
		  
		  
	  }
	  /**
	   * peek method, we return the first passenger in the queue
	 * @return first passenger in queue
	 */
	public Passenger peek() {
		  return get(0);
		  
	  }
	  
	/* (non-Javadoc)
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return super.size();
	}
	/* (non-Javadoc)
	 * @see java.util.ArrayList#isEmpty()
	 */
	public boolean isEmpty() {
		return super.isEmpty();
	}

}
