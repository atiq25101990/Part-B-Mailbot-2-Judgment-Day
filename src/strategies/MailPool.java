/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * General interface for different behavioural strategies
 * Changes: Removed dependance on generalised interface
 * Interface is useful if multiple different mailpool systems are within scope
 * Part B removes requirement for incorporation of alternative mailpool systems
 * Hence interface is unneccesary
 */

package strategies;

import java.util.ArrayList;

import automail.MailItem;
import automail.PriorityMailItem;

public class MailPool {
	
	public static final String PRIORITY_POOL = "PRIORITY_POOL";
	public static final String NON_PRIORITY_POOL = "NON_PRIORITY_POOL";
	
	private ArrayList<MailItem> nonPriorityPool;
	private ArrayList<MailItem> priorityPool;
	
	public MailPool(){
		nonPriorityPool = new ArrayList<MailItem>();
		priorityPool = new ArrayList<MailItem>();
	}
	
	public int getPriorityPoolSize(){
		return priorityPool.size();
	}

	public int getNonPriorityPoolSize() {
		return nonPriorityPool.size();
	}

	// Sort priority mail by priority, non-priority by arrival time
	public void addToPool(MailItem mailItem) {
		// Check whether it has a priority or not
		if(mailItem instanceof PriorityMailItem){
			// Add to priority items
			priorityPool.add(mailItem);
			priorityPool.sort(new PriorityComparer());

		}
		else{
			// Add to nonpriority items
			nonPriorityPool.add(mailItem);
			nonPriorityPool.sort(new ArrivalComparer());
		}
	}
	
	public MailItem getNonPriorityMail(){
		if(getNonPriorityPoolSize() > 0){
			return nonPriorityPool.remove(0);
		}
		else{
			return null;
		}
	}
	
	public MailItem getHighestPriorityMail(){
		if(getPriorityPoolSize() > 0){
			return priorityPool.remove(0);
		}
		else{
			return null;
		}
		
	}
	
	public MailItem getBestMail(int FloorFrom, int FloorTo) {
		
		ArrayList<MailItem> tempPriority = new ArrayList<MailItem>();
		
		// Check if there are any priority mail within the range
		for(MailItem m : priorityPool){
			if(isWithinRange(m,FloorFrom,FloorTo)){
				tempPriority.add(m);
			}
		}
		
		// If there is already something in priority then return it as the best mail
		if(tempPriority.size() > 0){
			// Since priorityPool is already sorted, that means items being added are already sorted with the
			// highest priority being in the front of the arraylist
			
			return tempPriority.get(0);
		}
		else{

			ArrayList<MailItem> tempNonPriority = new ArrayList<MailItem>();
			// Try the same thing with nonPriorityPool
			for(MailItem m : nonPriorityPool){
				if(isWithinRange(m,FloorFrom,FloorTo)){
					tempNonPriority.add(m);
				}
			}
			if(tempNonPriority.size() > 0){
				return tempNonPriority.get(0);
			}
		}
		
		return null;
	}
	
	private boolean isWithinRange(MailItem m, int FloorFrom, int FloorTo){

		if(m.getDestFloor() <= FloorTo && m.getDestFloor() >= FloorFrom){
			return true;
		}
		else{
			return false;
		}
		

	}

	public static int compareArrival(MailItem m1, MailItem m2){
		if(m1.getArrivalTime() < m2.getArrivalTime()){
			return -1;
		}
		else if(m1.getArrivalTime() == m2.getArrivalTime()){
			return 0;
		}
		else{
			return 1;
		}
	}
	

	

	
	
}
