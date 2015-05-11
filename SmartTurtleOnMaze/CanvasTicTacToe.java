import java.io.Serializable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.NullPointerException;
import ExceptionFolder.*;
/**
 * 
 * CanvasLabirin.java
 *
 * Class {@code CanvasLabirin} mendefinisikan tempat menggambarkan kura-kura. 
 * 
 * @author  DPBO 2008 @ Fasilkom UI
 *
 * Created on February 18, 2008, 2:14 PM
 * 
 */ 


public class CanvasTicTacToe extends javax.swing.JPanel implements Serializable {
    //private Binatang hewan;             //object of Binatang's class
    private int size = 20;              //the size of 1 pixel
    private int pix  = 20;
    private char[][] map;
    private String fileName;            //the name that opened by CanvasLabirin
    
    private Color tembokColor = Color.DARK_GRAY;        //the default color for wall
    private Color jalanColor = Color.CYAN;              //the default color for street
    private Color startColor = Color.WHITE;             //the default color for start
    private Color exitColor = Color.BLUE;               //the default color for exit
    private Color temp;
    private transient BufferedImage imgLabirin;      // object image untuk labirin
    
    private static int startX, startY;

    private static final char PLAIN = ' ';     // tanda pintu masuk
    private static final char XSIGN  = 'x';     // tanda pintu masuk
    private static final char YSIGN = 'y';    // tanda pintu keluar
    /** Creates new form CanvasLabirin
     * 
     * @param k - the animal from Binatang's class
     */
    
    public CanvasTicTacToe(Binatang k) {
        //hewan = k;
        defaultMap();           //set the contain of map with all wall
        initComponents();
        imgLabirin = new BufferedImage((size+2)*pix,(size+2)*pix,BufferedImage.TYPE_INT_ARGB);  //create new image for draw the maze
    }    
   
     /**
      * Creates new form CanvasLabirin
      */
     public CanvasTicTacToe() {
        initComponents();
        defaultMap();
        imgLabirin = new BufferedImage((size+2)*pix,(size+2)*pix,BufferedImage.TYPE_INT_ARGB);
    }
    
    private void defaultMap() {  //this method used to fill map with wall
        map = new char[size+2][size+2];
        for( int i=0; i<22; i++ ) {
			for( int j=0; j<22; j++ ) {
				map[i][j] = PLAIN;
			}
		}
    }
    
    /**
     * get the active animal
     * 
     * @return Binatang - the active animal
     */
    public Binatang getAnimal(){      //get the active animal in canvaslabirin
        return hewan;
    }
    
    /**
     * repaint the appearance
     * 
     * @param g - the Graphics
     */
    @Override
    public void paintComponent(java.awt.Graphics g){    //override the parent method
        super.paintComponent(g);
        try{
            Thread.sleep(50);
        }
        catch( Exception e ){}
        g.drawImage(imgLabirin,0,0,null);
        hewan.draw((java.awt.Graphics2D) g);
        try{
            Thread.sleep(50);
        }
        catch( Exception e ){}
        g.drawImage(imgLabirin,0,0,null);
        hewan.draw((java.awt.Graphics2D) g);
    }
    
    /**
     * create the maze with source from the array of char that exist in this object
     */
    public void createLabirin(){
        int i,j;
        Graphics2D g = imgLabirin.createGraphics();
        
        for(i = 0; i < map.length; i++){
                for(j = 0; j < map[i].length; j++){
                    if (map[i][j] == PLAIN)
                        g.setColor(tembokColor);
                    else if (map[i][j] == XSIGN)
                        g.setColor(startColor);
                    else if (map[i][j] == YSIGN)
                        g.setColor(exitColor);
                    else 
                        g.setColor(jalanColor);
                    g.fill3DRect(i*pix, j*pix, pix, pix, true); //make 3D rectangle for the pixel
                }
         }
    }
    // Hati-hati: method ini melakukan pembacaan file tanpa validasi isi file.
    /**
     * open the file
     * 
     * @param filename - the path of file's location
     * @return String
     */
    public String openLabirin(String filename) throws                           //this exception is in the folder namely "FolderException"
        FileNotFoundException, StringIndexOutOfBoundsException, IOException,
        NullPointerException, MoreStartException, MoreEndException,
        NoStartException, NoEndException, FormatException {
        fileName = filename;
        BufferedReader input;
        int statusStart = 0;
        int statusEnd = 0;
        boolean line = true;
        boolean format = true;
        try
        {
            input = new BufferedReader(new FileReader(filename));

            int i, j;
            int row = size;
            int column = size;
            String s;

            for(i = 0; i < map.length; i++){
                    for(j = 0; j < map[i].length; j++){
                            map[j][i] = TEMBOK;
                    }
            }

            for(i = 1; i <= row; i++){
                    s = input.readLine();
                    if( s == null ) line = false;           //check for row exception
                    for(j = 1; j <= column; j++){
                            map[j][i] = s.charAt(j - 1);
                            if (map[j][i]==MASUK){
                                statusStart++;              //check for start
                                startX=j;
                                startY=i;
                            }
                            if (map[j][i]==KELUAR)
                                statusEnd++;                //check for exit
                                
                            char z = map[j][i];
                            if( z!=' ' && z!='#' && z!='m' && z!='k' )
                            format = false;                 //check for format
                    }
            }
            if( !format ) {                                 //if the format error
                FormatException e = new FormatException();
                throw e;
            }
            if( !line ) {                                   //if the row is less
                StringIndexOutOfBoundsException e = new StringIndexOutOfBoundsException();
                throw e;
            }
            if( statusStart == 0 ) {                        //if no start
                NoStartException e = new NoStartException();
                throw e;
            }
            if( statusStart > 1 ) {                         //if start more than 1
                MoreStartException e = new MoreStartException();
                throw e;
            }
            if( statusEnd == 0 ) {                          //if no exit
                NoEndException e = new NoEndException();
                throw e;
            }
            if( statusEnd > 1 ) {                           //if exit more than 1
                MoreEndException e = new MoreEndException();
                throw e;
            }
                createLabirin();                                    //create maze
                hewan.setLabirin(this);                             //animal knowing new maze
                setAnimalPosition(startX, startY);    //set the position of animal to start position
                repaint();                                          //refresh the appearance
                return "File labirin: " + filename;
        }
        catch( FileNotFoundException e )        { throw e; }
        catch(StringIndexOutOfBoundsException e){ throw e; }
        catch( NullPointerException e )         { throw e; }
        catch( IOException e)                   { throw e; }
    }
    
