/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Controls mail delivery, tracks delivered mail
 * Changes: Removed dependance on interface, shifted scoring system over to simulation, changed methods to static
 */

package automail;

import java.util.ArrayList;

import exceptions.MailAlreadyDeliveredException;

public class Delivery {
	
	// List of mail delivered so far
	private static ArrayList<MailItem> MAIL_DELIVERED = new ArrayList<MailItem>();
	
	// Getter
	public static ArrayList<MailItem> getMailDelivered() {
		return MAIL_DELIVERED;
	}
	
	// Process, archive and score the delivery
	public static void deliver(MailItem deliveryItem){
		if(!MAIL_DELIVERED.contains(deliveryItem)){
			System.out.println("T: "+Clock.Time()+" | Delivered " + deliveryItem.toString());
			MAIL_DELIVERED.add(deliveryItem);
			// Calculate delivery score
			Simulation.scoreDelivery(deliveryItem);
		}
		else{
			try {
				throw new MailAlreadyDeliveredException();
			} catch (MailAlreadyDeliveredException e) {
				e.printStackTrace();
			}
		}
	}
}