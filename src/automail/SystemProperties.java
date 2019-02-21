/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Responsible for handling all simulation aspects relating to properties file
 * Changes: Brand new, replaced building class
 */

package automail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SystemProperties {
	
	// Properties + Getters, pre-typed
	// No setters, as properties are constant within any one simulation
	private static long SEED;
	public static long getSeed() {
		return SEED;
	}
	private static int NUMBER_OF_FLOORS;
	public static int getNumberofFloors() {
		return NUMBER_OF_FLOORS;
	}
	private static int LOWEST_FLOOR;
	public static int getLowestFloor() {
		return LOWEST_FLOOR;
	}
	private static int MAILROOM_FLOOR;
	public static int getMailRoomFloor() {
		return MAILROOM_FLOOR;
	}
	private static float DELIVERY_PENALTY;
	public static float getDeliveryPenalty() {
		return DELIVERY_PENALTY;
	}
	private static int LAST_DELIVERY_TIME;
	public static int getLastDeliveryTime() {
		return LAST_DELIVERY_TIME;
	}
	private static int MAIL_TO_CREATE;
	public static int getMailtoCreate() {
		return MAIL_TO_CREATE;
	}
	// Could also be defined as float
	// Opted against to avoid potential unexpected FPA differences when marking
	private static int MAIL_COUNT_PERCENTAGE_VARIATION;
	public static int getMailCountPercentageVariation() {
		return MAIL_COUNT_PERCENTAGE_VARIATION;
	}
	private static int PRIORITY_MAIL_IS_ONE_IN;
	public static int getPriorityMailisOnein() {
		return PRIORITY_MAIL_IS_ONE_IN;
	}
	private static String ROBOT_TYPE;
	public static String getRobotType() {
		return ROBOT_TYPE;
	}
	
	// Returns true if properties file loaded successfully, false if not
	public static boolean load(String fileName) {
		Properties automailProperties = new Properties();
		boolean success = true;
		
		// Load Properties File
		FileReader inStream = null;
		
		try {
			inStream = new FileReader(fileName);
			if(inStream != null){
				automailProperties.load(inStream);
			}
		} catch(IOException e) {
			e.printStackTrace();
			success = false;
		} catch(Exception e) {
			e.printStackTrace();
			success = false;
		} finally {
			 try {
				if (inStream != null) {
				        inStream.close();
				    }
			} catch (IOException e) {
				e.printStackTrace();
				success = false;
			}
		}
		
		// Now set static values for easy access
		// Parse everything to appropriate types now for easy access
		// Randomise seed if no value is given
    	if (automailProperties.getProperty("Seed") == null) {
    		SEED = System.nanoTime();
    	} else {
    		SEED = Long.parseLong(automailProperties.getProperty("Seed"));
    	}
    	
    	NUMBER_OF_FLOORS = Integer.parseInt(automailProperties.getProperty("Number_of_Floors"));
    	LOWEST_FLOOR = Integer.parseInt(automailProperties.getProperty("Lowest_Floor"));
    	MAILROOM_FLOOR = Integer.parseInt(automailProperties.getProperty("Location_of_MailRoom"));
    	DELIVERY_PENALTY = Float.parseFloat(automailProperties.getProperty("Delivery_Penalty"));
    	LAST_DELIVERY_TIME = Integer.parseInt(automailProperties.getProperty("Last_Delivery_Time"));
    	MAIL_TO_CREATE = Integer.parseInt(automailProperties.getProperty("Mail_to_Create"));
    	MAIL_COUNT_PERCENTAGE_VARIATION = Integer.parseInt(automailProperties.getProperty("Mail_Count_Percentage_Variation"));
    	PRIORITY_MAIL_IS_ONE_IN = Integer.parseInt(automailProperties.getProperty("Priority_Mail_is_One_in"));
    	ROBOT_TYPE = automailProperties.getProperty("Robot_Type");
    	
    	
    	
    	return success;
	}
}