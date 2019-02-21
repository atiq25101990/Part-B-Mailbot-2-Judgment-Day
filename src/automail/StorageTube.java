/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Defines the storage tube used by the robot to carry mail
 * Changes: Incorporated potential for different sized tubes
 */

package automail;

import exceptions.TubeFullException;

import java.util.Stack;

/**
 * The storage tube carried by the robot.
 */
public class StorageTube {
	
	// Define options for different sized tubes
	// Only need two values, actual tube class seemed most intuitive location
	public static final int SMALL_TUBE_SIZE = 4;
	public static final int BIG_TUBE_SIZE = 6;

	// Actual tube size, defined on creation
    public final int MAXIMUM_CAPACITY;
    // Tube is internally represented as stack
    public Stack<MailItem> tube;

    /**
     * Constructor for the storage tube
     */
    public StorageTube(int MAXIMUM_CAPACITY){
        this.tube = new Stack<MailItem>();
        this.MAXIMUM_CAPACITY = MAXIMUM_CAPACITY;
    }

    /**
     * @return if the storage tube is full
     */
    public boolean isFull(){
        return tube.capacity() == MAXIMUM_CAPACITY;
    }

    /**
     * @return if the storage tube is empty
     */
    public boolean isEmpty(){
        return tube.isEmpty();
    }
    
    /**
     * @return the first item in the storage tube (without removing it)
     */
    public MailItem peek() {
    	return tube.peek();
    }

    /**
     * Add an item to the tube
     * @param item The item being added
     * @throws TubeFullException thrown if an item is added which exceeds the capacity
     */
    // Removed pointless internal reference copy - slightly improved efficiency
    public void addItem(MailItem item) throws TubeFullException {
        if(getSize() + 1 <= MAXIMUM_CAPACITY){
        	tube.add(item);
        } else {
            throw new TubeFullException();
        }
    }

    /** @return the size of the tube **/
    public int getSize(){
    	return tube.size();
    }
    
    /** 
     * @return the first item in the storage tube (after removing it)
     */
    public MailItem pop(){
        return tube.pop();
    }

}
