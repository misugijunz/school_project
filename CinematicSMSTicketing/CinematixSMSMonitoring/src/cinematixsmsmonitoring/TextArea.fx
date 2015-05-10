/*
 * TextArea.fx
 *
 * Created on 09 Des 09, 6:29:28
 */

package cinematixsmsmonitoring;

import javafx.ext.swing.*;
import javax.swing.JComponent;
import javax.swing.JTextArea;

/**
 * @author Oscar Kurniawan Manule
 */

public class TextArea extends SwingScrollableComponent {

   

   override function createJComponent() : JComponent {
        new JTextArea();
   }

   public function getJTextArea() : JTextArea {
        getJComponent() as JTextArea;
   }
}
