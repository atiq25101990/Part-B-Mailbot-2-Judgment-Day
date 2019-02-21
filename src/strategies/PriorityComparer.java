/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Defines comparison of two priority mail items to be based on priority
 * Changes: None
 */

package strategies;

import java.util.Comparator;

import automail.MailItem;
import automail.PriorityMailItem;

/**
 * Comparator classes and helper method
 *
 */
public class PriorityComparer implements Comparator<MailItem> {
	// Compare Priority level, if they are the same, try comparing arrival time
	public int compare(MailItem m1, MailItem m2){
		if(((PriorityMailItem)m1).getPriorityLevel() > ((PriorityMailItem)m2).getPriorityLevel()){
			return -1;
		}
		else if(((PriorityMailItem)m1).getPriorityLevel() == ((PriorityMailItem)m2).getPriorityLevel()){
			return MailPool.compareArrival(m1,m2);
		}
		else{
			return 1;
		}
	}
	

}
