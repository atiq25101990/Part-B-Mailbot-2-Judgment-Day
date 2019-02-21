/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Defines comparison of two mail items to be based on arrival time
 * Changes: None
 */

package strategies;

import java.util.Comparator;

import automail.MailItem;

public class ArrivalComparer implements Comparator<MailItem>{

	@Override
	public int compare(MailItem m1, MailItem m2) {
		return MailPool.compareArrival(m1, m2);
	}
	
}
