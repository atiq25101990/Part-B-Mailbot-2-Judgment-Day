/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Comms behaviour from the given smart robot behaviour
 * Inherits all other behaviours from regular smart behaviour
 * Overrides filler comms methods with functional methods
 */

package strategies;

import automail.Clock;
import automail.MailItem;
import automail.PriorityMailItem;
import automail.StorageTube;

public class SmartCommsRobotBehaviour extends SmartRobotBehaviour {
	
	private int newPriorityArrival;
	
	public SmartCommsRobotBehaviour() {
		newPriorityArrival = 0;
	}

	
	
	public boolean returnToMailRoom(StorageTube tube) {
		
		System.out.println("Small Comms Smart Robot --- return to mail room");
		// Check if my tube contains only priority items
		if(!tube.isEmpty()){
			int priorityCount = 0;
			int nonPriorityCount = 0;
			// There has to be more priority than non-priority to keep going
			for(MailItem m : tube.tube){
				if(m instanceof PriorityMailItem){
					priorityCount++;
				}
				else{
					nonPriorityCount++;
				}
			}
			
			if(priorityCount >= nonPriorityCount){
				return false;
			}
			else{
				// Check if there is more than 1 priority arrival and the size of the tube is greater than or equal to half
				if(newPriorityArrival > 1 && tube.getSize() >= tube.MAXIMUM_CAPACITY/2){

					return true;
				}
				else{
					return false;
				}
				
			}
		}
		else{
			return true;
		}
	}

	public void priorityArrival(int priority) {
		System.out.println("Small Comms Smart Robot --- priority arrival");
    	// Record that a new one has arrived
		newPriorityArrival++;
    	System.out.println("T: "+Clock.Time()+" | Priority arrived");
		
	}
	
	public boolean fillStorageTube(MailPool mailPool, StorageTube tube) {
		System.out.println("Small Comms Smart Robot --- fill storage");
		if (super.fillStorageTube(mailPool, tube)) {
			newPriorityArrival = 0;
			return true;
		}
		return false;
	}
}
