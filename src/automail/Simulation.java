/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Controls and initialises simulation, including scoring system
 * Changes: Outsourced property handling to dedicated controller, incorporated score tracking
 */

package automail;

import exceptions.ExcessiveDeliveryException;
import strategies.StrategyInitialiser;

/**
 * This class simulates the behaviour of AutoMail
 */
public class Simulation {

	private static double totalScore = 0;

    public static void main(String[] args){
    	SystemProperties.load("automail.properties");
        StrategyInitialiser strategyInitialiser = new StrategyInitialiser();
        MailGenerator generator = new MailGenerator(strategyInitialiser.mailPool, SystemProperties.getSeed());
        
        /** Initiate all the mail */
        generator.generateAllMail();
        int priority;
       
        
        while(Delivery.getMailDelivered().size() != generator.MAIL_TO_CREATE) {
        	//System.out.println("-- Step: "+Clock.Time());
            priority = generator.step();
            if (priority > 0) strategyInitialiser.robot.behaviour.priorityArrival(priority);
            try {
				strategyInitialiser.robot.step();
			} catch (ExcessiveDeliveryException e) {
				e.printStackTrace();
				System.out.println("Simulation unable to complete..");
				System.exit(0);
			}
            Clock.Tick();
        }
        printResults();
    }
    
    public static void scoreDelivery(MailItem deliveryItem) {
    	// Penalty for longer delivery times
    	final double penalty = 1.1;
    	double priority_weight = 0;
        // Take (delivery time - arrivalTime)**penalty * (1+sqrt(priority_weight))
    	if(deliveryItem instanceof PriorityMailItem){
    		priority_weight = ((PriorityMailItem) deliveryItem).getPriorityLevel();
    	}
    	totalScore += Math.pow(Clock.Time() - deliveryItem.getArrivalTime(),penalty)*(1+Math.sqrt(priority_weight));
    }

    public static void printResults(){
        System.out.println("T: "+Clock.Time()+" | Simulation complete!");
        System.out.println("Final Delivery time: "+Clock.Time());
        System.out.printf("Final Score: %.2f%n", totalScore);
    }
}