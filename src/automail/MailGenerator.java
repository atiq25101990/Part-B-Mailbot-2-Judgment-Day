/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Responsible for generating mail for the simulation
 * Changes: Changed arbitrary constants to depend on properties, moved last_delivery_time over from clock
 */

package automail;

import java.util.*;

import strategies.MailPool;

public class MailGenerator {
	// Time at which last item of mail should be generated
	// Not controlled by properties file
    public static final int LAST_DELIVERY_TIME = 100;

    // Mail to be generated, post-randomisation
    public final int MAIL_TO_CREATE;

    // 
    private final Random random;
    
    private boolean complete;
    private MailPool mailPool;

    private HashMap<Integer,ArrayList<MailItem>> allMail;

    /**
     * Constructor for mail generation
     * @param mailToCreate roughly how many mail items to create
     * @param mailPool where mail items go on arrival
     * @param seed random seed for generating mail
     */
    public MailGenerator(MailPool mailPool, long seed){
        this.random = new Random(seed);
        // Vary arriving mail by property amount
        MAIL_TO_CREATE = SystemProperties.getMailtoCreate() * (100 - SystemProperties.getMailCountPercentageVariation()) / 100
        		+ random.nextInt(SystemProperties.getMailtoCreate() * 2 * SystemProperties.getMailCountPercentageVariation()/100);
        complete = false;
        allMail = new HashMap<Integer,ArrayList<MailItem>>();
        this.mailPool = mailPool;
    }

    /**
     * @return a new mail item that needs to be delivered
     */
    private MailItem generateMail(){
        int dest_floor = generateDestinationFloor();
        int priority_level = generatePriorityLevel();
        int arrival_time = generateArrivalTime();
        // Check if arrival time has a priority mail
        if(	(random.nextInt(SystemProperties.getPriorityMailisOnein()) > 0) ||  // Skew towards non priority mail
        	(allMail.containsKey(arrival_time) &&
        	allMail.get(arrival_time).stream().anyMatch(e -> PriorityMailItem.class.isInstance(e)))){
        	return new MailItem(dest_floor,arrival_time);      	
        }
        else{
        	return new PriorityMailItem(dest_floor,priority_level,arrival_time);
        }   
    }

    /**
     * @return a destination floor between the ranges of GROUND_FLOOR to FLOOR
     */
    private int generateDestinationFloor(){
        return SystemProperties.getLowestFloor() + random.nextInt(SystemProperties.getNumberofFloors());
    }

    /**
     * @return a random priority level selected from 1 - 100
     */
    private int generatePriorityLevel(){
        return 1 + random.nextInt(100);
    }
    
    /**
     * @return a random arrival time before the last delivery time
     */
    private int generateArrivalTime(){
        return 1 + random.nextInt(LAST_DELIVERY_TIME);
    }

    /**
     * This class initializes all mail and sets their corresponding values,
     */
    public void generateAllMail(){
    	int mailCreated = 0;
        while(!complete){
            MailItem newMail =  generateMail();
            int timeToDeliver = newMail.getArrivalTime();
            /** Check if key exists for this time **/
            if(allMail.containsKey(timeToDeliver)){
                /** Add to existing array */
                allMail.get(timeToDeliver).add(newMail);
            }
            else{
                /** If the key doesn't exist then set a new key along with the array of MailItems to add during
                 * that time step.
                 */
                ArrayList<MailItem> newMailList = new ArrayList<MailItem>();
                newMailList.add(newMail);
                allMail.put(timeToDeliver,newMailList);
            }
            /** Mark the mail as created */
            mailCreated++;

            /** Once we have satisfied the amount of mail to create, we're done!*/
            if(mailCreated == MAIL_TO_CREATE){
                complete = true;
            }
        }

    }
    
    /**
     * While there are steps left, create a new mail item to deliver
     * @return Priority, used to notify Robot
     */
    public int step(){
    	int priority = 0;
    	// Check if there are any mail to create
        if(this.allMail.containsKey(Clock.Time())){
            for(MailItem mailItem : allMail.get(Clock.Time())){
                mailPool.addToPool(mailItem);
                if (mailItem instanceof PriorityMailItem) priority = ((PriorityMailItem) mailItem).getPriorityLevel();
                System.out.println("T: "+Clock.Time()+" | Arrive    " + mailItem.toString());
            }
        }
        return priority;
    }
    
}
