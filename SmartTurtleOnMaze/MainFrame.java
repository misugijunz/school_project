import java.io.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar; 
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.reflect.*;
import java.lang.NullPointerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.util.concurrent.RejectedExecutionException;
import ExceptionFolder.*;


/**
 *
 * MainFrame.java
 *
 * Created on February 13, 2008, 3:00 PM
 * 
 * @author  DPBO 2008 @ Fasilkom UI
 * @author Denvil Prasetya
 * @author Oscar Kurniawan
 */
public class MainFrame extends javax.swing.JFrame implements Serializable {
    static MainFrame mainFrame;
     private InformasiLabirin info;    
      private Binatang hewan;
        private JFileChooser jFileChooser1;
         private String fileName;
          private String labirinAddress;
           private String[] sa;
            private String[] member = new String[100];
           private String currentName = new String("KurakuraLabirin");
          private int f = 0;
         private JRadioButtonMenuItem [] binatang;
        private String [] binatangNames;
       private ObjectOutputStream outputStream;
      private ObjectInputStream inputStream;
    private FileNameExtensionFilter filter;
   private LabirinEditor editor;
    
    /** Creates new form MainFrame */
    public MainFrame() {
        info = new InformasiLabirin(mainFrame,true, "Kura Kura dalam Labirin", "penjelasan-tugas3");
        
        File cwd = new File(".");         //create the object of class File with folder local is the folder that contain this source code
          sa = cwd.list();                //input all the name of file in folder to array a
          for(String s : sa) {
               if(s.endsWith(".class")) {
                   try {
                        Class<? extends Binatang> cl = Class.forName(s.substring(0, s.length() - 6)).asSubclass(Binatang.class);
                        //with generic, check *.class, input the name of file if extends Binatang's class
                         member[f] = s.substring(0, s.length() - 6);    //all the name of file without ".class" in the end of name
                         f++;                                           //count how many the file that extends Binatang's Class
                        }
                    catch(Exception e) { }
               }//end if
          }//end for
                  
        for ( int i=0; i<f; i++ )           //this action is to make the new instance of default animal, that is KurakuraLabirin
          {
              if ( ( member[i].equalsIgnoreCase("KurakuraLabirin") )  )         //if the name is KurakuraLabirin
              {
              try{
                  Class<?> _cl = Class.forName(member[i]);                          //call the class for name KurakuraLabirin
                   Class<? extends Binatang> cl = _cl.asSubclass(Binatang.class);    //make sure the class extends Binatang.class
                    Constructor<? extends Binatang> co = cl.getConstructor();         //call the constructor for this class without parameter
                     hewan = co.newInstance();                                         //create the animal of KurakuraLabirin
                      currentName = member[i];                                          //String that identify what's kind of active animal now
                     break;
                    }
                  catch( Exception e ){System.out.println( "error " + member[i] );}
                 }
               }
             initComponents();
            hewan.setSize(canvas.getWidth(), canvas.getHeight());                       //set default size of buffered Image that used to make the footstep of animal
          hewan.reset();                                                              //make the position of animal in the center of main arena
         hewan.setLabirin(canvas);
       jFileChooser1 = new JFileChooser("./labirin/");                             //make the default pointer of chooser to sub folder labirin
    }
    
    
    /** This method is called from within the constructor to
     *  initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();      //the outer label for main information of program
         labelTugas = new javax.swing.JLabel();      //the inner label for main information of program
          canvas = new CanvasTicTacToe(hewan);          //make new JPanel for main arena
           labelInput = new javax.swing.JLabel();      //label that contain information for input command
           input = new javax.swing.JTextField();       //the text field that we can write our command to animal
            lakukan = new javax.swing.JButton();        //the button that make our command delivered to animal
             status = new javax.swing.JLabel();          //the label that used to tell us the condition of program
              edit = new javax.swing.JButton();
        
        JMenu fileMenu = new JMenu( "File" ); // create file menu
         fileMenu.setMnemonic( 'f' ); // set mnemonic to F 

        JMenuBar bar = new JMenuBar(); // create menu bar 
         setJMenuBar( bar ); // add menu bar to application
          bar.add( fileMenu ); // add file menu to menu bar
        
        JMenuItem openItem = new JMenuItem( "Open", new ImageIcon("./icon/open.jpg") ); // create exit item with the icon "open.jpg"
         openItem.setMnemonic( 'o' ); // set mnemonic to x                
          fileMenu.add( openItem ); // add exit item to file menu

        JMenuItem saveItem = new JMenuItem("Save", new ImageIcon("./icon/save.jpg"));
         saveItem.setMnemonic( 's' );
          fileMenu.add( saveItem );
        
        JMenuItem loadItem = new JMenuItem("Load", new ImageIcon("./icon/save as.jpg"));
         loadItem.setMnemonic( 'l' );
          fileMenu.add( loadItem );
        
        fileMenu.addSeparator();                                                        // add separator bar to file menu
         JMenuItem exitItem = new JMenuItem( "Exit", new ImageIcon("./icon/exit.jpg") ); // create exit item
          exitItem.setMnemonic( 'x' );                                                    // set mnemonic to x                
           fileMenu.add( exitItem );                                                       // add exit item to file menu        
        
        JMenu actionMenu = new JMenu( "Action" ); // create action menu
         actionMenu.setMnemonic( 'a' );           // set mnemonic to A
          bar.add( actionMenu );                  // add action menu to menu bar
            JMenu gantiBinatangItem = new JMenu("Ganti Binatang");
             gantiBinatangItem.setMnemonic( 'g' );
              actionMenu.add( gantiBinatangItem );
        
        JMenuItem editorMenu = new JMenuItem("Labirin Editor", new ImageIcon("./icon/grid.jpg"));
         editorMenu.setMnemonic( 'l' );
          actionMenu.add( editorMenu );
          
        JMenuItem transferMenu = new JMenuItem("Transfer to editor");
         transferMenu.setMnemonic( 'e' );
          actionMenu.add( transferMenu );
        
        JMenuItem resetMenu = new JMenuItem("Reset", new ImageIcon( "./icon/reset.jpg" ));
         resetMenu.setMnemonic( 'r' );
          actionMenu.add( resetMenu );
          
        JMenu changeColorMenu = new JMenu("Change color");
         changeColorMenu.setMnemonic( 'm' );
          bar.add(changeColorMenu);
            JMenuItem tembokTool = new JMenuItem("Tembok");
             JMenuItem jalanTool = new JMenuItem("Jalan");
              JMenuItem startTool = new JMenuItem("Start");
               JMenuItem exitTool = new JMenuItem("Exit");
                JMenuItem moveTool = new JMenuItem("Move");
                 changeColorMenu.add(tembokTool);
                  changeColorMenu.add(jalanTool);
                   changeColorMenu.add(startTool);
                    changeColorMenu.add(exitTool);
                     changeColorMenu.add(moveTool);
            
        openItem.addActionListener(new java.awt.event.ActionListener(){             //add the listener to openItem, that will call the method to open the file
            public void actionPerformed(java.awt.event.ActionEvent evt) {           //the anonymous class in the method
                openFileActionPerformed(evt);                                       //call the method to open the file
            }
        });
        
        saveItem.addActionListener(new java.awt.event.ActionListener(){             //add the listener to save, that will call the method to save the condition of program
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        
        loadItem.addActionListener(new java.awt.event.ActionListener(){             //add the listener to load, that will call the method to load from saved data
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });
        
        editorMenu.addActionListener(new java.awt.event.ActionListener(){           //add the listener to open the labirin's editor
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editorActionPerformed(evt);
            }
        });
        
        resetMenu.addActionListener(new java.awt.event.ActionListener(){            //add the listener to clear the foot print of animal
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset(evt);
            }
        });
        
        transferMenu.addActionListener(new java.awt.event.ActionListener(){            //add the listener to clear the foot print of animal
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferActionPerformed(evt);
            }
        });
       
        exitItem.addActionListener(new java.awt.event.ActionListener() // anonymous inner class
           {
              public void actionPerformed( java.awt.event.ActionEvent evt ) // terminate application when user clicks exitItem
              {
                 try {
                  System.exit( 0 ); // exit application
                }
                catch( RejectedExecutionException e ){}
              } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener

        tembokTool.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed( java.awt.event.ActionEvent evt ) {
                     tembokColorActionPerformed(evt);
              }
        });
        
        jalanTool.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed( java.awt.event.ActionEvent evt ) {
                     jalanColorActionPerformed(evt);
              }
        });
        
        startTool.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed( java.awt.event.ActionEvent evt ) {
                     startColorActionPerformed(evt);
              }
        });
        
        exitTool.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed( java.awt.event.ActionEvent evt ) {
                     exitColorActionPerformed(evt);
              }
        });
        
        moveTool.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed( java.awt.event.ActionEvent evt ) {
                     moveColorActionPerformed(evt);
              }
        });

        
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//this segment is the code that make the radio button for change the active animal while the program is running

        binatangNames= new String[f-1];                 //array of String that will contain all the name of animal
         int temp=0;
          for (int i=0;i<f;i++)                           //this process is to transfer the name of all animal to object binatangNames
            if(!member[i].equalsIgnoreCase("binatang")){
                binatangNames[temp] = new String(member[i]);
                temp++;
            }
            
        // create radiobutton menu items for animal names
        binatang = new JRadioButtonMenuItem[ binatangNames.length ];
        ButtonGroup binatangButtonGroup = new ButtonGroup(); // manages animal names
       
        //this code is to get the location of animal's images from file "kurakuraku.properties" in the folder
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       
         String[] nameOfAnimal = new String[f-1];
         for( int g=0; g<binatangNames.length; g++ )
            nameOfAnimal[g] = bundle.getString(binatangNames[g]);       //transfer the string of image's location to nameOfAnimal
        
        for ( int count = 0 ; count < binatang.length; count++ )        //this code is to make the new radio button with the name and icon, and packing them
        {
           binatang[ count ] = new JRadioButtonMenuItem( binatangNames[ count ], new ImageIcon(nameOfAnimal[count]) );
           
           gantiBinatangItem.add(binatang[count]);          // add radio button of animal to gantiBinatang menu
            binatangButtonGroup.add( binatang[ count ] );    // add radio button of animal to binatang group
             binatang[ count ].addActionListener(new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt) {
              gantiActionPerformed(evt);                  //add listener to each of radio button, so if clicked, will change the active animal
             }
         });
        } // end for

        for( int k=0; k<binatang.length; k++   )            //this code is to make the default active radio button is radio button that contain text "KurakuraLabirin"
         if( binatangNames[k].equalsIgnoreCase("KurakuraLabirin") )
          binatang[ k ].setSelected( true );
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
       
        JMenu helpMenu = new JMenu("Help");
         helpMenu.setMnemonic( 'h' );
          bar.add( helpMenu );
        
        JMenuItem aboutItem = new JMenuItem("About", new ImageIcon("./icon/about.jpg"));
         helpMenu.add( aboutItem );
        
        JMenuItem informationItem = new JMenuItem("Information", new ImageIcon("./icon/help.jpg"));
         helpMenu.add( informationItem );
        
        aboutItem.addActionListener( new java.awt.event.ActionListener(){
            public void actionPerformed( java.awt.event.ActionEvent evt ) {
                JOptionPane.showMessageDialog(null, "by :\nDenvil\nOscar","About", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        informationItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informasiActionPerformed(evt);
            }
        });
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);        //set the function to terminate the program if EXIT clicked
         setTitle("Tugas 3");                                                        //set the title of main frame
          setMinimumSize(new java.awt.Dimension(470, 647));                           //set the minimum size of frame
           setName("mainFrame");                                                       //
            setResizable(false);                                                        //make the user cannot resize the frame while the program is running
             getContentPane().setLayout(null);                                           //set the type of layout
              String imageName = "./icon/" + currentName.substring(0, currentName.length()-7) + ".jpg";
               Image imgg = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
                setIconImage(imgg);

        labelTitle.setFont(new java.awt.Font("Arial", 0, 18));
         labelTitle.setText("Kuliah DPBO 2008 - Fasilkom UI");
          getContentPane().add(labelTitle);                                           //getContentPane().add(xxxx) is the method that used to add the component to frame
           labelTitle.setBounds(90, 10, 260, 22);                                      //setBounds used to set the location of component in the frame and set the size of this component

        labelTugas.setFont(new java.awt.Font("Arial", 0, 18));
         labelTugas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);       //make the position of component in the center horizontally
          labelTugas.setText("Tugas 3 - Kura-kura dalam labirin ");
           getContentPane().add(labelTugas);
            labelTugas.setBounds(70, 40, 310, 22);

        canvas.setBackground(new java.awt.Color(204, 255, 255));                    //set the color of background with 3 parameters of integer( value of RGB combination colors )
         canvas.setBorder(javax.swing.BorderFactory.createEtchedBorder());           //set the type of border of main arena
          canvas.setFocusable(false);
           canvas.setMaximumSize(new java.awt.Dimension(440, 440));
            canvas.setMinimumSize(new java.awt.Dimension(440, 440));
             canvas.setPreferredSize(new java.awt.Dimension(440, 440));
              canvas.setLayout(null);
               getContentPane().add(canvas);
                canvas.setBounds(10, 139, 440, 440);
                
                MouseHandler handler = new MouseHandler();
                 canvas.addMouseListener( handler );
                  canvas.addMouseMotionListener( handler );

        labelInput.setFont(new java.awt.Font("Arial", 0, 11));
         labelInput.setText("Perintah Anda:");
          labelInput.setMaximumSize(new java.awt.Dimension(200, 14));
           labelInput.setMinimumSize(new java.awt.Dimension(200, 14));
            getContentPane().add(labelInput);
             labelInput.setBounds(10, 60, 80, 14);
             
        

        input.setText("Tuliskan perintah anda disini");
        input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        input.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputFocusGained(evt);
            }
        });
        getContentPane().add(input);
        input.setBounds(10, 80, 230, 20);

        lakukan.setFont(new java.awt.Font("Arial", 0, 11));
         lakukan.setText("Lakukan");
          lakukan.setMaximumSize(new java.awt.Dimension(100, 23));
           lakukan.setMinimumSize(new java.awt.Dimension(100, 23));
            lakukan.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                 lakukanActionPerformed(evt);
            }
        });
        getContentPane().add(lakukan);
        lakukan.setBounds(250, 80, 110, 20);

        status.setFont(new java.awt.Font("Arial", 0, 12));
         status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          status.setLabelFor(input);
           status.setText("Tuliskan Perintah Anda atau tekan salah satu tombol");
            status.setToolTipText("Reaksi hewan");
             status.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
              status.setFocusable(false);
               getContentPane().add(status);
                status.setBounds(10, 110, 440, 30);
                
       edit.setFont(new java.awt.Font("Arial", 0, 14));
        edit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
           edit.setText("Editor");
            edit.setToolTipText("Labirin Editor");
              edit.setFocusable(false);
               getContentPane().add(edit);
                edit.setBounds(370, 80, 80, 20);
                 edit.addActionListener(new java.awt.event.ActionListener() {
                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                         editorActionPerformed(evt);
                }
            });
        
        pack();         //used to packing all the component that initialized in method initComponents()
    }// </editor-fold>//

    private void tembokColorActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.changeTembok();
    }

    private void jalanColorActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.changeJalan();
    }
    
    private void startColorActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.changeStart();
    }
    
    private void exitColorActionPerformed(java.awt.event.ActionEvent evt) {
        canvas.changeExit();
    }
    
    private void moveColorActionPerformed(java.awt.event.ActionEvent evt) {
        hewan.changeColorMove();
    }
   
    private void lakukanActionPerformed(java.awt.event.ActionEvent evt) {
        inputActionPerformed(evt);
}

    private void editorActionPerformed(java.awt.event.ActionEvent evt) {
        editor = new LabirinEditor(canvas);
         editor.setVisible(true);
}

    private void reset(java.awt.event.ActionEvent evt) {
        try {
            hewan.newJejak();
             canvas.repaint();
          }
        catch(NullPointerException e){}
}

    /**
     * show information window for main program 
     *
     * @param event - the event that reacting if info button clicked
     */
    private void informasiActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                info.setVisible(true);          //set the visibility of info true
            }
        });
}

    /**
     * @param event - the event that reacting if focus event reacting
     */
    private void inputFocusGained(java.awt.event.FocusEvent evt) {
        input.setText("");
}

    /**
     * @param event - the event that reacting if the user input the command
     */
    private void inputActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            if (!(input.getText().isEmpty()))
                status.setText("  "+ hewan.lakukan(input.getText()));    //make the object of Binatang to make the animal do the command
            else 
                status.setText("  hewan siap menerima perintah.");          //if that is the null command, then the status is the animal is ready to get the command
        }
        catch( NullPointerException e ) {
             status.setText("  Labirin tidak ada.");//if the animal do the command and make the exception, this is happened if the maze is null, so the status is doesn't have the maze
        }
}
    
    /**
     * transfer current maze to editor
     */
    private void transferActionPerformed(java.awt.event.ActionEvent evt) {
        editor = new LabirinEditor(canvas);           //make new Labirin Editor
         editor.setMap(canvas.getMap());                            //set the map from canvas to editor
          editor.refresh();                                         //refresh the appearance of editor
           editor.setVisible(true);                                 //show the window of editor
}

    /**
     * Open file of maze to be painted in arena 
     *
     * @param evt - The event that reacting if open menu clicked
     */
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        
        jFileChooser1 = new JFileChooser("./labirin/");             //set the default folder that will opened
        filter = new FileNameExtensionFilter("*.txt", "txt");       //create the extension's filter of file name, so the extensions just ".txt" that will showed
        jFileChooser1.setFileFilter(filter);                        //add filter to chooser

        int returnVal = jFileChooser1.showOpenDialog(this);         //open the open window and return the point from user's selection
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileName = jFileChooser1.getSelectedFile().getPath();
            try {
                 labirinAddress = ((CanvasLabirin)canvas).openLabirin(jFileChooser1.getSelectedFile().getPath());        //make the object canvas from CanvasLabirin class to paint the contents of the file
                  status.setText("..." + labirinAddress.substring(labirinAddress.length()/2, labirinAddress.length()));   //show the maze file name to status label
                   hewan.newJejak();
             }
        catch( FileNotFoundException e ) {
            JOptionPane.showMessageDialog(null, "Thread File Exist Detected!", "Not Found", JOptionPane.WARNING_MESSAGE);   //chain of Exception...
            Ask ask = new Ask(evt);         //this is the Inner class Ask in the MainFrame's class, that used to ask the user again if Exception occured
        }
        catch(StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Thread Column Detected!", "Broken Size", JOptionPane.WARNING_MESSAGE);
            Ask ask = new Ask(evt);
        }
        catch(FormatException e) {
            JOptionPane.showMessageDialog(null, "Thread Format Detected!", "Broken contain", JOptionPane.WARNING_MESSAGE);
            Ask ask = new Ask(evt);
        }
        catch(NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Thread Row Detected!", "Broken Size", JOptionPane.WARNING_MESSAGE);
            Ask ask = new Ask(evt);
        }
        catch( NoStartException e ) {
            JOptionPane.showMessageDialog(null, "Thread No Start Detected!", "Broken Door", JOptionPane.WARNING_MESSAGE);
            Ask ask = new Ask(evt);   
        }
        catch( MoreStartException e ) {
            JOptionPane.showMessageDialog(null, "Thread More Start Detected!", "Broken Door", JOptionPane.WARNING_MESSAGE);
            Ask ask = new Ask(evt);
        }
        catch( NoEndException e ) {
            JOptionPane.showMessageDialog(null, "Thread No End Detected!", "Broken Door", JOptionPane.WARNING_MESSAGE);
            Ask ask = new Ask(evt);
        }
        catch( MoreEndException e ) {
            JOptionPane.showMessageDialog(null, "Thread More End Detected!", "Broken Door", JOptionPane.WARNING_MESSAGE);
            Ask ask = new Ask(evt);
        }
        catch(IOException e){}
    }
}
    
    /**
     * Change the active animal in the arena
     * 
     * @param evt - event that reacting if user choose radio button to change the animal in arena
     */
    private void gantiActionPerformed(java.awt.event.ActionEvent evt ) {
        int sama = 0;                   //variable that indicate the new and old animal is the same if sama=1
        int berhasil = 0;               //variable that indicate the new animal have created and different from the old animal if berhasil=1
        for( int j=0; j<f-1; j++  ) {
            if( binatang[j].isSelected() ) {
                if( currentName.equalsIgnoreCase(binatangNames[j]) ) sama = 1;      //indicates the same animal?
                for ( int i=0; i<f; i++ ) {
                    if ( member[i].equalsIgnoreCase(binatangNames[j]) ) {
                        try{
                            Class<?> _cl = Class.forName(member[i]);
                            Class<? extends Binatang> cl = _cl.asSubclass(Binatang.class);
                            Constructor<? extends Binatang> co = cl.getConstructor( Binatang.class );   //call the constructor of animal that choosen by user
                            hewan = co.newInstance(hewan);                                              //make new animal with copy attribute of the old animal
                              
                            currentName = member[i];                        //update the name of current active animal
                            canvas.setAnimal(hewan);                        //set new animal to canvas
                            hewan.setLabirin(canvas);                       //set the maze for new animal
                            canvas.repaint();                               //refresh appearance
                            berhasil = 1;                                   //change the value of variable berhasil that means new animal has been created
                            status.setText("Hewan baru berhasil terbentuk");    //set the status that new animal has been created
                            
                            String imageName = "./icon/" + currentName.substring(0, currentName.length()-7) + ".jpg";
                             Image imgg = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
                              setIconImage(imgg);
                            break;
                        }
                        catch( Exception e ){System.out.println( "error " + member[i] );} 
                    }
                } //end for
                if( berhasil == 0) status.setText("Hewan baru gagal terbentuk");        //if change the active animal is fail
                if( sama == 1 ) status.setText("Hewan baru sama dengan hewan lama");    //if the new and old animal is the same
                break;
            } //end if(binatang[j].isSelected())
        }//end for
             
 }
 
    /**
     * Save the situation of program with extension ".ser"
     * 
     * @param evt - event that reacting if save menu selected
     */
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {
        filter = new FileNameExtensionFilter("*.ser", "ser");       //filter of .ser file
        jFileChooser1 = new JFileChooser("./Saved data/");          //set the destination folder
                closeFileLoad();                        //close access for file.setFileFilter(filter);                        //add filter to chooser
        try {
            jFileChooser1.showSaveDialog(this);                     //show save dialog
        }
        catch( java.awt.HeadlessException e ) {
            System.out.println("save dialog error");
        }
        try {
        String fileLocation = jFileChooser1.getSelectedFile().getPath();    //get the path of file that selected by user
        if(! fileLocation.endsWith(".ser")) fileLocation+= ".ser";          //set the default name of saved data with extension .ser
        
            try {
                openFileSave(fileLocation);                                 //create file used to save
                outputStream.writeObject(hewan);                            //save active animal
                outputStream.writeObject(canvas);                           //save the maze
                outputStream.writeObject(hewan.getRGB());                   //save the foot print of animal
                outputStream.writeObject(canvas.getMap());                  //save the maze in array of char 2 dimension
                closeFileSave();                                            //close access to file
                jFileChooser1 = null;
            }
            catch ( IOException ioException ) {
                System.out.println( "Error writing to file." );
            }
       }
       catch( java.lang.NullPointerException e) { }
        catch(java.util.concurrent.RejectedExecutionException e) {
            System.out.println("Tidak tau penyebabnya");
        }//end catch
    }//end method saveActionPerformed
    
    /**
     * Load the saved data
     * 
     * @param evt - event that reacting if load menu selected
     */
    private void loadActionPerformed(java.awt.event.ActionEvent evt) {
        filter = new FileNameExtensionFilter("*.ser", "ser");
        jFileChooser1 = new JFileChooser("./Saved data/");
        jFileChooser1.setFileFilter(filter);
        try {
            jFileChooser1.showOpenDialog(this);                                 //show open dialog
        }
        catch( java.awt.HeadlessException e ) {
            System.out.println("open dialog error");
        }
        try {
            String fileLocation = jFileChooser1.getSelectedFile().getPath();
            openFileLoad(fileLocation);                                         //open access for the selected file
            
            remove(canvas);                                                     //make JFrame remove the component canvas
            
            hewan = (Binatang) inputStream.readObject();                        //load the saved animal
             canvas = (CanvasLabirin) inputStream.readObject();                  //load the saved arena
              int[][] rgbHewan = (int[][]) inputStream.readObject();              //load the saved footprint of animal
               char[][] map = (char[][]) inputStream.readObject();            //load the saved image of maze
                hewan.setRGB(rgbHewan);                         //set footprint
                 canvas.setMap(map);
                 canvas.createLabirin();
               canvas.setAnimal(hewan);
              hewan.setLabirin(canvas);
              
              closeFileLoad();                        //close access for file
              jFileChooser1 = null;
            
            canvas.setBackground(new java.awt.Color(204, 255, 255));            //set atribute for arena
             canvas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
             canvas.setFocusable(false);
             canvas.setMaximumSize(new java.awt.Dimension(440, 440));
              canvas.setMinimumSize(new java.awt.Dimension(440, 440));
               canvas.setPreferredSize(new java.awt.Dimension(440, 440));
              canvas.setLayout(null);
             getContentPane().add(canvas);                                       //make JFrame add new component( canvas )
            canvas.setBounds(10, 139, 440, 440);
           canvas.repaint();                   //refresh the appearance
           
           MouseHandler handler = new MouseHandler();
                 canvas.addMouseListener( handler );
                  canvas.addMouseMotionListener( handler );
                  
           if( editor != null ) {               //if the editor have been occured before load, set the editor with new atribute
               editor.setCanvas(canvas);
               editor.setMap(canvas.getMap());
            }//end if
        }//end try
          catch( Exception e ) {System.out.println("CanvasLoad bermasalah.");}
    }//end method loadActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (mainFrame==null) mainFrame = new MainFrame();       //make new frame for application
                mainFrame.setVisible(true);   
            }
        });
    }
    
    /**
     * create the access for the file that used to save data
     * 
     * @fileLocation - the path of file's location
     */
    private void openFileSave( String fileLocation )
      {
         try                                                // open file
         {
            outputStream = new ObjectOutputStream(          //make new output stream for transfer data to outside the program
               new FileOutputStream( fileLocation ) );
         }                                                  // end try
         catch ( IOException ioException )
         {
            System.out.println( "Error opening file Save." );
         }                                                  // end catch
      }
    
    /**
     * close the access to file that used to save data
     */
    private void closeFileSave()
      {
         try                                                // close file
         {
            if ( outputStream != null )
               outputStream.close();                        //close the access for the file
         }                                                  // end try
         catch ( IOException ioException )
         {
            System.out.println( "Error closing file Save.");
        }                                                   // end catch
     }                                                      // end method closeFile
     
     /**
     * create the access for the file that used to load data
     * 
     * @fileLocation - the path of file's location
     */
     private void openFileLoad(String fileLocation)
     {
        try // open file
        {
           inputStream = new ObjectInputStream(            
              new FileInputStream( fileLocation ) );
        } // end try
        catch ( IOException ioException )
        {
           System.out.println( "Error opening file Load." );
        } // end catch
     } // end method openFile
     
     /**
     * close the access to file that used to load data
     */
     private void closeFileLoad()
     {
        try // close file and exit
        {
           if ( inputStream != null )
              inputStream.close();
        } // end try
        catch ( IOException ioException )
        {
           System.out.println( "Error closing file Load." );
        } // end catch
     } // end method closeFile


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CanvasLabirin canvas;
    private javax.swing.JButton ganti;
    private javax.swing.JButton informasi;
    private javax.swing.JTextField input;
    private javax.swing.JLabel labelInput;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelTugas;
    private javax.swing.JButton lakukan;
    private javax.swing.JButton openFile;
    private javax.swing.JLabel status;
    private javax.swing.JButton edit;
    // End of variables declaration//GEN-END:variables
    
    /**
     * the inner class that used if the Exception of maze occured
     */
    private class Ask {
        public Ask(java.awt.event.ActionEvent evt) {
            try{
                int answer = JOptionPane.showConfirmDialog(MainFrame.this, "Are you want to open new file?", "", JOptionPane.OK_CANCEL_OPTION);     //ask for open the new file
                if( answer == JOptionPane.OK_OPTION ) openFileActionPerformed(evt);
                else {
                    answer = JOptionPane.showConfirmDialog(MainFrame.this, "Are you want to open Labirin Editor?", "", JOptionPane.OK_CANCEL_OPTION);   //ask for open the labirin editor
                    if( answer == JOptionPane.OK_OPTION ) {
                        editor = new LabirinEditor(fileName, canvas);
                        editor.setVisible(true);
                    }
                }
            }
            catch ( NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Anda memilih untuk tidak membuka file yang baru.", "Information", 0);
            }
        }
    }
    
    private class MouseHandler extends MouseAdapter
    {
        //method that starting if mouse clicked
        public void mouseClicked( MouseEvent event )
        {
            if( !event.isAltDown() && !event.isMetaDown() )
             if( event.getClickCount() > 1 ) {
                 if( fileName != null ) {
                     editor = new LabirinEditor(fileName, canvas);
                     editor.setMap(canvas.getMap());
                     editor.refresh();
                     editor.setVisible(true);
                    }
                 else {
                     editor = new LabirinEditor(canvas);
                     editor.setMap(canvas.getMap());
                     editor.refresh();
                     editor.setVisible(true);
                    }
                }
        } // end method mouseClicked
    }
}
