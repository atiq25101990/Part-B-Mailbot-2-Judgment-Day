/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * New, smart robot behaviour, without comms
 * Changes: Removed comms capabilities, outsourced to subclass
 */

package strategies;

import java.util.ArrayList;

import automail.MailItem;
import automail.StorageTube;
import exceptions.TubeFullException;

public class SmartRobotBehaviour implements IRobotBehaviour{
	
	public SmartRobotBehaviour(){
	}

	public boolean returnToMailRoom(StorageTube tube) {
		return false;
	}

	public void priorityArrival(int priority) {}

	public boolean fillStorageTube(MailPool mailPool, StorageTube tube) {
		
		ArrayList<MailItem> tempTube = new ArrayList<MailItem>();

		// Empty my tube
		while(!tube.tube.isEmpty()){
			mailPool.addToPool(tube.pop());
		}
		
		// Grab priority mail
		while(tempTube.size() < tube.MAXIMUM_CAPACITY){
			if(containMail(mailPool,MailPool.PRIORITY_POOL)){
				tempTube.add(mailPool.getHighestPriorityMail());
			}
			else{
				// Fill it up with non priority
				if(containMail(mailPool,MailPool.NON_PRIORITY_POOL)){
					tempTube.add(mailPool.getNonPriorityMail());
				}
				else{
					break;
				}
				
			}
		}
		
		// Sort tempTube based on floor
		tempTube.sort(new ArrivalComparer());
		
		// Iterate through the tempTube
		while(tempTube.iterator().hasNext()){
			try {
				tube.addItem(tempTube.remove(0));
			} catch (TubeFullException e) {
				e.printStackTrace();
			}
		}
		
		// Check if there is anything in the tube
		if(!tube.tube.isEmpty()){
			return true;
		}
		return false;
	}
	
	private boolean containMail(MailPool m, String mailPoolIdentifier){
		System.out.println("Small Comms Smart Robot --- contain mail");
		if(mailPoolIdentifier.equals(MailPool.PRIORITY_POOL) && m.getPriorityPoolSize() > 0){
			return true;
		}
		else if(mailPoolIdentifier.equals(MailPool.NON_PRIORITY_POOL) && m.getNonPriorityPoolSize() > 0){
			return true;
		}
		else{
			return false;
		}
	}
	


}
