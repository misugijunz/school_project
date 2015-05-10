/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

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

}