    /**
     * make the animal set to position in index of maze
     * 
     * @param x - the index x in the maze
     * @param y - the index y in the maze
     */
    public void setAnimalPosition(int x, int y) {
        hewan.setPosition(new Dimension(x,y));
    }
    
    /**
     * check the condition of maze in the spesific area
     * 
     * @param x - the index of x map(array of char 2 dimesion)
     * @param y - the index of y map(array of char 2 dimesion)
     * @return boolean - true if the index(x,y) isn't wall, and otherwise
     */
    public boolean isValidPosition(int x, int y){
        return (map[x][y]!=TEMBOK);
    }
    
    /**
     * set the active animal with new animal
     * 
     * @param k - object from Binatang's class that will change the old animal
     */
    public void setAnimal( Binatang k ) {
        hewan = k;
    }
    
    /**
     * get current map
     * 
     * @return char[][] - the current map
     */
    public char[][] getMap() {
        char[][] mapTerbalik = new char[22][22];
        for ( int i=0; i<map.length; i++ )
         for ( int j=0; j<map[0].length; j++ )
            mapTerbalik[j][i] = map[i][j];
        return mapTerbalik;
    }
    
    /**
     * set the new map
     * 
     * @param chart - the new map
     */
    public void setMap(char[][] chart) {
        for ( int i=0; i<chart.length; i++ )
         for ( int j=0; j<chart[0].length; j++ )
            map[j][i] = chart[i][j];
         
         imgLabirin = new BufferedImage((size+2)*pix,(size+2)*pix,BufferedImage.TYPE_INT_ARGB);     //create new image for drawing
        }
    
    /**
     * change the color of wall with the JColorChooser
     */
    public void changeTembok() {
        temp = tembokColor;       //save the current color
        tembokColor = JColorChooser.showDialog(CanvasLabirin.this, "Choose a color of Wall", Color.DARK_GRAY );     //change the color with selected by user
                     if( tembokColor == null) tembokColor = temp;                                                   //if the color undefined, set with default
                     if( tembokColor.equals(jalanColor) || tembokColor.equals(startColor) || tembokColor.equals(exitColor) ) {  //if the color is same with other tool
                         tembokColor = temp;
                         temp = null;
                        }
                     checkColor();          //check the color, and repaint if success
                    }
                    
    /**
     * change the color of street with the JColorChooser
     */
    public void changeJalan() {
        temp = jalanColor;
        jalanColor = JColorChooser.showDialog(CanvasLabirin.this, "Choose a color of Street", Color.CYAN );
                     if( jalanColor == null) jalanColor = temp;
                     if( jalanColor.equals(tembokColor) || jalanColor.equals(startColor) || jalanColor.equals(exitColor) ) {
                         jalanColor = temp;
                         temp = null;
                        }
                     checkColor();
                    }
                    
    /**
     * change the color of start with the JColorChooser
     */
    public void changeStart() {
        temp = startColor;
        startColor = JColorChooser.showDialog(CanvasLabirin.this, "Choose a color of Start", Color.WHITE );
                     if( startColor == null) startColor = temp;
                     if( startColor.equals(jalanColor) || startColor.equals(tembokColor) || startColor.equals(exitColor) ) {
                         startColor = temp;
                         temp = null;
                        }
                     checkColor();
                    }
    /**
     * change the color of exit with the JColorChooser
     */                
    public void changeExit() {
        temp = exitColor;
        exitColor = JColorChooser.showDialog(CanvasLabirin.this, "Choose a color of Exit", Color.BLUE );
                     if( exitColor == null) exitColor = temp;
                     if( exitColor.equals(jalanColor) || exitColor.equals(startColor) || exitColor.equals(tembokColor) ) {
                         exitColor = temp;
                         temp = null;
                        }
                     checkColor();
                    }
                    
    private void checkColor() {         //method that check the successfull of change color of tool and rebuild the map with current color
        if( temp == null )
            JOptionPane.showMessageDialog(null, "Warna sama","",1);
        else {
            createLabirin();
            repaint();
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 255, 255));
        setMaximumSize(new java.awt.Dimension(440, 440));
        setMinimumSize(new java.awt.Dimension(440, 440));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
