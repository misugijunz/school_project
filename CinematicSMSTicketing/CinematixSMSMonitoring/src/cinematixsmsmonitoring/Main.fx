/*
 * Main.fx
 *
 * Created on 08 Des 09, 23:32:30
 */

package cinematixsmsmonitoring;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.lang.String;

/**
 * @author Oscar Kurniawan Manule
 */
def a = CustomFrameUI{}

Stage {
    title: "Cinematix SMS Server Monitoring"
    width: 515
    height: 635
    scene: Scene {
        content: [
            /*Text {
                font : Font {
                    size : 16
                }
                x: 10
                y: 30
                content: "Application content"
            }*/
            a
        ]
    }
}