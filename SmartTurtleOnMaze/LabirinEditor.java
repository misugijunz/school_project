import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar; 
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NullPointerException;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.SecurityException; 
import java.util.Formatter;        
import java.util.FormatterClosedException;
import java.util.Random;
import ExceptionFolder.*;

/**
 * MainFrame.java
 *
 * Created on February 19, 2008
 * 
 * @author Denvil Prasetya, Oscar Kurniawan
 */
public class LabirinEditor extends javax.swing.JFrame implements Serializable {
    private LabirinEditor labirinFrame;
     private JFileChooser jFileChooser1;
      private String fileName = null;       //variable that contain the name of file
       private char[][] map;
        private char construct = '#';       //variable that indicates the active tool
         private ObjectOutputStream outputStream;
          private ObjectInputStream inputStream;
           private FileNameExtensionFilter filter;
            private JRadioButtonMenuItem toolButton [];
           private boolean saved = false;
          private canvasEditor canvas;
         private JButton finish;
        private Graphics2D g;
       private BufferedImage imgLabirin;
      private JLabel status;            //status label for information of condition of active tool
     private Formatter out;
    private boolean statusLine = false;     //variable that indicates the view line
   private InformasiLabirin info;
  private CanvasLabirin arena;

    
    /** Creates new LabirinEditor
     * 
     * @param kanvas - JPanel that funcioned to show image in the arena
     */
    public LabirinEditor(CanvasLabirin kanvas) {
        imgLabirin = new BufferedImage(440,440,BufferedImage.TYPE_INT_ARGB);    //make new image from BufferedImage's class
         initComponents();                                                      //initialising all component that required
          jFileChooser1 = new JFileChooser("./labirin/");                       //set destination folder
           filter = new FileNameExtensionFilter("*.txt", "txt");                //make filter for extension ".txt"
            jFileChooser1.setFileFilter(filter);                                //add filter to chooser
             info = new InformasiLabirin(this, true, "Labirin Editor", "penjelasan-editor");              //create the information
              arena = kanvas;

        map = new char[22][22];                     //reserve the array of char to keep the maze
         for(int i = 0; i < map.length; i++)
          for(int j = 0; j < map[i].length; j++)
           map[j][i] = ' ';                         //set the contain of maze with street
                
        for( int i=0; i<22; i++ ) {                 //set the border of maze with wall
            map[i][0]='#';
             map[0][i]='#';
              map[i][21]='#';
               map[21][i]='#';
        }
    }
    
