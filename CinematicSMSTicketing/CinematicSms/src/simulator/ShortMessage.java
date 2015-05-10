/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;

/**
 *
 * @author Oz
 */
public class ShortMessage {

    private String message;
    private String phonenumber;

    ShortMessage(String message, String phonenumber) {
        this.message = message;
        this.phonenumber = phonenumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPhoneNumber() {
        return this.phonenumber;
    }

    public String getClippedMessage() {

        String temp = "";
        temp += this.message.substring(0, 16);
        temp += "...";

        return temp;

    }
    
    public boolean equals(Object o) {
        if (! (o instanceof ShortMessage))
            return false;

        ShortMessage sm = (ShortMessage)o;

        return (this.message.equalsIgnoreCase(sm.getMessage()) && this.phonenumber.equalsIgnoreCase(sm.getPhoneNumber()));
    }

}
