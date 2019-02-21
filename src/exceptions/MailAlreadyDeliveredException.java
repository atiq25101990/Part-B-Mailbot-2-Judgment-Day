/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Changes: None
 */

package exceptions;

/**
 * An exception thrown when a mail that is already delivered attempts to be delivered again.
 */
@SuppressWarnings("serial")
public class MailAlreadyDeliveredException extends Throwable    {

    public MailAlreadyDeliveredException(){
        super("This mail has already been delivered!");
    }
}
