/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SMSMonitoring.java
 *
 * Created on 09 Des 09, 22:51:38
 */

package cinematicserver;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Oz
 */
public class SMSMonitoring extends javax.swing.JFrame implements WindowListener {

    /** Creates new form SMSMonitoring */
    public SMSMonitoring(boolean status) {
        if (status)
            initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(cinematicserver.CinematicServerApp.class).getContext().getResourceMap(SMSMonitoring.class);
        jTextArea1.setForeground(resourceMap.getColor("jTextArea1.foreground")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText(resourceMap.getString("jTextArea1.text")); // NOI18N
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setBackground(resourceMap.getColor("jLabelJudul.background")); // NOI18N
        jLabel1.setFont(resourceMap.getFont("jLabelJudul.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabelJudul.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabelJudul.text")); // NOI18N
        jLabel1.setName("jLabelJudul"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, resourceMap.getColor("jMenuBar1.border.highlightOuterColor"), resourceMap.getColor("jMenuBar1.border.highlightInnerColor"), resourceMap.getColor("jMenuBar1.border.shadowOuterColor"), resourceMap.getColor("jMenuBar1.border.shadowInnerColor"))); // NOI18N
        jMenuBar1.setForeground(resourceMap.getColor("jMenuBar1.foreground")); // NOI18N
        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N
        jMenuBar1.add(jMenu2);

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws IOException {
        String lineread ="";
        String parsed;
        boolean status;
        KeywordParser parse = new KeywordParser("011011");
        //SMSMonitoring monitor = new SMSMonitoring(false);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SMSMonitoring monitor = new SMSMonitoring(true);
                monitor.setVisible(true);
                SMSMonitoring.monitor = monitor;
            }
        });

        try {
           echoServer = new ServerSocket(2222);
        }
        catch (IOException e) {
           System.out.println(e);
        }

        try {
           //clientSocket = echoServer.accept();
           //dis = new DataInputStream(clientSocket.getInputStream());
           //os = new PrintStream(clientSocket.getOutputStream());

           while (true) {

            clientSocket = echoServer.accept();
            dis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
             System.out.println("test\n");

             while (dis.ready()){
                lineread = dis.readLine();
             System.out.println("test after read\n");}

             /*while (lineread == null) {
                  lineread = dis.readLine();
                  //System.out.println("test\n");
             }*/


             monitor.setEditableTxtArea(true);
             monitor.appendText("SMS diterima dari +6285691231234");
             Thread.sleep(200);
             
             monitor.appendText("\tTeks diterima " + lineread);
             Thread.sleep(100);
             monitor.appendText("\tTeks diproses...");
             Thread.sleep(300);
             parsed = parse.getResponse(lineread);
             status = parse.isError();
             monitor.appendText("\tPemrosesan teks selesai");
             Thread.sleep(100);
             monitor.appendText(status);
             monitor.appendText("\tTeks Respon: " + parsed);
             Thread.sleep(100);
             monitor.appendText("\tPengiriman SMS teks respon...");
             System.out.println("Data akan dikirim");
             os.println(parsed);
             os.flush();
             System.out.println("Data sudah dikirim");
             Thread.sleep(400);
             monitor.appendText("\tSMS teks respon berhasil dikirim");
             monitor.setEditableTxtArea(false);
             System.out.println("reached here");
             
           }
        }
        catch (InterruptedException ex) {
            //Logger.getLogger(SMSMonitoring.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException e) {
           System.out.println(e);
        }


    }

    public void appendText(String text) {

        jTextArea1.append(text + "\n");

    }

    public void appendText(boolean status) {
        jTextArea1.append("\tStatus pemrosesan: ");

        //int pos_awal = jTextArea1.getCaretPosition();
        //int pos_akhir;

        if (!status) {
            //jTextArea1.setCaretColor(warningColorTxtArea);
            jTextArea1.append("Berhasil\n");
            //jTextArea1.setCaretColor(defaultColorTxtArea);
             //pos_akhir = jTextArea1.getCaretPosition();
             //jTextArea1.select(pos_awal, pos_akhir);
            // jTextArea1.setSelectedTextColor(successColorTxtArea);
             //jTextArea1.setSelectionColor(successColorTxtArea);
             //jTextArea1.setCaretColor(successColorTxtArea);
             //jTextArea1.setForeground(successColorTxtArea);
        }
        else {
            //jTextArea1.set(successColorTxtArea);
            jTextArea1.append("Gagal\n");
            //jTextArea1.setCaretColor(defaultColorTxtArea);
             //pos_akhir = jTextArea1.getCaretPosition();
             //jTextArea1.select(pos_awal, pos_akhir);
             //jTextArea1.setSelectedTextColor(warningColorTxtArea);
             //jTextArea1.setSelectionColor(warningColorTxtArea);
             //jTextArea1.setCaretColor(warningColorTxtArea);
             //jTextArea1.setForeground(warningColorTxtArea);

        }


    }

    public void setEditableTxtArea(boolean value) {
         jTextArea1.setEditable(value);
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables


    private static ServerSocket echoServer = null;
    private static BufferedReader dis;
    private static PrintStream os;
    private static Socket clientSocket = null;

    private final static Color defaultColorTxtArea = Color.BLACK;
    private final static Color warningColorTxtArea = Color.RED;
    private final static Color successColorTxtArea = Color.GREEN;

    public static SMSMonitoring monitor;

    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowClosing(WindowEvent e) {
        try {
            //throw new UnsupportedOperationException("Not supported yet.");
            dis.close();
            os.close();
            clientSocket.close();
            echoServer.close();
        } catch (IOException ioex) {

        }
    }

    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
