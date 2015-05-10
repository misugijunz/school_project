/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;

import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author Oz
 */
public class SmsSimulator extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command exitInitList;
    private Command backtoinitlistCommand;
    private Form form;
    private StringItem stringItem;
    private SplashScreen splashScreen;
    private List initList;
    private List inboxList;
    private Image imageInbox;
    private Image imageSms;
    private Image imageSplash;
    //</editor-fold>//GEN-END:|fields|0|
    private Command exitCom, replyCom, backCom, clrCom, sendCom, readCom;
    private List menuList;
    private  boolean done;
    private SMSCompozer smsCompozer,smsReader;
    private Vector messagelist;
    private Alert alertError;
    private Alert alertSent;
    private WaitScreen waitScreen;
    private SimpleCancellableTask task;
    private Displayable current;


    /**
     * The SmsSimulator constructor.
     */
    public SmsSimulator() {
        exitCom = new Command("Exit", Command.EXIT, 1);
        replyCom = new Command("Reply", Command.SCREEN, 1);
        backCom = new Command("Back", Command.BACK, 3);
        clrCom = new Command("Clear", Command.OK, 2);
        sendCom = new Command("Send", Command.OK, 1);
        readCom = new Command("Read", Command.OK, 1);
        menuList = new List("Inbox", List.IMPLICIT);
        messagelist = new Vector();

        messagelist.addElement(new ShortMessage("Selamat data anda terdaftar","2121"));
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
                current = form;
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|30-preAction
        } else if (displayable == inboxList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|3|30-preAction
                // write pre-action user code here
                inboxListAction();//GEN-LINE:|7-commandAction|4|30-postAction
                // write post-action user code here
            } else if (command == backtoinitlistCommand) {//GEN-LINE:|7-commandAction|5|42-preAction
                // write pre-action user code here
                switchDisplayable(null, getInitList());//GEN-LINE:|7-commandAction|6|42-postAction
                // write post-action user code here

            }//GEN-BEGIN:|7-commandAction|7|27-preAction
        } else if (displayable == initList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|7|27-preAction
                // write pre-action user code here
                initListAction();//GEN-LINE:|7-commandAction|8|27-postAction
                // write post-action user code here
            } else if (command == exitInitList) {//GEN-LINE:|7-commandAction|9|34-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|10|34-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|24-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|11|24-preAction
                // write pre-action user code here
                switchDisplayable(null, getInitList());//GEN-LINE:|7-commandAction|12|24-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|7-postCommandAction
        }//GEN-END:|7-commandAction|13|7-postCommandAction
        // write post-action user code here
        else if (displayable == smsCompozer) {
            if (command == backtoinitlistCommand) {
                switchDisplayable(null, getInitList());
            } else if (command == SMSCompozer.SEND_COMMAND) {
                switchDisplayable(null,getWaitScreen());
            }
        }
        else if (displayable == waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {
                // write pre-action user code here
                switchDisplayable(getAlertError(), getSmsComposer());
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {
                // write pre-action user code here
                switchDisplayable(getAlertSent(), getSmsComposer());
                try {
                    // write post-action user code here
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                switchDisplayable(getAlertReceivedMessage(), getInboxList());
            }
        }
        else if (displayable == smsReader) {
            if (command == backtoinitlistCommand) {
                switchDisplayable(null, getInitList());
            } else if (command == SMSCompozer.SEND_COMMAND) {
                switchDisplayable(null,getWaitScreen());
            }
        }
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
            form = new Form("Welcome", new Item[] { getStringItem() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
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
            splashScreen.setTitle("Loading");
            splashScreen.setCommandListener(this);
            splashScreen.setImage(getImageSplash());
            splashScreen.setText("Loading...");//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: initList ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of initList component.
     * @return the initialized component instance
     */
    public List getInitList() {
        if (initList == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            initList = new List("Messaging", Choice.IMPLICIT);//GEN-BEGIN:|25-getter|1|25-postInit
            initList.append("New Message", getImageSms());
            initList.append("Inbox", getImageInbox());
            initList.addCommand(getExitInitList());
            initList.setCommandListener(this);
            initList.setSelectedFlags(new boolean[] { false, false });//GEN-END:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return initList;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initListAction ">//GEN-BEGIN:|25-action|0|25-preAction
    /**
     * Performs an action assigned to the selected list element in the initList component.
     */
    public void initListAction() {//GEN-END:|25-action|0|25-preAction
        // enter pre-action user code here
        String __selectedString = getInitList().getString(getInitList().getSelectedIndex());//GEN-BEGIN:|25-action|1|36-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("New Message")) {//GEN-END:|25-action|1|36-preAction
                // write pre-action user code here
//GEN-LINE:|25-action|2|36-postAction
                // write post-action user code here
                switchDisplayable(null, getSmsComposer());
            } else if (__selectedString.equals("Inbox")) {//GEN-LINE:|25-action|3|38-preAction
                // write pre-action user code here
                switchDisplayable(null, getInboxList());//GEN-LINE:|25-action|4|38-postAction
                // write post-action user code here
            }//GEN-BEGIN:|25-action|5|25-postAction
        }//GEN-END:|25-action|5|25-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|25-action|6|
    //</editor-fold>//GEN-END:|25-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: inboxList ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initiliazed instance of inboxList component.
     * @return the initialized component instance
     */
    public List getInboxList() {
        if (inboxList == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            inboxList = new List("Inbox", Choice.IMPLICIT);                                    
            inboxList.addCommand(getBacktoinitlistCommand());
            //inboxList.setCommandListener(this);
            // write post-init user code here

            if (messagelist != null) {

                for (int i = messagelist.size() - 1; i >= 0; i--) {
                    Object _sms = messagelist.elementAt(i);
                    ShortMessage sms = (ShortMessage)_sms;
                    inboxList.append(sms.getPhoneNumber() + "\n" + sms.getClippedMessage(),getImageSms());
                }
            }
			inboxList.setCommandListener(this);
        }
	// write post-init user code here
        else {
            inboxList.deleteAll();
            if (messagelist != null) {

                for (int i = messagelist.size() - 1; i >= 0; i--) {
                    Object _sms = messagelist.elementAt(i);
                    ShortMessage sms = (ShortMessage)_sms;
                    inboxList.append(sms.getPhoneNumber() + "\n" + sms.getClippedMessage(),getImageSms());
                }
            }

			inboxList.setCommandListener(this);

        }//GEN-BEGIN:|29-getter|2|
        return inboxList;
    }
    //</editor-fold>//GEN-END:|29-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: inboxListAction ">//GEN-BEGIN:|29-action|0|29-preAction
    /**
     * Performs an action assigned to the selected list element in the inboxList component.
     */
    public void inboxListAction() {//GEN-END:|29-action|0|29-preAction
        // enter pre-action user code here
        String __selectedString = getInboxList().getString(getInboxList().getSelectedIndex());//GEN-LINE:|29-action|1|29-postAction
        // enter post-action user code here
        if (__selectedString != null) {
            int index;// = inboxList.getSelectedIndex();
            System.out.println(__selectedString);
            ShortMessage sm = null;
            for (index = messagelist.size() - 1; index >= 0; index--) {
                sm = (ShortMessage)messagelist.elementAt(index);
                String hold = sm.getPhoneNumber() + "\n" + sm.getClippedMessage();
                if (hold.equalsIgnoreCase(__selectedString)) break;
            }
            SMSCompozer sco = getSmsReader();
            System.out.println(index);
            System.out.println(sm.getMessage() + " " + __selectedString);
            sco.setPhoneNumber(sm.getPhoneNumber());
            sco.setMessage(sm.getMessage());
            switchDisplayable(null, sco);
        }
    }//GEN-BEGIN:|29-action|2|
    //</editor-fold>//GEN-END:|29-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitInitList ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of exitInitList component.
     * @return the initialized component instance
     */
    public Command getExitInitList() {
        if (exitInitList == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            exitInitList = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|33-getter|1|33-postInit
            // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return exitInitList;
    }
    //</editor-fold>//GEN-END:|33-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageSms ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of imageSms component.
     * @return the initialized component instance
     */
    public Image getImageSms() {
        if (imageSms == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|37-getter|1|37-@java.io.IOException
                imageSms = Image.createImage("/images/sms.png");
            } catch (java.io.IOException e) {//GEN-END:|37-getter|1|37-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|37-getter|2|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|3|
        return imageSms;
    }
    //</editor-fold>//GEN-END:|37-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageInbox ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of imageInbox component.
     * @return the initialized component instance
     */
    public Image getImageInbox() {
        if (imageInbox == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|39-getter|1|39-@java.io.IOException
                imageInbox = Image.createImage("/images/inbox.png");
            } catch (java.io.IOException e) {//GEN-END:|39-getter|1|39-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|39-getter|2|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|3|
        return imageInbox;
    }
    //</editor-fold>//GEN-END:|39-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backtoinitlistCommand ">//GEN-BEGIN:|41-getter|0|41-preInit
    /**
     * Returns an initiliazed instance of backtoinitlistCommand component.
     * @return the initialized component instance
     */
    public Command getBacktoinitlistCommand() {
        if (backtoinitlistCommand == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            backtoinitlistCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|41-getter|1|41-postInit
            // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return backtoinitlistCommand;
    }
    //</editor-fold>//GEN-END:|41-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageSplash ">//GEN-BEGIN:|45-getter|0|45-preInit
    /**
     * Returns an initiliazed instance of imageSplash component.
     * @return the initialized component instance
     */
    public Image getImageSplash() {
        if (imageSplash == null) {//GEN-END:|45-getter|0|45-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|45-getter|1|45-@java.io.IOException
                imageSplash = Image.createImage("/images/SplashScreenHP copy.png");
            } catch (java.io.IOException e) {//GEN-END:|45-getter|1|45-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|45-getter|2|45-postInit
            // write post-init user code here
        }//GEN-BEGIN:|45-getter|3|
        return imageSplash;
    }
    //</editor-fold>//GEN-END:|45-getter|3|

    public SMSCompozer getSmsComposer() {
        if (smsCompozer == null) {
            // write pre-init user code here
            smsCompozer = new SMSCompozer(getDisplay());
            smsCompozer.setTitle("Write SMS");
            smsCompozer.addCommand(SMSCompozer.SEND_COMMAND);
            smsCompozer.addCommand(getBacktoinitlistCommand());
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
            smsReader.removeCommand(SMSCompozer.SEND_COMMAND);
            //smsReader.addCommand(SMSCompozer.SEND_COMMAND);
            smsReader.addCommand(getBacktoinitlistCommand());
            smsReader.setCommandListener(this);
            smsReader.setBGColor(-3355444);
            smsReader.setFGColor(-16777216);
            smsReader.setPhoneNumber("1234");
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

    public void addMessage(Object o){
       messagelist.addElement(o);
       //inboxList.append(((ShortMessage)o).getPhoneNumber() + "\n" + ((ShortMessage)o).getClippedMessage(), imageSms);
    }

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alertSent ">
    /**
     * Returns an initiliazed instance of alertSent component.
     * @return the initialized component instance
     */
    public Alert getAlertSent() {
        if (alertSent == null) {
            // write pre-init user code here
            alertSent = new Alert("alert", "SMS sent", null, null);
            alertSent.setTimeout(Alert.FOREVER);
            // write post-init user code here
        }
        return alertSent;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alertError ">
    /**
     * Returns an initiliazed instance of alertError component.
     * @return the initialized component instance
     */
    public Alert getAlertError() {
        if (alertError == null) {
            // write pre-init user code here
            alertError = new Alert("alertError", "Error", null, null);
            alertError.setTimeout(Alert.FOREVER);
            // write post-init user code here
        }
        return alertError;
    }

    /**
     * Returns an initiliazed instance of alertError component.
     * @return the initialized component instance
     */
    public Alert getAlertReceivedMessage() {
        if (alertError == null) {
            // write pre-init user code here
            alertError = new Alert("SMS Received", "SMS Received", null, null);
            alertError.setTimeout(Alert.FOREVER);
            // write post-init user code here
        }
        return alertError;
    }

    /**
     * Returns an initiliazed instance of waitScreen component.
     * @return the initialized component instance
     */
    public WaitScreen getWaitScreen() {
        if (waitScreen == null) {
            // write pre-init user code here
            waitScreen = new WaitScreen(getDisplay());
            waitScreen.setTitle("Wait");
            waitScreen.setCommandListener(this);
            waitScreen.setTask(getTask());
            // write post-init user code here
        }
        return waitScreen;
    }

    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {
            // write pre-init user code here
            task = new SimpleCancellableTask();
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {
                    // write task-execution user code here
                    //msg = new MessageFetcher(smsCompozer.getMessage(), smsCompozer.getPhoneNumber());
                    String response = smsCompozer.sendSMSSimulator("localhost", 2222);
                    ShortMessage sm = new ShortMessage(response,"1234");
                    addMessage(sm);
                }
            });
            // write post-init user code here
        }
        return task;
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