    /**
     * Creates new LabirinEditor
     * 
     * @param location - the path of file's location that will opened by editor
     * 
     * @param kanvas - JPanel that funcioned to show image in the arena
     */
    public LabirinEditor(String location, CanvasLabirin kanvas) {
        fileName = location;
         imgLabirin = new BufferedImage(440,440,BufferedImage.TYPE_INT_ARGB);
          initComponents();
           jFileChooser1 = new JFileChooser("./labirin/");
            filter = new FileNameExtensionFilter("*.txt", "txt");
             jFileChooser1.setFileFilter(filter);
              map = new char[22][22];
               for( int i=0; i<22; i++ ) {                 //set the border of maze with wall
                   map[i][0]='#';
                    map[0][i]='#';
                     map[i][21]='#';
                      map[21][i]='#';
                    }
                    info = new InformasiLabirin(this, true, "Labirin Editor", "penjelasan-editor");       //create the information
                     readMap(location);                      //read the map from data
                      arena = kanvas;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
         
        
        JMenu fileMenu = new JMenu( "File" ); // create file menu
         fileMenu.setMnemonic( 'f' ); // set mnemonic to F 

        JMenuBar bar = new JMenuBar(); // create menu bar 
         setJMenuBar( bar ); // add menu bar to application
          bar.add( fileMenu ); // add file menu to menu bar
        
        JMenuItem openItem = new JMenuItem( "Open", new ImageIcon("./Icon/open.JPG") ); // create exit item
         openItem.setMnemonic( 'o' ); // set mnemonic to x                
          fileMenu.add( openItem ); // add exit item to file menu

        JMenuItem saveItem = new JMenuItem("Save", new ImageIcon("./Icon/save.jpg") );
         saveItem.setMnemonic( 's' );
          fileMenu.add( saveItem );
        
        JMenuItem saveAsItem = new JMenuItem("Save As", new ImageIcon("./Icon/save.jpg") );
         saveAsItem.setMnemonic( 'a' );
          fileMenu.add( saveAsItem );
        
        JMenuItem transferItem = new JMenuItem("Transfer to arena");
         transferItem.setMnemonic( 'e' );
          fileMenu.add(transferItem);
          
        fileMenu.addSeparator(); // add separator bar to file menu
         JMenuItem exitItem = new JMenuItem( "Exit", new ImageIcon("./Icon/exit.jpg") ); // create exit item
          exitItem.setMnemonic( 'x' ); // set mnemonic to x                
           fileMenu.add( exitItem ); // add exit item to file menu
        
        JMenu toolMenu = new JMenu( "Tool" ); // create action menu
         toolMenu.setMnemonic( 't' ); // set mnemonic to A
          bar.add( toolMenu ); // add action menu to menu bar
        
        JMenu viewMenu = new JMenu("View" );
         viewMenu.setMnemonic( 'v' );
          bar.add( viewMenu );
        
        JMenuItem GridMenuItem = new JMenuItem("Grid", new ImageIcon("./Icon/grid.jpg"));
         GridMenuItem.setMnemonic( 'g' );
          viewMenu.add( GridMenuItem );
        
        JMenu helpItem = new JMenu("Help");
         helpItem.setMnemonic( 'h' );
          bar.add(helpItem);
          
        JMenuItem aboutMenuItem = new JMenuItem("About", new ImageIcon("./icon/about.jpg"));
         aboutMenuItem.setMnemonic( 'u' );
          helpItem.add(aboutMenuItem);
        
        JMenuItem informationMenuItem = new JMenuItem("Information", new ImageIcon("./icon/help.jpg"));
         informationMenuItem.setMnemonic( 'n' );
          helpItem.add(informationMenuItem);
 
        
        status = new JLabel();                                                  //this component used to show the status of the active tool
         status.setFont( new java.awt.Font("Arial", 0, 12) );
          status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
           status.setText("Tool aktif : Tembok");
            status.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
             getContentPane().add(status);
              status.setBounds(10, 10, 440, 30);
        
        toolButton = new JRadioButtonMenuItem[ 4 ];                                                         //set the radiobutton of tool
        ButtonGroup toolButtonGroup = new ButtonGroup(); // manages tool names
           toolButton[0] = new JRadioButtonMenuItem( "Tembok", new ImageIcon("./icon/tembok.jpg") );
            toolButton[1] = new JRadioButtonMenuItem( "Jalan", new ImageIcon("./icon/jalan.jpg") );
             toolButton[2] = new JRadioButtonMenuItem( "Start", new ImageIcon("./icon/start.jpg") );
              toolButton[3] = new JRadioButtonMenuItem( "Exit", new ImageIcon("./icon/finish.jpg") );
    for( int h=0; h<4; h++ ) {
            toolMenu.add(toolButton[h]);
             toolButtonGroup.add(toolButton[h]);
              toolButton[h].addActionListener(new java.awt.event.ActionListener(){
                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                        gantiActionPerformed(evt);
                    }
             });
        }
    toolButton[0].setSelected( true );
        
