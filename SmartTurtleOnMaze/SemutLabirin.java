import java.awt.Dimension;
import java.awt.Graphics2D; 
import java.awt.geom.AffineTransform; 
import java.awt.image.BufferedImage;

public class SemutLabirin extends Binatang{
    
    /** Creates a new instance of Kurakura */
    public SemutLabirin() {
      
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       
      imageName = bundle.getString("SemutLabirin");
        
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);   
    }
    
    public SemutLabirin(Binatang b) {
      super(b);
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");       
      imageName = bundle.getString("SemutLabirin"); 
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      img = img.getScaledInstance(ukuran, ukuran, java.awt.Image.SCALE_SMOOTH);
    }
    
    private void majuSemut(double jarak){
        double dx,dy;
        dx = jarak * (int)Math.cos(arah);
        dy = jarak * (int)Math.sin(arah);
        
        x += dx;         
        y += dy;
        updateKoordinat(x,y);
    }
     
    public boolean majukanan() {
        double dxl = ukuran * (int)Math.cos(arah) /20;
        double dyl = ukuran * (int)Math.cos(arah) /20;
        int Px = xl;
        int Py = yl;
        if( labirin.isValidPosition( (int)(xl+dxl), (int)(yl+dyl) ) ) {
            majuSemut(ukuran);
            kanan();
            majuSemut(ukuran);
            kiri();
            if (jejak){
              Graphics2D g = imgJejak.createGraphics();
              g.draw(new java.awt.geom.Line2D.Double(Px*ukuran,Py*ukuran,xl*ukuran,yl*ukuran));
            }
            matTrans.setToTranslation(x,y);
            return true;
        }
        else return false;
    }
    
    public boolean majukiri() {
        double dxl = ukuran * (int)Math.cos(arah) /20;
        double dyl = -ukuran * (int)Math.cos(arah) /20;
        int Px = xl;
        int Py = yl;
        if( labirin.isValidPosition( (int)(xl+dxl), (int)(yl+dyl) ) ) {
            majuSemut(ukuran);
            kiri();
            majuSemut(ukuran);
            kanan();
            if (jejak){
              Graphics2D g = imgJejak.createGraphics();
              g.draw(new java.awt.geom.Line2D.Double(Px*ukuran,Py*ukuran,xl*ukuran,yl*ukuran));
            }
            matTrans.setToTranslation(x,y);
            return true;
        }
        else return false;
    }
        
    public String kerjakan( String input ) {
         String[] in = input.split(" ");
        if (in[0].equalsIgnoreCase("majukanan")){
                if (!(majukanan()))
                    return "Menabrak tembok";
        }
        else if (in[0].equalsIgnoreCase("majukiri")){
                if (!(majukiri()))
                    return "Menabrak tembok";
        }
        else if (in[0].equalsIgnoreCase("kanan"))
                kanan();
        else if (in[0].equalsIgnoreCase("kiri"))
                kiri();
        else if (in[0].equalsIgnoreCase("carijalan")){
                moveVector = new Dimension[4];
                moveVector[0] = new Dimension(1,1);
                moveVector[1] = new Dimension(-1,1);
                moveVector[2] = new Dimension(-1,-1);
                moveVector[3] = new Dimension(1,-1);
                String hasil = cariJalan( xl, yl );
            return hasil;
        }
        else
            return "Perintah tidak dipahami.";
        return null;
    }
     
}
