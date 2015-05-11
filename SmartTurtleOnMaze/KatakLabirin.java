import java.awt.Dimension;
import java.awt.Graphics2D; 
import java.awt.geom.AffineTransform; 
import java.awt.image.BufferedImage;
/**
 * Write a description of class KatakLabirin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class KatakLabirin extends Binatang
{
    /** Creates a new instance of Kurakura */
    public KatakLabirin() {
      
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       
      imageName = bundle.getString("KatakLabirin");
        
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);      
    }
   
    /**
     * Creates a new instance of Katak
     *
     * @param b - the old animal that will give the attribute to new Katak
     */
   public KatakLabirin(Binatang b) {
      super(b);
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       
      imageName = bundle.getString("KatakLabirin");
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);
    }
   
   private void lompat(double jarak){
        double dx,dy;
        dx = jarak * (int)Math.cos(arah);
        dy = jarak * (int)Math.sin(arah);
        
        cetakJejak(x, y, x+dx, y+dy);
        
        x += dx;         
        y += dy;
        matTrans.setToTranslation(x,y);
        updateKoordinat(x,y);
    }
   
   /**
     * make Katak go forward by 2 pixel
     * 
     * @return boolean - true if Kurakura did this command and successfully and otherwise
     */
   public boolean lompat2(){
        double dx,dy;
        dx = 2* ukuran * (int)Math.cos(arah);
        dy = 2* ukuran * (int)Math.sin(arah);
        
        int Xcheck = (int)(x + dx) / ukuran;
        int Ycheck = (int)(y + dy) / ukuran;
        if( labirin.isValidPosition(Xcheck,Ycheck) ) {
            lompat(2*ukuran);
            return true;
        }
        else{
            return false;
        }
    }
   
   /**
     * make Katak go forward by 3 pixel
     * 
     * @return boolean - true if Kurakura did this command and successfully and otherwise
     */
   public boolean lompat3(){
        double dx,dy;
        dx = 3* ukuran * (int)Math.cos(arah);
        dy = 3* ukuran * (int)Math.sin(arah);
        
        int Xcheck = (int)(x + dx) / ukuran;
        int Ycheck = (int)(y + dy) / ukuran;
        if( labirin.isValidPosition(Xcheck,Ycheck) ) {
            lompat(3*ukuran);
            return true;
        }
        else{
            return false;
        }
    }
   
   /**
     * make the katak do their ability appropriate the input
     * 
     * @param input - the string of command
     * @return String - the condition after process the input
     */
    public String kerjakan( String input ) {
         String[] in = input.split(" ");
        if (in[0].equalsIgnoreCase("lompat2")){
                if (!(lompat2()))
                    return "Menabrak tembok";
        }
        else if (in[0].equalsIgnoreCase("lompat3")){
                try{
                    if (!(lompat3()))
                    return "Menabrak tembok";
                }
                catch(Exception e) {
                    return "Menabrak tembok";
                }
        }
        else if (in[0].equalsIgnoreCase("kanan"))
                kanan();
        else if (in[0].equalsIgnoreCase("kiri"))
                kiri();
        else if (in[0].equalsIgnoreCase("carijalan")){
                moveVector = new Dimension[8];
                moveVector[0] = new Dimension(2,0);
                moveVector[1] = new Dimension(0,2);
                moveVector[2] = new Dimension(0,-2);
                moveVector[3] = new Dimension(-2,0);
                moveVector[4] = new Dimension(3,0);
                moveVector[5] = new Dimension(0,3);
                moveVector[6] = new Dimension(0,-3);
                moveVector[7] = new Dimension(-3,0);
                String hasil = cariJalan( xl, yl );
            return hasil;
        }
        else
            return "Perintah tidak dipahami.";
        return null;
    }      
}