        openItem.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        
        saveAsItem.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    saveAsActionPerformed(evt);
                }
                catch( Exception e ){
                    JOptionPane.showMessageDialog(null, "Aborts save action", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        saveItem.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        
        exitItem.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed( java.awt.event.ActionEvent evt ) {
                 closingEditorActionPerformed(evt);
              }
        });

        aboutMenuItem.addActionListener( new java.awt.event.ActionListener(){
            public void actionPerformed( java.awt.event.ActionEvent evt ) {
                JOptionPane.showMessageDialog(null, "by :\nDenvil\nOscar","About", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        informationMenuItem.addActionListener( new java.awt.event.ActionListener(){
            public void actionPerformed( java.awt.event.ActionEvent evt ) {
                info.setVisible(true);
            }
        });
        
        transferItem.addActionListener( new java.awt.event.ActionListener(){
            public void actionPerformed( java.awt.event.ActionEvent evt ) {
                transferActionPerformed(evt);
            }
        });
        
        GridMenuItem.addActionListener( new java.awt.event.ActionListener(){
            public void actionPerformed( java.awt.event.ActionEvent evt ) {
                viewLineActionPerformed(evt);           //view the line for the editor to make the user easier to edit maze
            }
        });
          
        setTitle("Labirin Editor");                         //set title of frame
         setMinimumSize(new java.awt.Dimension(470, 550));   //set size of frame
          setName("mainFrame");                               // NOI18N
           setResizable(false);                                //set unresizeable
            getContentPane().setLayout(null);                   //set type of layout

        canvas = new canvasEditor();
         canvas.setBackground(new java.awt.Color(204, 255, 255));
          canvas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
           canvas.setFocusable(false);
            canvas.setMaximumSize(new java.awt.Dimension(440, 440));
             canvas.setMinimumSize(new java.awt.Dimension(440, 440));
              canvas.setPreferredSize(new java.awt.Dimension(440, 440));
               canvas.setLayout(null);
                getContentPane().add(canvas);
                 canvas.setBounds(11, 48, 440, 440);

        MouseHandler handler = new MouseHandler();            //make the handler for canvas
         canvas.addMouseListener( handler );                  //if mouse clicked
          canvas.addMouseMotionListener( handler );           //if mouse move
           canvas.addMouseWheelListener( handler );           //if mouse scrolled
         
        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * open the file
     * 
     * @evt - event that reacting if open menu selected
     */
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        for(int i = 0; i < map.length; i++)
          for(int j = 0; j < map[i].length; j++)
           map[j][i] = '#';                         //set the contain of maze with wall
        
        int returnVal = jFileChooser1.showOpenDialog(this);
        try{    
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileName = jFileChooser1.getSelectedFile().getPath();
                readMap(fileName);
            }
        }
        catch(Exception e){}
    }
    
    /**
     * read the contain of file and auto repair if possible
     * 
     * @location - the String that contain the path of file's location
     */
    private void readMap(String location) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(location));    //make the buffered reader for the file in the spesific location
                String s;                   //temp variable for storing the contain of file
                 int i, j;
                  boolean broken = false;     //set status of maze to great condition
                
                for( i=1; i<=20; i++ ){
                    s = input.readLine();               //take all character/number/symbol in one line
                    if( s.length() < 20 ){              //if the length of line < 20
                        broken = true;                  //set the condition of maze to broken
                        for( int m=0; m<(20-s.length()); m++ )
                        s += "#";                       //add the wall to null area in that line
                    }
                        
                    for( j=1; j<=20; j++ ) {
                        map[i][j] = s.charAt(j - 1);            //store the data to map
                        if( map[i][j]!='m' && map[i][j]!='k')           // if the data not identified,
                         if( map[i][j]!='#' && map[i][j]!=' ' ) {       // so, it will change to street
                             map[i][j] = ' ';
                             broken = true;
                            }
                        }
                     setTitle(fileName);  
                    canvas.createLabirin();             //call method to paint the maze
                   canvas.repaint();                    //update the appearance
                 }
          if( broken ) JOptionPane.showMessageDialog(null, "Auto Repair", "File Broken", 1);
                //if maze is broken, and it can repaired, the maze will drawed and this warning occured
          input = null;
        }
        catch( Exception e )
         {JOptionPane.showMessageDialog(null, "Too Much Failure", "File Broken", 1);
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("notepad " + fileName);
                //rt.exec("C:\\Documents and Settings\\Oscar Kurniawan\\Desktop\\tugas 25 april b\\Labirin\\labirin gagal.txt");
            }
            catch(java.util.concurrent.RejectedExecutionException a){}
            catch(IOException z) {}
        }
                //else if maze is broken, and can't repaired, the maze won't drawed, and this warning occured
    }//end method readMap
    
    /**
     * change the active tool, that contain street, wall, start, and exit
     * 
     * @param evt - event that reacting if radio button clicked or mouse clicked for change tool
     */
    private void gantiActionPerformed(java.awt.event.ActionEvent evt ) {
        if( toolButton[0].isSelected() ) {
            construct = '#';    status.setText("Tool aktif : Tembok"); }    //activated wall tool
         if( toolButton[1].isSelected() ) {
             construct = ' ';    status.setText("Tool aktif : Jalan"); }    //activated street tool
          if( toolButton[2].isSelected() ) {
              construct = 'm';    status.setText("Tool aktif : Start"); }   //activated start tool
           if( toolButton[3].isSelected() ) {
               construct = 'k';    status.setText("Tool aktif : Exit"); }   //activated exit tool
    }//end method gantiActionPerformed
    
    /**
     * closing the Maze Editor
     * 
     * @param evt - event that reacting if exit menu selected
     */
    private void closingEditorActionPerformed(java.awt.event.ActionEvent evt) {
        boolean success = true;
        try {
            if( !saved ) {
            JOptionPane.showMessageDialog(null, "Your edited maze haven't been saved.", "", JOptionPane.WARNING_MESSAGE);
                saveAsActionPerformed(evt);
            }//end if
        }//end try
        catch( Exception e ) { success = false; }
        finally {
            if( success )
            setVisible(false); // exit LabirinEditor
        }
    }//end method closingEditorActionPerformed
    
    /**
     * transfer the current edited maze to arena and didn't save the current maze
     * 
     * @param evt - event that reacting if transfer maze is selected
     */
    private void transferActionPerformed(java.awt.event.ActionEvent evt) {
       arena.setMap(map);
       arena.createLabirin();
       int tempX = 0, tempY=0;
            for( int i=0; i<22; i++ )
             for( int j=0; j<22; j++ )
              if( map[i][j] == 'm' ) {
                  tempX = j;
                  tempY = i;
                  break;
                }
       if( tempX != 0 && tempY != 0 )         
       arena.setAnimalPosition(tempX, tempY);
       arena.repaint();
    }
    
    /**
     * save the maze that has been edited to the same file
     * 
     * @param evt - event that reacting if save menu is selected
     */
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if( fileName == null )
                saveAsActionPerformed(evt);
            else {
                out = new Formatter (fileName);     //prepare the file for saving
                 saved = true;
                  saveToText();                      //save the maze to file
                   out.close();                      //closing the access to this file
                    setTitle(fileName);
                } //end else
        }//end try
        catch(NullPointerException f){}
         catch( FileNotFoundException e ){}
          catch( SecurityException e ){}
    }//end method saveActionPerformed
    
    /**
     * save the maze that has been edited to the other file
     * 
     * @param evt - event that reacting if save as menu is selected
     */
    private void saveAsActionPerformed(java.awt.event.ActionEvent evt) throws NullPointerException,
        FileNotFoundException, SecurityException {
        try {
            saved = true;
            jFileChooser1.showSaveDialog(this);
             fileName = jFileChooser1.getSelectedFile().getPath();  //get path of file's location selected
              if(! fileName.endsWith(".txt")) fileName+= ".txt";    //set the name of file with extension ".txt"
        
            out = new Formatter(fileName);      //prepare the formatter for file that used to saving
             saveToText();                      //write the maze to file
              out.close();                      //closing accesss to this file
               setTitle(fileName);
        }//end try
        catch(NullPointerException f){throw f;}
         catch( FileNotFoundException e ) {throw e;}
          catch( SecurityException d ) {throw d;}
    }
    
    /**
     * write the maze to file
     */
    private void saveToText() {
        try {
            for( int i=1; i<21; i++ ) {
                for( int j=1; j<21; j++ )
                 out.format("%c", map[i][j]);   //write the character
                  out.format("\r\n");           //write the enter line
            }
        }
        catch(NullPointerException e) {}
    }
    
    /**
     * set the maze in Editor
     * 
     * @param peta - the array of char 2 dimension that contain space, wall, m, and k
     */
    public void setMap(char[][] peta) {
        for( int i=0; i<22; i++ )
         for( int j=0; j<22; j++ )
          map[i][j] = peta[i][j];
    }
    
    /**
     * refresh the appearance of editor with same method as repaint
     */
    public void refresh() {
        canvas.createLabirin();
        canvas.repaint();
    }
    
    /**
     * set the CanvasLabirin in LabirinEditor
     */
    public void setCanvas( CanvasLabirin newCanvas ) {
        arena = newCanvas;
    }
    
    /**
     * view the line for editor to make the user easier to edit maze
     * 
     * @param evt - event that reacting if view grid menu selected
     */
    private void viewLineActionPerformed(java.awt.event.ActionEvent evt) {
        if( statusLine )
            statusLine = false;     //set the negation of status line
                else 
                    statusLine = true;
            
        canvas.createLabirin(); //make canvas to repaint the maze
        canvas.repaint();       //refresh the appearance
    }//end method viewLineActionPerformed
    
    /**
     * inner class for mouse handler
     * implements MouseListener, MouseMotionListener
     */
    private class MouseHandler extends MouseAdapter
    {
        //method that starting if mouse clicked
        public void mouseClicked( MouseEvent event )
        {
            int x = (int)((event.getX()/20));    int y = (int)((event.getY()/20));
            if( !event.isAltDown() && !event.isMetaDown() )
               if( x>0 && x<21 && y >0 && y<21 ) {
                    map[y][x] = construct;
                 canvas.createLabirin();
                 canvas.repaint();
                 }
        } // end method mouseClicked

        // handle event when user drags mouse with button pressed
        public void mouseDragged( MouseEvent event )
        {
             int x = (int)((event.getX()/20));
                int y = (int)((event.getY()/20));
                    if( x>0 && x<21 && y >0 && y<21 )
                      if( !event.isAltDown() && !event.isMetaDown() ){
                        map[y][x] = construct;              //make the selected region to active tool
                         canvas.createLabirin();            //canvas recreate the modified maze
                          canvas.repaint();                 //refresh the appearance
            }//end if
        } // end method mouseDragged
        
        public void mousePressed( MouseEvent event )
        {
            if( event.isMetaDown() ) {                      //if right button of mouse clicked
                if( toolButton[0].isSelected() ) {          //jika yang aktif tembok
                    construct = ' ';    status.setText("Tool aktif : Jalan");
                     toolButton[1].setSelected(true);       //maka yang aktif adalah jalan
                      }
                       else {
                           construct = '#';    status.setText("Tool aktif : Tembok");
                            toolButton[0].setSelected(true); //maka yang aktif adalah tembok
                }
            }//end if
            else if( event.isAltDown() ) {                  //if middle button of mouse clicked
                if( toolButton[2].isSelected() ) {          //jika yang aktif start
                    construct = 'k';    status.setText("Tool aktif : Exit");
                     toolButton[3].setSelected(true);       //maka yang aktif adalah exit
                      }
                       else {
                           construct = 'm';    status.setText("Tool aktif : Start");
                            toolButton[2].setSelected(true); //maka yang aktif adalah tembok
                }
            }//end if
        }//end method mousePressed
        
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            Random rnd = new Random(); // random number generator
            int temp;
            for( int i=1; i<21; i++ )
             for( int j=1; j<21; j++ ) {
                 temp = rnd.nextInt(2);             //randomly make street or wall
                 switch(temp) {
                     case 1: map[i][j] = '#';
                             break;
                     default:map[i][j] = ' ';
                    } //end switch
                }//end for
              map[1+rnd.nextInt(20)][1+rnd.nextInt(20)] = 'm';  //create the start
              map[1+rnd.nextInt(20)][1+rnd.nextInt(20)] = 'k';  //create the exit
              canvas.createLabirin();                           //reconstruct maze
              canvas.repaint();                                 //refresh the appearance
        }//end method mouseWheelMoved
    }//end inner class mouseHandler
    
    /**
     * inner class that contain the JPanel for the arena of drawing
     * extends JPanel
     */
    private class canvasEditor extends JPanel {
        @Override       //override the method repaint()
        public void paintComponent(java.awt.Graphics g){
            super.paintComponent(g);
            try{
                Thread.sleep(50);
            }
            catch( Exception e ){}
            g.drawImage(imgLabirin,0,0,null);       //draw the image to arena
        }
        
        //method for create the maze
        public void createLabirin(){
            int i,j;
            saved = false;
            g = imgLabirin.createGraphics();        //set g for create graphics

            for(i = 0; i < map.length; i++){
                for(j = 0; j < map[i].length; j++){
                    if (map[j][i]=='#')                 //chain of if, to set the color of pixel
                     g.setColor(Color.DARK_GRAY);
                        else if (map[j][i]=='m')
                         g.setColor(Color.WHITE);
                            else if (map[j][i]=='k')
                             g.setColor(Color.BLUE);
                                else 
                                 g.setColor(Color.CYAN);
                 g.fill3DRect(i*20, j*20, 20, 20, true);
                }//end for 2
            }//end for 1
            if( statusLine ) makeLine();        //if status line is true, then the line will created
        }
        
        private void makeLine() {   //create the line
            g.setColor(Color.GREEN);        //set the color of line with green
             for( int i=1; i<22; i++ ){
                g.drawLine( i*20, 20, i*20, 420 );  //  draw line
                 g.drawLine( 20, i*20, 420, i*20 );  //
            }
        }//end method makeLine
    }//end inner class canvasEditor
}//end class LabirinEditor
