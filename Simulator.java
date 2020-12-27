/**
* The <code>Simulator</code> class implements a instance of Simulator
* objects. This is the "main" class for this homework, which has the main loop
* and where we run the simulation
*    

* @author D.J.Y David Justin Yu
*    e-mail: david.yu@stonybrook.edu
*    Stony Brook ID:111922653
**/
package homework4_214;

import java.util.Scanner;


/**
 * our class in which we run the simulation of the buses
 * @author David Yu
 *
 */
public class Simulator {
	private String inRoute[] = { "South P", "West", "SAC", "Chapin", "South P" }; //the stops in the inroute
	private String outRoute[] = { "South P", "PathMart", "Walmart", "Target", "South P" }; //the stops in the outroute

	private int numInBusses, numOutBusses, minGroupSize, maxGroupSize, capacity, groupsServed, totalTimeWaited; //data fields in which we use to run simulation
	private double arrivalProb; //probablity of the passenger arriving at a certation stop
	private int duration; //how long we will run the simulation 
	private int tempDestination =0; //a temporary destination variable

	/**
	 * default constructor
	 */
	public Simulator() {

	}

	/**
	 * constructor in which we use to construct an Simulator object with the specific
	 * data fields the user wants to run the simulation with
	 * @param numInBusses
	 * @param numOutBusses
	 * @param minGroupSize
	 * @param maxGroupSize
	 * @param capacity
	 * @param arrivalProb
	 * @param duration
	 */
	public Simulator(int numInBusses, int numOutBusses, int minGroupSize, int maxGroupSize, int capacity,
			double arrivalProb, int duration) {
		super();
		this.numInBusses = numInBusses;
		this.numOutBusses = numOutBusses;
		this.minGroupSize = minGroupSize;
		this.maxGroupSize = maxGroupSize;
		this.capacity = capacity;
		this.arrivalProb = arrivalProb;
		this.duration = duration;
	}

	/**
	 * our main method in which we create the simulator object and start the simulation
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(true) {
			Simulator x = newSim();
			double[] results = x.simulate(x.duration);
			if (results[0]>0) {
				System.out.println("avg time waited:" + (results[1]) + " minutes, groups of passengers served :" + (int)(results[0]));				
			}
			else
				System.out.println("no passengers served avg time NaN");
			System.out.println("Simulate again? Y/N");
			try {			
				String response = input.nextLine();
				response = response.toUpperCase();
				if (response.equals("Y")) {
					System.out.println("making new simulation!!");
					System.out.println();
					continue;
					
				}
				else {
					System.out.println("system exiting normally");
					System.exit(0);

				}
		
			} catch (Exception e) {
				System.out.println("system exiting normally");
				System.exit(0);			}
			
		}
		

	}

	/**
	 * method in which we use to take inputs from user to create the simulation with
	 * the data fields that the user wants. We return the object that we construct from user's inputs
	 * @return a new Simulation object
	 */
	public static Simulator newSim() {
		Scanner input = new Scanner(System.in);
		int in, out, min, max, cap, dura;
		double p;

		while (true) {
			try {
				System.out.println("Enter the number of In Route busses:");
				in = input.nextInt();
				input.nextLine();
				System.out.println("Enter the number of Out Route busses:");
				out = input.nextInt();
				input.nextLine();
				System.out.println("Enter the minimum group size of passengers:");
				min = input.nextInt();
				input.nextLine();
				System.out.println("Enter the maximum group size of passengers:");
				max = input.nextInt();
				input.nextLine();
				System.out.println("Enter the capacity of a bus:");
				cap = input.nextInt();
				input.nextLine();
				System.out.println("Enter the arrival probability:");
				p = input.nextDouble();
				input.nextLine();

				System.out.println("Enter the duration of the simulation:");
				dura = input.nextInt();
				input.nextLine();
				if (p >= 1 || p <= 0 || max <= min || min < 0) {
					throw new IllegalArgumentException();

				}

				break;

			} catch (IllegalArgumentException e) {
				System.out.println("inputs out of bounds, try again");

			} catch (Exception e) {
				System.out.println("invalid inputs try again");
				input.nextLine();

			}

		}
		return new Simulator(in, out, min, max, cap, p, dura);

	}

