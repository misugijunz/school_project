import java.io.Serializable;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform; 
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;
import javax.swing.JColorChooser;
import java.util.Stack;

/**
 * Abstract class Binatang - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Binatang implements Serializable
{
   private int angle = 4;
   private String[][] status = new String[22][22];
   private Color moveColor = Color.magenta;
   private transient PerintahLabirin perintah = new PerintahLabirin();
   protected int ukuran = 20;     //ukuran kelompok pixel
   protected int xl = 1, yl = 1;    // default awal posisi kura-kura di koordinat labirin
   protected int x = 200, y = 100;    // default awal posisi kura-kura dalam pixel
   protected int width = 440, height = 440;  // default ukuran layar/canvas
   protected double arah = 0; // arah 0 -> sumbu x 
   protected boolean jejak = true;    // status apakah membuat jejak atau tidak
   protected transient CanvasLabirin labirin;
   protected char[][] map;
   protected Dimension[] moveVector;
   protected Stack<Double> savePath = new Stack<Double>();
   
   protected String imageName; // default file name
   protected transient Image  img;        // object image kura-kura.  
   protected transient BufferedImage imgJejak;     // object image untuk jejak kura-kura
   protected AffineTransform matRotasi;  // mendefinisikan matriks rotasi
   protected AffineTransform matTrans;   // mendefinisikan matriks translasi      
   protected AffineTransform matGabung;  // mendefinisikan matriks gabungan

   /**
    * create new instance of Binatang
    */
   public Binatang() {
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       //get resource from kurakura.properties in this folder
      imageName = bundle.getString("KurakuraLabirin");                                          //get the string from title "KurakuraLabirin" in properties
      
      img = java.awt.Toolkit.getDefaultToolkit().getImage( imageName );                         //get the image
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);                 //set the size of image
      
      matRotasi = new AffineTransform();
      matGabung = new AffineTransform();
      matTrans = new AffineTransform();
    }
   
    /**
     * create new instance of Binatang
     * 
     * @param w - width for the area of foot print
     * @param h - height for the area of foot print
     */
   public Binatang(int w, int h){
        this();
        setSize(w,h);
        reset();
    }
   
   /**
    * create new instance of Binatang
    * 
    * @param c - the new object of Binatang that will change the position of old animal
    */
   public Binatang( Binatang c )
   {
       this();
       this.imgJejak = c.imgJejak;
       if( c != null )                  //copy all the attribute
       {
           xl = c.xl;
           yl = c.yl;
           matTrans = c.matTrans;
           matRotasi = c.matRotasi;
           matGabung = c.matGabung;
           x = c.x;
           y = c.y;
           arah = c.arah;
           jejak = c.jejak;
           width = c.width;
           height = c.height;
           angle = c.angle;
        }
   }
   
    /**
     * get the RGB from the foot print of animal
     * 
     * @return int[][] - the set of RGB in integer 2 dimension
     */
    public int[][] getRGB() {
       int[][] rgb = new int[440][440];
       for( int i=0; i<440; i++ )
        for( int j=0; j<440; j++ )
            rgb[i][j] = imgJejak.getRGB(i,j);
       return rgb;
        }
        
    /**
     * set the new RGB to old foot print
     * 
     * @param rgb - the set of new RGB
     */    
    public void setRGB( int[][] rgb ) {
        imgJejak = new BufferedImage(440,440,BufferedImage.TYPE_INT_ARGB);
        for( int i=0; i<440; i++ )
         for( int j=0; j<440; j++ )
          imgJejak.setRGB(i, j, rgb[i][j]);
          
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);
      
      perintah = new PerintahLabirin();
    }
   
   /**
    * clear the foot print of animal
    */
   public void newJejak() {
       imgJejak = new BufferedImage(440,440,BufferedImage.TYPE_INT_ARGB);
    }
    
   /**
    * make the animal know the new CanvasLabirin
    */
   public void setLabirin(CanvasLabirin cl){
        labirin = cl;
    }
   
   /**
    * set the size of area of foot print
    * 
    * @param w - width for the area of foot print
    * @param h - height for the area of foot print
    */
   public void setSize(int w, int h){
        width = w;
        height = h;
        imgJejak = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    }
   
   /**
    * make the animal set to left upper position
    */
   public void reset(){        
        x = 20;
        y = 20;
        matTrans.setToTranslation(x, y);
    }
   
   private void setAngle(int x) {                   //the method that calculate the angle of animal in maze
       if( x%4 == 0 ) setArah(0);
       else if ( x%4 == 1 ) setArah((Math.PI)/2);
       else if ( x%4 == 2 ) setArah(Math.PI);
       else setArah((Math.PI)*3/2);
       if( x%4 == 0 ) angle = 4;
    }
   
   /**
    * make the animal rotate -90 degree
    */
   protected void kiri(){
       angle--;
       setAngle(angle);
    }
   
   /**
    * make the animal rotate 90 degree
    */
   protected void kanan(){
       angle++;
       setAngle(angle);
    }
    
   /**
    * make animal draw the foot print or not
    * 
    * @param v - if v is true, the animal will draw the foot print, and otherwise
    */
   public void setJejak(boolean v){
        jejak = v;
    }
   
   /**
    * make animal draw the condition of his/her-self
    */
   public void draw(Graphics2D g)
    {      
      matGabung.setToIdentity();     
      matGabung.concatenate(matTrans);            
      matGabung.concatenate(matRotasi); 
      if (imgJejak!=null)
          g.drawImage(imgJejak,ukuran/2,ukuran/2,null);
      if (img!=null)
          g.drawImage(img, matGabung, null);
    }
   
   /**
    * get position of animal
    * 
    * @return Dimension - where the width of Dimension is index X, and height for index Y
    */
   public Dimension getPosition(){
        return new Dimension(x,y);
    }
   
   /**
    * set the position of animal
    * 
    * @param pos - the new Dimension that animal will set to this location, where the width of Dimension is index X, and height for index Y
    */
   public void setPosition(Dimension pos){
        xl = (int) pos.getWidth();
        yl = (int) pos.getHeight();
        x = xl *ukuran;
        y = yl *ukuran;
        matTrans.setToTranslation(x, y);
    }
   
   /**
    * change the color of foot print
    */ 
   public void changeColorMove() {
       moveColor = JColorChooser.showDialog(new Canvas(), "Choose a color of Exit", Color.magenta );
    }
    
   /**
    * update the field of xl and yl
    * 
    * @param x - this variable will syncronized with xl
    * @param y - this variable will syncronized with yl
    */
   protected void updateKoordinat(int x, int y){
        xl = x /ukuran;
        yl = y /ukuran;
    }
   
   /**
    * get the angle of animal
    * 
    * @return double - the angle of animal with right is 0 degree and rotate as clock
    */
   protected double getArah(){
        return arah;
    }
   
   /**
    * set the angle of animal
    * 
    * @param a - the angle of animal
    */
   protected void setArah(double a){
        arah =a;
        matRotasi.setToRotation(arah,img.getWidth(null)/2,img.getHeight(null)/2); // rotasi dihitung dari pusat image.              
    }
   
   /**
    * draw the foot print of animal's movement
    */
   protected void cetakJejak( int x, int y, double a, double b ) {
       if (jejak){
            Graphics2D g = imgJejak.createGraphics();
            g.setColor(moveColor);
            g.draw(new java.awt.geom.Line2D.Double(x,y,a,b));
        }
    }
   
   /**
    * method that will override by subclass of Binatang, where contain the ability of each animal
    * 
    * @param input - the command that knowed by active animal
    */
   abstract String kerjakan( String input );
   
   /**
    * method that will make the animal do the command
    * 
    * @param inputPerintah - the string of command
    * @return String - the string of situation of animal after do the command
    */
   public String lakukan(String inputPerintah) {
       String ret = perintah.lakukan(inputPerintah);
       return ret;
    }
    
   private void getMapFromLabirin() {
       map = labirin.getMap();
    }
   
   private int GetNodeNo(int Xindex, int Yindex) {
        return Xindex*map[0].length + Yindex;
    }
   
   /**
    * draw the foot print of animal with special trick
    */
   private void drawPath(int pX, int pY){             //this method used to draw the line from old x,y to new x,y
         if (jejak){
            Graphics2D g = imgJejak.createGraphics();
            g.setColor(moveColor);
            g.draw(new java.awt.geom.Line2D.Double(xl*ukuran,yl*ukuran,pX*ukuran,pY*ukuran));
        }
        xl=pX;
        yl=pY;
        x=xl*ukuran;
        y=yl*ukuran;
        matTrans.setToTranslation(x, y);
        labirin.repaint();
    }
   
   /**
    * this method will make the animal go to exit if exist with shortest path
    * 
    * @param yOrigin - the y index in map
    * @param yOrigin - the y index in map
    * @return String - the condition after do this command
    */
   protected String cariJalan(int yOrigin, int xOrigin) {
       int node = 0;
       int nodeStart = 0;
       int nodeEnd = 0;
       int pAwal = 0;
       int pAkhir = 0;
       int[] queue = new int[500];
       int[] origin = new int[500];
       
       getMapFromLabirin();
       Dimension[] dimensi = new Dimension[484];
       for( int i=0; i<map.length; i++ )
        for( int j=0; j<map[0].length; j++ )
           {
               node = GetNodeNo( i,j );
               dimensi[node] = new Dimension(i,j);
               status[i][j] = new String("READY");
           }
       for( int i=0; i<map.length; i++ )
        for( int j=0; j<map[0].length; j++ )
         {
             if( map[i][j] == 'k' )
                 nodeEnd = GetNodeNo( i,j );
         }

       nodeStart = GetNodeNo ( xOrigin, yOrigin );
       if( nodeStart == nodeEnd )
         return "Diam di tempat.";
                    //System.out.println("nilai nodeStart adalah " + nodeStart);
       queue[pAkhir] = nodeStart;
                    //System.out.println("nilai queue[pAkhir] adalah " + queue[pAkhir]);
                    //System.out.println("nilai queue[pAwal] adalah " + queue[pAwal]);
       pAkhir++;
                    //System.out.println("nilai pAkhir adalah " + pAkhir);
       origin[nodeStart] = -1;
                    //System.out.println("nilai origin[nodeStart] adalah " + origin[nodeStart]);
                    //System.out.println("nilai pAwal adalah " + pAwal);
       while ( queue[pAwal] != nodeEnd )
       {
           int x = (int) dimensi[queue[pAwal]].getWidth();
           int y = (int) dimensi[queue[pAwal]].getHeight();
           
           for ( int move=0; move<moveVector.length; move++ )
           {
               int dx = (int) moveVector[move].getWidth();
               int dy = (int) moveVector[move].getHeight();
                            //System.out.println(x + " " + y + " "+ dx + " " + dy);
               status[x][y] = "DONE";
               if( (x+dx) < 22 && (x+dx) > -1 && (y+dy) < 22 && (y+dy) > -1 )
               if( map[x+dx][y+dy] != '#' && status[x+dx][y+dy].equalsIgnoreCase("READY") )
               {
                   //System.out.println("masuk");
                   
                   status[x+dx][y+dy] = "WAITING";
                   node = GetNodeNo( x+dx, y+dy );
                   queue[pAkhir] = node;
                   pAkhir++;
                   origin[node] = queue[pAwal];

               }
            }
            pAwal++;
            if( pAwal == pAkhir )
             return "Labirin Buntu.";
       }
       
       savePath.push(dimensi[nodeEnd].getWidth());
       savePath.push(dimensi[nodeEnd].getHeight());
       int ff = nodeEnd;
       while ( origin[ff] != nodeStart )
       {
           int ori = origin[ff];
           savePath.push(dimensi[ori].getWidth());
           savePath.push(dimensi[ori].getHeight());
           ff = ori;
        }
        int count = 0;
        while ( ! savePath.empty() )
        {
            double x = savePath.pop();
            double y = savePath.pop();
            drawPath( (int)x, (int)y );
            count++;
        }
       return ("Jalan keluar ditemukan dengan membutuhkan jumlah langkah: " + count);
    }
    
    private class PerintahLabirin {
        public String lakukan(String inputPerintah){
            inputPerintah =  kerjakan(inputPerintah);
            labirin.repaint();
            if( inputPerintah == null )
                return "Perintah sudah dilaksanakan.";
            else return inputPerintah;
        } 
    }
   
}
