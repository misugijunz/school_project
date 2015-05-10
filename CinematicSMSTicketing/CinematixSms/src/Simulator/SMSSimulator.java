/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

//import hello.*;
import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;

/**
 * @author Oscar Kurniawan Manule;
 */
public class SMSSimulator extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command backCommand1;
    private Form form;
    private StringItem stringItem;
    private SplashScreen splashScreen;
    private List initmenu;
    private List inboxmenu;
    private Image splashimage;
    private Image newmsgimage;
    private Image inboximage;
    //</editor-fold>//GEN-END:|fields|0|
    private Command exitCom, replyCom, backCom, clrCom, sendCom, readCom;
    private List menuList;
    private  boolean done;
    private SMSCompozer smsCompozer,smsReader;
    private Vector messagelist;

    /**
     * The HelloMIDlet constructor.
     */
    public SMSSimulator() {
        exitCom = new Command("Exit", Command.EXIT, 1);
        replyCom = new Command("Reply", Command.SCREEN, 1);
        backCom = new Command("Back", Command.BACK, 3);
        clrCom = new Command("Clear", Command.OK, 2);
        sendCom = new Command("Send", Command.OK, 1);
        readCom = new Command("Read", Command.OK, 1);
        menuList = new List("Inbox", List.IMPLICIT);
        messagelist = new Vector();
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here

    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|39-preAction
        } else if (displayable == inboxmenu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|3|39-preAction
                // write pre-action user code here
                inboxmenuAction();//GEN-LINE:|7-commandAction|4|39-postAction
                // write post-action user code here
            } else if (command == backCommand1) {//GEN-LINE:|7-commandAction|5|48-preAction
                // write pre-action user code here
                switchDisplayable(null, getInitmenu());//GEN-LINE:|7-commandAction|6|48-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|30-preAction
        } else if (displayable == initmenu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|7|30-preAction
                // write pre-action user code here
                initmenuAction();//GEN-LINE:|7-commandAction|8|30-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|9|45-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|10|45-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|24-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|11|24-preAction
                // write pre-action user code here
                switchDisplayable(null, getInitmenu());//GEN-LINE:|7-commandAction|12|24-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|7-postCommandAction
        }//GEN-END:|7-commandAction|13|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|14|
    //</editor-fold>//GEN-END:|7-commandAction|14|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("SMS Menu", new Item[] { getStringItem() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
            form.addCommand(readCom);
            form.setCommandListener(this);
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            stringItem = new StringItem("Hello", "Hello, World!");//GEN-LINE:|16-getter|1|16-postInit
            // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|16-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|22-getter|1|22-postInit
            splashScreen.setTitle("Please Wait");
            splashScreen.setCommandListener(this);
            splashScreen.setImage(getSplashimage());
            splashScreen.setText("Loading...");//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here
            //splashScreen.setTitle("Loading");
        }//GEN-BEGIN:|22-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|22-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashimage ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of splashimage component.
     * @return the initialized component instance
     */
    public Image getSplashimage() {
        if (splashimage == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|26-getter|1|26-@java.io.IOException
                splashimage = Image.createImage("/images/SplashScreenHP copy.png");
            } catch (java.io.IOException e) {//GEN-END:|26-getter|1|26-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|26-getter|2|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|3|
        return splashimage;
    }
    //</editor-fold>//GEN-END:|26-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: initmenu ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of initmenu component.
     * @return the initialized component instance
     */
    public List getInitmenu() {
        if (initmenu == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            initmenu = new List("Messaging", Choice.IMPLICIT);//GEN-BEGIN:|28-getter|1|28-postInit
            initmenu.append("New Message", getNewmsgimage());
            initmenu.append("Inbox", getInboximage());
            initmenu.addCommand(getExitCommand());
            initmenu.setCommandListener(this);
            initmenu.setSelectedFlags(new boolean[] { false, false });//GEN-END:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return initmenu;
    }
    //</editor-fold>//GEN-END:|28-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initmenuAction ">//GEN-BEGIN:|28-action|0|28-preAction
    /**
     * Performs an action assigned to the selected list element in the initmenu component.
     */
    public void initmenuAction() {//GEN-END:|28-action|0|28-preAction
        // enter pre-action user code here
        String __selectedString = getInitmenu().getString(getInitmenu().getSelectedIndex());//GEN-BEGIN:|28-action|1|32-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("New Message")) {//GEN-END:|28-action|1|32-preAction
                // write pre-action user code here
//GEN-LINE:|28-action|2|32-postAction
                // write post-action user code here
                switchDisplayable(null,getSmsComposer());
            } else if (__selectedString.equals("Inbox")) {//GEN-LINE:|28-action|3|36-preAction
                // write pre-action user code here
                switchDisplayable(null, getInboxmenu());//GEN-LINE:|28-action|4|36-postAction
                // write post-action user code here
            }//GEN-BEGIN:|28-action|5|28-postAction
        }//GEN-END:|28-action|5|28-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|28-action|6|
    //</editor-fold>//GEN-END:|28-action|6|
    //</editor-fold>



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: newmsgimage ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of newmsgimage component.
     * @return the initialized component instance
     */
    public Image getNewmsgimage() {
        if (newmsgimage == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|34-getter|1|34-@java.io.IOException
                newmsgimage = Image.createImage("/images/sms.png");
            } catch (java.io.IOException e) {//GEN-END:|34-getter|1|34-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|34-getter|2|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|3|
        return newmsgimage;
    }
    //</editor-fold>//GEN-END:|34-getter|3|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: inboximage ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of inboximage component.
     * @return the initialized component instance
     */
    public Image getInboximage() {
        if (inboximage == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|37-getter|1|37-@java.io.IOException
                inboximage = Image.createImage("/images/inbox.png");
            } catch (java.io.IOException e) {//GEN-END:|37-getter|1|37-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|37-getter|2|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|3|
        return inboximage;
    }
    //</editor-fold>//GEN-END:|37-getter|3|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: inboxmenu ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of inboxmenu component.
     * @return the initialized component instance
     */
    public List getInboxmenu() {
        if (inboxmenu == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            inboxmenu = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|38-getter|1|38-postInit
            inboxmenu.addCommand(getBackCommand1());
            inboxmenu.setCommandListener(this);//GEN-END:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return inboxmenu;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: inboxmenuAction ">//GEN-BEGIN:|38-action|0|38-preAction
    /**
     * Performs an action assigned to the selected list element in the inboxmenu component.
     */
    public void inboxmenuAction() {//GEN-END:|38-action|0|38-preAction
        // enter pre-action user code here
        String __selectedString = getInboxmenu().getString(getInboxmenu().getSelectedIndex());//GEN-LINE:|38-action|1|38-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|38-action|2|
    //</editor-fold>//GEN-END:|38-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|47-getter|2|

    public SMSCompozer getSmsComposer() {
        if (smsCompozer == null) {
            // write pre-init user code here
            smsCompozer = new SMSCompozer(getDisplay());
            smsCompozer.setTitle("Write SMS");
            smsCompozer.addCommand(SMSCompozer.SEND_COMMAND);
            smsCompozer.addCommand(getExitCommand());
            smsCompozer.setCommandListener(this);
            smsCompozer.setBGColor(-3355444);
            smsCompozer.setFGColor(-16777216);
            smsCompozer.setPhoneNumber("");
            smsCompozer.setMessage("");
            smsCompozer.setSendAutomatically(false);
            smsCompozer.setPhoneNumberLabel("To:");
            smsCompozer.setMessageLabel("Message");
            // write post-init user code here

            //smsCompozer.setPhoneNumberLabel("From:");
            //smsCompozer.setMessageLabel("Message");
        }
        return smsCompozer;
    }

    public SMSCompozer getSmsReader() {
        if (smsReader == null) {
            // write pre-init user code here
            smsReader = new SMSCompozer(getDisplay());
            smsReader.setTitle("Read SMS");
            smsReader.addCommand(SMSCompozer.SEND_COMMAND);
            smsReader.addCommand(getExitCommand());
            smsReader.setCommandListener(this);
            smsReader.setBGColor(-3355444);
            smsReader.setFGColor(-16777216);
            smsReader.setPhoneNumber("2121");
            smsReader.setMessage("");
            smsReader.setSendAutomatically(false);
            smsReader.setPhoneNumberLabel("From:");
            smsReader.setMessageLabel("Message");
            // write post-init user code here

            //smsCompozer.setPhoneNumberLabel("From:");
            //smsCompozer.setMessageLabel("Message");
        }
        return smsReader;
    }

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

}