	/**
	 * our method in which we run the simulation
	 * @param duration
	 * @return an array holding how many passengers served and the average wait time
	 */
	public double[] simulate(int duration) {
		int time = 1; // initial time
		int totalServed = 0;
		int timeWaited = 0;
		int tempPeople = 0;

		Bus[] inBuses = new Bus[numInBusses];
		Bus[] outBuses = new Bus[numOutBusses];
		// making the buses
		int busTime = 0;
		int nextStopTime = 0;
		for (int i = 0; i < inBuses.length; i++) {
			inBuses[i] = new Bus(capacity, busTime, 0,nextStopTime);
			busTime += 30;
			
		}
		busTime = 0;
		nextStopTime = 0;
		for (int i = 0; i < outBuses.length; i++) {
			outBuses[i] = new Bus(capacity, busTime, 1,nextStopTime);
			busTime += 30;
			
		}

		PassengerQueue[] inBusStops = new PassengerQueue[5];
		PassengerQueue[] outBusStops = new PassengerQueue[5];
		// bus stops
		for (int i = 0; i < 5; i++) {
			inBusStops[i] = new PassengerQueue();
		}
		for (int i = 0; i < 5; i++) {
			outBusStops[i] = new PassengerQueue();
		}

		// main loop
		while (time != duration+1) {
			System.out.println("Minute " + time);
			
			//adding passengers
			tempDestination = 0;
			for (int i = 0; i < 4; i++) {
				if (booleanSource(arrivalProb)) {
					int tempSize = randInt(minGroupSize, maxGroupSize); // make group size
					if(i==0) {
						 tempDestination = randInt(1, 3);
						 inBusStops[i].enqueue(new Passenger(tempSize, tempDestination, time));
							System.out.println("A group of " + tempSize + " passengers arrived at "+inRoute[i] + 
									" heading to " + inRoute[tempDestination]);

						
					}
					else {
						 tempDestination = randInt(i+1, 4);
						 inBusStops[i].enqueue(new Passenger(tempSize, tempDestination, time));
							System.out.println("A group of " + tempSize + " passengers arrived at "+inRoute[i] + 
									" heading to " + inRoute[tempDestination]);

					}
					

				}

			}
			for (int i = 0; i < 4; i++) {
				if (booleanSource(arrivalProb)) {
					int tempSize = randInt(minGroupSize, maxGroupSize); // make group size
					if(i==0) {
						 tempDestination = randInt(1, 3);
						 outBusStops[i].enqueue(new Passenger(tempSize, tempDestination, time));
							System.out.println("A group of " + tempSize + " passengers arrived at "+outRoute[i] + 
									" heading to " + outRoute[tempDestination]);

						
					}
					else {
						 tempDestination = randInt(i+1, 4);
						 outBusStops[i].enqueue(new Passenger(tempSize, tempDestination, time));
							System.out.println("A group of " + tempSize + " passengers arrived at "+outRoute[i] + 
									" heading to " + outRoute[tempDestination]);

					}
					

				}

			}

			// end of add passengers
		
		
			int counter = 0;
			int tempArrived = 0;
			for (int i = 0; i < inBuses.length; i++) { // inbuses
				counter = 0;
				if (inBuses[i].getTimeToRest() == 0) {
				
					if (inBuses[i].getToNextStop() == 0) {
						System.out.print("In Bus "+(i+1)+" arrives at " + inRoute[inBuses[i].getNextStop()]);

						//deload
						for (int j = 0; j < inBuses[i].size(); j++) {
							if (inBuses[i].get(j).getDestination() == inBuses[i].getNextStop()) {
								//totalServed++;
								//timeWaited+=inBuses[i].get(j).getTimeWaited();
								tempPeople += inBuses[i].get(j).getGroup();
								inBuses[i].remove(j);
								j--;
							}
							
						}
						System.out.print(" deloads " + tempPeople +" ");
						tempPeople=0;
			
						
						//end of deload
						int stop = inBuses[i].getNextStop();
						counter = 0;
						while (counter != inBusStops[stop].size()) {
							if (inBusStops[stop].get(counter).getGroup() + inBuses[i].getCurrentPassengers() < capacity) {
								inBuses[i].enqueue(inBusStops[stop].get(counter)); //add to bus
								tempPeople +=inBusStops[stop].get(counter).getGroup();
								tempArrived = inBusStops[stop].get(counter).getTimeArrived(); //time that the person arrived at stop
								inBusStops[stop].get(counter).setTimeWaited(time-tempArrived); //setting the time that they waited
								timeWaited+= inBusStops[stop].get(counter).getTimeWaited(); //increment timewaited for averaging
								totalServed++; //incremenet how many ppl have been served
								inBusStops[stop].remove(counter); //remove them from que

							} else
								counter++;

						}
						System.out.println(" loads " + tempPeople +" ");
						tempPeople =0;
						inBuses[i].setToNextStop(20);
						if (stop==4) {
							inBuses[i].setTimeToRest(30);
							inBuses[i].setToNextStop(0);
							inBuses[i].setNextStop(0);


						}
						else
							inBuses[i].setNextStop(stop+1);

		

					}

				} 
			
					

			}
			
			counter = 0;
			for (int i = 0; i < outBuses.length; i++) { // outbusses
				counter = 0;
				if (outBuses[i].getTimeToRest() == 0) {
			
					if (outBuses[i].getToNextStop() == 0) {
						System.out.print("Out Bus "+(i+1)+" arrives at " + outRoute[outBuses[i].getNextStop()]);
					
						for (int j = 0; j < outBuses[i].size(); j++) {
							if (outBuses[i].get(j).getDestination() == outBuses[i].getNextStop()) {
								//totalServed++;
								tempPeople += outBuses[i].get(j).getGroup();
								//timeWaited += outBuses[i].get(counter).getTimeWaited();

								outBuses[i].remove(j);
								j--;
							}
							
						}
						
						System.out.print(" deloads " + tempPeople +" ");
						tempPeople =0;
				
						
						
						int stop = outBuses[i].getNextStop();
						counter = 0;
						while (counter != outBusStops[stop].size()) {
							if (outBusStops[stop].get(counter).getGroup() + outBuses[i].getCurrentPassengers() < capacity) {
								outBuses[i].enqueue(outBusStops[stop].get(counter));
								tempArrived = outBusStops[stop].get(counter).getTimeArrived();
								tempPeople += outBusStops[stop].get(counter).getGroup();
								outBusStops[stop].get(counter).setTimeWaited(time-tempArrived);
								timeWaited += outBusStops[stop].get(counter).getTimeWaited();
								totalServed++;

								outBusStops[stop].remove(counter);
								

							} else
								counter++;

						}
						
						System.out.println(" loads " + tempPeople +" ");
						tempPeople =0;
						outBuses[i].setToNextStop(20);
						if (stop==4) {
							outBuses[i].setTimeToRest(30);
							outBuses[i].setToNextStop(0);
							outBuses[i].setNextStop(0);


						}
						
						else 
							outBuses[i].setNextStop(stop+1);

		

					}

				} 
			
					

			}//end of adding and removing passengers out busses
			
			for (int i = 0; i < inBuses.length; i++) {
				if (inBuses[i].getTimeToRest()==0) {
					System.out.println("In Route Bus"+ (i+1)+ " is moving towards " + inRoute[inBuses[i].getNextStop()]
							+ " arrives in "+ inBuses[i].getToNextStop());
					
				}
				
			}
			for (int i = 0; i < outBuses.length; i++) {
				if (outBuses[i].getTimeToRest()==0) {
					System.out.println("Out Route Bus"+ (i+1)+ " is moving towards " + outRoute[outBuses[i].getNextStop()]
							+ " arrives in "+ outBuses[i].getToNextStop());
					
				}
				
			}
			for (int j = 0; j < inBuses.length; j++) {
				if (inBuses[j].getTimeToRest()==0) {
					inBuses[j].timeStopDown();
					
				}
				else {
					System.out.println("In Bus " + (j+1) + " is resting for "  + inBuses[j].getTimeToRest());

					inBuses[j].timeRestDown();
					
				}
					
				
			}
			for (int j = 0; j < outBuses.length; j++) {
				if (outBuses[j].getTimeToRest()==0) {
					outBuses[j].timeStopDown();
					
				}
				else {
					System.out.println("Out Bus " + (j+1)+ " is resting for "  + outBuses[j].getTimeToRest());

					outBuses[j].timeRestDown();

					
				}
				
			}
			for (int j = 0; j < inBusStops.length-1; j++) {
				System.out.print(j + "  (" + inRoute[j] +"):");
				for (int j2 = 0; j2 < inBusStops[j].size(); j2++) {
					System.out.print(inBusStops[j].get(j2).toString() +" ");
				}
				System.out.println();
				
			}
			for (int j = 0; j < outBusStops.length-1; j++) {
				System.out.print((j+4) + "  (" + outRoute[j] +"):");
				for (int j2 = 0; j2 < outBusStops[j].size(); j2++) {
					System.out.print(outBusStops[j].get(j2).toString() +" ");
				}
				System.out.println();
				
			}
			
			System.out.println();
			time++;
			

		}
		double average;
		if (timeWaited==0) {
			average = 0;
		}
		else
			average = timeWaited/totalServed;

		double[] x = {totalServed,average};
		return x;

	}

	
	/**
	 * method in which we create a random integer based on a min and max
	 * @param min
	 * @param max
	 * @return a random integer
	 */
	public int randInt(int min, int max) {
		int temp = min+(int)(Math.random()*((max-min)+1));
		return temp;
	}
	/**
	 * method in which rolls a number between 0 and 1 and according to p, which is a probablity
	 * will return true or false
	 * @param p
	 * @return true or false
	 */
	public boolean booleanSource(double p) {
		return (Math.random() < p);
		
	}

}
