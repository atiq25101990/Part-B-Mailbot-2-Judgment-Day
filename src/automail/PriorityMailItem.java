/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Additional properties of priority mail
 * Changes: None
 */

package automail;

public class PriorityMailItem extends MailItem {
	
	/** The priority of the mail item from 1 low to 100 high */
    private int PRIORITY_LEVEL;
    
	public PriorityMailItem(int dest_floor, int priority_level, int arrival_time) {
		super(dest_floor, arrival_time);
        this.PRIORITY_LEVEL = priority_level;
	}
	
	public void setPriorityLevel(int pRIORITY_LEVEL) {
		PRIORITY_LEVEL = pRIORITY_LEVEL;
	}
	
    /**
    *
    * @return the priority level of a mail item
    */
   public int getPriorityLevel(){
       return PRIORITY_LEVEL;
   }
   
   @Override
   public String toString(){
       return super.toString() +
               "| Priority Level: "+ getPriorityLevel()
               ;
   }




}
