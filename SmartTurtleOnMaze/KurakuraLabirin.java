import java.awt.Dimension;
import java.awt.Graphics2D; 
import java.awt.geom.AffineTransform; 
import java.awt.image.BufferedImage;
import java.util.Locale;

public class KurakuraLabirin extends Binatang{
    
    /** Creates a new instance of Kurakura */
    public KurakuraLabirin() {
      
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       
      imageName = bundle.getString("KurakuraLabirin");
        
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);   
    }
    
    /**
     * Creates a new instance of Kurakura
     *
     * @param b - the old animal that will give the attribute to new Kurakura
     */
    public KurakuraLabirin(Binatang b) {
      super(b);
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       
      imageName = bundle.getString("KurakuraLabirin"); 
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);
    }
    
    private void maju(double jarak){
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
     * make Kurakura go forward by 1 pixel
     * 
     * @return boolean - true if Kurakura did this command and successfully and otherwise
     */
    public boolean maju(){
        double dx,dy;
        dx = ukuran * (int)Math.cos(arah);
        dy = ukuran * (int)Math.sin(arah);
        
        int Xcheck = (int)(x + dx) / ukuran;
        int Ycheck = (int)(y + dy) / ukuran;
        if( labirin.isValidPosition(Xcheck,Ycheck) ) {   
            maju(ukuran);
            return true;
        }
        else{
            return false;
        }
    }
    
    private void mundur(double jarak){
        // lengkapi method ini untuk tugas 1
        maju(-jarak);
    }
    
    /**
     * make Kurakura go back by 1 pixel
     * 
     * @return boolean - true if Kurakura did this command and successfully and otherwise
     */
    public boolean mundur(){
        double dx,dy;
        dx = -(ukuran) * (int)Math.cos(arah);
        dy = -(ukuran) * (int)Math.sin(arah);
        
        int Xcheck = (int)(x + dx) / ukuran;
        int Ycheck = (int)(y + dy) / ukuran;
        if( labirin.isValidPosition(Xcheck,Ycheck) ) { 
            mundur(ukuran);          
            return true;
        }
        else{
            return false;
        }
    }
     
    /**
     * make the kurakura do their ability appropriate the input
     * 
     * @param input - the string of command
     * @return String - the condition after process the input
     */
    public String kerjakan( String input ) {
         String[] in = input.split(" ");
        if (in[0].equalsIgnoreCase("maju")){            //go forward by 1 pixel
                if (!(maju()))
                    return "Menabrak tembok";
        }
        else if (in[0].equalsIgnoreCase("mundur")){     //go back by 1 pixel
                if (!(mundur()))
                    return "Menabrak tembok";
        }
        else if (in[0].equalsIgnoreCase("kanan"))       //rotate to right
                kanan();
        else if (in[0].equalsIgnoreCase("kiri"))        //rotate to left
                kiri();
        else if (in[0].equalsIgnoreCase("carijalan")){  //search the exit with shortest path
                moveVector = new Dimension[4];
                moveVector[0] = new Dimension(1,0);
                moveVector[1] = new Dimension(0,1);
                moveVector[2] = new Dimension(0,-1);
                moveVector[3] = new Dimension(-1,0);
                String hasil = cariJalan( xl, yl );
            return hasil;
        }
        else
            return "Perintah tidak dipahami.";          //if the command is undefined
        return null;
    }
    
}
